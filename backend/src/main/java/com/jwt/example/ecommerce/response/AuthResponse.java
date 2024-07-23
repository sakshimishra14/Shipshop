package com.jwt.example.ecommerce.response;

public class AuthResponse {
    private String jwt;
    private boolean status;

    // Private constructor to prevent direct instantiation
    private AuthResponse() {}

    // Builder class
    public static class AuthResponseBuilder {
        private String jwt;
        private boolean status;

        public AuthResponseBuilder jwt(String jwt) {
            this.jwt = jwt;
            return this;
        }

        public AuthResponseBuilder status(boolean status) {
            this.status = status;
            return this;
        }

        public AuthResponse build() {
            AuthResponse response = new AuthResponse();
            response.jwt = this.jwt;
            response.status = this.status;
            return response;
        }
    }

    public static AuthResponseBuilder builder() {
        return new AuthResponseBuilder();
    }

    // Getters and setters
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
