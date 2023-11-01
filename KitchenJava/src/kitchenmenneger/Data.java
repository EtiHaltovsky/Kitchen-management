/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitchenmenneger;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author comp2
 */
public class Data {

    private CookWare[] arrCookware;
    private Recipe[] arrRecipe;

    public Data(Kitchen k) {
        arrCookware = new CookWare[5];
        arrRecipe = new Recipe[3];

        //אתחול משאבים
        arrCookware[0] = new CookWare("נפה", 1, k.getNapaUseTable(), k.getNapaTable());
        arrCookware[1] = new CookWare("מיקסר", 2, k.getMixerUseTable(), k.getMixerTable());
        arrCookware[2] = new CookWare("סיר", 2, k.getPotUseTable(), k.getPotTable());
        arrCookware[3] = new CookWare("כירה", 2, k.getGazUseTable(), k.getGazTable());
        arrCookware[4] = new CookWare("תנור", 1, k.getOvenUseTable(), k.getOvenTable1());

        //אתחול מתכונים
        arrRecipe[0] = new Recipe("אורז");
        arrRecipe[1] = new Recipe("עוגה כושית");
        arrRecipe[2] = new Recipe("עוגיות");

        //אתחול צעדים בכל מתכון         
        //אורז      
        ArrayList<StepInRecipe> listStepsRice = new ArrayList<>();
        //כל שלב יכול להיות כמה כלים שמשתמשים 
        //step1List1 רשימה של הכלים שמשתמשים באותו שלב
        ArrayList<CookWare> step1List1 = new ArrayList<CookWare>();
        step1List1.add(arrCookware[2]);
        step1List1.add(arrCookware[3]);
        //בכל שלב: כמה זמן, מה הפעולה, במה משתמשים
        listStepsRice.add(new StepInRecipe(5, "מביאים את המים לרתיחה", step1List1));

        ArrayList<CookWare> step1List2 = new ArrayList<CookWare>();
        step1List2.add(arrCookware[2]);
        step1List2.add(arrCookware[3]);
        listStepsRice.add(new StepInRecipe(2, "מוסיפים אורז  שמן ומלח ומערבבים", step1List2));

        ArrayList<CookWare> step1List3 = new ArrayList<CookWare>();
        step1List3.add(arrCookware[2]);
        step1List3.add(arrCookware[3]);
        listStepsRice.add(new StepInRecipe(15, "מבשלים את האורז", step1List3));

        arrRecipe[0].setSteps(listStepsRice);

        //עוגה כושית      
        ArrayList<StepInRecipe> listStepsCake = new ArrayList<>();
        ArrayList<CookWare> step1ListC1 = new ArrayList<CookWare>();
        step1ListC1.add(arrCookware[0]);
        listStepsCake.add(new StepInRecipe(5, "מנפים קמח", step1ListC1));

        ArrayList<CookWare> step1ListC2 = new ArrayList<CookWare>();
        step1ListC2.add(arrCookware[1]);
        listStepsCake.add(new StepInRecipe(5, "מקציפים ביצים וסוכר", step1ListC2));

        ArrayList<CookWare> step1ListC3 = new ArrayList<CookWare>();
        step1ListC3.add(arrCookware[1]);
        listStepsCake.add(new StepInRecipe(2, "מוסיפים את כל שאר החומרים", step1ListC3));

        ArrayList<CookWare> step1ListC4 = new ArrayList<CookWare>();
        step1ListC4.add(arrCookware[4]);
        listStepsCake.add(new StepInRecipe(20, "מכניסים לתנור מחומם מראש", step1ListC4));

        ArrayList<CookWare> step1ListC5 = new ArrayList<CookWare>();
        step1ListC5.add(arrCookware[2]);
        step1ListC5.add(arrCookware[3]);
        listStepsCake.add(new StepInRecipe(5, "מערבבים חומרים לציפוי ושופכים", step1ListC5));

        ArrayList<CookWare> step1ListC6 = new ArrayList<CookWare>();
        step1ListC6.add(arrCookware[4]);
        listStepsCake.add(new StepInRecipe(2, "מפזרים סוכריות או קוקוס", step1ListC6));

        arrRecipe[1].setSteps(listStepsCake);

//        
        //עוגיות     
        ArrayList<StepInRecipe> listStepsCookie = new ArrayList<>();
        ArrayList<CookWare> step1ListCK5 = new ArrayList<CookWare>();
        step1ListCK5.add(arrCookware[0]);
        listStepsCookie.add(new StepInRecipe(5, "מנפים קמח", step1ListCK5));

        ArrayList<CookWare> step1ListCK1 = new ArrayList<CookWare>();
        step1ListCK1.add(arrCookware[1]);
        listStepsCookie.add(new StepInRecipe(5, "מערבבים חומרים בקערה", step1ListCK1));

        ArrayList<CookWare> step1ListCK2 = new ArrayList<CookWare>();
        listStepsCookie.add(new StepInRecipe(5, "יוצרים עיגולים", step1ListCK2));

        ArrayList<CookWare> step1ListCK3 = new ArrayList<CookWare>();
        step1ListCK3.add(arrCookware[4]);
        listStepsCookie.add(new StepInRecipe(12, "מכניסים לתנור", step1ListCK3));

        ArrayList<CookWare> step1ListCK4 = new ArrayList<CookWare>();
        step1ListCK4.add(arrCookware[2]);
        step1ListCK4.add(arrCookware[3]);
        listStepsCookie.add(new StepInRecipe(2, "ממיסים שוקולד לבן ומזלפים", step1ListCK4));

        arrRecipe[2].setSteps(listStepsCookie);

    }

    //פונקציה שמקבלת שם של מתכון יוצרת תהליך מופע מהמתכון המתאים
    //ןמפעילה אותו ומחזירה אמת כשמתיים
    public boolean CreatARecipe(int num) {
        if (num == 1) {
            //שולח אותו לבצע את המתכון לפי השלבים שמאותחלים
            RecipeThread rt = new RecipeThread(arrRecipe[0]);
            rt.startRecipe();
            return true;
        } else if (num == 2) {
            RecipeThread rt = new RecipeThread(arrRecipe[1]);
            rt.startRecipe();
            return true;
        } else if (num == 3) {
            RecipeThread rt = new RecipeThread(arrRecipe[2]);
            rt.startRecipe();
            return true;
        }
        return false;
    }

}
