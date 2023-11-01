/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitchenmenneger;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

/**
 *
 * @author comp2
 */
public class RecipeThread {

    public static int count=0;
    private final int recipeCode;
    private Recipe recipe;

 

    public RecipeThread( Recipe recipe/*, JTable GraduationList*/) {
        synchronized(RecipeThread.class)
        {
            count++;
            this.recipeCode = count;
        }
        this.recipe = recipe;
       // this.GraduationList = GraduationList;
    }

    
    /**
     * @return the recipeCode
     */
    public int getRecipeCode() {
        return recipeCode;
    }

 

    /**
     * @return the recipe
     */
    public Recipe getRecipe() {
        return recipe;
    }

    /**
     * @param recipe the recipe to set
     */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

  
    public void startRecipe() {
        try {
            //מריץ את הפעלת המתכון-כל שלב במתכון מבצע-שם בטבלה הנכונה של כל כלי
            recipe.DoAll(recipeCode);
        } catch (Exception ex) {
            Logger.getLogger(RecipeThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
