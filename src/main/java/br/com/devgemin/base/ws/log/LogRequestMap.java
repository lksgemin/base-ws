package br.com.devgemin.base.ws.log;

public class LogRequestMap {

	private String method;
	private String path;
	private String message;

	public LogRequestMap(String method, String path, String message) {
		this.method = method;
		this.path = path;
		this.message = message;
	}

	public boolean equals(String method, String context, String path) {
		String url = this.method + context + this.path;
		String keyMap = url;
		String key = method + path;
		return equals(key, keyMap);
	}

	public boolean matches(String method, String context, String path) {
		String url = this.method + context + this.path;
		String keyMap = url;
		String key = method + path;
		return key.matches(keyMap);
	}

	public String getMessage() {
		return message;
	}

	private boolean equals(String key, String keyMap) {
		return key.replaceAll("/$", "").equals(keyMap.replaceAll("/$", ""));
	}
}