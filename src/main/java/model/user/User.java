package model.user;

import lombok.Data;

@Data
public class User {

    private int userId;

    private String username;

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {

    }


}
