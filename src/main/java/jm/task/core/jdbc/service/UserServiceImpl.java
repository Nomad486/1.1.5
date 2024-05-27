package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao daoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        daoHibernate.createUsersTable();
        System.out.println("Created users table");
    }

    public void dropUsersTable() {
        daoHibernate.dropUsersTable();
        System.out.println("Dropped users table");
    }

    public void saveUser(String name, String lastName, byte age) {
        daoHibernate.saveUser(name, lastName, age);
        System.out.println("User " + name + " has been saved");
    }

    public void removeUserById(long id) {
        daoHibernate.removeUserById(id);
        System.out.println("User " + id + " has been removed");
    }

    public List<User> getAllUsers() {
        return daoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
        daoHibernate.cleanUsersTable();
        System.out.println("Cleaned users table");
    }
}
