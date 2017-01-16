package map;

import images.ImagesLoader;
import utils.CurrentTimeSupplier;

import java.awt.*;
import java.util.Random;

/**
 * A point of the map.
 */
public class MapPoint {
    protected CurrentTimeSupplier currentTimeSupplier = new CurrentTimeSupplier();

    private final int rowIdx;
    private final int colIdx;

    private boolean isAvailable; // is available (empty case)?
    private boolean isPathway; // is a pathway?
    private boolean isMutable; // is a mutable?

    private Image image;

    private Image[] images; // array of images for animation.
    private int nbImages; // number of images of the animation.
    private int curImageIdx; // current image index of the animation.
    private int refreshTime; // refresh time of the animation (in ms).
    private long lastRefreshTs; // last refresh timestamp.

    private boolean isBombing; // is bombed (bomb on case)?
    private int nbFlames; // number of flames on that case (can be multiple because of crossing explosions).

    public MapPoint(int rowIdx, int colIdx) {
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        this.isAvailable = true;
    }

    public int getRowIdx() {
        return rowIdx;
    }

    public int getColIdx() {
        return colIdx;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isPathway() {
        return isPathway;
    }

    public void setPathway(boolean pathway) {
        isPathway = pathway;
    }

    public boolean isMutable() {
        return isMutable;
    }

    public void setMutable(boolean mutable) {
        isMutable = mutable;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image[] getImages() {
        return images;
    }

    public void setCurImageIdx(int curImageIdx) {
        this.curImageIdx = curImageIdx;
    }

    public int getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(int refreshTime) {
        this.refreshTime = refreshTime;
    }

    public void setLastRefreshTs(long lastRefreshTs) {
        this.lastRefreshTs = lastRefreshTs;
    }

    public boolean isBombing() {
        return isBombing;
    }

    public void setBombing(boolean bombing) {
        isBombing = bombing;
    }

    public void setNbFlames(int nbFlames) {
        this.nbFlames = nbFlames;
    }

    public void addFlame() {
        this.nbFlames++;
    }

    public void removeFlame() {
        this.nbFlames--;
    }

    public boolean isBurning() {
        return nbFlames > 0;
    }

    public void setImages(Image[] images, int nbImages) {
        this.images = images;
        this.nbImages = nbImages;
        this.curImageIdx = new Random().nextInt(nbImages);
    }

    /**
     * Set the image to burned.
     */
    public void setImageAsBurned() {
        image = ImagesLoader.imagesMatrix[ImagesLoader.singleBoomMatrixRowIdx][0]; // update image.
    }

    /**
     * Update the image.
     *
     * @return the image to paint.
     */
    Image updateImage() {
        Image imageToPaint;
        if (image != null) {
            imageToPaint = image;
        } else {
            long curTs = currentTimeSupplier.get().toEpochMilli(); // get the current time.
            if (curTs - lastRefreshTs > refreshTime) { // if it is time to refresh.
                lastRefreshTs = curTs;
                if (++curImageIdx == nbImages) curImageIdx = 0; // update the image to display.
            }
            imageToPaint = images[curImageIdx];
        }
        return imageToPaint;
    }

    /**
     * Paint the image.
     *
     * @param g       the graphics context
     * @param xScreen the abscissa on screen
     * @param yScreen the ordinate on screen
     */
    void paintBuffer(Graphics g, int xScreen, int yScreen) {
        g.drawImage(updateImage(), xScreen, yScreen, null);
    }
}