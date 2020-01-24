package gb.jtwo.ltwo.online;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame { //Наследуя от JFrame мы получаем всю функциональность окна

    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private JLabel inputLabel;
    private JButton calcButton;
    private JTextArea inputedString;
    private JTextArea outputedString;

    private Parser parser;


    public MainWindow() {
        super("GB.HW2.String_Parser"); //Заголовок окна
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT); //Если не выставить
        //размер и положение
        //то окно будет мелкое и незаметное
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //это нужно для того чтобы при
        //закрытии окна закрывалась и программа,
        //иначе она останется висеть в процессах
        parser = new Parser();
        inputLabel = new JLabel("Введите строку для обработки");
        inputedString = new JTextArea("10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0");
        calcButton = new JButton("Обработать строку");
        outputedString = new JTextArea();
        calcButton.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent actionEvent) {
                                             //добавить вызов метода обработки строки
                                             String result = "";
                                             try {
                                                 result = parser.getResult(inputedString.getText());
                                             } catch (NotNumberElement e) {
                                                 result += e.getMessage();
                                                 e.printStackTrace();
                                             }

                                             outputedString.setText(result);
                                         }
                                     }

        );
        //Подготавливаем временные компоненты
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        //Расставляем компоненты по местам
        buttonsPanel.add(inputLabel, BorderLayout.NORTH); //О размещении компонент поговорим позже
        buttonsPanel.add(inputedString, BorderLayout.NORTH);
        buttonsPanel.add(calcButton);
        buttonsPanel.add(outputedString);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) { //эта функция может быть и в другом классе
        MainWindow app = new MainWindow(); //Создаем экземпляр нашего приложения
        app.setVisible(true); //С этого момента приложение запущено!
        app.pack();
    }
}