import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
@SuppressWarnings("resource")
public class ReadData {

    public ReadData() {
        super();

    }
    //全部事物数据
    public ArrayList<ArrayList<String>> DataAll(String filePath)throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<ArrayList<String>> list = new ArrayList<>();
        ArrayList<String> line = new ArrayList<String>();
        String str = null;
        String s1 =null;
        String s =null;
        int length;
        String[] c;
        while(!(str=br.readLine()).equals("")){
            s =str.split(" ")[0];
            line.add(s);
            list.add(line);
            s1 = str.split(" ")[1];
            c= s1.split(",");
            length = s1.split(",").length;
            for(int i = 0 ; i<length; i++){
                line.add(c[i]);
            }
            //System.out.println(list);
            line = new ArrayList<>();
        }
        br.close();
        System.out.println("全部事务数据："+list);
        return list;
    }
    //产生候选C
    public  ArrayList<ArrayList<String>> CandidateSets(ArrayList<ArrayList<String>> list){
        //产生候选项
        ArrayList<String> line = new ArrayList<>();
        ArrayList<String> tLine = new ArrayList<>();
        ArrayList<ArrayList<String>> cs = new ArrayList<>();
        Iterator<ArrayList<String>> it = list.iterator();
        ArrayList<ArrayList<String>> css = new ArrayList<>();
        String temp = null;
        int sign =0;//sign==0表示读入一条事务的ID
        while(it.hasNext()){
            line = it.next();
            Iterator<String> it1 = line.iterator();
            while(it1.hasNext()){
                if(sign==0){
                    temp = it1.next();
                    sign=1;
                    continue;
                }
                temp = it1.next();
                tLine.add(temp);
                if(!cs.contains(tLine)){
                    cs.add(tLine);
                }
                tLine = new ArrayList<>();
            }
            sign =0;
            line = new ArrayList<>();
        }
        css = this.SortCandidateSetsIn(cs);
        cs = new ArrayList<>();
        cs = this.SortCandidateSetsOut(css);
        System.out.println("候选1项集："+cs);
        return cs;
    }
    //对候选项进行排序
    //1.候选项内排序
    public ArrayList<ArrayList<String>> SortCandidateSetsIn(ArrayList<ArrayList<String>> list){
        ArrayList<String> line = new ArrayList<>();
        ArrayList<String> tempLine = new ArrayList<>();
        Iterator<ArrayList<String>> it = list.iterator();
        ArrayList<ArrayList<String>> css = new ArrayList<>();
        String s1 = null;
        String s2 = null;
        while(it.hasNext()){
            line = it.next();
            //伪冒泡排序
            int length = line.size();
            //length!=0表示并没有全部遍历完
            while(length!=0){
                Iterator<String> iti = line.iterator();
                while(iti.hasNext()){
                    s1 = iti.next();
                    Iterator<String> itj = line.iterator();
                    while(itj.hasNext()){
                        s2 =itj.next();
                        if(s1.compareTo(s2)>=0){s1 = s2;}
                    }
                    tempLine.add(s1);
                    break;
                }
                Iterator<String> itf = line.iterator();
                while(itf.hasNext()){
                    if(itf.next().equals(s1)){
                        itf.remove();
                        length--;
                        break;
                    }
                }
            }
            css.add(tempLine);
            tempLine = new ArrayList<>();
        }

        return css;
    }
    //对候选项进行排序
    //2.候选项外排序
    public ArrayList<ArrayList<String>> SortCandidateSetsOut(ArrayList<ArrayList<String>> list){
        ArrayList<String> line = new ArrayList<>();
        ArrayList<String> tempLine = new ArrayList<>();
        ArrayList<ArrayList<String>> css = new ArrayList<>();
        int length = list.size();
        //length!=0表示并没有全部遍历完
        while(length!=0){
            Iterator<ArrayList<String>> iti = list.iterator();
            while(iti.hasNext()){
                line = iti.next();
                Iterator<ArrayList<String>> itj = list.iterator();
                while(itj.hasNext()){
                    tempLine = itj.next();
                    Iterator<String> it1 = line.iterator();
                    Iterator<String> it2 = tempLine.iterator();
                    while(it1.hasNext()){
                        if(it1.next().compareTo(it2.next())>0){
                            line = tempLine;
                            break;
                        }
                    }
                }
            }
            Iterator<ArrayList<String>> itj = list.iterator();
            while(itj.hasNext()){
                if(itj.next().equals(line)){
                    css.add(line);
                    itj.remove();
                    break;
                }
            }
            line = new ArrayList<>();
            tempLine = new ArrayList<>();
            length = length-1;
        }
        return css;
    }
}