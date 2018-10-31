package br.com.base.common;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class TimeProvider implements Serializable {

	private static final long serialVersionUID = -3301695478208950415L;

	public LocalDateTime getDataHoraAtual() {
		return LocalDateTime.now();
	}

	public LocalDate getDataAtual() {
		return LocalDate.now();
	}

	public Date toDate(LocalDateTime localDate) {
		Date out = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}

	public LocalDateTime toLocalDateTime(Date d) {
		return d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
}
