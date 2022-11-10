package com.javarush.games.minesweeper;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {
    private static final int SIDE = 9;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int countMinesOnField;
    private final static String MINE = "\uD83D\uDCA3";
    private final static String FLAG = "\uD83D\uDEA9";
    private int countFlags;

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    private void createGame() {
        for (int y = 0; y < SIDE; y++) {
            for (int x = 0; x < SIDE; x++) {
                boolean isMine = getRandomNumber(10) < 1;
                if (isMine) {
                    countMinesOnField++;
                }
                gameField[y][x] = new GameObject(x, y, isMine);
                setCellColor(x, y, Color.ORANGE);
            }
        }
        countMineNeighbors();
        countFlags = countMinesOnField;
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        openTile(x,y);
    }

    private void openTile(int x, int y) {
        GameObject go = gameField[y][x];
        go.isOpen = true;
        setCellColor(go.x, go.y,Color.GREEN);
        if (!go.isMine & go.countMineNeighbors == 0) {
            setCellValue(go.x, go.y,"");
            for (GameObject object : getNeighbors(go)) {
                if (!object.isOpen) {
                    openTile(object.x,object.y);
                }
            }
        }
        if (go.isMine) {
            go.isOpen = true;
            setCellColor(go.x, go.y,Color.RED);
            setCellValue(go.x, go.y,MINE);
        } else if (go.countMineNeighbors > 0){
            go.isOpen = true;
            setCellNumber(go.x, go.y, go.countMineNeighbors);
        }
        gameField[y][x] = go;
    }



    private void countMineNeighbors() {
        for (int x = 0; x < gameField.length; x++) {
            for (int y = 0; y < gameField[x].length; y++) {
                GameObject gameObject = gameField[y][x];
                if (gameObject.isMine)
                    continue;
                List<GameObject> neighbors = getNeighbors(gameObject);
                for (GameObject neighbor : neighbors) {
                    if (neighbor.isMine) gameObject.countMineNeighbors++;
                }
                gameField[y][x] = gameObject;
            }
        }
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> result = new ArrayList<>();
        for (int y = gameObject.y - 1; y <= gameObject.y + 1; y++) {
            for (int x = gameObject.x - 1; x <= gameObject.x + 1; x++) {
                if (y < 0 || y >= SIDE) {
                    continue;
                }
                if (x < 0 || x >= SIDE) {
                    continue;
                }
                if (gameField[y][x] == gameObject) {
                    continue;
                }
                result.add(gameField[y][x]);
            }
        }
        return result;
    }
}