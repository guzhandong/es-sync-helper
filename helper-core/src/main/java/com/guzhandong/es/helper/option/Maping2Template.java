package com.guzhandong.es.helper.option;


import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.guzhandong.es.helper.esapi.Response.Indice;
import com.knowlegene.commons.utils.FileUtils;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

@Component("maping2Template")
public class Maping2Template extends AbstractOption{



    @Override
    public Object exec(String[] args) {
        if (args.length>1){
            maping2Template(args[0],args[1]);
        } else {
            maping2Template(args[0],null);
        }

        return true;
    }

    public void maping2Template(String outFilePath, String patternStr) {
//        Pattern pattern = Pattern.compile(patternStr);
//        pattern.matcher("")
        //遍历索引
        Iterator<Map.Entry<String, Indice>> indexIterator =getIndiceMap().entrySet().iterator();

        Map<String,Object> templateMap = Maps.newHashMap();

        while (indexIterator.hasNext()) {
            Map.Entry<String, Indice> indice = indexIterator.next();
            Map<String,Object> mappings = indice.getValue().getMappings();

            //添加到模板集合
            templateMap.put(indice.getKey()+DEFAULT_TEMPLATE_SUFFIX,buildTemplate(indice.getKey(),mappings));
        }
        FileUtils.outputFile(outFilePath, new Gson().toJson(templateMap),false);
//        FileUtils.outputFile(outFilePath, JSONUtil.convertToJson(templateMap),false);
    }

}
