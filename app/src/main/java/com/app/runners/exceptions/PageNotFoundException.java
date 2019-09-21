package com.app.runners.exceptions;

public class PageNotFoundException extends HttpException {

    @Override
    public String getMessage() {
        return "404 page not found exception";
    }

}
