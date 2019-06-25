package com.guzhandong.es.helper;


import com.google.gson.Gson;
import com.guzhandong.es.helper.option.IOption;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class LaunchCommand {

    public static void main(String[] args) {

        if(args.length==0) {

//        args = new String[]{"http://192.168.100.102:9210","removeAllAliasAndAddAlias","kg.punish","aaaa"};
//        args = new String[]{"http://192.168.100.102:9210","maping2Template","data/templates.txt"};
//        args = new String[]{"http://192.168.100.102:9200","addTemplate","data/templates_test.txt"};
//        args = new String[]{"http://localhost:9200","addTemplate","data/templates_test.txt"};
//        args = new String[]{"http://192.168.100.102:9210","addTemplate","data/zy_es_templates.txt"};
//        args = new String[]{"http://192.168.100.102:9210","removeIndex"};

//            args = new String[]{"http://192.168.100.102:9210","getTemplate","data/templates_dump.txt"};
//            args = new String[]{"http://localhost:9200","removeIndex","5"};
//            args = new String[]{"http://localhost:9200","removeExpriedIndex","5"};
        }

        String apiUrl = args[0];
        String optional = args[1];
        Constans.ES_PAI_URL =  apiUrl;

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        IOption optionalInstance = applicationContext.getBean(optional, IOption.class);
        String [] optionArgs = Arrays.copyOfRange(args,2,args.length);
        Object res = optionalInstance.exec(optionArgs);
        applicationContext.close();
        System.out.println("result : " +new Gson().toJson(res));
        System.out.println("complate");
    }

}
