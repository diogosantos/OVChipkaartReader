package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by diogo.santos on 11/06/14.
 */
public class ReportEntryFilter {

	public static final int MAX_ENTRIES_PER_DAY = 2;

	/**
	 * Business mode displays only entries from business days, within business hours. Only two entries per day.
	 */
	private boolean isBusinessMode;

	public void setBusinessMode() {
		isBusinessMode = true;
	}

	public List<ReportEntry> getFilteredEntries(List<ReportEntry> entries) {
		if(isBusinessMode) {
			return getBusinessModeEntries(entries);
		}
		return entries;
	}

	private List<ReportEntry> getBusinessModeEntries(List<ReportEntry> entries) {
		List<ReportEntry> filteredEntries = new ArrayList<>();

		int entriesPerDay = 0;
		Date previousEntryDate = null;

		for(int i=0; i < entries.size(); i++) {
			ReportEntry entry = entries.get(i);

			if(matchBusinessMode(entry)) {

				if(previousEntryDate == null) {
					previousEntryDate = entry.getDate();
				}

				if(isSameDay(previousEntryDate, entry.getDate())) {
					entriesPerDay++;
				} else {
					entriesPerDay = 1;
				}

				if(entriesPerDay <= MAX_ENTRIES_PER_DAY) {
					filteredEntries.add(entry);
				}

				previousEntryDate = entry.getDate();
			}

		}

		return filteredEntries;
	}

	private boolean isSameDay(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);

		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);

		return c1.get(Calendar.DATE) == c2.get(Calendar.DATE) &&
				c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) &&
				c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR);
	}

	private boolean matchBusinessMode(ReportEntry entry) {
		return isBusinessMode && entry.isBusinessTime() && entry.isBusinessDay();
	}
}
