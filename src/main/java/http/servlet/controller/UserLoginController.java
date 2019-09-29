package http.servlet.controller;

import com.google.common.base.Charsets;
import domain.UserService;
import http.request.HttpRequest;
import http.response.HttpResponse;
import http.servlet.controller.exception.Page404NotFoundException;
import http.servlet.resolver.UserResolver;
import http.servlet.view.View;
import http.servlet.view.support.ViewResolver;

import java.io.IOException;

public class UserLoginController extends HttpController {

    @Override
    protected void doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        throw new Page404NotFoundException();
    }

    @Override
    protected void doPost(HttpRequest httpRequest, HttpResponse httpResponse) throws IOException {
        UserService userService = new UserService();
        httpResponse.addHeader("Content-Type", "text/html");

        if (userService.login(UserResolver.resolve(httpRequest))) {
            httpResponse.addHeader("Location", "/index.html");
            httpRequest.addSessionAttribute("logined", "true");
            httpResponse.addCookie("Path", "/");
            httpResponse.sendRedirect();
            return;
        }
        httpRequest.addSessionAttribute("logined", "false");
        httpResponse.addCookie("Path", "/");
        View view = ViewResolver.resolve("/user/login_failed.html");
        httpResponse.forward(view.render(Charsets.UTF_8));
    }
}