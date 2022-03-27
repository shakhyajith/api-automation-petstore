package e2e;

import controllers.UserController;
import models.UserData;
import models.dto.common.ResponseData;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.JsonParser;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class TestUser {

    private final Map<String,String> headers = new HashMap<>();
    private UserController userController;
    private UserData userData;

    @BeforeClass
    public void setUp() {
        userController = new UserController();
        userData = JsonParser.getJsonObjectFromFile(UserData[].class,"id",1002);
        headers.put("content-type","application/json");
    }

    @Test
    public void testUserCreation() throws ExecutionException, InterruptedException {
        System.out.println(userData);
        ResponseData<UserData> userDataResponseData = userController.getUsers(userData.getUsername(),headers);
        System.out.println(userDataResponseData);
    }


}
