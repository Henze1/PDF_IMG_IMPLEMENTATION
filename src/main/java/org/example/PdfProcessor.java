package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfProcessor {
    public static void addStampToPdf(String pdfFilePath, BufferedImage stampedImage) {
        try {
            PDDocument document = PDDocument.load(new File(pdfFilePath));
            for (PDPage page : document.getPages()) {
                PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);

                // Define the position for the stamped image in the bottom left corner
                float x = 10;
                float y = 10;

                // Add the stamped image to the PDF page
                contentStream.drawImage(stampedImage, x, y);

                // Close the content stream
                contentStream.close();
            }

            // Save the new PDF
            document.save(new File("stamped_" + pdfFilePath));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}