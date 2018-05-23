package com.taxi.shared;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

public class Login implements Serializable {
    private String login;
    private String password;

    public Login() {}

    @JsonCreator
    public Login(@JsonProperty("login") String login,
                 @JsonProperty("password") String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return Objects.equals(login, login1.login) &&
                Objects.equals(password, login1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }
}
