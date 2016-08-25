package org.darkphoenixs.pool.redis;

import org.darkphoenixs.pool.ConnectionPool;
import org.darkphoenixs.pool.PoolConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Properties;

/**
 * <p>Title: RedisConnectionPool</p>
 * <p>Description: Redis连接池</p>
 *
 * @author Victor.Zxy
 * @version 1.2.3
 * @see ConnectionPool
 * @since 2016 /8/25
 */
public class RedisConnectionPool implements ConnectionPool<Jedis> {

    private final JedisPool pool;

    /**
     * Instantiates a new Redis connection pool.
     *
     * @param host the host
     * @param port the port
     */
    public RedisConnectionPool(final String host, final int port) {

        this(new PoolConfig(), host, port);
    }

    /**
     * Instantiates a new Redis connection pool.
     *
     * @param poolConfig the pool config
     * @param host       the host
     * @param port       the port
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port) {

        this(poolConfig, host, port,
                RedisConfig.DEFAULT_TIMEOUT,
                RedisConfig.DEFAULT_TIMEOUT,
                RedisConfig.DEFAULT_PASSWORD,
                RedisConfig.DEFAULT_DATABASE,
                RedisConfig.DEFAULT_CLIENTNAME);
    }

    /**
     * Instantiates a new Redis connection pool.
     *
     * @param properties the properties
     */
    public RedisConnectionPool(final Properties properties) {

        this(new PoolConfig(), properties);
    }

    /**
     * Instantiates a new Redis connection pool.
     *
     * @param poolConfig the pool config
     * @param properties the properties
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final Properties properties) {

        this(poolConfig,
                properties.getProperty(RedisConfig.ADDRESS_PROPERTY).split(":")[0],
                Integer.parseInt(properties.getProperty(RedisConfig.ADDRESS_PROPERTY).split(":")[1]),
                Integer.parseInt(properties.getProperty(RedisConfig.CONN_TIMEOUT_PROPERTY, String.valueOf(RedisConfig.DEFAULT_TIMEOUT))),
                Integer.parseInt(properties.getProperty(RedisConfig.SO_TIMEOUT_PROPERTY, String.valueOf(RedisConfig.DEFAULT_TIMEOUT))),
                properties.getProperty(RedisConfig.PASSWORD_PROPERTY),
                Integer.parseInt(properties.getProperty(RedisConfig.DATABASE_PROPERTY, String.valueOf(RedisConfig.DEFAULT_DATABASE))),
                properties.getProperty(RedisConfig.CLIENTNAME_PROPERTY));

    }

    /**
     * Instantiates a new Redis connection pool.
     *
     * @param poolConfig        the pool config
     * @param host              the host
     * @param port              the port
     * @param connectionTimeout the connection timeout
     * @param soTimeout         the so timeout
     * @param password          the password
     * @param database          the database
     * @param clientName        the client name
     */
    public RedisConnectionPool(final PoolConfig poolConfig,
                               final String host,
                               final int port,
                               final int connectionTimeout,
                               final int soTimeout,
                               final String password,
                               final int database,
                               final String clientName) {

        this.pool = new JedisPool(poolConfig, host, port, connectionTimeout, soTimeout, password, database, clientName);
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
