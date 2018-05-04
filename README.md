# ETLExpress
1.实现数据动态注册
  1.1	客户端注册hdfs地址/hive表
  1.2	动态映射为对象
2.第二步实现表达式注册解析数据抽取
  2.1	表达式注册解析
  2.2	spark数据抽取表达式过滤
  2.3	获取集群负载,获取spark driver
  2.4	ssh到driver上执行spark-submit提交任务 任务结束后保存数据返回路径异步通知客户端数据抽取完成
3.后续会考虑逐步实现从消息队列通过sparkstreaming/storm实时读取数据到hdfs/hbase
  
