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
public class Recipe {

    private String name;
    private List<StepInRecipe> steps;

    public Recipe(String name) {
        this.name = name;
        steps = new ArrayList<>();
    }

    public Recipe() {
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the steps
     */
    public List<StepInRecipe> getSteps() {
        return steps;
    }

    /**
     * @param steps the steps to set
     */
    public void setSteps(List<StepInRecipe> steps) {
        this.steps = steps;
    }

    /* פונקציית עשייה בפועל שעוברת על כל הרשימה של השלבים ועושה לכל אחד דו  */
    public void DoAll(int id) throws Exception {
        //עובר ע כל השלבים במתכון ועבור כל שלב עושה do
        for (StepInRecipe item : steps) {
            //אורז1 מביאים לרתיחה
            item.Do(name + " " + id);
        }

    }

}
