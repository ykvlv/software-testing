package softwaretesting.lab2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;

public class Function {
    Sec sec;
    Cos cos;
    Log log;
    Ln ln;

    public Function() {
        this.sec = new Sec();
        this.cos = new Cos();
        this.ln = new Ln();
        this.log = new Log(ln);
    }

    public Function(Sec sec, Cos cos, Ln ln, Log log) {
        this.sec = sec;
        this.cos = cos;
        this.log = log;
        this.ln = ln;
    }

    public double SystemSolve(double x, double eps) {
        if (x <= 0) return sec.sec(x, eps) - cos.cos(x, eps);
        else
            return (
            (
                (log.log(2, x, eps) + log.log(3, x, eps)) / log.log(3, x, eps) * (log.log(2, x, eps) + log.log(3, x, eps)) / log.log(3, x, eps) * (log.log(2, x, eps) + log.log(3, x, eps)) / log.log(3, x, eps)
                -
                log.log(3, x, eps)
            )
            /
            (
                ln.ln(x, eps) * ln.ln(x, eps)
            )
        );
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
