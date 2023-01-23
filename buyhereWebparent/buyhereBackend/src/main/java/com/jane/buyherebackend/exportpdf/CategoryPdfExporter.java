/**
 * FileName: CategoryPdfExporter
 * Author: jane
 * Date: 2023/1/10 17:14
 * Description:
 * Version:
 */

package com.jane.buyherebackend.exportpdf;

import com.jane.buyherebackend.util.AbstractExporter;
import com.jane.buyherecommon.entity.Category;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class CategoryPdfExporter extends AbstractExporter {

    public void export(List<Category> listCategories, HttpServletResponse response) throws IOException {

        super.setResponseHeader(response, "application/pdf", ".pdf", "categories_");

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph paragraph = new Paragraph("List of Category", font);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(paragraph);

        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setSpacingBefore(10);
        table.setWidths(new float[] {1.2f, 3.5f});

        writeTableHeader(table);
        writeTableData(table, listCategories);

        document.add(table);

        document.close();
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Category ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Category Name", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table, List<Category> listCategories) {
        for (Category category : listCategories) {
            table.addCell(String.valueOf(category.getId()));
            table.addCell(category.getName());
        }
    }
}
