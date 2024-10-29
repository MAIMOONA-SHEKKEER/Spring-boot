package com.example.registration_api.controller;

import com.example.registration_api.entity.PropertyManager;
import com.example.registration_api.service.PropertyManagerService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.List;

@Controller
public class PM_PdfController {

    @Autowired
    private PropertyManagerService propertyManagerService;

    @GetMapping("/property-managers")
    public String viewPropertyManagers(Model model) {
        List<PropertyManager> managers = propertyManagerService.getAllPropertyManagers();
        System.out.println("Managers: " + managers); 
        model.addAttribute("managers", managers);
        return "property-managers"; 
    }

    @GetMapping("/api/property-managers/download")
    public void downloadPropertyManagers(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=property_managers.pdf");

        createPdf(response);
    }

    @GetMapping("/api/property-managers/view")
    public void viewPropertyManagersDocument(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=property_managers.pdf");

        createPdf(response);
    }

    private void createPdf(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Paragraph headline = new Paragraph("Property Managers List", boldFont);
        headline.setAlignment(Element.ALIGN_CENTER);
        document.add(headline);

        document.add(new Paragraph(" ")); 

        PdfPTable table = new PdfPTable(4);
        table.addCell("ID");
        table.addCell("Name");
        table.addCell("Phone");
        table.addCell("Email");


        List<PropertyManager> managers = propertyManagerService.getAllPropertyManagers();
        for (PropertyManager manager : managers) {
            table.addCell(String.valueOf(manager.getId()));
            table.addCell(manager.getName());
            table.addCell(manager.getPhone());
            table.addCell(manager.getEmail());
        }

        document.add(table);
        document.close();
    }
}
