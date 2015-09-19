Connection Pool Client
==============================
  基于 [Apache Commons Pool ™](http://commons.apache.org/proper/commons-pool/) 框架

  连接池客户端 ( [Apache Kafka](http://kafka.apache.org/) &amp; [Apache Hbase](http://hbase.apache.org/) &amp; [Redis](http://redis.io/) )
  
  * [Documentation](#documentation)
  * [KafkaConnectionPool](#kafkaconnectionpool)
  * [HbaseConnectionPool](#hbaseconnectionpool)
  * [RedisConnectionPool](#redisconnectionpool)


## Documentation

API documentation is available at [this]()

## KafkaConnectionPool

Use the [KafkaConnectionPool](https://github.com/darkphoenixs/connection-pool-client/blob/master/src/main/java/org/darkphoenixs/pool/kafka/KafkaConnectionPool.java) need instantiate `PoolConfig` and `Properties`

For example, the following 
```java
		/* poolConfig */
		PoolConfig config = new PoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);

		/* properties */
		Properties props = new Properties();
		props.setProperty("metadata.broker.list", "localhost:9092");
		props.setProperty("producer.type", "async");
		props.setProperty("request.required.acks", "0");
		props.setProperty("compression.codec", "snappy");
		props.setProperty("batch.num.messages", "200");

		/* connection pool */
		KafkaConnectionPool pool = new KafkaConnectionPool(config, props);

		/* pool getConnection */
		Producer<byte[], byte[]> producer = pool.getConnection();

		...

		/* producer send */
		producer.send(...);

		/* pool returnConnection */
		pool.returnConnection(producer);
```
## HbaseConnectionPool
Use the [HbaseConnectionPool](https://github.com/darkphoenixs/connection-pool-client/blob/master/src/main/java/org/darkphoenixs/pool/hbase/HbaseConnectionPool.java) need instantiate `PoolConfig` and `Configuration`

For example, the following 
```java
		/* poolConfig */
		PoolConfig config = new PoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);
		
		/* configuration */
		Configuration hbaseConfig = new Configuration();
		hbaseConfig.set("hbase.zookeeper.quorum", "localhost");
		hbaseConfig.set("hbase.zookeeper.property.clientPort", "2181");
		hbaseConfig.set("hbase.master", "localhost:60000");
		hbaseConfig.set("hbase.rootdir", "hdfs://localhost:9000/hbase");
		
		/* connection pool */
		HbaseConnectionPool pool = new HbaseConnectionPool(config, hbaseConfig);

		/* pool getConnection */
		Connection conn = pool.getConnection();

		/* conn getTable */
		Table table = conn.getTable(TableName.valueOf("TableTest"));

		...

		/* table put */
		table.put(...);

		/* table close */
		table.close();
		
		/* pool returnConnection */
		pool.returnConnection(conn);
```
## RedisConnectionPool
