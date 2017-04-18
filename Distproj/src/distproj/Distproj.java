/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distproj;

import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.security.auth.login.LoginException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.TALogic;

/**
 *
 * @author Andreas
 */
public class Distproj {
 public static void main(String[] args) throws Exception{
      Connection conn = null;
        Statement readStatement = null;
        ResultSet resultSet = null;
        String results = "";
            Class.forName("com.mysql.jdbc.Driver");
            String dbName = "TAtest";
            String userName = "master";
            String password = "mastertest";
            String hostname = "tatest.cmfa300zeve9.eu-central-1.rds.amazonaws.com";
            String port = "3306";
            String jdbcUrl = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;
            System.out.println("Getting remote connection "+jdbcUrl);
            DriverManager.getConnection(jdbcUrl);
            System.out.println("Remote connection successful");
            System.out.println("test");
            conn = DriverManager.getConnection(jdbcUrl);
        String statement = "SELECT * FROM Roles;";
        System.out.println("1");
        readStatement = conn.createStatement();
        System.out.println("2");
        resultSet = readStatement.executeQuery(statement);
        System.out.println("3");
        resultSet.first();
        System.out.println("4");
        results = resultSet.getString("roleName");
        System.out.println("5");
        resultSet.next();
        System.out.println("6");
        results += ", " + resultSet.getString("roleName");
        System.out.println("hej:" + results);

    }
    public static void mainx(String[] args) {
        Connection conn = null;
        Statement readStatement = null;
        ResultSet resultSet = null;
        String results = "";
        try {
            
            // localhost bruges til test på egen computer og ubuntu4 bruges til når du tester
            // med anden server.
            URL url = new URL("http://ubuntu4.javabog.dk:9998/teacherassistant?wsdl");
            //URL url = new URL("http://localhost:9978/galgeleg?wsdl");
            QName qname = new QName("http://server/", "TALogicService");
            Service service = Service.create(url, qname);
            MainInterface taService = service.getPort(MainInterface.class);
            
            String uuid = "";
            String firstname = "", lastname = "";
            String test = "";

            while(uuid.isEmpty()){
                System.out.println("Indtast studienummer");
                String username = new Scanner(System.in).nextLine();
                
                System.out.println("Indtast password");
                String password = new Scanner(System.in).nextLine();
                
                try {
                    ArrayList<String> userinfo = taService.login(username, password);
                    if (!userinfo.isEmpty()){
                        firstname = userinfo.get(0);
                        lastname = userinfo.get(1);
                        uuid = userinfo.get(3);
                        test = userinfo.get(2);
                        
                    }
                } catch (LoginException ex) {
//                    System.out.println(ex.getMessage());
                    System.out.println("Der er ingen matchende bruger med de indtastede oplysninger. Prøv igen!");
                }
                
                try{
        conn = MainInterface.getRemoteConnection();
        System.out.println("test");
        String statement = "SELECT * FROM Roles;";
        System.out.println("1");
        readStatement = conn.createStatement();
        System.out.println("2");
        resultSet = readStatement.executeQuery(statement);
        System.out.println("3");
        resultSet.first();
        System.out.println("4");
        results = resultSet.getString("roleName");
        System.out.println("5");
        resultSet.next();
        System.out.println("6");
        results += ", " + resultSet.getString("roleName");
        System.out.println("hej:" + results);
        } catch (Exception e){
                
                }
            }
            
            System.out.println("Du er logget ind som " + firstname + " " + lastname);
            System.out.println("din rolle er: "+test);
         
        }
        catch (Exception ex) {
            System.err.println("Kunne ikke forbinde til serveren");
            Logger.getLogger(Distproj.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
        
    }
}
