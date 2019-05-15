package com.guzhandong.es.helper;


import com.guzhandong.es.helper.esapi.optional.IOptional;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class LaunchCommand {

    public static void main(String[] args) {

//        args = new String[]{"http://192.168.100.102:9210","removeAllAliasAndAddAlias","kg.punish","aaaa"};
        args = new String[]{"http://192.168.100.102:9210","maping2Template","data/templates.txt"};

        String apiUrl = args[0];
        String optional = args[1];
        Constans.ES_PAI_URL =  apiUrl;

        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        IOptional optionalInstance = applicationContext.getBean(optional, IOptional.class);
        String [] optionArgs = Arrays.copyOfRange(args,2,args.length);
        Object res = optionalInstance.exec(optionArgs);
        applicationContext.close();
        System.out.println("complate");
    }

}
