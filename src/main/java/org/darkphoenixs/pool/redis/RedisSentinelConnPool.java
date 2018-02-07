package org.darkphoenixs.pool.redis;

import org.darkphoenixs.pool.ConnectionPool;
import org.darkphoenixs.pool.PoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * <p>Title: RedisSentinelConnPool</p>
 * <p>Description: Redis哨兵连接池</p>
 *
 * @author Victor.Zxy
 * @version 1.2.3
 * @see ConnectionPool
 * @since 2016 /8/25
 */
public class RedisSentinelConnPool implements ConnectionPool<Jedis> {

    private final JedisSentinelPool pool;

    /**
     * Instantiates a new Redis sentinel conn pool(For test).
     *
     * @param pool the pool
     */
    protected RedisSentinelConnPool(final JedisSentinelPool pool) {
        this.pool = pool;
    }

    /**
     * Instantiates a new Redis sentinel conn pool.
     *
     * @param masterName the master name
     * @param sentinels  the sentinels
     */
    public RedisSentinelConnPool(final String masterName, final Set<String> sentinels) {

        this(new PoolConfig(), masterName, sentinels);
    }

    /**
     * Instantiates a new Redis sentinel conn pool.
     *
     * @param poolConfig the pool config
     * @param masterName the master name
     * @param sentinels  the sentinels
     */
    public RedisSentinelConnPool(final PoolConfig poolConfig,
                                 final String masterName,
                                 final Set<String> sentinels) {

        this(poolConfig, masterName, sentinels, RedisConfig.DEFAULT_PASSWORD);
    }

    /**
     * Instantiates a new Redis sentinel conn pool.
     *
     * @param poolConfig the pool config
     * @param masterName the master name
     * @param sentinels  the sentinels
     * @param password   the password
     */
    public RedisSentinelConnPool(final PoolConfig poolConfig,
                                 final String masterName,
                                 final Set<String> sentinels,
                                 final String password) {

        this(poolConfig, masterName, sentinels, password, RedisConfig.DEFAULT_TIMEOUT);
    }

    /**
     * Instantiates a new Redis sentinel conn pool.
     *
     * @param poolConfig the pool config
     * @param masterName the master name
     * @param sentinels  the sentinels
     * @param password   the password
     * @param timeout    the timeout
     */
    public RedisSentinelConnPool(final PoolConfig poolConfig,
                                 final String masterName,
                                 final Set<String> sentinels,
                                 final String password,
                                 final int timeout) {

        this(poolConfig, masterName, sentinels, timeout, timeout, password,
                RedisConfig.DEFAULT_DATABASE,
                RedisConfig.DEFAULT_CLIENTNAME);
    }

    /**
     * Instantiates a new Redis sentinel conn pool.
     *
     * @param properties the properties
     */
    public RedisSentinelConnPool(final Properties properties) {

        this(new PoolConfig(), properties);
    }

    /**
     * Instantiates a new Redis sentinel conn pool.
     *
     * @param poolConfig the pool config
     * @param properties the properties
     */
    public RedisSentinelConnPool(final PoolConfig poolConfig, final Properties properties) {

        this(poolConfig,
                properties.getProperty(RedisConfig.MASTERNAME_PROPERTY),
                new HashSet<String>(Arrays.asList(properties.getProperty(RedisConfig.SENTINELS_PROPERTY).split(","))),
                Integer.parseInt(properties.getProperty(RedisConfig.TIMEOUT_PROPERTY, String.valueOf(RedisConfig.DEFAULT_TIMEOUT))),
                Integer.parseInt(properties.getProperty(RedisConfig.TIMEOUT_PROPERTY, String.valueOf(RedisConfig.DEFAULT_TIMEOUT))),
                properties.getProperty(RedisConfig.PASSWORD_PROPERTY),
                Integer.parseInt(properties.getProperty(RedisConfig.DATABASE_PROPERTY, String.valueOf(RedisConfig.DEFAULT_DATABASE))),
                properties.getProperty(RedisConfig.CLIENTNAME_PROPERTY));
    }

    /**
     * Instantiates a new Redis sentinel conn pool.
     *
     * @param poolConfig        the pool config
     * @param masterName        the master name
     * @param sentinels         the sentinels
     * @param connectionTimeout the connection timeout
     * @param soTimeout         the so timeout
     * @param password          the password
     * @param database          the database
     * @param clientName        the client name
     */
    public RedisSentinelConnPool(final PoolConfig poolConfig,
                                 final String masterName,
                                 final Set<String> sentinels,
                                 final int connectionTimeout,
                                 final int soTimeout,
                                 final String password,
                                 final int database,
                                 final String clientName) {

        this.pool = new JedisSentinelPool(masterName, sentinels, poolConfig, connectionTimeout, soTimeout, password, database, clientName);
    }

    @Override
    public Jedis getConnection() {

        return pool.getResource();
    }

    @Override
    public void returnConnection(Jedis conn) {

        if (conn != null)

            conn.close();
    }

    @Override
    public void invalidateConnection(Jedis conn) {

        if (conn != null)

            conn.close();
    }

    /**
     * Close.
     */
    public void close() {

        pool.close();
    }
}
