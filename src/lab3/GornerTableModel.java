package lab3;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;
    public GornerTableModel(Double from, Double to, Double step,
                            Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }
    public Double getFrom() {
        return from;
    }
    public Double getTo() {
        return to;
    }
    public Double getStep() {
        return step;
    }
    public int getColumnCount() {
// В данной модели два столбца
        return 4;
    }
    public int getRowCount() {
// Вычислить количество точек между началом и концом отрезка
// исходя из шага табулирования
        return (int)(Math.ceil((to-from)/step))+1;
    }
    public Object getValueAt(int row, int col) {
        // Вычислить значение X как НАЧАЛО_ОТРЕЗКА + ШАГ*НОМЕР_СТРОКИ
        double x = from + step*row;
        Double gornerResult = 0.0;
        for (int i = 0; i < coefficients.length; i++) {
            gornerResult = gornerResult * x + coefficients[i];
        }

        Double otherResult = coefficients[coefficients.length-1]; // TODO: calculate
        for (int i = 0; i < coefficients.length-1; i++) {
            otherResult += coefficients[i]*Math.pow(x, coefficients.length-1-i);
        }

        switch (col) {
            case 0:
                return x;
            case 1:
                return gornerResult;
            case 2:
                return otherResult;
            default:
                return gornerResult-otherResult;
        }
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            case 2:
                return "Не по схеме";
            default:
                return "Разница";
        }
    }
    public Class<?> getColumnClass(int col) {
// И в 1-ом и во 2-ом столбце находятся значения типа Double
        return Double.class;
    }
}


