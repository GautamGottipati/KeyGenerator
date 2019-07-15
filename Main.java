import cryptoutils.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Generating....");
        Generator.generate();
        KeyLoader.loadAsBinary("secret",".key");
        KeyLoader.loadAsBinary("secret",".pub");
        System.out.println("Generated Successfully...");
    }
}
