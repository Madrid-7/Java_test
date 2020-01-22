package Gui;

public class CreateMaze {
    //定义迷宫规模
    private int size;
    //定义迷宫的入口和出口
    private int entrance,exit;
    //用一维数组表示迷宫，0号下标位置空出
    private Place[] maze=null;
    //设置迷宫中每一个格子的方向
    private void setDirections(Place[] maze){
        for(int i=1;i<=size*size;i++){
            if(i%size!=0&&maze[i+1].getWall()==0&&maze[i+1]!=null){
                maze[i].setEast(maze[i+1]);
            }
            if(i<=size*(size-1)&&maze[i+size].getWall()==0&&maze[i+size]!=null){
                maze[i].setSouth(maze[i+size]);
            }
            if(i%size!=1&&maze[i-1].getWall()==0&&maze[i-1]!=null){
                maze[i].setWest(maze[i-1]);
            }
            if(i>size&&maze[i-size].getWall()==0&&maze[i-size]!=null){
                maze[i].setNorth(maze[i-size]);
            }
        }
    }

    public CreateMaze(){
        this.size=10;
        this.entrance=1;
        this.exit=this.size*this.size;
    }

    public CreateMaze(int size,int entrance,int exit){
        this.size=size;
        this.entrance=entrance;
        this.exit=exit;
    }

    public Place[] getMaze() {
        maze=new Place[size*size+1];
        for(int i=1;i<=size*size;i++){
            maze[i]=new Place((int)(Math.random()*2));
            maze[i].setIndex(i);
        }
        setDirections(maze);
        return maze;
    }
    public int getEntrance() {
        return entrance;
    }
    public void setEntrance(int entrance) {
        this.entrance = entrance;
    }
    public int getExit() {
        return exit;
    }
    public void setExit(int exit) {
        this.exit = exit;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }

}
