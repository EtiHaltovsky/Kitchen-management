/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitchenmenneger;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author comp2
 */
public class StepInRecipe {

    private List<CookWare> resource;
    private int Time;
    private String Description;

    public StepInRecipe() {
    }

    public StepInRecipe(int Time, String Description, List<CookWare> cookwares) {
        resource = cookwares;
        this.Time = Time;
        this.Description = Description;
    }

    /**
     * @return the resource
     */
    public List<CookWare> getSteps() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setSteps(List<CookWare> resource) {
        this.resource = resource;
    }

    /**
     * @return the Time
     */
    public int getTime() {
        return Time;
    }

    /**
     * @param Time the Time to set
     */
    public void setTime(int Time) {
        this.Time = Time;
    }

    /**
     * @return the Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * @param Description the Description to set
     */
    public void setDescription(String Description) {
        this.Description = Description;
    }

    /* פונקציית עשייה בפוע לשמפעילה על כל המשאבים עשייה  */
    public void Do(String recipeName) throws Exception {

        boolean allLocked = false;//האם כל המשאבים לשלב תפוסים
        int i;

        for (i = 0; i < resource.size(); i++) {//עובר על כל המשאבים לאותו שלב
            DefaultTableModel model = (DefaultTableModel) resource.get(i).getWaitingsTable().getModel();
            //עבור כל שלב מוסיף לטבלת הממתינים את המתכון לטבלה של המשאב הרצוי
            model.addRow(new Object[]{recipeName});

        }

        //if(allLocked==false)
        //כל המשאבים פנויים ויכול לתפוס
        while (!allLocked) {
            for (i = 0; i < resource.size(); i++) {
                //מנסה לתפוס את המשאב בתוך 3 שניות-תתפוס אותו ואם לא תשחרר ותנסה לתפוס שוב
                if (!resource.get(i).getSemaphore().tryAcquire(3, TimeUnit.SECONDS)) {//מנסה לתפוס את המשאב
                    for (int j = 0; j < i; j++) {
                        //עובר על כל המשאבים שתפס לפני כן ומשחרר אותם
                        resource.get(i).getSemaphore().release();//אם לא הצלחת לתפוס למשך 3 שניות תשחרר את כל השאר
                    }
                    //ברגע שתפוס-תנוח לפני שאתה מנסה שוב לתפוס
                    Thread.sleep(5000);
                    break;
                }
            }
            if (i == resource.size()) {//הכל תפוס
                //נועל את המשאבים של אותו שלב שתפס
                allLocked = true;
            }
        }

        for (i = 0; i < resource.size(); i++) {
            DefaultTableModel model = (DefaultTableModel) resource.get(i).getWaitingsTable().getModel();//מוחק אותו מהממתינים 
            //מחיקה עצמה
            for (int j = 0; j < model.getRowCount(); j++) {
                if (model.getValueAt(j, 0).equals(recipeName)) {
                    model.removeRow(j);
                }
            }

        }

        //עבור כל משאב מוסיף לרשימת משתמשים(אורז1 מביאים את המים לרתיחה)
        for (CookWare item : resource) {
            item.Use(Time, Description, recipeName);//עבור כל משאב מבצע את השלב שלו בפונקציה
        }
        //שיוכל להשתמש במשאב
        Thread.sleep(Time * 1000);

        for (CookWare item : resource) {
            item.getSemaphore().release();//משחרר את אותו המשאב

        }

        //מחיקה של המתכון מטבלת משתמשים של אותו משאב
        for (i = 0; i < resource.size(); i++) {
            DefaultTableModel model2 = (DefaultTableModel) resource.get(i).getUse().getModel();
            for (int j = 0; j < model2.getRowCount(); j++) {
                if (model2.getValueAt(j, 0).toString().contains(recipeName)) {
                    model2.removeRow(j);
                    break;
                }
            }

        }

    }

}
