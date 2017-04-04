package sprite.nomad;

import images.ImagesLoader;
import org.assertj.core.api.WithAssertions;
import org.junit.Before;
import org.junit.Test;
import sprite.SpriteType;
import utils.Direction;

import java.io.IOException;

import static sprite.SpriteAction.*;

public class WalkingEnemyTest implements WithAssertions {

    @Before
    public void fillImagesMatrix() throws IOException {
        ImagesLoader.fillImagesMatrix();
    }

    @Test
    public void constructorShouldSetMembersWithTheExpectedValues() throws Exception {
        Zora zora = new Zora(15, 30);
        assertThat(zora.getxMap()).isEqualTo(15);
        assertThat(zora.getyMap()).isEqualTo(30);
        assertThat(zora.getSpriteType()).isEqualTo(SpriteType.TYPE_SPRITE_WALKING_ENEMY);

        // - dying values.
        assertThat(zora.getDeathImages()).isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.deathMatrixRowIdx]);
        assertThat(zora.getNbDeathFrame()).isEqualTo(ImagesLoader.NB_DEATH_FRAME);
        assertThat(zora.getDeathRefreshTime()).isEqualTo(Zora.DEATH_REFRESH_TIME);

        // - walking values.
        assertThat(zora.getWalkBackImages()).isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.zoraWalkBackMatrixRowIdx]);
        assertThat(zora.getWalkFrontImages()).isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.zoraWalkFrontMatrixRowIdx]);
        assertThat(zora.getWalkLeftImages()).isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.zoraWalkLeftMatrixRowIdx]);
        assertThat(zora.getWalkRightImages()).isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.zoraWalkRightMatrixRowIdx]);
        assertThat(zora.getNbWalkFrame()).isEqualTo(ImagesLoader.NB_ZORA_WALK_FRAME);
        assertThat(zora.getWalkRefreshTime()).isEqualTo(Zora.WALK_REFRESH_TIME);

        assertThat(zora.getActingTime()).isEqualTo(Zora.ACTING_TIME);
        assertThat(zora.getCurSpriteAction()).isEqualTo(ACTION_WALKING);
        assertThat(zora.getRefreshTime()).isEqualTo(Zora.WALK_REFRESH_TIME);
    }

    @Test
    public void isActionAllowedShouldReturnTrue() throws Exception {
        Zora zora = new Zora(15, 30);
        assertThat(zora.isActionAllowed(ACTION_DYING)).isTrue();
        assertThat(zora.isActionAllowed(ACTION_WALKING)).isTrue();
    }

    @Test
    public void isActionAllowedShouldReturnFalse() throws Exception {
        Zora zora = new Zora(15, 30);
        assertThat(zora.isActionAllowed(ACTION_BREAKING)).isFalse();
        assertThat(zora.isActionAllowed(ACTION_FLYING)).isFalse();
        assertThat(zora.isActionAllowed(ACTION_WAITING)).isFalse();
        assertThat(zora.isActionAllowed(ACTION_WINING)).isFalse();
    }

    @Test
    public void hasActionChangedWithTheSameActionShouldReturnFalse() {
        Zora zora = new Zora(15, 30);

        // set test.
        zora.setCurSpriteAction(ACTION_DYING);
        zora.setLastSpriteAction(ACTION_DYING);

        // call & check.
        assertThat(zora.hasActionChanged()).isFalse();
    }

    @Test
    public void hasActionChangedAndWalkingToTheSameDirectionShouldReturnFalse() {
        Zora zora = new Zora(15, 30);

        // set test.
        zora.setCurSpriteAction(ACTION_WALKING);
        zora.setCurDirection(Direction.DIRECTION_NORTH);
        zora.setLastSpriteAction(ACTION_WALKING);
        zora.setLastDirection(Direction.DIRECTION_NORTH);

        // call & check.
        assertThat(zora.hasActionChanged()).isFalse();
    }

    @Test
    public void hasActionChangedWithADifferentActionShouldReturnTrue() throws Exception {
        Zora zora = new Zora(15, 30);

        // set test.
        zora.setCurSpriteAction(ACTION_WALKING);
        zora.setLastSpriteAction(ACTION_DYING);

        // call & check.
        assertThat(zora.hasActionChanged()).isTrue();
    }

    @Test
    public void hasActionChangedWalkingToADifferentDirectionShouldReturnTrue() throws Exception {
        Zora zora = new Zora(15, 30);

        // set test.
        zora.setCurSpriteAction(ACTION_WALKING);
        zora.setCurDirection(Direction.DIRECTION_NORTH);
        zora.setLastSpriteAction(ACTION_WALKING);
        zora.setLastDirection(Direction.DIRECTION_SOUTH);

        // call & check.
        assertThat(zora.hasActionChanged()).isTrue();
    }

    @Test
    public void updateSpriteShouldSetTheExpectedMember() throws Exception {
        Zora zora = new Zora(15, 30);

        // dying.
        zora.setCurSpriteAction(ACTION_DYING);
        zora.updateSprite();
        assertThat(zora.getImages()).isEqualTo(zora.getDeathImages());
        assertThat(zora.getNbImages()).isEqualTo(zora.getNbDeathFrame());
        assertThat(zora.getRefreshTime()).isEqualTo(Zora.DEATH_REFRESH_TIME);

        // walking back.
        zora.setCurSpriteAction(ACTION_WALKING);
        zora.setCurDirection(Direction.DIRECTION_NORTH);
        zora.updateSprite();
        assertThat(zora.getImages()).isEqualTo(zora.getWalkBackImages());
        assertThat(zora.getNbImages()).isEqualTo(zora.getNbWalkFrame());
        assertThat(zora.getRefreshTime()).isEqualTo(Zora.WALK_REFRESH_TIME);

        // walking front.
        zora.setCurSpriteAction(ACTION_WALKING);
        zora.setCurDirection(Direction.DIRECTION_SOUTH);
        zora.updateSprite();
        assertThat(zora.getImages()).isEqualTo(zora.getWalkFrontImages());
        assertThat(zora.getNbImages()).isEqualTo(zora.getNbWalkFrame());
        assertThat(zora.getRefreshTime()).isEqualTo(Zora.WALK_REFRESH_TIME);

        // walking left.
        zora.setCurSpriteAction(ACTION_WALKING);
        zora.setCurDirection(Direction.DIRECTION_WEST);
        zora.updateSprite();
        assertThat(zora.getImages()).isEqualTo(zora.getWalkLeftImages());
        assertThat(zora.getNbImages()).isEqualTo(zora.getNbWalkFrame());
        assertThat(zora.getRefreshTime()).isEqualTo(Zora.WALK_REFRESH_TIME);

        // walking right.
        zora.setCurSpriteAction(ACTION_WALKING);
        zora.setCurDirection(Direction.DIRECTION_EAST);
        zora.updateSprite();
        assertThat(zora.getImages()).isEqualTo(zora.getWalkRightImages());
        assertThat(zora.getNbImages()).isEqualTo(zora.getNbWalkFrame());
        assertThat(zora.getRefreshTime()).isEqualTo(Zora.WALK_REFRESH_TIME);
    }
}