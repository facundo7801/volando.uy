package uy.edu.fing.volandouy.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.enumerados.TipoDocumento;

@SuppressWarnings("serial")
public class AltaUsuario extends JInternalFrame {
	// Controllers
	private IUsuarioController controlUsr;
	// CardLayout
	private CardLayout cardLayout;
	private JPanel cardPanel;
	private JPanel panelVacio;
	private JPanel panelUsuario;
	private JPanel panelAerolinea;
	private JPanel panelCliente;

	// Componentes PanelUsuario
	private JRadioButton rdbtnAerolinea;
	private JRadioButton rdbtnCliente;
	private JTextField textNick;
	private JTextField textEmail;
	private JTextField textName;
	private JPasswordField textFieldContrasenia;
	private JPasswordField textFieldConfirmarContrasenia;
	private JLabel lblContraseniaLabel;
	private JLabel lblConfirmarContraseniaLabel;
	private JLabel lblFechaAltaLabel;
	private JComboBox<Integer> comboBoxFechaAltaDia;
	private JComboBox<String> comboBoxFechaAltaMes;
	private JComboBox<Integer> comboBoxFechaAltaAnio;
	private JButton btnSalirButton;
	private JButton btnRegistrarButton;

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

	// Variables auxiliares
	private Date fecha;

	public AltaUsuario(IUsuarioController iusuario) {
		this.controlUsr = iusuario;
		fecha = new Date();

		setClosable(true);
		setResizable(true);
		setTitle("Alta Usuario");
		setBounds(100, 100, 840, 760);
		setVisible(false);
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		InicializarPanelUsuario();

		// Crea el contenedor del CardLayout
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBackground(new Color(240, 240, 240));
		GridBagConstraints gbc_cardPanel = new GridBagConstraints();
		gbc_cardPanel.gridwidth = 12;
		gbc_cardPanel.gridheight = 16;
		gbc_cardPanel.insets = new Insets(0, 0, 5, 0);
		gbc_cardPanel.fill = GridBagConstraints.BOTH;
		gbc_cardPanel.gridx = 0;
		gbc_cardPanel.gridy = 7;
		getContentPane().add(cardPanel, gbc_cardPanel);

		// Crea el panel vacio que va a ser el incial del cardLayout
		panelVacio = new JPanel();
		cardPanel.add(panelVacio, "Vacio");

		InicializarPanelAerolinea();

		InicializarPanelCliente();

		// Botones de Salir y Registrar
		btnRegistrarButton = new JButton("Registrar");
		GridBagConstraints gbc_btnRegistrarButton = new GridBagConstraints();
		gbc_btnRegistrarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRegistrarButton.gridwidth = 2;
		gbc_btnRegistrarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegistrarButton.gridx = 3;
		gbc_btnRegistrarButton.gridy = 23;
		btnRegistrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaUsuarioActionPerformed(e);
			}
		});
		getContentPane().add(btnRegistrarButton, gbc_btnRegistrarButton);

		btnSalirButton = new JButton("Salir");
		GridBagConstraints gbc_btnSalirButton = new GridBagConstraints();
		gbc_btnSalirButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSalirButton.gridwidth = 2;
		gbc_btnSalirButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalirButton.gridx = 7;
		gbc_btnSalirButton.gridy = 23;
		getContentPane().add(btnSalirButton, gbc_btnSalirButton);

		cardLayout.show(cardPanel, "Vacio");

	}

	@SuppressWarnings("deprecation")
	private void InicializarPanelUsuario() {
		panelUsuario = new JPanel();
		GridBagConstraints gbc_panelUsuario = new GridBagConstraints();
		gbc_panelUsuario.gridwidth = 12;
		gbc_panelUsuario.gridheight = 7;
		gbc_panelUsuario.insets = new Insets(0, 0, 5, 0);
		gbc_panelUsuario.fill = GridBagConstraints.BOTH;
		gbc_panelUsuario.gridx = 0;
		gbc_panelUsuario.gridy = 0;
		getContentPane().add(panelUsuario, gbc_panelUsuario);
		GridBagLayout gbl_panelUsuario = new GridBagLayout();
		gbl_panelUsuario.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelUsuario.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelUsuario.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_panelUsuario.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelUsuario.setLayout(gbl_panelUsuario);

		JLabel lblTipoUsuarioLabel = new JLabel("Tipo Usuario");
		GridBagConstraints gbc_lblTipoUsuarioLabel = new GridBagConstraints();
		gbc_lblTipoUsuarioLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoUsuarioLabel.gridx = 2;
		gbc_lblTipoUsuarioLabel.gridy = 0;
		panelUsuario.add(lblTipoUsuarioLabel, gbc_lblTipoUsuarioLabel);

		rdbtnCliente = new JRadioButton("Cliente");
		GridBagConstraints gbc_rdbtnCliente = new GridBagConstraints();
		gbc_rdbtnCliente.gridwidth = 2;
		gbc_rdbtnCliente.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnCliente.gridx = 3;
		gbc_rdbtnCliente.gridy = 0;
		panelUsuario.add(rdbtnCliente, gbc_rdbtnCliente);

		// Seleccion tipo de usuario, se selecciona solo uno
		rdbtnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnCliente.setSelected(true);
				rdbtnAerolinea.setSelected(false);
				cardLayout.show(cardPanel, "Cliente");
				limpiarFormularioAerolinea();
				btnRegistrarButton.setVisible(true);

			}
		});

		rdbtnAerolinea = new JRadioButton("Aerolinea");
		GridBagConstraints gbc_rdbtnAerolinea = new GridBagConstraints();
		gbc_rdbtnAerolinea.gridwidth = 2;
		gbc_rdbtnAerolinea.insets = new Insets(0, 0, 5, 5);
		gbc_rdbtnAerolinea.gridx = 6;
		gbc_rdbtnAerolinea.gridy = 0;
		panelUsuario.add(rdbtnAerolinea, gbc_rdbtnAerolinea);

		rdbtnAerolinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnAerolinea.setSelected(true);
				rdbtnCliente.setSelected(false);
				cardLayout.show(cardPanel, "Aerolinea");
				limpiarFormularioCliente();
				btnRegistrarButton.setVisible(true);

			}
		});

		JLabel lblNickLabel = new JLabel("Nickname");
		GridBagConstraints gbc_lblNickLabel = new GridBagConstraints();
		gbc_lblNickLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickLabel.gridx = 2;
		gbc_lblNickLabel.gridy = 1;
		panelUsuario.add(lblNickLabel, gbc_lblNickLabel);

		textNick = new JTextField();
		GridBagConstraints gbc_textNick = new GridBagConstraints();
		gbc_textNick.gridwidth = 5;
		gbc_textNick.insets = new Insets(0, 0, 5, 5);
		gbc_textNick.fill = GridBagConstraints.BOTH;
		gbc_textNick.gridx = 3;
		gbc_textNick.gridy = 1;
		panelUsuario.add(textNick, gbc_textNick);
		textNick.setColumns(10);

		JLabel lblEmailLabel = new JLabel("Email");
		GridBagConstraints gbc_lblEmailLabel = new GridBagConstraints();
		gbc_lblEmailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmailLabel.gridx = 2;
		gbc_lblEmailLabel.gridy = 2;
		panelUsuario.add(lblEmailLabel, gbc_lblEmailLabel);

		textEmail = new JTextField();
		textEmail.setColumns(10);
		GridBagConstraints gbc_textEmail = new GridBagConstraints();
		gbc_textEmail.gridwidth = 5;
		gbc_textEmail.insets = new Insets(0, 0, 5, 5);
		gbc_textEmail.fill = GridBagConstraints.BOTH;
		gbc_textEmail.gridx = 3;
		gbc_textEmail.gridy = 2;
		panelUsuario.add(textEmail, gbc_textEmail);

		lblContraseniaLabel = new JLabel("Contraseña");
		GridBagConstraints gbc_lblContraseniaLabel = new GridBagConstraints();
		gbc_lblContraseniaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblContraseniaLabel.gridx = 2;
		gbc_lblContraseniaLabel.gridy = 3;
		panelUsuario.add(lblContraseniaLabel, gbc_lblContraseniaLabel);

		textFieldContrasenia = new JPasswordField();
		GridBagConstraints gbc_textFieldContrasenia = new GridBagConstraints();
		gbc_textFieldContrasenia.gridwidth = 5;
		gbc_textFieldContrasenia.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldContrasenia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldContrasenia.gridx = 3;
		gbc_textFieldContrasenia.gridy = 3;
		textFieldContrasenia.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				cambioTexto();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				cambioTexto();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// Este metodo solo se llama si hay un cambio en el estilo, en los textField no
				// se precisa pero es requerido por el DocumentListener
				cambioTexto();
			}

			// Se ejecuta al hacer un cambio en el texto
			private void cambioTexto() {
				if (textFieldContrasenia.getText().isBlank()) {
					textFieldConfirmarContrasenia.setEnabled(false);
					textFieldConfirmarContrasenia.setText("");
				} else {
					textFieldConfirmarContrasenia.setEnabled(true);
				}
			}
		});
		panelUsuario.add(textFieldContrasenia, gbc_textFieldContrasenia);
		textFieldContrasenia.setColumns(10);

		lblConfirmarContraseniaLabel = new JLabel("Confirmar contraseña");
		GridBagConstraints gbc_lblConfirmarContraseniaLabel = new GridBagConstraints();
		gbc_lblConfirmarContraseniaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblConfirmarContraseniaLabel.gridx = 2;
		gbc_lblConfirmarContraseniaLabel.gridy = 4;
		panelUsuario.add(lblConfirmarContraseniaLabel, gbc_lblConfirmarContraseniaLabel);

		textFieldConfirmarContrasenia = new JPasswordField();
		GridBagConstraints gbc_textFieldConfirmarContrasenia = new GridBagConstraints();
		gbc_textFieldConfirmarContrasenia.gridwidth = 5;
		gbc_textFieldConfirmarContrasenia.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldConfirmarContrasenia.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldConfirmarContrasenia.gridx = 3;
		gbc_textFieldConfirmarContrasenia.gridy = 4;
		panelUsuario.add(textFieldConfirmarContrasenia, gbc_textFieldConfirmarContrasenia);
		textFieldConfirmarContrasenia.setColumns(10);

		JLabel lblNombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombreLabel = new GridBagConstraints();
		gbc_lblNombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreLabel.gridx = 2;
		gbc_lblNombreLabel.gridy = 5;
		panelUsuario.add(lblNombreLabel, gbc_lblNombreLabel);

		textName = new JTextField();
		textName.setColumns(10);
		GridBagConstraints gbc_textName = new GridBagConstraints();
		gbc_textName.gridwidth = 5;
		gbc_textName.insets = new Insets(0, 0, 5, 5);
		gbc_textName.fill = GridBagConstraints.BOTH;
		gbc_textName.gridx = 3;
		gbc_textName.gridy = 5;
		panelUsuario.add(textName, gbc_textName);

		lblFechaAltaLabel = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblFechaAltaLabel = new GridBagConstraints();
		gbc_lblFechaAltaLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblFechaAltaLabel.gridx = 2;
		gbc_lblFechaAltaLabel.gridy = 6;
		panelUsuario.add(lblFechaAltaLabel, gbc_lblFechaAltaLabel);

		comboBoxFechaAltaDia = new JComboBox<>();
		comboBoxFechaAltaDia.setToolTipText("Dia");
		GridBagConstraints gbc_comboBoxFechaAltaDia = new GridBagConstraints();
		gbc_comboBoxFechaAltaDia.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxFechaAltaDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaDia.gridx = 3;
		gbc_comboBoxFechaAltaDia.gridy = 6;
		panelUsuario.add(comboBoxFechaAltaDia, gbc_comboBoxFechaAltaDia);

		comboBoxFechaAltaMes = new JComboBox<>();
		for (String mes : Utilidades.Month) {
			comboBoxFechaAltaMes.addItem(mes);
		}
		comboBoxFechaAltaMes.setToolTipText("Mes");
		comboBoxFechaAltaMes.setSelectedIndex(fecha.getMonth());
		GridBagConstraints gbc_comboBoxFechaAltaMes = new GridBagConstraints();
		gbc_comboBoxFechaAltaMes.gridwidth = 2;
		gbc_comboBoxFechaAltaMes.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxFechaAltaMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaMes.gridx = 4;
		gbc_comboBoxFechaAltaMes.gridy = 6;
		comboBoxFechaAltaMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarDiasFechaAlta();
			}
		});
		panelUsuario.add(comboBoxFechaAltaMes, gbc_comboBoxFechaAltaMes);

		comboBoxFechaAltaAnio = new JComboBox<>();
		comboBoxFechaAltaAnio.setToolTipText("Año");
		cargarAnioFechaAlta();
		comboBoxFechaAltaAnio.setSelectedIndex(fecha.getYear() - 24);
		GridBagConstraints gbc_comboBoxFechaAltaAnio = new GridBagConstraints();
		gbc_comboBoxFechaAltaAnio.gridwidth = 2;
		gbc_comboBoxFechaAltaAnio.insets = new Insets(0, 0, 0, 5);
		gbc_comboBoxFechaAltaAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaAnio.gridx = 6;
		gbc_comboBoxFechaAltaAnio.gridy = 6;
		comboBoxFechaAltaAnio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarDiasFechaAlta();
			}
		});
		panelUsuario.add(comboBoxFechaAltaAnio, gbc_comboBoxFechaAltaAnio);
		
		cargarDiasFechaAlta();
		comboBoxFechaAltaDia.setSelectedIndex(fecha.getDate() - 1);
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
		gbl_panelAerolinea.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelAerolinea.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, Double.MIN_VALUE };
		gbl_panelAerolinea.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		panelAerolinea.setLayout(gbl_panelAerolinea);

		cardPanel.add(panelAerolinea, "Aerolinea");

		lblWebLabel = new JLabel("Web");
		GridBagConstraints gbc_lblWebLabel = new GridBagConstraints();
		gbc_lblWebLabel.gridwidth = 2;
		gbc_lblWebLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblWebLabel.gridx = 2;
		gbc_lblWebLabel.gridy = 2;
		panelAerolinea.add(lblWebLabel, gbc_lblWebLabel);

		textWeb = new JTextField();
		GridBagConstraints gbc_textWeb = new GridBagConstraints();
		gbc_textWeb.gridwidth = 8;
		gbc_textWeb.insets = new Insets(0, 0, 5, 5);
		gbc_textWeb.fill = GridBagConstraints.BOTH;
		gbc_textWeb.gridx = 4;
		gbc_textWeb.gridy = 2;
		panelAerolinea.add(textWeb, gbc_textWeb);
		textWeb.setColumns(10);

		lblDescripcionLabel = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcionLabel = new GridBagConstraints();
		gbc_lblDescripcionLabel.gridwidth = 4;
		gbc_lblDescripcionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionLabel.gridx = 6;
		gbc_lblDescripcionLabel.gridy = 4;
		panelAerolinea.add(lblDescripcionLabel, gbc_lblDescripcionLabel);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBorder(textWeb.getBorder());
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 7;
		gbc_textArea.gridwidth = 8;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 4;
		gbc_textArea.gridy = 5;
		panelAerolinea.add(textArea, gbc_textArea);
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
		CBDia.setToolTipText("Dia");
		GridBagConstraints gbc_cBDia = new GridBagConstraints();
		gbc_cBDia.gridwidth = 2;
		gbc_cBDia.insets = new Insets(0, 0, 5, 5);
		gbc_cBDia.fill = GridBagConstraints.BOTH;
		gbc_cBDia.gridx = 4;
		gbc_cBDia.gridy = 3;
		panelCliente.add(CBDia, gbc_cBDia);

		CBMes = new JComboBox<>();
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
	private void limpiarFormulario() {
		textNick.setText("");
		textName.setText("");
		textEmail.setText("");
		textFieldContrasenia.setText("");
		textFieldConfirmarContrasenia.setText("");
		fecha = new Date();
		comboBoxFechaAltaMes.setSelectedIndex(fecha.getMonth());
		comboBoxFechaAltaAnio.setSelectedIndex(fecha.getYear() - 24);
		comboBoxFechaAltaDia.setSelectedIndex(fecha.getDate() - 1);
		limpiarFormularioCliente();
		limpiarFormularioAerolinea();
	}

	@SuppressWarnings("deprecation")
	private void limpiarFormularioCliente() {
		textApellido.setText("");
		textNacionalidad.setText("");
		// CBNacionalidad.setSelectedIndex(0);
		textDoc.setText("");
		CBMes.setSelectedIndex(fecha.getMonth());
		CBAnio.setSelectedIndex(fecha.getYear() - 24);
		CBDia.setSelectedIndex(fecha.getDate() - 1);
	}

	private void limpiarFormularioAerolinea() {
		textWeb.setText("");
		textArea.setText("");
	}

	@SuppressWarnings("deprecation")
	private boolean checkFormulario() {
		if (!camposComunes()) {
			mostrarMensajeError("No puede haber campos vacíos");
			return false;
		}

		if (!textEmail.getText().matches("^[^@]+@[^@]+$")) {
			mostrarMensajeError("El email tiene que tener el formato: (texto)@(texto)");
			return false;
		}
		
		if (textNick.getText().matches(".*[{}\\[\\]|\\\\^`].*")) {
			mostrarMensajeError("El nickname del usuario contiene caracteres no permitidos");
			return false;
		}
		
		if (textEmail.getText().matches(".*[{}\\[\\]|\\\\^`].*")) {
			mostrarMensajeError("El email del usuario contiene caracteres no permitidos");
			return false;
		}
		
		if (!tipoUsuarioSeleccionado()) {
			mostrarMensajeError("Debe elegir un tipo de usuario");
			return false;
		}

		if (rdbtnAerolinea.isSelected() && !camposAerolinea()) {
			mostrarMensajeError("Los campos de Aerolinea no pueden ser vacíos");
			return false;
		}

		if (rdbtnCliente.isSelected() && !camposCliente()) {
			mostrarMensajeError("Los campos del Cliente no pueden ser vacíos");
			return false;
		}

		if (!textFieldConfirmarContrasenia.getText().equals(textFieldContrasenia.getText())) {
			mostrarMensajeError("La contraseña ingresada y la confirmación no coinciden");
			return false;
		}

		return true;
	}

	@SuppressWarnings("deprecation")
	private boolean camposComunes() {
		return !textNick.getText().isEmpty() && !textEmail.getText().isEmpty() && !textName.getText().isEmpty()
				&& !textFieldContrasenia.getText().isBlank() && !textFieldConfirmarContrasenia.getText().isBlank();
	}

	private boolean tipoUsuarioSeleccionado() {
		return rdbtnCliente.isSelected() || rdbtnAerolinea.isSelected();
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

	@SuppressWarnings("deprecation")
	protected void AltaUsuarioActionPerformed(ActionEvent e) {
		try {
			if (checkFormulario()) {

				String contrasenia = textFieldContrasenia.getText();
				Date fechaAltaUser = new Date((Integer) comboBoxFechaAltaAnio.getSelectedItem() - 1900,
						Utilidades.MesStringToInt((String) comboBoxFechaAltaMes.getSelectedItem()) - 1,
						(Integer) comboBoxFechaAltaDia.getSelectedItem());
				
				Utilidades.actualizarFechaAlta(fechaAltaUser);

				if (rdbtnCliente.isSelected()) {
					List<String> reservas = null;
					List<String> comprasPaquete = null;
					Date fechaUser = new Date((Integer) CBAnio.getSelectedItem() - 1900,
							Utilidades.MesStringToInt((String) CBMes.getSelectedItem()) - 1,
							(Integer) CBDia.getSelectedItem());
					ClienteDto newCliente = new ClienteDto(textNick.getText(), textName.getText(), textEmail.getText(),
							textApellido.getText(), fechaUser, (TipoDocumento) CBTipoDoc.getSelectedItem(),
							textDoc.getText(), textNacionalidad.getText(), reservas, comprasPaquete, fechaAltaUser, contrasenia, null, null, null);
					/*
					 * PaisesEnum NacionalidadSelected = (PaisesEnum)
					 * CBNacionalidad.getSelectedItem(); String seleccionadoString =
					 * NacionalidadSelected.toString();
					 * 
					 * ClienteDto newCliente = new ClienteDto(textNick.getText(),
					 * textName.getText(), textEmail.getText(),
					 * textApellido.getText(),fechaUser,(TipoDocumento)CBTipoDoc.getSelectedItem(),
					 * textDoc.getText(), seleccionadoString, reservas, comprasPaquete);
					 */
					try {
						controlUsr.agregarUsuario(newCliente);
						JOptionPane.showMessageDialog(this, "Usuario Registrado con exito", "Registrar Usuario",
								JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(this, e1.getMessage(), "Alta de Usuario",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					List<String> rutasDeVuelo = null;
					AerolineaDto newAero = new AerolineaDto(textNick.getText(), textName.getText(), textEmail.getText(),
							textArea.getText(), textWeb.getText(), rutasDeVuelo, fechaAltaUser, contrasenia, null, null, null);
					try {
						controlUsr.agregarUsuario(newAero);
						JOptionPane.showMessageDialog(this, "Usuario Registrado con exito", "Registrar Usuario",
								JOptionPane.INFORMATION_MESSAGE);
						limpiarFormulario();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(this, e2.getMessage(), "Alta de Usuario",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(this, e1.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void cargarAnioFechaAlta(){
		for (Integer anio : Utilidades.getAnios()) {
			comboBoxFechaAltaAnio.addItem(anio);
		}
	}
	
	private void cargarDiasFechaAlta(){
		int i = 0;
		if (comboBoxFechaAltaDia.getItemCount() > 0) {
			i = comboBoxFechaAltaDia.getSelectedIndex();
			comboBoxFechaAltaDia.removeAllItems();
		}
		Integer mes = Utilidades.MesStringToInt((String) comboBoxFechaAltaMes.getSelectedItem());
		Integer anio = (Integer) comboBoxFechaAltaAnio.getSelectedIndex();
		for (Integer dia : Utilidades.getDias(mes, anio)) {
			comboBoxFechaAltaDia.addItem(dia);
		}

		if (i < comboBoxFechaAltaDia.getItemCount()) {
			comboBoxFechaAltaDia.setSelectedIndex(i);
		} else {
			comboBoxFechaAltaDia.setSelectedIndex(0);
		}
	}
	
}
