package gb.jtwo.ltwo.online;

public class NotNumberElement extends NumberFormatException {


    NotNumberElement(int i, int j, String element) {
        super("Выявлено несоответствие типа данных, элемент '" + element + "' в строке " + i + " номер " + j + " не является числом");
        printStackTrace();
    }
}