package com.example.registration_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.example.registration_api.entity.Customer;
import com.example.registration_api.service.CustomerService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@RestController
public class PdfController {

    @Autowired
    private CustomerService customerService;

    // Endpoint to display the download page (view)
    @GetMapping("/customers")
    public String showCustomersPage(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers); // Add data to the model
        return "customers"; // Returns the Thymeleaf template (customers.html)
    }

    // Endpoint to generate and download the PDF
    @GetMapping("/api/customers/download")
    public void downloadCustomers(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=customers.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        // Add title and table with customer data
        document.add(new Paragraph("Customer List"));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4); // Table with 4 columns
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Phone");
        table.addCell("Email");

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
