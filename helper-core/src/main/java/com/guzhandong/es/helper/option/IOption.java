package com.guzhandong.es.helper.option;

/**
 * 可选项操作接口. <br>
 *
 * @author guzhandong
 * @version [v1.0]
 * @createDate 2019-05-15 11:08
 * @since [jdk 1.8]
 **/
public interface IOption {


    /**
     * 执行方法，所有选项操作实现类都需要实现该方法，获取需要的参数. <br>
     * @param args :
     * @return java.lang.Object
     * @throws
     * @author guzhandong
     * @date 2019-05-29 16:02
     **/
    public Object exec(String[] args);

}
