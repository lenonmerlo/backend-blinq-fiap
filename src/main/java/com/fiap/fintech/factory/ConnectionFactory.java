package com.fiap.fintech.factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final String PROPERTIES_FILE = "/db.properties";

    private static final String url;
    private static final String user;
    private static final String password;

    static {
        try (InputStream input = ConnectionFactory.class.getResourceAsStream(PROPERTIES_FILE)) {
            Properties props = new Properties();
            if (input == null) {
                throw new RuntimeException("Arquivo db.properties não encontrado.");
            }
            props.load(input);

            url = props.getProperty("db.url");
            user = props.getProperty("db.user");
            password = props.getProperty("db.password");

            Class.forName("oracle.jdbc.OracleDriver");

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Erro ao carregar configurações de banco", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
