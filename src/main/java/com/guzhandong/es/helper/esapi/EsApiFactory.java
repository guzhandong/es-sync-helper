package com.guzhandong.es.helper.esapi;

import com.guzhandong.es.helper.Constans;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class EsApiFactory extends AbstractFactoryBean<EsApi> {

    public static EsApi buildClient(String url) {
        EsApi esApi = Feign.builder()
//                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(EsApi.class, url);
        return esApi;
    }


    @Override
    public Class<?> getObjectType() {
        return EsApi.class;
    }

    @Override
    protected EsApi createInstance() throws Exception {
        return buildClient(Constans.ES_PAI_URL);
    }
}
