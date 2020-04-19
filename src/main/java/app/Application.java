package app;

import encoders.TollFreeNumberEncoder;

public class Application {

    public static void main(String[] args) {
        TollFreeNumberEncoder encoder = new TollFreeNumberEncoder();
        encoder.process();
    }
}
