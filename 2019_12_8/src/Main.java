import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        System.out.println(map.get("作者"));
        System.out.println(map.getOrDefault("作者", "佚名"));
        System.out.println(map.containsKey("作者"));
        System.out.println(map.containsValue("佚名"));
        map.put("作者", "鲁迅");
        map.put("标题", "狂人日记");
        map.put("发表时间", "1918年");
        System.out.println(map.size());
        System.out.println(map.isEmpty());
        System.out.println(map.get("作者"));
        System.out.println(map.getOrDefault("作者", "佚名"));
        System.out.println(map.containsKey("作者"));
        System.out.println(map.containsValue("佚名"));
        for (Map.Entry<String, String> entry: map.entrySet()) {
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
    }

    public static void main1(String[] args) {
        Collection<String> collection = new ArrayList<>();
        collection.add("asdf");
        collection.add("2019");
        collection.size();
        collection.isEmpty();
        System.out.println(collection);
        collection.add("Java");
        for (String s: collection) {
            System.out.println(s);
        }

    }
}
