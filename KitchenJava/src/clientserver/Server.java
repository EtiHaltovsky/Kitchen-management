package clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import kitchenmenneger.Kitchen;
//import kitchenmenneger.KitchenMenneger;

public class Server {

     //מכיל את כתובת הIP של השרת-IP_ADDRESS
    public static final String IP_ADDRESS = "localhost";
      //מכיל את מספר הפורט בו השרת מאזין לבקשות מלקוחות-PORT
    public static final int PORT = 1000;
//מייצג סוקט המאזין לבקשות מלקוחות
    private ServerSocket serverSocket;
    //מייצג את מחלקת המטבח
    private Kitchen kitchen;

    public Server(Kitchen k) {
        kitchen = k;
        try {
            //להריץ שרת בפורט 1000
            serverSocket = new ServerSocket(PORT);
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void runServer() {
        //תמיד מקבל בקשות חדשות ושולח אותם לביצוע
        while (true)
        {
            try {
                //מאזין לבקשות של קליינטים כל עוד אין בקשות הוא יחכה בשורה הזאת וברגע שתבוא בקשה ילך ליצור תהליכון חדש
                Socket clientSocket = serverSocket.accept();
                
                //clientSocket מכיל את הנתונים של הבקשה  שבאה לשרת(לקוח)
                //
                ServerThread thread = new ServerThread(clientSocket, kitchen);
                thread.start();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public Server() {

    }

    public static void main(String[] args) {
 
        //יוצר מסך תצוגה
        Kitchen jframe = new Kitchen();
        jframe.setVisible(true);
        Server s = new Server(jframe);
        s.runServer();
    }

}
