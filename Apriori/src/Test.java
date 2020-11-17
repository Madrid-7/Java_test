import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) throws IOException {
        String filePath ="Data.txt";
        ArrayList<ArrayList<String>> fcs = new ArrayList<>();
        ArrayList<ArrayList<String>> ks = new ArrayList<>();
        ArrayList<ArrayList<String>> conns = new ArrayList<>();
        ArrayList<ArrayList<String>> cs = new ArrayList<>();
        ReadData rs =new ReadData();
        Apriori apri = new Apriori();
        //得到整个数据集 格式为[[T100, I1, I2, I5], .....]
        ArrayList<ArrayList<String>> data = rs.DataAll(filePath);
        //得到初始C候选
        cs = rs.CandidateSets(data);
        //候选1项集扫描计数
        ks = apri.scanData(cs, data,fcs,0);
        //产生频繁i+1项集
        for(int i = 1;i<3;i++){

            //连接步
            conns = apri.connection(ks,i);
            //剪枝步
            cs = apri.pruning(conns, fcs);
            //候选i+1项集扫描计数
            ks = apri.scanData(cs, data,fcs,i);
        }
        //产生关联规则
        apri.AssociationRules(ks,data);

    }

}
