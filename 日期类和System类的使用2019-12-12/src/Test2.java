public class Test2 {
    public static void main(String[] args) {

        System.out.println("用户名:" + System.getProperty("user.name"));
        System.out.println("用户目录:" + System.getProperty("user.home"));
        System.out.println("操作系统名:" + System.getProperty("os.name"));
        System.out.println("操作系统架构:" + System.getProperty("os.arch"));
        System.out.println("操作系统版本:" + System.getProperty("os.version"));
        System.out.println("文件分隔符:" + System.getProperty("file.separator"));
        System.out.println("路径分隔符:" + System.getProperty("path.separator"));

    }
}
