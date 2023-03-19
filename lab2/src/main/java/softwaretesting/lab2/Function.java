package softwaretesting.lab2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Function {
    Sec sec;
    Log log;
    Ln ln;

    public Function() {
        this.sec = new Sec();
        this.ln = new Ln();
        this.log = new Log(ln);
    }

    public Function(Sec sec, Ln ln, Log log) {
        this.sec = sec;
        this.log = log;
        this.ln = ln;
    }

    public double SystemSolve(double x, double eps) {
        if (x <= 0) return sec.sec(x, eps);
        else
            return (((log.log(2, x, eps) * log.log(2, x, eps) + log.log(10, x, eps)) * log.log(2, x, eps))
                    / log.log(2, x, eps)) / ln.ln(x, eps);
    }

    public double writeResultToCSV(double x, double eps, Writer out) {
        double res = SystemSolve(x, eps);
        try (CSVPrinter printer = CSVFormat.DEFAULT.print(out)) {
            printer.printRecord(x, res);
        } catch (IOException e) {
            System.out.println("Wrong filename");
        }
        return res;
    }
}
