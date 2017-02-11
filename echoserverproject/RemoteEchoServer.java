/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserverproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Anton
 */
public class RemoteEchoServer {

    private static final String ADMIN_USER_NAME = "admin";
    //this is the MD5 hash value of the password for stoping the server 
    private static final String STOP_PASSWORD = "615d72266ec076811a5b698c3f84fcc4";
    
    private int listenPort;
    private volatile boolean  serverRunning;
    
    public RemoteEchoServer(int listenPort){
        
        this.listenPort  = listenPort;
        this.serverRunning = false;
    }
    
    public void acceptConnections() {
        
        try
        {
            ServerSocket serverSocket = new ServerSocket(listenPort);
            Socket incomingConnection = null; 
            
            serverRunning = true;
            
            System.out.println("Server Running!");
            while(serverRunning){
               
                incomingConnection = serverSocket.accept();
                ClientConnection clientConnection = new ClientConnection(incomingConnection);
                clientConnection.start();
            }
        }
        catch(Exception ex){
            System.out.println(ex);
        }      
    }

    private class ClientConnection extends Thread{
        
        private final Socket incomingConnection;
        
        public ClientConnection(Socket incomingConnection){
            this.incomingConnection = incomingConnection;
        }
        
        @Override
        public void run(){
            try {
                openConnection();
            }
            catch (StopServerException ex) {
                System.out.println("SERVER STOPPED");
                System.exit(0);
            }
            finally{
                closeConnection();
            }
        }
        
        private void openConnection() throws StopServerException{
            
            BufferedReader  streamReader = null;
            PrintWriter streamWriter = null;
            
            try{
                            
                streamReader = new BufferedReader(new 
                        InputStreamReader(incomingConnection.getInputStream()));
                streamWriter = new PrintWriter(incomingConnection.getOutputStream(), true);
                
                handleConncetion(streamReader, streamWriter);
            }
            catch(IOException ex){
                 
                System.out.println(ex);
            }
              
        }
        
        private void handleConncetion(BufferedReader streamReader, PrintWriter streamWriter) throws StopServerException{

            String nextLine = null;
            String nextAnswer = null;
            
            try {
                
                while((nextLine = streamReader.readLine()) != null){
                    
                    stopServer(nextLine.trim());
                    System.out.println("Client> " + nextLine);
                    
                    nextAnswer = nextLine + "  OK!";
                    streamWriter.println(nextAnswer);
                    streamWriter.flush();
                }
            } 
            catch (IOException ex) {              
                System.out.println(ex);
            }
        }
        
        private void closeConnection(){
            
            try {               
                incomingConnection.close();
            } 
            catch (IOException ex) {
                
                System.out.println(ex);
            }
        }
    }
    
     private synchronized  void stopServer(String nextLine) throws StopServerException{
         
         if(nextLine.startsWith(ADMIN_USER_NAME)){
             
             String password = nextLine.split(" ")[1];
             String hashedPassword = hash(password);
             
             if(hashedPassword.compareTo(STOP_PASSWORD) == 0){
                   throw new StopServerException();
             }
         }
      }
        
     private String hash(String password){
         
            try{
                MessageDigest passwordDigest = MessageDigest.getInstance("MD5");
                passwordDigest.reset();
                passwordDigest.update(password.getBytes(), 0, password.length());
                byte [] digest = passwordDigest.digest();
                BigInteger bigInt = new BigInteger(1,digest);
                return bigInt.toString(16);
            } 
            catch (NoSuchAlgorithmException ex) {
                System.out.println(ex);
            }           
            return "";
        }
}
