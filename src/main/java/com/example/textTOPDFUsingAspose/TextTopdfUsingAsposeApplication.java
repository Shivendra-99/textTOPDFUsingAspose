package com.example.textTOPDFUsingAspose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspose.words.Document;

@SpringBootApplication
@RestController
public class TextTopdfUsingAsposeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TextTopdfUsingAsposeApplication.class, args);
	}

	@PostMapping("/readDataFromLocal")
	public String getReadDataFroLocal() {
		String pdfPath = "C:\\Users\\sanus\\Downloads\\50MB-TXT-FILE.txt";
		// String pdfPath = "output.pdf";
		// Document document = new Document();
		try {
			Document doc = new Document(pdfPath);
			doc.save("C:\\Users\\sanus\\Downloads\\50MB-TXT-FILE.pdf");
		} catch (Exception e) {
			System.out.println("Error in reading the file: " + e.getMessage());
		}
		return "File uploaded successfully";
	}

}
