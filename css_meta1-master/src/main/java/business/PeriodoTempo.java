package business;

import java.time.LocalDate;

public class PeriodoTempo {

	private LocalDate dateIni;
	private LocalDate dateEnd;
	
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
