package homeWork.lessons2.task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ParserTimeTable {
    public static void parse(String path) throws ParserConfigurationException, SAXException, IOException {
        //Вносим объекты в список
        List<Train> list = new ArrayList<>();

        DefaultHandler handler = new DefaultHandler() {

            Train train = new Train();
            String element;

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                element = qName;
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                element = "";

                //В конце тега добавляем объект в список
                if (qName.equals("train")) {
                    list.add(new Train(train));
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String value = new String(ch, start, length);
                //Выполняем демаршалинг
                if (element.equalsIgnoreCase("from"))
                    train.setFrom(value);
                if (element.equalsIgnoreCase("to"))
                    train.setTo(value);
                if (element.equalsIgnoreCase("date")) {
                    SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");
                    try {
                        train.setDate(formatDate.parse(value));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (element.equalsIgnoreCase("departure")) {
                    SimpleDateFormat formatDeparture = new SimpleDateFormat("HH:mm");
                    try {
                        train.setDeparture(formatDeparture.parse(value));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        //Выполняем парсинг XML через SAX
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(path, handler);

        //Вызов метода для проверки отправления поездов по введеной дате
        timeOutput(list);
    }

    //Метод для вывода поезда с XML, если время его отправления попадает во временой диапазон
    //Временной диапазон пользователь вводит сам
    private static void timeOutput(List<Train> list) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        Date startDate = null;
        Date endDate = null;

        boolean checkOut = true;

        while (checkOut) {
            try {
                System.out.println("Введите диапазон времени через ':' ");
                startDate = dateFormat.parse(scanner.nextLine());
                endDate = dateFormat.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Введеное время не коректно. Вводите вемя в формате '13:00' \n");
            }

            if (startDate != null && endDate != null)
                checkOut = false;
        }

        //Проверка на время отправления поезда, если подходит в установленный рамки, выводим объект
        for (Train train : list) {
            Date date = train.getDeparture();

            //Написал так чтобы работало, даже если testDate был точно равен одному из конечных случаев.
            if (!(date.before(startDate) || date.after(endDate))) {
                System.out.println(train);
            }
        }


    }
}
