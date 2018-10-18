package com.example.zengtingwei.pacman;

import android.graphics.Path;

public class GameState {
    static boolean win = true;
    public GameState(){

    }
    static public double evaluation(int agent_x,int agent_y,int pac_x,int pac_y){
        double dis = Math.sqrt(Math.pow((agent_y-pac_y),2)+Math.pow((agent_x-pac_x),2));
        int x_min = agent_x<pac_x?agent_x:pac_x;
        int x_max = agent_x>pac_x?agent_x:pac_x;
        int y_min = agent_y<pac_y?agent_y:pac_y;
        int y_max = agent_y>pac_y?agent_y:pac_y;
//        for(int i = x_min;i<=x_max;i++){
//            if(Layout.wall[][])
//        }
        return dis;
    }
    static public int getBeanNum(Layout map){
        int bean_num=0;
        for(int i=0;i<map.wall.length;i++){
            for(int j=0;j<map.wall[i].length;j++){
                if(map.wall[i][j]==2 || map.wall[i][j]==5){
                    bean_num++;
                }
            }
        }
        return bean_num;
    }
    static public boolean isMoveLegal(int x,int y, Layout map,Directions direction){
        x=getNextX(x, direction);
        y=getNextY(y, direction);

        if (x<0 || y<0 || x>27 || y>20) return false;
        return map.wall[y][x]!=1;
    }

    static public int getScore(int x,int y,Layout map){
        int score;
        score = map.wall[y][x];
        map.wall[y][x] = 0;

        return score;
    }
    static public int getNextY(int y,Directions direction){
        switch(direction){
            case UP:
                return y-1;
            case DOWN:
                return y+1;
            default: return y;
        }
    }
    static public int getNextX(int x,Directions direction){
        switch(direction) {
            case LEFT:
                return x - 1;
            case RIGHT:
                return x + 1;
            default: return x;
        }
    }


    static public boolean isGameOver(int x,int y,int b_x,int b_y,int c_x, int c_y, int bean_num){
        if(bean_num==0){
            win = true;
            return true;
        }
        if(b_x == x && b_y == y){
            win = false;
            return true;
        }
        if(c_x == x && c_y == y){
            win = false;
            return true;
        }
        return false;
    }
    static public Directions generateDirection(int agent_x,int agent_y,int pac_x,int pac_y,Layout map){
        double min = 5000000;
        Directions direction= Directions.RIGHT;
        double dis_up = evaluation(agent_x,agent_y-1,pac_x,pac_y);
        double dis_down=evaluation(agent_x,agent_y+1,pac_x,pac_y);
        double dis_left=evaluation(agent_x-1,agent_y,pac_x,pac_y);
        double dis_right=evaluation(agent_x+1,agent_y,pac_x,pac_y);
        if(dis_up < min && isMoveLegal(agent_x,agent_y,map,Directions.UP)){
            min = dis_up;
            direction = Directions.UP;
        }
        if(dis_down < min  && isMoveLegal(agent_x,agent_y,map,Directions.DOWN)){
            min = dis_down;
            direction = Directions.DOWN;
        }
        if(dis_left < min  && isMoveLegal(agent_x,agent_y,map,Directions.LEFT)){
            min = dis_left;
            direction = Directions.LEFT;
        }
        if(dis_right < min && isMoveLegal(agent_x,agent_y,map,Directions.RIGHT)){
            min = dis_right;
            direction = Directions.RIGHT;
        }
        return direction;
    }
}
