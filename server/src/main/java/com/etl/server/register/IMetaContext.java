package com.etl.server.register;

/**
 * @author houlongbin <a>https://github.com/dikelongzai</a>
 * @fileName com.etl.server.register
 * @email 15399073387@163.com
 * @description 配置表name为 hive表名或者其他参数
 * @since 2018/05/08
 */
public interface IMetaContext<K,V> {
    /**
     * 根据名称从属性列表中提取属性值
     * @param key 属性名称
     * @return
     */
    public V get(Object key);
    /**
     * 表达式计算的结果可以设置回调用系统
     * @param name 属性名称
     * @param object 属性值
     */
    public V put(K name, V object);
}
