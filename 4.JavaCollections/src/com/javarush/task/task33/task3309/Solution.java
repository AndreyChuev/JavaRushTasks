package com.javarush.task.task33.task3309;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws Exception {
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        marshaller.marshal(obj, document);

        NodeList nodeList = document.getElementsByTagName(tagName);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            String text = node.getTextContent();
            if (text != null && text.matches(".+CDATA.+")) continue;
            node.getParentNode().insertBefore(document.createComment(comment), node);
        }

        StringWriter stringWriter = new StringWriter();
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer transformer = factory.newTransformer();
        transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.transform(new DOMSource(document), new StreamResult(stringWriter));
        return stringWriter.toString();
    }

    public static void main(String[] args) throws Exception {
        Cat cat = new Cat();
        cat.name = "Марти";
        cat.weight = 2;
        System.out.println(toXmlWithComment(cat, "name", "Имя кота"));
    }

    @XmlRootElement
    public static class Cat {
        public String name;
        public int weight;
    }
}
