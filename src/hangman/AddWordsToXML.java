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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
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
    File file = new File("words.xml");

    public void runAddingSequence(String category, String phrase, String hint) {
        System.err.println("Started Adding Sequence");
        System.err.println("Phase 1:");
        populateLists();
        System.err.println("Phase 2:");
        addToAList(category, phrase, hint);
        System.err.println("Phase 3:");
        writeToXML();
        System.err.println("Finished Adding Sequence");
    }

    public void populateLists() {
        System.out.println("Started to Populate Lists");
        try {
            BufferedReader readXML = new BufferedReader(new FileReader(file));
            String line = readXML.readLine();
            String category = "";
            while (line != null) {
                if (line.contains("<Cat")) {
                    category = line;
                }
                //mythList
                if (category.contains("Myth") && (line.contains("<Phrase>") || line.contains("<Hint>"))) {
                    mythList.add(line);
                    System.out.println("Added " + line + " to Myth List");
                }
                //movieList
                if (category.contains("Movies") && (line.contains("<Phrase>") || line.contains("<Hint>"))) {
                    movieList.add(line);
                    System.out.println("Added " + line + " to Movie List");
                }
                //seriesList
                if (category.contains("Series") && (line.contains("<Phrase>") || line.contains("<Hint>"))) {
                    seriesList.add(line);
                    System.out.println("Added " + line + " to Series List");
                }
                //carsList
                if (category.contains("Cars") && (line.contains("<Phrase>") || line.contains("<Hint>"))) {
                    carsList.add(line);
                    System.out.println("Added " + line + " to Cars List");
                }
                //drinksList
                if (category.contains("Drinks") && (line.contains("<Phrase>") || line.contains("<Hint>"))) {
                    drinksList.add(line);
                    System.out.println("Added " + line + " to Drinks List");
                }
                //sportsList
                if (category.contains("Sport") && (line.contains("<Phrase>") || line.contains("<Hint>"))) {
                    sportsList.add(line);
                    System.out.println("Added " + line + " to Sports List");
                }
                //gamesList
                if (category.contains("Games") && (line.contains("<Phrase>") || line.contains("<Hint>"))) {
                    gamesList.add(line);
                    System.out.println("Added " + line + " to Games List");
                }
                //actorsList
                if (category.contains("Actors") && (line.contains("<Phrase>") || line.contains("<Hint>"))) {
                    actorsList.add(line);
                    System.out.println("Added " + line + " to Actors List");
                }
                //peopleList
                if (category.contains("People") && (line.contains("<Phrase>") || line.contains("<Hint>"))) {
                    peopleList.add(line);
                    System.out.println("Added " + line + " to People List");
                }
                line = readXML.readLine();
            }
        } catch (Exception ex) {
            Logger.getLogger(AddWordsToXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finished populating Lists");
    }

    public void addToAList(String category, String phrase, String hint) {
        System.out.println("Stated adding new entry to a list");
        try {
            if (category.equals("Mythology")) {
                mythList.add("<Phrase>" + phrase + "</Phrase>");
                mythList.add("<Hint>" + hint + "</Hint>");
            }

            if (category.equals("Movies")) {
                movieList.add("<Phrase>" + phrase + "</Phrase>");
                movieList.add("<Hint>" + hint + "</Hint>");
            }

            if (category.equals("Series")) {
                seriesList.add("<Phrase>" + phrase + "</Phrase>");
                seriesList.add("<Hint>" + hint + "</Hint>");
            }

            if (category.equals("Cars")) {
                carsList.add("<Phrase>" + phrase + "</Phrase>");
                carsList.add("<Hint>" + hint + "</Hint>");
            }

            if (category.equals("Drinks")) {
                drinksList.add("<Phrase>" + phrase + "</Phrase>");
                drinksList.add("<Hint>" + hint + "</Hint>");
            }

            if (category.equals("Sports")) {
                sportsList.add("<Phrase>" + phrase + "</Phrase>");
                sportsList.add("<Hint>" + hint + "</Hint>");
            }

            if (category.equals("Games")) {
                gamesList.add("<Phrase>" + phrase + "</Phrase>");
                gamesList.add("<Hint>" + hint + "</Hint>");
            }

            if (category.equals("Actors/Actresses")) {
                actorsList.add("<Phrase>" + phrase + "</Phrase>");
                actorsList.add("<Hint>" + hint + "</Hint>");
            }

            if (category.equals("Historical People")) {
                peopleList.add("<Phrase>" + phrase + "</Phrase>");
                peopleList.add("<Hint>" + hint + "</Hint>");
            }
        } catch (Exception e) {
        }
        System.out.println("Done adding new entry to a list");
    }

    public void writeToXML() {
        System.out.println("Started to write lists to xml file");
        try {
            PrintWriter writeToXMLFile = new PrintWriter(file);

            writeToXMLFile.append(heading + "\n");
            writeToXMLFile.append(rootStart + "\n");

            writeToXMLFile.append(tags[0] + "\n");
            for (int i = 0; i < mythList.size(); i++) {
                writeToXMLFile.append(mythList.get(i).toString() + "\n");
            }
            writeToXMLFile.append(catEnd + "\n");

            writeToXMLFile.append(tags[1] + "\n");
            for (int i = 0; i < movieList.size(); i++) {
                writeToXMLFile.append(movieList.get(i).toString() + "\n");
            }
            writeToXMLFile.append(catEnd + "\n");

            writeToXMLFile.append(tags[2] + "\n");
            for (int i = 0; i < seriesList.size(); i++) {
                writeToXMLFile.append(seriesList.get(i).toString() + "\n");
            }
            writeToXMLFile.append(catEnd + "\n");

            writeToXMLFile.append(tags[3] + "\n");
            for (int i = 0; i < carsList.size(); i++) {
                writeToXMLFile.append(carsList.get(i).toString() + "\n");
            }
            writeToXMLFile.append(catEnd + "\n");

            writeToXMLFile.append(tags[4] + "\n");
            for (int i = 0; i < drinksList.size(); i++) {
                writeToXMLFile.append(drinksList.get(i).toString() + "\n");
            }
            writeToXMLFile.append(catEnd + "\n");

            writeToXMLFile.append(tags[5] + "\n");
            for (int i = 0; i < sportsList.size(); i++) {
                writeToXMLFile.append(sportsList.get(i).toString() + "\n");
            }
            writeToXMLFile.append(catEnd + "\n");

            writeToXMLFile.append(tags[6] + "\n");
            for (int i = 0; i < gamesList.size(); i++) {
                writeToXMLFile.append(gamesList.get(i).toString() + "\n");
            }
            writeToXMLFile.append(catEnd + "\n");

            writeToXMLFile.append(tags[7] + "\n");
            for (int i = 0; i < actorsList.size(); i++) {
                writeToXMLFile.append(actorsList.get(i).toString() + "\n");
            }
            writeToXMLFile.append(catEnd + "\n");

            writeToXMLFile.append(tags[8] + "\n");
            for (int i = 0; i < peopleList.size(); i++) {
                writeToXMLFile.append(peopleList.get(i).toString() + "\n");
            }
            writeToXMLFile.append(catEnd + "\n");

            writeToXMLFile.append(rootEnd);

            writeToXMLFile.close();
        } catch (Exception ex) {
            Logger.getLogger(AddWordsToXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Finished writing lists to xml file");
    }
}
