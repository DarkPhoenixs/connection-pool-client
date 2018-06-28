package org.darkphoenixs.pool.http;

import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.Properties;

public class HttpConnectionFactoryTest {

    @Test
    public void test1() throws Exception {

        HttpConnectionFactory factory = new HttpConnectionFactory(Proxy.NO_PROXY, HttpConfig.DEFAULT_URL, HttpConfig.DEFAULT_METHOD, 5000, 5000, HttpConfig.DEFAULT_HEADER);

        factory.makeObject();
        factory.activateObject(null);
        factory.passivateObject(null);

        try {
            factory.validateObject(null);
        } catch (Exception e) {
        }

        try {
            factory.validateObject(new DefaultPooledObject<HttpURLConnection>(null));
        } catch (Exception e) {
        }

        try {
            factory.validateObject(new DefaultPooledObject<HttpURLConnection>(new HttpURLConnection(null) {
                @Override
                public void connect() throws IOException {

                }

                @Override
                public void disconnect() {

                }

                @Override
                public boolean usingProxy() {
                    return false;
                }

                @Override
                public int getResponseCode() throws IOException {
                    return 200;
                }
            }));
        } catch (Exception e) {
        }

        try {
            factory.validateObject(new DefaultPooledObject<HttpURLConnection>(new HttpURLConnection(null) {
                @Override
                public void connect() throws IOException {

                }

                @Override
                public void disconnect() {

                }

                @Override
                public boolean usingProxy() {
                    return false;
                }

                @Override
                public int getResponseCode() throws IOException {
                    return 201;
                }
            }));
        } catch (Exception e) {
        }

        try {
            factory.validateObject(new DefaultPooledObject<HttpURLConnection>(new HttpURLConnection(null) {
                @Override
                public void connect() throws IOException {

                }

                @Override
                public void disconnect() {

                }

                @Override
                public boolean usingProxy() {
                    return false;
                }

                @Override
                public int getResponseCode() throws IOException {
                    throw new IOException("test");
                }
            }));
        } catch (Exception e) {
        }

        try {
            factory.destroyObject(null);
        } catch (Exception e) {
        }

        try {
            factory.destroyObject(new DefaultPooledObject<HttpURLConnection>(null));
        } catch (Exception e) {
        }

        try {
            factory.destroyObject(new DefaultPooledObject<HttpURLConnection>(new HttpURLConnection(null) {
                @Override
                public void disconnect() {

                }

                @Override
                public boolean usingProxy() {
                    return false;
                }

                @Override
                public void connect() throws IOException {

                }
            }));
        } catch (Exception e) {
        }


        HttpConnectionFactory factory2 = new HttpConnectionFactory(null, HttpConfig.DEFAULT_URL, "POST", 5000, 5000, HttpConfig.DEFAULT_HEADER);

        factory2.makeObject();

        HttpConnectionFactory factory3 = new HttpConnectionFactory(null, "https://www.baidu1.com/", "POST", 5000, 5000, HttpConfig.DEFAULT_HEADER);

        try {
            factory3.makeObject();
        } catch (Exception e) {
        }

        HttpConnectionFactory factory4 = new HttpConnectionFactory(null, "123", "POST", 5000, 5000, HttpConfig.DEFAULT_HEADER);

        try {
            factory4.makeObject();
        } catch (Exception e) {
        }
    }

    @Test
    public void test2() throws Exception {

        Properties properties = new Properties();

        try {
            new HttpConnectionFactory(properties);
        } catch (Exception e) {
        }
        properties.setProperty(HttpConfig.PROXY_HOST_PROPERTY, "127.0.0.1");
        try {
            new HttpConnectionFactory(properties);
        } catch (Exception e) {
        }
        properties.remove(HttpConfig.PROXY_HOST_PROPERTY);
        properties.setProperty(HttpConfig.PROXY_PORT_PROPERTY, "1234");
        try {
            new HttpConnectionFactory(properties);
        } catch (Exception e) {
        }
        properties.setProperty(HttpConfig.PROXY_HOST_PROPERTY, "127.0.0.1");
        try {
            new HttpConnectionFactory(properties);
        } catch (Exception e) {
        }
        properties.setProperty(HttpConfig.HTTP_URL_PROPERTY, HttpConfig.DEFAULT_URL);
        try {
            new HttpConnectionFactory(properties);
        } catch (Exception e) {
        }
        properties.setProperty(HttpConfig.HTTP_METHOD_PROPERTY, HttpConfig.DEFAULT_METHOD);
        try {
            new HttpConnectionFactory(properties);
        } catch (Exception e) {
        }
        properties.setProperty(HttpConfig.CONNECT_TIMEOUT_PROPERTY, "1000");
        try {
            new HttpConnectionFactory(properties);
        } catch (Exception e) {
        }
        properties.setProperty(HttpConfig.READ_TIMEOUT_PROPERTY, "1000");
        try {
            new HttpConnectionFactory(properties);
        } catch (Exception e) {
        }
        properties.setProperty(HttpConfig.HTTP_HEADER_PROPERTY, "Content-type:application/json");
        try {
            new HttpConnectionFactory(properties);
        } catch (Exception e) {
        }
    }
}