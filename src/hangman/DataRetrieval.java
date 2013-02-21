/*
 * File:                DataRetrieval.java
 * Author:              Andre Sminky
 * Date:                November 2012
 * Operating System:    Windows 7 64-bit
 * Description:         Retrieves data from the handler that parsed the xml file
 */
package hangman;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Random;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Sminky
 */
public class DataRetrieval {

    //Var to contain the connection to the database
    Connection conn;
    static ArrayList wordList = new ArrayList();
    static ArrayList hintList = new ArrayList();
    static int selectedIndex;

    /**
     *
     */
    public DataRetrieval() {
    }

    //Retrieve word from database
    public String getWord(String cat) {
        //var to hold word to be retrieved
        String word;
        //call method that retrieves words from Handler class
        setWords(cat);
        //generate random number te get random word from list
        Random r = new Random();
        selectedIndex = r.nextInt(wordList.size());
        //set random word to word var
        word = wordList.get(selectedIndex).toString();
        //return the word retrieved
        return word;
    }

    /**
     *
     * @param word
     * @return
     */
    //retrieve hint for the word in play
    public String getHint() {
        //get hint from hintlist at same index as word from wordlist
        return hintList.get(selectedIndex).toString();
    }

    //sets words and hints to their lists
    public void setWords(String cat) {
        //clear lists
        wordList.clear();
        hintList.clear();
        //set handler objects
        MyHandler myhandler = new MyHandler();
        DefaultHandler handler = myhandler;

        SAXParserFactory factory = SAXParserFactory.newInstance();

        try {
            //parse xml and retrieve the data 
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse("words.xml", handler);

            //set data from handler in array
            String words[] = myhandler.getWords();

            //loop through array
            for (int i = 0; i < words.length; i++) {
                //enter if when category matches the category selected
                if (words[i].equals(cat)) {
                    //increment i to first word of that category
                    i++;
                    //split id from content (string format ID/Content eg. Word/Hello)
                    String[] split = words[i].split("/");
                    //while end of category is not reached add words and hint to their lists
                    while (!split[0].equals("Cat")) {
                        switch (split[0].trim()) {
                            //When ID is Phrase add Content to wordList
                            case "Phrase":
                                wordList.add(split[1]);
                                break;
                            //When ID is Hint add Content to hintList
                            case "Hint":
                                hintList.add(split[1]);
                                break;
                        }
                        //increment to next index
                        i++;
                        //split next entry
                        split = words[i].split("/");
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.err.println(e);
        }
    }
}