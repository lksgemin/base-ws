package br.com.devgemin.base.ws.log;

import static org.apache.commons.lang3.StringUtils.trimToEmpty;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.devgemin.base.ws.exception.HandlerException;
import br.com.devgemin.base.ws.util.Clock;
import br.com.devgemin.base.ws.util.DateSupport;


@Component
public class LoggerSupport {

	private Logger consoleLogger;

	private Logger serviceLogger;

	private DateSupport dateSupport;

	private Clock clock;

	private LogTranslator logTranslator;

	@Autowired
	public LoggerSupport(Logger consoleLogger, Logger serviceLogger, DateSupport dateSupport, Clock clock, LogTranslator logTranslator) {
		this.consoleLogger = consoleLogger;
		this.serviceLogger = serviceLogger;
		this.dateSupport = dateSupport;
		this.clock = clock;
		this.logTranslator = logTranslator;
	}

	public void logSuccess(HttpServletRequest request) {
		log(request, "Sucesso");
	}

	public void logError(HttpServletRequest request) {
		log(request, "Erro");
	}

	public void logError(String message, Exception e) {
		consoleLogger.error(message, e);
	}

	public void logError(String message, HandlerException e) {
		consoleLogger.info(message, e);
	}

	public void logInfo(String log) {
		consoleLogger.info(log);
	}

	private void log(HttpServletRequest request, String situacao) {
		String correlationid = getCorrelationid(request);
		String addressip = getAddressip(request);
		String systemname = getSystemname(request);
		String dtHr = getDataHora();
		String consumerid = getConsumerid(request);
		String usuarioLogado = getUsuarioLogado(request);
		String funcionalidade = getFuncionalidade(request);

		log(correlationid, addressip, systemname, dtHr, consumerid, usuarioLogado, funcionalidade, situacao, "");
	}

	private void log(String id, String addressip, String system, String dtHr, String idUser, String usuarioLogado, String funcionalidade, String situacao, String cartao) {
		serviceLogger.info(id + ";" + addressip + ";" + system + ";" + dtHr + ";" + idUser + ";" + usuarioLogado + ";" + funcionalidade + ";" + situacao + ";" + cartao);
	}

	private String getCorrelationid(HttpServletRequest request) {
		return trimToEmpty(request.getHeader("correlationid"));
	}

	private String getSystemname(HttpServletRequest request) {
		return trimToEmpty(request.getHeader("systemname"));
	}

	private String getAddressip(HttpServletRequest request) {
		return trimToEmpty(request.getHeader("addressip"));
	}

	private String getDataHora() {
		return dateSupport.toStringLog(clock.today());
	}

	private String getConsumerid(HttpServletRequest request) {
		return trimToEmpty(request.getHeader("consumerid"));
	}

	private String getUsuarioLogado(HttpServletRequest request) {
		return trimToEmpty(request.getHeader("usuarioLogado"));
	}

	private String getFuncionalidade(HttpServletRequest request) {
		String method = request.getMethod();
		String context = request.getContextPath();
		String path = request.getRequestURI();
		return logTranslator.translate(method, context, path);
	}
}