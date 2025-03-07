package edu.demosql.first.repository.config;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    public static final String DB_URL = "db.url";
    public static final String DB_LOGIN = "db.login";
    public static final String DB_PASSWORD = "db.password";
    public static final String JDBC_DRIVER = "jdbc.driver";
    public static final String CR_URL = "cr.url";

    private static Properties properties = new Properties();

    public synchronized static String getProperty (String name) {
        if (properties.isEmpty()) {
            try (InputStream is = Config.class.getClassLoader()
                    .getResourceAsStream("dao.properties")) {
                properties.load(is);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return properties.getProperty(name);
    }
}