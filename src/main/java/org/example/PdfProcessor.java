package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class PdfProcessor {
    public static void addStampToPdf(String pdfFilePath, BufferedImage stampedImage) throws IOException {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            for (PDPage page : document.getPages()) {
                PDImageXObject imageXObject;

                try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true);
                     ByteArrayOutputStream baos = new ByteArrayOutputStream()) {

                    float x = 10;
                    float y = 10;

                    ImageIO.write(stampedImage, "png", baos);
                    byte[] imageBytes = baos.toByteArray();

                    imageXObject = PDImageXObject.createFromByteArray(document, imageBytes, "stampedImage");

                    contentStream.drawImage(imageXObject, x, y);
                }
            }

            String fileNameWithoutExtension = pdfFilePath.replaceFirst("[.][^.]+$", "");
            String stampedPdfFilePath = fileNameWithoutExtension + "_stamped.pdf";
            document.save(new File(stampedPdfFilePath));
        }
    }
}