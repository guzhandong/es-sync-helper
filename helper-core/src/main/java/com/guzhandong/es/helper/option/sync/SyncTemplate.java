package com.guzhandong.es.helper.option.sync;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.guzhandong.es.helper.BaseParams;
import com.guzhandong.es.helper.esapi.dto.TemplateMapDto;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 获取所有模板. <br>
 *
 * @author guzhandong
 * @date 2019-05-27 16:04
 * @version [v1.0]
 * @since [jdk 1.8]
 **/
@Component("syncTemplate")
public class SyncTemplate extends SyncAbstractOption<BaseParams,TemplateMapDto, Map<String,Object>> {

    private Gson gson = new Gson();

    @Override
    protected TemplateMapDto getInputByEs() {
        return esApi.getTemplates();
    }

    @Override
    protected Object outputDataToEs(Map<String, Object> templateMapDto) {
        Map<String,Object> resMap = Maps.newHashMapWithExpectedSize(templateMapDto.size());
        templateMapDto.entrySet().forEach(entry->{
            Map<String,Object> res = esApi.addTemplateObj(entry.getKey(),gson.toJson(entry.getValue()));
            resMap.put(entry.getKey(),res);
        });
        return null;
    }

}
