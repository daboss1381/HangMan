/*
 * File:                StartSingleplayer.java
 * Author:              Andre Smink
 * Date:                November 2012
 * Operating System:    Windows 7 64-bit
 * Description:         Starts the Singleplayer mode and displays the Category
 *                      Selection frame
 */
package hangman;

/**
 *
 * @author Sminky
 */
public class StartSingleplayer {
    //CategorySelect class

    CategorySelect categorySelect = null;

    /**
     *
     */
    public StartSingleplayer() {
    }

    //open CategorySelect GUI
    /**
     *
     */
    public void start() {
        if (categorySelect == null) {
            categorySelect = new CategorySelect();
        }
        categorySelect.setVisible(true);
    }
}
