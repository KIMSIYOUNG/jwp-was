package http.servlet.controller;

import http.request.HttpRequest;
import http.response.HttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import static testhelper.Common.getHttpRequest;

public class UserCreateControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(UserCreateControllerTest.class);

    @Test
    @DisplayName("/user/create에 대한 POST 요청 테스트")
    public void doPost() throws IOException, URISyntaxException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        Controller controller = new UserCreateController();
        HttpRequest httpRequest = getHttpRequest("HTTP_POST_USER_CREATE.txt");
        HttpResponse httpResponse = new HttpResponse(byteArrayOutputStream);
        controller.service(httpRequest, httpResponse);

        logger.info(byteArrayOutputStream.toString());
    }
}