package sprite.nomad;

import static sprite.SpriteAction.ACTION_FLYING;

import java.io.IOException;

import org.assertj.core.api.WithAssertions;
import org.junit.Before;
import org.junit.Test;

import images.ImagesLoader;
import sprite.SpriteType;
import utils.Direction;

public class WhiteBirdTest implements WithAssertions {

    @Before
    public void fillImagesMatrix() throws IOException {
        ImagesLoader.fillImagesMatrix();
    }

    @Test
    public void constructorShouldSetMembersWithTheExpectedValues() throws Exception {
        WhiteBird whiteBird = new WhiteBird(15, 30, Direction.EAST, 5);

        // check members value.
        assertThat(whiteBird.getxMap()).isEqualTo(15);
        assertThat(whiteBird.getyMap()).isEqualTo(30);
        assertThat(whiteBird.getSpriteType()).isEqualTo(SpriteType.TYPE_FLYING_NOMAD);
        assertThat(whiteBird.getFlyFrontImages()).isNull();
        assertThat(whiteBird.getFlyBackImages()).isNull();
        assertThat(whiteBird.getFlyLeftImages())
                .isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.birdFlyLeftMatrixRowIdx]);
        assertThat(whiteBird.getFlyRightImages())
                .isEqualTo(ImagesLoader.imagesMatrix[ImagesLoader.birdFlyRightMatrixRowIdx]);
        assertThat(whiteBird.getNbFlyFrame()).isEqualTo(ImagesLoader.NB_BIRD_FLY_FRAME);
        assertThat(whiteBird.getRefreshTime()).isEqualTo(WhiteBird.REFRESH_TIME);
        assertThat(whiteBird.getActingTime()).isEqualTo(WhiteBird.ACTING_TIME);
        assertThat(whiteBird.getCurSpriteAction()).isEqualTo(ACTION_FLYING);
        assertThat(whiteBird.getLastSpriteAction()).isEqualTo(ACTION_FLYING);
        assertThat(whiteBird.getCurDirection()).isEqualTo(Direction.EAST);
        assertThat(whiteBird.getLastDirection()).isEqualTo(Direction.EAST);
        assertThat(whiteBird.getDeviation()).isEqualTo(5);
        assertThat(whiteBird.getMoveIdx()).isEqualTo(0);
        assertThat(whiteBird.getCurImageIdx()).isBetween(0, ImagesLoader.NB_BIRD_FLY_FRAME - 1);
    }
}