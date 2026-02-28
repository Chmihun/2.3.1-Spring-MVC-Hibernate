package chmihun.service;

import chmihun.dao.UserDao;
import chmihun.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImp(UserDao userDao) {this.userDao = userDao;}

    @Override
    @Transactional
    public void add(User user) {
        userDao.saveUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.updateUser(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userDao.deleteUser(id);
    }
}
