package com.fiap.fintech;

import com.fiap.fintech.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConnection {
    public static void main(String[] args) {
        System.out.println("Testando conexão Oracle...");
        try (
            Connection conn = ConnectionFactory.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT 1 FROM dual")) {

            if (rs.next()) {
                System.out.println("Conexão OK! SELECT 1 = " + rs.getInt(1));
            }

            DatabaseMetaData md = conn.getMetaData();
            System.out.println("Banco: " + md.getDatabaseProductName() + " " + md.getDatabaseProductVersion());
            System.out.println("Usuário/schema: " + md.getUserName());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
