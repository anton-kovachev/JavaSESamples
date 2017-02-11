/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserverproject;

/**
 *
 * @author Anton
 */
public class ServerStation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RemoteEchoServer echoServer = new RemoteEchoServer(3000);
        echoServer.acceptConnections();
    }
}
