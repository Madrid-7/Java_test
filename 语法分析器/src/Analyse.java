
import java.io.*;
import java.util.*;

public class Analyse implements AnalyseFun {
    /**
     * 几个比较重要的参数
     * @param MyfirstSet 终结符的first集合
     * @param MyfirstSetX 任意符号的first集合
     * @param start 文法的开始符
     * @param MyfollowSet follow集
     * @param VnSet 终结符的集合
     * @param VtSet 终结符的集合
     * @param inputStrings 文法拓展的输入
     * @param outputSet 输出的集合
     * @param table 分析表
     * @param MyAnaStatck 分析栈
     * @param strInput 需要使用预测分析器的输入串
     * @param MyAction 分析动作
     * @param Index 分析的索引值
     */
    //从文件中读入时后台无法识别空串ε，因此用符号&代替空串ε

    //first集是终结符的first集
    protected HashMap<Character, HashSet<Character>> MyfirstSet = new HashMap<>();
    //firstX集是指任意符号串的first集
    protected HashMap<String, HashSet<Character>> MyfirstSetX = new HashMap<>();
    //文法的开始符
    protected Character start = 'S';
    //follow集
    protected HashMap<Character, HashSet<Character>> MyfollowSet = new HashMap<>();

    //存储非终结符的结合
    protected HashSet<Character> VnSet = new HashSet<>();
    //存储终结符的结合
    protected HashSet<Character> VtSet = new HashSet<>();
    protected HashMap<Character, ArrayList<String>> outputSet = new HashMap<>();
    //定义分析表
    protected String [][] table;
    //分析栈
    protected Stack<Character> MyAnaStatck = new Stack<>();
    //定义要分析的输入串
    protected String strInput = "(a,a)$";
    //对动作的初始化
    protected String MyAction = "";
    int Index = 0;

    //从文件中读入
    protected String[] inputStrings = getSource();

    protected Analyse() {
    }

    public static void main(String[] args){
        Analyse test = new Analyse();

        test.getVnVt();
        test.Init();
        test.createTable();
        test.analyzeLL();
        test.ouput();

    }

    //从文件中读入文法的方法
    public  static  String[] getSource(){
        StringBuffer temp = new StringBuffer();
        FileInputStream fis= null;
        try {
            fis = new FileInputStream("Data.txt");
            byte[] buff=new byte[1024];
            int len=0;
            while((len=fis.read(buff))!=-1){
                temp.append(new String(buff,0,len));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return temp.toString().split("\n");
    }

    //初始化
    @Override
    public void Init() {
        for (String s1 : inputStrings) {
            //"S->a",
            //"S->^",
            //"S->(T)",
            //"T->SU",
            //"U->,SU",
            //"U->&"
            String[] str = s1.split("->");//通过符号“->”分隔每行的文法
            char c = str[0].charAt(0);//取得左边的非终结符
            ArrayList<String> list = outputSet.containsKey(c) ? outputSet.get(c) : new ArrayList<>();
            list.add(str[1]);//添加文法右边的内容到集合中
            outputSet.put(c, list);
        }
        //求非终结符的first集
        for (Character c1 : VnSet)
            getFirst(c1);

        for (Character c2 : VnSet) {
            ArrayList<String> l = outputSet.get(c2);
            for (String s2 : l)
                getFirst(s2);
        }
        //求出follow集
        getFollow(start);
        for (Character c3 : VnSet) {
            getFollow(c3);
        }
    }

    @Override
    public void getVnVt() {//取出终结符和非终结符的集合
        for (String s3 : inputStrings) {
            String[] str = s3.split("->");
            VnSet.add(str[0].charAt(0));
        }
        for (String s4 : inputStrings) {
            String[] str = s4.split("->");
            String right = str[1];
            for (int i = 0; i < right.length(); i++)
                if (!VnSet.contains(right.charAt(i)))
                    VtSet.add(right.charAt(i));
        }
    }

    @Override
    public void getFirst(Character c) {
        ArrayList<String> list = outputSet.get(c);
        HashSet<Character> set = MyfirstSet.containsKey(c) ? MyfirstSet.get(c) : new HashSet<Character>();
        // c为终结符 直接添加
        if (VtSet.contains(c)) {
            set.add(c);
            MyfirstSet.put(c, set);
            return;
        }
        // c为非终结符 处理其每条产生式
        for (String s5 : list) {
            // c 推出空串 直接添加
            if (s5 == Character.toString('&')) {
                set.add('&');
            }
            // X -> Y1Y2Y3… 情况
            else {
                // 从左往右扫描生成式右部
                int i = 0;
                while (i < s5.length()) {
                    char tn = s5.charAt(i);
                    //先处理防止未初始化
                    getFirst(tn);
                    HashSet<Character> tvSet = MyfirstSet.get(tn);
                    // 将其first集加入左部
                    for (Character tmp : tvSet)
                        set.add(tmp);
                    // 若包含空串 处理下一个符号
                    if (tvSet.contains('&'))
                        i++;
                        // 否则退出 处理下一个产生式
                    else
                        break;
                }
            }
        }
        MyfirstSet.put(c, set);
    }

    @Override
    public void getFirst(String s) {
        HashSet<Character> set = (MyfirstSetX.containsKey(s))? MyfirstSetX.get(s) : new HashSet<Character>();
        // 从左往右扫描该式
        int i = 0;
        while (i < s.length()) {
            char tn = s.charAt(i);
            HashSet<Character> tvSet = MyfirstSet.get(tn);
            // 将其非空 first集加入左部
            for (Character tmp : tvSet)
                if(tmp != '&')
                    set.add(tmp);
            // 若包含空串 处理下一个符号
            if (tvSet.contains('&'))
                i++;
                // 否则结束
            else
                break;
            // 到了尾部 即所有符号的first集都包含空串 把空串加入
            if (i == s.length()) {
                set.add('&');
            }
        }
        MyfirstSetX.put(s, set);
    }

    @Override
    public void getFollow(char ch) {
        ArrayList<String> list = outputSet.get(ch);
        HashSet<Character> setA = MyfollowSet.containsKey(ch) ? MyfollowSet.get(ch) : new HashSet<Character>();
        //如果是开始符 添加 $
        if (ch == start) {
            setA.add('$');
        }
        //查找输入的所有产生式，确定c的后跟 终结符
        for (Character cha : VnSet) {
            ArrayList<String> l = outputSet.get(cha);
            for (String s : l)
                for (int i = 0; i < s.length(); i++)
                    if (s.charAt(i) == ch && i + 1 < s.length() && VtSet.contains(s.charAt(i + 1)))
                        setA.add(s.charAt(i + 1));
        }
        MyfollowSet.put(ch, setA);
        //处理c的每一条产生式
        for (String s : list) {
            int i = s.length() - 1;
            while (i >= 0 ) {
                char tn = s.charAt(i);
                //只处理非终结符
                if(VnSet.contains(tn)){
                    // 都按 A->αB&  形式处理
                    //若&不存在   followA 加入 followB
                    //若&存在，把&的非空first集  加入followB
                    //若&存在  且 first(&)包含空串   followA 加入 followB

                    //若&存在
                    if (s.length() - i - 1 > 0) {
                        String right = s.substring(i + 1);
                        //非空first集 加入 followB
                        HashSet<Character> setF = null;
                        if( right.length() == 1 && MyfirstSet.containsKey(right.charAt(0))) {
                            setF = MyfirstSet.get(right.charAt(0));
                        }
                        else{
                            if(!MyfirstSetX.containsKey(right)){
                                HashSet<Character> set = new HashSet<Character>();
                                MyfirstSetX.put(right, set);
                            }
                            setF = MyfirstSetX.get(right);
                        }
                        HashSet<Character> setX = MyfollowSet.containsKey(tn) ? MyfollowSet.get(tn) : new HashSet<Character>();
                        for (Character cha : setF) {
                            if (cha != '&') {
                                setX.add(cha);
                            }
                        }
                        MyfollowSet.put(tn, setX);

                        // 若first(&)包含&   followA 加入 followB
                        if(setF.contains('&')){
                            if(tn != ch){
                                HashSet<Character> setB = MyfollowSet.containsKey(tn) ? MyfollowSet.get(tn) : new HashSet<Character>();
                                for (Character cha : setA) {
                                    setB.add(cha);
                                }
                                MyfollowSet.put(tn, setB);
                            }
                        }
                    }
                    //若&不存在   followA 加入 followB
                    else{
                        // A和B相同不添加
                        if(tn != ch){
                            HashSet<Character> setB = MyfollowSet.containsKey(tn) ? MyfollowSet.get(tn) : new HashSet<Character>();
                            for (Character cha : setA)
                                setB.add(cha);
                            MyfollowSet.put(tn, setB);
                        }
                    }
                    i--;
                }
                //如果是终结符往前扫描  如 A->aaaBCDaaaa  此时&为 CDaaaa
                else i--;
            }
        }
    }

    @Override
    public void createTable() {//构造预测分析表的格式
        //终结符数组
        Object[] VtArray = VtSet.toArray();
        //非终结符数组
        Object[] VnArray = VnSet.toArray();
        // 预测分析表初始化
        table = new String[VnArray.length + 1][VtArray.length + 1];
        table[0][0] = "Vn/Vt";
        //初始化首行首列
        for (int i = 0; i < VtArray.length; i++)
            table[0][i + 1] = (VtArray[i].toString().charAt(0) == '&') ? "$" : VtArray[i].toString();
        for (int i = 0; i < VnArray.length; i++)
            table[i + 1][0] = VnArray[i] + "";
        //全部置error
        for (int i = 0; i < VnArray.length; i++)
            for (int j = 0; j < VtArray.length; j++)
                table[i + 1][j + 1] = "error";

        //插入生成式
        for (char A : VnSet) {
            ArrayList<String> l = outputSet.get(A);
            for(String s : l){
                HashSet<Character> set = MyfirstSetX.get(s);
                for (char a : set)
                    insert(A, a, s);
                if(set.contains('&'))  {//如果包含&
                    HashSet<Character> setFollow = MyfollowSet.get(A);
                    if(setFollow.contains('$'))//判断follow集中是否包含$
                        insert(A, '$', s);
                    for (char b : setFollow)
                        insert(A, b, s);
                }
            }
        }
    }

    @Override
    public void analyzeLL() {
        System.out.println("-------------------1.LL分析过程-------------------");
        System.out.println("|   分析栈        输入串     动作|  ");
        MyAnaStatck.push('$');
        MyAnaStatck.push('S');
        displayLL();//调用分析过程方法
        char X = MyAnaStatck.peek();
        while (X != '$') {
            char a = strInput.charAt(Index);
            if (X == a) {
                MyAction = "match " + MyAnaStatck.peek();
                MyAnaStatck.pop();
                Index++;
            } else if (VtSet.contains(X))
                return;
            else if (find(X, a).equals("error"))
                return;
            else if (find(X, a).equals("&")) {
                MyAnaStatck.pop();
                MyAction = X + "->&";
            } else {
                String str = find(X, a);
                if (str != "") {
                    MyAction = X + "->" + str;
                    MyAnaStatck.pop();
                    int len = str.length();
                    for (int i = len - 1; i >= 0; i--)
                        MyAnaStatck.push(str.charAt(i));
                } else {
                    System.out.println("error at '" + strInput.charAt(Index) + " in " + Index);
                    return;
                }
            }
            X = MyAnaStatck.peek();
            displayLL();
        }
        System.out.println("LL(1)文法分析成功！");
        System.out.println("-------------------LL分析过程-------------------");
    }

    @Override
    public String find(char X, char a) {
        for (int i = 0; i < VnSet.size() + 1; i++) {
            if (table[i][0].charAt(0) == X)
                for (int j = 0; j < VtSet.size() + 1; j++) {
                    if (table[0][j].charAt(0) == a)
                        return table[i][j];
                }
        }
        return "";
    }

    @Override
    public void insert(char X, char a, String s) {//插入
        if(a == '&')
            a = '$';
        for (int i = 0; i < VnSet.size() + 1; i++) {
            if (table[i][0].charAt(0) == X)
                for (int j = 0; j < VtSet.size() + 1; j++) {
                    if (table[0][j].charAt(0) == a){
                        table[i][j] = s;
                        return;
                    }
                }
        }
    }

    @Override
    public void displayLL() {// 对输入串(a,a)$ 输出 LL(1)分析过程
        Stack<Character> s = MyAnaStatck;
        System.out.printf("%23s", s );//输出第一列：分析栈
        System.out.printf("%13s", strInput.substring(Index));//输出第二列：输入串
        System.out.printf("%10s", MyAction);//输出第三列：动作
        System.out.println();
    }

    @Override
    public void ouput() {
        // 输出终结符的first集
        System.out.println("-------------------2.first集-------------------");
        for (Character c : VnSet) {
            HashSet<Character> set = MyfirstSet.get(c);
            System.out.printf("%10s",c + "  ->   ");
            for (Character cha : set)
                System.out.print(cha+" ");
            System.out.println();
        }
        System.out.println("-------------------first集-------------------");
        // 输出任意符号串的first集
        System.out.println("-------------------firstX集-------------------");
        Set<String> setStr =  MyfirstSetX.keySet();
        for (String s : setStr) {
            HashSet<Character> set = MyfirstSetX.get(s);
            System.out.printf("%10s",s + "  ->   ");
            for (Character cha : set)
                System.out.print(cha+" ");
            System.out.println();
        }
        System.out.println("-------------------firstX集-------------------");
        // 输出follow集
        System.out.println("-------------------3.follow集-------------------");

        for (Character c : VnSet) {
            HashSet<Character> set = MyfollowSet.get(c);
            System.out.print("Follow " + c + " : ");
            for (Character cha : set)
                System.out.print(cha+" ");
            System.out.println();
        }
        System.out.println("-------------------follow集-------------------");
        //构造LL1文法预测分析表
        System.out.println("-------------------4.LL1预测分析表-------------------");

        for (int i = 0; i < VnSet.size() + 1; i++) {
            for (int j = 0; j < VtSet.size() + 1; j++) {
                System.out.printf("%8s", table[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("-------------------LL1预测分析表-------------------");
    }
}
