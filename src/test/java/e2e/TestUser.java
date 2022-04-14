package e2e;

import base.TestBase;
import controllers.UserController;
import models.user.UserCreateResponse;
import models.user.UserData;
import models.dto.common.ResponseData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonParser;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TestUser extends TestBase {

    private final Map<String, String> headers = new HashMap<>();
    private UserController userController;
    private UserData userData;
    private final UserData[] userDataArr = new UserData[2];

    @BeforeClass
    public void setUp() {
        userController = new UserController();
        userData = JsonParser.getJsonObjectFromFile(UserData.class, 1002);
        userDataArr[0] = JsonParser.getJsonObjectFromFile(UserData.class, 1084);
        userDataArr[1] = JsonParser.getJsonObjectFromFile(UserData.class,  1003);
        headers.put("content-type", "application/json");
    }

    @Test
    public void testUserCreation() throws ExecutionException, InterruptedException, UnsupportedEncodingException {
        ResponseData<UserCreateResponse> responseData = userController.creteUser(userData, headers);
        Assert.assertEquals(responseData.getStatusCode(), 200);
    }


    @Test(dependsOnMethods = "testUserCreation")
    public void testGetUserByUsername() throws ExecutionException, InterruptedException {
        ResponseData<UserData> responseData = userController.getUsers(userData.getUsername(), headers);
        Assert.assertEquals(responseData.getStatusCode(), 200);
        Assert.assertEquals(responseData.getEntity(), userData);
    }

    @Test(dependsOnMethods = "testGetUserByUsername")
    public void testUpdateUserData() throws UnsupportedEncodingException, ExecutionException, InterruptedException {
        userData.setPhone("+97718846923");
        ResponseData<UserCreateResponse> updateResponse = userController.updateUser(userData, headers);
        ResponseData<UserData> responseData = userController.getUsers(userData.getUsername(), headers);
        Assert.assertEquals(updateResponse.getStatusCode(), 200);
        Assert.assertEquals(responseData.getStatusCode(), 200);
        Assert.assertEquals(responseData.getEntity(), userData);

    }

    @Test(dependsOnMethods = "testUpdateUserData")
    public void testDeleteUser() throws ExecutionException, InterruptedException {
        ResponseData<UserCreateResponse> deleteResponse = userController.deleteUser(userData.getUsername(), headers);
        Assert.assertEquals(deleteResponse.getStatusCode(), 200);
    }

    @Test
    public void testCreateMultipleUsers() throws UnsupportedEncodingException, ExecutionException, InterruptedException {
        ResponseData<UserCreateResponse> responseData = userController.createWithArray(userDataArr,headers);
        Assert.assertEquals(responseData.getStatusCode(), 200);
    }


}
