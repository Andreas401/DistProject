/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distproj;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
@WebService
public interface MainInterface {
     @WebMethod ArrayList<String> login(String username, String password) throws LoginException;
     @WebMethod  public static Connection getRemoteConnection(){
        if(System.getenv("tatest.cmfa300zeve9.eu-central-1.rds.amazonaws.com")!= null){
            try{
            Class.forName("org.mysql.Driver");
            String dbName = System.getenv("TAtester");
            String userName = System.getenv("master");
            String password = System.getenv("mastertest");
            String hostname = System.getenv("tatest.cmfa300zeve9.eu-central-1.rds.amazonaws.com");
            String port = System.getenv("3306");
            String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "=user=" + userName + "&password=" + password;
            System.out.println("Getting remote connection");
            Connection con = DriverManager.getConnection(jdbcUrl);
            System.out.println("Remote connection successful");
            return con;
        }
        catch (ClassNotFoundException e) {System.out.println(e);}
        catch (SQLException e) {System.out.println(e);}
        }
        return null;
    }

     
     
}
