package com.example.zengtingwei.pacman;

import org.junit.Test;

import static com.example.zengtingwei.pacman.GameState.generateDirection;
import static com.example.zengtingwei.pacman.GameState.win;
import static junit.framework.TestCase.assertEquals;

/**
written by Zhiyuan Xu,Zengting wei
 */
public class TestGameState {
    @Test
    public void testEvaluation() {
        //Black-box test
        assertEquals("Should get 5.0 but have " + GameState.evaluation(0, 2, 3, 6), 5.0, GameState.evaluation(0, 2, 3, 6));
    }

    @Test
    public void testGetBeanNum() {
        Layout map = new Layout();
        //Black-box test
        assertEquals("Should get 229 but have " + GameState.getBeanNum(map), 229, GameState.getBeanNum(map));
        //White-box test
        map.wall[1][2] = 0;
        assertEquals("Should get 228 but have " + GameState.getBeanNum(map), 228, GameState.getBeanNum(map));
    }

    @Test
    public void testMoveLegal(){
        Layout map = new Layout();
        //Black-box test
        assertEquals("should get true but have "+GameState.isMoveLegal(1, 1, map, Directions.RIGHT), true, GameState.isMoveLegal(1, 1, map, Directions.RIGHT));
        map.wall[GameState.getNextY(1,Directions.RIGHT)][GameState.getNextX(1, Directions.RIGHT)]=1;
        //White-box test
        assertEquals("should get false but have "+GameState.isMoveLegal(1, 1, map, Directions.RIGHT), false, GameState.isMoveLegal(1, 1, map, Directions.RIGHT));
        //White-box test out of bounds
        assertEquals("should get false but have ", false, GameState.isMoveLegal(0, 0, map, Directions.UP));
        assertEquals("should get false but have ", false, GameState.isMoveLegal(27, 20, map, Directions.DOWN));
    }

    @Test
    public void testScore(){
        Layout map = new Layout();
        //Black-box test
        assertEquals("should get 1 but have "+ GameState.getScore(0, 0, map), 1, GameState.getScore(0, 0, map));
    }

    @Test
    public void testNextY(){
        //Black-box test
        assertEquals("should get 1 but have "+GameState.getNextY(0, Directions.DOWN), 1, GameState.getNextY(0, Directions.DOWN));
    }

    @Test
    public void testNextX(){
        //Black-box test
        assertEquals("should get 200 but have "+ GameState.getNextX(150, Directions.RIGHT), 151, GameState.getNextX(150, Directions.RIGHT));
    }

    @Test
    public void testGameOver(){
        //Black-box test
        assertEquals("should get true but have "+GameState.isGameOver(100, 200, 100, 200, 250,50,12), true, GameState.isGameOver(100, 200, 100, 200, 250,50,12));
        //White-box test
        assertEquals("should get true but have "+win, false, win);
        //Black-box test
        assertEquals("should get true but have "+GameState.isGameOver(100, 200, 200,300,0,0,0), true, GameState.isGameOver(100, 200, 200,300,0,0,0));
        //White-box test
        assertEquals("should get true but have "+win, true, win);
    }

    @Test
    public void testGenerateDirections() {
        //Black-box test
        Layout map = new Layout();
        assertEquals("should get DOWN but have "+generateDirection(1, 3, 4, 7, map), Directions.DOWN, generateDirection(1, 3, 4, 7, map));
    }
}