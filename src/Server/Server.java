package Server;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;


public class Server {
    public DatagramSocket datagramSocket;
    public DatagramPacket datagramPacketRecevice, datagramPacketSend;
    private  int buffSize=512;
    private int port;
ConnectServer connect=new ConnectServer();
    public Server(){
        JFrame serverGui=new JFrame("Server");

        JTextField jTextField=new JTextField("1234",20);
        JButton bu=new JButton("Listen");
        bu.setSize(100,50);
        bu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==bu){
                    serverGui.setVisible(false);
                    JOptionPane.showMessageDialog(serverGui,"Connect success","Thông báo kết nối",1);


                    connect.connectServer(Integer.parseInt(jTextField.getText()));


                }
            }
        });

        serverGui.add(jTextField);
        serverGui.add(bu);
        serverGui.setSize(290,100);
        serverGui.setLayout(new FlowLayout());
        serverGui.setLocationRelativeTo(null);
        serverGui.setVisible(true);
        serverGui.setResizable(false);
        serverGui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static  void main(String args[]) throws IOException {

new  Server();


    }
}
