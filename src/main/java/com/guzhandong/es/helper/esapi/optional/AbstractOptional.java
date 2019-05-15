package com.guzhandong.es.helper.esapi.optional;

import com.google.common.collect.Maps;
import com.guzhandong.es.helper.esapi.EsApi;
import com.guzhandong.es.helper.esapi.Response.ClusterStstes;
import com.guzhandong.es.helper.esapi.Response.Indice;
import com.guzhandong.es.helper.esapi.dto.TemplateDto;

import javax.annotation.Resource;
import java.util.Map;

public abstract class AbstractOptional implements IOptional {

    @Resource
    protected EsApi esApi;


    public Map<String, Indice> getIndiceMap() {
        ClusterStstes res = esApi.getClusterStats();
        return res.getMetadata().getIndices();
    }

    public TemplateDto buildTemplate(String index, Object mapping) {
        TemplateDto templateDto = new TemplateDto();
        templateDto.setMappings(mapping);
        templateDto.setOrder(DEFAULT_TEMPLATE_ORDER);
        templateDto.setSettings(Maps.newHashMapWithExpectedSize(0));
        templateDto.setTemplate(index+"*");
        return templateDto;
    }
    private static final String DEFAULT_TEMPLATE_SUFFIX = "_template";

    private static final int DEFAULT_TEMPLATE_ORDER = 0;


}
