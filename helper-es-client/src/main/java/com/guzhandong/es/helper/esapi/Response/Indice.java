package com.guzhandong.es.helper.esapi.Response;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Indice{

    private List<String> aliases = new ArrayList<String>(1);

    private Map<String,Object> mappings = new HashMap<String, Object>(1);

}
