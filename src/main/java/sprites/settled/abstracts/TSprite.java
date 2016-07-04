package sprites.settled.abstracts;

import java.awt.*;

/**
 * Abstract class of a timed sprites.
 * The sprite loops during a certain time.
 */
public abstract class TSprite extends Sprite {

    private final Image[] images; // array of images of the sprite.
    private final int nbImages; // number of images of the sprite.
    private int curImageIdx; // current image index of the sprite.
    private final int duration; // duration (in ms).
    private final long startTs; // start timestamp.
    private final int refreshTime; // refresh time (in ms).
    private long lastRefreshTs; // last refresh timestamp.

    public TSprite(int rowIdx,
                   int colIdx,
                   Image[] images,
                   int nbImages,
                   int duration,
                   int refreshTime) {
        super(rowIdx, colIdx);
        this.images = images;
        this.nbImages = nbImages;
        this.duration = duration;
        this.refreshTime = refreshTime;
        this.startTs = System.currentTimeMillis(); // get the current time.
    }

    /**
     * @return true if the sprite is finished, false otherwise.
     */
    public boolean isFinished() {
        return System.currentTimeMillis() - startTs > duration;
    }

    /**
     * Update the sprite image.
     *
     * @return the updated image.
     */
    public Image updateImage() {
        Image imageToPaint;
        long curTs = System.currentTimeMillis(); // get the current time.
        if (curTs - lastRefreshTs > refreshTime) { // it is time to refresh.
            lastRefreshTs = curTs;
            if (++curImageIdx == nbImages) {
                curImageIdx = 0;
            }
        }
        imageToPaint = images[curImageIdx];
        return imageToPaint;
    }
}
