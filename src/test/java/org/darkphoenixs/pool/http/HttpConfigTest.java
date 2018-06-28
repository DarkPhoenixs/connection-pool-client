package org.darkphoenixs.pool.http;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class HttpConfigTest {

    @Test
    public void test() throws Exception {

        Assert.assertEquals(HttpConfig.DEFAULT_URL, "https://www.baidu.com/");
        Assert.assertEquals(HttpConfig.DEFAULT_METHOD, "GET");
        Assert.assertEquals(HttpConfig.DEFAULT_IMEOUT, 30000);
        Assert.assertEquals(HttpConfig.DEFAULT_HEADER, Collections.singletonMap("Content-type", "application/json"));

        Assert.assertEquals(HttpConfig.PROXY_HOST_PROPERTY, "proxyHost");
        Assert.assertEquals(HttpConfig.PROXY_PORT_PROPERTY, "proxyPort");
        Assert.assertEquals(HttpConfig.HTTP_URL_PROPERTY, "httpUrl");
        Assert.assertEquals(HttpConfig.HTTP_METHOD_PROPERTY, "httpMethod");
        Assert.assertEquals(HttpConfig.CONNECT_TIMEOUT_PROPERTY, "connectTimeout");
        Assert.assertEquals(HttpConfig.READ_TIMEOUT_PROPERTY, "readTimeout");
        Assert.assertEquals(HttpConfig.HTTP_HEADER_PROPERTY, "httpHeader");
    }
}