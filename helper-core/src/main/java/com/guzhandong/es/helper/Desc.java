package com.guzhandong.es.helper;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;
import java.util.regex.Pattern;

/**
 * desc. <br>
 *
 * @author guzhandong
 * @version [v1.0]
 * @createDate 2019-05-28 21:43
 * @since [jdk 1.8]
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Desc {

    @AliasFor(annotation = Component.class)
    String value () default "";

    String desc () default "";

    String example () default "";
}
