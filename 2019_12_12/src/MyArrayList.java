public class MyArrayList implements MyList {

    private static final int LENGTH_MAX = 10;

    private int[] elementData;

    private int size=0;

    public MyArrayList(){
        elementData=new int[LENGTH_MAX];
    }
    public MyArrayList(int length){
        elementData = new int[length];
    }

    @Override
    public boolean add(int data) {
        if(this.size == LENGTH_MAX) {
            System.out.println("插入失败.");
            return false;
        } else {
            elementData[size++]=data;
            return true;
        }
    }

    @Override
    public int get(int index) {
        try {
            this.rangeCheck(index);
        } catch (BoxIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        return elementData[index-1];
    }

    private void rangeCheck(int index) throws BoxIndexOutOfBoundsException {
        if(index<0||index>=size){
            //自定义一个异常 来说明问题
            throw new BoxIndexOutOfBoundsException("Index"+index+",Size"+size);
        }
    }
    @Override
    public int remove(int index) {
        try {
            this.rangeCheck(index);
        } catch (BoxIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

        int data = elementData[index-1];
        for(int i = index-1; i < size - 1; i++){
            elementData[i] = elementData[i+1];
        }

        elementData[--size] = 0;
        return data;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void show() {
        for(int i = 0; i < size; i++){
            System.out.print(elementData[i]+"  ");
        }
        System.out.println();
    }
}
