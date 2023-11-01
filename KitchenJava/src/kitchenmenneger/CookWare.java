/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kitchenmenneger;

import java.util.concurrent.Semaphore;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author comp2
 */
public class CookWare {

    private String name;

    private JTable use;

    private JTable WaitingsTable;

    private int amount;

    private Semaphore semaphore;

    public CookWare() {
    }

    public CookWare(String name, int amount, JTable use, JTable WaitingsTable) {
        this.name = name;
        this.amount = amount;
        this.use = use;
        this.WaitingsTable = WaitingsTable;
        semaphore = new Semaphore(amount, true);
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public JTable getUse() {
        return use;
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
     * @return the WaitingsTable
     */
    public JTable getWaitingsTable() {
        return WaitingsTable;
    }

    /**
     * @param WaitingsTable the WaitingsTable to set
     */
    public void setWaitingsTable(JTable WaitingsTable) {
        this.WaitingsTable = WaitingsTable;
    }

    /*
     פונקציית  שימוש במשאב - מקבלת זמן ותיאור ופועלת בהתאם
     */
    public void Use(int time, String Description, String recipeName) throws Exception {
        //מוסיף את המתכון לטבלה הספציפית של אותו משאב
        DefaultTableModel model = (DefaultTableModel) use.getModel();
        model.addRow(new Object[]{recipeName + " " + Description});//מוסיף לרשימת משתמשים
    }

}
