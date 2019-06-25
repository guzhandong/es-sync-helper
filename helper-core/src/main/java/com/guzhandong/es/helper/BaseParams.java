package com.guzhandong.es.helper;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * desc. <br>
 *
 * @author guzhandong
 * @version [v1.0]
 * @createDate 2019-05-28 18:57
 * @since [jdk 1.8]
 **/
@Data
@NoArgsConstructor
public class BaseParams {

   public Pattern pa = Pattern.compile("([\\w|.|-]*)((?!0000)[0-9]{4}((0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])(29|30)|(0[13578]|1[02])31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)02-29)$");

    /**
     * 输入
     */
    protected String input;

    /**
     * 输出
     */
    protected String output;

    /**
     * 操作项
     */
    protected String option;

    public static void main(String[] args) {
        String string = "app.company_info_20190525";
        Pattern pa = new BaseParams().pa;
        Matcher matcher = pa.matcher(string);
        Matcher matcher1 = pa.matcher(string);
        System.out.println(matcher.matches());
        System.out.println(matcher.group());
        System.out.println(string.substring(string.length()-8));

        System.out.println(matcher1.matches());
        System.out.println(matcher1.group());
        System.out.println(string.substring(string.length()-8));


    }

}
