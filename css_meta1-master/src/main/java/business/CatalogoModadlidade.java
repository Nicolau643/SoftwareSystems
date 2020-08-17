package business;

import java.util.ArrayList;
import java.util.List;

public class CatalogoModadlidade {

	private List<Modalidade> data;
	
	public CatalogoModadlidade() {
		this.data = new ArrayList<>();
		data.add(new Modalidade("tenis", 30, 3));
	}
	/*
	public boolean exists(String modalidade) {
		return data.contains(modalidade);
	}*/

	public Modalidade getByName(String modalidade) {
		for (Modalidade m : data) {
			if (m.getName().equals(modalidade)) {
				return m;
			}
		}
		
		return null;
	}

	public String[] getAllNames() {
		ArrayList<String> res = new ArrayList<>();
		data.forEach(e -> {
			res.add(e.getName());
		});
		
		return (String [])res.toArray();
	}

}
