import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MyFrame extends JFrame implements ActionListener {
    JButton btn = new JButton("点我");
    /**
     * main()
     *
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyFrame frm = new MyFrame();
        frm.btn.addActionListener(frm);
        frm.add(frm.btn);
        frm.setSize(200, 400);
        frm.setVisible(true);

    }
    public void actionPerformed(ActionEvent e) {

        JOptionPane.showMessageDialog((JButton)e.getSource(), "有效果!");
    }

}
