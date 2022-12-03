package Clientx;





import GUI.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientGUI {
    public int destPort = 1234;
    public String host = "localhost";
    public Socket socket = null;



    public void Connect(String host, int port) throws IOException {
        socket = new Socket(host, destPort);


    }

    public void send(String sms,String command) throws IOException {

        DataOutputStream outStream = null;
        try {

            outStream = new DataOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String clientMessage = "", serverMessage = "",clientMessage1=" ";

            // System.out.println("Enter number :");
            clientMessage = sms;
            clientMessage1 = command;
            outStream.writeUTF(clientMessage);
            outStream.writeUTF(clientMessage1);
            outStream.flush();


            System.out.println(sms + " from send");

        } catch (Exception e) {
            System.out.println(e);
        }


    }

    public String recivece() {
        String sms = null;
        DataInputStream inStream = null;
        try {


            inStream = new DataInputStream(socket.getInputStream());
            sms = inStream.readUTF();
            System.out.println(sms);

        } catch (Exception evt) {
            System.out.println(evt);
        }
        return sms;
    }
//
//    public ClientGUI() throws IOException, InterruptedException {
////        JFrame panel=new JFrame();
////        panel.setLayout(new FlowLayout());
////        JLabel label=new JLabel("Server Host :");
////        JTextField txtAddress = new JTextField(13);
////        JLabel label1=new JLabel("Port :");
////        JTextField txtPort=new JTextField("1234");
////        JButton b =new JButton("Conect");
////        b.addActionListener(new ActionListener() {
////            @Override
////            public void actionPerformed(ActionEvent e) {
////                if (e.getSource()==b){
////                    panel.setVisible(false);
////                    try {
////                        Connect(txtAddress.getText(),Integer.parseInt(txtPort.getText()));
////                        panel.setVisible(false);
////
////                        gui.setVisible(true);
////                    } catch (IOException ex) {
////                        throw new RuntimeException(ex);
////                    }
////
////
////                }
////            }
////        });
////
////        panel.add(label);
////        panel.add(txtAddress);
////        // panel.add(label1);
////        // panel.add(txtPort);
////        panel.add(b);
////        panel.setSize(320,100);
////        panel.setVisible(true);
////        panel.setLocationRelativeTo(null);
////        panel.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
////    }
//
//
//
//}


}


