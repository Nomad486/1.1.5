package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl();

        userDao.createUsersTable();

        userDao.saveUser("Иван", "Иванов", (byte) 30);
        userDao.saveUser("Петр", "Петров", (byte) 25);
        userDao.saveUser("Сидор", "Сидоров", (byte) 20);
        userDao.saveUser("Николай", "Николаев", (byte) 35);

        userDao.removeUserById(2);

        userDao.getAllUsers();
    }
}
