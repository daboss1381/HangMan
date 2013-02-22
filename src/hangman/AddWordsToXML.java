/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * Put all content in memory
 * Oragnize each category into a List
 * Add new word to correct list
 * Rewrite all content to xml
 */

/*
 * Category list
 * Mythology
 * Movies
 * Series
 * Cars
 * Drinks
 * Sports
 * Games
 * Actors/Actresses
 * Historical People
 */
package hangman;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sminky
 */
public class AddWordsToXML {
    //Create Lists

    ArrayList mythList = new ArrayList();
    ArrayList movieList = new ArrayList();
    ArrayList seriesList = new ArrayList();
    ArrayList carsList = new ArrayList();
    ArrayList drinksList = new ArrayList();
    ArrayList sportsList = new ArrayList();
    ArrayList gamesList = new ArrayList();
    ArrayList actorsList = new ArrayList();
    ArrayList peopleList = new ArrayList();
    //Category tags
    String heading = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
    String rootStart = "<hangman>";
    String rootEnd = "</hangman>";
    String catEnd = "</Cat>";
    String[] tags = {"<Cat att=\"Myth\">", "<Cat att=\"Movies\">", "<Cat att=\"Series\">", "<Cat att=\"Cars\">", "<Cat att=\"Drinks\">", "<Cat att=\"Sport\">", "<Cat att=\"Games\">", "<Cat att=\"Actors\">", "<Cat att=\"People\">"};

    public void populateLists() {
        try {
            File file = new File("C:\\words.xml");
            System.out.println("File Found");
            BufferedReader readXML = new BufferedReader(new FileReader(file));
            System.err.println("File Reader init.");
            String line = readXML.readLine();
            System.out.println("Line 1 read");
            String category = "";
            System.out.println("Entering While");
            while(line != null){
                if(line.contains("<Cat")){
                    category = line;
                    System.err.println("Category Selected: " + category);
                }
                if(category.contains("Myth") && (line.contains("<Phrase>") || line.contains("<Hint>"))){
                    mythList.add(line);
                    System.out.println();
                }
                line = readXML.readLine();
            }
            printLists();
        } catch (Exception ex) {
            Logger.getLogger(AddWordsToXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void printLists() {
        for(int i = 0; i < mythList.size(); i++){
            System.out.println(mythList.get(i) + "\n");
        }
    }
}
