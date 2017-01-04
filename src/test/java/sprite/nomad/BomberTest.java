package sprite.nomad;

import static org.mockito.Mockito.mock;
import static sprite.nomad.BlueBomber.INVINCIBLE_TIME;
import static sprite.nomad.Bomber.Action.ACTION_DYING;
import static sprite.nomad.Bomber.Action.ACTION_WAITING;
import static sprite.nomad.Bomber.Action.ACTION_WALKING;

import java.io.IOException;
import java.time.Instant;

import org.assertj.core.api.WithAssertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import images.ImagesLoader;
import utils.CurrentTimeSupplier;
import utils.Direction;

public class BomberTest implements WithAssertions {

    @Before
    public void fillImagesMatrix() throws IOException {
        ImagesLoader.fillImagesMatrix();
    }

    @Test
    public void constructorShouldSetMembersWithTheExpectedValues() throws Exception {
        BlueBomber blueBomber = new BlueBomber(15, 30);

        // check members value.
        assertThat(blueBomber.getXMap()).isEqualTo(15);
        assertThat(blueBomber.getYMap()).isEqualTo(30);
        assertThat(blueBomber.getInitialXMap()).isEqualTo(blueBomber.getXMap());
        assertThat(blueBomber.getInitialYMap()).isEqualTo(blueBomber.getYMap());
        assertThat(blueBomber.getCurAction()).isEqualTo(ACTION_WAITING);
        assertThat(blueBomber.getLastAction()).isEqualTo(ACTION_WAITING);
        assertThat(blueBomber.getDeathImages()).
                isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberDeathMatrixRowIdx]);
        assertThat(blueBomber.getNbDeathFrame()).isEqualTo(ImagesLoader.NB_BOMBER_DEATH_FRAME);
        assertThat(blueBomber.getWaitImages()).
                isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWaitMatrixRowIdx]);
        assertThat(blueBomber.getNbWaitFrame()).isEqualTo(ImagesLoader.NB_BOMBER_WAIT_FRAME);
        assertThat(blueBomber.getWalkBackImages()).
                isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWalkBackMatrixRowIdx]);
        assertThat(blueBomber.getWalkFrontImages()).
                isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWalkFrontMatrixRowIdx]);
        assertThat(blueBomber.getWalkLeftImages()).
                isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWalkLeftMatrixRowIdx]);
        assertThat(blueBomber.getWalkRightImages()).
                isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWalkRightMatrixRowIdx]);
        assertThat(blueBomber.getNbWalkFrame()).isEqualTo(ImagesLoader.NB_BOMBER_WALK_FRAME);
        assertThat(blueBomber.getWinImages()).
                isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWinMatrixRowIdx]);
        assertThat(blueBomber.getNbWinFrame()).isEqualTo(ImagesLoader.NB_BOMBER_WIN_FRAME);
        assertThat(blueBomber.getRefreshTime()).isEqualTo(BlueBomber.REFRESH_TIME);
        assertThat(blueBomber.getMoveTime()).isEqualTo(BlueBomber.MOVING_TIME);
        assertThat(blueBomber.getInvincibilityTime()).isEqualTo(BlueBomber.INVINCIBLE_TIME);
    }

    @Test
    public void hasActionChangedWithTheSameActionShouldReturnFalse() {
        BlueBomber blueBomber = new BlueBomber(15, 30);

        // set test.
        blueBomber.setCurAction(ACTION_WAITING);
        blueBomber.setLastAction(ACTION_WAITING);

        // call & check.
        assertThat(blueBomber.hasActionChanged()).isFalse();
    }

    @Test
    public void hasActionChangedWithTheSameDirectionShouldReturnFalse() {
        BlueBomber blueBomber = new BlueBomber(15, 30);

        // set test.
        blueBomber.setCurAction(ACTION_WALKING);
        blueBomber.setCurDirection(Direction.NORTH);
        blueBomber.setLastAction(ACTION_WALKING);
        blueBomber.setLastDirection(Direction.NORTH);

        // call & check.
        assertThat(blueBomber.hasActionChanged()).isFalse();
    }

    @Test
    public void hasActionChangedWithADifferentActionShouldReturnTrue() throws Exception {
        BlueBomber blueBomber = new BlueBomber(15, 30);

        // set test.
        blueBomber.setCurAction(ACTION_WAITING);
        blueBomber.setLastAction(ACTION_WALKING); // last action != current action.

        // call & check.
        assertThat(blueBomber.hasActionChanged()).isTrue();
    }

    @Test
    public void hasActionChangedWithADifferentDirectionShouldReturnTrue() throws Exception {
        BlueBomber blueBomber = new BlueBomber(15, 30);

        // set test.
        blueBomber.setCurAction(ACTION_WALKING);
        blueBomber.setCurDirection(Direction.NORTH);
        blueBomber.setLastAction(ACTION_WALKING);
        blueBomber.setLastDirection(Direction.SOUTH);

        // call & check.
        assertThat(blueBomber.hasActionChanged()).isTrue();
    }

    @Test
    public void updateSpriteShouldSetTheExpectedMember() throws Exception {
        BlueBomber blueBomber = new BlueBomber(15, 30);

        // dying.
        blueBomber.setCurAction(Bomber.Action.ACTION_DYING);
        blueBomber.updateSprite();
        assertThat(blueBomber.getImages())
                .isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberDeathMatrixRowIdx]);
        assertThat(blueBomber.getNbImages()).isEqualTo(ImagesLoader.NB_BOMBER_DEATH_FRAME);

        // wait.
        blueBomber.setCurAction(Bomber.Action.ACTION_WAITING);
        blueBomber.updateSprite();
        assertThat(blueBomber.getImages())
                .isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWaitMatrixRowIdx]);
        assertThat(blueBomber.getNbImages()).isEqualTo(ImagesLoader.NB_BOMBER_WAIT_FRAME);

        // walking back.
        blueBomber.setCurAction(Bomber.Action.ACTION_WALKING);
        blueBomber.setCurDirection(Direction.NORTH);
        blueBomber.updateSprite();
        assertThat(blueBomber.getImages())
                .isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWalkBackMatrixRowIdx]);
        assertThat(blueBomber.getNbImages()).isEqualTo(ImagesLoader.NB_BOMBER_WALK_FRAME);

        // walking front.
        blueBomber.setCurAction(Bomber.Action.ACTION_WALKING);
        blueBomber.setCurDirection(Direction.SOUTH);
        blueBomber.updateSprite();
        assertThat(blueBomber.getImages())
                .isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWalkFrontMatrixRowIdx]);
        assertThat(blueBomber.getNbImages()).isEqualTo(ImagesLoader.NB_BOMBER_WALK_FRAME);

        // walking left.
        blueBomber.setCurAction(Bomber.Action.ACTION_WALKING);
        blueBomber.setCurDirection(Direction.WEST);
        blueBomber.updateSprite();
        assertThat(blueBomber.getImages())
                .isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWalkLeftMatrixRowIdx]);
        assertThat(blueBomber.getNbImages()).isEqualTo(ImagesLoader.NB_BOMBER_WALK_FRAME);

        // walking right.
        blueBomber.setCurAction(Bomber.Action.ACTION_WALKING);
        blueBomber.setCurDirection(Direction.EAST);
        blueBomber.updateSprite();
        assertThat(blueBomber.getImages())
                .isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWalkRightMatrixRowIdx]);
        assertThat(blueBomber.getNbImages()).isEqualTo(ImagesLoader.NB_BOMBER_WALK_FRAME);

        // win.
        blueBomber.setCurAction(Bomber.Action.ACTION_WINING);
        blueBomber.updateSprite();
        assertThat(blueBomber.getImages())
                .isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.blueBomberWinMatrixRowIdx]);
        assertThat(blueBomber.getNbImages()).isEqualTo(ImagesLoader.NB_BOMBER_WIN_FRAME);
    }

    @Test
    public void isFinishedShouldReturnTrue() throws Exception {
        BlueBomber blueBomber = new BlueBomber(15, 30);

        // set test.
        blueBomber.setCurAction(ACTION_DYING);
        blueBomber.updateSprite();
        blueBomber.setCurImageIdx(ImagesLoader.NB_BOMBER_DEATH_FRAME - 1);

        // call & check.
        assertThat(blueBomber.isFinished()).isTrue();
    }

    @Test
    public void isFinishedWithACurActionDifferentOfDyingShouldReturnFalse() throws Exception {
        BlueBomber blueBomber = new BlueBomber(15, 30);

        // set test.
        blueBomber.setCurAction(ACTION_WALKING);
        blueBomber.setCurDirection(Direction.NORTH);
        blueBomber.updateSprite();
        blueBomber.setCurImageIdx(ImagesLoader.NB_BOMBER_DEATH_FRAME - 1);

        // call & check.
        assertThat(blueBomber.isFinished()).isFalse();
    }

    @Test
    public void isFinishedWithACurImageIdxDifferentOfNbImagesMinus1ShouldReturnFalse() throws Exception {
        BlueBomber blueBomber = new BlueBomber(15, 30);

        // set test.
        blueBomber.setCurAction(ACTION_DYING);
        blueBomber.updateSprite();
        blueBomber.setCurImageIdx(0);

        // call & check.
        assertThat(blueBomber.isFinished()).isFalse();
    }

    @Test
    public void initShouldSetMembersWithTheExpectedValues() throws Exception {
        BlueBomber blueBomber = new BlueBomber(10, 20);

        // mock CurrentTimeSupplier class to set currentTimeMillis to 1000ms.
        CurrentTimeSupplier currentTimeSupplier = mock(CurrentTimeSupplier.class);
        Mockito.when(currentTimeSupplier.get()).thenReturn(Instant.ofEpochMilli(1000L));
        blueBomber.setCurrentTimeSupplier(currentTimeSupplier);

        // set test (updateSprites members with value != than the expected ones).
        blueBomber.setCurAction(ACTION_DYING);
        blueBomber.setXMap(30);
        blueBomber.setYMap(40);
        blueBomber.setInvincible(false);

        // init bomber and check values.
        blueBomber.init();
        assertThat(blueBomber.getXMap()).isEqualTo(blueBomber.getInitialXMap());
        assertThat(blueBomber.getYMap()).isEqualTo(blueBomber.getInitialYMap());
        assertThat(blueBomber.getCurAction()).isEqualTo(ACTION_WAITING);
        assertThat(blueBomber.isInvincible()).isEqualTo(true);
        assertThat(blueBomber.getLastInvincibilityTs()).isEqualTo(1000L);
    }

    @Test
    public void updateImageWhenInvincibleTimeReachedShouldSetIsInvincibleToFalse() throws Exception {
        BlueBomber blueBomber = new BlueBomber(10, 20);

        // mock CurrentTimeSupplier class to set currentTimeMillis to 1000ms.
        CurrentTimeSupplier currentTimeSupplier = mock(CurrentTimeSupplier.class);
        Mockito.when(currentTimeSupplier.get()).thenReturn(Instant.ofEpochMilli(10000L));
        blueBomber.setCurrentTimeSupplier(currentTimeSupplier);

        // set test.
        blueBomber.setCurAction(ACTION_WAITING);
        blueBomber.setInvincible(true); // set to invincible.

        // 1st case: should continue to be invincible.
        blueBomber.setLastInvincibilityTs(10000L - INVINCIBLE_TIME); // limit not reached.
        blueBomber.updateImage();
        assertThat(blueBomber.isInvincible()).isTrue();

        // 2nd case: should stop being invincible.
        blueBomber.setLastInvincibilityTs(10000L - INVINCIBLE_TIME - 1); // limit reached.
        blueBomber.updateImage();
        assertThat(blueBomber.isInvincible()).isFalse();
    }
}