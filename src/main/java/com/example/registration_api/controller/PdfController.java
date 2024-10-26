package com.example.registration_api.controller;

import com.example.registration_api.entity.Customer;
import com.example.registration_api.service.CustomerService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller // Use @Controller instead of @RestController for HTML template support
public class PdfController {

    @Autowired
    private CustomerService customerService;

    // Endpoint to display the customers view (Thymeleaf template)
    @GetMapping("/customers")
    public String showCustomersPage(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers); // Add data to the model
        return "customers"; // Render the Thymeleaf template "customers.html"
    }

    // Endpoint to generate and download the PDF
    @GetMapping("/api/customers/download")
    public void downloadCustomers(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=customers.pdf");

        // Create a new PDF document
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        // Add space
        document.add(new Paragraph(" "));
        // Create a bold font for the headline
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);

        // Create the headline paragraph with bold font and center alignment
        Paragraph headline = new Paragraph("Customer List", boldFont);
        headline.setAlignment(Element.ALIGN_CENTER); // Align to center
        document.add(headline);

        // Add more space
        document.add(new Paragraph(" "));
        // Add more space
        document.add(new Paragraph(" "));
        // Create a table with 4 columns
        PdfPTable table = new PdfPTable(4);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Phone");
        table.addCell("Email");

        // Populate the table with customer data
        List<Customer> customers = customerService.getAllCustomers();
        for (Customer customer : customers) {
            table.addCell(String.valueOf(customer.getId()));
            table.addCell(customer.getName());
            table.addCell(customer.getPhone());
            table.addCell(customer.getEmail());
        }

        document.add(table);
        document.close();
    }
}
