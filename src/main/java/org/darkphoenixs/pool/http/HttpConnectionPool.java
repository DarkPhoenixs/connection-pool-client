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
package org.darkphoenixs.pool.http;

import org.darkphoenixs.pool.ConnectionPool;
import org.darkphoenixs.pool.PoolBase;
import org.darkphoenixs.pool.PoolConfig;

import java.net.HttpURLConnection;
import java.net.Proxy;
import java.util.Map;
import java.util.Properties;

/**
 * <p>HttpConnectionPool</p>
 * <p>Http连接池</p>
 *
 * @author Victor
 * @see PoolBase
 * @see ConnectionPool
 * @since 1.2.7
 */
public class HttpConnectionPool extends PoolBase<HttpURLConnection> implements ConnectionPool<HttpURLConnection> {

    private static final long serialVersionUID = -6365735440944992890L;

    /**
     * Instantiates a new Http connection pool.
     */
    public HttpConnectionPool() {

        this(HttpConfig.DEFAULT_URL);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param url the url
     */
    public HttpConnectionPool(final String url) {

        this(url, HttpConfig.DEFAULT_METHOD);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param poolConfig the pool config
     * @param url        the url
     */
    public HttpConnectionPool(final PoolConfig poolConfig, final String url) {

        this(poolConfig, url, HttpConfig.DEFAULT_METHOD);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param poolConfig the pool config
     * @param proxy      the proxy
     * @param url        the url
     */
    public HttpConnectionPool(final PoolConfig poolConfig, final Proxy proxy, final String url) {

        this(poolConfig, proxy, url, HttpConfig.DEFAULT_METHOD);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param proxy the proxy
     * @param url   the url
     */
    public HttpConnectionPool(final Proxy proxy, final String url) {

        this(proxy, url, HttpConfig.DEFAULT_METHOD);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param url    the url
     * @param method the method
     */
    public HttpConnectionPool(final String url, final String method) {

        this(new PoolConfig(), url, method);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param proxy  the proxy
     * @param url    the url
     * @param method the method
     */
    public HttpConnectionPool(final Proxy proxy, final String url, final String method) {

        this(new PoolConfig(), proxy, url, method);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param poolConfig the pool config
     * @param url        the url
     * @param method     the method
     */
    public HttpConnectionPool(final PoolConfig poolConfig, final String url, final String method) {

        this(poolConfig, url, method, HttpConfig.DEFAULT_IMEOUT, HttpConfig.DEFAULT_IMEOUT);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param poolConfig the pool config
     * @param proxy      the proxy
     * @param url        the url
     * @param method     the method
     */
    public HttpConnectionPool(final PoolConfig poolConfig, final Proxy proxy, final String url, final String method) {

        this(poolConfig, proxy, url, method, HttpConfig.DEFAULT_IMEOUT, HttpConfig.DEFAULT_IMEOUT);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param poolConfig     the pool config
     * @param url            the url
     * @param method         the method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     */
    public HttpConnectionPool(final PoolConfig poolConfig, final String url, final String method, final int connectTimeout, final int readTimeout) {

        this(poolConfig, url, method, connectTimeout, readTimeout, HttpConfig.DEFAULT_HEADER);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param poolConfig     the pool config
     * @param proxy          the proxy
     * @param url            the url
     * @param method         the method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     */
    public HttpConnectionPool(final PoolConfig poolConfig, final Proxy proxy, final String url, final String method, final int connectTimeout, final int readTimeout) {

        this(poolConfig, proxy, url, method, connectTimeout, readTimeout, HttpConfig.DEFAULT_HEADER);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param poolConfig     the pool config
     * @param url            the url
     * @param method         the method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param header         the header
     */
    public HttpConnectionPool(final PoolConfig poolConfig, final String url, final String method, final int connectTimeout, final int readTimeout, final Map<String, String> header) {

        this(poolConfig, null, url, method, connectTimeout, readTimeout, header);
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param poolConfig the pool config
     * @param properties the properties
     */
    public HttpConnectionPool(final PoolConfig poolConfig, final Properties properties) {

        super(poolConfig, new HttpConnectionFactory(properties));
    }

    /**
     * Instantiates a new Http connection pool.
     *
     * @param poolConfig     the pool config
     * @param proxy          the proxy
     * @param url            the url
     * @param method         the method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param header         the header
     */
    public HttpConnectionPool(final PoolConfig poolConfig, final Proxy proxy, final String url, final String method, final int connectTimeout, final int readTimeout, final Map<String, String> header) {

        super(poolConfig, new HttpConnectionFactory(proxy, url, method, connectTimeout, readTimeout, header));
    }

    @Override
    public HttpURLConnection getConnection() {
        return super.getResource();
    }

    @Override
    public void returnConnection(HttpURLConnection conn) {
        super.returnResource(conn);
    }

    @Override
    public void invalidateConnection(HttpURLConnection conn) {
        super.invalidateResource(conn);
    }
}
