/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman;

import java.util.HashSet;
import java.util.Set;

import bomberman.Bomberman;
import bomberman.GameField;
import bomberman.ImageHandler;
import bomberman.SoundHandler;
import bomberman.Bomb;
/**
 *
 * @author Aldiyar
 */
class GameHelper {
    Set<Bomb> bombs = new HashSet<Bomb>();
    public void kill(Bomberman bomberman) {
        bomberman.alive = false;
        resetPlayer(bomberman);
        System.out.println(bomberman.x + ", " + bomberman.y + " died!");
    }
    public void bombPlant(GameField gameField, Bomberman bomberman, SoundHandler soundHandler) {
        if (bomberman.putBomb && bomberman.alive) {
            if (bomberman.bombsAllowed > bomberman.bombsPlanted) {
                int divX = (bomberman.x % 800) / 40;
                int divY = (bomberman.y % 600) / 40;
                if (gameField.field[divX][divY] != 3) {
                    Bomb bomb = new Bomb(bomberman);
                    bombs.add(bomb);
                    bomberman.bombsPlanted++;
                    soundHandler.plant();
                }
            }
        }
    }

    public void updateBombs(GameField gameField, Bomberman player1, Bomberman player2, SoundHandler soundHandler) {
        bombPlant(gameField, player1, soundHandler);
        bombPlant(gameField, player2, soundHandler);
        Set<Bomb> removed = new HashSet<Bomb>();
        for (Bomb b : bombs) {
            b.updateExistTime(gameField, soundHandler);
            if (b.existTime >= b.disappearTime) {
                removed.add(b);
                b.whoPlanted.bombsPlanted--;
            }
        }
        bombs.removeAll(removed);
    }
    public void restartGame(GameField gameField, Bomberman player1, Bomberman player2, String fname) {
        gameField.loadLevel(fname, player1, player2);
        player1.alive = true;
        player2.alive = true;
        resetPlayer(player1);
        resetPlayer(player2);
    }
    public void resetPlayer (Bomberman bomberman) {
        bomberman.bombsAllowed = 1;
        bomberman.speed = 2;
        bomberman.power = 2;
    }
}
