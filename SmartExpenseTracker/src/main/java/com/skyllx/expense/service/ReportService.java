package com.skyllx.expense.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.opencsv.CSVWriter;
import com.skyllx.expense.dao.ExpenseDao;
import com.skyllx.expense.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ExpenseDao expenseDao;

    // Generate CSV Report
    public byte[] generateCSVReport() throws IOException {
        List<Expense> expenses = expenseDao.getAllExpenses();

        try (StringWriter stringWriter = new StringWriter();
             CSVWriter csvWriter = new CSVWriter(stringWriter)) {

            // Write Header
            String[] header = {"Category", "Amount", "Date"};
            csvWriter.writeNext(header);

            // Write Data
            for (Expense expense : expenses) {
                String categoryName = expense.getCategory() != null ? expense.getCategory().getName() : "N/A";
                String date = expense.getDate() != null ? expense.getDate().toString() : "N/A";
                csvWriter.writeNext(new String[]{categoryName, String.valueOf(expense.getAmount()), date});
            }

            return stringWriter.toString().getBytes();
        }
    }

    // Generate PDF Report
    public byte[] generatePDFReport() throws IOException {
        List<Expense> expenses = expenseDao.getAllExpenses();

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(out);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new Paragraph("Expense Report").setBold().setFontSize(16));

            Table table = new Table(3);
            table.addCell("Category");
            table.addCell("Amount");
            table.addCell("Date");

            for (Expense expense : expenses) {
                String categoryName = expense.getCategory() != null ? expense.getCategory().getName() : "N/A";
                String date = expense.getDate() != null ? expense.getDate().toString() : "N/A";
                table.addCell(categoryName);
                table.addCell(String.valueOf(expense.getAmount()));
                table.addCell(date);
            }

            document.add(table);
            return out.toByteArray();
        }
    }
}