package Gui;

public class Path {
    //调用创建迷宫类
    CreateMaze newMaze;
    //保存迷宫路径
    boolean[] path;
    //保存合格迷宫
    Place[] maze=null;
    int entrance;
    int exit;
    private int searchPathNumber(){
        maze=newMaze.getMaze();
        int pathAll=0;
        //保存当前路径
        Place [][] path=new Place [maze.length][];
        for(int i=1;i<path.length;i++){
            path [i] = new Place [5];
        }
        //当前路径数组下标
        int pathTop=0;
        //当前位置的下一位置的可能数下标
        int [] top=new int [maze.length];
        for(int i=1;i<top.length;i++){
            top[i]=-1;
        }
        //寻找迷宫路径数
        if(maze[entrance].getWall()==0){
            pathTop++;
            top[pathTop]++;
            path[pathTop][top[pathTop]]=maze[entrance];
            while(pathTop>0){
                //判断当前位置是否为结束位置，是，保存迷宫路径，退回上一位置，否，寻找下一不重复位置
                if(path[pathTop][0]==maze[exit]){
                    pathAll++;
                    top[pathTop]--;
                    pathTop--;
                }else if(!path[pathTop][top[0]].isSearch()){//寻找当前位置的下一位置的可能数
                    if(path[pathTop][0].getEast()!=null&&path[pathTop][0].getEast()!=path[pathTop][0].getLast()&&!path[pathTop][0].getEast().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getEast();
                    }
                    if(path[pathTop][0].getSouth()!=null&&path[pathTop][0].getSouth()!=path[pathTop][0].getLast()&&!path[pathTop][0].getSouth().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getSouth();
                    }
                    if(path[pathTop][0].getWest()!=null&&path[pathTop][0].getWest()!=path[pathTop][0].getLast()&&!path[pathTop][0].getWest().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getWest();
                    }
                    if(path[pathTop][0].getNorth()!=null&&path[pathTop][0].getNorth()!=path[pathTop][0].getLast()&&!path[pathTop][0].getNorth().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getNorth();
                    }
                    path[pathTop][0].setSearch(true);
                }//当前位置的下一位置的所有可能依次查询，无下一位置则回退到上一位置
                if(top[pathTop]==0){
                    path[pathTop][0].setLast(null);
                    path[pathTop][0].setSearch(false);
                    top[pathTop]--;
                    pathTop--;
                }else{
                    pathTop++;
                    top[pathTop]++;
                    path[pathTop][0]=path[pathTop-1][top[pathTop-1]--];
                    path[pathTop][0].setLast(path[pathTop-1][0]);
                }
            }
        }
        return pathAll;
    }

    private void setPath(){
        //保存当前路径
        Place [][] path=new Place [maze.length][];
        for(int i=1;i<path.length;i++){
            path [i] = new Place [5];
        }
        //当前路径数组下标
        int pathTop=0;
        //当前位置的下一位置的可能数下标
        int [] top=new int [maze.length];
        for(int i=1;i<top.length;i++){
            top[i]=-1;
        }
        //寻找迷宫路径数
        if(maze[entrance].getWall()==0){
            pathTop++;
            top[pathTop]++;
            path[pathTop][top[pathTop]]=maze[entrance];
            while(pathTop>0){
                //判断当前位置是否为结束位置，是，保存迷宫路径，退回上一位置，否，寻找下一不重复位置
                if(path[pathTop][0]==maze[exit]){
                    for(int i=1;i<=pathTop;i++){
                        this.path[path[i][0].getIndex()]=true;
                    }
                    top[pathTop]--;
                    pathTop--;
                    break;
                }else if(!path[pathTop][top[0]].isSearch()){//寻找当前位置的下一位置的可能数
                    if(path[pathTop][0].getEast()!=null&&path[pathTop][0].getEast()!=path[pathTop][0].getLast()&&!path[pathTop][0].getEast().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getEast();
                    }
                    if(path[pathTop][0].getSouth()!=null&&path[pathTop][0].getSouth()!=path[pathTop][0].getLast()&&!path[pathTop][0].getSouth().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getSouth();
                    }
                    if(path[pathTop][0].getWest()!=null&&path[pathTop][0].getWest()!=path[pathTop][0].getLast()&&!path[pathTop][0].getWest().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getWest();
                    }
                    if(path[pathTop][0].getNorth()!=null&&path[pathTop][0].getNorth()!=path[pathTop][0].getLast()&&!path[pathTop][0].getNorth().isSearch()){
                        path[pathTop][++top[pathTop]]=path[pathTop][0].getNorth();
                    }
                    path[pathTop][0].setSearch(true);
                }//当前位置的下一位置的所有可能依次查询，无下一位置则回退到上一位置
                if(top[pathTop]==0){
                    path[pathTop][0].setLast(null);
                    path[pathTop][0].setSearch(false);
                    top[pathTop]--;
                    pathTop--;
                }else{
                    pathTop++;
                    top[pathTop]++;
                    path[pathTop][0]=path[pathTop-1][top[pathTop-1]--];
                    path[pathTop][0].setLast(path[pathTop-1][0]);
                }
            }
        }
    }

    private void searchPath(){
        while(true){
            if(searchPathNumber()==1){
                setPath();
                break;
            }
        }
    }

    public Path(){
        newMaze=new CreateMaze();
        path=new boolean [newMaze.getSize()*newMaze.getSize()+1];
        this.entrance=newMaze.getEntrance();
        this.exit=newMaze.getExit();
    }

    public Path(int size,int entrance,int exit){
        newMaze=new CreateMaze(size,entrance,exit);
        path=new boolean [newMaze.getSize()*newMaze.getSize()+1];
        this.entrance=newMaze.getEntrance();
        this.exit=newMaze.getExit();
    }

    public Place[] getMaze() {
        searchPath();
        return maze;
    }

    public int getSize(){
        return newMaze.getSize();
    }

    public int getEntrance() {
        return entrance;
    }

    public int getExit() {
        return exit;
    }

    public boolean[] getPath() {
        return path;
    }

    public CreateMaze getNewMaze() {
        return newMaze;
    }

}


