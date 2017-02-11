/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserverproject;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;



/**
 *
 * @author Anton
 */
public class RemoteEchoClient {
    
    private static final String BYE_COMMAND = "$BYE$";
    
    private final String hostIP;
    private final int hostPort;
    
    
    public RemoteEchoClient(String hostIP, int hostPort){
        
        this.hostIP = hostIP;
        this.hostPort = hostPort;
    }
    
    public void perFormConversation(){  
        setUpConnection();
    }
    
    private void setUpConnection(){
        
        try {
            
            Socket socket = new Socket(hostIP, hostPort);
            openConnection(socket);
            closeConnection(socket);
        } 
        catch (UnknownHostException ex) {
            System.out.println(ex);
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    private void openConnection(Socket socket)
    {
        try {
            
            BufferedReader socketReader = new BufferedReader( new 
                    InputStreamReader(socket.getInputStream()));
            
            PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
            
            handleConnection(socketReader, socketWriter);
        } 
        catch (IOException ex) {
            
            System.out.println(ex);
        }
    }
    
    private void handleConnection(BufferedReader socketReader, PrintWriter socketWriter)
    {
        Scanner stdInReader;
        
        try {
            stdInReader = new Scanner(System.in);
            
            String nextLine = null;
            String nextAnswer = null;
            
            showTips();
            
            while((nextLine = stdInReader.nextLine()) != null &&
                   nextLine.trim().compareTo(BYE_COMMAND) != 0){
                
                System.out.println("Client> " + nextLine);
                socketWriter.println(nextLine);

                nextAnswer = socketReader.readLine();
                System.out.println("Server> " + nextAnswer);
                
            }         
            stdInReader.close();
        } 
        catch (FileNotFoundException ex){         
            System.out.println(ex);
        }
        catch(IOException ex){
            System.out.println(ex);
        }
    }

    private void showTips() {
        
            System.out.println("Enter the messages here!");
            System.out.println("To cancel the connection to the server(stop the client) type $BYE$");
            System.out.println("To STOP the server type admin #admin123# ");
            System.out.println("In order to experience the multithreading functionality , " +
                                "please run both client stations!");
    }
    
    private void closeConnection(Socket socket)
    {
        try {
            socket.close();
        } 
        catch (IOException ex) {
            System.out.println(ex);
        }
    }
}
