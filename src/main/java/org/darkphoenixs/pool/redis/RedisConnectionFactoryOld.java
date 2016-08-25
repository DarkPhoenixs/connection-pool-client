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

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.darkphoenixs.pool.ConnectionException;
import org.darkphoenixs.pool.ConnectionFactory;
import redis.clients.jedis.BinaryJedis;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>Title: RedisConnectionFactoryOld</p>
 * <p>Description: Redis连接工厂</p>
 *
 * @author Victor
 * @version 1.0
 * @see ConnectionFactory
 * @since 2015年9月19日
 */
@Deprecated
class RedisConnectionFactoryOld implements ConnectionFactory<Jedis> {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5692815845396189037L;

    /**
     * hostAndPort
     */
    private final AtomicReference<HostAndPort> hostAndPort = new AtomicReference<HostAndPort>();
    /**
     * connectionTimeout
     */
    private final int connectionTimeout;
    /**
     * soTimeout
     */
    private final int soTimeout;
    /**
     * password
     */
    private final String password;
    /**
     * database
     */
    private final int database;
    /**
     * clientName
     */
    private final String clientName;

    /**
     * <p>Title: RedisConnectionFactoryOld</p>
     * <p>Description: 构造方法</p>
     *
     * @param host              地址
     * @param port              端口
     * @param connectionTimeout 连接超时
     * @param soTimeout         超时时间
     * @param password          密码
     * @param database          数据库
     * @param clientName        客户端名称
     */
    public RedisConnectionFactoryOld(final String host, final int port, final int connectionTimeout,
                                     final int soTimeout, final String password, final int database, final String clientName) {
        this.hostAndPort.set(new HostAndPort(host, port));
        this.connectionTimeout = connectionTimeout;
        this.soTimeout = soTimeout;
        this.password = password;
        this.database = database;
        this.clientName = clientName;
    }

    /**
     * @param properties 参数配置
     * @since 1.2.1
     */
    public RedisConnectionFactoryOld(final Properties properties) {

        String address = properties.getProperty(RedisConfig.ADDRESS_PROPERTY);
        if (address == null)
            throw new ConnectionException("[" + RedisConfig.ADDRESS_PROPERTY + "] is required !");

        this.hostAndPort.set(new HostAndPort(address.split(":")[0], Integer.parseInt(address.split(":")[1])));

        this.connectionTimeout = Integer.parseInt(properties.getProperty(RedisConfig.CONN_TIMEOUT_PROPERTY, String.valueOf(RedisConfig.DEFAULT_TIMEOUT)));
        this.soTimeout = Integer.parseInt(properties.getProperty(RedisConfig.SO_TIMEOUT_PROPERTY, String.valueOf(RedisConfig.DEFAULT_TIMEOUT)));
        this.database = Integer.parseInt(properties.getProperty(RedisConfig.DATABASE_PROPERTY, String.valueOf(RedisConfig.DEFAULT_DATABASE)));
        this.password = properties.getProperty(RedisConfig.PASSWORD_PROPERTY);
        this.clientName = properties.getProperty(RedisConfig.CLIENTNAME_PROPERTY);
    }

    /**
     * <p>Title: setHostAndPort</p>
     * <p>Description: 设置地址和端口</p>
     *
     * @param hostAndPort 地址和端口
     */
    public void setHostAndPort(final HostAndPort hostAndPort) {
        this.hostAndPort.set(hostAndPort);
    }

    @Override
    public PooledObject<Jedis> makeObject() throws Exception {

        Jedis jedis = this.createConnection();

        return new DefaultPooledObject<Jedis>(jedis);
    }

    @Override
    public void destroyObject(PooledObject<Jedis> p) throws Exception {

        BinaryJedis jedis = (BinaryJedis) p.getObject();
        if (!(jedis.isConnected()))
            return;
        try {
            try {
                jedis.quit();
            } catch (Exception e) {
            }
            jedis.disconnect();
        } catch (Exception e) {
        }
    }

    @Override
    public boolean validateObject(PooledObject<Jedis> p) {

        BinaryJedis jedis = (BinaryJedis) p.getObject();
        try {
            HostAndPort hostAndPort = (HostAndPort) this.hostAndPort.get();

            String connectionHost = jedis.getClient().getHost();
            int connectionPort = jedis.getClient().getPort();

            return ((hostAndPort.getHost().equals(connectionHost))
                    && (hostAndPort.getPort() == connectionPort)
                    && (jedis.isConnected()) && (jedis.ping().equals("PONG")));
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public void activateObject(PooledObject<Jedis> p) throws Exception {

        BinaryJedis jedis = (BinaryJedis) p.getObject();
        if (jedis.getDB().longValue() != this.database)
            jedis.select(this.database);
    }

    @Override
    public void passivateObject(PooledObject<Jedis> p) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Jedis createConnection() throws Exception {

        HostAndPort hostAndPort = (HostAndPort) this.hostAndPort.get();
        Jedis jedis = new Jedis(hostAndPort.getHost(), hostAndPort.getPort(),
                this.connectionTimeout, this.soTimeout);

        try {
            jedis.connect();
            if (null != this.password) {
                jedis.auth(this.password);
            }
            if (this.database != 0) {
                jedis.select(this.database);
            }
            if (this.clientName != null) {
                jedis.clientSetname(this.clientName);
            }
        } catch (Exception je) {
            jedis.close();
            throw je;
        }

        return jedis;
    }

}
