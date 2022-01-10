package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> usersList();
    User getUser(Long id);
    void save(User user);
    void update(Long id, User user);
    void delete(Long id);
    User getUserByName(String username);
}
