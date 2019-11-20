package com.lineate.traineeship;

import org.junit.Assert;
import org.junit.Test;


import java.util.Collections;

public class UserServiceNameTest {

    private static ServiceFactory serviceFactory = new ServiceFactory();
    private static UserService userService = serviceFactory.createUserService();
    private  static Group group = userService.createGroup("group", Collections.emptySet());

    @Test
    public void testUserName() {
        User user1 = userService.createUser("user", group);
        User user2 = userService.createUser("", group);
        User user3 = userService.createUser(" ", group);
        User user4 = userService.createUser("   ", group);


        Assert.assertNotEquals(0, user1.getName().trim().length());//хорошо
        Assert.assertNotEquals(0, user2.getName().trim().length());//провал
        Assert.assertNotEquals(0, user3.getName().trim().length());//провал
        Assert.assertNotEquals(0, user4.getName().trim().length());//провал

        try{
            User user5 = userService.createUser(null, group);
            // Assert.assertNotEquals(0, user5.getName().trim().length());

        }catch (NullPointerException e){
            System.out.println(e);
        }
    }

    @Test
    public void testGroupName() {
        Group group2 = userService.createGroup("", Collections.emptySet());
        Group group3 = userService.createGroup(" ", Collections.emptySet());
        Group group4 = userService.createGroup("    ", Collections.emptySet());

        Assert.assertNotEquals(0, group.getName().trim().length());//хорошо
        Assert.assertNotEquals(0, group2.getName().trim().length());//провал
        Assert.assertNotEquals(0, group3.getName().trim().length());//провал
        Assert.assertNotEquals(0, group4.getName().trim().length());//провал
    }
}
