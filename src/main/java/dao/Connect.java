package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    public Connection connect(){
        String url = "jdbc:postgresql://127.0.0.1:5432/phoneTalking";
        String name = "postgres";
        String password = "1";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, name, password);
        }
        catch (Exception e){
            return null;
        }
    }
}
