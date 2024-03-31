package com.example.demo.Response;

public class LoginResponse {
    private String message;
    private boolean success;
    private String token;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginResponse() {};

    public LoginResponse(String message, boolean success, String token) {
        this.message = message;
        this.success = success;
        this.token = token;
    }

    public LoginResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }


    public static LoginResponseBuilder builder() {
        return new LoginResponseBuilder();
    }

    public static class LoginResponseBuilder {
        private String message;
        private boolean success;
        private String token;

        private LoginResponseBuilder() {}

        public LoginResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public LoginResponseBuilder success(boolean success) {
            this.success = success;
            return this;
        }

        public LoginResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public LoginResponse build() {
            LoginResponse response = new LoginResponse();
            response.setMessage(message);
            response.setSuccess(success);
            response.setToken(token);
            return response;
        }
    }
}
