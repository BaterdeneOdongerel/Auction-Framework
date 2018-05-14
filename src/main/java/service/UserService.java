package service;

import model.user.User;

import java.util.List;

public interface UserService {

    void insert(User user);
    User selectByEmail(String email);

    User selectById(int id);

    List<User> selectAll();

    void delete(int id);

    void update(User user, int id);
    
    boolean login(String email, String password);

    List<User> selectbyName(String name);

    User getCurrentUser();
}
