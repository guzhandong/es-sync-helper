package com.guzhandong.es.helper.esapi.optional;

import com.google.common.collect.Maps;
import com.guzhandong.es.helper.esapi.Response.Indice;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class RemoveAllAliasAndAddAlias extends AbstractOptional {

    @Override
    public Object exec(String[] args) {
        removeAllAliasAndAddAlias(args[0],args[1]);
        return true;
    }

    /**
     * 删除已有的本别名，并对指定索引添加本别名. <br>
     * @param indexName
     * @param newAlias
     * @return void
     * @throws
     * @author guzhandong
     * @date 2019-05-14 21:38
     **/
    public void removeAllAliasAndAddAlias(String indexName, final String newAlias) {

        //索引名字和别名的映射
        final Map<String, String> aliasMap = Maps.newHashMap();
        //遍历索引
        Iterator<Map.Entry<String, Indice>> indexIterator = getIndiceMap().entrySet().iterator();
        while (indexIterator.hasNext()) {
            Map.Entry<String, Indice> indice = indexIterator.next();
            //遍历别名
            List<String> aliases = indice.getValue().getAliases();
            for (int i = 0; i < aliases.size(); i++) {
                if (newAlias.equals(aliases.get(i))) {
                    aliasMap.put(indice.getKey(), aliases.get(i));
                }
            }
        }

        //删除别名，不包含刚添加的
        Iterator<Map.Entry<String,String>> iterator = aliasMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,String> entry = iterator.next();
            Object a = esApi.removeAlias(entry.getKey(),entry.getValue());
        }

        //添加别名
        esApi.addAlias(indexName,newAlias);
    }
}
