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
package org.darkphoenixs.pool.jdbc;

import org.junit.Assert;
import org.junit.Test;

public class JdbcConfigTest {

    @Test
    public void test() throws Exception {

        Assert.assertEquals(JdbcConfig.DEFAULT_DRIVER_CLASS, "com.mysql.jdbc.Driver");
        Assert.assertEquals(JdbcConfig.DEFAULT_JDBC_URL, "jdbc:mysql://localhost:3306/test");
        Assert.assertEquals(JdbcConfig.DEFAULT_JDBC_USERNAME, "root");
        Assert.assertEquals(JdbcConfig.DEFAULT_JDBC_PASSWORD, "root");

        Assert.assertEquals(JdbcConfig.DRIVER_CLASS_PROPERTY, "driverClass");
        Assert.assertEquals(JdbcConfig.JDBC_URL_PROPERTY, "jdbcUrl");
        Assert.assertEquals(JdbcConfig.JDBC_USERNAME_PROPERTY, "username");
        Assert.assertEquals(JdbcConfig.JDBC_PASSWORD_PROPERTY, "password");
    }

}
