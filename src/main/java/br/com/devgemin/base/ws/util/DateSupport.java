package br.com.devgemin.base.ws.util;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.Seconds;
import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.exception.baseexception.internalservererror.GlobalDateParseExeption;


@Component
public class DateSupport {

	public SimpleDateFormat getFormat() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		format.setLenient(false);
		return format;
	}

	public Date toDate(String date) {
		if (!isBlank(date) && !date.matches("\\d{4}-\\d{2}-\\d{2}"))
			throw new GlobalDateParseExeption();

		return toDate(date, "yyyy-MM-dd");
	}

	public Date toDateTime(String date) {
		if (!isBlank(date) && !date.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}:\\d{3}"))
			throw new GlobalDateParseExeption();

		return toDate(date, "yyyy-MM-dd HH:mm:ss:SSS");
	}

	public String toStringLog(Date date) {
		return toString(date, "yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	}

	public String toStringDate(Date date) {
		return toString(date, "yyyy-MM-dd");
	}

	public String toStringDateTime(Date date) {
		return toString(date, "yyyy-MM-dd HH:mm:ss");
	}

	public String toStringOutputFile(Date date) {
		return toString(date, "yyyyMMddHHmmss");
	}

	public String toStringPtBr(Date date) {
		return toString(date, "dd/MM/yyyy");
	}

	public String toStringPtBr(String date) {
		return toStringPtBr(toDate(date));
	}

	public boolean isDateValid(final String date) {
		try {
			if (isBlank(date))
				return true;

			if (!date.matches("\\d{4}-\\d{2}-\\d{2}"))
				return false;

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);
			format.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public boolean isBefore(Date date, Date verified) {
		if (date == null || verified == null)
			return false;

		LocalDate localDate = LocalDate.fromDateFields(date);
		LocalDate localDateVerified = LocalDate.fromDateFields(verified);
		return localDateVerified.isBefore(localDate);
	}

	public boolean isAfter(Date date, Date verified) {
		if (date == null || verified == null)
			return false;

		LocalDate localDate = LocalDate.fromDateFields(date);
		LocalDate localDateVerified = LocalDate.fromDateFields(verified);
		return localDateVerified.isAfter(localDate);
	}

	public Date minusDays(Date date, int days) {
		return LocalDate.fromDateFields(date).minusDays(days).toDate();
	}

	public Date plusDays(Date date, int days) {
		return LocalDate.fromDateFields(date).plusDays(days).toDate();
	}

	public int getRangeOfDays(Date start, Date end) {
		LocalDate startLocalDate = LocalDate.fromDateFields(start);
		LocalDate endLocalDate = LocalDate.fromDateFields(end);
		if (startLocalDate.isAfter(endLocalDate))
			return 0;
		return Days.daysBetween(startLocalDate, endLocalDate).getDays();
	}

	public int getRangeOfSeconds(Date start, Date end) {
		LocalDateTime startLocalDate = LocalDateTime.fromDateFields(start);
		LocalDateTime endLocalDate = LocalDateTime.fromDateFields(end);
		if (startLocalDate.isAfter(endLocalDate))
			return 0;
		return Seconds.secondsBetween(startLocalDate, endLocalDate).getSeconds();
	}

	private Date toDate(String date, String pattern) {
		if (isBlank(date))
			return null;

		try {
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			format.setLenient(false);
			return format.parse(date);
		} catch (Exception e) {
			throw new GlobalDateParseExeption(e);
		}
	}

	private String toString(Date date, String pattern) {
		if (date == null)
			return null;

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		format.setLenient(false);
		return format.format(date);
	}
}