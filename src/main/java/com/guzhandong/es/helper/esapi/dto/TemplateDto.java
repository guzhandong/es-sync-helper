package com.guzhandong.es.helper.esapi.dto;

import lombok.Data;

@Data
public class TemplateDto {

    private int order;

    private Object mappings;

    private String template;

    private Object settings;
}
