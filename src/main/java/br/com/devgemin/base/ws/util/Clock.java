package br.com.devgemin.base.ws.util;

import java.util.Date;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class Clock {

	public Date today() {
		return LocalDateTime.fromDateFields(new Date()).toDate();
	}
}