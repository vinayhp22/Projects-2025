package com.skyllx.expense.controller;

import com.skyllx.expense.service.ReportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/download/csv")
    @ResponseBody
    public void downloadCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=expenses.csv");
        response.getOutputStream().write(reportService.generateCSVReport());
        response.getOutputStream().flush();
    }

    @GetMapping("/download/pdf")
    @ResponseBody
    public void downloadPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=expenses.pdf");
        response.getOutputStream().write(reportService.generatePDFReport());
        response.getOutputStream().flush();
    }
}
