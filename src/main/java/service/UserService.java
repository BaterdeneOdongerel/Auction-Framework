package service;


import model.User;
import model.LabelValue;


import java.util.List;

public interface UserService extends CrudTemplate<User> {

    User selectByEmail(String email);

    boolean login(String email, String password);

    List<User> selectByName(String name);

    User getCurrentUser();

    List<LabelValue> getUserListForDropDown();
}
