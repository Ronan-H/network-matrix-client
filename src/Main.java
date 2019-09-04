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
                byte[] bytes = {(byte) i, (byte) j, (byte) colour[0], (byte) colour[1], (byte) colour[2]};
                client.writeBytes(bytes);
            }
        }

        System.out.println("Finished. Exiting...");
        client.close();
    }

}
