import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Test {


    //保留字表
    public static Set<String> ReserveWord = new HashSet<>();
    //界符表(12)
    public static Set<Character> JiefuTable = new HashSet<>();
    //标识符表
    public static Set<String> IDentifierTable = new HashSet<>();
    //常数表
    public static Set<String> DigitBTable = new HashSet<>();


    static {
        String[] strs = {
                "auto", "break", "case", "char", "const", "continue",
                "default", "do", "double", "else", "enum", "extern",
                "float", "for", "goto", "if", "int", "long",
                "register", "return", "short", "signed", "sizeof", "static",
                "struct", "switch", "typedef", "union", "unsigned", "void",
                "volatile", "while"
        };

        //界符表(12)
        Character[] Jiefu = {
            ';', '(', ')', '^', ',', '#', '%', '[', ']', '{', '}', '.'
        };

        ReserveWord.addAll(Arrays.asList(strs));
        JiefuTable.addAll(Arrays.asList(Jiefu));
    }

    //判断是否是：字母
    public static boolean Isletter(char x) { return (x >= 'a' && x <= 'z')||(x >= 'A' && x <= 'Z'); }

    //判断是否是：数字
    public static boolean IsDigit(char x) { return x >= '0' && x <= '9'; }

    //判断是否是：界符
    public static boolean IsJiefu(char x)
    {
        return JiefuTable.contains(x);
    }

    //判断是否是 算数运算符：加减乘
    public static boolean IsSuanshuyunsuanfu(char x)
    {
        return x == '+' || x == '-' || x == '*';
    }

    //判断是否是 关系运算符：等于（赋值），大于小于（大于等于，小于等于，大于小于）
    public static boolean IsGuanxiyunsuanfu(char x) { return x == '=' || x == '<' || x == '>'; }

    public static void read_write_File () throws IOException {
        FileReader reader = new FileReader("in.txt");
        BufferedReader bReader = new BufferedReader(reader);

        FileWriter writer = new FileWriter("out.txt");
        BufferedWriter bWriter = new BufferedWriter(writer);
        FileWriter writer1 = new FileWriter("ReserveWordOut.txt");
        BufferedWriter bWriter1 = new BufferedWriter(writer1);
        FileWriter writer2 = new FileWriter("IDentifierTableOut.txt");
        BufferedWriter bWriter2 = new BufferedWriter(writer2);
        FileWriter writer3 = new FileWriter("SuanshuyunsuanfuOut.txt");
        BufferedWriter bWriter3 = new BufferedWriter(writer3);
        FileWriter writer4 = new FileWriter("DigitOut.txt");
        BufferedWriter bWriter4 = new BufferedWriter(writer4);
        FileWriter writer5 = new FileWriter("JiefuOut.txt");
        BufferedWriter bWriter5 = new BufferedWriter(writer5);
        FileWriter writer6 = new FileWriter("GuanxiyunsuanfuOut.txt");
        BufferedWriter bWriter6 = new BufferedWriter(writer6);


        String content= "";
        boolean flag = false;

        //readLine一行一行的读取
        while((content = bReader.readLine()) != null) {


            int count = 0;

            while (flag) {
                while (count < content.length()) {
                    if(content.charAt(count)=='*') {
                        count++;

                        if(count< content.length() && content.charAt(count)=='/') {
                            count++;
                            flag = false;
                        }
                    }
                    else count++;
                }
                break;
            }

            while (count < content.length()) {

                if (content.charAt(count) == ' ') {
                    count++;
                }

                else if (Isletter(content.charAt(count)) || content.charAt(count) == '_') {
                    String str = "";
                    str += content.charAt(count++);
                    while (count < content.length() && (Isletter(content.charAt(count)) || IsDigit(content.charAt(count)) || content.charAt(count)=='_')) {
                        str += content.charAt(count++);
                    }
                    if(ReserveWord.contains(str))
                    {
                        System.out.println(str);
                        bWriter.write("(" + "0" + "," + str + ")" + "\r\n");
                        bWriter1.write("(" + "0" + "," + str + ")" + "\r\n");
                        continue;
                    }
                    IDentifierTable.add(str);
                    System.out.println(str);
                    bWriter.write("(" + "1" + "," + str + ")" + "\r\n");
                    bWriter2.write("(" + "1" + "," + str + ")" + "\r\n");

                }

                else if(IsSuanshuyunsuanfu(content.charAt(count))) {
                    String str = "";
                    str += content.charAt(count++);
                    System.out.println(str);
                    bWriter.write("(" + "2" + "," + str + ")" + "\r\n");
                    bWriter3.write("(" + "2" + "," + str + ")" + "\r\n");
                }

                else if(IsDigit(content.charAt(count))) {
                    String str = "";
                    str += content.charAt(count++);
                    while(count < content.length() && IsDigit(content.charAt(count))) {
                        str += content.charAt(count++);
                    }
                    DigitBTable.add(str);
                    System.out.println(str);
                    bWriter.write("(" + "3" + "," + str + ")" + "\r\n");
                    bWriter4.write("(" + "3" + "," + str + ")" + "\r\n");
                }

                else if(IsJiefu(content.charAt(count))) {
                    String str = "";
                    str += content.charAt(count);
                    System.out.println(str);
                    bWriter.write("(" + "4" + "," + str + ")" + "\r\n");
                    bWriter5.write("(" + "4" + "," + str + ")" + "\r\n");
                    count++;

                }

                else if(IsGuanxiyunsuanfu(content.charAt(count))) {

                    String str = "";
                    if (content.charAt(count) == '<') {
                        str += content.charAt(count);
                        count++;
                        if(content.charAt(count)=='>' || content.charAt(count)=='=')
                        {
                            str += content.charAt(count);
                            count++;
                        }
                    }
                    else {
                        str += content.charAt(count);
                        count++;
                        if(content.charAt(count)=='=')
                        {
                            str += content.charAt(count++);
                        }
                    }
                    System.out.println(str);
                    bWriter.write("(" + "5" + "," + str + ")" + "\r\n");
                    bWriter6.write("(" + "5" + "," + str + ")" + "\r\n");
                }

                else if(content.charAt(count)=='/') {
                    String str = "";
                    str += content.charAt(count);
                    count++;
                    if(content.charAt(count)!='*' && content.charAt(count) !='/') {

                        bWriter.write("(" + "2" + "," + str + ")" + "\r\n");
                        bWriter3.write("(" + "2" + "," + str + ")" + "\r\n");

                    }
                    if(content.charAt(count)=='/') {
                        break;
                    }
                    if(content.charAt(count)=='*') {
                        count++;
                        flag = true;
                        while (count < content.length()) {
                            if(content.charAt(count)=='*') {
                                count++;

                                if(count< content.length() && content.charAt(count)=='/') {
                                    count++;
                                    flag = false;
                                }
                            }
                            else count++;
                        }
                    }
                }

                else {
                    String str = "";
                    str += content.charAt(count);
                    count++;
                    System.out.println(str);
                    bWriter.write("(" + "6" + "," + str + ")" + "\r\n");
                }
            }

        }
        reader.close();
        bReader.close();
        bWriter.close();
        writer.close();
        bWriter1.close();
        writer1.close();
        bWriter2.close();
        writer2.close();
        bWriter3.close();
        writer3.close();
        bWriter4.close();
        writer4.close();
        bWriter5.close();
        writer5.close();
        bWriter6.close();
        writer6.close();
    }

    public static void main(String[] args) throws IOException {
        read_write_File();
    }
}