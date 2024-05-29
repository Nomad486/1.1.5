package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "A51yulpr57";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties props = new Properties();
                props.put(Environment.URL, DB_URL);
                props.put(Environment.USER, DB_USERNAME);
                props.put(Environment.PASS, DB_PASSWORD);
                props.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                props.put(Environment.SHOW_SQL,"true");
                props.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                props.put(Environment.HBM2DDL_AUTO, "create");

                configuration.setProperties(props);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Connected to database");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to connect to database");
            }
        }
        return sessionFactory;
    }
}
