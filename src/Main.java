public class Main {

    public static void main(String[] args) {
        System.out.println("Starting...");
        Client client = new Client("192.168.0.28", 28891);
        System.out.println("Connected. Sending colours...");

        int[][] colours = {{255, 0, 0}, {0, 255, 0}, {0, 0, 255}};
        int counter = 0;

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                int[] colour = colours[counter++ % 3];
                String line = String.format("%d,%d,%d,%d,%d",
                        i, j, colour[0], colour[1], colour[2]);
                client.writeString(line);
            }
        }

        System.out.println("Finished. Exiting...");
        client.close();
    }

}
