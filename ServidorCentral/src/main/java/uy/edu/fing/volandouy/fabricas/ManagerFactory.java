package uy.edu.fing.volandouy.fabricas;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.controllers.PaqueteController;
import uy.edu.fing.volandouy.controllers.UsuarioController;
import uy.edu.fing.volandouy.controllers.VueloController;
import uy.edu.fing.volandouy.managers.PaqueteManager;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.managers.VueloManager;

public class ManagerFactory implements IManagerFactory {

	private static ManagerFactory instance;
	private static VueloManager vueloManagerInstance;
	private static UserManager userManagerInstance;
	private static PaqueteManager paqueteManagerInstance;

	private ManagerFactory() {
		this.getVueloManager();
	}

	public static ManagerFactory getInstance() {
		if (instance == null) {
			synchronized (ManagerFactory.class) {
				if (instance == null) {
					instance = new ManagerFactory();
				}
			}
		}
		return instance;
	}

	public VueloManager getVueloManager() {
		if (vueloManagerInstance == null) {
			synchronized (VueloManager.class) {
				if (vueloManagerInstance == null) {
					vueloManagerInstance = VueloManager.getInstance();
				}
			}
		}
		return vueloManagerInstance;
	}

	@Override
	public PaqueteManager getPaqueteManager() {
		if (paqueteManagerInstance == null) {
			synchronized (PaqueteManager.class) {
				if (paqueteManagerInstance == null) {
					paqueteManagerInstance = PaqueteManager.getInstance();
				}
			}
		}
		return paqueteManagerInstance;
	}

	@Override
	public UserManager getUserManager() {
		if (userManagerInstance == null) {
			synchronized (UserManager.class) {
				if (userManagerInstance == null) {
					userManagerInstance = UserManager.getInstance();
				}
			}
		}
		return userManagerInstance;
	}

	@Override
	public IPaqueteController getPaqueteController() {
		return new PaqueteController();
	}

	@Override
	public IUsuarioController getUsuarioController() {
		return new UsuarioController();
	}

	@Override
	public IVueloController getVueloController() {
		return new VueloController();
	}

}
