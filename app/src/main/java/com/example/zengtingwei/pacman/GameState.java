package com.example.zengtingwei.pacman;

import android.graphics.Path;

public class GameState {
    public GameState(){

    }
    static public double evaluation(float agent_x,float agent_y,float pac_x,float pac_y){
        return Math.sqrt(Math.pow((agent_y-pac_y),2)+Math.pow((agent_x-pac_x),2));
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
    static public boolean isMoveLegal(float x,float y, Layout map,Directions direction){
        int idx_j = (int)GameState.getNextX(x,direction)/50;
        int idx_i = (int)GameState.getNextY(y,direction)/50;
        if(map.wall[idx_i][idx_j]!=1){
            return true;
        }
        return false;
    }

    static public int getScore(float x,float y,Layout map){
        int score = 0;
        score = map.wall[(int)x][(int)y];
        map.wall[(int)x][(int)y] = 0;

        return score;
    }
    static public float getNextY(float y,Directions direction){
        switch(direction){
            case UP:
                return y-50;
            case DOWN:
                return y+50;
            default:
                return y;
        }
    }
    static public float getNextX(float x,Directions direction){
        switch(direction){
            case LEFT:
                return x-50;
            case RIGHT:
                return x+50;
            default:
                return x;
        }
    }


    static public boolean isGameOver(float x,float y,float b_x,float b_y,int bean_num){
        if(bean_num==0){
            return true;
        }
        if(b_x == x && b_y == y){
            return true;
        }
        return false;
    }
    static public Directions generateDirection(float agent_x,float agent_y,float pac_x,float pac_y,Layout map){
        double min = 5000000;
        Directions direction= Directions.RIGHT;
        double dis_up = evaluation(agent_x,agent_y-50,pac_x,pac_y);
        double dis_down=evaluation(agent_x,agent_y+50,pac_x,pac_y);
        double dis_left=evaluation(agent_x-50,agent_y,pac_x,pac_y);
        double dis_right=evaluation(agent_x+50,agent_y,pac_x,pac_y);
        if(dis_up < min && isMoveLegal(agent_x,agent_y,map,Directions.UP)){
            min = dis_up;
            direction = Directions.UP;
        }
        if(dis_down < min-10  && isMoveLegal(agent_x,agent_y,map,Directions.DOWN)){
            min = dis_down;
            direction = Directions.DOWN;
        }
        if(dis_left < min-10  && isMoveLegal(agent_x,agent_y,map,Directions.LEFT)){
            min = dis_left;
            direction = Directions.LEFT;
        }
        if(dis_right < min-10 && isMoveLegal(agent_x,agent_y,map,Directions.RIGHT)){
            min = dis_right;
            direction = Directions.RIGHT;
        }
        return direction;
    }
}
