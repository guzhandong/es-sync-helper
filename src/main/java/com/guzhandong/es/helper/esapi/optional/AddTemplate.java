package com.guzhandong.es.helper.esapi.optional;

import com.google.common.collect.Maps;
import com.google.gson.GsonBuilder;
import com.guzhandong.es.helper.esapi.dto.TemplateMapDto;
import com.knowlegene.commons.utils.FileUtils;
import com.knowlegene.commons.utils.JSONUtil;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AddTemplate extends AbstractOptional{

    @Override
    public Object exec(String[] args) {
        addTemplates(args[1]);
        return true;
    }


    public void addTemplates(String inputFilePath) {
        String mappingStr = FileUtils.intputFile(inputFilePath);
        TemplateMapDto templateMapDto = new GsonBuilder().create().fromJson(mappingStr,TemplateMapDto.class);
        Map<String,Object> resMap = Maps.newHashMapWithExpectedSize(templateMapDto.size());
        templateMapDto.entrySet().forEach(entry->{
            Map<String,Object> res = esApi.addTemplateObj(entry.getKey(),JSONUtil.convertToJson(entry.getValue()));
            resMap.put(entry.getKey(),res);

        });
    }
}
