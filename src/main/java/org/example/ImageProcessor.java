package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProcessor {
    public static void main(String[] args) {
        // Specify the input and output file paths
        String inputFile = "path/to/input_image.jpg";
        String outputFile = "path/to/output_image.jpg";

        // Process the image
        processImage(inputFile, outputFile);
    }

    private static void processImage(String inputFilePath, String outputFilePath) {
        try {
            // Read the original image
            BufferedImage originalImage = ImageIO.read(new File(inputFilePath));

            // Perform grayscale conversion
            BufferedImage processedImage = convertToGrayscale(originalImage);

            // Save the processed image
            ImageIO.write(processedImage, "jpg", new File(outputFilePath));

            System.out.println("Image processing completed. Processed image saved to: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BufferedImage convertToGrayscale(BufferedImage image) {
        // Create a new BufferedImage for the grayscale image
        BufferedImage grayscaleImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        // Perform the grayscale conversion
        grayscaleImage.getGraphics().drawImage(image, 0, 0, null);

        return grayscaleImage;
    }
}
