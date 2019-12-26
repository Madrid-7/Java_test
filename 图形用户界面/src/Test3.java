import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test3 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("登录界面");
        JPanel jp1,jp2,jp3;
        JTextField jtf1;
        JPasswordField jpf1;
        JLabel jlb1,jlb2;
        JButton jb1,jb2;
        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();
        jlb1=new JLabel("用户名");
        jlb2=new JLabel("密	  码");
        jb1=new JButton("登录");
        jb2=new JButton("取消");
        jtf1=new JTextField(10);
        jpf1=new JPasswordField(10);
        frame.setLayout(new GridLayout(3,1));
        frame.add(jp1);
        frame.add(jp2);
        frame.add(jp3);
        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(jlb2);
        jp2.add(jpf1);
        jp3.add(jb1);
        jp3.add(jb2);
        frame.setTitle("登录界面");
        frame.setSize(300, 200);
        frame.setLocation(500, 100);
        jb1.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame jff = new JFrame();
                jff.setSize(150, 200);
                jff.setLocation(250, 250);
                jff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JTextField jtf = new JTextField("登录成功");
                jff.add(jtf);
                jff.setVisible(true);
            }
        });
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
