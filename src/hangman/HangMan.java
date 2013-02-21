/*
 * File:                HangMan.java
 * Author:              Andre Smink
 * Date:                November 2012
 * Operating System:    Windows 7 64-bit
 * Description:         Sets the Look and Feel
 *                      Tests for correct scren resolution
 *                      Starts the program
 */
package hangman;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author Sminky
 */
public class HangMan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        if (dim.getWidth() < 1200 || dim.getHeight() < 700) {
            JOptionPane.showMessageDialog(null, "Minimum screen resolution of 1200 X 700 required.\nYour screen resolution is " + (int) dim.getWidth() + " X " + (int) dim.getHeight() + ".", "Resolution Error", JOptionPane.ERROR_MESSAGE);
        } else {
            //Start the game by opening the Main Menu
            Menu menu = new Menu();
            menu.setVisible(true);
        }
    }
}
