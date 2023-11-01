package clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private Socket clientSocket;
   

    private ObjectOutputStream writer;
    private ObjectInputStream reader;

    //שולח בקשה לשרת ומקבל תשובה
    public Client() {
        try {
           //מייצג את החיבור לשרת;
           //מכיל את מספר הפורט בו השרת מאזין לבקשות מלקוחות-PORT
           //מכיל את כתובת הIP של השרת-IP_ADDRESS
            clientSocket = new Socket(Server.IP_ADDRESS, Server.PORT);
            //הנתונים שכותבים לשרת
            writer = new ObjectOutputStream(clientSocket.getOutputStream());
           
            //הנתונים שמקבלים מהשרת
            reader = new ObjectInputStream(clientSocket.getInputStream());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

	
    //לוקחת מספר שלם כפרמטר ומחזירה מחרוזת
    public String sendNumberAndGetString(int num) {
        try {
          
            //כותבת מספר
            writer.writeObject(num);
            //עושה המרה למחרוזת ומקבלת את זה מהשרת
            String response = (String) reader.readObject();
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "שגיאה";
    }

}
