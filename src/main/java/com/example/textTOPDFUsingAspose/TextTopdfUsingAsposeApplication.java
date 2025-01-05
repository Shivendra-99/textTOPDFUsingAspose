package com.example.textTOPDFUsingAspose;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TextTopdfUsingAsposeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextTopdfUsingAsposeApplication.class, args);
	}

	@PostMapping("/readDataFromLocal")
	public String getReadDataFroLocal() {
		//String pdfPath = "C:\\Users\\sanus\\Downloads\\50MB-TXT-FILE.txt";
		// String pdfPath = "output.pdf";
		// Document document = new Document();
		String txtFilePath = "C:\\Users\\sanus\\Downloads\\50MB-TXT-FILE.txt";
        String pdfFilePath = "C:\\Users\\sanus\\Downloads\\50MB-TXT-FILE.pdf";
        // Create a new PDF document
        PDDocument document = new PDDocument();
        
        // Load the text file
        try {
            BufferedReader reader = new BufferedReader(new FileReader(txtFilePath));
            String line;
            
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            
            float margin = 50;
            float yPosition = page.getMediaBox().getHeight() - margin;
            float leading = 14.5f;
            float startX = margin;
            float startY = yPosition;
            
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);

            while ((line = reader.readLine()) != null) {
                // Check if new page is needed
                if (yPosition <= margin) {
                    contentStream.endText();
                    contentStream.close();
                    page = new PDPage(PDRectangle.A4);
                    document.addPage(page);
                    contentStream = new PDPageContentStream(document, page);
                    contentStream.setFont(PDType1Font.HELVETICA, 12);
                    yPosition = page.getMediaBox().getHeight() - margin;
                    contentStream.beginText();
                    contentStream.newLineAtOffset(startX, startY);
                }

                contentStream.showText(line);
                contentStream.newLineAtOffset(0, -leading);
                yPosition -= leading;
            }

            contentStream.endText();
            contentStream.close();
            reader.close();
            
            // Save the PDF document
            document.save("C:\\Users\\sanus\\Downloads\\50MB-TXT-FILE.pdf");
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading from local file";
        }
		return "Data read from local file";
	}

}
