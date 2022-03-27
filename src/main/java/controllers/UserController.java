package controllers;

import models.UserCreateResponse;
import models.UserData;
import models.dto.common.ResponseContent;
import models.dto.common.ResponseData;
import utils.JsonParser;
import utils.PropertyReader;
import utils.RequestHandler;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class UserController {
    public static final String BASE_URL = PropertyReader.getProperty("base.url");

    public ResponseData<UserData> getUsers(String username, Map<String, String> headers) throws ExecutionException, InterruptedException {
        String url = BASE_URL + "/user/" + username;
        ResponseContent responseContent = RequestHandler.httpGET(url, headers);
        UserData userData = JsonParser.parseToObject(UserData.class, responseContent.getContent());
        return new ResponseData<>(responseContent.getStatusCode(), userData);
    }

    public ResponseData<UserCreateResponse> creteUser(UserData userData, Map<String, String> headers) throws UnsupportedEncodingException, ExecutionException, InterruptedException {
        String url = BASE_URL + "/user/";
        ResponseContent responseContent = RequestHandler.httpPOST(url, userData, headers);
        UserCreateResponse response = JsonParser.parseToObject(UserCreateResponse.class, responseContent.getContent());
        return new ResponseData<>(responseContent.getStatusCode(), response);
    }
}
