package br.com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;

/* Classe criada para uma expressao regular verificar]
 * se o uri está passando um id, exempplo: www.projeto.com.br/diretorio/12, on o numero 12 é o id da consulta.
 */

public class RegexUtil {
	private static final Pattern regexAll = Pattern.compile("/diretorio");
	private static final Pattern regexById = Pattern
			.compile("/diretorio/([0-9]*)");

	// Verificar se a URL é no paddrão "/diretorio/id"
	public static Long matchId(String requestUri) throws ServletException {
		// Verifica o ID
		Matcher matcher = regexById.matcher(requestUri);
		if (matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			if (s != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}

	// Verificar se a URL é no paddrão "/diretorio/id"
	public boolean matchAll(String requestUri) throws ServletException {
		Matcher matcher = regexAll.matcher(requestUri);
		if (matcher.find()) {
			return true;
		}
		return false;
	}
}
