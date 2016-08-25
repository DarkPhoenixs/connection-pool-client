package org.darkphoenixs.pool;

import org.junit.Assert;
import org.junit.Test;

public class ConnectionExceptionTest {

    @Test
    public void test() throws Exception {

        ConnectionException exp0 = new ConnectionException();

        Assert.assertNull(exp0.getMessage());

        ConnectionException exp1 = new ConnectionException("ConnectionException1");

        Assert.assertEquals("ConnectionException1", exp1.getMessage());

        Throwable thr2 = new Throwable("ConnectionException2");

        ConnectionException exp2 = new ConnectionException(thr2);

        Assert.assertEquals(thr2, exp2.getCause());

        Throwable thr3 = new Throwable("ConnectionException3");

        ConnectionException exp3 = new ConnectionException("ConnectionException3", thr3);

        Assert.assertEquals("ConnectionException3", exp3.getMessage());

        Assert.assertEquals(thr3, exp3.getCause());
    }
}
