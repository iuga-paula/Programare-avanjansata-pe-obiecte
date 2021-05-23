package Bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Configurare_Bd {

        private static final String DATABSE_URL = "jdbc:mysql://localhost:3306/foodapp";
        private static final String USER = "root";
        private static final String PASSWORD = "paula";

        private static Connection databaseConnection;

        private Configurare_Bd() {
        }

        public static Connection getDatabaseConnection()    {
            try {
                if(databaseConnection == null || databaseConnection.isClosed()) {
                    databaseConnection = DriverManager.getConnection(DATABSE_URL, USER, PASSWORD);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return databaseConnection;
        }

        public static void closedatabaseConnection()    {
            try {
                if(databaseConnection != null && !databaseConnection.isClosed()) {
                    databaseConnection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

}

