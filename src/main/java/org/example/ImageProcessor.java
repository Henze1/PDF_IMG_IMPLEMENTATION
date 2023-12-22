package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageProcessor {
    public static BufferedImage overlaySignature(BufferedImage stampImage, String signature) {
        Graphics2D graphics = stampImage.createGraphics();

        Font font = new Font("Arial", Font.BOLD, 30);
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);

        int x = (stampImage.getWidth() - graphics.getFontMetrics().stringWidth(signature)) / 2;
        int y = stampImage.getHeight() / 2;

        graphics.drawString(signature, x, y);

        graphics.dispose();

        return stampImage;
    }
}
