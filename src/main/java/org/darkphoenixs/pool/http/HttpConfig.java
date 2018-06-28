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

import java.util.Collections;
import java.util.Map;

/**
 * <p>HttpConfig</p>
 * <p>Http配置</p>
 *
 * @author Victor
 * @since 1.2.7
 */
public interface HttpConfig {

    String DEFAULT_URL = "https://www.baidu.com/";

    String DEFAULT_METHOD = "GET";

    Map DEFAULT_HEADER = Collections.singletonMap("Content-type", "application/json");

    int DEFAULT_IMEOUT = 30000;

    String PROXY_HOST_PROPERTY = "proxyHost";

    String PROXY_PORT_PROPERTY = "proxyPort";

    String HTTP_URL_PROPERTY = "httpUrl";

    String HTTP_METHOD_PROPERTY = "httpMethod";

    String CONNECT_TIMEOUT_PROPERTY = "connectTimeout";

    String READ_TIMEOUT_PROPERTY = "readTimeout";

    String HTTP_HEADER_PROPERTY = "httpHeader";

}
