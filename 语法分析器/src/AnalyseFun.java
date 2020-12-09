interface AnalyseFun {
    void Init();//初始化
    void getVnVt();//获取非终结符和终结符的集合
    void getFirst(Character c);//first集
    void getFirst(String s);//任意字符的first集
    void getFollow(char c);//follow集
    void createTable();//构造表
    void analyzeLL();//LL(1)分析过程
    String find(char X, char a);//查找
    void insert(char X, char a,String s);//插入
    void displayLL();//输出分析过程
    void ouput();//其他输出信息
}