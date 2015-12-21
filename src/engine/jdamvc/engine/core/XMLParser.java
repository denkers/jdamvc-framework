
package jdamvc.engine.core;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;


public class XMLParser
{
        
    public static Document getDocument(String path)
    {
        try
        {
            File configFile             =   new File(Config.class.getResource(path).getFile());
            DocumentBuilder dBuilder    =   DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc                =   dBuilder.parse(configFile);
            doc.getDocumentElement().normalize();
            return doc;
        }
        
        catch(ParserConfigurationException | SAXException | IOException e)
        {
            return null;
        }
    }
    
    public static boolean nodeBoolValue(String value)
    {
        return value.equalsIgnoreCase("true") || value.equals("1");
    }
    
    public static int nodeIntValue(String value)
    {
        try
        {
            return Integer.parseInt(value);
        }
        
        catch(NumberFormatException e)
        {
            return 0;
        }
    }
}
