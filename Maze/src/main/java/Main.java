import javax.swing.*;
import java.awt.*;

public class Main {
    private static void initUI(){
        JFrame frame1 = new JFrame("Labirint");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Graf graf = new Graf();
        frame1.add(graf);
        graf.readMatrixFromFile();
        graf.bfsForMore();
        frame1.setSize(1000, 1000);
        frame1.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                initUI();
            }
        });
    }
}
