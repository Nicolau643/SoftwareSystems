package business;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embeddable;

@Embeddable
public class PeriodoTempo {
	
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dateIni;
	@Convert(converter = LocalDateAttributeConverter.class)
	private LocalDate dateEnd;
	
	public PeriodoTempo() {}
	
	public PeriodoTempo(LocalDate dateIni, LocalDate dateEnd) {
		super();
		this.dateIni = dateIni;
		this.dateEnd = dateEnd;
	}

	public LocalDate getDateIni() {
		return dateIni;
	}

	public LocalDate getDateEnd() {
		return dateEnd;
	}
	
	
	
}
