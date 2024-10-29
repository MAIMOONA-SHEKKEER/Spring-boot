package com.example.registration_api.controller;

import com.example.registration_api.entity.Query;
import com.example.registration_api.service.QueryService;
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

@Controller
public class QueryPdfController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/queries")
    public String showQueriesPage(Model model) {
        List<Query> queries = queryService.getAllQueries();
        model.addAttribute("queries", queries); 
        return "queries"; 
    }

    @GetMapping("/api/queries/download")
    public void downloadQueries(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=queries.pdf");

        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        document.add(new Paragraph(" "));
        Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);

        Paragraph headline = new Paragraph("Query List", boldFont);
        headline.setAlignment(Element.ALIGN_CENTER);
        document.add(headline);

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(4);
        table.addCell("ID");
        table.addCell("Owner Name");
        table.addCell("Contact Number");
        table.addCell("Queries");
        table.addCell("Status");

   
        List<Query> queries = queryService.getAllQueries();
        for (Query query : queries) {
            table.addCell(String.valueOf(query.getId()));
            table.addCell(query.getOwnerName());
            table.addCell(query.getContactNumber());
            table.addCell(String.join(", ", query.getQueries())); 
            table.addCell(query.getStatus());
        }

        document.add(table);
        document.close();
    }
}
