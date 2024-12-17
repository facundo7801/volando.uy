package uy.edu.fing.volandouy.fabricas;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.managers.PaqueteManager;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.managers.VueloManager;

public interface IManagerFactory {
	VueloManager getVueloManager();

	PaqueteManager getPaqueteManager();

	UserManager getUserManager();

	IPaqueteController getPaqueteController();

	IUsuarioController getUsuarioController();

	IVueloController getVueloController();
}
