package com.guzhandong.es.helper.esapi.Response;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Metadata {

    private Map<String,Indice> indices = new HashMap<String,Indice>();
}
