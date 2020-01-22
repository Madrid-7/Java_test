package Gui;

import java.awt.*;
import java.awt.event.*;
public class Figure {
    Path path;
    Place[] maze=null;
    Button[] button=null;
    boolean[] isPath=null;
    class MazeGameFigure extends Frame implements ActionListener{
        public MazeGameFigure(){
            super("迷宫游戏");
        }
        public void init(){
            this.setSize(250, 250);
            this.setBackground(Color.WHITE);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            this.setLayout(new GridLayout(4,1));
            Label welcom=new Label("欢迎进入迷宫游戏!");
            Button start=new Button("开始游戏");
            Button set=new Button("游戏设置");
            Button end=new Button("退出游戏");
            start.setBackground(Color.WHITE);
            set.setBackground(Color.WHITE);
            end.setBackground(Color.WHITE);
            add(welcom);
            add(start);
            add(set);
            add(end);
            start.addActionListener(this);
            set.addActionListener(this);
            end.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("开始游戏")){
                MazeFigure mazeFigure=new MazeFigure();
                mazeFigure.init();
                dispose();
            }
            if(e.getActionCommand().equals("游戏设置")){
                MazeSetFigure mazeSetFigure=new MazeSetFigure();
                mazeSetFigure.init();
                dispose();
            }
            if(e.getActionCommand().equals("退出游戏")){
                dispose();
            }
        }
    }
    class MazeFigure extends Frame implements ActionListener{
        public MazeFigure(){
            super("迷宫");
        }
        public void init(){
            this.setSize(500, 500);
            this.setBackground(Color.BLACK);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            this.setLayout(new GridLayout(path.getSize(),path.getSize()));
            maze=path.getMaze();
            int entrance=path.getEntrance();
            int exit=path.getExit();
            button=new Button[maze.length];
            for(int i=1;i<maze.length;i++){
                if(maze[i].getWall()==0){
                    button[i]=new Button("");
                    button[i].setActionCommand("路");
                    button[i].setBackground(Color.WHITE);
                }
                if(maze[i].getWall()==1){
                    button[i]=new Button("墙");
                    button[i].setActionCommand("墙");
                    button[i].setBackground(Color.LIGHT_GRAY);
                }
            }
            button[entrance].setLabel("入口");
            button[exit].setLabel("出口");
            for(int i=1;i<button.length;i++){
                button[i].addActionListener(this);
                add(button[i]);
            }
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        private boolean isComplete(){
            isPath=path.getPath();
            for(int i=1;i<isPath.length;i++){
                if(isPath[i]&&button[i].getBackground()!=Color.RED){
                    return false;
                }
            }
            return true;
        }
        public void actionPerformed(ActionEvent e){
            Button button=(Button)e.getSource();
            if(button.getActionCommand().equals("路")){
                if(button.getBackground()==Color.WHITE){
                    button.setBackground(Color.RED);
                }else if(button.getBackground()==Color.RED){
                    button.setBackground(Color.WHITE);
                }
            }
            if(isComplete()){
                CongratulationFigure congratulationFigure=new CongratulationFigure();
                congratulationFigure.init();
                this.dispose();
            }
        }
    }
    class MazeSetFigure extends Frame implements ActionListener ,TextListener{
        String newSize,newEntrance,newExit;
        TextField setMaze,setEntrance,setExit;
        int size,entrance,exit;
        public MazeSetFigure(){
            super("迷宫设置");
        }
        public void init(){
            this.setSize(250, 150);
            this.setBackground(Color.WHITE);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            GridLayout layout=new GridLayout(4,2);
            this.setLayout(layout);
            Label size=new Label("迷宫规模");
            Label entrance=new Label("迷宫入口");
            Label exit=new Label("迷宫出口");
            Button menu=new Button("返回菜单");
            Button set=new Button("设置完成");
            setMaze= new TextField("10");
            setEntrance= new TextField("左上角");
            setExit= new TextField("右下角");
            add(size);
            add(setMaze);
            add(entrance);
            add(setEntrance);
            add(exit);
            add(setExit);
            add(menu);
            add(set);
            menu.addActionListener(this);
            set.addActionListener(this);
            setMaze.addTextListener(this);
            setEntrance.addTextListener(this);
            setExit.addTextListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("返回菜单")){
                dispose();
                Figure figure=new Figure();
                figure.init();
            }
            if(e.getActionCommand().equals("设置完成")){
                boolean isSizeReasonable=true;
                boolean isEntranceReasonable=true;
                boolean isExitReasonable=true;
                newSize=setMaze.getText();
                newEntrance=setEntrance.getText();
                newExit=setExit.getText();
                try{
                    size=Integer.parseInt(newSize);
                }catch(Exception ex){
                    isSizeReasonable=false;
                }
                if(isSizeReasonable==true){
                    if(newEntrance.equals("左上角")){
                        entrance=1;
                    }else if(newEntrance.equals("右上角")){
                        entrance=size;
                    }else if(newEntrance.equals("左下角")){
                        entrance=size*(size-1)+1;
                    }else if(newEntrance.equals("右下角")){
                        entrance=size*size;
                    }else{
                        isEntranceReasonable=false;
                    }

                    if(newExit.equals("左上角")){
                        exit=1;
                    }else if(newExit.equals("右上角")){
                        exit=size;
                    }else if(newExit.equals("左下角")){
                        exit=size*(size-1)+1;
                    }else if(newExit.equals("右下角")){
                        exit=size*size;
                    }else{
                        isExitReasonable=false;
                    }

                    if(isEntranceReasonable==true&&isExitReasonable==true){
                        if(entrance==exit){
                            isEntranceReasonable=false;
                            isExitReasonable=false;
                        }
                    }
                }
                if(isSizeReasonable==true&&isEntranceReasonable==true&&isExitReasonable==true){
                    dispose();
                    Figure figure=new Figure(size,entrance,exit);
                    figure.init();
                }else{
                    SetErrorFigure setErrorFigure=new SetErrorFigure();
                    setErrorFigure.init();
                    dispose();
                }
            }
        }
        public void textValueChanged(TextEvent e){

        }
    }
    class CongratulationFigure extends Frame implements ActionListener{
        public CongratulationFigure(){
            super("恭喜");
        }
        public void init(){
            this.setSize(220, 100);
            this.setBackground(Color.WHITE);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            this.setLayout(new GridLayout(2,1));
            Label text=new Label("恭喜您成功走出迷宫!");
            Button button=new Button("确认");
            button.setBackground(Color.WHITE);
            add(text);
            add(button);
            button.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("确认")){
                dispose();
                Figure figure=new Figure();
                figure.init();
            }
        }
    }
    class SetErrorFigure extends Frame implements ActionListener{
        public SetErrorFigure(){
            super("错误");
        }
        public void init(){
            this.setSize(230, 100);
            this.setBackground(Color.WHITE);
            Toolkit kit =Toolkit.getDefaultToolkit();
            Dimension screenSize=kit.getScreenSize();
            int screenWidth=screenSize.width;
            int screenHeight=screenSize.height;
            int windowWidth=this.getWidth();
            int windowHeight=this.getHeight();
            this.setLocation((screenWidth-windowWidth)/2,(screenHeight-windowHeight)/2 );
            this.setLayout(new GridLayout(2,1));
            Label text=new Label("您输入的数据不合理,设置失败!");
            Button button=new Button("确认");
            button.setBackground(Color.WHITE);
            add(text);
            add(button);
            button.addActionListener(this);
            addWindowListener(new closeWin());
            this.setVisible(true);
        }
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand().equals("确认")){
                dispose();
                Figure figure=new Figure();
                figure.init();
            }
        }
    }
    class closeWin extends WindowAdapter{
        public void windowClosing(WindowEvent e){
            Window w=e.getWindow();
            w.dispose();
        }
    }

    public Figure(){
        path=new Path();
    }

    public Figure(int size,int entrance,int exit){
        path=new Path(size,entrance,exit);
    }

    public void init(){
        MazeGameFigure mazeGameFigure=new MazeGameFigure();
        mazeGameFigure.init();
    }

}

