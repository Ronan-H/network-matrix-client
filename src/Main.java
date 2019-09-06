import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting...");
        Client client = new Client("192.168.0.28", 28891);
        MatrixController matrixController = new MatrixController(client);
        System.out.println("Connected.");

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        BufferedImage oldScreenshot = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
        while (true) {
            Point mouseLoc = MouseInfo.getPointerInfo().getLocation();
            Rectangle rect = new Rectangle(mouseLoc.x - 8, mouseLoc.y - 8, 16, 16);
            BufferedImage screenshot = robot.createScreenCapture(rect);

            BufferedImage screenshotCropped = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = screenshotCropped.getGraphics();
            graphics.drawImage(screenshot, 0, 0, 16, 16, null);
            graphics.dispose();
            screenshot = screenshotCropped;

            int numUpdated = 0;

            for (int y = 0; y < 16; y++) {
                for (int x = 0; x < 16; x++) {
                    int rgb = screenshot.getRGB(x, y);
                    int oldRgb = oldScreenshot.getRGB(x, y);

                    if (rgb != oldRgb) {
                        // update pixel
                        int r = (rgb >> 16) & 0xFF;
                        int g = (rgb >> 8) & 0xFF;
                        int b = rgb & 0xFF;

                        matrixController.setPixelValue(x, y, r, g, b);

                        numUpdated++;
                    }
                }
            }

            if (numUpdated > 0) {
                matrixController.show();
            }

            try {
                Thread.sleep(32);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

            oldScreenshot = deepCopy(screenshot);
        }

        //System.out.println("Finished. Exiting...");
        //client.close();
    }

    private static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
