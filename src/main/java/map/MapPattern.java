package map;

import java.awt.*;

public class MapPattern {

    // images information.
    private final Image[] imageArray;
    private final int width;
    private final int height;

    private final boolean isPathway;  // is a pathway pattern?
    private final boolean isMutable; // is a mutable pattern?

    private final boolean isEntrance; // is an entrance?
    private final boolean isExit; // is an exit?

    private final String name;

    public MapPattern(Image[] imageArray,
                      int width,
                      int height,
                      boolean isPathway,
                      boolean isMutable,
                      boolean isEntrance,
                      boolean isExit,
                      String name) {
        this.imageArray = imageArray;
        this.width = width;
        this.height = height;
        this.isPathway = isPathway;
        this.isMutable = isMutable;
        this.isEntrance = isEntrance;
        this.isExit = isExit;
        this.name = name;
    }

    public Image[] getImageArray() {
        return imageArray;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isPathway() {
        return isPathway;
    }

    public boolean isMutable() {
        return isMutable;
    }

    public boolean isEntrance() {
        return isEntrance;
    }

    public boolean isExit() {
        return isExit;
    }

    public String getName() {
        return name;
    }
}
