package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Scanner;

public class PdfImageProcessor {
    public static void main(String[] args) {
        // Get user input for signature and PDF file location
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter signature (2 or 3 characters): ");
        String signature = scanner.nextLine();

        System.out.print("Enter PDF file location: ");
        String pdfFilePath = scanner.nextLine();

        // Load stamp image from resources
        BufferedImage stampImage = null;
        try {
            stampImage = ImageIO.read(PdfImageProcessor.class.getResourceAsStream("/stamp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Overlay signature onto stamp image
        BufferedImage stampedImage = ImageProcessor.overlaySignature(stampImage, signature);

        // Add stamped image to the PDF
        PdfProcessor.addStampToPdf(pdfFilePath, stampedImage);

        System.out.println("PDF processing complete. Check the stamped PDF file.");
    }
}
