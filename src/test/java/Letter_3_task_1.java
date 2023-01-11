
import org.junit.jupiter.api.Test;

import javax.swing.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Letter_3_task_1 {

    @Test

    public void getText() {

        String text;

        text = JOptionPane.showInputDialog("Введите любую фразу длиной до 15 символов");
        JOptionPane.showMessageDialog(null, "Ваша фраза, \n" + text);

        int n = text.length();

        assertTrue(n<15, "your phrase is longer than fifteen characters");

    }

}
