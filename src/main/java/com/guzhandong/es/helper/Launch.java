package com.guzhandong.es.helper;


import com.guzhandong.es.helper.esapi.EsApiFactory;

public class Launch {

    public static void main(String[] args) {
//        args = new String[]{"http://192.168.100.102:9210","kg.punish","aaaa"};


        String note = "args : es_api_url      index_name      alias_name    ";
        String eample = "example :http://192.168.100.102:9200    kg.group_2018        kg.group ";
        String dd =  "===========================================================================";
        System.out.println(dd);
        System.out.println(note);
        System.out.println(eample);
        System.out.println(dd);
        if (args == null || args.length<3) {
            System.err.println("plase enter the  parameter!");
            System.exit(1);
        }

        String esHost = args[0];
        String index = args[1];
        String alias = args[2];

        EsHelper esHelper = new EsHelper(EsApiFactory.buildClient(esHost));
        esHelper.removeAllAliasAndAddAlias(index,alias);
        System.out.println("complate");
        System.out.println(dd);
    }

}
