class XxxError extends Exception {
    public XxxError(String message) {
        super(message);
    }
}

public class Main {

    public static void  throwError() throws XxxError{
        throw new XxxError("Xxx发生异常");
    }

    public static void main(String[] args) {
        try {
            throwError();
        } catch (XxxError xxxError) {
            xxxError.printStackTrace();
        } finally {
            System.out.println("run there");
        }
    }
}
