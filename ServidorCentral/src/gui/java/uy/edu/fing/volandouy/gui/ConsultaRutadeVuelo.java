package uy.edu.fing.volandouy.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.dto.VueloDto;

@SuppressWarnings("serial")
public class ConsultaRutadeVuelo extends JInternalFrame {
	// Controllers
	private IUsuarioController iusuario;
	private IVueloController ivuelo;

	// Ventanas
	private ConsultaVuelo consultaVueloFrame;

	// Componentes
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private JPanel panelPrincipal;
	private JPanel panelMasDatos;
	private JDesktopPane desktopPaneConsultavuelo;

	private JLabel lblAerolineasLabel;
	private JComboBox<String> comboBoxAerolineas;
	private JLabel lblRutasLabel;
	private JComboBox<String> comboBoxRutas;
	private JLabel lblNombreLabel;
	private JTextField textFieldNombre;
	private JLabel lblFechaAltaLabel;
	private JTextField textFieldFechaAlta;
	private JLabel lblCostoTuristaLabel;
	private JLabel lblAerolineaLabel;
	private JLabel lblCostoEjecutivoLabel;
	private JLabel lblCostoEquipajeLabel;
	private JTextField textFieldCostoTurista;
	private JTextField textFieldAerolinea;
	private JTextField textFieldCostoEjecutivo;
	private JTextField textFieldCostoEquipaje;
	private JLabel lblHoraLabel;
	private JLabel lblCiudadOrigenLabel;
	private JTextField textFieldHora;
	private JTextField textFieldCiudadOrigen;
	private JLabel lblCiudadDestinoLabel;
	private JTextField textFieldCiudadDestino;
	private JButton btnMostrarMasDatosButton;
	private JButton btnCancelarButton;
	private JLabel lblDescripcionLabel;
	private JTextArea textAreaDescripcion;
	private JLabel lblCategoriasLabel;
	private JLabel lblVuelosLabel;
	private JComboBox<String> comboBoxVuelos;
	private JButton btnConsultarVueloButton;
	private JButton btnVolverButton;
	private JList<String> listCategorias;

	// Variables auxiliares
	private List<UsuarioDto> usuariosDto;
	private JScrollPane scrollPaneCategorias;
	private DefaultListModel<String> modeloLista;
	private JLabel lblResumenLabel;
	private JTextArea textAreaResumen;
	private JLabel lblEstadoLabel;
	private JTextField textFieldEstado;

	public ConsultaRutadeVuelo(IUsuarioController iusuarioC, IVueloController ivueloC) {
		this.iusuario = iusuarioC;
		this.ivuelo = ivueloC;
		usuariosDto = iusuario.listarUsuario();

		// Inicializar valores del JInternalFrame
		setBounds(100, 100, 840, 760);
		setVisible(false);
		setTitle("Consulta Ruta de Vuelo");
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

		// Crea el panel principal del caso de uso
		panelPrincipal = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelPrincipal.setLayout(gridBagLayout);
		cardPanel.add(panelPrincipal, "panelPrincipal");

		lblAerolineasLabel = new JLabel("Aerolíneas");
		GridBagConstraints gbc_lblAerolineasLabel = new GridBagConstraints();
		gbc_lblAerolineasLabel.gridwidth = 2;
		gbc_lblAerolineasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAerolineasLabel.gridx = 5;
		gbc_lblAerolineasLabel.gridy = 1;
		panelPrincipal.add(lblAerolineasLabel, gbc_lblAerolineasLabel);

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
				cargarComboBoxRutas((String) comboBoxAerolineas.getSelectedItem());
			}
		});
		panelPrincipal.add(comboBoxAerolineas, gbc_comboBoxAerolineas);

		lblRutasLabel = new JLabel("Rutas");
		GridBagConstraints gbc_lblRutasLabel = new GridBagConstraints();
		gbc_lblRutasLabel.gridwidth = 2;
		gbc_lblRutasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutasLabel.gridx = 5;
		gbc_lblRutasLabel.gridy = 5;
		panelPrincipal.add(lblRutasLabel, gbc_lblRutasLabel);

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
					InicializarDatos((String) comboBoxAerolineas.getSelectedItem(),
							(String) comboBoxRutas.getSelectedItem());
					cargarComboBoxVuelos((String) comboBoxAerolineas.getSelectedItem(),
							(String) comboBoxRutas.getSelectedItem());
				}
			}
		});
		panelPrincipal.add(comboBoxRutas, gbc_comboBoxRutas);

		lblNombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombreLabel = new GridBagConstraints();
		gbc_lblNombreLabel.gridwidth = 2;
		gbc_lblNombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreLabel.gridx = 5;
		gbc_lblNombreLabel.gridy = 9;
		panelPrincipal.add(lblNombreLabel, gbc_lblNombreLabel);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 5;
		gbc_textFieldNombre.gridy = 10;
		panelPrincipal.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		lblCostoTuristaLabel = new JLabel("Costo Turista");
		GridBagConstraints gbc_lblCostoTuristaLabel = new GridBagConstraints();
		gbc_lblCostoTuristaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoTuristaLabel.gridx = 1;
		gbc_lblCostoTuristaLabel.gridy = 12;
		panelPrincipal.add(lblCostoTuristaLabel, gbc_lblCostoTuristaLabel);

		textFieldCostoTurista = new JTextField();
		textFieldCostoTurista.setEditable(false);
		GridBagConstraints gbc_textFieldCostoTurista = new GridBagConstraints();
		gbc_textFieldCostoTurista.gridwidth = 3;
		gbc_textFieldCostoTurista.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCostoTurista.fill = GridBagConstraints.BOTH;
		gbc_textFieldCostoTurista.gridx = 2;
		gbc_textFieldCostoTurista.gridy = 12;
		panelPrincipal.add(textFieldCostoTurista, gbc_textFieldCostoTurista);
		textFieldCostoTurista.setColumns(10);

		textFieldAerolinea = new JTextField();
		textFieldAerolinea.setEditable(false);
		GridBagConstraints gbc_textFieldAerolinea = new GridBagConstraints();
		gbc_textFieldAerolinea.gridwidth = 3;
		gbc_textFieldAerolinea.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAerolinea.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAerolinea.gridx = 7;
		gbc_textFieldAerolinea.gridy = 12;
		panelPrincipal.add(textFieldAerolinea, gbc_textFieldAerolinea);
		textFieldAerolinea.setColumns(10);

		lblAerolineaLabel = new JLabel("Aerolínea");
		GridBagConstraints gbc_lblAerolineaLabel = new GridBagConstraints();
		gbc_lblAerolineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAerolineaLabel.gridx = 10;
		gbc_lblAerolineaLabel.gridy = 12;
		panelPrincipal.add(lblAerolineaLabel, gbc_lblAerolineaLabel);

		lblCostoEjecutivoLabel = new JLabel("Costo Ejecutivo");
		GridBagConstraints gbc_lblCostoEjecutivoLabel = new GridBagConstraints();
		gbc_lblCostoEjecutivoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoEjecutivoLabel.gridx = 1;
		gbc_lblCostoEjecutivoLabel.gridy = 14;
		panelPrincipal.add(lblCostoEjecutivoLabel, gbc_lblCostoEjecutivoLabel);

		textFieldCostoEjecutivo = new JTextField();
		textFieldCostoEjecutivo.setEditable(false);
		GridBagConstraints gbc_textFieldCostoEjecutivo = new GridBagConstraints();
		gbc_textFieldCostoEjecutivo.gridwidth = 3;
		gbc_textFieldCostoEjecutivo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCostoEjecutivo.fill = GridBagConstraints.BOTH;
		gbc_textFieldCostoEjecutivo.gridx = 2;
		gbc_textFieldCostoEjecutivo.gridy = 14;
		panelPrincipal.add(textFieldCostoEjecutivo, gbc_textFieldCostoEjecutivo);
		textFieldCostoEjecutivo.setColumns(10);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
		gbc_textFieldFechaAlta.gridwidth = 3;
		gbc_textFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaAlta.fill = GridBagConstraints.BOTH;
		gbc_textFieldFechaAlta.gridx = 7;
		gbc_textFieldFechaAlta.gridy = 14;
		panelPrincipal.add(textFieldFechaAlta, gbc_textFieldFechaAlta);
		textFieldFechaAlta.setColumns(10);

		lblFechaAltaLabel = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblFechaAltaLabel = new GridBagConstraints();
		gbc_lblFechaAltaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaLabel.gridx = 10;
		gbc_lblFechaAltaLabel.gridy = 14;
		panelPrincipal.add(lblFechaAltaLabel, gbc_lblFechaAltaLabel);

		lblCostoEquipajeLabel = new JLabel("Costo Equipaje");
		GridBagConstraints gbc_lblCostoEquipajeLabel = new GridBagConstraints();
		gbc_lblCostoEquipajeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoEquipajeLabel.gridx = 1;
		gbc_lblCostoEquipajeLabel.gridy = 16;
		panelPrincipal.add(lblCostoEquipajeLabel, gbc_lblCostoEquipajeLabel);

		textFieldCostoEquipaje = new JTextField();
		textFieldCostoEquipaje.setEditable(false);
		GridBagConstraints gbc_textFieldCostoEquipaje = new GridBagConstraints();
		gbc_textFieldCostoEquipaje.gridwidth = 3;
		gbc_textFieldCostoEquipaje.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCostoEquipaje.fill = GridBagConstraints.BOTH;
		gbc_textFieldCostoEquipaje.gridx = 2;
		gbc_textFieldCostoEquipaje.gridy = 16;
		panelPrincipal.add(textFieldCostoEquipaje, gbc_textFieldCostoEquipaje);
		textFieldCostoEquipaje.setColumns(10);

		textFieldCiudadOrigen = new JTextField();
		textFieldCiudadOrigen.setEditable(false);
		GridBagConstraints gbc_textFieldCiudadOrigen = new GridBagConstraints();
		gbc_textFieldCiudadOrigen.gridwidth = 3;
		gbc_textFieldCiudadOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCiudadOrigen.fill = GridBagConstraints.BOTH;
		gbc_textFieldCiudadOrigen.gridx = 7;
		gbc_textFieldCiudadOrigen.gridy = 16;
		panelPrincipal.add(textFieldCiudadOrigen, gbc_textFieldCiudadOrigen);
		textFieldCiudadOrigen.setColumns(10);

		lblCiudadOrigenLabel = new JLabel("Ciudad Origen");
		GridBagConstraints gbc_lblCiudadOrigenLabel = new GridBagConstraints();
		gbc_lblCiudadOrigenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudadOrigenLabel.gridx = 10;
		gbc_lblCiudadOrigenLabel.gridy = 16;
		panelPrincipal.add(lblCiudadOrigenLabel, gbc_lblCiudadOrigenLabel);

		lblHoraLabel = new JLabel("Hora");
		GridBagConstraints gbc_lblHoraLabel = new GridBagConstraints();
		gbc_lblHoraLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoraLabel.gridx = 1;
		gbc_lblHoraLabel.gridy = 18;
		panelPrincipal.add(lblHoraLabel, gbc_lblHoraLabel);

		textFieldHora = new JTextField();
		textFieldHora.setEditable(false);
		GridBagConstraints gbc_textFieldHora = new GridBagConstraints();
		gbc_textFieldHora.gridwidth = 3;
		gbc_textFieldHora.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHora.fill = GridBagConstraints.BOTH;
		gbc_textFieldHora.gridx = 2;
		gbc_textFieldHora.gridy = 18;
		panelPrincipal.add(textFieldHora, gbc_textFieldHora);
		textFieldHora.setColumns(10);

		textFieldCiudadDestino = new JTextField();
		textFieldCiudadDestino.setEditable(false);
		GridBagConstraints gbc_textFieldCiudadDestino = new GridBagConstraints();
		gbc_textFieldCiudadDestino.gridwidth = 3;
		gbc_textFieldCiudadDestino.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCiudadDestino.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudadDestino.gridx = 7;
		gbc_textFieldCiudadDestino.gridy = 18;
		panelPrincipal.add(textFieldCiudadDestino, gbc_textFieldCiudadDestino);
		textFieldCiudadDestino.setColumns(10);

		lblCiudadDestinoLabel = new JLabel("Ciudad Destino");
		GridBagConstraints gbc_lblCiudadDestinoLabel = new GridBagConstraints();
		gbc_lblCiudadDestinoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudadDestinoLabel.gridx = 10;
		gbc_lblCiudadDestinoLabel.gridy = 18;
		panelPrincipal.add(lblCiudadDestinoLabel, gbc_lblCiudadDestinoLabel);

		btnMostrarMasDatosButton = new JButton("Mostrar mas datos");
		GridBagConstraints gbc_btnMostrarMasDatosButton = new GridBagConstraints();
		gbc_btnMostrarMasDatosButton.gridwidth = 2;
		gbc_btnMostrarMasDatosButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnMostrarMasDatosButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnMostrarMasDatosButton.gridx = 4;
		gbc_btnMostrarMasDatosButton.gridy = 22;
		btnMostrarMasDatosButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "mostrarMasDatos");
			}
		});
		panelPrincipal.add(btnMostrarMasDatosButton, gbc_btnMostrarMasDatosButton);

		btnCancelarButton = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelarButton = new GridBagConstraints();
		gbc_btnCancelarButton.gridwidth = 2;
		gbc_btnCancelarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelarButton.gridx = 6;
		gbc_btnCancelarButton.gridy = 22;
		panelPrincipal.add(btnCancelarButton, gbc_btnCancelarButton);

		// Crea el panel de mostrar mas datos
		panelMasDatos = new JPanel();
		GridBagLayout gridBagLayout2 = new GridBagLayout();
		gridBagLayout2.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout2.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gridBagLayout2.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout2.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelMasDatos.setLayout(gridBagLayout2);
		cardPanel.add(panelMasDatos, "mostrarMasDatos");

		lblDescripcionLabel = new JLabel("Descripción");
		GridBagConstraints gbc_lblDescripcionLabel = new GridBagConstraints();
		gbc_lblDescripcionLabel.gridwidth = 8;
		gbc_lblDescripcionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionLabel.gridx = 2;
		gbc_lblDescripcionLabel.gridy = 1;
		panelMasDatos.add(lblDescripcionLabel, gbc_lblDescripcionLabel);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setBorder(textFieldNombre.getBorder());
		textAreaDescripcion.setEditable(false);
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		gbc_textAreaDescripcion.gridheight = 4;
		gbc_textAreaDescripcion.gridwidth = 8;
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescripcion.gridx = 2;
		gbc_textAreaDescripcion.gridy = 2;
		panelMasDatos.add(textAreaDescripcion, gbc_textAreaDescripcion);

		lblCategoriasLabel = new JLabel("Categorías");
		GridBagConstraints gbc_lblCategoriasLabel = new GridBagConstraints();
		gbc_lblCategoriasLabel.gridwidth = 8;
		gbc_lblCategoriasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoriasLabel.gridx = 2;
		gbc_lblCategoriasLabel.gridy = 7;
		panelMasDatos.add(lblCategoriasLabel, gbc_lblCategoriasLabel);

		modeloLista = new DefaultListModel<>();
		listCategorias = new JList<>(modeloLista);
		listCategorias.setEnabled(false);
		scrollPaneCategorias = new JScrollPane(listCategorias);
		GridBagConstraints gbc_scrollPaneCategorias = new GridBagConstraints();
		gbc_scrollPaneCategorias.gridheight = 4;
		gbc_scrollPaneCategorias.gridwidth = 8;
		gbc_scrollPaneCategorias.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneCategorias.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneCategorias.gridx = 2;
		gbc_scrollPaneCategorias.gridy = 8;
		panelMasDatos.add(scrollPaneCategorias, gbc_scrollPaneCategorias);

		lblEstadoLabel = new JLabel("Estado");
		GridBagConstraints gbc_lblEstadoLabel = new GridBagConstraints();
		gbc_lblEstadoLabel.gridwidth = 8;
		gbc_lblEstadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblEstadoLabel.gridx = 2;
		gbc_lblEstadoLabel.gridy = 13;
		panelMasDatos.add(lblEstadoLabel, gbc_lblEstadoLabel);

		textFieldEstado = new JTextField();
		textFieldEstado.setEditable(false);
		GridBagConstraints gbc_textFieldEstado = new GridBagConstraints();
		gbc_textFieldEstado.gridwidth = 8;
		gbc_textFieldEstado.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEstado.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEstado.gridx = 2;
		gbc_textFieldEstado.gridy = 14;
		panelMasDatos.add(textFieldEstado, gbc_textFieldEstado);
		textFieldEstado.setColumns(10);

		lblResumenLabel = new JLabel("Descripción Corta");
		GridBagConstraints gbc_lblResumenLabel = new GridBagConstraints();
		gbc_lblResumenLabel.gridwidth = 8;
		gbc_lblResumenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblResumenLabel.gridx = 2;
		gbc_lblResumenLabel.gridy = 16;
		panelMasDatos.add(lblResumenLabel, gbc_lblResumenLabel);

		textAreaResumen = new JTextArea();
		textAreaResumen.setLineWrap(true);
		textAreaResumen.setBorder(textFieldNombre.getBorder());
		textAreaResumen.setEditable(false);
		GridBagConstraints gbc_textAreaResumen = new GridBagConstraints();
		gbc_textAreaResumen.gridheight = 2;
		gbc_textAreaResumen.gridwidth = 8;
		gbc_textAreaResumen.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaResumen.fill = GridBagConstraints.BOTH;
		gbc_textAreaResumen.gridx = 2;
		gbc_textAreaResumen.gridy = 17;
		panelMasDatos.add(textAreaResumen, gbc_textAreaResumen);

		lblVuelosLabel = new JLabel("Vuelos");
		GridBagConstraints gbc_lblVuelosLabel = new GridBagConstraints();
		gbc_lblVuelosLabel.gridwidth = 8;
		gbc_lblVuelosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblVuelosLabel.gridx = 2;
		gbc_lblVuelosLabel.gridy = 20;
		panelMasDatos.add(lblVuelosLabel, gbc_lblVuelosLabel);

		comboBoxVuelos = new JComboBox<>();
		GridBagConstraints gbc_comboBoxVuelos = new GridBagConstraints();
		gbc_comboBoxVuelos.gridheight = 2;
		gbc_comboBoxVuelos.gridwidth = 8;
		gbc_comboBoxVuelos.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxVuelos.fill = GridBagConstraints.BOTH;
		gbc_comboBoxVuelos.gridx = 2;
		gbc_comboBoxVuelos.gridy = 21;
		panelMasDatos.add(comboBoxVuelos, gbc_comboBoxVuelos);

		btnConsultarVueloButton = new JButton("Consultar Vuelo");
		GridBagConstraints gbc_btnConsultarVueloButton = new GridBagConstraints();
		gbc_btnConsultarVueloButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConsultarVueloButton.gridwidth = 2;
		gbc_btnConsultarVueloButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultarVueloButton.gridx = 4;
		gbc_btnConsultarVueloButton.gridy = 24;
		btnConsultarVueloButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validarConsultarVuelo()) {
					cardLayout.show(cardPanel, "ConsultaVuelo");
					InicializarConsultaVueloFrame();
					// setTitle("Consulta Vuelo");
					/*
					 * JButton btnCerrarConsultaVuelo = consultaVueloFrame.getCancelarButton();
					 * btnCerrarConsultaVuelo.addActionListener(new ActionListener() { public void
					 * actionPerformed(ActionEvent arg0) { cardLayout.show(cardPanel,
					 * "panelPrincipal"); } });
					 */
				}
			}
		});
		panelMasDatos.add(btnConsultarVueloButton, gbc_btnConsultarVueloButton);

		btnVolverButton = new JButton("Volver");
		GridBagConstraints gbc_btnVolverButton = new GridBagConstraints();
		gbc_btnVolverButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnVolverButton.gridwidth = 2;
		gbc_btnVolverButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnVolverButton.gridx = 6;
		gbc_btnVolverButton.gridy = 24;
		btnVolverButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "panelPrincipal");
			}
		});
		panelMasDatos.add(btnVolverButton, gbc_btnVolverButton);

		// Crea el panel de ConsultaVuelo
		desktopPaneConsultavuelo = new JDesktopPane();
		desktopPaneConsultavuelo.setBackground(new Color(240, 240, 240));
		cardPanel.add(desktopPaneConsultavuelo, "ConsultaVuelo");

		inicializarAerolineasRutas();

	}

	public JButton getCancelarButton() {
		return btnCancelarButton;
	}

	private void InicializarConsultaVueloFrame() {
		// Setea el internal frame de ConsultaVuelo
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
				cardLayout.show(cardPanel, "panelPrincipal");
				DestruirConsultaVuelo();
				// setTitle("Consulta Ruta de Vuelo");
			}
		});
		consultaVueloFrame.precargarCampos((String) comboBoxAerolineas.getSelectedItem(),
				(String) comboBoxRutas.getSelectedItem(), (String) comboBoxVuelos.getSelectedItem());
		desktopPaneConsultavuelo.add(consultaVueloFrame);

	}

	// Se llama desde el ConsultaVuelo cuando se desea volver a ConsultaRutaDeVuelo
	private void DestruirConsultaVuelo() {
		consultaVueloFrame.setVisible(false);
		desktopPaneConsultavuelo.remove(consultaVueloFrame);
		consultaVueloFrame.dispose();

	}

	// Carga todos los datos de la ruta de vuelo seleccionada
	@SuppressWarnings("deprecation")
	private void InicializarDatos(String aerolinea, String ruta) {
		try {

			cargarComboBoxVuelos(aerolinea, ruta);

			List<RutaDeVueloDto> rutasAerolinea = iusuario.listarRutas(aerolinea);
			RutaDeVueloDto rutaDto = null;
			for (RutaDeVueloDto rutaSistema : rutasAerolinea) {
				if (rutaSistema.getNombre().equals(ruta)) {
					rutaDto = rutaSistema;
				}
			}

			textFieldAerolinea.setText(rutaDto.getAerolinea().getNombre());

			textFieldCiudadDestino
					.setText(rutaDto.getCiudadDestino().getNombre() + " - " + rutaDto.getCiudadOrigen().getPais());

			textFieldCiudadOrigen
					.setText(rutaDto.getCiudadOrigen().getNombre() + " - " + rutaDto.getCiudadOrigen().getPais());

			textFieldCostoEjecutivo.setText(String.valueOf(rutaDto.getCostoEjecutivo()));

			textFieldCostoEquipaje.setText(String.valueOf(rutaDto.getCostoEquipajeExtra()));

			textFieldCostoTurista.setText(String.valueOf(rutaDto.getCostoTurista()));

			String diaFechaAlta = Integer.toString(rutaDto.getFechaAlta().getDate());
			String mesFechaAlta = Utilidades.Month[rutaDto.getFechaAlta().getMonth()];
			String anioFechaAlta = Integer.toString(rutaDto.getFechaAlta().getYear() + 1900);
			textFieldFechaAlta.setText(diaFechaAlta + " de " + mesFechaAlta + " de " + anioFechaAlta);

			textFieldHora.setText(rutaDto.getHora().toString().substring(0, 5));

			textFieldNombre.setText(rutaDto.getNombre());

			textAreaDescripcion.setText(rutaDto.getDescripcion());

			textFieldEstado.setText(rutaDto.getEstado().toString());

			textAreaResumen.setText(rutaDto.getResumen());

			// Carga las categorias
			modeloLista.removeAllElements();
			for (CategoriaDto categoriaDto : rutaDto.getCategorias()) {
				modeloLista.addElement(categoriaDto.getNombre());
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void inicializarAerolineasRutas() {
		for (UsuarioDto usuario : usuariosDto) {
			if (usuario instanceof AerolineaDto) {
				comboBoxAerolineas.addItem(usuario.getNickName());
			}
		}
		if (comboBoxAerolineas.getItemCount() > 0) {

			comboBoxAerolineas.setSelectedIndex(0);
			cargarComboBoxRutas((String) comboBoxAerolineas.getSelectedItem());

		}

	}

	private void cargarComboBoxRutas(String aerolinea) {
		comboBoxRutas.removeAllItems();
		AerolineaDto aerolineadto = null;

		for (UsuarioDto usuario : usuariosDto) {
			if (usuario.getNickName().equals((String) comboBoxAerolineas.getSelectedItem())) {
				aerolineadto = (AerolineaDto) usuario;
			}
		}

		for (String rutaNombre : aerolineadto.getRutasDeVuelo()) {
			comboBoxRutas.addItem(rutaNombre);
		}

		if (comboBoxRutas.getItemCount() > 0) {
			comboBoxRutas.setSelectedIndex(0);
			InicializarDatos(aerolinea, (String) comboBoxRutas.getSelectedItem());
		} else {
			limpiarFormulario();
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
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

		}

	}

	private void limpiarFormulario() {
		textFieldAerolinea.setText("");
		textFieldCiudadDestino.setText("");
		textFieldCiudadOrigen.setText("");
		textFieldCostoEjecutivo.setText("");
		textFieldCostoEquipaje.setText("");
		textFieldCostoTurista.setText("");
		textFieldFechaAlta.setText("");
		textFieldHora.setText("");
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldEstado.setText("");
		textAreaResumen.setText("");
		modeloLista.removeAllElements();
		comboBoxVuelos.removeAllItems();
	}

	private boolean validarConsultarVuelo() {
		if (comboBoxAerolineas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ninguna aerolinea", "Error",
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

		return true;
	}

	// Se llama desde el ConsultaUsuario
	public void precargarCampos(String aerolinea, String ruta) {
		comboBoxAerolineas.addItem(aerolinea);
		comboBoxAerolineas.setSelectedIndex(0);
		comboBoxAerolineas.setEnabled(false);

		comboBoxRutas.addItem(ruta);
		comboBoxRutas.setSelectedIndex(0);
		comboBoxRutas.setEnabled(false);

		InicializarDatos(aerolinea, ruta);

		int i = 1;
		while (!((String) comboBoxAerolineas.getSelectedItem()).equals(aerolinea)) {
			comboBoxAerolineas.setSelectedIndex(i);
			i++;
		}

		i = 1;
		while (!((String) comboBoxRutas.getSelectedItem()).equals(ruta)) {
			comboBoxRutas.setSelectedIndex(i);
			i++;
		}

	}

}