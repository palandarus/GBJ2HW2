package gb.jtwo.ltwo.online;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private String Errormsg;

    private String[][] parse(String inputText) throws NotQuadCubeArrayException {
        inputText.trim();
        inputText = inputText.replaceAll("  ", " ");
        int strCount = 0;
        Matcher m = Pattern.compile("\n").matcher(inputText);
        while (m.find()) {
            strCount++;
        }
        String[] stroka = new String[strCount];
        String[][] result = new String[strCount][4];
        try {
            stroka = inputText.split("\n");
            result = new String[strCount + 1][inputText.indexOf("\n")];
        } catch (NegativeArraySizeException e) {
            e.printStackTrace();
        }

        int maxel = 0, minel = stroka[0].length();
        try {
            for (int i = 0; i <= strCount; i++) {
                for (int j = 0; j < stroka[i].length(); j++) {
                    result[i] = stroka[i].split(" ");
                    maxel = (maxel < result[i].length) ? result[i].length : maxel;
                    minel = (minel > result[i].length) ? result[i].length : minel;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        if (maxel > 4 || strCount > 4 || minel < 4) throw new NotQuadCubeArrayException(maxel, minel, strCount + 1);
        return result;
    }

    public String getResult(String text) throws NotNumberElement {
        String result = "";
        String[][] array;
        try {
            array = parse(text);
            result = getCalculate(array);
        } catch (NotQuadCubeArrayException e) {
            e.printStackTrace();
        } catch (NotNumberElement e) {
            throw e;
        }

        return result;


    }

    private String getCalculate(String[][] array) throws NotNumberElement {
        float result = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                try {
                    result += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new NotNumberElement(i + 1, j + 1, array[i][j]);
                }
            }
        }
        return Float.toString(result / 2);
    }

    public String getErrormsg() {
        return Errormsg;
    }
}
