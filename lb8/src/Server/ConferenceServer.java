package Server;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.ConcurrentModificationException;


public class ConferenceServer{

    private final TextArea textField;
    private static TextField Port;
    private static  JLabel l3;
    private  final  ConfData confData;
    private  static final ConferenceServer instance =new ConferenceServer();
    private Registry registry = null;
    private ConferenceServer() {
        confData = new ConfData();

        JFrame jFrame = new JFrame("Server\uD83D\uDDFF");
        jFrame.setBounds(0,0,640,480);
        jFrame.setVisible(true);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new GridLayout(1,5,10,10));
        {
            JButton starBtn = new JButton("Start");
            JButton stopBtn = new JButton("Stop");
            JButton saveBtn = new JButton("Save");
            JButton loadBtn = new JButton("Load");
            JButton exitBtn = new JButton("Exit");

            btnPanel.add(starBtn);
            btnPanel.add(stopBtn);
            btnPanel.add(saveBtn);
            btnPanel.add(loadBtn);
            btnPanel.add(exitBtn);

            loadBtn.addActionListener(l->{  ConfData dconfData = new ConfData();
                printText("Loading...");
                try {

                    dconfData.loadStruct("Export.xml");

                } catch (ParserConfigurationException | SAXException  e) {
                    throw new RuntimeException(e);

                }  catch ( ConcurrentModificationException e){ printText("Complete.");}

                for (UserRecord userRecord : dconfData.getStruct()) {

                    registerUser(userRecord);

                }



            });

            saveBtn.addActionListener(l->{
                confData.exportToXML("Export.xml");
                printText("Export finished");

            });

            starBtn.addActionListener(l -> {

                printText("Starting...");
                try {
                    if(registry == null) {
                        registry = LocateRegistry.createRegistry(Integer.parseInt(Port.getText()));

                    }
                    String name = "register";

                    Remote stub = new ServiceTaskImpl();

                    registry.rebind(name, stub);
                } catch (RemoteException e) {
                    throw new RuntimeException(e);
                }
                printText("Loading... Port : " +Integer.parseInt(Port.getText()));
            });

            exitBtn.addActionListener(l->{
                JOptionPane.showMessageDialog(jFrame, "The app will close");

                System.exit(0);

            });

            stopBtn.addActionListener(l->{
                try {
                    registry.unbind("register");
                } catch (RemoteException | NotBoundException e) {
                    throw new RuntimeException(e);
                }

            });
        }

        JPanel dataPanel = new JPanel();
        dataPanel.setLayout(new GridLayout(1,6,10,10));

        JLabel textlabel = new JLabel("host: "); textlabel.setFont(new Font("Times New Roman",Font.BOLD,16));
        JLabel ip = new JLabel("localhost");
        JLabel textlabel2 = new JLabel("            port: "); textlabel2.setFont(new Font("Times New Roman",Font.BOLD,16));
        TextField l2 = new TextField("1049");
        l2.setEnabled(true);
        Port = l2;
        JLabel textlabel3 = new JLabel("participating: "); textlabel3.setFont(new Font("Times New Roman",Font.BOLD,16));
         l3 = new JLabel("0");
        {
            dataPanel.add(textlabel);
            dataPanel.add(ip);
            dataPanel.add(textlabel2);
            dataPanel.add(l2);
            dataPanel.add(textlabel3);
            dataPanel.add(l3);
        }

        textField = new TextArea("");
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setBackground(Color.yellow);
        scrollPane.add(textField);

        jFrame.setLayout(new BorderLayout());
        jFrame.add(scrollPane,BorderLayout.CENTER);
        jFrame.add(btnPanel, BorderLayout.SOUTH);
        jFrame.add(dataPanel, BorderLayout.NORTH);

        jFrame.repaint(); jFrame.revalidate();
    }

    private void printText(String txt){

        textField.setText(textField.getText()+"\n"+txt);
    }

    public void registerUser(UserRecord userRecord){
        assert userRecord!=null;
        confData.addUser(userRecord);
        textField.setText(textField.getText() + "\n" + userRecord);
        l3.setText(String.valueOf(1+Integer.parseInt(l3.getText())));
    }

    public static void main(String[] args){






    }


    public static ConferenceServer getInstance()
    {

        return instance;

    }
}


