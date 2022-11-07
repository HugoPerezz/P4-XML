import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class App {
    private static final String FILENAME = "sitemap.xml";

    public static void main(String[] args) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(("https://www.xataka.com/sitemap_index.xml"));
            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("sitemap");
            int file_Id = 0;
            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("sitemap");

                    String loc = element.getElementsByTagName("loc").item(0).getTextContent();


                    Document doc1 = db.parse((loc));

                    doc1.getDocumentElement().normalize();


                    NodeList list1 = doc1.getElementsByTagName("url");


                    URL url = new URL(loc);
                    String path = url.getPath().replace('/', '_');
                    System.out.println(path);

                    try (PrintStream docu = new PrintStream("C:\\Users\\Alex\\IdeaProjects\\P4\\xmls\\" + path + ".txt")) {


                    }

                    for (int temp1 = 0; temp1 < list1.getLength(); temp1++) {
                        Node node1 = list1.item(temp1);
                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element element1 = (Element) node1;

                            String id1 = element1.getAttribute("loc");

                            String loc1;
                            loc1 = element1.getElementsByTagName("loc").item(0).getTextContent();
                            loc1 = loc1+"\n";
                            try {
                                Files.write(Paths.get("C:\\Users\\Alex\\IdeaProjects\\P4\\xmls\\" + path + ".txt"), loc_2.getBytes(), StandardOpenOption.APPEND);
                            } catch (IOException e) {
                                //exception handling left as an exercise for the reader
                            }
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}