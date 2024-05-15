package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR (255), lastName VARCHAR (255), age INT)");
            connection.commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.executeUpdate("DROP TABLE IF EXISTS users");
            connection.commit();
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement("INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)")) {
            connection.setAutoCommit(false);
            prepStmt.setString(1, name);
            prepStmt.setString(2, lastName);
            prepStmt.setByte(3, age);
            prepStmt.executeUpdate();
            connection.commit();
            System.out.println("User " + name + " has been saved");
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnection();
             PreparedStatement prepStmt = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            connection.setAutoCommit(false);
            prepStmt.setLong(1, id);
            prepStmt.executeUpdate();
            connection.commit();
            System.out.println("User " + id + " has been removed");
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastName");
                byte age = rs.getByte("age");
                User user = new User(id, name, lastName, age);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement stmt = connection.createStatement()) {
            connection.setAutoCommit(false);
            stmt.execute("TRUNCATE TABLE users");
            connection.commit();
            System.out.println("Users table has been cleaned");
        } catch (SQLException e) {
            rollback();
            e.printStackTrace();
        }
    }

    private void rollback() {
        try {
            Connection connection = Util.getConnection();
            connection.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
