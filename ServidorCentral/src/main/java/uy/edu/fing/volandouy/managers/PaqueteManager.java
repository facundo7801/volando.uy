package uy.edu.fing.volandouy.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import uy.edu.fing.volandouy.modelo.Paquete;
import uy.edu.fing.volandouy.util.SearchUtil;

public class PaqueteManager {
	private static PaqueteManager instance;
	private List<Paquete> paquetes;

	private PaqueteManager() {
		paquetes = new ArrayList<>();
	}

	private Paquete buscarPorCondicion(Predicate<Paquete> condicion) {
		for (Paquete paquete : paquetes) {
			if (condicion.test(paquete)) {
				return paquete;
			}
		}
		return null;
	}

	public static synchronized PaqueteManager getInstance() {
		if (instance == null) {
			instance = new PaqueteManager();
		}
		return instance;
	}

	public void addPaquete(Paquete paquete) {
		paquetes.add(paquete);
	}

	public List<Paquete> getPaquetes() {
		return paquetes;
	}

	public Paquete findPaqueteByName(String nombre) {
		return buscarPorCondicion(paquete -> paquete.getNombre().equals(nombre));
	}

	public Paquete buscarPaquetePorNombre(String nombre) {
		return SearchUtil.buscarPorCondicion(paquetes, paquete -> paquete.getNombre().equals(nombre));
	}

}
