package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    public static BufferedImage overlaySignature(BufferedImage stampImage, String signature) {
        // Create a Graphics2D object to perform the overlay
        Graphics2D graphics = stampImage.createGraphics();

        // Set font properties for the signature
        Font font = new Font("Arial", Font.BOLD, 30);
        graphics.setFont(font);
        graphics.setColor(Color.BLACK);

        // Calculate the position to center the signature on the stamp image
        int x = (stampImage.getWidth() - graphics.getFontMetrics().stringWidth(signature)) / 2;
        int y = stampImage.getHeight() / 2;

        // Draw the signature onto the stamp image
        graphics.drawString(signature, x, y);

        // Dispose of the Graphics2D object to free up resources
        graphics.dispose();

        return stampImage;
    }
}
