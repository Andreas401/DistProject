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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andreas
 */
public class Distproj {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            // localhost bruges til test på egen computer og ubuntu4 bruges til når du tester
            // med anden server.
            //URL url = new URL("http://ubuntu4.javabog.dk:9978/teacherassistant?wsdl");
            URL url = new URL("http://localhost:9978/teacherassistant?wsdl");
            QName qname = new QName("http://server/", "TALogicService");
            Service service = Service.create(url, qname);
            MainInterface gameService = service.getPort(MainInterface.class);
            
            String uuid = "";
            String firstname = "", lastname = "";

            while(uuid.isEmpty()){
                System.out.println("Indtast studienummer:");
                String username = new Scanner(System.in).nextLine();
                
                System.out.println("Indtast password");
                String password = new Scanner(System.in).nextLine();
                
                try {
                    ArrayList<String> userinfo = gameService.login(username, password);
                    if (!userinfo.isEmpty()){
                        firstname = userinfo.get(0);
                        lastname = userinfo.get(1);
                        uuid = userinfo.get(2);
                    }
                } catch (LoginException ex) {
//                    System.out.println(ex.getMessage());
                    System.out.println("Der er ingen matchende bruger med de indtastede oplysninger. Prøv igen!");
                }
            }
            
            System.out.println("Du er logget ind som " + firstname + " " + lastname);
        }
        catch (Exception ex) {
            System.err.println("Kunne ikke forbinde til serveren");
            Logger.getLogger(Distproj.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
    }
}
