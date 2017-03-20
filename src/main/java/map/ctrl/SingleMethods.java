package map.ctrl;

import images.ImagesLoader;
import map.MapPoint;
import utils.Tuple3;

import java.awt.*;
import java.util.Random;

public class SingleMethods {

    /**
     * Try to place a single pathway on map.
     * If the case is available, place the pathway and return true, otherwise return false.
     *
     * @param mapPoint                  the MapPoint to place the pathway
     * @param perDecoratedSinglePathway the percentage of decorated elements to place among single pathway
     * @param perDynamicSinglePathway   the percentage of dynamic elements to place among decorated single pathway
     * @return true if the pathway has been placed, false otherwise
     */
    public static boolean placeSinglePathwayOnMap(MapPoint mapPoint,
                                                  int perDecoratedSinglePathway,
                                                  int perDynamicSinglePathway) {
        if (mapPoint.isAvailable()) {
            int randomPercent = Math.abs(new Random().nextInt(100)); // randomly get a % value.
            if (randomPercent < perDecoratedSinglePathway) {
                randomPercent = Math.abs(new Random().nextInt(100)); // randomly get a % value.
                if (randomPercent < perDynamicSinglePathway) {
                    Tuple3 dynamicElt = ImagesLoader.getRandomDynamicSinglePathway(); // animated.
                    mapPoint.setImages((Image[]) dynamicElt.getFirst(), (Integer) dynamicElt.getSecond());
                    mapPoint.setRefreshTime((Integer) dynamicElt.getThird());
                } else {
                    mapPoint.setImage(ImagesLoader.getRandomDecoratedSinglePathway()); // decorated pathway.
                }
            } else {
                mapPoint.setImage(ImagesLoader.getVirginSinglePathway());  // virgin.
            }
            mapPoint.setMutable(false);
            mapPoint.setPathway(true);
            mapPoint.setAvailable(false);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Try to place a single mutable obstacle on map.
     * If the case is available, place the mutable obstacle and return true, otherwise return false.
     *
     * @param mapPoint the MapPoint to place the mutable obstacle
     * @return true if the mutable obstacle has been placed, false otherwise
     */
    public static boolean placeSingleMutableObstacleOnMap(MapPoint mapPoint) {
        if (mapPoint.isAvailable()) {
            mapPoint.setImage(ImagesLoader.getRandomSingleMutableObstacle());
            mapPoint.setMutable(true);
            mapPoint.setPathway(false);
            mapPoint.setAvailable(false);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Try to place a single immutable obstacle on map.
     * If the case is available, place the immutable obstacle and return true, otherwise return false.
     *
     * @param mapPoint the MapPoint to place the immutable obstacle
     * @return true if the immutable obstacle has been placed, false otherwise
     */
    public static boolean placeSingleImmutableObstacleOnMap(MapPoint mapPoint) {
        if (mapPoint.isAvailable()) {
            mapPoint.setImage(ImagesLoader.getRandomSingleImmutableObstacle());
            mapPoint.setMutable(false);
            mapPoint.setPathway(false);
            mapPoint.setAvailable(false);
            return true;
        } else {
            return false;
        }
    }
}
