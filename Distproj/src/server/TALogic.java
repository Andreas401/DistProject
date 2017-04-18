/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import brugerautorisation.transport.soap.Brugeradmin;
import distproj.MainInterface;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.security.auth.login.LoginException;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.util.HashMap;

@WebService(endpointInterface = "distproj.MainInterface")
public class TALogic implements MainInterface{
    private String uuid;
    Object test1;
    public TALogic() {
       
    }

   
    
    public void TALogic(){}
    
     public ArrayList<String> login(String username, String password) throws LoginException{
        try {
            URL url = new URL("http://javabog.dk:9901/brugeradmin?wsdl");
            QName qname = new QName("http://soap.transport.brugerautorisation/", "BrugeradminImplService");
            Service service = Service.create(url, qname);
            Brugeradmin userAuth = service.getPort(Brugeradmin.class);
            
            ArrayList<String> userinfo = new ArrayList<String>();
            try {
                if(!userAuth.hentBruger(username, password).toString().isEmpty()){
                    userAuth.setEkstraFelt(username, password, "rolle", "student");
                    userinfo.add(userAuth.hentBruger(username, password).fornavn);
                    userinfo.add(userAuth.hentBruger(username, password).efternavn);
                    uuid = UUID.randomUUID().toString();
                    userinfo.add(uuid);
                    userAuth.setEkstraFelt(username, password, "roller", setRole(username));
                    userinfo.add((String) userAuth.getEkstraFelt(username, password, "roller"));
                    
                    
                }
                
            } catch (Exception ex) {
//                Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, ex);
                throw new LoginException("Wrong username or password");
            }
            
            return userinfo;
        } catch (MalformedURLException ex) {
//            Logger.getLogger(GameLogic.class.getName()).log(Level.SEVERE, null, ex);
            throw new LoginException("No connection to login server");
        }
        
        
    }
     

    public String setRole(String username){
        String role;
        if(username.contains("s1")){
            role = "student";
            
        } else {
            role = "TA";
        }
        return role;
    }
    
    private boolean stillLoggedIn (String uuid) throws LoginException {
        try {
            if (this.uuid.equals(uuid))
                return true;
            else 
                throw new LoginException("Not logged in");
        } catch (Exception ex){
            throw new LoginException("Not logged in");
        }
        
    }

    
}
