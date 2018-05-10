package com.etl.server.register;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author houlongbin <a>https://github.com/dikelongzai</a>
 * @fileName com.etl.server.register
 * @email 15399073387@163.com
 * @description
 * @since 2018/05/08
 */
public class CacheMeta implements Serializable {

    private static final String HIVE_META="hiveMeta";
    //hiveMeta
    public static Map<String,? extends MetaRegisterContext> META_CACHE= new ConcurrentHashMap<String, MetaRegisterContext>();


}
