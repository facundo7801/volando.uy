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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.dto.VueloDto;

@SuppressWarnings("serial")
public class AltaVuelo extends JInternalFrame {

	// Controladores
	private IUsuarioController iusuario;
	private IVueloController ivuelo;
	// Componentes
	private JTextField textFieldNombre;
	private JTextField textFieldAsientoTurista;
	private JTextField textFieldAsientoEjecutivo;
	private JTextField textFieldDuracion;
	private JButton btnCancelar;
	private JComboBox<String> comboBoxAerolineas;
	private JComboBox<String> comboBoxRutas;
	private JComboBox<Integer> comboBoxFechaDia;
	private JComboBox<String> comboBoxFechaMes;
	private JComboBox<Integer> comboBoxFechaAnio;
	private JComboBox<Integer> comboBoxFechaAltaDia;
	private JComboBox<String> comboBoxFechaAltaMes;
	private JComboBox<Integer> comboBoxFechaAltaAnio;
	// Variables auxiliares
	private List<UsuarioDto> usuariosDto;
	private String respaldoDuracion;
	private String respaldoTurista;
	private String respaldoEjecutivo;
	private Date fechaActual = new Date();

	public AltaVuelo(IUsuarioController iusuarioC, IVueloController ivueloC) {
		// Atributos y seteos previos
		this.iusuario = iusuarioC;
		this.ivuelo = ivueloC;
		usuariosDto = iusuario.listarUsuario();

		// Inicializar valores del JInternalFrame
		setBounds(100, 100, 840, 760);
		setVisible(false);
		setTitle("Alta Vuelo");
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
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblAerolineasLabel = new JLabel("Aerolineas");
		GridBagConstraints gbc_lblAerolineasLabel = new GridBagConstraints();
		gbc_lblAerolineasLabel.anchor = GridBagConstraints.NORTH;
		gbc_lblAerolineasLabel.gridwidth = 2;
		gbc_lblAerolineasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAerolineasLabel.gridx = 5;
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
		comboBoxAerolineas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarComboBoxRutas((String) comboBoxAerolineas.getSelectedItem());
			}
		});
		getContentPane().add(comboBoxAerolineas, gbc_comboBoxAerolineas);

		JLabel lblRutasLabel = new JLabel("Rutas de Vuelo");
		GridBagConstraints gbc_lblRutasLabel = new GridBagConstraints();
		gbc_lblRutasLabel.gridwidth = 2;
		gbc_lblRutasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutasLabel.gridx = 5;
		gbc_lblRutasLabel.gridy = 5;
		getContentPane().add(lblRutasLabel, gbc_lblRutasLabel);

		comboBoxRutas = new JComboBox<>();
		GridBagConstraints gbc_comboBoxRutas = new GridBagConstraints();
		gbc_comboBoxRutas.gridheight = 2;
		gbc_comboBoxRutas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRutas.gridwidth = 8;
		gbc_comboBoxRutas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxRutas.gridx = 2;
		gbc_comboBoxRutas.gridy = 6;
		getContentPane().add(comboBoxRutas, gbc_comboBoxRutas);

		JLabel lblVueloLabel = new JLabel("Datos del Vuelo");
		GridBagConstraints gbc_lblVueloLabel = new GridBagConstraints();
		gbc_lblVueloLabel.gridwidth = 2;
		gbc_lblVueloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblVueloLabel.gridx = 5;
		gbc_lblVueloLabel.gridy = 9;
		getContentPane().add(lblVueloLabel, gbc_lblVueloLabel);

		JLabel lblNombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombreLabel = new GridBagConstraints();
		gbc_lblNombreLabel.gridheight = 2;
		gbc_lblNombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreLabel.gridx = 1;
		gbc_lblNombreLabel.gridy = 11;
		getContentPane().add(lblNombreLabel, gbc_lblNombreLabel);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridheight = 2;
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 11;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldAsientoTurista = new JTextField();
		textFieldAsientoTurista.setText("0");
		respaldoTurista = textFieldAsientoTurista.getText();
		textFieldAsientoTurista.setColumns(10);
		GridBagConstraints gbc_textFieldAsientoTurista = new GridBagConstraints();
		gbc_textFieldAsientoTurista.gridheight = 2;
		gbc_textFieldAsientoTurista.gridwidth = 3;
		gbc_textFieldAsientoTurista.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAsientoTurista.fill = GridBagConstraints.BOTH;
		gbc_textFieldAsientoTurista.gridx = 7;
		gbc_textFieldAsientoTurista.gridy = 11;
		getContentPane().add(textFieldAsientoTurista, gbc_textFieldAsientoTurista);
		textFieldAsientoTurista.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Utilidades.verificarNumeros(textFieldAsientoTurista.getText())) {
					respaldoTurista = textFieldAsientoTurista.getText();
				} else if (Utilidades.verificarCerosPrincipio(textFieldAsientoTurista.getText())) {
					textFieldAsientoTurista
							.setText(Utilidades.eliminarCerosPrincipio(textFieldAsientoTurista.getText()));
					respaldoTurista = textFieldAsientoTurista.getText();
				} else if (Utilidades.verificarTodoCeros(textFieldAsientoTurista.getText())) {
					textFieldAsientoTurista.setText("0");
					respaldoTurista = textFieldAsientoTurista.getText();
				} else {
					textFieldAsientoTurista.setText(respaldoTurista);
				}
			}
		});

		JLabel lblAsientosTuristaLabel = new JLabel("Asientos Turista");
		GridBagConstraints gbc_lblAsientosTuristaLabel = new GridBagConstraints();
		gbc_lblAsientosTuristaLabel.gridheight = 2;
		gbc_lblAsientosTuristaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAsientosTuristaLabel.gridx = 10;
		gbc_lblAsientosTuristaLabel.gridy = 11;
		getContentPane().add(lblAsientosTuristaLabel, gbc_lblAsientosTuristaLabel);

		JLabel lblFechaLabel = new JLabel("Fecha");
		GridBagConstraints gbc_lblFechaLabel = new GridBagConstraints();
		gbc_lblFechaLabel.gridheight = 2;
		gbc_lblFechaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaLabel.gridx = 1;
		gbc_lblFechaLabel.gridy = 14;
		getContentPane().add(lblFechaLabel, gbc_lblFechaLabel);

		comboBoxFechaDia = new JComboBox<>();
		GridBagConstraints gbc_comboBoxFechaDia = new GridBagConstraints();
		gbc_comboBoxFechaDia.gridheight = 2;
		gbc_comboBoxFechaDia.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaDia.gridx = 2;
		gbc_comboBoxFechaDia.gridy = 14;
		getContentPane().add(comboBoxFechaDia, gbc_comboBoxFechaDia);

		comboBoxFechaMes = new JComboBox<>();
		for (String mes : Utilidades.Month) {
			comboBoxFechaMes.addItem(mes);
		}
		comboBoxFechaMes.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxFechaMes = new GridBagConstraints();
		gbc_comboBoxFechaMes.gridheight = 2;
		gbc_comboBoxFechaMes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaMes.gridx = 3;
		gbc_comboBoxFechaMes.gridy = 14;
		comboBoxFechaMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox("FechaDias");
			}
		});
		getContentPane().add(comboBoxFechaMes, gbc_comboBoxFechaMes);

		comboBoxFechaAnio = new JComboBox<>();
		for (Integer anio : Utilidades.getAnios()) {
			comboBoxFechaAnio.addItem(anio);
		}
		comboBoxFechaAnio.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxFechaAnio = new GridBagConstraints();
		gbc_comboBoxFechaAnio.gridheight = 2;
		gbc_comboBoxFechaAnio.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAnio.gridx = 4;
		gbc_comboBoxFechaAnio.gridy = 14;
		comboBoxFechaAnio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox("FechaDias");
			}
		});
		getContentPane().add(comboBoxFechaAnio, gbc_comboBoxFechaAnio);

		textFieldAsientoEjecutivo = new JTextField();
		textFieldAsientoEjecutivo.setText("0");
		respaldoEjecutivo = textFieldAsientoEjecutivo.getText();
		textFieldAsientoEjecutivo.setColumns(10);
		GridBagConstraints gbc_textFieldAsientoEjecutivo = new GridBagConstraints();
		gbc_textFieldAsientoEjecutivo.gridheight = 2;
		gbc_textFieldAsientoEjecutivo.gridwidth = 3;
		gbc_textFieldAsientoEjecutivo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAsientoEjecutivo.fill = GridBagConstraints.BOTH;
		gbc_textFieldAsientoEjecutivo.gridx = 7;
		gbc_textFieldAsientoEjecutivo.gridy = 14;
		getContentPane().add(textFieldAsientoEjecutivo, gbc_textFieldAsientoEjecutivo);
		textFieldAsientoEjecutivo.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Utilidades.verificarNumeros(textFieldAsientoEjecutivo.getText())) {
					respaldoEjecutivo = textFieldAsientoEjecutivo.getText();
				} else if (Utilidades.verificarCerosPrincipio(textFieldAsientoEjecutivo.getText())) {
					textFieldAsientoEjecutivo
							.setText(Utilidades.eliminarCerosPrincipio(textFieldAsientoEjecutivo.getText()));
					respaldoEjecutivo = textFieldAsientoEjecutivo.getText();
				} else if (Utilidades.verificarTodoCeros(textFieldAsientoEjecutivo.getText())) {
					textFieldAsientoEjecutivo.setText("0");
					respaldoEjecutivo = textFieldAsientoEjecutivo.getText();
				} else {
					textFieldAsientoEjecutivo.setText(respaldoEjecutivo);
				}
			}
		});

		JLabel lblAsientosEjecutivoLabel = new JLabel("Asientos Ejecutivo");
		GridBagConstraints gbc_lblAsientosEjecutivoLabel = new GridBagConstraints();
		gbc_lblAsientosEjecutivoLabel.gridheight = 2;
		gbc_lblAsientosEjecutivoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAsientosEjecutivoLabel.gridx = 10;
		gbc_lblAsientosEjecutivoLabel.gridy = 14;
		getContentPane().add(lblAsientosEjecutivoLabel, gbc_lblAsientosEjecutivoLabel);

		JLabel lblDuracionLabel = new JLabel("Duración");
		GridBagConstraints gbc_lblDuracionLabel = new GridBagConstraints();
		gbc_lblDuracionLabel.gridheight = 2;
		gbc_lblDuracionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracionLabel.gridx = 1;
		gbc_lblDuracionLabel.gridy = 17;
		getContentPane().add(lblDuracionLabel, gbc_lblDuracionLabel);

		textFieldDuracion = new JTextField();
		textFieldDuracion.setText("00:01");
		respaldoDuracion = textFieldDuracion.getText();
		textFieldDuracion.setColumns(10);
		GridBagConstraints gbc_textFieldDuracion = new GridBagConstraints();
		gbc_textFieldDuracion.gridheight = 2;
		gbc_textFieldDuracion.gridwidth = 3;
		gbc_textFieldDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDuracion.fill = GridBagConstraints.BOTH;
		gbc_textFieldDuracion.gridx = 2;
		gbc_textFieldDuracion.gridy = 17;
		getContentPane().add(textFieldDuracion, gbc_textFieldDuracion);
		textFieldDuracion.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				if (Utilidades.verificarDuracion(textFieldDuracion.getText())) {
					respaldoDuracion = textFieldDuracion.getText();
				} else {
					textFieldDuracion.setText(respaldoDuracion);
				}
			}
		});

		comboBoxFechaAltaDia = new JComboBox<>();
		GridBagConstraints gbc_comboBoxFechaAltaDia = new GridBagConstraints();
		gbc_comboBoxFechaAltaDia.gridheight = 2;
		gbc_comboBoxFechaAltaDia.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAltaDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaDia.gridx = 7;
		gbc_comboBoxFechaAltaDia.gridy = 17;
		getContentPane().add(comboBoxFechaAltaDia, gbc_comboBoxFechaAltaDia);

		comboBoxFechaAltaMes = new JComboBox<>();
		// Agrega los meses
		for (String mes : Utilidades.Month) {
			comboBoxFechaAltaMes.addItem(mes);
		}
		comboBoxFechaAltaMes.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxFechaAltaMes = new GridBagConstraints();
		gbc_comboBoxFechaAltaMes.gridheight = 2;
		gbc_comboBoxFechaAltaMes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAltaMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaMes.gridx = 8;
		gbc_comboBoxFechaAltaMes.gridy = 17;
		comboBoxFechaAltaMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox("FechaAltaDias");
			}
		});
		getContentPane().add(comboBoxFechaAltaMes, gbc_comboBoxFechaAltaMes);

		comboBoxFechaAltaAnio = new JComboBox<>();
		for (Integer anio : Utilidades.getAnios()) {
			comboBoxFechaAltaAnio.addItem(anio);
		}
		comboBoxFechaAltaAnio.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxFechaAltaAnio = new GridBagConstraints();
		gbc_comboBoxFechaAltaAnio.gridheight = 2;
		gbc_comboBoxFechaAltaAnio.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAltaAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaAnio.gridx = 9;
		gbc_comboBoxFechaAltaAnio.gridy = 17;
		comboBoxFechaAltaAnio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox("FechaAltaDias");
			}
		});
		getContentPane().add(comboBoxFechaAltaAnio, gbc_comboBoxFechaAltaAnio);

		JLabel lblFechaAltaLabel = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblFechaAltaLabel = new GridBagConstraints();
		gbc_lblFechaAltaLabel.gridheight = 2;
		gbc_lblFechaAltaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaLabel.gridx = 10;
		gbc_lblFechaAltaLabel.gridy = 17;
		getContentPane().add(lblFechaAltaLabel, gbc_lblFechaAltaLabel);

		JButton btnAceptarButton = new JButton("Registrar");
		GridBagConstraints gbc_btnAceptarButton = new GridBagConstraints();
		gbc_btnAceptarButton.fill = GridBagConstraints.BOTH;
		gbc_btnAceptarButton.gridwidth = 2;
		gbc_btnAceptarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptarButton.gridx = 4;
		gbc_btnAceptarButton.gridy = 20;
		btnAceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaVuelo();
			}
		});
		getContentPane().add(btnAceptarButton, gbc_btnAceptarButton);

		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 20;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

		inicializarAerolineasRutas();
		// agregarDiasComboBox("FechaDias");
		// agregarDiasComboBox("FechaAltaDias");
		setearFechaActual();

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

	public JButton getCancelarButton() {
		return btnCancelar;
	}

	// Solo hace falta validar que los campos en los que se requieren un numero sean
	// efectivamente un numero y que los comboBox esten marcados
	// Retorna true si todo esta bien
	private boolean validarFormulario() {
		if (comboBoxAerolineas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Falta seleccionar una aerolinea", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxRutas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Falta seleccionar una ruta", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String nombre = textFieldNombre.getText();
		if (nombre != null && nombre.matches(".*[{}\\[\\]|\\\\^`].*")) {
			JOptionPane.showMessageDialog(this, "El nombre del vuelo contiene caracteres no permitidos", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;

	}

	private void agregarDiasComboBox(String nombre) {
		int i = 0;
		if (nombre.equals("FechaDias")) {
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

		if (nombre.equals("FechaAltaDias")) {
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
		}

	}

	@SuppressWarnings("deprecation")
	private void setearFechaActual() {
		comboBoxFechaAltaMes.setSelectedIndex(fechaActual.getMonth());
		comboBoxFechaAltaAnio.setSelectedIndex(fechaActual.getYear() - 24);
		agregarDiasComboBox("FechaAltaDias");
		comboBoxFechaAltaDia.setSelectedIndex(fechaActual.getDate() - 1);

		comboBoxFechaMes.setSelectedIndex(fechaActual.getMonth());
		comboBoxFechaAnio.setSelectedIndex(fechaActual.getYear() - 24);
		agregarDiasComboBox("FechaDias");
		comboBoxFechaDia.setSelectedIndex(fechaActual.getDate() - 1);
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		
		textFieldDuracion.setText("00:01");
		
		textFieldAsientoTurista.setText("0");
		
		textFieldAsientoEjecutivo.setText("0");
		
		comboBoxAerolineas.setSelectedIndex(0);
		
		comboBoxRutas.setSelectedIndex(0);
		
		setearFechaActual();
		
	}
	
	@SuppressWarnings("deprecation")
	private void altaVuelo() {
		if (validarFormulario()) {
			try {
				// La fecha es Date(anio - 1900, mes - 1, dia)
				String nombreVuelo = textFieldNombre.getText();

				Date fecha = new Date((Integer) comboBoxFechaAnio.getSelectedItem() - 1900,
						Utilidades.MesStringToInt((String) comboBoxFechaMes.getSelectedItem()) - 1,
						(Integer) comboBoxFechaDia.getSelectedItem());

				// Si sale un error de que la duracion es null es por esta funcion
				Time duracion = Utilidades.stringToTime(textFieldDuracion.getText());

				int cantMaxAsTurista = Integer.parseInt(textFieldAsientoTurista.getText());

				int cantMaxAsEjecutivo = Integer.parseInt(textFieldAsientoEjecutivo.getText());

				String aerolinea = (String) comboBoxAerolineas.getSelectedItem();

				String ruta = (String) comboBoxRutas.getSelectedItem();

				/* Cambio en la hora, minutos y segundos de la fecha de alta del vuelo */
				//Date fechaActualAlta = new Date();
				
				Date fechaAlta = new Date((Integer) comboBoxFechaAltaAnio.getSelectedItem() - 1900,
						Utilidades.MesStringToInt((String) comboBoxFechaAltaMes.getSelectedItem()) - 1,
						(Integer) comboBoxFechaAltaDia.getSelectedItem());

				/* Si se registra un vuelo en una fecha que no es la actual entonces se asume que la hora es valida */
				/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String fechaAltaString = dateFormat.format(fechaAlta);
				String fechaActualAltaString = dateFormat.format(fechaActualAlta);
				if (fechaAltaString.equals(fechaActualAltaString)) {
					fechaAlta.setHours(fechaActualAlta.getHours());
					fechaAlta.setMinutes(fechaActualAlta.getMinutes());
					fechaAlta.setSeconds(fechaActualAlta.getSeconds());
				}*/
				Utilidades.actualizarFechaAlta(fechaAlta);
				
				/* Cambio en la hora, minutos y segundos de la fecha del vuelo */
				RutaDeVueloDto rutadto = iusuario.obtenerRutaDeVueloPorNombre(ruta);
				if (ruta != null) {
					Time horaVuelo = rutadto.getHora();
					fecha.setHours(horaVuelo.getHours());
					fecha.setMinutes(horaVuelo.getMinutes());
					fecha.setSeconds(horaVuelo.getSeconds());
				}
				
				List<String> clientes = new ArrayList<>();

				VueloDto nuevoVuelo = new VueloDto(nombreVuelo, fecha, duracion, cantMaxAsTurista, cantMaxAsEjecutivo,
						fechaAlta, aerolinea, ruta, clientes, null);
				
				ivuelo.agregarVuelo(nuevoVuelo);

				JOptionPane.showMessageDialog(this, "El vuelo fue registrado con exito en el sistema", "Exito",
						JOptionPane.INFORMATION_MESSAGE);
				
				limpiarFormulario();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

			}

		}

	}

}
