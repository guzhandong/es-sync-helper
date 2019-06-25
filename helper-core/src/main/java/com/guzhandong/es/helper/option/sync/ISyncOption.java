package com.guzhandong.es.helper.option.sync;

import com.guzhandong.es.helper.BaseParams;

/**
 * @author guzhandong
 * @CREATE 2019-05-15 11:08
 */
public interface ISyncOption<A extends BaseParams> {

    public Object exec(A params);
}
