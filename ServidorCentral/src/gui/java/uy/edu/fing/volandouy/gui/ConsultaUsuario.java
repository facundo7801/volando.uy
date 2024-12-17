package uy.edu.fing.volandouy.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;

@SuppressWarnings("serial")
public class ConsultaUsuario extends JInternalFrame {
	// Controllers
	private IUsuarioController controlusr;
	private IVueloController controlvuelo;
	private IPaqueteController controlpaquete;
	// CardLayout
	private CardLayout cardLayoutPrincipal;
	private JPanel panelPrincipal;
	private JPanel panelAntiguo;
	private JPanel panelMasDatos;
	private JDesktopPane desktopPaneVuelo;
	private JDesktopPane desktopPanePaquete;
	private JDesktopPane desktopPaneRuta;

	// Ventanas
	private ConsultaRutadeVuelo consultaRutaFrame;
	private ConsultaVuelo consultaVueloFrame;
	private ConsultaPaquete consultaPaqueteFrame;

	// Componentes
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JPanel panelVacio;
	private JPanel panelUsuario;
	private JPanel panelAerolinea;
	private JPanel panelCliente;
	private JTextField textNick;
	private JTextField textEmail;
	private JTextField textName;
	private JButton btnSalirButton;
	private JButton btnModificarButton;
	// Componentes PanelCliente
	private JLabel lblApellidoLabel;
	private JLabel lblFechaNacimientoLabel;
	private JLabel lblNacionalidadLabel;
	private JLabel lblTipoDocumentoLabel;
	private JLabel lblNumeroDocumentoLabel;
	private JTextField textDoc;
	private JTextField textApellido;
	private JTextField textNacionalidad;
	private JComboBox<Integer> CBDia;
	private JComboBox<String> CBMes;
	private JComboBox<Integer> CBAnio;
	private JComboBox<TipoDocumento> CBTipoDoc;
	// Componentes PanelAerolinea
	private JLabel lblWebLabel;
	private JTextField textWeb;
	private JLabel lblDescripcionLabel;
	private JTextArea textArea;
	private JLabel lblRutasLabel;
	private JComboBox<String> CBRutas;
	private JComboBox<String> CBUsuario;
	private JButton btnConsultarRutaButton;
	private JButton btnGuardarButton;

	// Variables auxiliares
	private List<UsuarioDto> usersList;
	private List<String> nicknames;
	private UsuarioDto respaldoUsr = null;
	//private String nacionalidad;
	private List<String> rutasDeVueloAux;
	//private List<RutaDeVueloDto> rutalst;
	private JButton btnMasDatosClienteButton;
	private JLabel lblVuelosLabel;
	private JComboBox<String> comboBoxVuelos;
	private JLabel lblPaqueteLabel;
	private JComboBox<String> comboBoxPaquetes;
	private JButton btnConsultaVueloButton;
	private JButton btnConsultaPaqueteButton;
	private JButton btnAtrasButton;

	public ConsultaUsuario(IUsuarioController controlUsuario, IVueloController ivuelo, IPaqueteController controlpaquete) {
		controlusr = controlUsuario;
		controlvuelo = ivuelo;
		this.controlpaquete = controlpaquete;

		usersList = controlUsuario.listarUsuario();

		nicknames = new ArrayList<>();

		for (UsuarioDto usuario : usersList) {
			nicknames.add(usuario.getNickName());
		}

		setClosable(true);
		setResizable(true);
		setTitle("Consulta Usuario");
		setBounds(100, 100, 840, 760);
		setVisible(false);
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		// Crea el contenedor del CardLayout
		// Crea el CardLayout
		cardLayoutPrincipal = new CardLayout();
		// Crea el contenedor del CardLayout
		panelPrincipal = new JPanel(cardLayoutPrincipal);
		panelPrincipal.setBackground(new Color(240, 240, 240));
		setContentPane(panelPrincipal);
		
		panelAntiguo = new JPanel();
		panelPrincipal.add(panelAntiguo, "panelAntiguo");

		// Consulta Ruta
		desktopPaneRuta = new JDesktopPane();
		desktopPaneRuta.setBackground(new Color(240, 240, 240));
		panelPrincipal.add(desktopPaneRuta, "ConsultaRuta");

		// Consulta Vuelo
		desktopPaneVuelo = new JDesktopPane();
		desktopPaneVuelo.setBackground(new Color(240, 240, 240));
		panelPrincipal.add(desktopPaneVuelo, "ConsultaVuelo");

		//Consulta Paquete
		desktopPanePaquete = new JDesktopPane();
		desktopPanePaquete.setBackground(new Color(240, 240, 240));
		panelPrincipal.add(desktopPanePaquete, "ConsultaPaquete");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelAntiguo.setLayout(gridBagLayout);

		InicializarPanelUsuario();

		// Crea el contenedor del CardLayout
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBackground(new Color(240, 240, 240));
		GridBagConstraints gbc_cardPanel = new GridBagConstraints();
		gbc_cardPanel.gridwidth = 12;
		gbc_cardPanel.gridheight = 14;
		gbc_cardPanel.insets = new Insets(0, 0, 5, 0);
		gbc_cardPanel.fill = GridBagConstraints.BOTH;
		gbc_cardPanel.gridx = 0;
		gbc_cardPanel.gridy = 9;
		panelAntiguo.add(cardPanel, gbc_cardPanel);

		// Crea el panel vacio que va a ser el incial del cardLayout
		panelVacio = new JPanel();
		cardPanel.add(panelVacio, "Vacio");

		InicializarPanelMostrarMasDatos();
		
		InicializarPanelAerolinea();

		InicializarPanelCliente();

		// Botones de Salir y Registrar
		btnModificarButton = new JButton("Modificar");
		btnModificarButton.setVisible(false);
		btnModificarButton.setEnabled(false);
		GridBagConstraints gbc_btnModificarButton = new GridBagConstraints();
		gbc_btnModificarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnModificarButton.gridwidth = 2;
		gbc_btnModificarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnModificarButton.gridx = 3;
		gbc_btnModificarButton.gridy = 23;
		btnModificarButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (btnModificarButton.getText().equals("Modificar")) {
					textName.setEditable(true);
					textApellido.setEditable(true);
					textNacionalidad.setEditable(true);
					// CBNacionalidad.setEnabled(true);

					textDoc.setEditable(true);
					textWeb.setEditable(true);
					textArea.setEditable(true);
					CBDia.setEnabled(true);
					CBMes.setEnabled(true);
					CBAnio.setEnabled(true);
					btnGuardarButton.setEnabled(true);
					btnGuardarButton.setVisible(true);
					CBTipoDoc.setEnabled(true);
					CBUsuario.setEnabled(false);

					btnModificarButton.setText("Cancelar");
				} else {
					textName.setEditable(false);
					textApellido.setEditable(false);
					textNacionalidad.setEditable(false);
					// CBNacionalidad.setEnabled(false);

					textDoc.setEditable(false);
					textWeb.setEditable(false);
					textArea.setEditable(false);
					CBDia.setEnabled(false);
					CBMes.setEnabled(false);
					CBAnio.setEnabled(false);
					CBTipoDoc.setEnabled(false);
					btnGuardarButton.setEnabled(false);
					btnGuardarButton.setVisible(false);
					CBUsuario.setEnabled(true);

					textName.setText(respaldoUsr.getNombre());
					if (respaldoUsr instanceof ClienteDto) {

						ClienteDto aux = (ClienteDto) respaldoUsr;
						textApellido.setText(aux.getApellido());
						textNacionalidad.setText(aux.getNacionalidad());
						//nacionalidad = aux.getNacionalidad();
						/* setNacionalidadCB(); */

						textDoc.setText(aux.getNumeroDocumento());
						Date dateAux = aux.getFechaNacimiento();
						CBAnio.setSelectedIndex(dateAux.getYear() - 24);
						CBMes.setSelectedIndex(dateAux.getMonth());
						CBDia.setSelectedIndex(dateAux.getDate() - 1);

						TipoDocumento DocAux = aux.getTipoDocumento();
						CBTipoDoc.setSelectedItem(DocAux);

					} else {
						AerolineaDto auxAero = (AerolineaDto) respaldoUsr;
						textWeb.setText(auxAero.getWebsite());
						textArea.setText(auxAero.getDescripcion());

					}

					btnModificarButton.setText("Modificar");

				}
			}
		});

		panelAntiguo.add(btnModificarButton, gbc_btnModificarButton);

		cardLayout.show(cardPanel, "Vacio");

		btnGuardarButton = new JButton("Guardar");
		btnGuardarButton.setVisible(false);
		btnGuardarButton.setEnabled(false);
		GridBagConstraints gbc_btnGuardarButton = new GridBagConstraints();
		gbc_btnGuardarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardarButton.gridx = 6;
		gbc_btnGuardarButton.gridy = 23;

		btnGuardarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarModificar();
				textName.setEditable(false);
				textApellido.setEditable(false);
				textNacionalidad.setEditable(false);
				// CBNacionalidad.setEnabled(false);

				textDoc.setEditable(false);
				textWeb.setEditable(false);
				textArea.setEditable(false);
				CBDia.setEnabled(false);
				CBMes.setEnabled(false);
				CBAnio.setEnabled(false);
				CBTipoDoc.setEnabled(false);
				btnGuardarButton.setEnabled(false);
				btnGuardarButton.setVisible(false);
				CBUsuario.setEnabled(true);
				guardarRespaldo();
				btnModificarButton.setText("Modificar");

			}
		});

		panelAntiguo.add(btnGuardarButton, gbc_btnGuardarButton);

		btnSalirButton = new JButton("Salir");
		GridBagConstraints gbc_btnSalirButton = new GridBagConstraints();
		gbc_btnSalirButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalirButton.gridwidth = 2;
		gbc_btnSalirButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalirButton.gridx = 8;
		gbc_btnSalirButton.gridy = 23;
		panelAntiguo.add(btnSalirButton, gbc_btnSalirButton);

	}

	private void InicializarPanelUsuario() {
		panelUsuario = new JPanel();
		GridBagConstraints gbc_panelUsuario = new GridBagConstraints();
		gbc_panelUsuario.gridwidth = 12;
		gbc_panelUsuario.gridheight = 9;
		gbc_panelUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_panelUsuario.fill = GridBagConstraints.BOTH;
		gbc_panelUsuario.gridx = 0;
		gbc_panelUsuario.gridy = 0;
		panelAntiguo.add(panelUsuario, gbc_panelUsuario);
		GridBagLayout gbl_panelUsuario = new GridBagLayout();
		gbl_panelUsuario.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelUsuario.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelUsuario.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelUsuario.rowWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelUsuario.setLayout(gbl_panelUsuario);

		JLabel lblTipoUsuarioLabel = new JLabel("Usuario");
		GridBagConstraints gbc_lblTipoUsuarioLabel = new GridBagConstraints();
		gbc_lblTipoUsuarioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoUsuarioLabel.gridx = 2;
		gbc_lblTipoUsuarioLabel.gridy = 1;
		panelUsuario.add(lblTipoUsuarioLabel, gbc_lblTipoUsuarioLabel);

		CBUsuario = new JComboBox<>();

		for (String nick : nicknames) {
			CBUsuario.addItem(nick);
		}
		// Si no hay items se deshabilita
		if (nicknames.size() == 0) {
			CBUsuario.setEnabled(false);
		}
		GridBagConstraints gbc_cBUsuario = new GridBagConstraints();
		gbc_cBUsuario.gridwidth = 5;
		gbc_cBUsuario.insets = new Insets(0, 0, 5, 5);
		gbc_cBUsuario.fill = GridBagConstraints.HORIZONTAL;
		gbc_cBUsuario.gridx = 3;
		gbc_cBUsuario.gridy = 1;
		CBUsuario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evento) {
				mostrarDatos(usersList, evento);
			}
		});
		panelUsuario.add(CBUsuario, gbc_cBUsuario);

		JLabel lblNickLabel = new JLabel("Nickname");
		GridBagConstraints gbc_lblNickLabel = new GridBagConstraints();
		gbc_lblNickLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickLabel.gridx = 2;
		gbc_lblNickLabel.gridy = 3;
		panelUsuario.add(lblNickLabel, gbc_lblNickLabel);

		textNick = new JTextField();
		textNick.setEditable(false);
		GridBagConstraints gbc_textNick = new GridBagConstraints();
		gbc_textNick.gridwidth = 5;
		gbc_textNick.insets = new Insets(0, 0, 5, 5);
		gbc_textNick.fill = GridBagConstraints.BOTH;
		gbc_textNick.gridx = 3;
		gbc_textNick.gridy = 3;
		panelUsuario.add(textNick, gbc_textNick);
		textNick.setColumns(10);

		JLabel lblEmailLabel = new JLabel("Email");
		GridBagConstraints gbc_lblEmailLabel = new GridBagConstraints();
		gbc_lblEmailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailLabel.gridx = 2;
		gbc_lblEmailLabel.gridy = 5;
		panelUsuario.add(lblEmailLabel, gbc_lblEmailLabel);

		textEmail = new JTextField();
		textEmail.setEditable(false);
		textEmail.setColumns(10);
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.gridwidth = 5;
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.fill = GridBagConstraints.BOTH;
		gbc_textEmail.gridx = 3;
		gbc_textEmail.gridy = 5;
		panelUsuario.add(textEmail, gbc_textEmail);

		JLabel lblNombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombreLabel = new GridBagConstraints();
		gbc_lblNombreLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNombreLabel.gridx = 2;
		gbc_lblNombreLabel.gridy = 7;
		panelUsuario.add(lblNombreLabel, gbc_lblNombreLabel);

		textName = new JTextField();
		textName.setEditable(false);
		textName.setColumns(10);
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.gridwidth = 5;
		gbc_textName.insets = new Insets(0, 0, 0, 5);
		gbc_textName.fill = GridBagConstraints.BOTH;
		gbc_textName.gridx = 3;
		gbc_textName.gridy = 7;
		panelUsuario.add(textName, gbc_textName);

	}

	private void InicializarPanelAerolinea() {
		panelAerolinea = new JPanel();
		GridBagConstraints gbc_panelAerolinea = new GridBagConstraints();
		gbc_panelAerolinea.gridwidth = 12;
		gbc_panelAerolinea.gridheight = 7;
		gbc_panelAerolinea.insets = new Insets(0, 0, 5, 0);
		gbc_panelAerolinea.fill = GridBagConstraints.BOTH;
		gbc_panelAerolinea.gridx = 0;
		gbc_panelAerolinea.gridy = 0;
		GridBagLayout gbl_panelAerolinea = new GridBagLayout();
		gbl_panelAerolinea.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelAerolinea.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelAerolinea.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, Double.MIN_VALUE };
		gbl_panelAerolinea.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panelAerolinea.setLayout(gbl_panelAerolinea);

		cardPanel.add(panelAerolinea, "Aerolinea");

		lblWebLabel = new JLabel("Web");
		GridBagConstraints gbc_lblWebLabel = new GridBagConstraints();
		gbc_lblWebLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblWebLabel.gridx = 1;
		gbc_lblWebLabel.gridy = 2;
		panelAerolinea.add(lblWebLabel, gbc_lblWebLabel);

		textWeb = new JTextField();
		textWeb.setEditable(false);
		GridBagConstraints gbc_textWeb = new GridBagConstraints();
		gbc_textWeb.gridwidth = 5;
		gbc_textWeb.insets = new Insets(0, 0, 5, 5);
		gbc_textWeb.fill = GridBagConstraints.BOTH;
		gbc_textWeb.gridx = 2;
		gbc_textWeb.gridy = 2;
		panelAerolinea.add(textWeb, gbc_textWeb);
		textWeb.setColumns(10);

		lblDescripcionLabel = new JLabel("Descripción");
		GridBagConstraints gbc_lblDescripcionLabel = new GridBagConstraints();
		gbc_lblDescripcionLabel.gridwidth = 5;
		gbc_lblDescripcionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionLabel.gridx = 2;
		gbc_lblDescripcionLabel.gridy = 4;
		panelAerolinea.add(lblDescripcionLabel, gbc_lblDescripcionLabel);

		lblRutasLabel = new JLabel("Rutas de Vuelo");
		GridBagConstraints gbc_lblRutasLabel = new GridBagConstraints();
		gbc_lblRutasLabel.gridwidth = 5;
		gbc_lblRutasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutasLabel.gridx = 8;
		gbc_lblRutasLabel.gridy = 4;
		panelAerolinea.add(lblRutasLabel, gbc_lblRutasLabel);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBorder(textWeb.getBorder());
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 8;
		gbc_textArea.gridwidth = 5;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 5;
		panelAerolinea.add(textArea, gbc_textArea);

		CBRutas = new JComboBox<>();
		CBRutas.setEnabled(false);
		GridBagConstraints gbc_cBRutas = new GridBagConstraints();
		gbc_cBRutas.gridheight = 2;
		gbc_cBRutas.gridwidth = 5;
		gbc_cBRutas.insets = new Insets(0, 0, 5, 5);
		gbc_cBRutas.fill = GridBagConstraints.BOTH;
		gbc_cBRutas.gridx = 8;
		gbc_cBRutas.gridy = 5;
		panelAerolinea.add(CBRutas, gbc_cBRutas);

		btnConsultarRutaButton = new JButton("Consultar Ruta");
		GridBagConstraints gbc_btnConsultarRutaButton = new GridBagConstraints();
		gbc_btnConsultarRutaButton.gridwidth = 3;
		gbc_btnConsultarRutaButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultarRutaButton.gridx = 9;
		gbc_btnConsultarRutaButton.gridy = 8;
		btnConsultarRutaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayoutPrincipal.show(panelPrincipal, "ConsultaRuta");
				InicializarConsultaRuta();
				//setTitle("Consulta Ruta de Vuelo");
				/*JButton btnCerrarConsultaRuta = consultaRutaFrame.getCancelarButton();
				btnCerrarConsultaRuta.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cardLayoutPrincipal.show(panelPrincipal, "panelAntiguo");
					}
				});*/
			}
		});
		panelAerolinea.add(btnConsultarRutaButton, gbc_btnConsultarRutaButton);
	}

	private void InicializarPanelMostrarMasDatos() {
		panelMasDatos = new JPanel();
		GridBagConstraints gbc_panelMasDatos = new GridBagConstraints();
		gbc_panelMasDatos.gridwidth = 12;
		gbc_panelMasDatos.gridheight = 7;
		gbc_panelMasDatos.insets = new Insets(0, 0, 5, 0);
		gbc_panelMasDatos.fill = GridBagConstraints.BOTH;
		gbc_panelMasDatos.gridx = 0;
		gbc_panelMasDatos.gridy = 0;
		GridBagLayout gbl_panelMasDatos = new GridBagLayout();
		gbl_panelMasDatos.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelMasDatos.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelMasDatos.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, Double.MIN_VALUE };
		gbl_panelMasDatos.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panelMasDatos.setLayout(gbl_panelMasDatos);

		cardPanel.add(panelMasDatos, "masDatos");
		
		lblVuelosLabel = new JLabel("Vuelos");
		GridBagConstraints gbc_lblVuelosLabel = new GridBagConstraints();
		gbc_lblVuelosLabel.gridwidth = 2;
		gbc_lblVuelosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblVuelosLabel.gridx = 6;
		gbc_lblVuelosLabel.gridy = 1;
		panelMasDatos.add(lblVuelosLabel, gbc_lblVuelosLabel);
		
		comboBoxVuelos = new JComboBox<>();
		GridBagConstraints gbc_comboBoxVuelos = new GridBagConstraints();
		gbc_comboBoxVuelos.gridwidth = 10;
		gbc_comboBoxVuelos.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxVuelos.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVuelos.gridx = 2;
		gbc_comboBoxVuelos.gridy = 2;
		panelMasDatos.add(comboBoxVuelos, gbc_comboBoxVuelos);
		
		lblPaqueteLabel = new JLabel("Paquetes");
		GridBagConstraints gbc_lblPaqueteLabel = new GridBagConstraints();
		gbc_lblPaqueteLabel.gridwidth = 2;
		gbc_lblPaqueteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaqueteLabel.gridx = 6;
		gbc_lblPaqueteLabel.gridy = 4;
		panelMasDatos.add(lblPaqueteLabel, gbc_lblPaqueteLabel);
		
		comboBoxPaquetes = new JComboBox<>();
		GridBagConstraints gbc_comboBoxPaquetes = new GridBagConstraints();
		gbc_comboBoxPaquetes.gridwidth = 10;
		gbc_comboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquetes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquetes.gridx = 2;
		gbc_comboBoxPaquetes.gridy = 5;
		panelMasDatos.add(comboBoxPaquetes, gbc_comboBoxPaquetes);
		
		btnConsultaVueloButton = new JButton("Consultar Vuelo");
		GridBagConstraints gbc_btnConsultaVueloButton = new GridBagConstraints();
		gbc_btnConsultaVueloButton.gridwidth = 2;
		gbc_btnConsultaVueloButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultaVueloButton.gridx = 4;
		gbc_btnConsultaVueloButton.gridy = 8;
		btnConsultaVueloButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutPrincipal.show(panelPrincipal, "ConsultaVuelo");
				InicializarConsultaVuelo();
				//setTitle("Consulta Vuelo");
				/*JButton btnCerrarConsultaVuelo = consultaVueloFrame.getCancelarButton();
				btnCerrarConsultaVuelo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						cardLayoutPrincipal.show(panelAntiguo, "panelAntiguo");
					}
				});*/
			}
		});
		panelMasDatos.add(btnConsultaVueloButton, gbc_btnConsultaVueloButton);
		
		btnConsultaPaqueteButton = new JButton("Consultar Paquete");
		GridBagConstraints gbc_btnConsultaPaqueteButton = new GridBagConstraints();
		gbc_btnConsultaPaqueteButton.gridwidth = 2;
		gbc_btnConsultaPaqueteButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultaPaqueteButton.gridx = 6;
		gbc_btnConsultaPaqueteButton.gridy = 8;
		btnConsultaPaqueteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutPrincipal.show(panelPrincipal, "ConsultaPaquete");
				InicializarConsultaPaquete();
				//setTitle("Consulta Paquete");
			}
		});
		panelMasDatos.add(btnConsultaPaqueteButton, gbc_btnConsultaPaqueteButton);
		
		btnAtrasButton = new JButton("Atras");
		GridBagConstraints gbc_btnAtrasButton = new GridBagConstraints();
		gbc_btnAtrasButton.gridwidth = 2;
		gbc_btnAtrasButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtrasButton.gridx = 8;
		gbc_btnAtrasButton.gridy = 8;
		btnAtrasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "Cliente");
				btnModificarButton.setEnabled(true);
			}
		});
		panelMasDatos.add(btnAtrasButton, gbc_btnAtrasButton);
	}

	@SuppressWarnings("deprecation")
	private void InicializarPanelCliente() {
		panelCliente = new JPanel();
		GridBagConstraints gbc_panelCliente = new GridBagConstraints();
		gbc_panelCliente.gridwidth = 12;
		gbc_panelCliente.gridheight = 7;
		gbc_panelCliente.insets = new Insets(0, 0, 5, 0);
		gbc_panelCliente.fill = GridBagConstraints.BOTH;
		gbc_panelCliente.gridx = 0;
		gbc_panelCliente.gridy = 0;
		GridBagLayout gbl_panelCliente = new GridBagLayout();
		gbl_panelCliente.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCliente.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCliente.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, Double.MIN_VALUE };
		gbl_panelCliente.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panelCliente.setLayout(gbl_panelCliente);

		cardPanel.add(panelCliente, "Cliente");

		lblApellidoLabel = new JLabel("Apellido");
		GridBagConstraints gbc_lblApellidoLabel = new GridBagConstraints();
		gbc_lblApellidoLabel.gridwidth = 2;
		gbc_lblApellidoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidoLabel.gridx = 2;
		gbc_lblApellidoLabel.gridy = 1;
		panelCliente.add(lblApellidoLabel, gbc_lblApellidoLabel);

		textApellido = new JTextField();
		textApellido.setEditable(false);
		GridBagConstraints gbc_textApellido = new GridBagConstraints();
		gbc_textApellido.gridwidth = 8;
		gbc_textApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textApellido.fill = GridBagConstraints.BOTH;
		gbc_textApellido.gridx = 4;
		gbc_textApellido.gridy = 1;
		panelCliente.add(textApellido, gbc_textApellido);
		textApellido.setColumns(10);

		lblFechaNacimientoLabel = new JLabel("Fecha Nacimiento");
		GridBagConstraints gbc_lblFechaNacimientoLabel = new GridBagConstraints();
		gbc_lblFechaNacimientoLabel.gridwidth = 2;
		gbc_lblFechaNacimientoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimientoLabel.gridx = 2;
		gbc_lblFechaNacimientoLabel.gridy = 3;
		panelCliente.add(lblFechaNacimientoLabel, gbc_lblFechaNacimientoLabel);

		CBDia = new JComboBox<>();
		CBDia.setEnabled(false);
		CBDia.setToolTipText("Dia");
		Date fecha = new Date();
		GridBagConstraints gbc_cBDia = new GridBagConstraints();
		gbc_cBDia.gridwidth = 2;
		gbc_cBDia.insets = new Insets(0, 0, 5, 5);
		gbc_cBDia.fill = GridBagConstraints.BOTH;
		gbc_cBDia.gridx = 4;
		gbc_cBDia.gridy = 3;
		panelCliente.add(CBDia, gbc_cBDia);

		CBMes = new JComboBox<>();
		CBMes.setEnabled(false);
		for (String mes : Utilidades.Month) {
			CBMes.addItem(mes);
		}
		CBMes.setToolTipText("Mes");
		CBMes.setSelectedIndex(fecha.getMonth());
		GridBagConstraints gbc_cBMes = new GridBagConstraints();
		gbc_cBMes.gridwidth = 2;
		gbc_cBMes.insets = new Insets(0, 0, 5, 5);
		gbc_cBMes.fill = GridBagConstraints.BOTH;
		gbc_cBMes.gridx = 7;
		gbc_cBMes.gridy = 3;
		CBMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarDias();
			}
		});
		panelCliente.add(CBMes, gbc_cBMes);

		CBAnio = new JComboBox<>();
		CBAnio.setEnabled(false);
		CBAnio.setToolTipText("Año");
		cargarAnio();
		CBAnio.setSelectedIndex(fecha.getYear() - 24);
		GridBagConstraints gbc_cBAnio = new GridBagConstraints();
		gbc_cBAnio.gridwidth = 2;
		gbc_cBAnio.insets = new Insets(0, 0, 5, 5);
		gbc_cBAnio.fill = GridBagConstraints.BOTH;
		gbc_cBAnio.gridx = 10;
		gbc_cBAnio.gridy = 3;
		CBAnio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarDias();
			}
		});
		panelCliente.add(CBAnio, gbc_cBAnio);

		lblNacionalidadLabel = new JLabel("Nacionalidad");
		GridBagConstraints gbc_lblNacionalidadLabel = new GridBagConstraints();
		gbc_lblNacionalidadLabel.gridwidth = 2;
		gbc_lblNacionalidadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNacionalidadLabel.gridx = 2;
		gbc_lblNacionalidadLabel.gridy = 5;
		panelCliente.add(lblNacionalidadLabel, gbc_lblNacionalidadLabel);

		textNacionalidad = new JTextField();
		textNacionalidad.setEditable(false);
		GridBagConstraints gbc_textNacionalidad = new GridBagConstraints();
		gbc_textNacionalidad.gridwidth = 8;
		gbc_textNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_textNacionalidad.fill = GridBagConstraints.BOTH;
		gbc_textNacionalidad.gridx = 4;
		gbc_textNacionalidad.gridy = 5;
		panelCliente.add(textNacionalidad, gbc_textNacionalidad);
		textNacionalidad.setColumns(10);

		lblTipoDocumentoLabel = new JLabel("Tipo documento");
		GridBagConstraints gbc_lblTipoDocumentoLabel = new GridBagConstraints();
		gbc_lblTipoDocumentoLabel.gridwidth = 2;
		gbc_lblTipoDocumentoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDocumentoLabel.gridx = 2;
		gbc_lblTipoDocumentoLabel.gridy = 7;
		panelCliente.add(lblTipoDocumentoLabel, gbc_lblTipoDocumentoLabel);

		CBTipoDoc = new JComboBox<>();
		CBTipoDoc.setEnabled(false);
		CBTipoDoc.setModel(new DefaultComboBoxModel<TipoDocumento>(TipoDocumento.values()));
		GridBagConstraints gbc_cBTipoDoc = new GridBagConstraints();
		gbc_cBTipoDoc.gridwidth = 8;
		gbc_cBTipoDoc.insets = new Insets(0, 0, 5, 5);
		gbc_cBTipoDoc.fill = GridBagConstraints.BOTH;
		gbc_cBTipoDoc.gridx = 4;
		gbc_cBTipoDoc.gridy = 7;
		panelCliente.add(CBTipoDoc, gbc_cBTipoDoc);

		lblNumeroDocumentoLabel = new JLabel("Número documento");
		GridBagConstraints gbc_lblNumeroDocumentoLabel = new GridBagConstraints();
		gbc_lblNumeroDocumentoLabel.gridwidth = 2;
		gbc_lblNumeroDocumentoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNumeroDocumentoLabel.gridx = 2;
		gbc_lblNumeroDocumentoLabel.gridy = 9;
		panelCliente.add(lblNumeroDocumentoLabel, gbc_lblNumeroDocumentoLabel);

		textDoc = new JTextField();
		textDoc.setEditable(false);
		GridBagConstraints gbc_textDoc = new GridBagConstraints();
		gbc_textDoc.gridwidth = 8;
		gbc_textDoc.insets = new Insets(0, 0, 5, 5);
		gbc_textDoc.fill = GridBagConstraints.BOTH;
		gbc_textDoc.gridx = 4;
		gbc_textDoc.gridy = 9;
		panelCliente.add(textDoc, gbc_textDoc);
		textDoc.setColumns(10);

		cargarDias();
		CBDia.setSelectedIndex(fecha.getDate() - 1);

		btnMasDatosClienteButton = new JButton("Mostrar mas datos");
		GridBagConstraints gbc_btnMasDatosClienteButton = new GridBagConstraints();
		gbc_btnMasDatosClienteButton.gridwidth = 2;
		gbc_btnMasDatosClienteButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnMasDatosClienteButton.gridx = 6;
		gbc_btnMasDatosClienteButton.gridy = 11;
		btnMasDatosClienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "masDatos");
				btnModificarButton.setEnabled(false);
			}
		});
		panelCliente.add(btnMasDatosClienteButton, gbc_btnMasDatosClienteButton);

	}

	public JButton getCancelar() {
		return btnSalirButton;
	}

	private void cargarDias() {
		int i = 0;
		if (CBDia.getItemCount() > 0) {
			i = CBDia.getSelectedIndex();
			CBDia.removeAllItems();
		}
		Integer mes = Utilidades.MesStringToInt((String) CBMes.getSelectedItem());
		Integer anio = (Integer) CBAnio.getSelectedIndex();
		for (Integer dia : Utilidades.getDias(mes, anio)) {
			CBDia.addItem(dia);
		}

		if (i < CBDia.getItemCount()) {
			CBDia.setSelectedIndex(i);
		} else {
			CBDia.setSelectedIndex(0);
		}
	}

	private void cargarAnio() {
		for (Integer anio : Utilidades.getAnios()) {
			CBAnio.addItem(anio);
		}
	}

	@SuppressWarnings("deprecation")
	private void mostrarDatos(List<UsuarioDto> usersList, ActionEvent evento) {
		if (CBUsuario.isEnabled() && CBUsuario.getSelectedIndex() != -1) {
			String nick = (String) CBUsuario.getSelectedItem();
			int i = 0;
			UsuarioDto usr = null;
			while (i < usersList.size() && usr == null) {
				if (usersList.get(i).getNickName().equals(nick)) {
					usr = usersList.get(i);
				}
				i++;

			}

			textNick.setText(usr.getNickName());
			textEmail.setText(usr.getEmail());
			textName.setText(usr.getNombre());

			if (usr instanceof ClienteDto) {
				ClienteDto clien = (ClienteDto) usr;
				respaldoUsr = clien;

				cardLayout.show(cardPanel, "Cliente");

				lblApellidoLabel.setVisible(true);
				lblFechaNacimientoLabel.setVisible(true);
				lblNacionalidadLabel.setVisible(true);
				lblTipoDocumentoLabel.setVisible(true);
				lblNumeroDocumentoLabel.setVisible(true);
				textApellido.setVisible(true);
				textNacionalidad.setVisible(true);
				// CBNacionalidad.setVisible(true);

				textDoc.setVisible(true);
				CBAnio.setVisible(true);
				CBMes.setVisible(true);
				CBDia.setVisible(true);
				CBTipoDoc.setVisible(true);

				btnModificarButton.setVisible(true);
				btnModificarButton.setEnabled(true);

				lblDescripcionLabel.setVisible(false);
				lblWebLabel.setVisible(false);
				textArea.setVisible(false);
				textWeb.setVisible(false);
				lblRutasLabel.setVisible(false);
				CBRutas.setVisible(false);

				textApellido.setText(clien.getApellido());
				textNacionalidad.setText(clien.getNacionalidad());
				/*
				 * nacionalidad = clien.getNacionalidad(); setNacionalidadCB();
				 */

				textDoc.setText(clien.getNumeroDocumento());

				Date fechaAux = clien.getFechaNacimiento();

				CBAnio.setSelectedIndex(fechaAux.getYear() - 24);
				CBMes.setSelectedIndex(fechaAux.getMonth());
				CBDia.setSelectedIndex(fechaAux.getDate() - 1);

				TipoDocumento tipoDocAux = clien.getTipoDocumento();
				CBTipoDoc.setSelectedItem(tipoDocAux);
				
				comboBoxVuelos.removeAllItems();
				for (String nombreVuelo : clien.getReservas()) {
					comboBoxVuelos.addItem(nombreVuelo);
				}
				if (comboBoxVuelos.getItemCount() > 0) {
					btnConsultaVueloButton.setEnabled(true);
					comboBoxVuelos.setSelectedIndex(0);
				}else {
					btnConsultaVueloButton.setEnabled(false);
				}
				
				comboBoxPaquetes.removeAllItems();
				for (String nombrePaquete : clien.getComprasPaquete()) {
					comboBoxPaquetes.addItem(nombrePaquete);
				}
				if (comboBoxPaquetes.getItemCount() > 0) {
					btnConsultaPaqueteButton.setEnabled(true);
					comboBoxPaquetes.setSelectedIndex(0);
				}else {
					btnConsultaPaqueteButton.setEnabled(false);
				}

			} else {
				AerolineaDto clienAero = (AerolineaDto) usr;
				respaldoUsr = clienAero;

				cardLayout.show(cardPanel, "Aerolinea");

				lblDescripcionLabel.setVisible(true);
				lblWebLabel.setVisible(true);
				textArea.setVisible(true);
				textWeb.setVisible(true);

				lblRutasLabel.setVisible(true);
				CBRutas.setVisible(true);

				btnModificarButton.setVisible(true);
				btnModificarButton.setEnabled(true);

				lblApellidoLabel.setVisible(false);
				lblFechaNacimientoLabel.setVisible(false);
				lblNacionalidadLabel.setVisible(false);
				lblTipoDocumentoLabel.setVisible(false);
				lblNumeroDocumentoLabel.setVisible(false);
				textApellido.setVisible(false);
				textNacionalidad.setVisible(false);
				// CBNacionalidad.setVisible(false);

				textDoc.setVisible(false);
				CBAnio.setVisible(false);
				CBMes.setVisible(false);
				CBDia.setVisible(false);
				CBTipoDoc.setVisible(false);

				textArea.setText(clienAero.getDescripcion());
				textWeb.setText(clienAero.getWebsite());
				
				rutasDeVueloAux = clienAero.getRutasDeVuelo();
				CBRutas.removeAllItems();
				for (String ruta : rutasDeVueloAux) {
					CBRutas.addItem(ruta);
				}
				if (rutasDeVueloAux.size() > 0) {
					/*try {
						rutalst = controlusr.listarRutas(clienAero.getNickName());
					} catch (Exception e) {
						e.printStackTrace();
					}*/
					CBRutas.setEnabled(true);
					btnConsultarRutaButton.setEnabled(true);
					CBRutas.setSelectedIndex(0);
				} else {
					btnConsultarRutaButton.setEnabled(false);
				}

			}

		}
	}

	private void guardarRespaldo() {
		if (CBUsuario.isEnabled() && CBUsuario.getSelectedIndex() != -1) {
			usersList = controlusr.listarUsuario();
			CBUsuario.removeAllItems();
			for (UsuarioDto usr : usersList) {
				CBUsuario.addItem(usr.getNickName());
			}
			CBUsuario.setSelectedItem(respaldoUsr.getNickName());

			String nick = (String) CBUsuario.getSelectedItem();
			int i = 0;
			UsuarioDto usr = null;
			while (i < usersList.size() && usr == null) {
				if (usersList.get(i).getNickName().equals(nick)) {
					usr = usersList.get(i);
				}
				i++;

			}

			respaldoUsr = usr;

		}

	}

	private boolean checkFormulario() {
		if (!camposComunes()) {
			mostrarMensajeError("No puede haber campos vacíos");
			return false;
		}

		if (!usuarioSeleccionado()) {
			mostrarMensajeError("Debe seleccionar un usuario");
			return false;
		}

		if (respaldoUsr instanceof AerolineaDto && !camposAerolinea()) {
			mostrarMensajeError("Los campos de Aerolinea no pueden ser vacíos");
			return false;
		}

		if (respaldoUsr instanceof ClienteDto && !camposCliente()) {
			mostrarMensajeError("Los campos del Cliente no pueden ser vacíos");
			return false;
		}

		return true;
	}

	private boolean camposComunes() {
		return !textName.getText().isEmpty();
	}

	private boolean usuarioSeleccionado() {
		return CBUsuario.getSelectedIndex() != -1;
	}

	private boolean camposAerolinea() {
		return !textArea.getText().isEmpty();
	}

	private boolean camposCliente() {
		return !textApellido.getText().isEmpty() && !textApellido.getText().isEmpty()
				&& Utilidades.verificarLetras(textNacionalidad.getText());
	}

	private void mostrarMensajeError(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje, "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
	}

	private void confirmarModificar() {
		try {
			if (checkFormulario()) {
				if (respaldoUsr instanceof ClienteDto) {
					@SuppressWarnings("deprecation")
					Date fechaUser = new Date((Integer) CBAnio.getSelectedItem() - 1900,
							Utilidades.MesStringToInt((String) CBMes.getSelectedItem()) - 1,
							(Integer) CBDia.getSelectedItem());
					List<String> reservas = null;
					List<String> comprasPaquete = null;
					ClienteDto auxConfClien = new ClienteDto(respaldoUsr.getNickName(), textName.getText(),
							respaldoUsr.getEmail(), textApellido.getText(), fechaUser,
							(TipoDocumento) CBTipoDoc.getSelectedItem(), textDoc.getText(), textNacionalidad.getText(),
							reservas, comprasPaquete, respaldoUsr.getFechaAlta(), respaldoUsr.getContrasenia(), respaldoUsr.getImagen(), respaldoUsr.getSeguidos(), respaldoUsr.getSeguidores());
					/*
					 * PaisesEnum NacionalidadSelected = (PaisesEnum)
					 * CBNacionalidad.getSelectedItem(); String seleccionadoString =
					 * NacionalidadSelected.toString(); ClienteDto auxConfClien = new
					 * ClienteDto(respaldoUsr.getNickName(), textField_3.getText(),
					 * respaldoUsr.getEmail(),
					 * textapellido.getText(),fechaUser,(TipoDocumento)CBTipodoc.getSelectedItem(),
					 * textdoc.getText(),seleccionadoString,reservas,comprasPaquete);
					 */

					controlusr.actualizarUsuario(auxConfClien);

				} else {
					List<String> rutasDeVuelo = null;
					AerolineaDto newAero = new AerolineaDto(respaldoUsr.getNickName(), textName.getText(),
							respaldoUsr.getEmail(), textArea.getText(), textWeb.getText(), rutasDeVuelo, respaldoUsr.getFechaAlta(), respaldoUsr.getContrasenia(), respaldoUsr.getImagen(), respaldoUsr.getSeguidos(), respaldoUsr.getSeguidores());
					controlusr.actualizarUsuario(newAero);
				}
			}
		} catch (Exception error) {
			mostrarMensajeError(error.getMessage());
		}
	}

	private void InicializarConsultaRuta() {
		consultaRutaFrame = new ConsultaRutadeVuelo(controlusr, controlvuelo);

		// Elimina el borde de arriba de la ventana
		consultaRutaFrame.setBorder(null);
		((BasicInternalFrameUI) consultaRutaFrame.getUI()).setNorthPane(null);

		consultaRutaFrame.setBackground(new Color(240, 240, 240));
		consultaRutaFrame.setSize(800, 600);
		consultaRutaFrame.setVisible(true);
		consultaRutaFrame.getCancelarButton().setText("Atras");
		consultaRutaFrame.getCancelarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutPrincipal.show(panelPrincipal, "panelAntiguo");
				DestruirConsultaRuta();
				//setTitle("Consulta Usuario");
			}
		});

		//Aerolinea
		AerolineaDto aerolineaDto = null;
		for (UsuarioDto usuarioDto : usersList) {
			if (usuarioDto instanceof AerolineaDto && usuarioDto.getNickName().equals((String)CBUsuario.getSelectedItem())) {
				aerolineaDto = (AerolineaDto)usuarioDto;
			}
		}
		
		//Ruta
		String ruta = null;
		for (String nombreRuta : aerolineaDto.getRutasDeVuelo()) {
			if (nombreRuta.equals((String)CBRutas.getSelectedItem())) {
				ruta = nombreRuta;
			}
		}
		
		consultaRutaFrame.precargarCampos(aerolineaDto.getNickName(), ruta);
		desktopPaneRuta.add(consultaRutaFrame);

	}

	// Se llama desde el ConsultaRuta cuando se desea volver a panelAntiguo
	private void DestruirConsultaRuta() {
		consultaRutaFrame.setVisible(false);
		desktopPaneRuta.remove(consultaRutaFrame);
		consultaRutaFrame.dispose();
	}

	private void InicializarConsultaVuelo() {
		consultaVueloFrame = new ConsultaVuelo(controlusr, controlvuelo);

		// Elimina el borde de arriba de la ventana
		consultaVueloFrame.setBorder(null);
		((BasicInternalFrameUI) consultaVueloFrame.getUI()).setNorthPane(null);

		consultaVueloFrame.setBackground(new Color(240, 240, 240));
		consultaVueloFrame.setSize(800, 600);
		consultaVueloFrame.setVisible(true);
		consultaVueloFrame.getCancelarButton().setText("Atras");
		consultaVueloFrame.getCancelarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutPrincipal.show(panelPrincipal, "panelAntiguo");
				//setTitle("Consulta Usuario");
				DestruirConsultaVuelo();
			}
		});
		// Busco la aerolinea y la ruta
		List<AerolineaDto> aerolineasdto = new ArrayList<>();
		for (UsuarioDto usuariodto : usersList) {
			if (usuariodto instanceof AerolineaDto) {
				aerolineasdto.add((AerolineaDto) usuariodto);
			}
		}
		
		String aerolineaSirve = null;
		String rutaSirve = null;
		
		for (AerolineaDto aerolineadto : aerolineasdto) {
			try {
				List<RutaDeVueloDto> rutasDto = controlusr.listarRutas(aerolineadto.getNickName());
				for (RutaDeVueloDto rutaDto : rutasDto) {
					for (String nombreVuelo : rutaDto.getVuelos()) {
						if (nombreVuelo.equals((String)comboBoxVuelos.getSelectedItem())) {
							aerolineaSirve = aerolineadto.getNickName();
							rutaSirve = rutaDto.getNombre();
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		consultaVueloFrame.precargarCampos(aerolineaSirve, rutaSirve, (String) comboBoxVuelos.getSelectedItem());
		desktopPaneVuelo.add(consultaVueloFrame);
	}

	// Se llama desde el ConsultaVuelo cuando se desea volver a panelAntiguo
	private void DestruirConsultaVuelo() {
		consultaVueloFrame.setVisible(false);
		desktopPaneVuelo.remove(consultaVueloFrame);
		consultaVueloFrame.dispose();
	}
	
	private void InicializarConsultaPaquete(){
		consultaPaqueteFrame = new ConsultaPaquete(controlusr, controlvuelo, controlpaquete);

		// Elimina el borde de arriba de la ventana
		consultaPaqueteFrame.setBorder(null);
		((BasicInternalFrameUI) consultaPaqueteFrame.getUI()).setNorthPane(null);

		consultaPaqueteFrame.setBackground(new Color(240, 240, 240));
		consultaPaqueteFrame.setSize(800, 600);
		consultaPaqueteFrame.setVisible(true);
		consultaPaqueteFrame.getCancelarButton().setText("Atras");
		consultaPaqueteFrame.getCancelarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayoutPrincipal.show(panelPrincipal, "panelAntiguo");
				DestruirConsultaPaquete();
				//setTitle("Consulta Usuario");
			}
		});
		// Busco la aerolinea y la ruta
		List<AerolineaDto> aerolineasdto = new ArrayList<>();
		for (UsuarioDto usuariodto : usersList) {
			if (usuariodto instanceof AerolineaDto) {
				aerolineasdto.add((AerolineaDto) usuariodto);
			}
		}
		
		consultaPaqueteFrame.precargarCampos((String)comboBoxPaquetes.getSelectedItem());
		desktopPanePaquete.add(consultaPaqueteFrame);
	}
	
	// Se llama desde el ConsultaPaquete cuando se desea volver a panelAntiguo
		private void DestruirConsultaPaquete() {
			consultaPaqueteFrame.setVisible(false);
			desktopPanePaquete.remove(consultaPaqueteFrame);
			consultaPaqueteFrame.dispose();
		}

}
