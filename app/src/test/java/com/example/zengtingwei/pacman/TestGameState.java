package com.example.zengtingwei.pacman;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestGameState {
    @Test
    public void testEvaluation() {
        assertEquals("Should get 5.0 but have ", 5.0, GameState.evaluation(0, 2, 3, 6));
    }

    @Test
    public void testGetBeanNum() {
        Layout map = new Layout();
        assertEquals("Should get 100 but have ", 229, GameState.getBeanNum(map));
    }

    @Test
    public void testMoveLegal(){
        Layout map = new Layout();
        assertEquals("should get true but have ", true, GameState.isMoveLegal(50, 50, map, Directions.RIGHT));
    }

    @Test
    public void testScore(){
        Layout map = new Layout();
        assertEquals("should get 1 but have ", 1, GameState.getScore(0, 0, map));
    }

    @Test
    public void testNextY(){
        assertEquals("should get 50 but have ", 50f, GameState.getNextY(0, Directions.DOWN));
    }

    @Test
    public void testNextX(){
        assertEquals("should get 200 but have ", 200f, GameState.getNextX(150, Directions.RIGHT));
    }

    @Test
    public void testGameOver(){
        Layout map = new Layout();
        assertEquals("should get true but have ", true, GameState.isGameOver(100, 200, 100, 200, 250,50,12));
        assertEquals("should get true but have ", true, GameState.isGameOver(100, 200, 200,300,0,0,0));
    }
}