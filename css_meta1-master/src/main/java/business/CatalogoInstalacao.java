package business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CatalogoInstalacao {
	
	private List<Instalacao> data;

	public CatalogoInstalacao() {
		this.data = new ArrayList<>();
		List<Modalidade> m = new ArrayList<>();
		m.add(new Modalidade("tenis", 30, 3));
		data.add(new Instalacao("campo", 34,m));
	}

	public String[] getAllNames() {
		ArrayList<String> res = new ArrayList<>();
		data.forEach(e -> res.add(e.getNome()));
		return (String[])res.toArray();
	}

	public boolean existByName(String inst) {
		return data.stream().filter(e->e.getNome().equals(inst)).count() != 0;
	}

	public Instalacao getByName(String inst) {
		for (Instalacao i : data) {
			if (i.getNome().equals(i.getNome())) {
				return i;
			}
		}
		return null;
	}

	public List<Sessao> getSessoesModOneDay(Modalidade mod) {
		List<Sessao> res = new ArrayList<>();
		
		data.forEach(i -> {
			if (i.getModalidades().contains(mod)) {
				i.getSessoes().forEach(s -> {
					LocalDateTime aux = LocalDateTime.of(s.getDate(), s.getTimeInit());
					if (aux.isAfter(LocalDateTime.now()) && aux.isBefore(LocalDateTime.now().plusHours(24))) {
						res.add(s);
					}
				});
			}
			
		});
		return res;
	}

	public List<Sessao> getSessoesMod(Modalidade mod) {
		List<Sessao> res = new ArrayList<>();
		
		data.forEach(i -> {
			if (i.getModalidades().contains(mod)) {
				i.getSessoes().forEach(s -> {
					res.add(s);
				});
			}
			
		});
		return res;
		
	}

}
