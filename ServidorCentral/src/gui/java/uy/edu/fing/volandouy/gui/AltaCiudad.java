package uy.edu.fing.volandouy.gui;

import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import java.util.Date;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.CiudadDto;

import javax.swing.JComboBox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AltaCiudad extends JInternalFrame {
	//Controllers
	private IVueloController controladorVuelo;
	//Componentes
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JTextField textFieldNombre;
	private JTextField textFieldPais;
	private JTextField textFieldNombreAeropuerto;
	private JTextField textFieldWebsite;
	private JTextArea textAreaDescripcion;
	private JComboBox<Integer> comboBoxDia;
	private JComboBox<String> comboBoxMes;
	private JComboBox<Integer> comboBoxAnio;
	//Variables auxiliares
	private Date fechaActual = new Date();

	public AltaCiudad(IVueloController controladorVuelo) {
		this.controladorVuelo = controladorVuelo;
		// Inicializar valores del JInternalFrame
		setBounds(100, 100, 840, 760);
		setVisible(false);
		setTitle("Alta Ciudad");
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
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombreLabel = new GridBagConstraints();
		gbc_lblNombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreLabel.gridx = 2;
		gbc_lblNombreLabel.gridy = 2;
		getContentPane().add(lblNombreLabel, gbc_lblNombreLabel);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 7;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 3;
		gbc_textFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblPaisLabel = new JLabel("País");
		GridBagConstraints gbc_lblPaisLabel = new GridBagConstraints();
		gbc_lblPaisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaisLabel.gridx = 2;
		gbc_lblPaisLabel.gridy = 4;
		getContentPane().add(lblPaisLabel, gbc_lblPaisLabel);

		textFieldPais = new JTextField();
		GridBagConstraints gbc_textFieldPais = new GridBagConstraints();
		gbc_textFieldPais.gridwidth = 7;
		gbc_textFieldPais.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPais.fill = GridBagConstraints.BOTH;
		gbc_textFieldPais.gridx = 3;
		gbc_textFieldPais.gridy = 4;
		getContentPane().add(textFieldPais, gbc_textFieldPais);
		textFieldPais.setColumns(10);

		JLabel lblAeropuertoLabel = new JLabel("Aeropuerto");
		GridBagConstraints gbc_lblAeropuertoLabel = new GridBagConstraints();
		gbc_lblAeropuertoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAeropuertoLabel.gridx = 2;
		gbc_lblAeropuertoLabel.gridy = 6;
		getContentPane().add(lblAeropuertoLabel, gbc_lblAeropuertoLabel);

		textFieldNombreAeropuerto = new JTextField();
		GridBagConstraints gbc_textFieldNombreAeropuerto = new GridBagConstraints();
		gbc_textFieldNombreAeropuerto.gridwidth = 7;
		gbc_textFieldNombreAeropuerto.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreAeropuerto.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombreAeropuerto.gridx = 3;
		gbc_textFieldNombreAeropuerto.gridy = 6;
		getContentPane().add(textFieldNombreAeropuerto, gbc_textFieldNombreAeropuerto);
		textFieldNombreAeropuerto.setColumns(10);

		JLabel lblDescripcionLabel = new JLabel("Descripción");
		GridBagConstraints gbc_lblDescripcionLabel = new GridBagConstraints();
		gbc_lblDescripcionLabel.gridwidth = 8;
		gbc_lblDescripcionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionLabel.gridx = 2;
		gbc_lblDescripcionLabel.gridy = 8;
		getContentPane().add(lblDescripcionLabel, gbc_lblDescripcionLabel);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setBorder(textFieldNombre.getBorder());
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		gbc_textAreaDescripcion.gridwidth = 8;
		gbc_textAreaDescripcion.gridheight = 7;
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescripcion.gridx = 2;
		gbc_textAreaDescripcion.gridy = 9;
		getContentPane().add(textAreaDescripcion, gbc_textAreaDescripcion);

		JLabel lblWebsiteLabel = new JLabel("Website");
		GridBagConstraints gbc_lblWebsiteLabel = new GridBagConstraints();
		gbc_lblWebsiteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblWebsiteLabel.gridx = 2;
		gbc_lblWebsiteLabel.gridy = 17;
		getContentPane().add(lblWebsiteLabel, gbc_lblWebsiteLabel);

		textFieldWebsite = new JTextField();
		GridBagConstraints gbc_textFieldWebsite = new GridBagConstraints();
		gbc_textFieldWebsite.gridwidth = 7;
		gbc_textFieldWebsite.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldWebsite.fill = GridBagConstraints.BOTH;
		gbc_textFieldWebsite.gridx = 3;
		gbc_textFieldWebsite.gridy = 17;
		getContentPane().add(textFieldWebsite, gbc_textFieldWebsite);
		textFieldWebsite.setColumns(10);

		JLabel lblFechaAltaLabel = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblFechaAltaLabel = new GridBagConstraints();
		gbc_lblFechaAltaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaLabel.gridx = 2;
		gbc_lblFechaAltaLabel.gridy = 19;
		getContentPane().add(lblFechaAltaLabel, gbc_lblFechaAltaLabel);

		comboBoxDia = new JComboBox<>();
		GridBagConstraints gbc_comboBoxDia = new GridBagConstraints();
		gbc_comboBoxDia.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDia.fill = GridBagConstraints.BOTH;
		gbc_comboBoxDia.gridx = 3;
		gbc_comboBoxDia.gridy = 19;
		getContentPane().add(comboBoxDia, gbc_comboBoxDia);

		comboBoxMes = new JComboBox<>();
		for (String mes : Utilidades.Month) {
			comboBoxMes.addItem(mes);
		}
		comboBoxMes.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxMes = new GridBagConstraints();
		gbc_comboBoxMes.gridwidth = 2;
		gbc_comboBoxMes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMes.fill = GridBagConstraints.BOTH;
		gbc_comboBoxMes.gridx = 5;
		gbc_comboBoxMes.gridy = 19;
		comboBoxMes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox();
			}
		});
		getContentPane().add(comboBoxMes, gbc_comboBoxMes);

		comboBoxAnio = new JComboBox<>();
		for (Integer anio : Utilidades.getAnios()) {
			comboBoxAnio.addItem(anio);
		}
		comboBoxAnio.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxAnio = new GridBagConstraints();
		gbc_comboBoxAnio.gridwidth = 2;
		gbc_comboBoxAnio.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAnio.fill = GridBagConstraints.BOTH;
		gbc_comboBoxAnio.gridx = 8;
		gbc_comboBoxAnio.gridy = 19;
		comboBoxAnio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox();
			}
		});
		getContentPane().add(comboBoxAnio, gbc_comboBoxAnio);

		btnGuardar = new JButton("Registrar");
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnGuardar.gridwidth = 2;
		gbc_btnGuardar.insets = new Insets(0, 0, 5, 5);
		gbc_btnGuardar.gridx = 3;
		gbc_btnGuardar.gridy = 22;
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegistrarCiudad();
			}
		});
		getContentPane().add(btnGuardar, gbc_btnGuardar);

		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 7;
		gbc_btnCancelar.gridy = 22;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		//agregarDiasComboBox();
		setearFechaActual();
		
	}

	public JButton getCancelarButton() {
		return btnCancelar;
	}

	private boolean validarCampos() {
		if (textFieldNombre.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (textFieldPais.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "El país es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (textFieldNombreAeropuerto.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "El aeropuerto es obligatorio.", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if(textAreaDescripcion.getText().isBlank()) {
			JOptionPane.showMessageDialog(this, "La descripcion es obligatoria", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	private void setearFechaActual() {
		comboBoxMes.setSelectedIndex(fechaActual.getMonth());
		comboBoxAnio.setSelectedIndex(fechaActual.getYear() - 24);
		agregarDiasComboBox();
		comboBoxDia.setSelectedIndex(fechaActual.getDate() - 1);
	}
	
	private void limpiarCampos() {
		textFieldNombre.setText("");
		textFieldPais.setText("");
		textFieldNombreAeropuerto.setText("");
		textAreaDescripcion.setText("");
		textFieldWebsite.setText("");
		setearFechaActual();
	}

	@SuppressWarnings("deprecation")
	private void RegistrarCiudad() {
		if (validarCampos()) {
			String nombre = textFieldNombre.getText();
			String pais = textFieldPais.getText();
			String aeropuerto = textFieldNombreAeropuerto.getText();
			String descripcion = textAreaDescripcion.getText();
			String website = textFieldWebsite.getText();
			Integer dia = (Integer)comboBoxDia.getSelectedItem();
			Integer mes = Utilidades.MesStringToInt((String)comboBoxMes.getSelectedItem()) - 1;
			Integer anio = ((Integer)comboBoxAnio.getSelectedItem()) - 1900;
			Date fechaAlta = new Date(anio, mes, dia);
			Utilidades.actualizarFechaAlta(fechaAlta);
			
			CiudadDto nuevaCiudad = new CiudadDto(nombre, pais, aeropuerto, descripcion, website, fechaAlta);
			try {
				controladorVuelo.agregarCiudad(nuevaCiudad);
				JOptionPane.showMessageDialog(this, "La Ciudad fue registrada exitosamente en el sistema", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				limpiarCampos();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void agregarDiasComboBox() {
		int i = 0;
		if (comboBoxDia.getItemCount() > 0) {
			i = comboBoxDia.getSelectedIndex();
			comboBoxDia.removeAllItems();
		}
		Integer mes = Utilidades.MesStringToInt((String) comboBoxMes.getSelectedItem());
		Integer anio = (Integer) comboBoxAnio.getSelectedIndex();
		for (Integer dia : Utilidades.getDias(mes, anio)) {
			comboBoxDia.addItem(dia);
		}

		if (i < comboBoxDia.getItemCount()) {
			comboBoxDia.setSelectedIndex(i);
		} else {
			comboBoxDia.setSelectedIndex(0);
		}

	}

}
