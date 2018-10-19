package com.example.zengtingwei.pacman;

//written by Zhisheng Ni, modified by Zhiyuan Xu
public class GameState {
    static boolean win = true;

    //evaluate the distance between pac man and ghost
    static public double evaluation(int agent_x,int agent_y,int pac_x,int pac_y){
        double dis = Math.sqrt(Math.pow((agent_y-pac_y),2)+Math.pow((agent_x-pac_x),2));
        return dis;
    }

    //count the number of beans
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

    //judge whether the move is valid
    static public boolean isMoveLegal(int x,int y, Layout map,Directions direction){
        x=getNextX(x, direction);
        y=getNextY(y, direction);

        if (x<0 || y<0 || x>27 || y>20) return false;
        return map.wall[y][x]!=1;
    }

    //get the score of single move and remove the bean
    static public int getScore(int x,int y,Layout map){
        int score;
        score = map.wall[y][x];
        map.wall[y][x] = 0;

        return score;
    }

    //update the coordinates
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

    //judge if the game is over and win or lose
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

    //generate direction for ghost
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
