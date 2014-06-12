package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by diogo.santos on 11/06/14.
 */
public class Report {

	List<ReportEntry> entries = new ArrayList<>();

	public void add(ReportEntry entry) {
		entries.add(entry);
	}

	public List<ReportEntry> entries() {
		return Collections.unmodifiableList(entries);
	}

	public void sortEntries() {
		Collections.sort(entries, new Comparator<ReportEntry>() {
			@Override
			public int compare(ReportEntry o1, ReportEntry o2) {
				return o1.getDate().compareTo(o2.getDate());
			}
		});
	}

	public List<ReportEntry> filteredEntries(ReportEntryFilter filter) {
		return filter.getFilteredEntries(entries);
	}
}
