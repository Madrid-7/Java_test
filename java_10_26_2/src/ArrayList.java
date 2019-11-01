import java.util.Arrays;

public class ArrayList {
    private int[] elem;
    private int usedSize;
    private final int CAPACITY = 2;

    public ArrayList() {
        this.elem = new int [CAPACITY];
        this.usedSize = 0;
    }

    // 打印顺序表
    public void disPlay() {
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i] +" ");
        }
        System.out.println( );
    }

    private boolean isFull() {
        if(this.usedSize ==this.elem.length)
            return true;
        else
            return false;
    }
    // 在 pos 位置新增元素
    public void add(int pos, int data) {
        if(pos < 0 || pos > this.usedSize) {
            System.out.println("error");
        } else {
            if(isFull()) {
                this.elem = Arrays.copyOf(this.elem, this.elem.length*2);
            }
            for (int i = this.usedSize - 1; i >=pos; i--) {
                this.elem[i+1] = this.elem[i];
            }
            this.elem[pos] = data;
            this.usedSize++;
        }
    }

    // 判定是否包含某个元素
    public boolean contains(int toFind) {
        for (int i = 0; i < this.usedSize; i++) {
            if(this.elem[i] == toFind)
                return true;
        }
        return false;
    }

    // 查找某个元素对应的位置
    public int search(int toFind) {
        for (int i = 0; i < this.usedSize; i++) {
            if(this.elem[i] == toFind)
                return i;
        }
        return -1; }
}
