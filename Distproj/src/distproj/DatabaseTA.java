/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distproj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Andreas
 */
public class DatabaseTA {
    
    public Connection getDatabaseTA() throws Exception{
    
        Connection conn = null;

            Class.forName("com.mysql.jdbc.Driver");
            String dbName = "TADB";
            String userName = "master";
            String password = "masterpass";
            String hostname = "tadatabase.cfkvocs79lnv.eu-central-1.rds.amazonaws.com";
            String port = "3306";
            String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
            System.out.println("Getting remote connection "+jdbcUrl);
            DriverManager.getConnection(jdbcUrl);
            System.out.println("Remote connection successful");
            conn = DriverManager.getConnection(jdbcUrl);

        return conn;
        
    }
    
   
}
