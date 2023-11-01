package clientserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import kitchenmenneger.Data;
import kitchenmenneger.Kitchen;

public class ServerThread extends Thread {

    //כל לקוח שפותח את המסך יוצר serverThread לעצמו
    //לכן כמה לקוחות יכולים להריץ את אותו מתכון על אותו שרת
    private static Data data;
    private Socket clientSocket;

    public ServerThread(Socket clientSocket, Kitchen k) {
       
        this.clientSocket = clientSocket;
        data = new Data(k);
    }

    @Override
    public void run() {
        //לקרוא מהמסך של השרת
        ObjectInputStream reader=null ;
        //לכתוב נתונים למסך של השרת
        ObjectOutputStream writer=null;
        try {
            //משתנה שיכול לקרוא מקובץ (יצרנו אותו) clientSocket.getInputStream()-מאיפה תקרא
             reader = new ObjectInputStream(clientSocket.getInputStream());
             writer = new ObjectOutputStream(clientSocket.getOutputStream());

             //מקבל את מספר המתכון
            int num = (int) reader.readObject();

            while (num != -1) {
                //תיצור לי מתכון לפי מספר המתכון(0-2)
                data.CreatARecipe(num);
                writer.writeObject("המתכון הסתיים בהצלחה");
                num = (int) reader.readObject();

            }
        } 
        catch (Exception e) {
           
        }
      try{
      
           if(reader!=null)
            reader.close();    
      }
      catch(Exception e){
      }
       try{
      
           if(writer!=null)
            writer.close();    
      }
      catch(Exception e){
      }
        try{
      
           if(clientSocket!=null)
            clientSocket.close();    
      }
      catch(Exception e){
      }
    }

}
