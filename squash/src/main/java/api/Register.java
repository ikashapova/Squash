package api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Register {
    private String email;
    private String password;

    public Register(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @JsonProperty("Email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("Password")
    public String getPassword() {
        return password;
    }
}