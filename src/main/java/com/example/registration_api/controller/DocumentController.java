package com.example.registration_api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import jakarta.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
public class DocumentController {

    private final TemplateEngine templateEngine;

    // Inject TemplateEngine via constructor
    public DocumentController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    // Endpoint for rendering the HTML document
    @GetMapping("/document")
    public String generateDocument(@RequestParam(required = false, defaultValue = "Guest") String userName, Model model) {
        // Sample Data
        List<Map<String, Object>> items = List.of(
            Map.of("name", "Item 1", "description", "Description 1", "quantity", 2, "price", 20.00),
            Map.of("name", "Item 2", "description", "Description 2", "quantity", 5, "price", 50.00)
        );

        // Add data to the model
        model.addAttribute("userName", userName);
        model.addAttribute("date", LocalDate.now());
        model.addAttribute("items", items);
        
        // Return the Thymeleaf template name (document.html)
        return "document"; // Ensure you have a corresponding document.html in resources/templates
    }

    // Endpoint for generating the PDF document
    @GetMapping("/document/pdf")
    public void generatePdf(@RequestParam(required = false, defaultValue = "Guest") String userName, HttpServletResponse response) 
            throws IOException, DocumentException {

        // Prepare data for the template
        List<Map<String, Object>> items = List.of(
            Map.of("name", "Item 1", "description", "Description 1", "quantity", 2, "price", 20.00),
            Map.of("name", "Item 2", "description", "Description 2", "quantity", 5, "price", 50.00)
        );

        // Generate HTML content from the Thymeleaf template
        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("date", LocalDate.now().toString());
        context.setVariable("items", items);

        String htmlContent = templateEngine.process("document", context);

        // Set up Flying Saucer renderer for PDF generation
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();

        // Set response headers for PDF download
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=document.pdf");

        // Write the generated PDF to the response output stream
        try (OutputStream out = response.getOutputStream()) {
            renderer.createPDF(out);
        }
    }
}
