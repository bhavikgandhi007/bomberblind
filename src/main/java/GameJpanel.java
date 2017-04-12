import exceptions.CannotCreateMapElementException;
import exceptions.InvalidConfigurationException;
import exceptions.InvalidPropertiesException;
import map.Map;
import map.zelda.ZeldaMap;
import map.zelda.ZeldaMapProperties;
import map.zelda.ZeldaMapSetting;
import sprite.SpriteType;
import sprite.nomad.BlueBomber;
import sprite.nomad.Bomber;
import sprite.settled.BonusType;
import sprite.settled.Sparkle;
import spriteList.SpriteList;
import spriteList.SpritesProperties;
import spriteList.SpritesSetting;
import utils.Timer;
import utils.Tuple2;
import utils.text.SkinnedTextWithBG;
import utils.TopBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static images.ImagesLoader.IMAGE_SIZE;
import static map.ctrl.NomadMethods.isNomadCrossingExit;
import static spriteList.ctrl.AddingMethods.addBomber;
import static spriteList.ctrl.AddingMethods.addSparkle;
import static spriteList.ctrl.GenerationMethods.placeAGroupOfBird;
import static utils.Direction.DIRECTION_EAST;

public class GameJpanel extends JPanel implements Runnable, KeyListener {

    private Map map;
    private Bomber bomber;
    private SpriteList spriteList;
    private List<Long> pressedKeyList;

    private final Timer timer = new Timer();
    private GameStatus gameStatus;

    // this members allow printing map from a certain point.
    private int xMapStartPosOnScreen;
    private int yMapStartPosOnScreen;

    public GameJpanel(int screenWidth, int screenHeight) throws IOException, InvalidPropertiesException,
            InvalidConfigurationException, CannotCreateMapElementException {

        // create the map.
        map = new ZeldaMap(
                new ZeldaMapSetting(
                        new ZeldaMapProperties("/zelda.map.properties").loadProperties().checkProperties()),
                screenWidth,
                screenHeight);
        map.generateMap();

        // create the list of sprites.
        spriteList = new SpriteList(
                new SpritesSetting(
                        new SpritesProperties("/zelda.sprites.properties").loadProperties().checkProperties()),
                map,
                screenWidth,
                screenHeight);
        spriteList.generateSprites();

        // create the main bomber and add it to the list of sprites.
        Tuple2<Integer, Integer> bbManInitialPosition = map.computeInitialBbManPosition();
        bomber = new BlueBomber(bbManInitialPosition.getFirst(), bbManInitialPosition.getSecond());
        addBomber(spriteList, bomber);

        // create an exit sign to help the player finding the exit.
        Tuple2<Integer, Integer> exitSignPosition = map.computeExitSignPosition();
        addSparkle(spriteList, new Sparkle(exitSignPosition.getFirst() ,(exitSignPosition.getSecond())));

        // create the 3 first birds :)
        placeAGroupOfBird(spriteList, 3, -100, bbManInitialPosition.getSecond() + 225, DIRECTION_EAST, -8);

        // create a list to handle pressed keys.
        pressedKeyList = new ArrayList<>();
        pressedKeyList.add(0L); // add the "wait" action.

        setFocusable(true);
        addKeyListener(this);

        Thread T = new Thread(this);
        T.start();
    }

    /**
     * Update the ZeldaMap start position on screen function of the Bomber map position.
     */
    private void updateMapStartPosOnScreen() {
        if (bomber.getxMap() < getWidth() / 2) {
            xMapStartPosOnScreen = 0;
        } else if (bomber.getxMap() > (map.getMapWidth() * IMAGE_SIZE) - (getWidth() / 2)) {
            xMapStartPosOnScreen = (map.getMapWidth() * IMAGE_SIZE) - getWidth();
        } else {
            xMapStartPosOnScreen = bomber.getxMap() - (getWidth() / 2);
        }
        if (bomber.getyMap() < getHeight() / 2) {
            yMapStartPosOnScreen = 0;
        } else if (bomber.getyMap() > (map.getMapHeight() * IMAGE_SIZE) - (getHeight() / 2)) {
            yMapStartPosOnScreen = (map.getMapHeight() * IMAGE_SIZE) - getHeight();
        } else {
            yMapStartPosOnScreen = bomber.getyMap() - (getHeight() / 2);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        try {
            map.paintBuffer(g2d, xMapStartPosOnScreen, yMapStartPosOnScreen);
            spriteList.paintBuffer(g2d, xMapStartPosOnScreen, yMapStartPosOnScreen);
            TopBar.paintBuffer(g2d, map.getScreenWidth(), bomber, timer.getElapsedTime());

            if (gameStatus == GameStatus.STATUS_GAME_NEW) {
                SkinnedTextWithBG.paintBuffer(g2d, map.getScreenWidth(), map.getScreenHeight(), SkinnedTextWithBG.TEXT_NEW);
            } else if (gameStatus == GameStatus.STATUS_GAME_OVER) {
                SkinnedTextWithBG.paintBuffer(g2d, map.getScreenWidth(), map.getScreenHeight(), SkinnedTextWithBG.TEXT_GAME_OVER);
            } else if (gameStatus == GameStatus.STATUS_GAME_WIN) {
                SkinnedTextWithBG.paintBuffer(g2d, map.getScreenWidth(), map.getScreenHeight(), SkinnedTextWithBG.TEXT_WIN);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Long keyCode = (long) e.getKeyCode();
        Long lastKeyCode = pressedKeyList.get(pressedKeyList.size() - 1);
        if (!keyCode.equals(lastKeyCode)) {
            pressedKeyList.add((long) e.getKeyCode());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeyList.remove(Long.valueOf(e.getKeyCode()));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void run() {
        gameStatus = GameStatus.STATUS_GAME_NEW;
        while (true) {
            try {
                int pressedKey = pressedKeyList.get(pressedKeyList.size() - 1).intValue();
                if (pressedKey == KeyEvent.VK_ESCAPE) { // quit the game.
                    System.exit(0);

                } else if (gameStatus == GameStatus.STATUS_GAME_NEW) {
                    if (pressedKey == KeyEvent.VK_ENTER) {
                        gameStatus = GameStatus.STATUS_GAME_PLAY;
                        bomber.setInvincible();
                        timer.start();
                    }

                } else if (gameStatus == GameStatus.STATUS_GAME_PLAY) {
                    if (bomber.getBonus(BonusType.TYPE_BONUS_HEART) == 0) { // the bomber is dead.
                        timer.stop();
                        gameStatus = GameStatus.STATUS_GAME_OVER;

                    } else if (pressedKey == KeyEvent.VK_Q && // the bomber try to exit.
                            isNomadCrossingExit(map.getMapPointMatrix(),
                                    map.getMapWidth(),
                                    map.getMapHeight(),
                                    bomber.getxMap(),
                                    bomber.getyMap())) {
                        timer.stop();
                        gameStatus = GameStatus.STATUS_GAME_WIN;

                    } else {
                        spriteList.update(pressedKey);
                    }
                }
                updateMapStartPosOnScreen();

                // update the list order to handle sprites superposition.
                spriteList.sort((o1, o2) -> {
                    if (o1.getSpriteType() != o2.getSpriteType() &&
                            (o1.getSpriteType() == SpriteType.TYPE_SPRITE_FLYING_NOMAD ||
                                    o2.getSpriteType() == SpriteType.TYPE_SPRITE_FLYING_NOMAD)) {
                        return o1.getSpriteType() == SpriteType.TYPE_SPRITE_FLYING_NOMAD ? 1 : -1;
                    } else if (o1.getyMap() != o2.getyMap()) {
                        return o1.getyMap() > o2.getyMap() ? 1 : -1;
                    } else {
                        return 0;
                    }
                });
                repaint();
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}