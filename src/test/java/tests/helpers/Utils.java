package tests.helpers;

import request.entities.user.Data;
import request.entities.user.User;

import java.util.Random;

public class Utils {

    public static int generateId(){
        int randomNum;
        int max = 100000;
        int min = 1000;
        Random rn = new Random();
        int n = max - min + 1;
        int i = rn.nextInt() % n;
        randomNum =  min + i;
        return randomNum;
    }

    public static User createTestUser(){
        User user = new User();
        Data userData = new Data();
        int userId = generateId();
        userData.setId(userId);
        userData.setName("TestName" + userId);
        userData.setEmail("test" + userId + "@test.gmail.com");
        user.setData(userData);
        //Run RestAssured request to create user using API and return User object
        return user;
    }



}
