package br.com.devgemin.base.ws.log;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.util.I18N;

@Component
public class LogTranslator {

	private static final List<LogRequestMap> MAP = new ArrayList<>();

	static {
		//MAP.add(new LogRequestMap("POST", "/extract/transactionsExtractImport", "transactions.extract.import"));
	}

	private I18N i18n;

	@Autowired
	public LogTranslator(I18N i18n) {
		this.i18n = i18n;
	}

	public String translate(String method, String context, String path) {
		for (LogRequestMap map : MAP)
			if (map.equals(method, context, path))
				return i18n.getLog(map.getMessage());

		for (LogRequestMap map : MAP)
			if (map.matches(method, context, path))
				return i18n.getLog(map.getMessage());

		return method + " " + path;
	}
}