package be.bstorm.demowebappjakartaee.models.user;

import be.bstorm.demowebappjakartaee.entities.User;

import java.util.Objects;

public class UserForm {

    private String username;
    private String password;

    public UserForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "UserForm{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserForm userForm = (UserForm) o;
        return Objects.equals(username, userForm.username) && Objects.equals(password, userForm.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
