package app;

import app.config.ApplicationConfig;
import app.controllers.impl.ExceptionController;
import app.exceptions.ApiException;
import app.exceptions.Message;
import app.routes.Routes;
import io.javalin.Javalin;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args) {
        ApplicationConfig.startServer(7070);
    }