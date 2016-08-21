package map.zelda;

import images.ImagesLoader;
import map.MapPattern;

/**
 * Create patterns for all the graphical elements (except for simple pathway - 1*1).
 * These patterns will be used to dynamically generate the map.
 */
public class ZeldaMapPatterns {

    // castles.
    public static MapPattern castle1 = new MapPattern(ImagesLoader.imagesMatrix[ImagesLoader.castleT1MatrixRowIdx],
            ImagesLoader.CASTLE_WIDTH, ImagesLoader.CASTLE_HEIGHT, false, false, "castle1");
    public static MapPattern castle2 = new MapPattern(ImagesLoader.imagesMatrix[ImagesLoader.castleT2MatrixRowIdx],
            ImagesLoader.CASTLE_WIDTH, ImagesLoader.CASTLE_HEIGHT, false, false, "castle2");

    // obstacles.
    public static MapPattern edge = new MapPattern(ImagesLoader.imagesMatrix[ImagesLoader.edgeMatrixRowIdx],
            ImagesLoader.EDGE_WIDTH, ImagesLoader.EDGE_HEIGHT, false, false, "edge");
    public static MapPattern tree1 = new MapPattern(ImagesLoader.imagesMatrix[ImagesLoader.tree1MatrixRowIdx],
            ImagesLoader.TREE_WIDTH, ImagesLoader.TREE_HEIGHT, false, false, "tree1");
    public static MapPattern tree2 = new MapPattern(ImagesLoader.imagesMatrix[ImagesLoader.tree2MatrixRowIdx],
            ImagesLoader.TREE_WIDTH, ImagesLoader.TREE_HEIGHT, false, false, "tree2");
    public static MapPattern wood1 = new MapPattern(ImagesLoader.imagesMatrix[ImagesLoader.wood1MatrixRowIdx],
            ImagesLoader.WOOD_WIDTH, ImagesLoader.WOOD_HEIGHT, false, false, "wood1");
    public static MapPattern wood2 = new MapPattern(ImagesLoader.imagesMatrix[ImagesLoader.wood2MatrixRowIdx],
            ImagesLoader.WOOD_WIDTH, ImagesLoader.WOOD_HEIGHT, false, false, "wood2");

    // pathways.
    public static MapPattern puddle1 = new MapPattern(ImagesLoader.imagesMatrix[ImagesLoader.puddle1MatrixRowIdx],
            ImagesLoader.PUDDLE_WIDTH, ImagesLoader.PUDDLE_HEIGHT, true, false, "puddle1");
    public static MapPattern puddle2 = new MapPattern(ImagesLoader.imagesMatrix[ImagesLoader.puddle2MatrixRowIdx],
            ImagesLoader.PUDDLE_WIDTH, ImagesLoader.PUDDLE_HEIGHT, true, false, "puddle2");

}