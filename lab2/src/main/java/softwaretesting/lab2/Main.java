package softwaretesting.lab2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        double[] xs = new double[]{Double.NEGATIVE_INFINITY, -2*Math.PI, -Math.PI, 0, 0.5, 1, 2, 4, 100, Double.POSITIVE_INFINITY};
//        String[] fileNames = new String[]{"Cos", "Ln", "Log2", "Log3", "Sec", "Sin", "System"};
//        Cos c = new Cos();
//        Sin s = new Sin();
//        Function f = new Function();
//        Ln l = new Ln();
        Sec s = new Sec();

//        for (String s: fileNames){
            for (double x: xs) {
//                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/CsvFiles/Inputs/%sIn.csv".formatted("Sin"), true));
//                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/CsvFiles/Inputs/%sIn.csv".formatted("Ln"), true));
                BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/CsvFiles/Inputs/%sIn.csv".formatted("Sec"), true));
                s.writeResultToCSV(x, 0.0001, writer);
            }
//        }
    }
}
