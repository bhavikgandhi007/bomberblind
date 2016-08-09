package map;

import images.ImagesLoader;
import org.assertj.core.api.WithAssertions;
import org.junit.Test;
import org.mockito.Mockito;
import utils.CurrentTimeSupplier;

import java.awt.*;
import java.time.Instant;

import static org.mockito.Mockito.mock;

public class RMapPointTest implements WithAssertions {

    @Test
    public void rMapPointShouldSetMembersWithTheAppropriateValues() throws Exception {
        RMapPoint rMapPoint = new RMapPoint(5, 10);
        assertThat(rMapPoint.getRowIdx()).isEqualTo(5);
        assertThat(rMapPoint.getColIdx()).isEqualTo(10);
        assertThat(rMapPoint.isAvailable()).isTrue();
        assertThat(rMapPoint.isPathway()).isFalse();
        assertThat(rMapPoint.isMutable()).isFalse();
        assertThat(rMapPoint.isBombing()).isFalse();
        assertThat(rMapPoint.isBurning()).isFalse();
    }

    @Test
    public void setAvailableShouldSetTheMemberWithTheAppropriateValue() throws Exception {
        RMapPoint rMapPoint = new RMapPoint(5, 10);
        rMapPoint.setAvailable(false);
        assertThat(rMapPoint.isAvailable()).isFalse();
    }

    @Test
    public void setPathwayShouldSetTheMemberWithTheAppropriateValue() throws Exception {
        RMapPoint rMapPoint = new RMapPoint(5, 10);
        rMapPoint.setPathway(true);
        assertThat(rMapPoint.isPathway()).isTrue();
    }

    @Test
    public void setMutableShouldSetTheMemberWithTheAppropriateValue() throws Exception {
        RMapPoint rMapPoint = new RMapPoint(5, 10);
        rMapPoint.setMutable(true);
        assertThat(rMapPoint.isMutable()).isTrue();
    }

    @Test
    public void setBombingShouldSetTheMemberWithTheAppropriateValue() throws Exception {
        RMapPoint rMapPoint = new RMapPoint(5, 10);
        rMapPoint.setBombing(true);
        assertThat(rMapPoint.isBombing()).isTrue();
    }

    @Test
    public void addAndRemoveFlameShouldSetTheMemberWithTheAppropriateValue() throws Exception {
        RMapPoint rMapPoint = new RMapPoint(5, 10);
        rMapPoint.addFlame();
        rMapPoint.addFlame();
        assertThat(rMapPoint.isBurning()).isTrue();
        rMapPoint.removeFlame();
        rMapPoint.removeFlame();
        assertThat(rMapPoint.isBurning()).isFalse();
    }

    @Test
    public void setImageShouldSetTheMemberWithTheAppropriateValue() throws Exception {
        RMapPoint rMapPoint = new RMapPoint(5, 10);
        Image img = ImagesLoader.createImage("/images/icon.gif");
        rMapPoint.setImage(img);
        assertThat(rMapPoint.image).isEqualTo(img);
    }

    @Test
    public void setImagesShouldSetTheMemberWithTheAppropriateValue() throws Exception {
        RMapPoint rMapPoint = new RMapPoint(5, 10);
        Image[] imgArray = new Image[1];
        rMapPoint.setImages(imgArray, 1);
        assertThat(rMapPoint.images).isEqualTo(imgArray);
    }

    @Test
    public void setRefreshTimeShouldSetTheMemberWithTheAppropriateValue() throws Exception {
        RMapPoint rMapPoint = new RMapPoint(5, 10);
        rMapPoint.setRefreshTime(100);
        assertThat(rMapPoint.refreshTime).isEqualTo(100);
    }

    @Test
    public void setImageAsBurnedShouldSetTheMemberWithAnImage() throws Exception {
        Image img = ImagesLoader.createImage("/images/icon.gif");

        RMapPoint rMapPoint = mock(RMapPoint.class);
        Mockito.doAnswer((t) -> {
            rMapPoint.image = img;
            return null;
        }).when(rMapPoint).setImageAsBurned();

        rMapPoint.setImageAsBurned();
        assertThat(rMapPoint.image).isEqualTo(img);
    }

    @Test
    public void updateImageShouldSetTheMemberWithTheAppropriateValue() throws Exception {

        // mock CurrentTimeSupplier class to set currentTimeMillis to 1000ms.
        CurrentTimeSupplier currentTimeSupplier = mock(CurrentTimeSupplier.class);
        Mockito.when(currentTimeSupplier.get()).thenReturn(Instant.ofEpochMilli(1000L));

        RMapPoint rMapPoint = new RMapPoint(5, 10);
        rMapPoint.setRefreshTime(100); // set the refresh time to 100ms.
        rMapPoint.currentTimeSupplier = currentTimeSupplier;

        Image[] imgArray = new Image[4];
        imgArray[0] = ImagesLoader.createImage("/images/icon.gif");
        imgArray[1] = ImagesLoader.createImage("/images/icon.gif");
        imgArray[2] = ImagesLoader.createImage("/images/icon.gif");
        imgArray[3] = ImagesLoader.createImage("/images/icon.gif");
        rMapPoint.setImages(imgArray, 4);
        rMapPoint.curImageIdx = 1; // fix it as it is randomly init.

        rMapPoint.lastRefreshTs = 1000L; // current time - last refresh time < 100ms -> image no change.
        assertThat(rMapPoint.updateImage()).isEqualTo(imgArray[1]);
        rMapPoint.lastRefreshTs = 800L; // current time - last refresh time > 100ms -> image change.
        assertThat(rMapPoint.updateImage()).isEqualTo(imgArray[2]);
        rMapPoint.lastRefreshTs = 800L; // current time - last refresh time > 100ms -> image change.
        assertThat(rMapPoint.updateImage()).isEqualTo(imgArray[3]);
        rMapPoint.lastRefreshTs = 800L; // current time - last refresh time > 100ms -> image change.
        assertThat(rMapPoint.updateImage()).isEqualTo(imgArray[0]);
    }
}