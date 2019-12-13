public interface MyList {
    boolean add(int data);
    int get(int index);
    int remove(int index);
    int size();
    void show();
}

class BoxIndexOutOfBoundsException extends Exception {
    public BoxIndexOutOfBoundsException(String message) {
        super(message);
    }
}
