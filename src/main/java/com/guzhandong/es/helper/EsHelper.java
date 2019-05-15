package com.guzhandong.es.helper;

import com.google.common.collect.Maps;
import com.guzhandong.es.helper.esapi.EsApi;
import com.guzhandong.es.helper.esapi.Response.ClusterStstes;
import com.guzhandong.es.helper.esapi.Response.Indice;
import com.guzhandong.es.helper.esapi.dto.TemplateDto;
import com.knowlegene.commons.utils.FileUtils;
import com.knowlegene.commons.utils.JSONUtil;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class EsHelper {

    private EsApi esApi;

    public EsHelper(EsApi esApi) {
        this.esApi = esApi;
    }

    /**
     * 删除已有的本别名，并对指定索引添加本别名. <br>
     * @param indexName
     * @param newAlias
     * @return void
     * @throws
     * @author guzhandong
     * @date 2019-05-14 21:38
     **/
    public void removeAllAliasAndAddAlias(String indexName, final String newAlias) {

        //索引名字和别名的映射
        final Map<String,String> aliasMap = Maps.newHashMap();
        //遍历索引
        Iterator<Map.Entry<String, Indice>> indexIterator =getIndiceMap().entrySet().iterator();
        while (indexIterator.hasNext()) {
            Map.Entry<String,Indice> indice = indexIterator.next();
            //遍历别名
            List<String> aliases = indice.getValue().getAliases();
            for (int i =0;i<aliases.size();i++) {
                if (newAlias.equals(aliases.get(i))) {
                    aliasMap.put(indice.getKey(),aliases.get(i));
                }
            }
        }


        //删除别名，不包含刚添加的
        Iterator<Map.Entry<String,String>> iterator = aliasMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String,String> entry = iterator.next();
            Object a = esApi.removeAlias(entry.getKey(),entry.getValue());
        }

        //添加别名
        esApi.addAlias(indexName,newAlias);
    }

    public Map<String,Indice> getIndiceMap() {
        ClusterStstes res = esApi.getClusterStats();
        return res.getMetadata().getIndices();
    }

    private static final String DEFAULT_TEMPLATE_SUFFIX = "_template";
    private static final int DEFAULT_TEMPLATE_ORDER = 0;

    public TemplateDto buildTemplate(String index, Object mapping) {
        TemplateDto templateDto = new TemplateDto();
        templateDto.setMappings(mapping);
        templateDto.setOrder(DEFAULT_TEMPLATE_ORDER);
        templateDto.setSettings(Maps.newHashMapWithExpectedSize(0));
        templateDto.setTemplate(index+"*");
        return templateDto;
    }

    public void maping2Template(String outFilePath, String patternStr) {
        Pattern pattern = Pattern.compile(patternStr);
//        pattern.matcher("")
        //遍历索引
        Iterator<Map.Entry<String, Indice>> indexIterator =getIndiceMap().entrySet().iterator();

        Map<String,Object> templateMap = Maps.newHashMap();

        while (indexIterator.hasNext()) {
            Map.Entry<String, Indice> indice = indexIterator.next();
            Map<String,Object> mappings = indice.getValue().getMappings();

            //添加到模板集合
            templateMap.put(indice.getKey(),buildTemplate(indice.getKey(),mappings));
        }
        FileUtils.outputFile(outFilePath, JSONUtil.convertToJson(templateMap),false);
    }


    public void addTemplates(String inputFilePath) {
        String mappingStr = FileUtils.intputFile(inputFilePath);
//        TemplateMapDto templateMapDto = JSONUtil.parseJson(TemplateMapDto.class,mappingStr);
//        Map<String,Object> resMap = Maps.newHashMapWithExpectedSize(templateMapDto.size());
//        templateMapDto.entrySet().forEach(entry->{
//            Map<String,Object> res = esApi.addTemplateObj(entry.getKey(),JSONUtil.convertToJson(entry.getValue()));
//            resMap.put(entry.getKey(),res);
//
//        });
    }




}
