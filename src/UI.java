import javax.swing.*;
import java.awt.*;

public class UI extends JFrame {
    private ColorPanel panel;

    public UI(Color[][] colors) {
        panel = new ColorPanel(15, colors);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        setTitle("Network Matrix Client");
        setVisible(true);
    }

    class ColorPanel extends JPanel {
        private static final int SIZE = 16;
        private int scale;
        private int scaledSize;
        private Color[][] colors;
        private int padding = 4;

        public ColorPanel(int scale, Color[][] colors) {
            this.scale = scale;
            this.colors = colors;
            // account for 1 pixel border
            this.scaledSize = SIZE * (scale + 1) + 1 + padding;

            setIgnoreRepaint(true);

            Dimension dim = new Dimension(scaledSize, scaledSize);
            setMinimumSize(dim);
            setMaximumSize(dim);
            setPreferredSize(dim);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(Color.BLACK);
            g.fillRect(0, 0, scaledSize, scaledSize);

            // draw colors
            int cellSize = scale + 1;
            for (int y = 0; y < SIZE; y++) {
                for (int x = 0; x < SIZE; x++) {
                    g.setColor(colors[y][x]);
                    g.fillOval(
                            (x * cellSize) + 1 + padding,
                            (y * cellSize) + 1 + padding,
                            scale - padding,
                            scale - padding
                    );
                }
            }
        }
    }
}