package org.example;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {
    public static void main(String[] argv) {
        Main main = new Main();
        try {
            File fXmlFile = new File("testFile/testFile1.xml");
//            File fXmlFile = new File("data_java-2.xml");
            Set<PeriodTime> dateTimes = main.getLocalDateTimeListFromXmlFile(fXmlFile);
            System.out.println("dateTimes");
            System.out.println(dateTimes);
            System.out.println(dateTimes.size());

            ArrayList<PeriodTime> workingTimes = main.getPartitionByPeriodTime(dateTimes);
            System.out.println("workingTimes");
            System.out.println(workingTimes);
            System.out.println(workingTimes.size());

            ArrayList<PeriodTime> emptyIntervals = main.getEmptyIntervals(workingTimes);
            System.out.println("emptyIntervals: ");
            System.out.println(emptyIntervals);
            System.out.println(emptyIntervals.size());


//            Set<People> list = new TreeSet<>();
//            People demo1 = new People("Anh", "Anh");
//            People demo2 = new People("Le", "Le");
//            People demo3 = new People("Anh", "anh");
//            People demo4 = new People("Anh", "Anh");
//            People demo5 = new People("anh", "Annh");
//            list.add(demo1);
//            System.out.println(list.size());
//            list.add(demo2);
//            System.out.println(list.size());
//            list.add(demo3);
//            System.out.println(list.size());
//            list.add(demo4);
//            System.out.println(list.size());
//            list.add(demo5);
//            System.out.println(list.size());


//            Set<PeriodTime> list = new TreeSet<>();
//            LocalDateTime date1 = LocalDateTime.of(2015,1,28,10,15);
//            LocalDateTime date2 = LocalDateTime.of(2015,1,28,11,0);
//            LocalDateTime date3 = LocalDateTime.of(2015,1,28,14,10);
//            LocalDateTime date4 = LocalDateTime.of(2015,1,28,14,30);
//            LocalDateTime date5 = LocalDateTime.of(2015,1,28,16,20);
//            PeriodTime demo1 = new PeriodTime(date1,date2);
//            PeriodTime demo2 = new PeriodTime(date2,date3);
//            PeriodTime demo3 = new PeriodTime(date2,date4);
//            PeriodTime demo4 = new PeriodTime(date1,date4);
//            PeriodTime demo5 = new PeriodTime(date1,date2);
//            list.add(demo1);
//            System.out.println(list.size());
//            list.add(demo2);
//            System.out.println(list.size());
//            list.add(demo3);
//            System.out.println(list.size());
//            list.add(demo4);
//            System.out.println(list.size());
//            list.add(demo5);
//            System.out.println(list.size());
//            System.out.println(list);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Set<PeriodTime> getLocalDateTimeListFromXmlFile(File fXmlFile) throws ParserConfigurationException, IOException, SAXException {
        // read XML file
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("bar");

        // get times
        Set<PeriodTime> dateTimes = new TreeSet<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String strStartDateTime = eElement.getAttribute("startdate");
                String strEndDateTime = eElement.getAttribute("enddate");

                LocalDateTime startDateTime = LocalDateTime.parse(strStartDateTime, formatter);
                LocalDateTime endDateTime = LocalDateTime.parse(strEndDateTime, formatter);

                PeriodTime newPeriodTime = new PeriodTime(startDateTime, endDateTime);
                dateTimes.add(newPeriodTime);
            }
        }
        return dateTimes;
    }

    public ArrayList<PeriodTime> getPartitionByPeriodTime(Set<PeriodTime> dateTimes) {
        ArrayList<PeriodTime> list = new ArrayList<>(dateTimes);
        ArrayList<PeriodTime> dividedTimes = new ArrayList<>();
        LocalDateTime tempStart = list.get(0).getStartTime();
        LocalDateTime tempEnd = list.get(0).getEndTime();
        dividedTimes.add(new PeriodTime(tempStart,tempEnd));
        int index = 0;

        for (int i = 1; i < list.size(); i++) {
            LocalDateTime curStartTime = list.get(i).getStartTime();
            LocalDateTime curEndTime = list.get(i).getEndTime();

            if (curStartTime.compareTo(tempEnd) < 0) {
                if (curEndTime.compareTo(tempEnd) > 0) {
                    dividedTimes.remove(index);
                    tempEnd = curEndTime;
                    dividedTimes.add(new PeriodTime(tempStart,tempEnd));
                }
            } else if (curStartTime == tempEnd) {
                dividedTimes.remove(index);
                tempEnd = curEndTime;
                dividedTimes.add(new PeriodTime(tempStart,tempEnd));;
            } else {
                dividedTimes.add(new PeriodTime(curStartTime,curEndTime));
                tempStart = curStartTime;
                tempEnd = curEndTime;
                index++;
            }
        }
        return dividedTimes;
    }

    public ArrayList<PeriodTime> getEmptyIntervals(ArrayList<PeriodTime> dividedTimes) {
        ArrayList<PeriodTime> emptyIntervals = new ArrayList<>();
        LocalDateTime tempStartTime = dividedTimes.get(0).getEndTime();

        for (int i = 1; i < dividedTimes.size(); i++) {
            LocalDateTime tempEndTime = dividedTimes.get(i).getStartTime();
            PeriodTime newPeriodTime = new PeriodTime(tempStartTime, tempEndTime);
            emptyIntervals.add(newPeriodTime);
            tempStartTime = dividedTimes.get(i).getEndTime();
        }
        return emptyIntervals;
    }
}