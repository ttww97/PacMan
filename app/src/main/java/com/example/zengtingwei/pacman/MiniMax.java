package com.example.zengtingwei.pacman;

//written by Zhisheng Ni, but it did not perform as good as we expect;
public class MiniMax {
    private static Directions action;
    private static double maxint = -1000;
    private static double minint = 1000;
    private static int Depth = 1;

    //evaluate the score
    public static double evaluation(int agent_x,int agent_y,int pac_x,int pac_y,Layout map){
        double score=0;
        double dis;
        for(int i=0;i<map.wall.length;i++){
            for(int j=0;j<map.wall[i].length;j++){
                if(map.wall[i][j]==2 || map.wall[i][j]==5){
                    dis = Math.sqrt(Math.pow((agent_y-i),2)+Math.pow((agent_x-j),2));
                    score+= map.wall[i][j]/dis;
                }
            }
        }
        score += 1/Math.sqrt(Math.pow((agent_y-pac_y),2)+Math.pow((agent_x-pac_x),2));
        return score;
    }
    //maximize the score of opponents' move.
    public static double maximize(int agent_x,int agent_y,int pac_x,int pac_y, double alpha,double beta,int depth,Layout map){
        if(depth==Depth){
            return(evaluation(agent_x,agent_y,pac_x,pac_y,map));
        }
        double v = maxint;
        if(GameState.isMoveLegal(agent_x,agent_y,map,Directions.UP)){
            double w = minimize(agent_x,agent_y-1,pac_x,pac_y,alpha,beta,depth+1,map);
            if (w>v){
                v = w;
                action = Directions.UP;
            }
            if (v>beta){
                return v;
            }
            if (v>alpha){
                alpha=v;
            }
        }

        if(GameState.isMoveLegal(agent_x,agent_y,map,Directions.DOWN)){
            double w = minimize(agent_x,agent_y+1,pac_x,pac_y,alpha,beta,depth+1,map);
            if (w>v){
                v = w;
                action = Directions.DOWN;
            }
            if (v>beta){
                return v;
            }
            if (v>alpha){
                alpha=v;
            }
        }

        if(GameState.isMoveLegal(agent_x,agent_y,map,Directions.LEFT)){
            double w = minimize(agent_x-1,agent_y,pac_x,pac_y,alpha,beta,depth+1,map);
            if (w>v){
                v = w;
                action = Directions.LEFT;
            }
            if (v>beta){
                return v;
            }
            if (v>alpha){
                alpha=v;
            }
        }

        if(GameState.isMoveLegal(agent_x,agent_y,map,Directions.RIGHT)){
            double w = minimize(agent_x+1,agent_y,pac_x,pac_y,alpha,beta,depth+1,map);
            if (w>v){
                v = w;
                action = Directions.UP;
            }
            if (v>beta){
                return v;
            }
            if (v>alpha){
                alpha=v;
            }
        }

        return v;

    }
    //minimize the score of opponents' move.
    public static double minimize(int agent_x,int agent_y,int pac_x,int pac_y,double alpha,double beta,int depth, Layout map){
        if( depth==Depth){
            return(evaluation(agent_x,agent_y,pac_x,pac_y,map));
        }
        double v = minint;
        if(GameState.isMoveLegal(agent_x,agent_y,map,Directions.UP)){
            double w = maximize(agent_x,agent_y-1,pac_x,pac_y,alpha,beta,depth+1,map);
            if (w<=v){
                v = w;
            }
            if (v<alpha){
                return v;
            }
            if (v<beta){
                beta =v;
            }
        }

        if(GameState.isMoveLegal(agent_x,agent_y,map,Directions.DOWN)){
            double w = maximize(agent_x,agent_y+1,pac_x,pac_y,alpha,beta,depth+1,map);
            if (w<=v){
                v = w;
            }
            if (v<alpha){
                return v;
            }
            if (v<beta){
                beta =v;
            }
        }

        if(GameState.isMoveLegal(agent_x,agent_y,map,Directions.LEFT)){
            double w = maximize(agent_x-1,agent_y,pac_x,pac_y,alpha,beta,depth+1,map);
            if (w<=v){
                v = w;
            }
            if (v<alpha){
                return v;
            }
            if (v<beta){
                beta =v;
            }
        }

        if(GameState.isMoveLegal(agent_x,agent_y,map,Directions.RIGHT)){
            double w = maximize(agent_x+1,agent_y,pac_x,pac_y,alpha,beta,depth+1,map);
            if (w<=v){
                v = w;
            }
            if (v<alpha){
                return v;
            }
            if (v<beta){
                beta =v;
            }
        }
        return v;
    }

    public static Directions generateDirection(int agent_x,int agent_y,int pac_x,int pac_y,Layout map){
        double score;
        score = maximize(agent_x,agent_y,pac_x,pac_y,minint,maxint,0,map);
        return action;
    }

}
