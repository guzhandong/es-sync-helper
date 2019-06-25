package com.guzhandong.es.helper.option;


import com.google.common.collect.Maps;
import com.guzhandong.es.helper.Desc;
import com.knowlegene.commons.utils.DateUtils;

import java.util.Calendar;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Desc(value="removeTemplate",example="")
public class RemoveIndex extends AbstractOption{

    private Pattern pattern = Pattern.compile("([\\w|.|-]*)((?!0000)[0-9]{4}((0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])(29|30)|(0[13578]|1[02])31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)02-29)$");

    @Override
    public Object exec(String[] args) {
        if (args.length>1){
//            maping2Template(args[0],args[1]);
        } else {

//            maping2Template(args[0],null);
        }
        deleteTemplate(args[0],null);

        return true;
    }

    public void deleteTemplate(String day, String patternStr) {
//        Pattern pattern = Pattern.compile(patternStr);
        //遍历索引
        int dayVal = Integer.valueOf(day);
        Calendar calendar = setCalendarZero(Calendar.getInstance());
        calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)-dayVal);

        Map<String,Object> result = Maps.newHashMap();
        getIndiceMap().keySet().forEach(t->{
            Matcher matcher =  pattern.matcher(t);
            if (matcher.find()) {
                String indexDateString = t.substring(t.length()-8);
                Calendar indexDate = setCalendarZero(DateUtils.parseDateCalendar(indexDateString,10));
                if (calendar.compareTo(indexDate)>0) {
                    result.put(t,esApi.removeIndex(t));
                }
            }
        });
    }

    public static void main(String[] args) {
        new RemoveIndex().deleteTemplate("5","");

    }

    private Calendar setCalendarZero(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar;
    }

}
