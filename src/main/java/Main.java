import model.Report;
import parser.ReportParser;
import model.ReportEntryFilter;
import view.ReportPrinter;

import java.io.File;
import java.io.IOException;

/**
 * Created by diogo.santos on 11/06/14.
 */
public class Main {

	public static void main(String[] args) throws IOException {
		//TODO: get it as the first argument.
		File file = getReportFile();
		Report report = parseReport(file);

		//TODO: get it as the "-b" or "--business-mode" argument.
		ReportEntryFilter filter = new ReportEntryFilter();
		filter.setBusinessMode();

		//TODO: get an argument to print it out in other ways. Like, PDF, CSV, etc.
		ReportPrinter out = new ReportPrinter();
		out.applyFilter(filter);
		out.print(report);
	}

	private static Report parseReport(File file) throws IOException {
		ReportParser parser = new ReportParser();
		return parser.getReport(file);
	}

	private static File getReportFile() {
		String reportLocation = "/Users/diogo.santos/Desktop/transacties_-1963293149.csv";
		return new File(reportLocation);
	}

}
