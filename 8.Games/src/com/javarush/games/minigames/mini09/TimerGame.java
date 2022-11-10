package com.javarush.games.minigames.mini09;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

/* 
Таймер
*/

public class TimerGame extends Game {

    @Override
    public void initialize() {
        setScreenSize(3, 3);
        setTurnTimer(1000);
    }

    @Override
    public void onTurn(int step) {
        if (step % 2 == 0) {
            setCellNumber(1, 1, step);
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    setCellColor(x, y, Color.GREEN);
                }
            }
        } else {
            setCellNumber(1, 1, step);
            for (int y = 0; y < 3; y++) {
                for (int x = 0; x < 3; x++) {
                    setCellColor(x, y, Color.ORANGE);
                }
            }
        }
    }
}
