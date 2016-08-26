package org.darkphoenixs.pool.redis;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Properties;

public class RedisConnectionFactoryOldTest {

    @Before
    public void before() throws Exception {


        Thread th = new Thread(new Runnable() {

            private ServerSocket serverSocket;

            @Override
            public void run() {

                try {
                    serverSocket = new ServerSocket(RedisConfig.DEFAULT_PORT);

                    serverSocket.accept();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        th.setDaemon(true);

        th.start();
    }

    @Test
    public void test_0() throws Exception {

        Properties prop = new Properties();

        RedisConnectionFactoryOld factory;

        try {
            factory = new RedisConnectionFactoryOld(prop);

        } catch (Exception e) {
        }

        prop.setProperty(RedisConfig.ADDRESS_PROPERTY, RedisConfig.DEFAULT_HOST
                + ":" + RedisConfig.DEFAULT_PORT);
        prop.setProperty(RedisConfig.CONN_TIMEOUT_PROPERTY,
                RedisConfig.DEFAULT_TIMEOUT + "");
        prop.setProperty(RedisConfig.SO_TIMEOUT_PROPERTY,
                RedisConfig.DEFAULT_TIMEOUT + "");
        prop.setProperty(RedisConfig.DATABASE_PROPERTY,
                RedisConfig.DEFAULT_DATABASE + "");
        prop.setProperty(RedisConfig.CLIENTNAME_PROPERTY, "Test");
        prop.setProperty(RedisConfig.PASSWORD_PROPERTY, "Test");

        factory = new RedisConnectionFactoryOld(prop);

        factory = new RedisConnectionFactoryOld(RedisConfig.DEFAULT_HOST, 1233,
                RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_TIMEOUT,
                "Test", 1, "Test");

        factory.setHostAndPort(new HostAndPort(RedisConfig.DEFAULT_HOST,
                RedisConfig.DEFAULT_PORT));

        try {
            factory.makeObject();

        } catch (Exception e) {
        }

        try {
            factory.activateObject(new DefaultPooledObject<Jedis>(null));
        } catch (Exception e) {
        }

        try {
            factory.validateObject(new DefaultPooledObject<Jedis>(null));
        } catch (Exception e) {
        }

        try {
            factory.passivateObject(new DefaultPooledObject<Jedis>(null));
        } catch (Exception e) {
        }

        try {
            factory.destroyObject(new DefaultPooledObject<Jedis>(null));
        } catch (Exception e) {
        }
    }

    @Test
    public void test_1() throws Exception {

        RedisConnectionFactoryOld factory = new RedisConnectionFactoryOld(
                RedisConfig.DEFAULT_HOST, RedisConfig.DEFAULT_PORT,
                RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_TIMEOUT,
                RedisConfig.DEFAULT_PASSWORD, RedisConfig.DEFAULT_DATABASE,
                RedisConfig.DEFAULT_CLIENTNAME);

        factory.validateObject(new DefaultPooledObject<Jedis>(factory
                .makeObject().getObject()));

        factory.destroyObject(new DefaultPooledObject<Jedis>(factory
                .makeObject().getObject()));
    }

    @Test
    public void test_2() throws Exception {

        RedisConnectionFactoryOld factory = new RedisConnectionFactoryOld(
                RedisConfig.DEFAULT_HOST, RedisConfig.DEFAULT_PORT,
                RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_TIMEOUT,
                RedisConfig.DEFAULT_PASSWORD, RedisConfig.DEFAULT_DATABASE,
                RedisConfig.DEFAULT_CLIENTNAME);

        try {
            factory.activateObject(new DefaultPooledObject<Jedis>(new Jedis()));
        } catch (Exception e) {
        }
        try {
            factory.validateObject(new DefaultPooledObject<Jedis>(new Jedis()));
        } catch (Exception e) {
        }
        try {
            factory.passivateObject(new DefaultPooledObject<Jedis>(new Jedis()));

        } catch (Exception e) {
        }
        try {
            factory.destroyObject(new DefaultPooledObject<Jedis>(new Jedis()));
        } catch (Exception e) {
        }

        RedisConnectionFactoryOld factory2 = new RedisConnectionFactoryOld(
                RedisConfig.DEFAULT_HOST, RedisConfig.DEFAULT_PORT,
                RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_TIMEOUT,
                RedisConfig.DEFAULT_PASSWORD, 2, RedisConfig.DEFAULT_CLIENTNAME);

        try {
            factory2.activateObject(new DefaultPooledObject<Jedis>(
                    new JedisConn(RedisConfig.DEFAULT_HOST,
                            RedisConfig.DEFAULT_PORT)));
        } catch (Exception e) {
        }

        try {
            factory2.destroyObject(new DefaultPooledObject<Jedis>(
                    new JedisConn2(RedisConfig.DEFAULT_HOST,
                            RedisConfig.DEFAULT_PORT)));
        } catch (Exception e) {
        }

        try {
            factory2.destroyObject(new DefaultPooledObject<Jedis>(
                    new JedisConn(RedisConfig.DEFAULT_HOST,
                            RedisConfig.DEFAULT_PORT)));
        } catch (Exception e) {
        }

    }

    @Test
    public void test_3() throws Exception {

        RedisConnectionFactoryOld factory3 = new RedisConnectionFactoryOld(
                "localhost", 1234, RedisConfig.DEFAULT_TIMEOUT,
                RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD,
                RedisConfig.DEFAULT_DATABASE, RedisConfig.DEFAULT_CLIENTNAME);

        try {
            boolean rev = factory3
                    .validateObject(new DefaultPooledObject<Jedis>(
                            new JedisConn4("localhost", 1234)));

            Assert.assertFalse(rev);

        } catch (Exception e) {
        }

        try {
            boolean rev = factory3
                    .validateObject(new DefaultPooledObject<Jedis>(
                            new JedisConn(RedisConfig.DEFAULT_HOST, 1234)));
            Assert.assertTrue(rev);

        } catch (Exception e) {
        }

        try {
            boolean rev = factory3
                    .validateObject(new DefaultPooledObject<Jedis>(
                            new JedisConn2(RedisConfig.DEFAULT_HOST, 1233)));
            Assert.assertFalse(rev);

        } catch (Exception e) {
        }

        try {
            boolean rev = factory3
                    .validateObject(new DefaultPooledObject<Jedis>(
                            new JedisConn3("localhost", 1234)));

            Assert.assertFalse(rev);

        } catch (Exception e) {
        }


    }

    @Test
    public void test_4() throws Exception {

        RedisConnectionFactoryOld factory = new RedisConnectionFactoryOld(
                RedisConfig.DEFAULT_HOST, RedisConfig.DEFAULT_PORT,
                RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_TIMEOUT,
                "test", RedisConfig.DEFAULT_DATABASE,
                RedisConfig.DEFAULT_CLIENTNAME);
        try {
            factory.makeObject();
        } catch (Exception e) {
        }

        factory = new RedisConnectionFactoryOld(RedisConfig.DEFAULT_HOST,
                RedisConfig.DEFAULT_PORT, RedisConfig.DEFAULT_TIMEOUT,
                RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD, 3,
                RedisConfig.DEFAULT_CLIENTNAME);
        try {
            factory.makeObject();
        } catch (Exception e) {
        }

        factory = new RedisConnectionFactoryOld(RedisConfig.DEFAULT_HOST,
                RedisConfig.DEFAULT_PORT, RedisConfig.DEFAULT_TIMEOUT,
                RedisConfig.DEFAULT_TIMEOUT, RedisConfig.DEFAULT_PASSWORD,
                RedisConfig.DEFAULT_DATABASE, "test");
        try {
            factory.makeObject();
        } catch (Exception e) {
        }
    }
}
