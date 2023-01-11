import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Letter_3_task_1_alternativ_vvod {

    @Test

    public void getText() {

        String text;


        //String text = JOptionPane.showInputDialog("Введите любую фразу длиной до 15 символов");
        //JOptionPane.showMessageDialog(null, "Ваша фраза, \n" + text);

        // другой способ ввода не работает. Спросила в чате

        Scanner in = new Scanner(System.in);
        System.out.println("Введите любую фразу длиной до 15 символов   ");
        text = in.nextLine();

        System.out.printf("Your phrase: %d \n", text);


        int n = text.length();

        assertTrue(n<15, "your phrase is longer than fifteen characters");

    }

}
