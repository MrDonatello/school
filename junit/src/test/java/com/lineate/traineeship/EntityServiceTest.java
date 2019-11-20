package com.lineate.traineeship;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class EntityServiceTest {

    @Test
    public void testRead() {
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.createUserService();
        EntityService entityService = serviceFactory.createEntityService();

        Group group1 = userService.createGroup("g1", Collections.singleton(Permission.read));
        User user1 = userService.createUser("u1", group1);
        entityService.createEntity(user1, "e1", "v1");
        Assert.assertEquals("v1", entityService.getEntityValue(user1, "e1"));

        User user2 = userService.createUser("u2", group1);
        Assert.assertEquals("v1", entityService.getEntityValue(user2, "e1"));

        Group group2 = userService.createGroup("g2", Collections.singleton(Permission.read));
        User user3 = userService.createUser("u3", group2);
        Assert.assertNull(entityService.getEntityValue(user3, "e1"));
        Assert.assertFalse(entityService.updateEntity(user3, "e1", "v3"));

        Group group3 = userService.createGroup("g3", Collections.singleton(Permission.write));
        user1.getGroups().add(group3);
        user3.getGroups().add(group3);
        entityService.createEntity(user1, "e2", "v1");
        Assert.assertEquals("v1", entityService.getEntityValue(user1, "e2"));
        Assert.assertEquals("v1", entityService.getEntityValue(user2, "e2"));
        Assert.assertNull(entityService.getEntityValue(user3, "e2"));
        Assert.assertTrue(entityService.updateEntity(user3, "e2", "v2"));
        Assert.assertEquals("v2", entityService.getEntityValue(user1, "e2"));
    }

    @Test
    public void testUpdate() {
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.createUserService();
        EntityService entityService = serviceFactory.createEntityService();

        Group group = userService.createGroup("group", Collections.singleton(Permission.read));
        User user = userService.createUser("user", group);
        entityService.createEntity(user, "entity", "value");

        Assert.assertEquals("value", entityService.getEntityValue(user, "entity"));
        entityService.updateEntity(user, "entity", "value2");
        Assert.assertEquals("value2", entityService.getEntityValue(user, "entity"));
    }

    @Test
    public void testWrite() {
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.createUserService();
        EntityService entityService = serviceFactory.createEntityService();

        Group group1 = userService.createGroup("g1", Collections.singleton(Permission.write));
        User user1 = userService.createUser("u1", group1);
        entityService.createEntity(user1, "e1", "v1");
        Assert.assertEquals("v1", entityService.getEntityValue(user1, "e1"));

        User user2 = userService.createUser("u2", group1);
        Assert.assertNull(entityService.getEntityValue(user2, "e1"));

        Assert.assertTrue(entityService.updateEntity(user1, "e1", "v2"));
        Assert.assertEquals("v2", entityService.getEntityValue(user1, "e1"));
        Assert.assertNull(entityService.getEntityValue(user2, "e1"));

        Assert.assertTrue(entityService.updateEntity(user2, "e1", "v3"));
        Assert.assertEquals("v3", entityService.getEntityValue(user1, "e1"));
        Assert.assertNull(entityService.getEntityValue(user2, "e1"));
    }
}
