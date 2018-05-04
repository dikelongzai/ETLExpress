# ETLExpress
随着大数据技术的发展,近几年接触了不同业务需求的ETL,积累了一些心得，数据的ETL大概分为以下几种类型
1.mssql/oracle/mysql->hdfs/hive/hbase 业务数据库到大数据集群的ETL
2.MQ/kafka->hdfs/hbase 消息队列到大数据集群的ETL
3.hdfs/hive->hbase  hbase->hdfs/hive 大数据集群内部的ETL设计到平时的数据抽取,分析统计
4.hfs/hive/hbase->mssql/oracle/mysql 数据成果输出报表
5.业务直接把文件放到hdfs,注册规则,我们根据规则清洗数据落地入库,报表输出
设计到的技术栈主要有 存储类型 hdfs,hive,hbase 计算引擎 storm/spark/mr/sqoop
每次业务对数据团队有需求我们都得写MR/spark过滤数据,打包,作业部署,提取结果给业务,整个过程有几个问题
1.成本较高主要包括沟通成本,研发,部署的时间成本，整个过程耗时较长
2.大数据团队有搞不完的ETL,大多数都是重复工作
3.业务满意度也不高觉得响应慢
有没有什么办法解决以上问题,有没有办法通过技术手段使整个过程缩短ETL过程耗时,使得整个过程灵活可配置
大概整理了一下思路
1.实现数据动态注册
  1.1	客户端注册hdfs地址/hive表
  1.2	动态映射为对象
2.第二步实现表达式注册解析数据抽取
  2.1	表达式注册解析
  2.2	spark数据抽取表达式过滤
  2.3	获取集群负载,获取spark driver
  2.4	ssh到driver上执行spark-submit/mr/storm/sqoop提交任务 任务结束后保存数据返回路径异步通知客户端数据抽取完成
3.后续的一些想法
  3.1 通过kafka/MQ 消息队列注册,启动spark-streaming job/storm实时写入到hdfs/hbase
  3.2 灵活配置 hdfs/hbase/hive->spark/sqoop->hdfs/db
  3.3 预估集群资源及数据抽取大小调整作业并行度及输出策略
  大概整理了一下思路，参考了一些资料,要设计到的技术栈git引用如下
  1. QLExpress git@github.com:alibaba/QLExpress.git 
      由阿里的电商业务规则、表达式（布尔组合）、特殊数学公式计算（高精度）、语法分析、脚本二次定制等强需求而设计的一门动态脚本引擎解析工       具。 在阿里集团有很强的影响力，同时为了自身不断优化、发扬开源贡献精神，于2012年开源
  2. sshxcute git@github.com:liuning123/sshxcute.git
      java执行封装ssh插件,通过获取集群负载自动化部署shell脚本提交MR/SPARK/SQOOP作业
  3. git@github.com:tang-jie/NettyRPC.git
      通信方式RPC+HTTP完成元数据注册和表达式注册
   
