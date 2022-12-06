package Server;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.regex.Pattern;


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
        String CheckPortInput="^((6553[0-5])|(655[0-2][0-9])|(65[0-4][0-9]{2})|(6[0-4][0-9]{3})|([1-5][0-9]{4})|([0-5]{0,5})|([0-9]{1,4}))$";
        bu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()==bu){
                    if (jTextField.getText().isEmpty()){
                        JOptionPane.showMessageDialog(serverGui,"Please enter your port");
                    } else if (!Pattern.matches(CheckPortInput,jTextField.getText())) {
                        JOptionPane.showMessageDialog(serverGui,"The port is not true, please enter again !");
                    }
                    else {
                        serverGui.setVisible(false);
                        JOptionPane.showMessageDialog(serverGui,"Connect success","Thông báo kết nối",1);


                        connect.connectServer(Integer.parseInt(jTextField.getText()));
                    }



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
