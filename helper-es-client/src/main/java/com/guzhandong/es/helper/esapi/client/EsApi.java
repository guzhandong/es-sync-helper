package com.guzhandong.es.helper.esapi.client;


import com.guzhandong.es.helper.esapi.Response.Alias;
import com.guzhandong.es.helper.esapi.Response.ClusterStstes;
import com.guzhandong.es.helper.esapi.dto.TemplateMapDto;
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
    public TemplateMapDto getTemplates();

    @RequestLine("DELETE /_template/{name}")
    public Map<String,Object> removeTemplates(@Param("name") String templateName);



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


    /**
     * 给索引添加别名. <br>
     * @param indexName  :
     * @param indexAlias :
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @throws
     * @author guzhandong
     * @date 2019-05-28 22:05
     **/
    @RequestLine("POST /_aliases")
    @Headers("Content-Type: application/json")
    @Body("%7B\"actions\":[%7B\"add\":%7B\"index\":\"{index_name}\",\"alias\":\"{index_alias}\"%7D%7D]%7D")
    public Map<String,Object> addAlias(
            @Param("index_name") String indexName
            , @Param("index_alias") String indexAlias);


    /**
     * 获取全部索引别名. <br>
     *
     * @return
     * @throws
     * @author guzhandong
     * @date 2019-05-28 22:04
     **/
//    @RequestLine("GET /_aliases")
//    public String getAlias();
    @RequestLine("GET /_aliases")
    public Alias getAlias();



    /**
     * 删除索引的别名. <br>
     * @param indexName  : 索引名称
     * @param indexAlias :  别名名称
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @throws
     * @author guzhandong
     * @date 2019-05-28 22:04
     **/
    @RequestLine("POST /_aliases")
    @Body("%7B\"actions\":[%7B\"remove\":%7B\"index\":\"{indexName}\",\"alias\":\"{indexAlias}\"%7D%7D]%7D")
    public  Map<String,Object> removeAlias(
            @Param("indexName") String indexName
            , @Param("indexAlias") String indexAlias);

    /**
     *  删除索引. <br>
     * @param indexName :
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @throws
     * @author guzhandong
     * @date 2019-05-28 22:03
     **/
    @RequestLine("DELETE /{indexName}")
    public  Map<String,Object> removeIndex(
            @Param("indexName") String indexName)
    ;

}
