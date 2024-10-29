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
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller 
public class PdfController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public String showCustomersPage(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers); 
        return "customers"; 
    }

    @GetMapping("/api/customers/download")
    public void downloadCustomers(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=customers.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        document.add(new Paragraph(" "));
  
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);


        Paragraph headline = new Paragraph("Customer List", boldFont);
        headline.setAlignment(Element.ALIGN_CENTER);
        document.add(headline);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        PdfPTable table = new PdfPTable(4);
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

    @GetMapping("/customers/{id}")
    public String showCustomerDetailPage(@PathVariable Long id, Model model) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            model.addAttribute("customer", customer.get()); 
            return "customer_detail"; 
        } else {
            model.addAttribute("error", "Customer not found");
            return "error";
        }
    }

    @GetMapping("/api/customers/{id}/download")
    public void downloadCustomerById(@PathVariable Long id, HttpServletResponse response)
            throws DocumentException, IOException {
        Optional<Customer> customer = customerService.getCustomerById(id);

        if (customer.isPresent()) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=customer_" + id + ".pdf");

            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);

            Paragraph headline = new Paragraph("Customer Details", boldFont);
            headline.setAlignment(Element.ALIGN_CENTER);
            document.add(headline);

            document.add(new Paragraph(" "));

            Customer c = customer.get();
            document.add(new Paragraph("Customer ID: " + c.getId()));
            document.add(new Paragraph("Name: " + c.getName()));
            document.add(new Paragraph("Phone: " + c.getPhone()));
            document.add(new Paragraph("Email: " + c.getEmail()));

            document.close();
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer not found");
        }
    }
}
