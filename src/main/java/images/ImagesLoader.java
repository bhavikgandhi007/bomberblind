package images;

import utils.Tuple3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

/**
 * Load the images of the game in a matrix of type 'Image'.
 * The 'Image' are loaded in a 'MediaTracker' to speed up the displaying and avoid sparkling.
 */
public class ImagesLoader {
    public final static int IMAGE_SIZE = 30; // size of an 'Image' in pixels (30*30).

    public static Image[][] imagesMatrix; // matrix of images (holding all the game images).
    public final static int NB_MATRIX_ROW = 57;
    public final static int NB_MATRIX_COL = 128;

    // images location.
    public final static String IMAGES_DIR = "/images";
    public final static String BOMBER_SKIN_DIR = IMAGES_DIR + "/sprites/bomber";
    public final static String ENEMY_SKIN_DIR = IMAGES_DIR + "/sprites/enemy";
    public final static String BOMB_SKIN_DIR = IMAGES_DIR + "/sprites/bomb";
    public final static String FLAME_SKIN_DIR = IMAGES_DIR + "/sprites/flame";
    public final static String BIRD_SKIN_DIR = IMAGES_DIR + "/sprites/bird";
    public final static String SCENE_SKIN_DIR = IMAGES_DIR + "/scene";
    public final static String ASCII_SKIN_DIR = IMAGES_DIR + "/ascii";
    public final static String BONUS_SKIN_DIR = IMAGES_DIR + "/bonus";

    // bomber:
    public final static int NB_BOMBER_DEATH_FRAME = 9;
    public final static int NB_BOMBER_WAIT_FRAME = 4;
    public final static int NB_BOMBER_WALK_FRAME = 4;
    public final static int NB_BOMBER_WIN_FRAME = 8;

    // - blue Bomber.
    public static int blueBomberDeathMatrixRowIdx;
    public static int blueBomberWaitMatrixRowIdx;
    public static int blueBomberWalkBackMatrixRowIdx;
    public static int blueBomberWalkFrontMatrixRowIdx;
    public static int blueBomberWalkLeftMatrixRowIdx;
    public static int blueBomberWalkRightMatrixRowIdx;
    public static int blueBomberWinMatrixRowIdx;

    // enemies:
    // - cloaked skeleton.
    public final static int NB_CLOAKED_SKELETON_DEATH_FRAME = 4;
    public final static int NB_CLOAKED_SKELETON_WALK_FRAME = 4;
    public static int cloakedSkeletonDeathMatrixRowIdx;
    public static int cloakedSkeletonWalkBackMatrixRowIdx;
    public static int cloakedSkeletonWalkFrontMatrixRowIdx;
    public static int cloakedSkeletonWalkLeftMatrixRowIdx;
    public static int cloakedSkeletonWalkRightMatrixRowIdx;

    // - mummy.
    public final static int NB_MUMMY_DEATH_FRAME = 4;
    public final static int NB_MUMMY_WALK_FRAME = 2;
    public static int mummyDeathMatrixRowIdx;
    public static int mummyWalkBackMatrixRowIdx;
    public static int mummyWalkFrontMatrixRowIdx;
    public static int mummyWalkLeftMatrixRowIdx;
    public static int mummyWalkRightMatrixRowIdx;

    // - mecanic angel.
    public final static int NB_MECA_ANGEL_DEATH_FRAME = 5;
    public final static int NB_MECA_ANGEL_WALK_FRAME = 2;
    public static int mecaAngelDeathMatrixRowIdx;
    public static int mecaAngelWalkBackMatrixRowIdx;
    public static int mecaAngelWalkFrontMatrixRowIdx;
    public static int mecaAngelWalkLeftMatrixRowIdx;
    public static int mecaAngelWalkRightMatrixRowIdx;

    // - minotor.
    public final static int NB_MINOTOR_BREAK_FRAME = 2;
    public final static int NB_MINOTOR_DEATH_FRAME = 5;
    public final static int NB_MINOTOR_WALK_FRAME = 4;
    public static int minotorDeathMatrixRowIdx;
    public static int minotorWalkBackMatrixRowIdx;
    public static int minotorWalkFrontMatrixRowIdx;
    public static int minotorWalkLeftMatrixRowIdx;
    public static int minotorWalkRightMatrixRowIdx;
    public static int minotorBreakBackMatrixRowIdx;
    public static int minotorBreakFrontMatrixRowIdx;
    public static int minotorBreakLeftMatrixRowIdx;
    public static int minotorBreakRightMatrixRowIdx;

    // - bird
    public final static int NB_BIRD_FLY_FRAME = 3;
    public static int birdFlyBackMatrixRowIdx;
    public static int birdFlyFrontMatrixRowIdx;
    public static int birdFlyLeftMatrixRowIdx;
    public static int birdFlyRightMatrixRowIdx;

    // settled:
    public final static int NB_BOMB_FRAME = 4;
    public static int bombMatrixRowIdx;
    public final static int NB_FLAME_FRAME = 3;
    public static int flameMatrixRowIdx;
    public final static int NB_FLAME_END_FRAME = 9;
    public static int flameEndMatrixRowIdx;

    // scene elements:
    public final static int CASTLE_WIDTH = 6;
    public final static int CASTLE_HEIGHT = 5;
    public static int castleT1MatrixRowIdx;
    public static int castleT2MatrixRowIdx;
    public final static int EDGE_WIDTH = 1;
    public final static int EDGE_HEIGHT = 4;
    public static int edgeMatrixRowIdx;
    public final static int TREE_WIDTH = 4;
    public final static int TREE_HEIGHT = 4;
    public static int tree1MatrixRowIdx;
    public static int tree2MatrixRowIdx;
    public final static int WOOD_WIDTH = 8;
    public final static int WOOD_HEIGHT = 10;
    public static int wood1MatrixRowIdx;
    public static int wood2MatrixRowIdx;
    public final static int NB_SINGLE_OBSTABLE = 2;
    public static int singleObstacleMatrixRowIdx;
    public final static int NB_FLOWER_FRAME = 3;
    public static int flowerMatrixRowIdx;
    public final static int NB_SINGLE_BOOM = 1;
    public static int singleBoomMatrixRowIdx;
    public final static int PUDDLE_WIDTH = 7;
    public final static int PUDDLE_HEIGHT = 6;
    public static int puddle1MatrixRowIdx;
    public static int puddle2MatrixRowIdx;
    public final static int NB_SINGLE_PATHWAY = 20;
    public static int singlePathwayMatrixRowIdx;
    public final static int NB_SINGLE_MUTABLE = 3;
    public static int singleMutableMatrixRowIdx;

    // ascii:
    public static int asciiMatrixRowIdx;

    // bonus:
    public final static int NB_BONUS_BOMB_FRAME = 1;
    public static int bonusBombMatrixRowIdx;
    public final static int NB_BONUS_FLAME_FRAME = 1;
    public static int bonusFlameMatrixRowIdx;
    public final static int NB_BONUS_HEART_FRAME = 1;
    public static int bonusHeartMatrixRowIdx;
    public final static int NB_BONUS_ROLLER_FRAME = 1;
    public static int bonusRollerMatrixRowIdx;

    public static int lastRowIdx; // for test purpose.
    public static boolean imageLoaded = false; // for test purpose.

    /**
     * Create an 'Image' based to a relative path (from 'resources' folder).
     *
     * @param relativePath relative path to an image
     * @return the created 'Image'
     * @throws IOException if the file does not exist
     */
    public static Image createImage(String relativePath) throws IOException {
        URL imageURL = ImagesLoader.class.getResource(relativePath);
        if (imageURL != null) {
            return ImageIO.read(imageURL);
        } else {
            throw new IOException("file not found: " + relativePath);
        }
    }

    /**
     * Fill a matrix of 'Image'.
     *
     * @throws IOException if a file does not exist
     */
    public static void fillImagesMatrix() throws IOException {
        if (imageLoaded) return; // load once the images (test purpose).

        int rowIdx = 0;
        imagesMatrix = new Image[NB_MATRIX_ROW][NB_MATRIX_COL];

        // bombers:
        // - blue bomber.
        for (int i = 0; i < NB_BOMBER_DEATH_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BOMBER_SKIN_DIR + "/blue_bomber/death_" + imageIdx + ".png");
        }
        blueBomberDeathMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BOMBER_WAIT_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BOMBER_SKIN_DIR + "/blue_bomber/wait_" + imageIdx + ".png");
        }
        blueBomberWaitMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BOMBER_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BOMBER_SKIN_DIR + "/blue_bomber/walk_back_" + imageIdx + ".png");
        }
        blueBomberWalkBackMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BOMBER_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BOMBER_SKIN_DIR + "/blue_bomber/walk_front_" + imageIdx + ".png");
        }
        blueBomberWalkFrontMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BOMBER_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BOMBER_SKIN_DIR + "/blue_bomber/walk_left_" + imageIdx + ".png");
        }
        blueBomberWalkLeftMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BOMBER_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BOMBER_SKIN_DIR + "/blue_bomber/walk_right_" + imageIdx + ".png");
        }
        blueBomberWalkRightMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BOMBER_WIN_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BOMBER_SKIN_DIR + "/blue_bomber/win_" + imageIdx + ".png");
        }
        blueBomberWinMatrixRowIdx = rowIdx++;

        // enemies:
        // - cloaked skeleton.
        for (int i = 0; i < NB_CLOAKED_SKELETON_DEATH_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/cloaked_skeleton/death_" + imageIdx + ".png");
        }
        cloakedSkeletonDeathMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_CLOAKED_SKELETON_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/cloaked_skeleton/walk_back_" + imageIdx + ".png");
        }
        cloakedSkeletonWalkBackMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_CLOAKED_SKELETON_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/cloaked_skeleton/walk_front_" + imageIdx + ".png");
        }
        cloakedSkeletonWalkFrontMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_CLOAKED_SKELETON_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/cloaked_skeleton/walk_left_" + imageIdx + ".png");
        }
        cloakedSkeletonWalkLeftMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_CLOAKED_SKELETON_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/cloaked_skeleton/walk_right_" + imageIdx + ".png");
        }
        cloakedSkeletonWalkRightMatrixRowIdx = rowIdx++;

        // - mummy.
        for (int i = 0; i < NB_MECA_ANGEL_DEATH_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/mummy/death_" + imageIdx + ".png");
        }
        mummyDeathMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MECA_ANGEL_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/mummy/walk_back_" + imageIdx + ".png");
        }
        mummyWalkBackMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MUMMY_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/mummy/walk_front_" + imageIdx + ".png");
        }
        mummyWalkFrontMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MUMMY_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/mummy/walk_left_" + imageIdx + ".png");
        }
        mummyWalkLeftMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MUMMY_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/mummy/walk_right_" + imageIdx + ".png");
        }
        mummyWalkRightMatrixRowIdx = rowIdx++;

        // - mecanical angel.
        for (int i = 0; i < NB_MECA_ANGEL_DEATH_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/meca_angel/death_" + imageIdx + ".png");
        }
        mecaAngelDeathMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MECA_ANGEL_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/meca_angel/walk_back_" + imageIdx + ".png");
        }
        mecaAngelWalkBackMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MECA_ANGEL_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/meca_angel/walk_front_" + imageIdx + ".png");
        }
        mecaAngelWalkFrontMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MECA_ANGEL_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/meca_angel/walk_left_" + imageIdx + ".png");
        }
        mecaAngelWalkLeftMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MECA_ANGEL_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/meca_angel/walk_right_" + imageIdx + ".png");
        }
        mecaAngelWalkRightMatrixRowIdx = rowIdx++;

        // - minotor.
        for (int i = 0; i < NB_MINOTOR_DEATH_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/minotor/death_" + imageIdx + ".png");
        }
        minotorDeathMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MINOTOR_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/minotor/walk_back_" + imageIdx + ".png");
        }
        minotorWalkBackMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MINOTOR_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/minotor/walk_front_" + imageIdx + ".png");
        }
        minotorWalkFrontMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MINOTOR_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/minotor/walk_left_" + imageIdx + ".png");
        }
        minotorWalkLeftMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MINOTOR_WALK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/minotor/walk_right_" + imageIdx + ".png");
        }
        minotorWalkRightMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MINOTOR_BREAK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/minotor/break_back_" + imageIdx + ".png");
        }
        minotorBreakBackMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MINOTOR_BREAK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/minotor/break_front_" + imageIdx + ".png");
        }
        minotorBreakFrontMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MINOTOR_BREAK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/minotor/break_left_" + imageIdx + ".png");
        }
        minotorBreakLeftMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_MINOTOR_BREAK_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(ENEMY_SKIN_DIR + "/minotor/break_right_" + imageIdx + ".png");
        }
        minotorBreakRightMatrixRowIdx = rowIdx++;

        // bird:
        for (int i = 0; i < NB_BIRD_FLY_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BIRD_SKIN_DIR + "/fly_back_" + imageIdx + ".png");
        }
        birdFlyBackMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BIRD_FLY_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BIRD_SKIN_DIR + "/fly_front_" + imageIdx + ".png");
        }
        birdFlyFrontMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BIRD_FLY_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BIRD_SKIN_DIR + "/fly_left_" + imageIdx + ".png");
        }
        birdFlyLeftMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BIRD_FLY_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BIRD_SKIN_DIR + "/fly_right_" + imageIdx + ".png");
        }
        birdFlyRightMatrixRowIdx = rowIdx++;

        // settled:
        for (int i = 0; i < NB_BOMB_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BOMB_SKIN_DIR + "/bomb_" + imageIdx + ".png");
        }
        bombMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_FLAME_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(FLAME_SKIN_DIR + "/flame_" + imageIdx + ".png");
        }
        flameMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_FLAME_END_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(FLAME_SKIN_DIR + "/flame_end_" + imageIdx + ".png");
        }
        flameEndMatrixRowIdx = rowIdx++;

        // scene:
        // - immutable elements.
        // -- castle (1 & 2).
        for (int i = 0; i < CASTLE_WIDTH * CASTLE_HEIGHT; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/obstacle/castle_1/castle_t1_" + imageIdx + ".png");
        }
        castleT1MatrixRowIdx = rowIdx++;
        for (int i = 0; i < CASTLE_WIDTH * CASTLE_HEIGHT; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/obstacle/castle_2/castle_t2_" + imageIdx + ".png");
        }
        castleT2MatrixRowIdx = rowIdx++;

        // -- edge.
        for (int i = 0; i < EDGE_WIDTH * EDGE_HEIGHT; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/obstacle/edge/edge_" + imageIdx + ".png");
        }
        edgeMatrixRowIdx = rowIdx++;

        // -- tree (1 & 2).
        for (int i = 0; i < TREE_WIDTH * TREE_HEIGHT; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/obstacle/tree_1/tree_1_" + imageIdx + ".png");
        }
        tree1MatrixRowIdx = rowIdx++;
        for (int i = 0; i < TREE_WIDTH * TREE_HEIGHT; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/obstacle/tree_2/tree_2_" + imageIdx + ".png");
        }
        tree2MatrixRowIdx = rowIdx++;

        // -- wood (1 & 2).
        for (int i = 0; i < WOOD_WIDTH * WOOD_HEIGHT; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/obstacle/wood_1/wood_1_" + imageIdx + ".png");
        }
        wood1MatrixRowIdx = rowIdx++;
        for (int i = 0; i < WOOD_WIDTH * WOOD_HEIGHT; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/obstacle/wood_2/wood_2_" + imageIdx + ".png");
        }
        wood2MatrixRowIdx = rowIdx++;

        // -- single obstacle (1*1).
        for (int i = 0; i < NB_SINGLE_OBSTABLE; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/obstacle/obstacle_" + imageIdx + ".png");
        }
        singleObstacleMatrixRowIdx = rowIdx++;

        // -- boom (1*1).
        for (int i = 0; i < NB_SINGLE_BOOM; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/pathway/boom/boom_" + imageIdx + ".png");
        }
        singleBoomMatrixRowIdx = rowIdx++;

        // -- flower (animated).
        for (int i = 0; i < NB_FLOWER_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/pathway/flower/flower_" + imageIdx + ".png");
        }
        flowerMatrixRowIdx = rowIdx++;

        // -- puddle (1 & 2).
        for (int i = 0; i < PUDDLE_WIDTH * PUDDLE_HEIGHT; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/pathway/puddle_1/puddle_1_" + imageIdx + ".png");
        }
        puddle1MatrixRowIdx = rowIdx++;
        for (int i = 0; i < PUDDLE_WIDTH * PUDDLE_HEIGHT; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/pathway/puddle_2/puddle_2_" + imageIdx + ".png");
        }
        puddle2MatrixRowIdx = rowIdx++;

        // -- single pathway (1*1).
        for (int i = 0; i < NB_SINGLE_PATHWAY; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/immutable/pathway/pathway_" + imageIdx + ".png");
        }
        singlePathwayMatrixRowIdx = rowIdx++;

        // single mutable (1*1).
        for (int i = 0; i < NB_SINGLE_MUTABLE; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(SCENE_SKIN_DIR + "/mutable/mutable_" + imageIdx + ".png");
        }
        singleMutableMatrixRowIdx = rowIdx++;

        // ascii.
        for (int i = 0; i <= 127; i++) {
            String imageIdx = String.format("%2s", i).replace(' ', '0');
            try {
                imagesMatrix[rowIdx][i] = createImage(ASCII_SKIN_DIR + "/" + imageIdx + ".png");
            } catch (IOException e) { // the ascii character is not available, put an empty image.
                imagesMatrix[rowIdx][i] = createImage(ASCII_SKIN_DIR + "/32.png");
            }
        }
        asciiMatrixRowIdx = rowIdx++;

        // bonus:
        for (int i = 0; i < NB_BONUS_BOMB_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BONUS_SKIN_DIR + "/bomb/bomb_" + imageIdx + ".png");
        }
        bonusBombMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BONUS_FLAME_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BONUS_SKIN_DIR + "/flame/flame_" + imageIdx + ".png");
        }
        bonusFlameMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BONUS_HEART_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BONUS_SKIN_DIR + "/heart/heart_" + imageIdx + ".png");
        }
        bonusHeartMatrixRowIdx = rowIdx++;
        for (int i = 0; i < NB_BONUS_ROLLER_FRAME; i++) {
            String imageIdx = String.format("%2s", i + 1).replace(' ', '0');
            imagesMatrix[rowIdx][i] = createImage(BONUS_SKIN_DIR + "/roller/roller_" + imageIdx + ".png");
        }
        bonusRollerMatrixRowIdx = rowIdx;

        lastRowIdx = rowIdx;
        imageLoaded = true;
    }


    /**
     * @return a random single static pathway image.
     */
    public static Image getRandomSingleStaticPathway() {
        Random R = new Random(); // init the random function.
        int imageIdx = R.nextInt(ImagesLoader.NB_SINGLE_PATHWAY);
        return ImagesLoader.imagesMatrix[ImagesLoader.singlePathwayMatrixRowIdx][imageIdx];
    }

    /**
     * @return a random single dynamic pathway image.
     */
    @SuppressWarnings("unchecked")
    public static Tuple3<Image[], Integer, Integer> getRandomSingleDynamicPathway() {
        return new Tuple3<>(ImagesLoader.imagesMatrix[ImagesLoader.flowerMatrixRowIdx],
                ImagesLoader.NB_FLOWER_FRAME, 100);
    }

    /**
     * @return a random single mutable image.
     */
    public static Image getRandomSingleMutable() {
        Random R = new Random(); // init the random function.
        int imageIdx = R.nextInt(ImagesLoader.NB_SINGLE_MUTABLE);
        return ImagesLoader.imagesMatrix[ImagesLoader.singleMutableMatrixRowIdx][imageIdx];
    }

    /**
     * @return a random single obstacle image.
     */
    public static Image getRandomSingleObstacle() {
        Random R = new Random(); // init the random function.
        int imageIdx = R.nextInt(ImagesLoader.NB_SINGLE_OBSTABLE);
        return ImagesLoader.imagesMatrix[ImagesLoader.singleObstacleMatrixRowIdx][imageIdx];
    }
}
