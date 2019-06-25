package com.guzhandong.es.helper.option.sync;

import com.google.common.collect.Maps;
import com.guzhandong.es.helper.BaseParams;
import com.guzhandong.es.helper.esapi.Response.ClusterStstes;
import com.guzhandong.es.helper.esapi.Response.Indice;
import com.guzhandong.es.helper.esapi.client.EsApi;
import com.guzhandong.es.helper.esapi.dto.TemplateDto;
import com.knowlegene.commons.utils.FileUtils;
import com.knowlegene.commons.utils.JSONUtil;

import javax.annotation.Resource;
import java.util.Map;

public abstract class SyncAbstractOption<A extends BaseParams,R,P> implements ISyncOption,IResult {

    @Resource
    protected EsApi esApi;

    protected A  params;

    protected Class<R> resultClass;

    protected Class<P>  para;

    protected Object  t;

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

    protected static final String DEFAULT_TEMPLATE_SUFFIX = "_template";

    protected static final int DEFAULT_TEMPLATE_ORDER = 0;


    @Override
    public Object exec(BaseParams params) {
        Object res = getInput();
        outputData((P)res);
        return null;
    }

    protected Object getInput() {
        String inputPara = params.getInput();
        boolean res = isEs(inputPara);
        if (res){
            return getInputByEs();
        } else {
            return getInputByFile();
        }
    }

    protected boolean isEs(String uri) {
        boolean isHttp = uri.startsWith("http://");
        return isHttp;
    }

    protected R getInputByFile() {
        String dataJson = FileUtils.intputFile(params.getInput());
//        return JSONUtil.parseJson(resultClass,dataJson);
        return null;
    }

    protected abstract R getInputByEs();

    protected Object outputData(P data) {
        String output = params.getOutput();
        boolean res = isEs(output);
        if (res){
            return outputDataToEs(data);
        } else {
            return outputDataToFile(data);
        }
    }

    protected Object outputDataToFile(P data){
        FileUtils.outputFile(params.getOutput(),JSONUtil.convertToJson(data),false);
        return true;
    }

    protected abstract Object outputDataToEs(P data);

}
