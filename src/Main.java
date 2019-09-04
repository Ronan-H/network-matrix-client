import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting...");
        Client client = new Client("192.168.0.28", 28891);
        System.out.println("Connected.");

        int[][] colours = {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}};
        int counter = 0;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int[] colour = colours[counter++ % 3];
            }
        }
        client.show();

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        while (true) {
            Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
            Rectangle rect = new Rectangle(mouseLoc.x - 8, mouseLoc.y - 8, 16, 16);
            BufferedImage screenshot = robot.createScreenCapture(rect);

            for (int y = 0; y < 16; y++) {
                for (int x = 0; x < 16; x++) {
                    int rgb = screenshot.getRGB(x, y);

                    int r = (rgb >> 16) & 0xFF;
                    int g = (rgb >> 8) & 0xFF;
                    int b = rgb & 0xFF;

                    client.setPixelValue(x, y, r, g, b);
                }
            }

            client.show();

            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //System.out.println("Finished. Exiting...");
        //client.close();
    }

}
