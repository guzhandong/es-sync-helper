package com.guzhandong.es.helper.option;

import com.google.gson.Gson;
import com.guzhandong.es.helper.esapi.dto.TemplateMapDto;
import com.knowlegene.commons.utils.FileUtils;
import org.springframework.stereotype.Component;

/**
 * 获取所有模板. <br>
 *
 * @author guzhandong
 * @date 2019-05-27 16:04
 * @version [v1.0]
 * @since [jdk 1.8]
 **/
@Component("getTemplate")
public class GetTemplate extends AbstractOption{

    private Gson gson = new Gson();

    @Override
    public Object exec(String[] args) {
        getTemplates(args[0], null);
        return true;
    }


    public void getTemplates(String outputFilePath,String patters) {
        TemplateMapDto templateMapDto = esApi.getTemplates();
        FileUtils.outputFile(outputFilePath,gson.toJson(templateMapDto),false);
    }
}
