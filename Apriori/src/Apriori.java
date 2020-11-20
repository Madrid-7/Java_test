import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Apriori {
    private final int minCount = 2;             //最小支持度

    private final double minConfidence = 0.7;    //最小置信度

    public Apriori() {
        super();
    }
    //扫描数据集，得出支持度以及项集
    public ArrayList<ArrayList<String>> scanData(ArrayList<ArrayList<String>> list,ArrayList<ArrayList<String>> data,ArrayList<ArrayList<String>> fcs,int n){
        ArrayList<String> line;
        ArrayList<String> tempLine;
        Iterator<ArrayList<String>> it = list.iterator();
        ArrayList<ArrayList<String>> css = new ArrayList<>();
        String str = null;
        Integer count = 0;
        boolean sign = false;
        while(it.hasNext()){
            line = it.next();
            Iterator<ArrayList<String>> it1 = data.iterator();
            while(it1.hasNext()){
                tempLine = it1.next();
                Iterator<String> iti =line.iterator();
                while(iti.hasNext()){
                    str = iti.next();
                    if(tempLine.contains(str)){
                        sign = true;
                    }else{
                        sign = false;
                        break;
                    }
                }
                if(sign){
                    count++;
                }
            }
            if(count>=this.minCount){

                line.add(count.toString());
                css.add(line);
                count = 0;
            }else{
                line.add(count.toString());
                fcs.add(line);
                count = 0;
            }
        }
        System.out.println("频繁"+(n+1)+"项集："+css);
        System.out.println("非频繁"+(n+1)+"项集："+fcs);
        return css;
    }
    //连接
    public ArrayList<ArrayList<String>> connection(ArrayList<ArrayList<String>> list,int n){
        Iterator<ArrayList<String>> it = list.iterator();
        ArrayList<ArrayList<String>> css = new ArrayList<>();
        ArrayList<String> line1 = new ArrayList<>();
        ArrayList<String> line2 = new ArrayList<>();
        ArrayList<String> tempLine = new ArrayList<>();

        String str1 = null;
        String str2 = null;
        int i= 0;
        int length = list.size();
        boolean sign = true;
        while(it.hasNext()){
            line1 = it.next();
            int j = i+1;
            i++;
            //求频繁2项集时
            if(n==1){
                while(j<length){
                    line2 = list.get(j);
                    str1 = line1.get(0);
                    str2 = line2.get(0);
                    tempLine.add(str1);
                    tempLine.add(str2);
                    css.add(tempLine);
                    j++;
                    tempLine = new ArrayList<>();
                    line2 = new ArrayList<>();
                }
                line1 = new ArrayList<>();
            }else{
                while(j<length){
                    line2 = list.get(j);
                    //比较前n-1项是否相等
                    for(int m = 0 ;m<n-1;m++){
                        if(!line1.get(m).equals(line2.get(m))){
                            sign = false;
                            break;
                        }
                    }
                    if(sign){
                        for(int m = 0 ;m<line1.size()-1;m++){
                            str1 = line1.get(m);
                            tempLine.add(str1);
                        }
                        str2 = line2.get(n-1);
                        tempLine.add(str2);
                        css.add(tempLine);
                    }
                    j++;
                    sign = true;
                    tempLine = new ArrayList<>();
                    line2 = new ArrayList<>();
                }
                line1 = new ArrayList<>();
            }
        }
        System.out.println("候选"+(n+1)+"项集"+css);
        return css;
    }
    //剪枝
    public ArrayList<ArrayList<String>> pruning(ArrayList<ArrayList<String>> list,ArrayList<ArrayList<String>> fcs){
        ArrayList<ArrayList<String>> css = new ArrayList<>();
        ArrayList<String> line = new ArrayList<>();
        ArrayList<String> line1 = new ArrayList<>();
        Iterator<ArrayList<String>> itf = fcs.iterator();
        String str = null;
        int length;
        int count = 0;
        while(itf.hasNext()){
            //取出一项非频繁项
            line = itf.next();
            length = line.size();

            Iterator<ArrayList<String>> itl = list.iterator();
            //使用取出的非频繁项比较所有候选项是否真包含该非频繁项
            while(itl.hasNext()){
                line1 = itl.next();
                //比较每一项
                Iterator<String> it = line.iterator();
                while(it.hasNext()){
                    str = it.next();
                    if(line1.contains(str)){
                        count++;
                    }
                    if(count == length)
                        break;
                }
                if(count==length){
                    itl.remove();
                    count = 0;
                }
                line1 =new ArrayList<>();
            }
            line = new ArrayList<>();
            count = 0;
        }
        css = list;
        fcs.clear();
        System.out.println("剪枝后："+css);
        return css;
    }
    //产生关联规则
    public void AssociationRules(ArrayList<ArrayList<String>> list,ArrayList<ArrayList<String>> data){
        double confidence;
        Iterator<ArrayList<String>> it = list.iterator();
        ArrayList<ArrayList<String>> css = new ArrayList<>();
        ArrayList<String> line = new ArrayList<>();
        ArrayList<String> line1 = new ArrayList<>();
        ArrayList<String> line2 = new ArrayList<>();
        ArrayList<String> tempLine = new ArrayList<>();
        double supportCountAnd = 0;
        double supportCountAlone = 0;
        String str =null;
        String str1 = null;
        String str2 = null;
        int length = 0;
        int lengthLine = 0;
        int count;
        int value = 0;
        Integer temp ;
        boolean sign = true;
        //求集合的所有子集（不包括空集，全集）
        while(it.hasNext()){
            line = it.next();
            line1 = line;
            Iterator<String> itl = line.iterator();
            count = 0;
            lengthLine = line.size();
            while(itl.hasNext()){
                if((count+1)==lengthLine){
                    str1 = itl.next();
                    temp =new Integer(str1);
                    supportCountAnd = temp.doubleValue();
                    itl.remove();
                }else{
                    itl.next();
                    count++;
                }
            }
            Set<Set<String>> result = new HashSet<Set<String>>();	//用来存放子集的集合
            length = line.size() ;
            int num = length==0 ? 0 : 1<<(length);	//2的n次方，若集合set为空，num为0；若集合set有4个元素，那么num为16.

            for(int i = 0; i < num; i++){
                Set<String> subSet = new HashSet<String>();
                int index = i;
                for(int j = 0; j < length; j++){
                    if((index & 1) == 1){		//每次判断index最低位是否为 1，为 1则把集合set的第j个元素放到子集中
                        subSet.add(line.get(j));
                    }
                    index >>= 1;		//右移一位
                }
                if(subSet.size()!=0&&subSet.size()!=length)
                    result.add(subSet);		//把子集存储起来
            }
            line= new ArrayList<>();
            Iterator<Set<String>> its = result.iterator();

            while(its.hasNext()){
                Set<String> tempSet = its.next();
                Iterator<String> itss = tempSet.iterator();
                while(itss.hasNext()){
                    str = itss.next();
                    line.add(str);
                }
                css.add(line);
                line = new ArrayList<>();
            }
            //求得子集为css，通过子集进行操作
            //求置信度
            //求子集的计数
            Iterator<ArrayList<String>> csit = css.iterator();
            while(csit.hasNext()){
                line = csit.next();
                Iterator<ArrayList<String>> dit = data.iterator();
                while(dit.hasNext()){
                    tempLine = dit.next();
                    Iterator<String> cslineIt = line.iterator();
                    while(cslineIt.hasNext()){
                        str = cslineIt.next();
                        if(!tempLine.contains(str)){
                            sign = false;
                            break;
                        }
                    }
                    if(sign){value++;}
                    sign = true;
                }
                temp = new Integer(value);
                supportCountAlone = temp.doubleValue();
                if((supportCountAnd/supportCountAlone)>=this.minConfidence){

                    Iterator<String> itste = line1.iterator();
                    while(itste.hasNext()){
                        str2 = itste.next();
                        if(!line.contains(str2))
                            line2.add(str2);
                    }
                    str2 = null;
                    System.out.print(line);
                    line = new ArrayList<>();
                    System.out.print("->");
                    System.out.print(line2);
                    line2 = new ArrayList<>();
                    System.out.print("  置信度:");
                    System.out.println(supportCountAnd/supportCountAlone);
                }
                line.add(temp.toString());
                value = 0;
            }
            css = new ArrayList<>();
        }
    }
}
