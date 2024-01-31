package kg.kursanov.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Configration{



    private final  static String url = "jdbc:postgresql://localhost:5432/postgres";

    private final  static  String userName = "postgres";

    private  final  static  String password = "716202";


    public static Connection getConnection(){
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url, userName, password);

            System.out.println("Connected!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return connection;
    }

}
