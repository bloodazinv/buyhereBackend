package com.jane.buyherebackend.exportcsv;


import com.jane.buyherebackend.util.AbstractExporter;
import com.jane.buyherecommon.entity.Customer;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class CustomerCsvExporter extends AbstractExporter {

public void export(List<Customer> listCustomer, HttpServletResponse response) throws IOException {
		
		super.setResponseHeader(response, "text/csv", ".csv", "customers_");
		
		Writer writer = new OutputStreamWriter(response.getOutputStream(), "utf-8");
		writer.write('\uFEFF');
		
		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, 
				CsvPreference.STANDARD_PREFERENCE);

		String[] csvHeader = {"Customer ID", "First Name", "Last Name", "E-mail",  "City", "State", "Country" , "Enabled"};
		String[] fieldMapping = {"id", "firstName", "lastName", "email",  "city", "state" , "country" , "enabled"};

		csvWriter.writeHeader(csvHeader);

		for (Customer customer : listCustomer) {
			csvWriter.write(customer, fieldMapping);
		}

		csvWriter.close();
	}
}
