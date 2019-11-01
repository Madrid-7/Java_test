public class User {
    String  username;
    String password;
    static int num = 0;
    public User() {
        num++;
    }
    public User(String name) {
        this.username = name;
        num++;
    }
    public User(String name, String password) {
        this.username = name;
        this.password = password;
        num++;
    }
    public void SetUser(String name, String password) {
        this.username=name;
        this.password=password;
    }

}
