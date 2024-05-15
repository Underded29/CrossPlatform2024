package Server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ConfData {

    private ArrayList<UserRecord> alist ;

    public ConfData(){
        alist = new ArrayList<>();
    }
    public ConfData(UserRecord... records){
        alist = new ArrayList<>(List.of(records));


    }

    public void exportToXML(String path){
        Transformer trf;
        DOMSource src;
        FileOutputStream file;

        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element element = document.createElement("List");

            for (int i = 0; i < alist.size(); i++) {
                Element user = document.createElement("User");

                Element e1 = document.createElement("name");
                e1.setTextContent(alist.get(i).name);
                Element e2 = document.createElement("surname");
                e2.setTextContent(alist.get(i).surname);
                Element e3 = document.createElement("title");
                e3.setTextContent(alist.get(i).title);
                Element e4 = document.createElement("email");
                e4.setTextContent(alist.get(i).email);
                Element e5 = document.createElement("workplace");
                e5.setTextContent(alist.get(i).workPlace);
                appendMultiple(user, e1, e2, e3, e4, e5);
                element.appendChild(user);

            }
        document.appendChild(element);
            trf = TransformerFactory.newInstance().newTransformer();
            trf.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
            trf.setOutputProperty(OutputKeys.INDENT, "yes");
            trf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            src = new DOMSource(document);
            file = new FileOutputStream(
                    path);

            StreamResult result = new StreamResult(file);
            trf.transform(src, result);


        }
        catch (Exception e){
            System.err.println(e);

        }
    }

    private Element appendMultiple(Element body, Element...elements){
        for (Element e:elements) {
            body.appendChild(e);
        }

        return body;
    }
    public void addUser(UserRecord rec){

        alist.add(rec);}
    public void loadStruct(String path) throws ParserConfigurationException, SAXException {
        alist = new ArrayList<>();

        SAXParser saxParser = SAXParserFactory.newDefaultInstance().newSAXParser();
        DataHandler handler = new DataHandler();
        try {
            saxParser.parse(new File(path),handler);
            alist.addAll(handler.getStruct());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public ArrayList<UserRecord> getStruct() {

        return alist;
    }


}


class DataHandler extends DefaultHandler {

    private int currentUser = 0;

    private String textContent;
    private String curTag;

    private  ArrayList<UserRecord> dataStruct;
    public DataHandler(){


    }
    public ArrayList<UserRecord> getStruct() {
        System.err.println(dataStruct.size() +""+ dataStruct.get(1));
        return dataStruct;
    }
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)  {
        //qName -- tag name
        //System.out.println(qName);
        curTag = qName;
        if(qName.compareTo("List")==0){
            if(dataStruct!=null){
                return;}
            dataStruct = new ArrayList<>();
        } else if (qName.compareTo("User")==0) {

            dataStruct.add(new UserRecord());  currentUser++;
        } else if (qName.compareTo("name")==0) {
            dataStruct.get(currentUser-1).name = qName;
        }
        else if (qName.compareTo("surname")==0) {
            dataStruct.get(currentUser-1).surname = qName;
        }
        else if (qName.compareTo("title")==0) {
            dataStruct.get(currentUser-1).title = qName;
        } else if (qName.compareTo("email")==0) {
            dataStruct.get(currentUser-1).email = qName;
        }
        else if (qName.compareTo("workplace")==0) {
            dataStruct.get(currentUser-1).workPlace = qName;
        }



    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {




    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String string;
        if (!(string = new String(ch, start, length)).isBlank()) {
            System.out.println(string);
            if (curTag.compareTo("name") == 0) {
                dataStruct.get(currentUser - 1).name = string;
            } else if (curTag.compareTo("surname") == 0) {


                dataStruct.get(currentUser - 1).surname = string;

            } else if (curTag.compareTo("title") == 0) {
                dataStruct.get(currentUser - 1).title = string;
            } else if (curTag.compareTo("workplace") == 0) {
                dataStruct.get(currentUser - 1).workPlace = string;

            } else if (curTag.compareTo("email") == 0) {
                dataStruct.get(currentUser - 1).email = string;
            }
        }

    }
}