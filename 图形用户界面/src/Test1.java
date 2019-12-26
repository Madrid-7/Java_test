import javax.swing.*;
import java.awt.*;

public class Test1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("A simple GUI");
        JLabel jt = new JLabel("Which one is your choice?");
        JButton yes = new JButton("yes");
        JTextField text = new JTextField("@*#&%#$...");
        FlowLayout flow = new FlowLayout();
        frame.setLayout(flow);
        frame.add(jt);
        frame.add(yes);
        frame.add(text);
        frame.setBounds(300,300,400,100);
        jt.setBackground(Color.CYAN);
        yes.setBackground(Color.BLUE);
        text.setBackground(Color.GREEN);
        text.setForeground(Color.YELLOW);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
