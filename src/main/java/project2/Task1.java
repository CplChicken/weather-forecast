package project2;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class Task1 
{
    public static void main(String[] args) throws Exception
    {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new URL("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2656397").openStream());
    
    Element root = doc.getDocumentElement();
    
    System.out.println(root.getElementsByTagName("title").item(1).getTextContent());
    }
}

