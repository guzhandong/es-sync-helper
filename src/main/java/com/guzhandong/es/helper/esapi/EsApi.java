package com.guzhandong.es.helper.esapi;


import com.guzhandong.es.helper.esapi.Response.ClusterStstes;
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.Map;

@Headers("Content-Type: application/json")
public interface EsApi {


    @RequestLine("GET /_stats")
    public String getStats();


    @RequestLine("GET /_cluster/state")
    public ClusterStstes getClusterStats();
//    public JsonObject getClusterStats();

    @RequestLine("GET /_template")
    public String getTemplates();


    @RequestLine("PUT /_template/{name}")
    @Body("{\n" +
            "    \"template\" : \"{pattern}\",\n" +
            "    \"order\" : {order},\n" +
            "    \"settings\" : {settings},\n" +
            "    \"mappings\" : {mappings}\n" +
            "}")
    public String addTemplate(
            @Param("pattern") String pattern,
            @Param("order") int order,
            @Param("settings") String settings,
            @Param("mappings") String mappings);

    @RequestLine("PUT /_template/{name}")
    @Body("{data}")
    public Map<String,Object> addTemplateObj(
            @Param("name") String templateName,
            @Param("data") String template
    );


    @RequestLine("POST /_aliases")
    @Headers("Content-Type: application/json")
    @Body("%7B\"actions\":[%7B\"add\":%7B\"index\":\"{index_name}\",\"alias\":\"{index_alias}\"%7D%7D]%7D")
    public Map<String,Object> addAlias(
            @Param("index_name") String indexName
            ,@Param("index_alias") String indexAlias);


    @RequestLine("GET /_aliases")
    public String getAlias();



    @RequestLine("POST /_aliases")
    @Body("%7B\"actions\":[%7B\"remove\":%7B\"index\":\"{indexName}\",\"alias\":\"{indexAlias}\"%7D%7D]%7D")
    public  Map<String,Object> removeAlias(
            @Param("indexName") String indexName
            ,@Param("indexAlias") String indexAlias);

}
