package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> usersList() {
        return userDao.usersList();
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public void save(User user) {
        user.addRole(userDao.getRole(2L));
        userDao.save(user);
    }

    @Override
    public void update(Long id, User user) {
        userDao.update(id, user);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User getUserByName(String username) {
        return userDao.getUserByName(username);
    }
}
