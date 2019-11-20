package com.lineate.traineeship;

import org.junit.Assert;
import org.junit.Test;


public class PermissionFlagTest {

    @Test
    public void testPermissionFlag() {
        Assert.assertEquals( Permission.valueOf("read").name(), "read");
        Assert.assertEquals( Permission.valueOf("write").name(), "write");
    }
}
