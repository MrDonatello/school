package com.lineate.traineeship;

import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;

public class UserServiceGroupTest {

    @Test
    public void testUserGroup() {
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.createUserService();
        Group group = userService.createGroup("group", Collections.emptySet());
        User user1 = userService.createUser("user", group);
        Assert.assertNotNull( user1.getGroups());
        Assert.assertFalse( user1.getGroups().isEmpty());

        try{
            User user2 = userService.createUser("user2", null);

        }catch (NullPointerException e){
            System.out.println(e);
        }
    }

    @Test
    public void testGroupPermission() {
        ServiceFactory serviceFactory = new ServiceFactory();
        UserService userService = serviceFactory.createUserService();
        Group group = userService.createGroup("group", Collections.singleton(Permission.read));
        Group group2 = userService.createGroup("group", Collections.emptySet());
        Group group3 = userService.createGroup("group", null);
        Assert.assertFalse( group.getPermissions().isEmpty());//хорошо
        Assert.assertFalse( group2.getPermissions().isEmpty());//проваал
        Assert.assertNotNull( group3.getPermissions());//проваал

    }
}
