/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distproj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Andreas
 */
public class DatabaseUse{
    
     Connection conn;
    
     public String getRoleNames() throws Exception{
        String statement = "";
        String result = "";
        Statement readstatement = null;
        ResultSet resultSet = null;
        
        statement = "SELECT * FROM roles;";
        readstatement = conn.createStatement();
        resultSet = readstatement.executeQuery(statement);
        resultSet.first();
        result = resultSet.getString("roleName");
        resultSet.next();
        result += ", " + resultSet.getString("roleName");
        resultSet.next();
        result += ", " + resultSet.getString("roleName");

 
        return result;
    }
    
    public void addToQueue(String name) throws Exception {
        
        Statement st;
        st = conn.createStatement();
        
        st.executeUpdate("INSERT INTO roles (roleName) " + "VALUES ('" + name + "')");
        
    }

    public DatabaseUse() throws Exception {
        conn = new DatabaseTA().getDatabaseTA();
    }
    
}
