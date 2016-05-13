#Connection Pool Client

[![Organization](https://img.shields.io/badge/org-%20DarkPhoenixs-yellow.svg)](http://www.darkphoenixs.org)
[![Build Status](https://travis-ci.org/DarkPhoenixs/connection-pool-client.svg?branch=master)](https://travis-ci.org/DarkPhoenixs/connection-pool-client)
[![Codecov](https://codecov.io/gh/darkphoenixs/connection-pool-client/branch/master/graph/badge.svg)](https://codecov.io/gh/DarkPhoenixs/connection-pool-client)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.darkphoenixs/connectionpool-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.darkphoenixs/connectionpool-client/)
[![GitHub release](https://img.shields.io/github/release/DarkPhoenixs/connection-pool-client.svg)](https://github.com/DarkPhoenixs/connection-pool-client/releases)
[![License](https://img.shields.io/badge/license-%20Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)


  This is a simple multi-purpose connection pool client base on [Apache Commons Pool ™](http://commons.apache.org/proper/commons-pool/) 

  _With_
  * [Apache Kafka](http://kafka.apache.org/)
  * [Apache Hbase](http://hbase.apache.org/)
  * [Redis](http://redis.io/)
  * RMDB (MySQL/SQL Server/Oracle)
  * Socket (TCP)


## Documentation

API documentation is available at [this](http://htmlpreview.github.io/?https://raw.githubusercontent.com/darkphoenixs/connection-pool-client/v1.0/doc/index.html)

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

		/* table close */
		table.close();
		
		/* pool returnConnection */
		pool.returnConnection(conn);
```
## RedisConnectionPool
Use the [RedisConnectionPool](https://github.com/darkphoenixs/connection-pool-client/blob/master/src/main/java/org/darkphoenixs/pool/redis/RedisConnectionPool.java) need instantiate `PoolConfig` 

For example, the following 
```java
		/* poolConfig */
		PoolConfig config = new PoolConfig();
		config.setMaxTotal(20);
		config.setMaxIdle(5);
		config.setMaxWaitMillis(1000);
		config.setTestOnBorrow(true);
		
		/* connection pool */
		RedisConnectionPool pool = new RedisConnectionPool(config, "localhost", 6379);
		
		/* pool getConnection */
		Jedis jedis = pool.getConnection();
			
		...
		
		/* pool getConnection */
		pool.returnConnection(jedis);
```
