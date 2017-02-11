/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package echoserverproject;

/**
 *
 * @author Anton
 */
public class ClientStation1 {

    public static void main(String[] args) {

        RemoteEchoClient echoClient = new RemoteEchoClient("localhost", 3000);
        echoClient.perFormConversation();        
    }
}
