package com.guzhandong.es.helper.esapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TemplateDto {

    private int order;

    private Object mappings;

    private String template;

    private Object settings;

    private Object index_patterns;

//    private Object aliases;
}
