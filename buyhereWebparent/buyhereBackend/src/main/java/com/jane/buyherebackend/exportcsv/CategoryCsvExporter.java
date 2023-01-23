/**
 * FileName: CategoryCsvExporter
 * Author: jane
 * Date: 2023/1/10 17:13
 * Description:
 * Version:
 */

package com.jane.buyherebackend.exportcsv;

import com.jane.buyherebackend.util.AbstractExporter;
import com.jane.buyherecommon.entity.Category;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class CategoryCsvExporter extends AbstractExporter {
    public void export(List<Category> listCategories, HttpServletResponse response)
            throws IOException {
        super.setResponseHeader(response, "text/csv", ".csv", "categories_");

        Writer writer = new OutputStreamWriter(response.getOutputStream(), "utf-8");
        writer.write('\uFEFF');

        ICsvBeanWriter csvWriter = new CsvBeanWriter(writer,
                CsvPreference.STANDARD_PREFERENCE);

        String[] csvHeader = {"Category ID", "Category Name"};
        String[] fieldMapping = {"id", "name"};

        csvWriter.writeHeader(csvHeader);

        for (Category category : listCategories) {
            category.setName(category.getName().replace("--", "  "));
            csvWriter.write(category, fieldMapping);
        }

        csvWriter.close();
    }
}
