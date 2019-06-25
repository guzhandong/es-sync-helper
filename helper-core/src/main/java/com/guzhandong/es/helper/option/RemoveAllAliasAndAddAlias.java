package com.guzhandong.es.helper.option;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class RemoveAllAliasAndAddAlias extends AbstractOption {

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
        getIndiceMap().entrySet().forEach(indice->{
            //遍历别名
            List<String> aliases = indice.getValue().getAliases();
            aliases.forEach(index->{
                if (newAlias.equals(index)) {
                    aliasMap.put(indice.getKey(), index);
                }
            });
        });

        //删除别名，不包含刚添加的
        aliasMap.entrySet().forEach(entry->{
            Object a = esApi.removeAlias(entry.getKey(),entry.getValue());
        });

        //添加别名
        esApi.addAlias(indexName,newAlias);
    }
}
