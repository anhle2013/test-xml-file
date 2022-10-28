import org.example.Main;
import org.example.PeriodTime;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMain {
    Main testMain = new Main();

    @Test
    void testGetLocalDateTimeListFromXmlFile() throws ParserConfigurationException, IOException, SAXException {
        File fXmlFile = new File("testFile/testFile1.xml");
        Set<PeriodTime> result = new TreeSet<>();
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,3,0),LocalDateTime.of(2015,1,27,4,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,0),LocalDateTime.of(2015,1,27,10,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,0),LocalDateTime.of(2015,1,27,17,10)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,0),LocalDateTime.of(2015,1,27,22,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,20),LocalDateTime.of(2015,1,27,22,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,9,10),LocalDateTime.of(2015,1,27,22,30)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,9,20),LocalDateTime.of(2015,1,27,10,50)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,10,0),LocalDateTime.of(2015,1,27,17,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,12,0),LocalDateTime.of(2015,1,27,22,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,16,20),LocalDateTime.of(2015,1,27,21,30)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,16,30),LocalDateTime.of(2015,1,27,20,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,18,0),LocalDateTime.of(2015,1,27,19,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,18,0),LocalDateTime.of(2015,1,27,22,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,19,0),LocalDateTime.of(2015,1,27,20,0)));

        assertEquals(testMain.getLocalDateTimeListFromXmlFile(fXmlFile),result);
    }

    @Test
    void testGetPartitionByPeriodTime() {
        Set<PeriodTime> dateTimes = new TreeSet<>();
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,3,0),LocalDateTime.of(2015,1,27,4,0)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,0),LocalDateTime.of(2015,1,27,10,0)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,0),LocalDateTime.of(2015,1,27,17,10)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,0),LocalDateTime.of(2015,1,27,22,0)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,20),LocalDateTime.of(2015,1,27,22,0)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,9,10),LocalDateTime.of(2015,1,27,22,30)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,9,20),LocalDateTime.of(2015,1,27,10,50)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,10,0),LocalDateTime.of(2015,1,27,17,0)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,12,0),LocalDateTime.of(2015,1,27,22,0)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,16,20),LocalDateTime.of(2015,1,27,21,30)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,16,30),LocalDateTime.of(2015,1,27,20,0)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,18,0),LocalDateTime.of(2015,1,27,19,0)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,18,0),LocalDateTime.of(2015,1,27,22,0)));
        dateTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,19,0),LocalDateTime.of(2015,1,27,20,0)));

        Set<PeriodTime> result = new TreeSet<>();
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,3,0), LocalDateTime.of(2015,1,27,4,0)));
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,0), LocalDateTime.of(2015,1,27,22,30)));

        assertEquals(new TreeSet<>(testMain.getPartitionByPeriodTime(dateTimes)), result);
    }

    @Test
    void testGetEmptyIntervals() {
        ArrayList<PeriodTime> dividedTimes = new ArrayList<>();
        dividedTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,3,0), LocalDateTime.of(2015,1,27,4,0)));
        dividedTimes.add(new PeriodTime(LocalDateTime.of(2015,1,27,8,0), LocalDateTime.of(2015,1,27,22,30)));

        ArrayList<PeriodTime> result = new ArrayList<>();
        result.add(new PeriodTime(LocalDateTime.of(2015,1,27,4,0), LocalDateTime.of(2015,1,27,8,0)));

        assertEquals(new TreeSet<>(testMain.getEmptyIntervals(dividedTimes)), new TreeSet<>(result));
    }
}
