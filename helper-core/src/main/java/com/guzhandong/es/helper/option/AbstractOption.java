package com.guzhandong.es.helper.option;

import com.google.common.collect.Maps;
import com.guzhandong.es.helper.esapi.Response.ClusterStstes;
import com.guzhandong.es.helper.esapi.Response.Indice;
import com.guzhandong.es.helper.esapi.client.EsApi;
import com.guzhandong.es.helper.esapi.dto.TemplateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Map;

public abstract class AbstractOption implements IOption {

    protected static final String DEFAULT_TEMPLATE_SUFFIX = "_template";

    protected static final int DEFAULT_TEMPLATE_ORDER = 0;

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    protected EsApi esApi;

    protected Map<String, Indice> getIndiceMap() {
        ClusterStstes res = esApi.getClusterStats();
        return res.getMetadata().getIndices();
    }

    protected TemplateDto buildTemplate(String index, Object mapping) {
        TemplateDto templateDto = new TemplateDto();
        templateDto.setMappings(mapping);
        templateDto.setOrder(DEFAULT_TEMPLATE_ORDER);
        templateDto.setSettings(Maps.newHashMapWithExpectedSize(0));
        templateDto.setTemplate(index+"*");
        templateDto.setIndex_patterns(index+"*");
        return templateDto;
    }

}
