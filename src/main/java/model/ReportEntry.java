package model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by diogo.santos on 11/06/14.
 */
public class ReportEntry {

	private Date checkInDate;
	private Date checkOutDate;
	private BigDecimal amount;

	public ReportEntry(Date checkInDate, Date checkOutDate, BigDecimal amount) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.amount = amount;
	}

	public Date getDate() {
		if(hasCheckInAndCheckout()) {
			return checkInDate;
		} else {
			return checkOutDate;
		}
	}

	private boolean hasCheckInAndCheckout() {
		return checkInDate != null && checkOutDate != null;
	}

	public Date getCheckInDate() {
		return checkInDate;
	}

	public Date getCheckOutDate() {
		return checkOutDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public boolean isBusinessDay() {
		int dayOfWeek = getDateAsCalendar().get(Calendar.DAY_OF_WEEK);
		return dayOfWeek >= Calendar.MONDAY && dayOfWeek <= Calendar.THURSDAY;
	}

	public boolean isBusinessTime() {
		int hour = getDateAsCalendar().get(Calendar.HOUR_OF_DAY);
		return hour >= 7 && hour <= 19;
	}

	private Calendar getDateAsCalendar() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDate());
		return cal;
	}
}
