import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test2 extends JFrame {
    JPanel jp1,jp2,jp3;
    JTextField jtf1;
    JPasswordField jpf1;
    JLabel jlb1,jlb2;
    JButton jb1,jb2;
    public static void main(String[] args) {
        Test2 a=new Test2();
    }
    public Test2() {
        jp1=new JPanel();
        jp2=new JPanel();
        jp3=new JPanel();
        jlb1=new JLabel("用户名");
        jlb2=new JLabel("密	  码");
        jb1=new JButton("登录");
        jb2=new JButton("取消");
        jtf1=new JTextField(10);
        jpf1=new JPasswordField(10);
        this.setLayout(new GridLayout(3,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        jp1.add(jlb1);
        jp1.add(jtf1);
        jp2.add(jlb2);
        jp2.add(jpf1);
        jp3.add(jb1);
        jp3.add(jb2);
        this.setTitle("登录界面");
        this.setSize(300, 200);
        this.setLocation(500, 100);
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
               // this.dispose();
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}


