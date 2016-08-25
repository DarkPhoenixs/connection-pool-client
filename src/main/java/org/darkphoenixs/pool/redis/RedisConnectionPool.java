/*
 * Copyright 2015-2016 Dark Phoenixs (Open-Source Organization).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.darkphoenixs.pool.redis;

import org.darkphoenixs.pool.ConnectionPool;
import org.darkphoenixs.pool.PoolBase;
import org.darkphoenixs.pool.PoolConfig;
import redis.clients.jedis.Client;
import redis.clients.jedis.Jedis;

import java.util.Properties;

/**
 * <p>Title: RedisConnectionPool</p>
 * <p>Description: Redis连接池</p>
 *
 * @author Victor
 * @version 1.0
 * @see ConnectionPool
 * @see PoolBase
 * @since 2015年9月19日
 */
public class RedisConnectionPool extends PoolBase<Jedis> implements ConnectionPool<Jedis> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7068775808649440797L;

    /**
     * <p>Title: RedisConnectionPool</p>
     * <p>Description: 默认构造方法</p>
     */
    public RedisConnectionPool() {
        this(RedisConfig.DEFAULT_HOST, RedisConfig.DEFAULT_PORT);
    }

    /**
     * <p>Title: RedisConnectionPool</p>
     * <p>Description: 构造方法</p>
     *
     * @param poolConfig 池配置
     * @param host       地址
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final String host) {
        this(poolConfig, host, RedisConfig.DEFAULT_PORT, RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD);
    }

    /**
     * <p>Title: RedisConnectionPool</p>
     * <p>Description: 构造方法</p>
     *
     * @param host 地址
     * @param port 端口
     */
    public RedisConnectionPool(final String host, final int port) {
        this(new PoolConfig(), host, port);
    }

    /**
     * <p>Title: RedisConnectionPool</p>
     * <p>Description: 构造方法</p>
     *
     * @param poolConfig 池配置
     * @param host       地址
     * @param port       端口
     * @param timeout    超时
     * @param password   密码
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
                               final int timeout, final String password) {
        this(poolConfig, host, port, timeout, password, RedisConfig.DEFAULT_DATABASE, RedisConfig.DEFAULT_CLIENTNAME);
    }

    /**
     * <p>Title: RedisConnectionPool</p>
     * <p>Description: 构造方法</p>
     *
     * @param poolConfig 池配置
     * @param host       地址
     * @param port       端口
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port) {
        this(poolConfig, host, port, RedisConfig.DEFAULT_TIMEOUT);
    }

    /**
     * <p>Title: RedisConnectionPool</p>
     * <p>Description: 构造方法</p>
     *
     * @param poolConfig 池配置
     * @param host       地址
     * @param port       端口
     * @param timeout    超时
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
                               final int timeout) {
        this(poolConfig, host, port, timeout, RedisConfig.DEFAULT_PASSWORD, RedisConfig.DEFAULT_DATABASE);
    }

    /**
     * <p>Title: RedisConnectionPool</p>
     * <p>Description: 构造方法</p>
     *
     * @param poolConfig 池配置
     * @param host       地址
     * @param port       端口
     * @param timeout    超时
     * @param password   密码
     * @param database   数据库
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
                               final int timeout, final String password, final int database) {
        this(poolConfig, host, port, timeout, password, database, RedisConfig.DEFAULT_CLIENTNAME);
    }

    /**
     * <p>Title: RedisConnectionPool</p>
     * <p>Description: 构造方法</p>
     *
     * @param poolConfig 池配置
     * @param host       地址
     * @param port       端口
     * @param timeout    超时
     * @param password   密码
     * @param database   数据库
     * @param clientName 客户端名称
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
                               final int timeout, final String password, final int database, final String clientName) {
        this(poolConfig, host, port, timeout, timeout, password, database,
                clientName);
    }

    /**
     * <p>Title: RedisConnectionPool</p>
     * <p>Description: 构造方法</p>
     *
     * @param poolConfig        池配置
     * @param host              地址
     * @param port              端口
     * @param connectionTimeout 连接超时
     * @param soTimeout         超时时间
     * @param password          密码
     * @param database          数据库
     * @param clientName        客户端名称
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final String host, final int port,
                               final int connectionTimeout, final int soTimeout, final String password,
                               final int database, final String clientName) {
        super(poolConfig, new RedisConnectionFactory(host, port, connectionTimeout,
                soTimeout, password, database, clientName));
    }

    /**
     * @param poolConfig 池配置
     * @param properties 参数配置
     * @since 1.2.1
     */
    public RedisConnectionPool(final PoolConfig poolConfig, final Properties properties) {

        super(poolConfig, new RedisConnectionFactory(properties));
    }

    @Override
    public Jedis getConnection() {

        return super.getResource();
    }

    @Override
    public void returnConnection(Jedis conn) {

        Client client = conn.getClient();

        if (client.isBroken())

            super.invalidateResource(conn);
        else
            super.returnResource(conn);
    }

    @Override
    public void invalidateConnection(Jedis conn) {

        super.invalidateResource(conn);
    }
}
