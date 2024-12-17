package uy.edu.fing.volandouy.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import uy.edu.fing.volandouy.fabricas.IManagerFactory;
import uy.edu.fing.volandouy.fabricas.ManagerFactory;
import uy.edu.fing.volandouy.managers.PaqueteManager;
import uy.edu.fing.volandouy.managers.UserManager;
import uy.edu.fing.volandouy.managers.VueloManager;
import uy.edu.fing.volandouy.util.DataLoader;
import uy.edu.fing.volandouy.webServices.LoginPublicador;

@SuppressWarnings("serial")
public class MainViewV2 extends JFrame {
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private CasosDeUso casosDeUsoFrame;
	@SuppressWarnings("unused")
	private VueloManager vueloManager;
	@SuppressWarnings("unused")
	private PaqueteManager paqueteManager;
	@SuppressWarnings("unused")
	private UserManager userManager;

    
    //static {
       // t//ry (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
       //     properties.load(fis);
     //   } catch (IOException e) {
       //     throw new RuntimeException("Error al cargar el archivo de configuración: " + CONFIG_PATH, e);
     //   }
   // }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainViewV2 frame = new MainViewV2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	
	public MainViewV2() {
		// Obtener la instancia Singleton de la Factory
		IManagerFactory managerFactory = ManagerFactory.getInstance();

		// Inicializar los manejadores
		vueloManager = managerFactory.getVueloManager();
		paqueteManager = managerFactory.getPaqueteManager();
		userManager = managerFactory.getUserManager();
		
		//Publicador WebServices
		LoginPublicador inicializador = new LoginPublicador();
		inicializador.iniciarPublicacion();
		

		// Inicializar la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 840, 760);

		// Crea el CardLayout
		cardLayout = new CardLayout();
		// Crea el contenedor del CardLayout
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBackground(new Color(240, 240, 240));
		setContentPane(cardPanel);

		// Crear y agregar la primera tarjeta (contenido principal)
		JPanel principalContentPanel = crearPrincipalContentPanel();
		cardPanel.add(principalContentPanel, "Menu");

		// Crear y agregar la segunda tarjeta (JDesktopPane con JInternalFrame)
		JDesktopPane desktopPane = new JDesktopPane();
		casosDeUsoFrame = new CasosDeUso(cardPanel, cardLayout);
		casosDeUsoFrame.setBackground(new Color(128, 128, 128));
		casosDeUsoFrame.setSize(800, 600);
		casosDeUsoFrame.setVisible(true);
		desktopPane.add(casosDeUsoFrame);
		cardPanel.add(desktopPane, "CasosDeUso");

		// Mostrar la tarjeta inicial
		cardLayout.show(cardPanel, "Menu");

	}

	private JPanel crearPrincipalContentPanel() {
		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(255, 153, 51));
		GridBagLayout gbl_panelMenu = new GridBagLayout();
		gbl_panelMenu.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelMenu.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gbl_panelMenu.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelMenu.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelMenu.setLayout(gbl_panelMenu);

		JPanel panelOpciones = new JPanel();
		panelOpciones.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelOpciones.setBackground(new Color(0, 153, 204));
		GridBagConstraints gbc_panelOpciones = new GridBagConstraints();
		gbc_panelOpciones.gridwidth = 8;
		gbc_panelOpciones.gridheight = 24;
		gbc_panelOpciones.insets = new Insets(0, 0, 5, 5);
		gbc_panelOpciones.fill = GridBagConstraints.BOTH;
		gbc_panelOpciones.gridx = 2;
		gbc_panelOpciones.gridy = 1;
		panelMenu.add(panelOpciones, gbc_panelOpciones);
		GridBagLayout gbl_panelOpciones = new GridBagLayout();
		gbl_panelOpciones.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelOpciones.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0 };
		gbl_panelOpciones.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelOpciones.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelOpciones.setLayout(gbl_panelOpciones);

		JLabel lblTituloLabel = new JLabel(
				"<html><div style='text-align: center;'>" + "<b><span style='font-size:18px;'>Volando.uy</span></b><br>"
						+ "<b><span style='font-size:18px;'>Grupo 23</span></b>" + "</div></html>");
		GridBagConstraints gbc_lblTituloLabel = new GridBagConstraints();
		gbc_lblTituloLabel.gridheight = 6;
		gbc_lblTituloLabel.gridwidth = 6;
		gbc_lblTituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloLabel.gridx = 3;
		gbc_lblTituloLabel.gridy = 1;
		panelOpciones.add(lblTituloLabel, gbc_lblTituloLabel);

		JButton btnCasosDeUsoButton = new JButton("Casos de Uso");
		GridBagConstraints gbc_btnCasosDeUsoButton = new GridBagConstraints();
		gbc_btnCasosDeUsoButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCasosDeUsoButton.gridwidth = 4;
		gbc_btnCasosDeUsoButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnCasosDeUsoButton.gridx = 4;
		gbc_btnCasosDeUsoButton.gridy = 7;
		btnCasosDeUsoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "CasosDeUso");
			}
		});
		panelOpciones.add(btnCasosDeUsoButton, gbc_btnCasosDeUsoButton);

		JButton btnCargarDatos = new JButton("Cargar datos");
		btnCargarDatos.setEnabled(true);
		GridBagConstraints gbc_btnCargarDatos = new GridBagConstraints();
		gbc_btnCargarDatos.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCargarDatos.gridwidth = 4;
		gbc_btnCargarDatos.insets = new Insets(0, 0, 5, 5);
		gbc_btnCargarDatos.gridx = 4;
		gbc_btnCargarDatos.gridy = 9;
		btnCargarDatos.addActionListener(e -> {
			try {	
				String proyectoPath = System.getProperty("user.dir");
				DataLoader dataLoader = new DataLoader(true, proyectoPath);
				dataLoader.loadAllFiles();
				dataLoader.processUsuarios();
				dataLoader.processCategorias();
				dataLoader.processCiudades();
				dataLoader.processRutasDeVuelo();
				dataLoader.processVuelos();
				dataLoader.processReservasYPasajes();
				dataLoader.processPaquetes();
				dataLoader.processPaquetesRutas();
				dataLoader.processComprasPaquetes();
				dataLoader.processSeguidos();

				
				btnCargarDatos.setEnabled(false);
				JOptionPane.showMessageDialog(panelOpciones, "Datos cargados exitosamente.");

			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(panelOpciones, "Error al cargar los datos: " + ex.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		panelOpciones.add(btnCargarDatos, gbc_btnCargarDatos);

		JButton btnSalir = new JButton("Salir");
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalir.gridwidth = 4;
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 4;
		gbc_btnSalir.gridy = 11;
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
				System.exit(0);
			}
		});

		panelOpciones.add(btnSalir, gbc_btnSalir);

		JLabel lblVersionLabel = new JLabel("<html><b>v3.0</b></html>");
		GridBagConstraints gbc_lblVersionLabel = new GridBagConstraints();
		gbc_lblVersionLabel.gridheight = 2;
		gbc_lblVersionLabel.gridwidth = 2;
		gbc_lblVersionLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblVersionLabel.gridx = 0;
		gbc_lblVersionLabel.gridy = 24;
		panelOpciones.add(lblVersionLabel, gbc_lblVersionLabel);

		JLabel lblGrupoLabel = new JLabel(
				"<html><div style='text-align: center;'><b>2do semestre<br>2024</b></div></html>");
		GridBagConstraints gbc_lblGrupoLabel = new GridBagConstraints();
		gbc_lblGrupoLabel.gridheight = 2;
		gbc_lblGrupoLabel.gridwidth = 3;
		gbc_lblGrupoLabel.gridx = 9;
		gbc_lblGrupoLabel.gridy = 24;
		panelOpciones.add(lblGrupoLabel, gbc_lblGrupoLabel);

		return panelMenu;
	}
}
