package com.guzhandong.es.helper.esapi.Response;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.omg.CORBA.Any;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class Alias extends HashMap<String, Alias.AliasVal> {



    public Map<String, List<String>> getAllAlias() {
        Map<String,List<String>> aliasMap = Maps.newHashMapWithExpectedSize(size());
        entrySet().forEach(entry->aliasMap.put(entry.getKey(), Lists.newArrayList(entry.getValue().getAliases().keySet())));
        return aliasMap;
    }

    public Map<String, List<String>> getHasAlias() {
        Map<String, List<String>> aliasMap = getAllAlias();
        Iterator<Entry<String,List<String>>> entryIterator = aliasMap.entrySet().iterator();
        while (entryIterator.hasNext()) {
            List<String> aliasList = entryIterator.next().getValue();
            if (aliasList==null || aliasList.size()==0) {
                entryIterator.remove();
            }
        }
        return aliasMap;
    }


    @Data
    @NoArgsConstructor
    class AliasVal {
        private HashMap<String,Object> aliases;
    }
}
