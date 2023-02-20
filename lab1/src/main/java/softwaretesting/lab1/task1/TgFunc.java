package softwaretesting.lab1.task1;

import java.lang.Math;

public class TgFunc {

    // |x| < pi/2
    // производную tg(x) фактически нереально вычислять, поэтому захардкодили просто первые 10 членов ряда Тейлора
    public static double tg(double x) {
        if(Double.isNaN(x) || Double.isInfinite(x)){
            return Double.NaN;
        }
        if (Math.abs(x) >= Math.PI/2){
            return Double.NaN;
        }
        return x + (Math.pow(x, 3)/3) + (2*Math.pow(x, 5)/15) + (17*Math.pow(x, 7)/315) + (62*Math.pow(x, 9)/2835);
    }
}
