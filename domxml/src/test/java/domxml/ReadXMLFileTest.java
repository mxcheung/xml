package domxml;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//@Ignore
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ActiveProfiles("local")
public class ReadXMLFileTest {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(ReadXMLFileTest.class);
	public static int PRETTY_PRINT_INDENT_FACTOR = 4;

	@Test
	public void shouldGenerateWireMock() throws IOException {
		loadfile();
	}

	public void loadfile() {

		try {

			File fXmlFile = new File("src\\test\\resources\\root.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			XmlHelper xmlHelper2 = new XmlHelper();
			Element pharmacies = doc.getDocumentElement();
		//	System.out.println("root nNodeJson : " + xmlHelper2.nodeToJson(pharmacies));
			System.out.println( xmlHelper2.nodeToJson(pharmacies));

		//	NodeList nList = doc.getElementsByTagName("section");

		//	System.out.println("----------------------------");
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadfile2() {

		try {

			File fXmlFile = new File("src\\test\\resources\\root.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			XmlHelper xmlHelper2 = new XmlHelper();
			Element pharmacies = doc.getDocumentElement();
			System.out.println("root nNodeJson : " + xmlHelper2.nodeToJson(pharmacies));

			NodeList nList = doc.getElementsByTagName("section");

			System.out.println("----------------------------");
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				XmlHelper xmlHelper = new XmlHelper();
				System.out.println("section nNodeJson : " + xmlHelper.nodeToJson(nNode));
				System.out.println("----------------------------");

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;
					System.out.println("label : " + eElement.getElementsByTagName("label").item(0).getTextContent());

					/* Parse section */
					NodeList qList = eElement.getElementsByTagName("question");
					parseSectionQuestions(qList);

					/*
					 * System.out.println("Staff id : " + eElement.getAttribute("id"));
					 * System.out.println("First Name : " +
					 * eElement.getElementsByTagName("firstname").item(0).getTextContent());
					 * System.out.println("Last Name : " +
					 * eElement.getElementsByTagName("lastname").item(0).getTextContent());
					 * System.out.println("Nick Name : " +
					 * eElement.getElementsByTagName("nickname").item(0).getTextContent());
					 * System.out.println("Salary : " +
					 * eElement.getElementsByTagName("salary").item(0).getTextContent());
					 */
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseSectionQuestions(NodeList nList) throws Exception {
		XmlHelper xmlHelper = new XmlHelper();
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;
				System.out.println("id : " + eElement.getAttribute("id"));
				System.out.println("prev : " + eElement.getAttribute("prev"));
				System.out.println("nNode : " + xmlHelper.nodeToString(nNode));
				System.out.println("nNodeJson : " + xmlHelper.nodeToJson(nNode));

				// String nodeStr = xmlUtil.nodeToString(nNode);
				// JSONObject xmlJSONObj = XML.toJSONObject(nodeStr);
				// String jsonPrettyPrintString =
				// xmlJSONObj.toString(PRETTY_PRINT_INDENT_FACTOR);
				// System.out.println(jsonPrettyPrintString);

				// createDocFromElement(nNode);

			}

		}
	}

	private void createDocFromElement(Node nNode) throws Exception {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbFactory.newDocumentBuilder();
		Document doc = builder.newDocument();
		doc.appendChild(nNode);
		prettyPrint(doc);

	}

	public static final void prettyPrint(Document xml) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		Writer out = new StringWriter();
		tf.transform(new DOMSource(xml), new StreamResult(out));
		System.out.println(out.toString());
	}

}