package gb.jtwo.ltwo.online;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private String[][] parse(String inputText) throws NotQuadCubeArrayException {

        int strCount = 0;
        Matcher m = Pattern.compile("\n").matcher(inputText);
        while (m.find()) {
            strCount++;
        }
        String[] stroka = inputText.split("\n");
        String[][] result = new String[strCount + 1][inputText.indexOf("\n")];
        int maxel = 0;
        for (int i = 0; i <= strCount; i++) {
            for (int j = 0; j < stroka[i].length(); j++) {
                result[i] = stroka[i].split(" ");
                maxel = (maxel < result[i].length) ? result[i].length : maxel;
            }
        }
        if (maxel > 4 || strCount > 4) throw new NotQuadCubeArrayException(maxel, strCount + 1);
        return result;
    }

    public String getResult(String text) {
        String result = "";
        String[][] array;
        try {
            array = parse(text);
            result = getCalculate(array);
        } catch (NotQuadCubeArrayException e) {
            e.printStackTrace();
            result += e.getMessage();
        }
        catch (NotNumberElement e){
            e.printStackTrace();
            result += e.getMessage();
        }
        return result;


    }

    private String getCalculate(String[][] array) throws NotNumberElement {
        int result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    result += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new NotNumberElement(i+1,j+1,array[i][j]);
                }
            }
        }
        return Float.toString(result / 2);
    }

    public class NotQuadCubeArrayException extends IOException {
        private int str, stlb;

        public int getstr() {
            return str;
        }

        public int getstlb() {
            return stlb;
        }

        public NotQuadCubeArrayException(int maxel, int maxstr) {
            super("Выявлено несоблюдение условия размерности массива, текущий размер массива " + maxstr + "x" + maxel);
        }
    }

    public class NotNumberElement extends NumberFormatException {


        NotNumberElement(int i, int j, String element) {
            super("Выявлено несоответствие типа данных, элемент '" + element+ "' в строке " + i + " номер " + j + "не является числом");
        }
    }

}
