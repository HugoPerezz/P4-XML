import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

            Document doc = db.parse("https://www.xataka.com/sitemap_index.xml");
            doc.getDocumentElement().normalize();

            System.out.println("Elemento raiz :" + doc.getDocumentElement().getNodeName());
            System.out.println("------");

            NodeList list = doc.getElementsByTagName("sitemap");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    String id = element.getAttribute("sitemap");
                    String loc = element.getElementsByTagName("loc").item(0).getTextContent();
                    System.out.println("First link : " + loc);

                    Document doc1 = db.parse((loc));
                    doc1.getDocumentElement().normalize();

                    System.out.println("Elemento raiz :" + doc1.getDocumentElement().getNodeName());
                    System.out.println("------");

                    NodeList list1 = doc1.getElementsByTagName("url");

                    for (int temp1 = 0; temp1 < list1.getLength(); temp1++) {
                        Node node1 = list1.item(temp1);
                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element element1 = (Element) node1;
                            String id1 = element1.getAttribute("loc");
                            String loc1;
                            if (element1.getElementsByTagName("loc").item(0) == null) {
                            } else {
                                loc1 = element1.getElementsByTagName("loc").item(0).getTextContent();
                                System.out.println("First link1 : " + loc1);
                            }
                        }
                    }
                    System.out.println("------");
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

