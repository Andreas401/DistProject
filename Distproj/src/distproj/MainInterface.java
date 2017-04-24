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
     @WebMethod public String getRoleNames() throws Exception;
     @WebMethod public void addToQueue(String name) throws Exception;

     
     
}
