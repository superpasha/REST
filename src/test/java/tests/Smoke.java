package tests;

import builders.UserBuilder;
import org.junit.jupiter.api.Test;
import request.entities.user.Data;
import request.entities.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.Resources;
import tests.helpers.Utils;

import static io.restassured.RestAssured.given;
import static tests.helpers.Utils.generateId;

public class Smoke extends BaseTest{
    UserBuilder userBuider = new UserBuilder();
    User user;
    private static Logger logger = LoggerFactory.getLogger(Smoke.class);

    @Test
    public void CreateUserWithValidParameters(){
        logger.info("----TEST START: CreateUserWithValidParameters ----");
        String userName = "TestUser" + generateId();
        user = userBuider.withName(userName).build();
        requestSpecification = given()
                .spec(requestSpecification())
                .basePath(Resources.valueOf("users").getResource()).body(user);
        logger.info("Test user created with name: " + userName);

        //running "when" restAssured where we call POST http method
        //in "then" section we assert that status code is 200 and response
        //Assert that user that returned in JSON has name equal to "userName" field that we stored before.
        //example
        //Assert.assertEquals(200, response.getStatusCode());
        //Assert.assertEquals(userName, getJsonPath(response, "name");

        logger.info("----TEST FINISHED: CreateUserWithValidParameters ----");

    }

    @Test
    public void GetUserById(){
        logger.info("----TEST START: GetUserById ----");

        //Create test user to get
        User testUser = Utils.createTestUser();
        Data userData = testUser.getData();
        int testUserId = userData.getId();
        logger.info("Test user created with ID: " + testUserId);

        requestSpecification = given().spec(requestSpecification())
                .pathParam("testUserId", testUserId)
                .basePath(Resources.valueOf("users").getResource() + "/{testUserId}");

        //running "when" restAssured method where we call GET http method
        //in "then" section we assert that status code is 200 and response
        //Assert that user that returned in JSON has name equal to "userId" field that we stored before.
        //example
        //Assert.assertEquals(200, response.getStatusCode());
        //Assert.assertEquals(testUserId, getJsonPath(response, "id");

        logger.info("----TEST FINISHED: GetUserById ----");
    }

    @Test
    public void UpdateUserDetails_Email(){
        logger.info("----TEST START: UpdateUserDetails_Email ----");

        //Create test user to get
        User testUser = Utils.createTestUser();
        Data userData = testUser.getData();
        int testUserId = userData.getId();
        String testUserEmail = userData.getEmail();
        logger.info("Test user created with ID: " + testUserId);
        //Change email
        String newUserEmail = "test@test.com";
        userData.setEmail(newUserEmail);
        requestSpecification = given().spec(requestSpecification())
                .basePath(Resources.valueOf("users").getResource()).body(testUser);
        //running "when" restAssured method where we call PUT http method
        //in "then" section we assert that status code is 200 and response contains new value for "email"


        logger.info("----TEST FINISHED: UpdateUserDetails_Email ----");
    }

    @Test
    public void DeleteUser(){
        logger.info("----TEST START: DeleteUser ----");

        //Create test user to get
        User testUser = Utils.createTestUser();
        Data userData = testUser.getData();
        int testUserId = userData.getId();
        logger.info("Test user created with ID: " + testUserId);
        //Run get user details request to make sure user created

        requestSpecification = given().spec(requestSpecification())
                .pathParam("testUserId", testUserId)
                .basePath(Resources.valueOf("users").getResource() + "/{testUserId}");
        //running "when" restAssured method where we call DELETE http method
        //in "then" section we assert that status code is 204
        //call get user details resource for test user to check that user is really deleted

        logger.info("----TEST FINISHED: DeleteUser ----");
    }



}
