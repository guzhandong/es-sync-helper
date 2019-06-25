package com.guzhandong.es.helper.option;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.guzhandong.es.helper.esapi.dto.TemplateMapDto;
import com.knowlegene.commons.utils.FileUtils;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component("addTemplate")
public class AddTemplate extends AbstractOption{

    @Override
    public Object exec(String[] args) {
        addTemplates(args[0]);
        return true;
    }




    public void addTemplates(String inputFilePath) {
        Gson gson = new Gson();
        String mappingStr = FileUtils.intputFile(inputFilePath);
//        mappingStr.replace("\\u2002","");
//        mappingStr.replace("\\u2003","");
//        mappingStr.replace("\\u00a0","");
//        mappingStr.replace("\\n","");
        TemplateMapDto templateMapDto = new GsonBuilder().create().fromJson(mappingStr,TemplateMapDto.class);
        Map<String,Object> resMap = Maps.newHashMapWithExpectedSize(templateMapDto.size());
        templateMapDto.entrySet().forEach(entry->{
            //TODO 临时处理，修复数据中没有该字段，导致不能正常导入
            if (entry.getValue().getIndex_patterns()==null) {
                entry.getValue().setIndex_patterns(entry.getKey()+"*");
            }
            Map<String,Object> res = esApi.addTemplateObj(entry.getKey(),gson.toJson(entry.getValue()));
            resMap.put(entry.getKey(),res);

        });
    }
}
