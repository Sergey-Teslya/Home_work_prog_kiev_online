package homeWork.lessons2.task3;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ParserXML {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse("https://api.privatbank.ua/p24api/pubinfo?exchange&coursid=3");

        // Возвращает объект класса Element, получаем корневой элемент XML_JSON
        Element root = document.getDocumentElement();
        System.out.println("Корневой элемент: " + root.getNodeName());

        //Получение списка дочерних узло
        NodeList nodeListRoow = root.getChildNodes();

        //
        for (int i = 0; i < nodeListRoow.getLength(); i++){
            Node node = nodeListRoow.item(i);

            //Проверка если текущий узел не текст, то преобразуем его в Element и вызываем метод для его разбора
            if (node.getNodeType() == Node.ELEMENT_NODE){
                Element exchangerate = (Element) node;
                //Получаем список атрибутов
                NamedNodeMap exchangerateAttributs = exchangerate.getChildNodes().item(0).getAttributes();

                //Выводим все атрибути со значениями
                for (int j = 0; j < exchangerateAttributs.getLength(); j++) {
                    System.out.print(exchangerate.getChildNodes().item(0).getAttributes().item(j) + " ");
                }
                System.out.println();
            }
        }
    }
}
