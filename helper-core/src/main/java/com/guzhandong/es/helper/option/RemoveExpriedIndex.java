package com.guzhandong.es.helper.option;

import com.google.common.collect.Maps;
import com.knowlegene.commons.utils.DateUtils;
import com.knowlegene.commons.utils.JSONUtil;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * desc. <br>
 *
 * @author guzhandong
 * @version [v1.0]
 * @createDate 2019-05-28 21:51
 * @since [jdk 1.8]
 **/
@Component
public class RemoveExpriedIndex extends AbstractOption{

    private Pattern pattern = Pattern.compile("([\\w|.|-]*)((?!0000)[0-9]{4}((0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])(29|30)|(0[13578]|1[02])31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)02-29)$");

    @Override
    public Object exec(String[] args) {
        if (args.length>1){
//            maping2Template(args[0],args[1]);
        } else {

//            maping2Template(args[0],null);
        }
        removeExpriedIndex(args[0],null);

        return true;
    }

    public void removeExpriedIndex(String day, String patternStr) {
//        Pattern pattern = Pattern.compile(patternStr);
        //遍历索引
        int dayVal = Integer.valueOf(day);
        //待添加保险措施，如果他有别名，且有该别名的只有这一个，则不进行删除
        //current 当前有别名则不进行删除

        Calendar calendar = setCalendarZero(Calendar.getInstance());
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-dayVal);

        Map<String,Object> result = Maps.newHashMap();
        removeHasAliasIndex(getIndiceMap().keySet()).forEach(t->{
            Matcher matcher =  pattern.matcher(t);
            if (matcher.find()) {
                String indexDateString = t.substring(t.length()-8);
                Calendar indexDate = setCalendarZero(DateUtils.parseDateCalendar(indexDateString,10));
                if (calendar.compareTo(indexDate)>0) {
                    result.put(t,esApi.removeIndex(t));
                }
            }
        });
//        logger.info("delete index : {}", JSONUtil.convertToJson(result));
    }


    /**
     * 删除包含别名的索引名称. <br>
     * @param indexCollection :
     * @return java.util.Set<java.lang.String>
     * @throws
     * @author guzhandong
     * @date 2019-06-25 18:21
     **/
    private Set<String> removeHasAliasIndex(Collection<String> indexCollection) {
        Map<String, List<String>> hasAliasIndexMap = esApi.getAlias().getHasAlias();
        return indexCollection.stream().filter(li->!hasAliasIndexMap.containsKey(li)).collect(Collectors.toSet());
    }

    public static void main(String[] args) {
        new RemoveExpriedIndex().removeExpriedIndex("5","");

    }

    private Calendar setCalendarZero(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar;
    }

}
