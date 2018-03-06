
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.json.JSONException;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.mxcheung.XmlToJsonHelper;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CustomerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTest.class);
	private XmlToJsonHelper xmlToJsonHelper;

	protected static final String TEST_XML = "Tutorials";

	protected static final String TEST_FILEPATH = "src\\test\\resources\\";

	private XPath xpath;

	@Before
	public void setup() throws IOException {
		xmlToJsonHelper = new XmlToJsonHelper();
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();

	}

	@Test
	public void shouldLocateNode() throws FileNotFoundException, IOException, ParserConfigurationException,
			SAXException, JSONException, XPathExpressionException {
		String dataFile = TEST_FILEPATH + TEST_XML + ".xml";
		Document doc = xmlToJsonHelper.xmltoDoc(new File(dataFile));
		Node nNode = findNode("01", doc);
		String json = xmlToJsonHelper.nodeToJson(nNode);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			LOGGER.info("author : " +  eElement.getElementsByTagName("author").item(0).getTextContent());
		}
	}

	@Test
	public void shouldLocateSection() throws FileNotFoundException, IOException, ParserConfigurationException,
			SAXException, JSONException, XPathExpressionException {
		String dataFile = TEST_FILEPATH + TEST_XML + ".xml";
		Document doc = xmlToJsonHelper.xmltoDoc(new File(dataFile));
		NodeList nodeList = findNodeList("04", doc);

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node nNode = nodeList.item(i);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				LOGGER.info("name : " + eElement.getAttribute("name"));
				LOGGER.info("section : " + eElement.getTextContent());
			}
		}
	}

	public Node findNode(String id, Document doc) throws XPathExpressionException {
		String expression = "//*[@tutId='" + id + "']";
		Node node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
		return node;
	}

	public NodeList findNodeList(String id, Document doc) throws XPathExpressionException {
		String expression = "//*[@tutId='" + id + "']/descendant::section ";
		NodeList nodeList = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
		return nodeList;
	}

}
