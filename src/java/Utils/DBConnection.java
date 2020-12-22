/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection 
{
    //&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
    private static final String URL = "jdbc:mysql://localhost:3306/online_banking_service_database?useSSL=false";
    
    private static final String DRIVER = "com.mysql.jdbc.Driver";

    private static final String USERNAME = "root";

    private static final String PASSWORD = "123456";

    private static Connection connection = null;

    public static Connection openConnection() 
    {
        if(connection != null)
        {
            return connection;
        } 
        else 
        {
            try 
            {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
            catch(ClassNotFoundException _e)
            {
                _e.printStackTrace();
            }
            catch(SQLException _e)
            {
                _e.printStackTrace();
            }
            return connection;
        }

    }
}
