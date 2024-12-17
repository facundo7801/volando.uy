package uy.edu.fing.volandouy.modelo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.fabricas.ManagerFactory;
import uy.edu.fing.volandouy.managers.PaqueteManager;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.managers.VueloManager;

public class ManagerFactoryTest {

	private ManagerFactory managerFactory;

	@Before
	public void setUp() {
		managerFactory = ManagerFactory.getInstance();
	}

	@Test
	public void testSingletonInstance() {
		ManagerFactory anotherInstance = ManagerFactory.getInstance();
		assertSame("ManagerFactory tiene que ser Singleton", managerFactory, anotherInstance);
	}

	@Test
	public void testGetVueloManager() {
		VueloManager vueloManager = managerFactory.getVueloManager();
		assertNotNull("VueloManager no tiene que ser null", vueloManager);
		VueloManager anotherVueloManager = managerFactory.getVueloManager();
		assertSame("VueloManager tiene que ser Singleton", vueloManager, anotherVueloManager);
	}

	@Test
	public void testGetPaqueteController() {
		IPaqueteController paqueteController = managerFactory.getPaqueteController();
		assertNotNull("PaqueteController no tiene que ser null", paqueteController);
	}

	@Test
	public void testGetUsuarioController() {
		IUsuarioController usuarioController = managerFactory.getUsuarioController();
		assertNotNull("UsuarioController no tiene que ser null", usuarioController);
	}

	@Test
	public void testGetVueloController() {
		IVueloController vueloController = managerFactory.getVueloController();
		assertNotNull("VueloController no tiene que ser null", vueloController);
	}
	
	@Test
    public void testGetPaqueteManager() {
        // Comprobamos que getPaqueteManager devuelve una instancia de PaqueteManager
        PaqueteManager paqueteManager = managerFactory.getPaqueteManager();
        
        // Verificamos que no sea null
        assertNotNull("PaqueteManager should not be null", paqueteManager);
        
        // Verificamos que sea una instancia de PaqueteManager
        assertTrue("Returned object should be an instance of PaqueteManager", paqueteManager instanceof PaqueteManager);
    }

    @Test
    public void testGetUserManager() {
        // Comprobamos que getUserManager devuelve una instancia de UserManager
        UserManager userManager = managerFactory.getUserManager();
        
        // Verificamos que no sea null
        assertNotNull("UserManager should not be null", userManager);
        
        // Verificamos que sea una instancia de UserManager
        assertTrue("Returned object should be an instance of UserManager", userManager instanceof UserManager);
        
        userManager.setUsuarios(userManager.getUsuarios());
    }
}
