package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class PdfImageProcessor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String signature = getValidatedSignature(scanner);
        String pdfFilePath = getValidatedPdfFilePath(scanner);

        BufferedImage stampImage;
        try {
            stampImage = loadImage();


        } catch (IOException e) {
            System.err.println("Error loading stamp image: " + e.getMessage());
            return;
        }

        BufferedImage stampedImage = ImageProcessor.overlaySignature(stampImage, signature);

        try {
            PdfProcessor.addStampToPdf(pdfFilePath, stampedImage);
            System.out.println("PDF processing complete. Check the stamped PDF file.");
        } catch (IOException e) {
            System.err.println("Error processing PDF: " + e.getMessage());
        }
    }

    private static BufferedImage loadImage() throws IOException {
        BufferedImage image = ImageIO.read(new File(Objects.requireNonNull(PdfImageProcessor.class.getClassLoader().getResource("img/png-clipart-postmark-stamp-seal-postmark-stamp-seal-thumbnail.png")).getPath()));
        if (image == null) {
            throw new IOException("Failed to load image from: " + "img/png-clipart-postmark-stamp-seal-postmark-stamp-seal-thumbnail.png");
        }
        return image;
    }

    private static String getValidatedSignature(Scanner scanner) {
        while (true) {
            System.out.print("Enter signature (2 or 3 characters): ");
            String signature = scanner.nextLine();

            if (signature.length() >= 2 && signature.length() <= 3) {
                return signature;
            }

            System.err.println("Invalid signature length. Please enter 2 or 3 characters.");
        }
    }

    private static String getValidatedPdfFilePath(Scanner scanner) {
        while (true) {
            System.out.print("Enter PDF file location: ");
            String pdfFilePath = scanner.nextLine();

            File file = new File(pdfFilePath);
            if (file.exists() && file.isFile() && pdfFilePath.endsWith(".pdf")) {
                return pdfFilePath;
            }

            System.err.println("Invalid PDF file path. Please ensure the file exists and has a .pdf extension.");
        }
    }
}