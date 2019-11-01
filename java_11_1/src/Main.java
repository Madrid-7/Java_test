public class Main {
    public static void main(String[] args) {
        User user1 = new User();
        User user2 = new User("zhangsan");
        User user3 = new User("lisi", "12345");
        user1.SetUser("wangwu", "54321");
        System.out.println(user1.username + " " +user1.password);
        System.out.println(user2.username);
        System.out.println(user3.username + " " + user3.password);
        System.out.println(User.num);
    }
}
