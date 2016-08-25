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
package org.darkphoenixs.pool.hbase;

import org.junit.Assert;
import org.junit.Test;

public class HbaseConfigTest {

    @Test
    public void test() throws Exception {

        Assert.assertEquals(HbaseConfig.DEFAULT_HOST, "localhost");
        Assert.assertEquals(HbaseConfig.DEFAULT_PORT, "2181");
        Assert.assertNull(HbaseConfig.DEFAULT_MASTER);
        Assert.assertNull(HbaseConfig.DEFAULT_ROOTDIR);

        Assert.assertEquals(HbaseConfig.ZOOKEEPER_QUORUM_PROPERTY, "hbase.zookeeper.quorum");
        Assert.assertEquals(HbaseConfig.ZOOKEEPER_CLIENTPORT_PROPERTY, "hbase.zookeeper.property.clientPort");
        Assert.assertEquals(HbaseConfig.MASTER_PROPERTY, "hbase.master");
        Assert.assertEquals(HbaseConfig.ROOTDIR_PROPERTY, "hbase.rootdir");
    }
}
