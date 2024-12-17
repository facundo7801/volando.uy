package uy.edu.fing.volandouy.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.fabricas.IManagerFactory;
import uy.edu.fing.volandouy.fabricas.ManagerFactory;

/*Para agregar un caso de uso hay que crear un atributo con el tipo de la ventana,
 * setearlo en null e ir a la función itemsMenuBar que esta abajo del todo */

/*Los botones de Aceptar los podemos configurar en cada frame, limpiando el formulario por ejemplo o dejando los datos iguales, asi el que este ejecutando el caso
 * sale del caso unicamente cerrando la ventana o dandole a Cancelar*/

/*Los botones de Cancelar en cada caso de uso hay que agregarlos. Cuando los creen en sus frames haganlo como un atributo
 * y creen una operacion que sea public JButton getCancelarButton(){} que retorne el boton, asi se configura aca*/

@SuppressWarnings("serial")
public class CasosDeUso extends JInternalFrame {

	// Componentes
	private JPanel contentPane;
	private JDesktopPane panelDesktop;
	// Componentes de control en el menu
	private JMenuItem btnPresionado = null;
	private JInternalFrame frameSeleccionado = null;
	//Ventanas
	private RutasMasVisitadas rutasMasVisitadasFrame = null;
	private AltaUsuario altaUsuarioFrame = null;
	private ConsultaUsuario consultaUsuarioFrame = null;
	private AltaVuelo altaVueloFrame = null;
	private ConsultaVuelo consultaVueloFrame = null;
	private AltaRutaDeVuelo altaRutaFrame = null;
	private ConsultaRutadeVuelo consultaRutaDeVueloFrame = null;
	private ReservarVuelo reservarVueloFrame = null;
	private AltaCiudad altaCiudadFrame = null;
	private AltaPaquete altaPaqueteFrame = null;
	private AgregarRutaPaquete agregarRutaPaqueteFrame = null;
	private ComprarPaquete comprarPaqueteFrame = null;
	private ConsultaPaquete consultaPaqueteFrame = null;
	private AceptarRechazarRuta aceptarRechazarRutaFrame = null;
	// Interfaces
	private IUsuarioController iusuario;
	private IVueloController ivuelo;
	private IPaqueteController ipaquete;

	
	public CasosDeUso(JPanel cardPanel, CardLayout cardLayout) {
		setBackground(new Color(0, 153, 204));
		inicializarControllers();

		// Elimina el borde de arriba de la ventana
		setBorder(null);
		((BasicInternalFrameUI) this.getUI()).setNorthPane(null);

		// Setea todo lo de la ventana
		setResizable(true);
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		setBounds(100, 100, 840, 760);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 153, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// ContentPane del JInternalFrame
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 200, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JMenuBar MenuBarItems = itemsMenuBar(cardPanel, cardLayout);
		setJMenuBar(MenuBarItems);

		// DesktopPane donde se despliegan los casos de uso (Derecha)
		panelDesktop = new JDesktopPane();
		panelDesktop.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDesktop.setName("desktopPane");
		panelDesktop.setBackground(new Color(0, 153, 204));
		GridBagConstraints gbc_panelDesktop = new GridBagConstraints();
		gbc_panelDesktop.gridwidth = 3;
		gbc_panelDesktop.gridheight = 2;
		gbc_panelDesktop.insets = new Insets(0, 0, 5, 0);
		gbc_panelDesktop.fill = GridBagConstraints.BOTH;
		gbc_panelDesktop.gridx = 0;
		gbc_panelDesktop.gridy = 0;
		contentPane.add(panelDesktop, gbc_panelDesktop);

	}

	private void inicializarControllers() {
		IManagerFactory fabrica = ManagerFactory.getInstance();
		iusuario = fabrica.getUsuarioController();
		ivuelo = fabrica.getVueloController();
		ipaquete = fabrica.getPaqueteController();

	}

	private void seleccionarFrame(String nombreFrame) {
		switch (nombreFrame) {
		case "Alta Usuario":
			frameSeleccionado = altaUsuarioFrame;
			break;
		case "Consulta Usuario":
			frameSeleccionado = consultaUsuarioFrame;
			break;
		case "Alta Vuelo":
			frameSeleccionado = altaVueloFrame;
			break;
		case "Consulta Vuelo":
			frameSeleccionado = consultaVueloFrame;
			break;
		case "Alta Ruta de Vuelo":
			frameSeleccionado = altaRutaFrame;
			break;
		case "Consulta Ruta de Vuelo":
			frameSeleccionado = consultaRutaDeVueloFrame;
			break;
		case "Confirmar/Rechazar Ruta de Vuelo":
			frameSeleccionado = aceptarRechazarRutaFrame;
			break;
		case "Reservar Vuelo":
			frameSeleccionado = reservarVueloFrame;
			break;
		case "Alta Ciudad":
			frameSeleccionado = altaCiudadFrame;
			break;
		case "Alta Paquete":
			frameSeleccionado = altaPaqueteFrame;
			break;
		case "Agregar Ruta Paquete":
			frameSeleccionado = agregarRutaPaqueteFrame;
			break;
		case "Comprar Paquete":
			frameSeleccionado = comprarPaqueteFrame;
			break;
		case "Consulta Paquete":
			frameSeleccionado = consultaPaqueteFrame;
			break;
		case "Rutas mas visitadas":
			frameSeleccionado = rutasMasVisitadasFrame;
			break;
		}
	}

	// Si el frame todavia no tiene un boton de cancelar, en el case del frame poner
	// un break; y el boton queda inicializado en null
	private JButton seleccionarCancelarButtonFrame(String nombreFrame) {
		switch (nombreFrame) {
		case "Alta Usuario":
			return altaUsuarioFrame.getCancelar();
		case "Consulta Usuario":
			return consultaUsuarioFrame.getCancelar();
		case "Alta Vuelo":
			return altaVueloFrame.getCancelarButton();
		case "Consulta Vuelo":
			return consultaVueloFrame.getCancelarButton();
		case "Alta Ruta de Vuelo":
			return altaRutaFrame.getCancelarButton();
		case "Consulta Ruta de Vuelo":
			return consultaRutaDeVueloFrame.getCancelarButton();
		case "Confirmar/Rechazar Ruta de Vuelo":
			return aceptarRechazarRutaFrame.getCancelarButton();
		case "Reservar Vuelo":
			return reservarVueloFrame.getCancelarButton();
		case "Alta Ciudad":
			return altaCiudadFrame.getCancelarButton();
		case "Alta Paquete":
			return altaPaqueteFrame.getCancelarButton();
		case "Comprar Paquete":
			return comprarPaqueteFrame.getCancelarButton();
		case "Agregar Ruta Paquete":
			return agregarRutaPaqueteFrame.getCancelarButton();
		case "Consulta Paquete":
			return consultaPaqueteFrame.getCancelarButton();
		case "Rutas mas visitadas":
			return rutasMasVisitadasFrame.getCancelarButton();
		}

		return null;

	}

	private void inicializarVentana(String nombreFrame) {
		switch (nombreFrame) {
		case "Alta Usuario":
			altaUsuarioFrame = new AltaUsuario(iusuario);
			break;
		case "Consulta Usuario":
			consultaUsuarioFrame = new ConsultaUsuario(iusuario, ivuelo, ipaquete);
			break;
		case "Alta Vuelo":
			altaVueloFrame = new AltaVuelo(iusuario, ivuelo);
			break;
		case "Consulta Vuelo":
			consultaVueloFrame = new ConsultaVuelo(iusuario, ivuelo);
			break;
		case "Alta Ruta de Vuelo":
			altaRutaFrame = new AltaRutaDeVuelo(iusuario, ivuelo);
			break;
		case "Consulta Ruta de Vuelo":
			consultaRutaDeVueloFrame = new ConsultaRutadeVuelo(iusuario, ivuelo);
			break;
		case "Confirmar/Rechazar Ruta de Vuelo":
			aceptarRechazarRutaFrame = new AceptarRechazarRuta(iusuario);
			break;
		case "Reservar Vuelo":
			reservarVueloFrame = new ReservarVuelo(iusuario, ivuelo, ipaquete);
			break;
		case "Alta Ciudad":
			altaCiudadFrame = new AltaCiudad(ivuelo);
			break;
		case "Alta Paquete":
			altaPaqueteFrame = new AltaPaquete(ipaquete);
			break;
		case "Agregar Ruta Paquete":
			agregarRutaPaqueteFrame = new AgregarRutaPaquete(iusuario, ipaquete);
			break;
		case "Comprar Paquete":
			comprarPaqueteFrame = new ComprarPaquete(iusuario, ipaquete);
			break;
		case "Consulta Paquete":
			consultaPaqueteFrame = new ConsultaPaquete(iusuario, ivuelo, ipaquete);
			break;
		case "Rutas mas visitadas":
			rutasMasVisitadasFrame = new RutasMasVisitadas(iusuario);
			break;
		}

	}

	// Se ejecuta solo si un caso de uso esta abierto y se abre otro sin cerrar el
	// primero
	private void destruirVentana(String nombreFrame) {
		switch (nombreFrame) {
		case "Alta Usuario":
			panelDesktop.remove(altaUsuarioFrame);
			altaUsuarioFrame.dispose();
			break;
		case "Consulta Usuario":
			panelDesktop.remove(consultaUsuarioFrame);
			consultaUsuarioFrame.dispose();
			break;
		case "Alta Vuelo":
			panelDesktop.remove(altaVueloFrame);
			altaVueloFrame.dispose();
			break;
		case "Consulta Vuelo":
			panelDesktop.remove(consultaVueloFrame);
			consultaVueloFrame.dispose();
			break;
		case "Alta Ruta de Vuelo":
			panelDesktop.remove(altaRutaFrame);
			altaRutaFrame.dispose();
			break;
		case "Consulta Ruta de Vuelo":
			panelDesktop.remove(consultaRutaDeVueloFrame);
			consultaRutaDeVueloFrame.dispose();
			break;
		case "Confirmar/Rechazar Ruta de Vuelo":
			panelDesktop.remove(aceptarRechazarRutaFrame);
			aceptarRechazarRutaFrame.dispose();
			break;
		case "Reservar Vuelo":
			panelDesktop.remove(reservarVueloFrame);
			reservarVueloFrame.dispose();
			break;
		case "Alta Ciudad":
			panelDesktop.remove(altaCiudadFrame);
			altaCiudadFrame.dispose();
			break;
		case "Alta Paquete":
			panelDesktop.remove(altaPaqueteFrame);
			altaPaqueteFrame.dispose();
			break;
		case "Agregar Ruta Paquete":
			panelDesktop.remove(agregarRutaPaqueteFrame);
			agregarRutaPaqueteFrame.dispose();
			break;
		case "Comprar Paquete":
			panelDesktop.remove(comprarPaqueteFrame);
			comprarPaqueteFrame.dispose();
			break;
		case "Consulta Paquete":
			panelDesktop.remove(consultaPaqueteFrame);
			consultaPaqueteFrame.dispose();
			break;
		case "Rutas mas visitadas":
			panelDesktop.remove(rutasMasVisitadasFrame);
			rutasMasVisitadasFrame.dispose();
			break;
		}
	}

	private void agregarItemMenu(String texto, JMenu itemsContainer, String nombreFrame) {
		// Item
		JMenuItem btnNuevo = new JMenuItem(texto);
		btnNuevo.setName(nombreFrame);
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (btnPresionado != null) {
					destruirVentana(btnPresionado.getName());
					btnPresionado.setEnabled(true);
				}
				inicializarVentana(nombreFrame);
				seleccionarFrame(nombreFrame);
				panelDesktop.add(frameSeleccionado);
				frameSeleccionado.setVisible(true);
				btnPresionado = btnNuevo;
				// Evento de cuando se cierra la ventana
				frameSeleccionado.addInternalFrameListener(new InternalFrameAdapter() {
					@Override
					public void internalFrameClosing(InternalFrameEvent e) {
						frameSeleccionado.setVisible(false);
						panelDesktop.remove(frameSeleccionado);
						// Esto destruye la ventan si es cerrada directamente, si se abre otro caso de
						// uso se ejecuta destruirVentana
						frameSeleccionado.dispose();
						btnNuevo.setEnabled(true);
						if (btnPresionado.getName().equals(btnNuevo.getName())) {
							btnPresionado = null;
						}
					}
				});

				btnNuevo.setEnabled(false);

				// Setea el boton de Cancelar del frame para que funcione adecuadamente cuando
				// se presiona
				JButton btnCancelar = seleccionarCancelarButtonFrame(nombreFrame);
				if (btnCancelar != null) {
					btnCancelar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							frameSeleccionado.setVisible(false);
							panelDesktop.remove(frameSeleccionado);
							// Esto destruye la ventan si es cerrada directamente, si se abre otro caso de
							// uso se ejecuta destruirVentana
							frameSeleccionado.dispose();
							btnNuevo.setEnabled(true);
							if (btnPresionado.getName().equals(btnNuevo.getName())) {
								btnPresionado = null;
							}
						}
					});
				}
				// Termina el seteo

			}
		});
		// Centrar horizontalmente
		btnNuevo.setAlignmentX(Component.CENTER_ALIGNMENT);
		itemsContainer.add(btnNuevo);

	}

	private JMenu agregarMenu(JMenuBar menuBar, String texto) {
		JMenu menuNuevo = new JMenu(texto);
		menuNuevo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		menuBar.add(menuNuevo);

		return menuNuevo;
	}

	// Agrega los casos de uso como botones al menu vertical
	private JMenuBar itemsMenuBar(JPanel cardPanel, CardLayout cardLayout) {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(0, 153, 204));
		menuBar.setBorder(new LineBorder(new Color(0, 0, 0)));

		// Opciones del sistema
		JMenu sistema = agregarMenu(menuBar, "Sistema");
		sistema.setOpaque(true);
		sistema.setBackground(new Color(0, 153, 255));
		sistema.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));

		// MenuItem para ir hacia atras
		JMenuItem btnAtrasButton = new JMenuItem("Atras");
		btnAtrasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "Menu");
				if (btnPresionado != null) {
					destruirVentana(btnPresionado.getName());
					btnPresionado.setEnabled(true);
					frameSeleccionado = null;
					btnPresionado = null;
				}
			}
		});
		sistema.add(btnAtrasButton);

		// Casos de uso y otros
		JMenu itemsUsuario = agregarMenu(menuBar, "Usuario");
		itemsUsuario.setOpaque(true);
		itemsUsuario.setBackground(new Color(0, 153, 255));
		itemsUsuario.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
		JMenu itemsRuta = agregarMenu(menuBar, "Rutas");
		itemsRuta.setOpaque(true);
		itemsRuta.setBackground(new Color(0, 153, 255));
		itemsRuta.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
		JMenu itemsVuelo = agregarMenu(menuBar, "Vuelos");
		itemsVuelo.setOpaque(true);
		itemsVuelo.setBackground(new Color(0, 153, 255));
		itemsVuelo.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
		JMenu itemsCiudad = agregarMenu(menuBar, "Ciudad");
		itemsCiudad.setOpaque(true);
		itemsCiudad.setBackground(new Color(0, 153, 255));
		itemsCiudad.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
		JMenu itemsPaquete = agregarMenu(menuBar, "Paquetes");
		itemsPaquete.setOpaque(true);
		itemsPaquete.setBackground(new Color(0, 153, 255));
		itemsPaquete.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));

		// Items
		/*
		 * Hay que llamar a la función agregarItemMenu y los parametros son: El texto
		 * que se va a mostrar en el JMenu (Boton) del MenuBar El JMenu donde se
		 * muestran todos los JMenuItems (Ej: caso de uso de Alta Usuario) El nombre que
		 * va a tener el JMenuItem, siendo este el identificador (Que sea unico en el
		 * sistema lo tenemos que controlar a mano) Los JMenuItem se muestran en el
		 * orden que se agregaron, el primero se muestra arriba y el ultimo abajo
		 */
		agregarItemMenu("Alta Usuario", itemsUsuario, "Alta Usuario");
		agregarItemMenu("Consulta Usuario", itemsUsuario, "Consulta Usuario");
		agregarItemMenu("Alta Ruta de Vuelo", itemsRuta, "Alta Ruta de Vuelo");
		agregarItemMenu("Consulta Ruta de Vuelo", itemsRuta, "Consulta Ruta de Vuelo");
		agregarItemMenu("Confirmar/Rechazar Ruta de Vuelo", itemsRuta, "Confirmar/Rechazar Ruta de Vuelo");
		agregarItemMenu("Rutas mas visitadas", itemsRuta, "Rutas mas visitadas");
		agregarItemMenu("Alta Vuelo", itemsVuelo, "Alta Vuelo");
		agregarItemMenu("Reservar Vuelo", itemsVuelo, "Reservar Vuelo");
		agregarItemMenu("Consulta Vuelo", itemsVuelo, "Consulta Vuelo");
		agregarItemMenu("Alta Ciudad", itemsCiudad, "Alta Ciudad");
		agregarItemMenu("Alta Paquete", itemsPaquete, "Alta Paquete");
		agregarItemMenu("Agregar Ruta Paquete", itemsPaquete, "Agregar Ruta Paquete");
		agregarItemMenu("Comprar Paquete", itemsPaquete, "Comprar Paquete");
		agregarItemMenu("Consulta Paquete", itemsPaquete, "Consulta Paquete");
		
		return menuBar;

	}

}
