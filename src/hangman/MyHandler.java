/*
 * File:                MyHandler.java
 * Author:              Andre Smink
 * Date:                November 2012
 * Operating System:    Windows 7 64-bit
 * Description:         Parses the xml file and add the data to a list
 */
package hangman;

import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Sminky
 */
public class MyHandler extends DefaultHandler {

    //vars
    StringBuffer buffer;
    ArrayList data = null;
    String cat = "";

    public MyHandler() {
        //create arrayList to hold data
        data = new ArrayList();
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void startElement(String namespacesURI, String sName, String qName, Attributes attrs) throws SAXException {
        int length = attrs.getLength();
        //Each attribute
        for (int i = 0; i < length; i++) {
            // Get names and values to each attribute
            String name = attrs.getQName(i);
            //add atribute to list as the start of category
            data.add(attrs.getValue(i));
        }
    }

    @Override
    public void endElement(String namespacesURI, String sName, String qName) throws SAXException {
        if (!(buffer == null)) {
            //add tag name as ID and tag content as Content to list as ID/Content
            data.add(qName + "/" + buffer.toString().trim());
            buffer = null;
        }
    }

    @Override
    public void characters(char buf[], int offset, int len) throws SAXException {
        String s = new String(buf, offset, len);
        if (buffer == null) {
            buffer = new StringBuffer(s);
        } else {
            buffer.append(s);
        }
    }

    @Override
    public void endDocument() throws SAXException {
    }

    //move list to array and return it for use in DataRetrieval Class
    public String[] getWords() {
        String[] words = new String[data.size()];
        for (int i = 0; i < data.size(); i++) {
            words[i] = data.get(i).toString();
        }
        return words;
    }
}
