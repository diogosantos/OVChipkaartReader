package parser;

import model.Report;
import model.ReportEntry;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.File;
import java.io.IOException;

/**
 * Created by diogo.santos on 11/06/14.
 */
public class ReportParser {

	public Report getReport(File file) throws IOException {
		LineIterator lineIterator = FileUtils.lineIterator(file);

		Report report = new Report();
		skipHeaders(lineIterator);

		ReportEntryParser parser = new ReportEntryParser();
		while(lineIterator.hasNext()) {
			String reportLine = lineIterator.nextLine();
			ReportEntry entry = parser.getReportEntry(reportLine);
			report.add(entry);
		}
		return report;
	}

	private void skipHeaders(LineIterator lineIterator) {
		lineIterator.next(); // skip the first line;
	}

}
