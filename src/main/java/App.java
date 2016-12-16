import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import images.ImagesLoader;

public class App extends JFrame {

    private final int SCREEN_WIDTH = 1080;
    private final int SCREEN_HEIGHT = 720;
    private final String PACKAGE_NAME = "Bomberblind";
    private final String PACKAGE_VERSION = "1.0-SNAPSHOT";

    private App() {
        System.out.println(PACKAGE_NAME + " v" + PACKAGE_VERSION);

        try {
            System.out.println("- load images ... ");
            ImagesLoader.fillImagesMatrix();

            System.out.println("- create and set JFrame ... ");
            setJframe();

            System.out.println("- create and set JPanel ... ");
            GameJpanel gameJpanel = new GameJpanel(SCREEN_WIDTH, SCREEN_HEIGHT);
            setContentPane(gameJpanel);

            System.out.println("- run game. ");
            setVisible(true);

        } catch (Exception e) {
            System.err.println("App: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new App();
    }

    private void setJframe() throws IOException {
        setTitle(PACKAGE_NAME + " v" + PACKAGE_VERSION);
        setSize(SCREEN_WIDTH + 8, SCREEN_HEIGHT + 25); // offset borders and title bars.
        setLocationRelativeTo(null); // align to center.
        setIconImage(ImageIO.read(App.class.getResource("/images/icon.gif")));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
    }
}
