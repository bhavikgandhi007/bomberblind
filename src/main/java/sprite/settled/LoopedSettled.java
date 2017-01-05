package sprite.settled;

import sprite.SpriteType;

import java.awt.*;

import static sprite.settled.LoopedSettled.Status.STATUS_ALIVE;
import static sprite.settled.LoopedSettled.Status.STATUS_ENDED;

/**
 * Abstract class of a looped settled sprite.
 * The sprite loops a certain number of times.
 */
@SuppressWarnings("WeakerAccess") // needed for mokito.spy.
public abstract class LoopedSettled extends Settled {

    /**
     * enum the different available action of a looped settled.
     */
    enum Status {
        STATUS_ALIVE, STATUS_ENDED
    }

    private final int nbTimes; // number of times the sprite should be painted.

    private Status curStatus = STATUS_ALIVE; // current status of the sprite.

    /**
     * Create a looped settled.
     *
     * @param rowIdx      the map row index of the sprite
     * @param colIdx      the map col index of the sprite
     * @param spriteType  the sprite's type
     * @param refreshTime the sprite refreshTime (i.e. fps)
     * @param images      the sprite's array of images
     * @param nbImages    the number of images
     * @param nbTimes     the max number of times the sprite must loop
     */
    LoopedSettled(int rowIdx, int colIdx, SpriteType spriteType, int refreshTime, Image[] images, int nbImages,
                  int nbTimes) {
        super(rowIdx, colIdx, spriteType, refreshTime, images, nbImages);
        this.nbTimes = nbTimes;
    }

    int getNbTimes() {
        return nbTimes;
    }

    Status getCurStatus() {
        return curStatus;
    }

    void setCurStatus(Status curStatus) {
        this.curStatus = curStatus;
    }

    @Override
    public boolean updateStatus() {
        if ((getCurLoopIdx() == nbTimes - 1) && getCurImageIdx() == getNbImages() - 1) {
            curStatus = STATUS_ENDED;
            return true;
        }
        return false;
    }

    @Override
    public boolean isFinished() {
        return curStatus.equals(STATUS_ENDED);
    }
}