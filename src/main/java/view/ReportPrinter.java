package view;

import model.Report;
import model.ReportEntry;
import model.ReportEntryFilter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by diogo.santos on 11/06/14.
 */
public class ReportPrinter {

	private SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
	private SimpleDateFormat formatDate = new SimpleDateFormat("E dd/M/yyyy");

	private ReportEntryFilter filter;

	private BigDecimal total = BigDecimal.ZERO;

	public void applyFilter(ReportEntryFilter filter) {
		this.filter = filter;
	}

	public void print(Report report) {
		printHeader();
		printLines(report);
		printFooter();
	}

	private void printHeader() {
		print("#    | date          |  time   | amount");
		print("-----------------------------------------");
	}

	private void print(String output) {
		System.out.println(output);
	}

	private void printLines(Report report) {
		report.sortEntries();
		List<ReportEntry> entries = getEntries(report);
		for (int i = 0; i < entries.size(); i++) {
			ReportEntry reportEntry = entries.get(i);
			total = total.add(reportEntry.getAmount());
			print(String.format("%02d:  | %s ", i+1, getReportEntryToPrint(reportEntry)));
		}
	}

	private List<ReportEntry> getEntries(Report report) {
		if(hasFilter()) {
			return report.filteredEntries(filter);
		}
		return report.entries();
	}

	private boolean hasFilter() {
		return filter != null;
	}

	private String getReportEntryToPrint(ReportEntry reportEntry) {
		return String.format("%s | %s | %s", formatDate(reportEntry.getDate()), formatTime(reportEntry.getCheckOutDate()), formatCurrency(reportEntry.getAmount()));
	}

	private String formatDate(Date date) {
		if(date != null) {
			return formatDate.format(date);
		}
		return "   ";
	}

	private String formatTime(Date dateTime) {
		if(dateTime != null) {
			return String.format(" %s ", formatTime.format(dateTime));
		}
		return "        ";
	}

	private String formatCurrency(BigDecimal amount) {
		return amount.toString();
	}

	private void printFooter() {
		print("-----------------------------------------");
		print(String.format("                               | %s ", formatCurrency(total)));
	}
}
