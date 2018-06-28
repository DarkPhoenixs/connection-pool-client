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

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.darkphoenixs.pool.ConnectionException;
import org.darkphoenixs.pool.ConnectionFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * <p>HttpConnectionFactory</p>
 * <p>Http连接工厂</p>
 *
 * @author Victor
 * @see ConnectionFactory
 * @since 1.2.7
 */
public class HttpConnectionFactory implements ConnectionFactory<HttpURLConnection> {

    private static final long serialVersionUID = 7077039510216492412L;

    private final Proxy proxy;

    private final String address;

    private final String method;

    private final int connectTimeout;

    private final int readTimeout;

    private final Map<String, String> header;

    /**
     * Instantiates a new Http connection factory.
     *
     * @param proxy          the proxy
     * @param address        the address
     * @param method         the method
     * @param connectTimeout the connect timeout
     * @param readTimeout    the read timeout
     * @param header         the header
     */
    public HttpConnectionFactory(final Proxy proxy, final String address,
                                 final String method, final int connectTimeout,
                                 final int readTimeout, final Map<String, String> header) {

        this.proxy = proxy;
        this.address = address;
        this.method = method;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.header = header;
    }

    /**
     * Instantiates a new Http connection factory.
     *
     * @param properties the properties
     */
    public HttpConnectionFactory(final Properties properties) {

        String proxyHost = properties.getProperty(HttpConfig.PROXY_HOST_PROPERTY);
        String proxyPort = properties.getProperty(HttpConfig.PROXY_PORT_PROPERTY);

        if (proxyHost != null && proxyPort != null)
            proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, Integer.valueOf(proxyPort)));
        else
            proxy = null;

        address = properties.getProperty(HttpConfig.HTTP_URL_PROPERTY);
        if (address == null)
            throw new ConnectionException("[" + HttpConfig.HTTP_URL_PROPERTY + "] is required !");

        method = properties.getProperty(HttpConfig.HTTP_METHOD_PROPERTY);
        if (method == null)
            throw new ConnectionException("[" + HttpConfig.HTTP_METHOD_PROPERTY + "] is required !");

        connectTimeout = Integer.valueOf(properties.getProperty(HttpConfig.CONNECT_TIMEOUT_PROPERTY, String.valueOf(HttpConfig.DEFAULT_IMEOUT)));

        readTimeout = Integer.valueOf(properties.getProperty(HttpConfig.READ_TIMEOUT_PROPERTY, String.valueOf(HttpConfig.DEFAULT_IMEOUT)));

        header = new HashMap<String, String>();

        String headers = properties.getProperty(HttpConfig.HTTP_HEADER_PROPERTY, " : ");

        for (String headStr : headers.split(","))

            header.put(headStr.split(":")[0], headStr.split(":")[1]);
    }


    @Override
    public PooledObject<HttpURLConnection> makeObject() throws Exception {

        HttpURLConnection connection = this.createConnection();

        return new DefaultPooledObject<HttpURLConnection>(connection);
    }

    @Override
    public void destroyObject(PooledObject<HttpURLConnection> p) throws Exception {

        HttpURLConnection connection = p.getObject();

        if (connection != null)

            connection.disconnect();
    }

    @Override
    public boolean validateObject(PooledObject<HttpURLConnection> p) {

        HttpURLConnection connection = p.getObject();

        if (connection != null)

            try {
                return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
            } catch (IOException e) {
                return false;
            }

        return false;
    }

    @Override
    public void activateObject(PooledObject<HttpURLConnection> p) throws Exception {
        // TODO Auto-generated method stub
    }

    @Override
    public void passivateObject(PooledObject<HttpURLConnection> p) throws Exception {
        // TODO Auto-generated method stub
    }


    @Override
    public HttpURLConnection createConnection() throws Exception {

        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(this.address);

            if (proxy != null)
                urlConnection = (HttpURLConnection) url.openConnection(proxy);
            else
                urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setConnectTimeout(connectTimeout);
            urlConnection.setReadTimeout(readTimeout);

            for (Map.Entry<String, String> entry : header.entrySet())
                urlConnection.setRequestProperty(entry.getKey(), entry.getValue());

            urlConnection.setRequestMethod(method);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);

            if (method.equals("POST"))
                urlConnection.setUseCaches(false);

            urlConnection.connect();

        } catch (Exception e) {
            if (urlConnection != null)
                urlConnection.disconnect();
            throw e;
        }

        return urlConnection;
    }
}
