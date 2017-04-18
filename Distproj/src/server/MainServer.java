package server;


import distproj.MainInterface;
import brugerautorisation.transport.soap.Brugeradmin;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.util.Scanner;
import javax.xml.ws.Endpoint;

public class MainServer {
    public static void main(String[] args) throws MalformedURLException {
        
        System.out.println("Start server!");
        MainInterface ta = new TALogic();
        
        
        Endpoint.publish("http://[::]:9998/teacherassistant", ta);
       System.out.println("Bottom of the server code!");
}

}