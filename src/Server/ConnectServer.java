package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;




public class ConnectServer {
    public ServerSocket server;
    public void connectServer(int port){
        try{
            server=new ServerSocket(port);
            int counter=0;
            System.out.println("Server Started ....");
            while(true){
                counter++;
                Socket serverClient=server.accept();//server accept the client connection request

                System.out.println(" >> " + "Client :" + serverClient.getInetAddress() + " connected !");

                //send  the request to a separate thread

                ServerClientThread sct = new ServerClientThread(serverClient,counter);
                sct.start();



            }
        }catch(Exception e){
            System.out.println(e);
        }
    }



}
