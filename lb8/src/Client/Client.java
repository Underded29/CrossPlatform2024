package Client;

import Server.ServiceTask;
import Server.UserRecord;

import javax.swing.*;
import java.awt.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client extends JFrame {
    private static Registry registry = null;
    public Client(){

        setBounds(0,0,640,480);
        setResizable(false);
        setLayout(null);

        JPanel Jpanel = new JPanel();
        Jpanel.setBounds(20,10,450,20);
        Jpanel.setLayout(new GridLayout(1,5));

        JLabel textlabel = new JLabel("             host: "); textlabel.setFont(new Font("Times New Roman",Font.BOLD,16));
        JLabel textlabel2 = new JLabel("            port: "); textlabel2.setFont(new Font("Times New Roman",Font.BOLD,16));
        TextField l2 = new TextField("1049");

        TextField textField = new TextField("localhost");
        Jpanel.add(textlabel);
        Jpanel.add(textField);
        Jpanel.add(new JPanel()); Jpanel.add(textlabel2);
        Jpanel.add(l2);

        setVisible(true);

        JPanel buttonPanel = new JPanel();

        Button register= new Button("Register");
        Button clear= new Button("Clear");
        Button getInfo = new Button("getInfo");
        Button finish= new Button("Finish");

        buttonPanel.setLayout(new GridLayout(1,4,20,20));
        buttonPanel.add(register);
        buttonPanel.add(clear);
        buttonPanel.add(getInfo);
        buttonPanel.add(finish);

        register.addActionListener(l->{
            try {
                Registry registry = LocateRegistry.getRegistry(textField.getText(), 1049);
                // Отримання об'єкта сервісу з реєстру
                ServiceTask service = (ServiceTask) registry.lookup("register");
            //    service.executeTask( new UserRecord("lox","pidorov", "dayn", "pidoras at ukr.net","Circus"));
            } catch (RemoteException | NotBoundException e) {
                throw new RuntimeException(e);
            }


        });
        JPanel mainData = new JPanel();
        mainData.setLayout(new GridLayout(5,2,0,0));
        mainData.setBounds(20,50,600,200);
        JLabel name = new JLabel("name"); name.setFont(new Font("Times New Roman",Font.BOLD,16));
        TextField tf1 = new TextField();



        JLabel surn = new JLabel("surname"); name.setFont(new Font("Times New Roman",Font.BOLD,16));
        TextField tf2 = new TextField();

        JLabel organization = new JLabel("Company"); name.setFont(new Font("Times New Roman",Font.BOLD,16));
        TextField tf3 = new TextField();

        JLabel email = new JLabel("Email"); name.setFont(new Font("Times New Roman",Font.BOLD,16));
        TextField tf4 = new TextField();

        mainData.add(name); mainData.add(tf1);
        mainData.add(surn); mainData.add(tf2);
        mainData.add(organization); mainData.add(tf3);

        mainData.add(email); mainData.add(tf4);



        clear.addActionListener(l->{
            tf1.setText("");
            tf2.setText("");
            tf3.setText("");
            tf4.setText("");
        });
        register.addActionListener(l->{


            ServiceTask service;
            try {
                service = (ServiceTask) registry.lookup("register");
            } catch (RemoteException | NotBoundException e) {
                throw new RuntimeException(e);
            }
            try {
                service.executeTask( new UserRecord(tf1.getText(),
                        tf2.getText(), "user", tf4.getText(),tf3.getText()));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }


        });


        buttonPanel.setBounds(70,400,500,30);
        add(buttonPanel);
        add(Jpanel);
        add(mainData);
    }

    public static void main(String[] args){

        Client cl = new Client();
        try {
            registry = LocateRegistry.getRegistry("localhost", 1049);
        } catch (RemoteException e) {
        throw new RuntimeException(e);
    }



    }
}
