package parser;

import model.ReportEntry;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import static java.lang.Integer.parseInt;

/**
 * Created by diogo.santos on 11/06/14.
 */
public class ReportEntryParser {

	public ReportEntry getReportEntry(String reportLine) {
		String[] values = sanitize(reportLine).split(";");
		Date checkInDate = parseDateWithTime(values[0], values[1]);
		Date checkOutDate = parseDateWithTime(values[0], values[3]);
		BigDecimal amount = parseCurrency(values[5]);
		return new ReportEntry(checkInDate, checkOutDate, amount);
	}

	private String sanitize(String reportLine) {
		return reportLine.replaceAll("\"", "");
	}

	private BigDecimal parseCurrency(String currencyString) {
		return new BigDecimal(currencyString.replaceAll(",", "."));
	}

	private Date parseDateWithTime(String date, String time) {
		if(StringUtils.isEmpty(date) || StringUtils.isEmpty(time)) {
			return null;
		}
		return doParseDateWithTime(date, time);
	}

	private Date doParseDateWithTime(String date, String time) {
		Calendar calendar = Calendar.getInstance();
		String[] dateSplit = date.split("-");
		calendar.set(parseInt(dateSplit[2]), parseInt(dateSplit[1]) -1, parseInt(dateSplit[0]));
		String[] timeSplit = time.split(":");
		calendar.set(Calendar.HOUR_OF_DAY, parseInt(timeSplit[0]));
		calendar.set(Calendar.MINUTE, parseInt(timeSplit[1]));
		return calendar.getTime();
	}

}
