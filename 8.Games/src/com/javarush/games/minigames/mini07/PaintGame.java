package com.javarush.games.minigames.mini07;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

/* 
Работа с мышью
*/

public class PaintGame extends Game {
    private static final int width = 5;
    private static final int height = 5;

    @Override
    public void initialize() {
        setScreenSize(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                setCellColor(x,y, Color.WHITE);
            }
        }
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        setCellColor(x,y,Color.GREEN);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        setCellColor(x,y,Color.ORANGE);
    }
}
