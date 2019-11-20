package com.lineate.traineeship;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collections;


public class EntityNameTest {

    @Parameterized.Parameters(name = "{index}: [{0}] -> {1}")
    public static Iterable<Object[]> names() {
        return Arrays.asList(new Object[][] {
                { "valid", true },
                { "invalid :(", false },
                { " ", false },
                { "  ", false },
                { "\t", true },
                { "1", true },
                { "0123456789012345678901234567890123", false },
                { "name!!11", true } });
    }

    @Parameterized.Parameter(0)
    public String fInput;

    @Parameterized.Parameter(1)
    public boolean fExpected;

    @Test
    public void testName() {
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.createUserService();
        Group group = userService.createGroup("group", Collections.emptySet());
        User user = userService.createUser("user", group);
        EntityService entityService = serviceFactory.createEntityService();
        Assert.assertEquals(fExpected, entityService.createEntity(user, fInput, "value"));
    }
}
