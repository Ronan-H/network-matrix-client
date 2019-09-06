import java.awt.*;

public class MatrixController {
    private Client client;
    private UI ui;
    private Color[][] colours;

    public MatrixController(Client client) {
        this.client = client;

        colours = new Color[16][16];
        ui = new UI(colours);
    }

    public void setPixelValue(int x, int y, int r, int g, int b) {
        client.setPixelValue(x, y, r, g, b);

        b += 15;
        float[] hsb = Color.RGBtoHSB(r, g, b, null);
        hsb[1] = (float) Math.min(hsb[1] + 0.1, 1);
        hsb[2] = (float) Math.min(hsb[2] + 0.3, 1);
        colours[y][x] = new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }

    public void show() {
        client.show();
        ui.repaint();
    }
}
