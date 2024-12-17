package uy.edu.fing.volandouy.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyVetoException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.CategoriaDto;
import uy.edu.fing.volandouy.dto.CiudadDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;

@SuppressWarnings("serial")
public class AltaRutaDeVuelo extends JInternalFrame {
	// Controllers
	IUsuarioController iusuario;
	IVueloController ivuelo;
	// Componenetes
	private JTextField textFieldNombre;
	private JTextField textFieldHora;
	private JTextField textFieldCostoTurista;
	private JTextField textFieldCostoEjecutivo;
	private JTextField textFieldCostoEquipaje;
	private JComboBox<String> comboBoxAerolineas;
	private JComboBox<String> comboBoxCiudadDestino;
	private JComboBox<String> comboBoxCiudadOrigen;
	private JComboBox<Integer> comboBoxFechaAltaDia;
	private JComboBox<String> comboBoxFechaAltaMes;
	private JComboBox<Integer> comboBoxFechaAltaAnio;
	private JList<String> listCategorias;
	private JButton btnAceptarButton;
	private JButton btnCancelarButton;
	// Variables auxiliares
	private DefaultListModel<String> modeloLista;
	private Date fechaActual = new Date();
	private String respaldoHora;
	private String respaldoCostoTurista;
	private String respaldoCostoEjecutivo;
	private String respaldoCostoEquipaje;
	private JLabel lblDescripcionLabel;
	private JTextArea textAreaDescripcion;
	private JLabel lblResumenLabel;
	private JTextArea textAreaResumen;

	public AltaRutaDeVuelo(IUsuarioController iusuario, IVueloController ivuelo) {
		this.iusuario = iusuario;
		this.ivuelo = ivuelo;

		// Inicializar el internalFrame
		setBounds(100, 100, 840, 760);
		setVisible(false);
		setTitle("Alta Ruta de Vuelo");
		setClosable(true);
		setResizable(true);
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblAerolineasLabel = new JLabel("Aerolineas");
		GridBagConstraints gbc_lblAerolineasLabel = new GridBagConstraints();
		gbc_lblAerolineasLabel.gridwidth = 8;
		gbc_lblAerolineasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAerolineasLabel.gridx = 2;
		gbc_lblAerolineasLabel.gridy = 1;
		getContentPane().add(lblAerolineasLabel, gbc_lblAerolineasLabel);

		comboBoxAerolineas = new JComboBox<>();
		GridBagConstraints gbc_comboBoxAerolineas = new GridBagConstraints();
		gbc_comboBoxAerolineas.gridheight = 2;
		gbc_comboBoxAerolineas.gridwidth = 8;
		gbc_comboBoxAerolineas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAerolineas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxAerolineas.gridx = 2;
		gbc_comboBoxAerolineas.gridy = 2;
		getContentPane().add(comboBoxAerolineas, gbc_comboBoxAerolineas);

		JLabel lblNombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombreLabel = new GridBagConstraints();
		gbc_lblNombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreLabel.gridx = 1;
		gbc_lblNombreLabel.gridy = 5;
		getContentPane().add(lblNombreLabel, gbc_lblNombreLabel);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 5;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldHora = new JTextField();
		textFieldHora.setText("00:00");
		respaldoHora = textFieldHora.getText();
		GridBagConstraints gbc_textFieldHora = new GridBagConstraints();
		gbc_textFieldHora.gridwidth = 3;
		gbc_textFieldHora.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHora.fill = GridBagConstraints.BOTH;
		gbc_textFieldHora.gridx = 7;
		gbc_textFieldHora.gridy = 5;
		textFieldHora.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Utilidades.verificarDuracion(textFieldHora.getText())) {
					respaldoHora = textFieldHora.getText();
				} else if (textFieldHora.getText().equals("00:00")) {
					respaldoHora = textFieldHora.getText();
				} else {
					textFieldHora.setText(respaldoHora);
				}
			}
		});
		getContentPane().add(textFieldHora, gbc_textFieldHora);
		textFieldHora.setColumns(10);

		JLabel lblHoraLabel = new JLabel("Hora");
		GridBagConstraints gbc_lblHoraLabel = new GridBagConstraints();
		gbc_lblHoraLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoraLabel.gridx = 10;
		gbc_lblHoraLabel.gridy = 5;
		getContentPane().add(lblHoraLabel, gbc_lblHoraLabel);

		JLabel lblCostoTuristaLabel = new JLabel("Costo Turista");
		GridBagConstraints gbc_lblCostoTuristaLabel = new GridBagConstraints();
		gbc_lblCostoTuristaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoTuristaLabel.gridx = 1;
		gbc_lblCostoTuristaLabel.gridy = 7;
		getContentPane().add(lblCostoTuristaLabel, gbc_lblCostoTuristaLabel);

		textFieldCostoTurista = new JTextField();
		textFieldCostoTurista.setText("0");
		respaldoCostoTurista = textFieldCostoTurista.getText();
		GridBagConstraints gbc_textFieldCostoTurista = new GridBagConstraints();
		gbc_textFieldCostoTurista.gridwidth = 3;
		gbc_textFieldCostoTurista.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCostoTurista.fill = GridBagConstraints.BOTH;
		gbc_textFieldCostoTurista.gridx = 2;
		gbc_textFieldCostoTurista.gridy = 7;
		textFieldCostoTurista.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Utilidades.verificarCerosPrincipio(textFieldCostoTurista.getText())) {
					textFieldCostoTurista.setText(Utilidades.eliminarCerosPrincipio(textFieldCostoTurista.getText()));
					respaldoCostoTurista = textFieldCostoTurista.getText();
				} else if (Utilidades.verificarTodoCeros(textFieldCostoTurista.getText())) {
					textFieldCostoTurista.setText("0");
					respaldoCostoTurista = textFieldCostoTurista.getText();
				} else if (Utilidades.verificarDecimales(textFieldCostoTurista.getText())) {
					respaldoCostoTurista = textFieldCostoTurista.getText();
				} else {
					textFieldCostoTurista.setText(respaldoCostoTurista);
				}
			}
		});
		getContentPane().add(textFieldCostoTurista, gbc_textFieldCostoTurista);
		textFieldCostoTurista.setColumns(10);

		textFieldCostoEjecutivo = new JTextField();
		textFieldCostoEjecutivo.setText("0");
		respaldoCostoEjecutivo = textFieldCostoEjecutivo.getText();
		GridBagConstraints gbc_textFieldCostoEjecutivo = new GridBagConstraints();
		gbc_textFieldCostoEjecutivo.gridwidth = 3;
		gbc_textFieldCostoEjecutivo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCostoEjecutivo.fill = GridBagConstraints.BOTH;
		gbc_textFieldCostoEjecutivo.gridx = 7;
		gbc_textFieldCostoEjecutivo.gridy = 7;
		textFieldCostoEjecutivo.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Utilidades.verificarCerosPrincipio(textFieldCostoEjecutivo.getText())) {
					textFieldCostoEjecutivo
							.setText(Utilidades.eliminarCerosPrincipio(textFieldCostoEjecutivo.getText()));
					respaldoCostoEjecutivo = textFieldCostoEjecutivo.getText();
				} else if (Utilidades.verificarTodoCeros(textFieldCostoEjecutivo.getText())) {
					textFieldCostoEjecutivo.setText("0");
					respaldoCostoEjecutivo = textFieldCostoEjecutivo.getText();
				} else if (Utilidades.verificarDecimales(textFieldCostoEjecutivo.getText())) {
					respaldoCostoEjecutivo = textFieldCostoEjecutivo.getText();
				} else {
					textFieldCostoEjecutivo.setText(respaldoCostoEjecutivo);
				}
			}
		});
		getContentPane().add(textFieldCostoEjecutivo, gbc_textFieldCostoEjecutivo);
		textFieldCostoEjecutivo.setColumns(10);

		JLabel lblCostoEjecutivoLabel = new JLabel("Costo Ejecutivo");
		GridBagConstraints gbc_lblCostoEjecutivoLabel = new GridBagConstraints();
		gbc_lblCostoEjecutivoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoEjecutivoLabel.gridx = 10;
		gbc_lblCostoEjecutivoLabel.gridy = 7;
		getContentPane().add(lblCostoEjecutivoLabel, gbc_lblCostoEjecutivoLabel);

		JLabel lblCostoEquipajeLabel = new JLabel("Costo Equipaje");
		GridBagConstraints gbc_lblCostoEquipajeLabel = new GridBagConstraints();
		gbc_lblCostoEquipajeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoEquipajeLabel.gridx = 1;
		gbc_lblCostoEquipajeLabel.gridy = 9;
		getContentPane().add(lblCostoEquipajeLabel, gbc_lblCostoEquipajeLabel);

		textFieldCostoEquipaje = new JTextField();
		textFieldCostoEquipaje.setText("0");
		respaldoCostoEquipaje = textFieldCostoEquipaje.getText();
		GridBagConstraints gbc_textFieldCostoEquipaje = new GridBagConstraints();
		gbc_textFieldCostoEquipaje.gridwidth = 3;
		gbc_textFieldCostoEquipaje.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCostoEquipaje.fill = GridBagConstraints.BOTH;
		gbc_textFieldCostoEquipaje.gridx = 2;
		gbc_textFieldCostoEquipaje.gridy = 9;
		textFieldCostoEquipaje.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Utilidades.verificarCerosPrincipio(textFieldCostoEquipaje.getText())) {
					textFieldCostoEquipaje.setText(Utilidades.eliminarCerosPrincipio(textFieldCostoEquipaje.getText()));
					respaldoCostoEquipaje = textFieldCostoEquipaje.getText();
				} else if (Utilidades.verificarTodoCeros(textFieldCostoEquipaje.getText())) {
					textFieldCostoEquipaje.setText("0");
					respaldoCostoEquipaje = textFieldCostoEquipaje.getText();
				} else if (Utilidades.verificarDecimales(textFieldCostoEquipaje.getText())) {
					respaldoCostoEquipaje = textFieldCostoEquipaje.getText();
				} else {
					textFieldCostoEquipaje.setText(respaldoCostoEquipaje);
				}
			}
		});
		getContentPane().add(textFieldCostoEquipaje, gbc_textFieldCostoEquipaje);
		textFieldCostoEquipaje.setColumns(10);

		comboBoxCiudadOrigen = new JComboBox<>();
		GridBagConstraints gbc_comboBoxCiudadOrigen = new GridBagConstraints();
		gbc_comboBoxCiudadOrigen.gridwidth = 3;
		gbc_comboBoxCiudadOrigen.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCiudadOrigen.fill = GridBagConstraints.BOTH;
		gbc_comboBoxCiudadOrigen.gridx = 7;
		gbc_comboBoxCiudadOrigen.gridy = 9;
		getContentPane().add(comboBoxCiudadOrigen, gbc_comboBoxCiudadOrigen);

		JLabel lblCiudadOrigenLabel = new JLabel("Ciudad origen");
		GridBagConstraints gbc_lblCiudadOrigenLabel = new GridBagConstraints();
		gbc_lblCiudadOrigenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudadOrigenLabel.gridx = 10;
		gbc_lblCiudadOrigenLabel.gridy = 9;
		getContentPane().add(lblCiudadOrigenLabel, gbc_lblCiudadOrigenLabel);

		JLabel lblCiudadDestinoLabel = new JLabel("Ciudad destino");
		GridBagConstraints gbc_lblCiudadDestinoLabel = new GridBagConstraints();
		gbc_lblCiudadDestinoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudadDestinoLabel.gridx = 1;
		gbc_lblCiudadDestinoLabel.gridy = 11;
		getContentPane().add(lblCiudadDestinoLabel, gbc_lblCiudadDestinoLabel);

		comboBoxCiudadDestino = new JComboBox<>();
		GridBagConstraints gbc_comboBoxCiudadDestino = new GridBagConstraints();
		gbc_comboBoxCiudadDestino.gridwidth = 3;
		gbc_comboBoxCiudadDestino.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCiudadDestino.fill = GridBagConstraints.BOTH;
		gbc_comboBoxCiudadDestino.gridx = 2;
		gbc_comboBoxCiudadDestino.gridy = 11;
		getContentPane().add(comboBoxCiudadDestino, gbc_comboBoxCiudadDestino);

		comboBoxFechaAltaDia = new JComboBox<>();
		GridBagConstraints gbc_comboBoxFechaAltaDia = new GridBagConstraints();
		gbc_comboBoxFechaAltaDia.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAltaDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaDia.gridx = 7;
		gbc_comboBoxFechaAltaDia.gridy = 11;
		getContentPane().add(comboBoxFechaAltaDia, gbc_comboBoxFechaAltaDia);

		comboBoxFechaAltaMes = new JComboBox<>();
		for (String mes : Utilidades.Month) {
			comboBoxFechaAltaMes.addItem(mes);
		}
		comboBoxFechaAltaMes.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxFechaAltaMes = new GridBagConstraints();
		gbc_comboBoxFechaAltaMes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAltaMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaMes.gridx = 8;
		gbc_comboBoxFechaAltaMes.gridy = 11;
		comboBoxFechaAltaMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox();
			}
		});
		getContentPane().add(comboBoxFechaAltaMes, gbc_comboBoxFechaAltaMes);

		comboBoxFechaAltaAnio = new JComboBox<>();
		for (Integer anio : Utilidades.getAnios()) {
			comboBoxFechaAltaAnio.addItem(anio);
		}
		comboBoxFechaAltaAnio.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxFechaAltaAnio = new GridBagConstraints();
		gbc_comboBoxFechaAltaAnio.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAltaAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaAnio.gridx = 9;
		gbc_comboBoxFechaAltaAnio.gridy = 11;
		comboBoxFechaAltaAnio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox();
			}
		});
		getContentPane().add(comboBoxFechaAltaAnio, gbc_comboBoxFechaAltaAnio);

		JLabel lblFechaAltaLabel = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblFechaAltaLabel = new GridBagConstraints();
		gbc_lblFechaAltaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaLabel.gridx = 10;
		gbc_lblFechaAltaLabel.gridy = 11;
		getContentPane().add(lblFechaAltaLabel, gbc_lblFechaAltaLabel);

		JLabel lblCategoriasLabel = new JLabel("Categorías");
		GridBagConstraints gbc_lblCategoriasLabel = new GridBagConstraints();
		gbc_lblCategoriasLabel.gridwidth = 3;
		gbc_lblCategoriasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCategoriasLabel.gridx = 1;
		gbc_lblCategoriasLabel.gridy = 13;
		getContentPane().add(lblCategoriasLabel, gbc_lblCategoriasLabel);

		modeloLista = new DefaultListModel<>();
		inicializarCategorias();
		listCategorias = new JList<>(modeloLista);
		if (modeloLista.size() > 0) {
			listCategorias.setSelectedIndex(0);
		}

		lblDescripcionLabel = new JLabel("Descripción");
		GridBagConstraints gbc_lblDescripcionLabel = new GridBagConstraints();
		gbc_lblDescripcionLabel.gridwidth = 4;
		gbc_lblDescripcionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionLabel.gridx = 4;
		gbc_lblDescripcionLabel.gridy = 13;
		getContentPane().add(lblDescripcionLabel, gbc_lblDescripcionLabel);

		lblResumenLabel = new JLabel("Descripción Corta");
		GridBagConstraints gbc_lblResumenLabel = new GridBagConstraints();
		gbc_lblResumenLabel.gridwidth = 3;
		gbc_lblResumenLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblResumenLabel.gridx = 8;
		gbc_lblResumenLabel.gridy = 13;
		getContentPane().add(lblResumenLabel, gbc_lblResumenLabel);
		listCategorias.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		JScrollPane scrollPaneList = new JScrollPane(listCategorias);
		GridBagConstraints gbc_scrollPaneList = new GridBagConstraints();
		gbc_scrollPaneList.gridheight = 6;
		gbc_scrollPaneList.gridwidth = 3;
		gbc_scrollPaneList.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneList.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneList.gridx = 1;
		gbc_scrollPaneList.gridy = 14;
		getContentPane().add(scrollPaneList, gbc_scrollPaneList);

		btnAceptarButton = new JButton("Registrar");
		GridBagConstraints gbc_btnAceptarButton = new GridBagConstraints();
		gbc_btnAceptarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAceptarButton.gridwidth = 2;
		gbc_btnAceptarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptarButton.gridx = 3;
		gbc_btnAceptarButton.gridy = 22;
		btnAceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaRutaDeVuelo();
			}
		});

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setBorder(textFieldNombre.getBorder());
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		gbc_textAreaDescripcion.gridheight = 6;
		gbc_textAreaDescripcion.gridwidth = 4;
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescripcion.gridx = 4;
		gbc_textAreaDescripcion.gridy = 14;
		getContentPane().add(textAreaDescripcion, gbc_textAreaDescripcion);

		textAreaResumen = new JTextArea();
		textAreaResumen.setLineWrap(true);
		textAreaResumen.setBorder(textFieldNombre.getBorder());
		GridBagConstraints gbc_textAreaResumen = new GridBagConstraints();
		gbc_textAreaResumen.gridheight = 6;
		gbc_textAreaResumen.gridwidth = 3;
		gbc_textAreaResumen.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaResumen.fill = GridBagConstraints.BOTH;
		gbc_textAreaResumen.gridx = 8;
		gbc_textAreaResumen.gridy = 14;
		getContentPane().add(textAreaResumen, gbc_textAreaResumen);
		getContentPane().add(btnAceptarButton, gbc_btnAceptarButton);

		btnCancelarButton = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelarButton = new GridBagConstraints();
		gbc_btnCancelarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelarButton.gridwidth = 2;
		gbc_btnCancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelarButton.gridx = 7;
		gbc_btnCancelarButton.gridy = 22;
		getContentPane().add(btnCancelarButton, gbc_btnCancelarButton);

		agregarDiasComboBox();
		setearFechaActual();
		inicializarAerolineas();
		inicializarCiudades();

	}

	private void inicializarCiudades() {
		List<CiudadDto> ciudadesDto = ivuelo.listarCiudades();
		for (CiudadDto ciudaddto : ciudadesDto) {
			comboBoxCiudadOrigen.addItem(ciudaddto.getNombre() + " - " + ciudaddto.getPais());
			comboBoxCiudadDestino.addItem(ciudaddto.getNombre() + " - " + ciudaddto.getPais());
		}
		if (ciudadesDto.size() > 0) {
			comboBoxCiudadOrigen.setSelectedIndex(0);
			comboBoxCiudadDestino.setSelectedIndex(0);
		}
	}

	private void inicializarCategorias() {

		// Codigo de la funcion
		modeloLista.removeAllElements();
		List<CategoriaDto> categoriasDto = ivuelo.listarCategorias();
		for (CategoriaDto categoriadto : categoriasDto) {
			modeloLista.addElement(categoriadto.getNombre());
		}

	}

	private void inicializarAerolineas() {
		List<UsuarioDto> usuariosDto = iusuario.listarUsuario();
		for (UsuarioDto usuariodto : usuariosDto) {
			if (usuariodto instanceof AerolineaDto) {
				comboBoxAerolineas.addItem(usuariodto.getNickName());
			}
		}
		if (comboBoxAerolineas.getItemCount() > 0) {
			comboBoxAerolineas.setSelectedIndex(0);
		}

	}

	public JButton getCancelarButton() {
		return btnCancelarButton;
	}

	@SuppressWarnings("deprecation")
	private void setearFechaActual() {
		comboBoxFechaAltaMes.setSelectedIndex(fechaActual.getMonth());
		comboBoxFechaAltaAnio.setSelectedIndex(fechaActual.getYear() - 24);
		agregarDiasComboBox();
		comboBoxFechaAltaDia.setSelectedIndex(fechaActual.getDate() - 1);
	}

	private void agregarDiasComboBox() {
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

	private void limpiarFormulario() {
		comboBoxAerolineas.setSelectedIndex(0);

		comboBoxCiudadOrigen.setSelectedIndex(0);

		comboBoxCiudadDestino.setSelectedIndex(0);

		setearFechaActual();

		textFieldCostoEjecutivo.setText("0");

		textFieldCostoTurista.setText("0");

		textFieldCostoEquipaje.setText("0");

		textFieldHora.setText("00:00");

		textFieldNombre.setText("");

		textAreaDescripcion.setText("");

		textAreaResumen.setText("");

		listCategorias.setSelectedIndex(0);

	}

	private boolean validarFormulario() {
		if (comboBoxAerolineas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ninguna aerolinea", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxCiudadOrigen.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ninguna ciudad de origen", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxCiudadDestino.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ninguna ciudad de destino", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (listCategorias.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ninguna categoría, seleccione al menos una", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String nombre = textFieldNombre.getText();
		if  (nombre != null && nombre.matches(".*[{}\\[\\]|\\\\^`].*")) {
			JOptionPane.showMessageDialog(this, "El nombre de la ruta contiene caracteres no permitidos", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		// El chequeo de que las ciudades sean iguales esta en la logica del controlador
		// de usuario

		return true;

	}

	@SuppressWarnings("deprecation")
	private void altaRutaDeVuelo() {
		if (validarFormulario()) {
			try {
				String nombre = textFieldNombre.getText();

				String descripcion = textAreaDescripcion.getText();

				Float costoTurista = Float.parseFloat(textFieldCostoTurista.getText());

				Float costoEjecutivo = Float.parseFloat(textFieldCostoEjecutivo.getText());

				Float costoEquipajeExtra = Float.parseFloat(textFieldCostoEquipaje.getText());

				Integer dia = (Integer) comboBoxFechaAltaDia.getSelectedItem();
				Integer mes = Utilidades.MesStringToInt((String) comboBoxFechaAltaMes.getSelectedItem()) - 1;
				Integer anio = ((Integer) comboBoxFechaAltaAnio.getSelectedItem()) - 1900;
				Date fechaAlta = new Date(anio, mes, dia);
				Utilidades.actualizarFechaAlta(fechaAlta);

				AerolineaDto aerolinea = new AerolineaDto((String) comboBoxAerolineas.getSelectedItem(), null, null,
						null, null, null, null, "Contra", "Img", null, null);

				List<CategoriaDto> categorias = new ArrayList<>();
				for (int i : listCategorias.getSelectedIndices()) {
					CategoriaDto categoriaDto = new CategoriaDto(modeloLista.elementAt(i));
					categorias.add(categoriaDto);
				}

				List<String> vuelos = new ArrayList<>();
				Time hora = Utilidades.stringToTime(textFieldHora.getText());
				String[] ciudadyPaisOrigen = comboBoxCiudadOrigen.getSelectedItem().toString().split(" - ");
				String ciudadOrigenNombre = ciudadyPaisOrigen[0].trim();
				String paisOrigenNombre = ciudadyPaisOrigen[1].trim();

				CiudadDto ciudadOrigen = new CiudadDto((String) ciudadOrigenNombre, paisOrigenNombre, null, null, null,
						null);

				String[] ciudadyPaisDestino = comboBoxCiudadDestino.getSelectedItem().toString().split(" - ");
				String ciudadDestinoNombre = ciudadyPaisDestino[0].trim();
				String paisDestinoNombre = ciudadyPaisDestino[1].trim();
				CiudadDto ciudadDestino = new CiudadDto(ciudadDestinoNombre, paisDestinoNombre, null, null, null, null);
				String resumen = textAreaResumen.getText();

				RutaDeVueloDto rutaDto = new RutaDeVueloDto(nombre, descripcion, costoTurista, costoEjecutivo,
						costoEquipajeExtra, fechaAlta, aerolinea, categorias, vuelos, hora, ciudadOrigen, ciudadDestino,
						EstadoRuta.Ingresada, resumen, null, null, 0, null);
				iusuario.agregarRuta(rutaDto);
				limpiarFormulario();
				JOptionPane.showMessageDialog(this, "La Ruta de Vuelo fue registrada con exito en el sistema", "Exito",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			}

		}

	}

}
