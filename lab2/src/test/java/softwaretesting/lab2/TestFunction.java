package softwaretesting.lab2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

class TestFunction {

    static double functionEps = 0.1;
    double eps = 0.1;

    static Sec secMock;
    static Cos cosMock;
    static Sin sinMock;
    static Ln lnMock;
    static Log logMock;

    static Reader secIn;
    static Reader cosIn;
    static Reader sinIn;
    static Reader lnIn;
    static Reader log2In;
    static Reader log3In;


    @BeforeAll
    static void init() {
        secMock = Mockito.mock(Sec.class);
        cosMock = Mockito.mock(Cos.class);
        sinMock = Mockito.mock(Sin.class);
        lnMock = Mockito.mock(Ln.class);
        logMock = Mockito.mock(Log.class);
        try {
            secIn = new FileReader("src/main/resources/CsvFiles/Inputs/SecIn.csv");
            cosIn = new FileReader("src/main/resources/CsvFiles/Inputs/CosIn.csv");
            sinIn = new FileReader("src/main/resources/CsvFiles/Inputs/SinIn.csv");
            lnIn = new FileReader("src/main/resources/CsvFiles/Inputs/LnIn.csv");
            log2In = new FileReader("src/main/resources/CsvFiles/Inputs/Log2In.csv");
            log3In = new FileReader("src/main/resources/CsvFiles/Inputs/Log3In.csv");

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(secIn);
            for (CSVRecord record : records) {
                Mockito.when(secMock.sec(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(cosIn);
            for (CSVRecord record : records) {
                Mockito.when(cosMock.cos(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                Mockito.when(sinMock.sin(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record : records) {
                Mockito.when(lnMock.ln(Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log2In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.log(2, Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log3In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.log(3, Double.parseDouble(record.get(0)), functionEps)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException ex) {
            System.err.println("Ты как в тесте IOE поймал?!");
        }

    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testSystemWithMocks(double value, double expected) {
        Function function = new Function(secMock, cosMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithSec(double value, double expected) {
        Function function = new Function(new Sec(cosMock), cosMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithCos(double value, double expected) {
        Function function = new Function(new Sec(new Cos(sinMock)), cosMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithSin(double value, double expected) {
        Function function = new Function(new Sec(new Cos(new Sin())), cosMock, lnMock, logMock);
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithLog(double value, double expected) {
        Function function = new Function(secMock, cosMock, lnMock, new Log(new Ln()));  // fixme pls must use "new Function(secMock, cosMock, lnMock, new Log(lnMock));"
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps * 50);  // fixme decrease eps
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithLn(double value, double expected) {
        Function function = new Function(secMock, new Cos(), new Ln(), new Log());
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps * 10);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/CsvFiles/Inputs/SystemIn.csv")
    void testWithSinAndLn(double value, double expected) {
        Function function = new Function();
        Assertions.assertEquals(expected, function.SystemSolve(value, functionEps), eps* 10);
    }
}