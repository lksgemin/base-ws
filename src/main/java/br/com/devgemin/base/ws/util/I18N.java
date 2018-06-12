package br.com.devgemin.base.ws.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.message.MessageKey;

@Component
public class I18N {

	public String get(MessageKey key, String... param) {
		ResourceBundle resource = ResourceBundle.getBundle("ValidationMessages", getLocale());
		return MessageFormat.format(resource.getString(key.toString()), (Object[]) param);
	}

	public String getLog(String key) {
		ResourceBundle resource = ResourceBundle.getBundle("Log", getLocale());
		return MessageFormat.format(resource.getString(key), (Object[]) null);
	}

	private Locale getLocale() {
		return LocaleContextHolder.getLocale();
	}
}