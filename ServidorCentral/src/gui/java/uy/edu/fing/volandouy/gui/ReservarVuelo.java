package uy.edu.fing.volandouy.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.PasajeDto;
import uy.edu.fing.volandouy.dto.ReservaDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.dto.VueloDto;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

//Se habilita ingresar los datos si el cliente fue seleccionado
@SuppressWarnings("serial")
public class ReservarVuelo extends JInternalFrame {

	// Controladores
	private IUsuarioController iusuario;
	private IVueloController ivuelo;
	private IPaqueteController ipaquete;
	// InternalFrames
	private ConsultaVuelo consultaVueloFrame;
	private ConsultaPaquete consultaPaqueteFrame;
	// Componentes
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private JButton btnCancelarButton;
	private JButton btnIngresarDatosButton;
	private JButton btnConsultaVueloButton;
	private JButton btnConsultaPaqueteButton;
	private JButton btnRegistrarButton;
	private JTextField textFieldCantidadPasajes;
	private JTextField textFieldUnidadesExtra;
	private JTextField textFieldNombrePasajero;
	private JTextField textFieldApellidoPasajero;
	private JComboBox<TipoAsiento> comboBoxTipoAsiento;
	private JComboBox<String> comboBoxPasajeros;
	private JComboBox<String> comboBoxAerolineas;
	private JComboBox<String> comboBoxRutas;
	private JComboBox<String> comboBoxVuelos;
	private JComboBox<String> comboBoxClientes;
	private JComboBox<String> comboBoxPaquetes;
	private JComboBox<Integer> comboBoxFechaDia;
	private JComboBox<String> comboBoxFechaMes;
	private JComboBox<Integer> comboBoxFechaAnio;
	private JDesktopPane desktopPaneVuelo;
	private JDesktopPane desktopPanePaquete;
	// Datos auxiliares
	private List<ClienteDto> clientesDto = new ArrayList<>();
	private List<AerolineaDto> aerolineasDto = new ArrayList<>();
	private List<PasajeDto> pasajerosInfo = new ArrayList<>();
	private int cantidadPasajerosAntigua = 1;
	private JLabel lblFechaAltaLabel;
	private Date fechaActual = new Date();

	public ReservarVuelo(IUsuarioController iusuarioC, IVueloController ivueloC, IPaqueteController ipaqueteC) {
		// Atributos y seteos previos
		this.iusuario = iusuarioC;
		this.ivuelo = ivueloC;
		this.ipaquete = ipaqueteC;
		List<UsuarioDto> usuariosDto = iusuario.listarUsuario();
		for (UsuarioDto usuario : usuariosDto) {
			if (usuario instanceof AerolineaDto) {
				aerolineasDto.add((AerolineaDto) usuario);
			} else {
				clientesDto.add((ClienteDto) usuario);
			}
		}
		PasajeDto pasajeroInicial = new PasajeDto("", "", "");
		pasajerosInfo.add(pasajeroInicial);

		// Inicializar el internalFrame
		setBounds(100, 100, 840, 760);
		setVisible(false);
		setTitle("Reserva de Vuelo");
		setClosable(true);
		setResizable(true);
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		// Crea el CardLayout
		cardLayout = new CardLayout();
		// Crea el contenedor del CardLayout
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBackground(new Color(240, 240, 240));
		setContentPane(cardPanel);

		// Crea y agrega la primera tarjeta (JPanel con los comboBox de cliente, vuelo,
		// etc)
		inicializarReservas();

		// Crear y agregar la segunda tarjeta (JDesktopPane con JInternalFrame para
		// vuelo)
		inicializarConsultaVueloDesktopPane();

		// Crear y agregar la tercera tarjeta (JDesktopPane con JInternalFrame para
		// paquete)
		desktopPanePaquete = new JDesktopPane();
		cardPanel.add(desktopPanePaquete, "ConsultaPaquete");

		// Crear y agregar la cuarta tarjeta (JPanel con los componentes necesarios para
		// ingresar los datos del vuelo)
		inicializarIngresarDatos();

		// Mostrar la tarjeta inicial
		cardLayout.show(cardPanel, "Menu");

		// Setea los valores por defecto necesarios en todos los componentes al crear la
		// ventana
		inicializarCampos();

	}

	public JButton getCancelarButton() {
		return btnCancelarButton;
	}

	public void inicializarReservas() {
		// Crear y agregar la primera tarjeta (contenido principal)
		JPanel principalContentPanel = new JPanel();
		cardPanel.add(principalContentPanel, "Reserva");

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		principalContentPanel.setLayout(gridBagLayout);

		JLabel lblAerolineasLabel = new JLabel("Aerolíneas");
		GridBagConstraints gbc_lblAerolineasLabel = new GridBagConstraints();
		gbc_lblAerolineasLabel.gridwidth = 2;
		gbc_lblAerolineasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAerolineasLabel.gridx = 5;
		gbc_lblAerolineasLabel.gridy = 1;
		principalContentPanel.add(lblAerolineasLabel, gbc_lblAerolineasLabel);

		comboBoxAerolineas = new JComboBox<>();
		GridBagConstraints gbc_comboBoxAerolineas = new GridBagConstraints();
		gbc_comboBoxAerolineas.gridheight = 2;
		gbc_comboBoxAerolineas.gridwidth = 8;
		gbc_comboBoxAerolineas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAerolineas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxAerolineas.gridx = 2;
		gbc_comboBoxAerolineas.gridy = 2;
		comboBoxAerolineas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxAerolineas.getSelectedIndex() != -1) {
					cargarComboBoxRutas((String) comboBoxAerolineas.getSelectedItem());
				}
			}
		});
		principalContentPanel.add(comboBoxAerolineas, gbc_comboBoxAerolineas);

		JLabel lblRutasLabel = new JLabel("Rutas");
		GridBagConstraints gbc_lblRutasLabel = new GridBagConstraints();
		gbc_lblRutasLabel.gridwidth = 2;
		gbc_lblRutasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutasLabel.gridx = 5;
		gbc_lblRutasLabel.gridy = 5;
		principalContentPanel.add(lblRutasLabel, gbc_lblRutasLabel);

		comboBoxRutas = new JComboBox<>();
		GridBagConstraints gbc_comboBoxRutas = new GridBagConstraints();
		gbc_comboBoxRutas.gridheight = 2;
		gbc_comboBoxRutas.gridwidth = 8;
		gbc_comboBoxRutas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRutas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxRutas.gridx = 2;
		gbc_comboBoxRutas.gridy = 6;
		comboBoxRutas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxAerolineas.getSelectedIndex() != -1 && comboBoxRutas.getSelectedIndex() != -1) {
					cargarComboBoxVuelos((String) comboBoxAerolineas.getSelectedItem(),
							(String) comboBoxRutas.getSelectedItem());
				}
			}
		});
		principalContentPanel.add(comboBoxRutas, gbc_comboBoxRutas);

		JLabel lblVuelosLabel = new JLabel("Vuelos");
		GridBagConstraints gbc_lblVuelosLabel = new GridBagConstraints();
		gbc_lblVuelosLabel.gridwidth = 2;
		gbc_lblVuelosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblVuelosLabel.gridx = 5;
		gbc_lblVuelosLabel.gridy = 9;
		principalContentPanel.add(lblVuelosLabel, gbc_lblVuelosLabel);

		comboBoxVuelos = new JComboBox<>();
		GridBagConstraints gbc_comboBoxVuelos = new GridBagConstraints();
		gbc_comboBoxVuelos.gridheight = 2;
		gbc_comboBoxVuelos.gridwidth = 8;
		gbc_comboBoxVuelos.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxVuelos.fill = GridBagConstraints.BOTH;
		gbc_comboBoxVuelos.gridx = 2;
		gbc_comboBoxVuelos.gridy = 10;
		comboBoxVuelos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxAerolineas.getSelectedIndex() != -1 && comboBoxRutas.getSelectedIndex() != -1
						&& comboBoxVuelos.getSelectedIndex() != -1) {
					btnConsultaVueloButton.setEnabled(true);
					habilitarReserva();
				}
			}
		});
		principalContentPanel.add(comboBoxVuelos, gbc_comboBoxVuelos);

		JLabel lblClientesLabel = new JLabel("Clientes");
		GridBagConstraints gbc_lblClientesLabel = new GridBagConstraints();
		gbc_lblClientesLabel.gridwidth = 2;
		gbc_lblClientesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientesLabel.gridx = 5;
		gbc_lblClientesLabel.gridy = 13;
		principalContentPanel.add(lblClientesLabel, gbc_lblClientesLabel);

		comboBoxClientes = new JComboBox<>();
		GridBagConstraints gbc_comboBoxClientes = new GridBagConstraints();
		gbc_comboBoxClientes.gridheight = 2;
		gbc_comboBoxClientes.gridwidth = 8;
		gbc_comboBoxClientes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxClientes.fill = GridBagConstraints.BOTH;
		gbc_comboBoxClientes.gridx = 2;
		gbc_comboBoxClientes.gridy = 14;
		comboBoxClientes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarComboBoxPaquetes((String) comboBoxClientes.getSelectedItem());
				// Si hay algun error en pasajeros es esto
				inicializarPrimerPasajero();
			}
		});
		principalContentPanel.add(comboBoxClientes, gbc_comboBoxClientes);

		JLabel lblPaquetesLabel = new JLabel("Paquetes");
		GridBagConstraints gbc_lblPaquetesLabel = new GridBagConstraints();
		gbc_lblPaquetesLabel.gridwidth = 2;
		gbc_lblPaquetesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaquetesLabel.gridx = 5;
		gbc_lblPaquetesLabel.gridy = 17;
		principalContentPanel.add(lblPaquetesLabel, gbc_lblPaquetesLabel);

		comboBoxPaquetes = new JComboBox<>();
		GridBagConstraints gbc_comboBoxPaquetes = new GridBagConstraints();
		gbc_comboBoxPaquetes.gridwidth = 8;
		gbc_comboBoxPaquetes.gridheight = 2;
		gbc_comboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquetes.fill = GridBagConstraints.BOTH;
		gbc_comboBoxPaquetes.gridx = 2;
		gbc_comboBoxPaquetes.gridy = 18;
		comboBoxPaquetes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxPaquetes.getSelectedIndex() != -1
						&& ((String) comboBoxPaquetes.getSelectedItem()).equals("")) {
					btnConsultaPaqueteButton.setEnabled(false);
				} else {
					btnConsultaPaqueteButton.setEnabled(true);
				}
			}
		});
		principalContentPanel.add(comboBoxPaquetes, gbc_comboBoxPaquetes);

		btnConsultaVueloButton = new JButton("Consulta Vuelo");
		GridBagConstraints gbc_btnConsultaVueloButton = new GridBagConstraints();
		gbc_btnConsultaVueloButton.gridwidth = 2;
		gbc_btnConsultaVueloButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultaVueloButton.gridx = 4;
		gbc_btnConsultaVueloButton.gridy = 21;
		btnConsultaVueloButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "ConsultaVuelo");
				InicializarConsultaVueloFrame();
				// Verificar
				/*
				 * JButton btnCerrarConsultaVuelo = consultaVueloFrame.getCancelarButton();
				 * btnCerrarConsultaVuelo.addActionListener(new ActionListener() { public void
				 * actionPerformed(ActionEvent arg0) { cardLayout.show(cardPanel, "Reserva"); }
				 * });
				 */
			}
		});
		principalContentPanel.add(btnConsultaVueloButton, gbc_btnConsultaVueloButton);

		btnConsultaPaqueteButton = new JButton("Consulta Paquete");
		GridBagConstraints gbc_btnConsultaPaqueteButton = new GridBagConstraints();
		gbc_btnConsultaPaqueteButton.gridwidth = 2;
		gbc_btnConsultaPaqueteButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultaPaqueteButton.gridx = 6;
		gbc_btnConsultaPaqueteButton.gridy = 21;
		btnConsultaPaqueteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "ConsultaPaquete");
				InicializarConsultaPaqueteFrame();
			}
		});
		principalContentPanel.add(btnConsultaPaqueteButton, gbc_btnConsultaPaqueteButton);

		btnRegistrarButton = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistrarButton = new GridBagConstraints();
		gbc_btnRegistrarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrarButton.gridx = 4;
		gbc_btnRegistrarButton.gridy = 23;
		btnRegistrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reservarVuelo();
			}
		});
		principalContentPanel.add(btnRegistrarButton, gbc_btnRegistrarButton);

		btnIngresarDatosButton = new JButton("Ingresar datos");
		GridBagConstraints gbc_btnIngresarDatosButton = new GridBagConstraints();
		gbc_btnIngresarDatosButton.gridwidth = 2;
		gbc_btnIngresarDatosButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnIngresarDatosButton.gridx = 5;
		gbc_btnIngresarDatosButton.gridy = 23;
		btnIngresarDatosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inicializarPrimerPasajero();
				cardLayout.show(cardPanel, "IngresarDatos");
			}
		});
		principalContentPanel.add(btnIngresarDatosButton, gbc_btnIngresarDatosButton);

		btnCancelarButton = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelarButton = new GridBagConstraints();
		gbc_btnCancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelarButton.gridx = 7;
		gbc_btnCancelarButton.gridy = 23;
		principalContentPanel.add(btnCancelarButton, gbc_btnCancelarButton);
	}

	private void InicializarConsultaPaqueteFrame() {
		consultaPaqueteFrame = new ConsultaPaquete(iusuario, ivuelo, ipaquete);

		// Elimina el borde de arriba de la ventana
		consultaPaqueteFrame.setBorder(null);
		((BasicInternalFrameUI) consultaPaqueteFrame.getUI()).setNorthPane(null);

		consultaPaqueteFrame.setBackground(new Color(240, 240, 240));
		consultaPaqueteFrame.setSize(800, 600);
		consultaPaqueteFrame.setVisible(true);
		consultaPaqueteFrame.getCancelarButton().setText("Atras");
		consultaPaqueteFrame.getCancelarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "Reserva");
				DestruirConsultaPaquete();
			}
		});
		consultaPaqueteFrame.precargarCampos((String) comboBoxPaquetes.getSelectedItem());
		desktopPanePaquete.add(consultaPaqueteFrame);
	}

	// Se llama desde el ConsultaPaquete cuando se desea volver a Reservar
	private void DestruirConsultaPaquete() {
		consultaPaqueteFrame.setVisible(false);
		desktopPanePaquete.remove(consultaPaqueteFrame);
		consultaPaqueteFrame.dispose();
	}

	// Se llama desde el boton Consulta Vuelo
	private void InicializarConsultaVueloFrame() {
		consultaVueloFrame = new ConsultaVuelo(iusuario, ivuelo);

		// Elimina el borde de arriba de la ventana
		consultaVueloFrame.setBorder(null);
		((BasicInternalFrameUI) consultaVueloFrame.getUI()).setNorthPane(null);

		consultaVueloFrame.setBackground(new Color(240, 240, 240));
		consultaVueloFrame.setSize(800, 600);
		consultaVueloFrame.setVisible(true);
		consultaVueloFrame.getCancelarButton().setText("Atras");
		consultaVueloFrame.getCancelarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "Reserva");
				DestruirConsultaVuelo();
			}
		});
		consultaVueloFrame.precargarCampos((String) comboBoxAerolineas.getSelectedItem(),
				(String) comboBoxRutas.getSelectedItem(), (String) comboBoxVuelos.getSelectedItem());
		desktopPaneVuelo.add(consultaVueloFrame);
	}

	// Se llama desde el ConsultaVuelo cuando se desea volver a Reservar
	private void DestruirConsultaVuelo() {
		consultaVueloFrame.setVisible(false);
		desktopPaneVuelo.remove(consultaVueloFrame);
		consultaVueloFrame.dispose();
	}

	// Se llama desde el constructor del frame
	private void inicializarConsultaVueloDesktopPane() {
		desktopPaneVuelo = new JDesktopPane();
		desktopPaneVuelo.setBackground(new Color(240, 240, 240));
		cardPanel.add(desktopPaneVuelo, "ConsultaVuelo");
	}

	private void inicializarIngresarDatos() {
		JPanel ingresarDatosPanel = new JPanel();
		GridBagLayout gridBagLayoutDatosPanel = new GridBagLayout();
		gridBagLayoutDatosPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayoutDatosPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0 };
		gridBagLayoutDatosPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, Double.MIN_VALUE };
		gridBagLayoutDatosPanel.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		ingresarDatosPanel.setLayout(gridBagLayoutDatosPanel);
		cardPanel.add(ingresarDatosPanel, "IngresarDatos");

		JLabel lblTipoAsientoLabel = new JLabel("Tipo asiento");
		GridBagConstraints gbc_lblTipoAsientoLabel = new GridBagConstraints();
		gbc_lblTipoAsientoLabel.gridwidth = 2;
		gbc_lblTipoAsientoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoAsientoLabel.gridx = 5;
		gbc_lblTipoAsientoLabel.gridy = 2;
		ingresarDatosPanel.add(lblTipoAsientoLabel, gbc_lblTipoAsientoLabel);

		comboBoxTipoAsiento = new JComboBox<>();
		comboBoxTipoAsiento.setModel(new DefaultComboBoxModel<TipoAsiento>(TipoAsiento.values()));
		comboBoxTipoAsiento.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxTipoAsiento = new GridBagConstraints();
		gbc_comboBoxTipoAsiento.gridheight = 2;
		gbc_comboBoxTipoAsiento.gridwidth = 8;
		gbc_comboBoxTipoAsiento.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipoAsiento.fill = GridBagConstraints.BOTH;
		gbc_comboBoxTipoAsiento.gridx = 2;
		gbc_comboBoxTipoAsiento.gridy = 3;
		ingresarDatosPanel.add(comboBoxTipoAsiento, gbc_comboBoxTipoAsiento);

		JLabel lblCantidadPasajesLabel = new JLabel("Cantidad de Pasajes");
		GridBagConstraints gbc_lblCantidadPasajesLabel = new GridBagConstraints();
		gbc_lblCantidadPasajesLabel.gridwidth = 2;
		gbc_lblCantidadPasajesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadPasajesLabel.gridx = 1;
		gbc_lblCantidadPasajesLabel.gridy = 7;
		ingresarDatosPanel.add(lblCantidadPasajesLabel, gbc_lblCantidadPasajesLabel);

		textFieldCantidadPasajes = new JTextField();
		GridBagConstraints gbc_textFieldCantidadPasajes = new GridBagConstraints();
		gbc_textFieldCantidadPasajes.gridwidth = 2;
		gbc_textFieldCantidadPasajes.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCantidadPasajes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidadPasajes.gridx = 3;
		gbc_textFieldCantidadPasajes.gridy = 7;
		textFieldCantidadPasajes.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldCantidadPasajes.getText().matches("\\d+")
						&& !textFieldCantidadPasajes.getText().matches("0+")) {
					int cantidadPasajeros = Integer.parseInt(textFieldCantidadPasajes.getText());
					if (cantidadPasajeros > cantidadPasajerosAntigua) {
						for (int i = cantidadPasajerosAntigua; i < cantidadPasajeros; i++) {
							int numeroPasajero = i + 1;
							comboBoxPasajeros.addItem("Pasajero " + numeroPasajero);
							PasajeDto pasajeroNuevo = new PasajeDto("", "", "");
							pasajerosInfo.add(pasajeroNuevo);
						}
					} else {
						comboBoxPasajeros.setSelectedIndex(0);
						textFieldNombrePasajero.setText(pasajerosInfo.get(0).getNombre());
						textFieldApellidoPasajero.setText(pasajerosInfo.get(0).getApellido());
						for (int i = cantidadPasajerosAntigua - 1; i + 1 > cantidadPasajeros; i--) {
							comboBoxPasajeros.removeItemAt(i);
							pasajerosInfo.remove(i);
						}
					}
					cantidadPasajerosAntigua = cantidadPasajeros;
				} else {
					textFieldCantidadPasajes.setText(Integer.toString(cantidadPasajerosAntigua));
				}

				if (textFieldCantidadPasajes.getText().matches("^0+\\d+")) {
					textFieldCantidadPasajes
							.setText(Utilidades.eliminarCerosPrincipio(textFieldCantidadPasajes.getText()));
				}

				if (cantidadPasajerosAntigua == 1) {
					comboBoxPasajeros.setEnabled(false);
					textFieldNombrePasajero.setEnabled(false);
					textFieldApellidoPasajero.setEnabled(false);
				} else {
					comboBoxPasajeros.setEnabled(true);
					// textFieldNombrePasajero.setEnabled(true);
					// textFieldApellidoPasajero.setEnabled(true);
				}
			}
		});

		ingresarDatosPanel.add(textFieldCantidadPasajes, gbc_textFieldCantidadPasajes);
		textFieldCantidadPasajes.setColumns(10);

		textFieldUnidadesExtra = new JTextField();
		GridBagConstraints gbc_textFieldUnidadesExtra = new GridBagConstraints();
		gbc_textFieldUnidadesExtra.gridwidth = 2;
		gbc_textFieldUnidadesExtra.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldUnidadesExtra.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldUnidadesExtra.gridx = 7;
		gbc_textFieldUnidadesExtra.gridy = 7;
		textFieldUnidadesExtra.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!textFieldUnidadesExtra.getText().matches("\\d+")
						|| textFieldUnidadesExtra.getText().matches("0+")) {
					textFieldUnidadesExtra.setText("0");
				}

				if (textFieldUnidadesExtra.getText().matches("0+\\d+")) {
					String texto = textFieldUnidadesExtra.getText();
					textFieldUnidadesExtra.setText(Utilidades.eliminarCerosPrincipio(texto));
				}
			}
		});
		ingresarDatosPanel.add(textFieldUnidadesExtra, gbc_textFieldUnidadesExtra);
		textFieldUnidadesExtra.setColumns(10);

		JLabel lblUnidadesExtraLabel = new JLabel("Unidades de equipaje extra");
		GridBagConstraints gbc_lblUnidadesExtraLabel = new GridBagConstraints();
		gbc_lblUnidadesExtraLabel.gridwidth = 2;
		gbc_lblUnidadesExtraLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblUnidadesExtraLabel.gridx = 9;
		gbc_lblUnidadesExtraLabel.gridy = 7;
		ingresarDatosPanel.add(lblUnidadesExtraLabel, gbc_lblUnidadesExtraLabel);

		JLabel lblPasajerosLabel = new JLabel("Pasajeros");
		GridBagConstraints gbc_lblPasajerosLabel = new GridBagConstraints();
		gbc_lblPasajerosLabel.gridwidth = 2;
		gbc_lblPasajerosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasajerosLabel.gridx = 5;
		gbc_lblPasajerosLabel.gridy = 10;
		ingresarDatosPanel.add(lblPasajerosLabel, gbc_lblPasajerosLabel);

		comboBoxPasajeros = new JComboBox<>();
		GridBagConstraints gbc_comboBoxPasajeros = new GridBagConstraints();
		gbc_comboBoxPasajeros.gridheight = 2;
		gbc_comboBoxPasajeros.gridwidth = 8;
		gbc_comboBoxPasajeros.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPasajeros.fill = GridBagConstraints.BOTH;
		gbc_comboBoxPasajeros.gridx = 2;
		gbc_comboBoxPasajeros.gridy = 11;
		comboBoxPasajeros.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				seleccionarPasajero();
			}
		});
		ingresarDatosPanel.add(comboBoxPasajeros, gbc_comboBoxPasajeros);

		JLabel lblNombrePasajeroLabel = new JLabel("Nombre del pasajero");
		GridBagConstraints gbc_lblNombrePasajeroLabel = new GridBagConstraints();
		gbc_lblNombrePasajeroLabel.gridwidth = 2;
		gbc_lblNombrePasajeroLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombrePasajeroLabel.gridx = 1;
		gbc_lblNombrePasajeroLabel.gridy = 15;
		ingresarDatosPanel.add(lblNombrePasajeroLabel, gbc_lblNombrePasajeroLabel);

		textFieldNombrePasajero = new JTextField();
		GridBagConstraints gbc_textFieldNombrePasajero = new GridBagConstraints();
		gbc_textFieldNombrePasajero.gridwidth = 2;
		gbc_textFieldNombrePasajero.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombrePasajero.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombrePasajero.gridx = 3;
		gbc_textFieldNombrePasajero.gridy = 15;
		textFieldNombrePasajero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int i = comboBoxPasajeros.getSelectedIndex();
				pasajerosInfo.get(i).setNombre(textFieldNombrePasajero.getText());
			}
		});
		ingresarDatosPanel.add(textFieldNombrePasajero, gbc_textFieldNombrePasajero);
		textFieldNombrePasajero.setColumns(10);

		textFieldApellidoPasajero = new JTextField();
		GridBagConstraints gbc_textFieldApellidoPasajero = new GridBagConstraints();
		gbc_textFieldApellidoPasajero.gridwidth = 2;
		gbc_textFieldApellidoPasajero.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellidoPasajero.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellidoPasajero.gridx = 7;
		gbc_textFieldApellidoPasajero.gridy = 15;
		textFieldApellidoPasajero.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				int i = comboBoxPasajeros.getSelectedIndex();
				pasajerosInfo.get(i).setApellido(textFieldApellidoPasajero.getText());
			}
		});
		ingresarDatosPanel.add(textFieldApellidoPasajero, gbc_textFieldApellidoPasajero);
		textFieldApellidoPasajero.setColumns(10);

		JLabel lblApellidoPasajeroLabel = new JLabel("Apellido del pasajero");
		GridBagConstraints gbc_lblApellidoPasajeroLabel = new GridBagConstraints();
		gbc_lblApellidoPasajeroLabel.gridwidth = 2;
		gbc_lblApellidoPasajeroLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellidoPasajeroLabel.gridx = 9;
		gbc_lblApellidoPasajeroLabel.gridy = 15;
		ingresarDatosPanel.add(lblApellidoPasajeroLabel, gbc_lblApellidoPasajeroLabel);

		JButton btnAtrasButton = new JButton("Hecho");
		GridBagConstraints gbc_btnAtrasButton = new GridBagConstraints();
		gbc_btnAtrasButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAtrasButton.gridwidth = 2;
		gbc_btnAtrasButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnAtrasButton.gridx = 5;
		gbc_btnAtrasButton.gridy = 23;
		btnAtrasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "Reserva");
			}
		});

		lblFechaAltaLabel = new JLabel("Fecha Reserva");
		GridBagConstraints gbc_lblFechaAltaLabel = new GridBagConstraints();
		gbc_lblFechaAltaLabel.gridwidth = 2;
		gbc_lblFechaAltaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaLabel.gridx = 1;
		gbc_lblFechaAltaLabel.gridy = 18;
		ingresarDatosPanel.add(lblFechaAltaLabel, gbc_lblFechaAltaLabel);

		comboBoxFechaDia = new JComboBox<>();
		GridBagConstraints gbc_comboBoxFechaDia = new GridBagConstraints();
		gbc_comboBoxFechaDia.gridwidth = 2;
		gbc_comboBoxFechaDia.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaDia.gridx = 3;
		gbc_comboBoxFechaDia.gridy = 18;
		ingresarDatosPanel.add(comboBoxFechaDia, gbc_comboBoxFechaDia);

		comboBoxFechaMes = new JComboBox<>();
		for (String mes : Utilidades.Month) {
			comboBoxFechaMes.addItem(mes);
		}
		comboBoxFechaMes.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxFechaMes = new GridBagConstraints();
		gbc_comboBoxFechaMes.gridwidth = 2;
		gbc_comboBoxFechaMes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaMes.gridx = 5;
		gbc_comboBoxFechaMes.gridy = 18;
		comboBoxFechaMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox("FechaDias");
			}
		});
		ingresarDatosPanel.add(comboBoxFechaMes, gbc_comboBoxFechaMes);

		comboBoxFechaAnio = new JComboBox<>();
		for (Integer anio : Utilidades.getAnios()) {
			comboBoxFechaAnio.addItem(anio);
		}
		comboBoxFechaAnio.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxFechaAnio = new GridBagConstraints();
		gbc_comboBoxFechaAnio.gridwidth = 2;
		gbc_comboBoxFechaAnio.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAnio.gridx = 7;
		gbc_comboBoxFechaAnio.gridy = 18;
		comboBoxFechaAnio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox("FechaDias");
			}
		});
		ingresarDatosPanel.add(comboBoxFechaAnio, gbc_comboBoxFechaAnio);
		ingresarDatosPanel.add(btnAtrasButton, gbc_btnAtrasButton);

		setearFechaActual();

	}

	@SuppressWarnings("deprecation")
	private void setearFechaActual() {
		comboBoxFechaMes.setSelectedIndex(fechaActual.getMonth());
		comboBoxFechaAnio.setSelectedIndex(fechaActual.getYear() - 24);
		agregarDiasComboBox("FechaDias");
		comboBoxFechaDia.setSelectedIndex(fechaActual.getDate() - 1);

	}

	private void agregarDiasComboBox(String nombre) {
		int i = 0;
		if (comboBoxFechaDia.getItemCount() > 0) {
			i = comboBoxFechaDia.getSelectedIndex();
			comboBoxFechaDia.removeAllItems();
		}
		Integer mes = Utilidades.MesStringToInt((String) comboBoxFechaMes.getSelectedItem());
		Integer anio = (Integer) comboBoxFechaAnio.getSelectedIndex();
		for (Integer dia : Utilidades.getDias(mes, anio)) {
			comboBoxFechaDia.addItem(dia);
		}

		if (i < comboBoxFechaDia.getItemCount()) {
			comboBoxFechaDia.setSelectedIndex(i);
		} else {
			comboBoxFechaDia.setSelectedIndex(0);
		}

	}

	private void inicializarCampos() {
		// Info reserva y pasajeros
		textFieldUnidadesExtra.setText("0");

		textFieldCantidadPasajes.setText("1");

		comboBoxPasajeros.setEnabled(false);
		comboBoxPasajeros.addItem("Pasajero 1");
		comboBoxPasajeros.setSelectedIndex(0);

		for (AerolineaDto aerolineaDto : aerolineasDto) {
			comboBoxAerolineas.addItem(aerolineaDto.getNickName());
		}
		if (comboBoxAerolineas.getItemCount() > 0) {
			comboBoxAerolineas.setSelectedIndex(0);
			cargarComboBoxRutas((String) comboBoxAerolineas.getSelectedItem());
		} else {
			btnIngresarDatosButton.setEnabled(false);
			btnConsultaVueloButton.setEnabled(false);
			btnRegistrarButton.setEnabled(false);
		}

		for (ClienteDto cliente : clientesDto) {
			comboBoxClientes.addItem(cliente.getNickName());
		}
		if (comboBoxClientes.getItemCount() > 0) {
			comboBoxClientes.setSelectedIndex(0);
			cargarComboBoxPaquetes((String) comboBoxClientes.getSelectedItem());
			habilitarReserva();
		} else {
			btnConsultaPaqueteButton.setEnabled(false);
		}

	}

	private void habilitarReserva() {
		if (comboBoxAerolineas.getSelectedIndex() != -1 && comboBoxRutas.getSelectedIndex() != -1
				&& comboBoxVuelos.getSelectedIndex() != -1 && comboBoxClientes.getSelectedIndex() != -1) {
			btnIngresarDatosButton.setEnabled(true);
			btnRegistrarButton.setEnabled(true);
		}
	}

	// Nunca va a ser null porque el boton para pasar a los datos se habilita una
	// vez seleccionado lo basico (todo menos paquete)
	// Se llama cada vez que se cambia al cliente seleccionado ya que la ventana de
	// ingresarDatos solo esta abierta si el que interactua con la GUI esta ahi
	private void inicializarPrimerPasajero() {
		ClienteDto clienteReserva = null;
		for (ClienteDto cliente : clientesDto) {
			if (cliente.getNickName().equals((String) comboBoxClientes.getSelectedItem())) {
				clienteReserva = cliente;
			}
		}

		textFieldNombrePasajero.setText(clienteReserva.getNombre());
		textFieldApellidoPasajero.setText(clienteReserva.getApellido());
		textFieldNombrePasajero.setEnabled(false);
		textFieldApellidoPasajero.setEnabled(false);
		pasajerosInfo.get(0).setNombre(clienteReserva.getNombre());
		pasajerosInfo.get(0).setApellido(clienteReserva.getApellido());
		comboBoxPasajeros.setSelectedIndex(0);
	}

	private void seleccionarPasajero() {
		int i = comboBoxPasajeros.getSelectedIndex();
		textFieldNombrePasajero.setText(pasajerosInfo.get(i).getNombre());
		textFieldApellidoPasajero.setText(pasajerosInfo.get(i).getApellido());
		if (i == 0) {
			textFieldNombrePasajero.setEnabled(false);
			textFieldApellidoPasajero.setEnabled(false);
		} else {
			textFieldNombrePasajero.setEnabled(true);
			textFieldApellidoPasajero.setEnabled(true);
		}
	}

	private void cargarComboBoxRutas(String aerolinea) {
		comboBoxRutas.removeAllItems();
		AerolineaDto aerolineadto = null;

		for (AerolineaDto usuario : aerolineasDto) {
			if (usuario.getNickName().equals((String) comboBoxAerolineas.getSelectedItem())) {
				aerolineadto = usuario;
			}
		}

		for (String rutaNombre : aerolineadto.getRutasDeVuelo()) {
			comboBoxRutas.addItem(rutaNombre);
		}

		if (comboBoxRutas.getItemCount() > 0) {
			comboBoxRutas.setSelectedIndex(0);
			cargarComboBoxVuelos(aerolinea, (String) comboBoxRutas.getSelectedItem());
		} else {
			comboBoxVuelos.removeAllItems();
			btnRegistrarButton.setEnabled(false);
			btnConsultaVueloButton.setEnabled(false);
			btnIngresarDatosButton.setEnabled(false);
		}

	}

	private void cargarComboBoxVuelos(String aerolinea, String ruta) {
		try {
			comboBoxVuelos.removeAllItems();
			List<VueloDto> vuelosDto = ivuelo.listarVuelo(aerolinea, ruta);
			for (VueloDto vuelo : vuelosDto) {
				comboBoxVuelos.addItem(vuelo.getNombre());
			}

			if (comboBoxVuelos.getItemCount() > 0) {
				comboBoxVuelos.setSelectedIndex(0);
				btnConsultaVueloButton.setEnabled(true);
				habilitarReserva();
			} else {
				btnRegistrarButton.setEnabled(false);
				btnConsultaVueloButton.setEnabled(false);
				btnIngresarDatosButton.setEnabled(false);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	private void cargarComboBoxPaquetes(String clienteNombre) {
		ClienteDto cliente = null;
		for (ClienteDto clienteList : clientesDto) {
			if (clienteList.getNickName().equals(clienteNombre)) {
				cliente = clienteList;
			}
		}

		comboBoxPaquetes.removeAllItems();
		comboBoxPaquetes.addItem("");
		comboBoxPaquetes.setSelectedIndex(0);
		btnConsultaPaqueteButton.setEnabled(false);
		for (String paqueteNombre : cliente.getComprasPaquete()) {
			comboBoxPaquetes.addItem(paqueteNombre);
		}
		/*
		 * if (comboBoxPaquetes.getItemCount() > 0) {
		 * comboBoxPaquetes.setSelectedIndex(0);
		 * btnConsultaPaqueteButton.setEnabled(true); } else {
		 * btnConsultaPaqueteButton.setEnabled(false); }
		 */

	}

	// Valida todos los campos que se utilizan para realizar la reserva
	private boolean validarDatosReserva() {
		if (comboBoxAerolineas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ninguna aerolínea", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (comboBoxRutas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ninguna ruta", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (comboBoxVuelos.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ningún vuelo", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (comboBoxClientes.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ningún cliente", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;

	}

	private void limpiarInfoReserva() {
		comboBoxTipoAsiento.setSelectedIndex(0);
		textFieldCantidadPasajes.setText("1");
		textFieldUnidadesExtra.setText("0");
		comboBoxPasajeros.setSelectedIndex(0);
		textFieldNombrePasajero.setText(pasajerosInfo.get(0).getNombre());
		textFieldApellidoPasajero.setText(pasajerosInfo.get(0).getApellido());
		textFieldNombrePasajero.setEnabled(false);
		textFieldApellidoPasajero.setEnabled(false);
		for (int i = 1; i < pasajerosInfo.size(); i++) {
			pasajerosInfo.remove(i);
		}
	}

	@SuppressWarnings("deprecation")
	private void reservarVuelo() {
		if (validarDatosReserva()) {

			try {
				Integer dia = (Integer) comboBoxFechaDia.getSelectedItem();
				Integer mes = Utilidades.MesStringToInt((String) comboBoxFechaMes.getSelectedItem()) - 1;
				Integer anio = ((Integer) comboBoxFechaAnio.getSelectedItem()) - 1900;
				Date fechaReserva = new Date(anio, mes, dia);

				/* Cambios en la hora, minutos y segundos de la fecha de reserva */
				//Date fechaActualReserva = new Date();
				
				/* Si la reserva no se realiza en la fecha actual entonces se asume que se hace en un horario valido */
				/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String fechaReservaString = dateFormat.format(fechaReserva);
				String fechaActualReservaString = dateFormat.format(fechaActualReserva);
				if (fechaReservaString.equals(fechaActualReservaString)) {
					fechaReserva.setHours(fechaActualReserva.getHours());
					fechaReserva.setMinutes(fechaActualReserva.getMinutes());
					fechaReserva.setSeconds(fechaActualReserva.getSeconds());
				}*/
				Utilidades.actualizarFechaAlta(fechaReserva);
				
				TipoAsiento tipoAsiento = (TipoAsiento) comboBoxTipoAsiento.getSelectedItem();

				int uniEquipajeExtra = Integer.parseInt(textFieldUnidadesExtra.getText());

				float costo = 0;

				String cliente = (String) comboBoxClientes.getSelectedItem();

				String aerolinea = (String) comboBoxAerolineas.getSelectedItem();

				String ruta = (String) comboBoxRutas.getSelectedItem();

				String vuelo = (String) comboBoxVuelos.getSelectedItem();

				String paquete;
				if (comboBoxPaquetes.getSelectedIndex() == -1
						|| ((String) comboBoxPaquetes.getSelectedItem()).equals("")) {
					paquete = null;
				} else {
					paquete = (String) comboBoxPaquetes.getSelectedItem();
				}

				ReservaDto reservaDto = new ReservaDto(fechaReserva, tipoAsiento, uniEquipajeExtra, costo,
						pasajerosInfo, cliente, aerolinea, ruta, vuelo, paquete, null, null, null);

				ivuelo.reservarVuelo(reservaDto);

				JOptionPane.showMessageDialog(this, "La reserva fue registrada con exito en el sistema", "Exito",
						JOptionPane.INFORMATION_MESSAGE);

				limpiarInfoReserva();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

}
