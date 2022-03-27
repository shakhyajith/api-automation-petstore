package controllers;

import models.user.UserCreateResponse;
import models.user.UserData;
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

public ResponseData<UserCreateResponse> createWithArray(UserData[] userData, Map<String, String> headers) throws UnsupportedEncodingException, ExecutionException, InterruptedException {
    String url = BASE_URL + "/user/"+"createWithArray";
    ResponseContent responseContent = RequestHandler.httpPOST(url, userData, headers);
    UserCreateResponse response = JsonParser.parseToObject(UserCreateResponse.class, responseContent.getContent());
    return new ResponseData<>(responseContent.getStatusCode(), response);
}
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

    public ResponseData<UserCreateResponse> updateUser(UserData userData, Map<String, String> headers) throws UnsupportedEncodingException, ExecutionException, InterruptedException {
        String url = BASE_URL + "/user/" + userData.getUsername();
        ResponseContent responseContent = RequestHandler.httpUPDATE(url, userData, headers);
        UserCreateResponse response = JsonParser.parseToObject(UserCreateResponse.class, responseContent.getContent());
        return new ResponseData<>(responseContent.getStatusCode(), response);
    }

    public ResponseData<UserCreateResponse> deleteUser(String username, Map<String, String> headers) throws ExecutionException, InterruptedException {
        String url = BASE_URL + "/user/" + username;
        ResponseContent responseContent = RequestHandler.httpDELETE(url, headers);
        UserCreateResponse userData = JsonParser.parseToObject(UserCreateResponse.class, responseContent.getContent());
        return new ResponseData<>(responseContent.getStatusCode(), userData);
    }
}
