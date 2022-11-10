package com.javarush.games.minigames.mini08;

import com.javarush.engine.cell.*;

/* 
Работа с клавиатурой
*/

public class KeyboardGame extends Game {

    @Override
    public void initialize() {
        setScreenSize(3, 3);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                setCellColor(x, y, Color.WHITE);
            }
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case UP:
                for (int x = 0; x < 3; x++)
                    setCellColor(x,0,Color.GREEN);
                break;
            case DOWN:
                for (int x = 0; x < 3; x++)
                    setCellColor(x,2,Color.GREEN);
                break;
            case LEFT:
                for (int y = 0; y < 3; y++)
                    setCellColor(0,y,Color.GREEN);
                break;
            case RIGHT:
                for (int y = 0; y < 3; y++)
                    setCellColor(2,y,Color.GREEN);
                break;
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        if (key == Key.UP || key == Key.DOWN || key == Key.LEFT || key == Key.RIGHT) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    setCellColor(x, y, Color.WHITE);
                }
            }
        }
    }
}
