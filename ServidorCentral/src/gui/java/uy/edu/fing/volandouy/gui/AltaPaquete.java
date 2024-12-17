package uy.edu.fing.volandouy.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyVetoException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.PaqueteDto;

@SuppressWarnings("serial")
public class AltaPaquete extends JInternalFrame {

	// Controladores
	private IPaqueteController paqueteCtrl;
	private JButton btnCancelar;
	private JComboBox<Integer> comboBoxFechaAltaDia;
	private JComboBox<String> comboBoxFechaAltaMes;
	private JComboBox<Integer> comboBoxFechaAltaAnio;
	private JTextField textFieldNombre;
	private JTextField textFieldPeriodoValidez;
	private JTextField textFieldDescuento;
	private JTextArea textAreaDescripcion;
	private Integer cantidadPeriodoAntigua = 1;
	private Integer cantidadDescuentoAntigua = 1;
	private Date fechaActual = new Date();

	public AltaPaquete(IPaqueteController paqueteCtrl) {
		this.paqueteCtrl = paqueteCtrl;
		setBounds(100, 100, 840, 760);
		setVisible(false);
		setTitle("Alta Paquete");
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
				0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblNombrePaqueteLabel = new JLabel("Nombre de Paquete");
		GridBagConstraints gbc_lblNombrePaqueteLabel = new GridBagConstraints();
		gbc_lblNombrePaqueteLabel.gridheight = 3;
		gbc_lblNombrePaqueteLabel.gridwidth = 2;
		gbc_lblNombrePaqueteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombrePaqueteLabel.gridx = 2;
		gbc_lblNombrePaqueteLabel.gridy = 4;
		getContentPane().add(lblNombrePaqueteLabel, gbc_lblNombrePaqueteLabel);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridheight = 3;
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 4;
		gbc_textFieldNombre.gridy = 4;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblDescripcionLabel = new JLabel("Descripción");
		GridBagConstraints gbc_lblDescripcionLabel = new GridBagConstraints();
		gbc_lblDescripcionLabel.gridheight = 3;
		gbc_lblDescripcionLabel.gridwidth = 2;
		gbc_lblDescripcionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionLabel.gridx = 2;
		gbc_lblDescripcionLabel.gridy = 7;
		getContentPane().add(lblDescripcionLabel, gbc_lblDescripcionLabel);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setBorder(textFieldNombre.getBorder());
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		gbc_textAreaDescripcion.gridheight = 3;
		gbc_textAreaDescripcion.gridwidth = 3;
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescripcion.gridx = 4;
		gbc_textAreaDescripcion.gridy = 7;
		getContentPane().add(textAreaDescripcion, gbc_textAreaDescripcion);

		JLabel lblPeriodoValidezLabel = new JLabel("Período de validez");
		GridBagConstraints gbc_lblPeriodoValidezLabel = new GridBagConstraints();
		gbc_lblPeriodoValidezLabel.gridheight = 2;
		gbc_lblPeriodoValidezLabel.gridwidth = 2;
		gbc_lblPeriodoValidezLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeriodoValidezLabel.gridx = 2;
		gbc_lblPeriodoValidezLabel.gridy = 10;
		getContentPane().add(lblPeriodoValidezLabel, gbc_lblPeriodoValidezLabel);

		textFieldPeriodoValidez = new JTextField();
		textFieldPeriodoValidez.setText("1");
		GridBagConstraints gbc_textFieldPeriodoValidez = new GridBagConstraints();
		gbc_textFieldPeriodoValidez.gridheight = 2;
		gbc_textFieldPeriodoValidez.gridwidth = 3;
		gbc_textFieldPeriodoValidez.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPeriodoValidez.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPeriodoValidez.gridx = 4;
		gbc_textFieldPeriodoValidez.gridy = 10;
		textFieldPeriodoValidez.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldPeriodoValidez.getText().matches("^0+\\d+")) {
					textFieldPeriodoValidez
							.setText(Utilidades.eliminarCerosPrincipio(textFieldPeriodoValidez.getText()));
					cantidadPeriodoAntigua = Integer.parseInt(textFieldPeriodoValidez.getText());
				} else if (textFieldPeriodoValidez.getText().matches("\\d+")
						&& !textFieldPeriodoValidez.getText().matches("0+")) {
					cantidadPeriodoAntigua = Integer.parseInt(textFieldPeriodoValidez.getText());
				} else {
					textFieldPeriodoValidez.setText(Integer.toString(cantidadPeriodoAntigua));
				}
			}
		});
		getContentPane().add(textFieldPeriodoValidez, gbc_textFieldPeriodoValidez);
		textFieldPeriodoValidez.setColumns(10);

		JLabel lblDescuentoLabel_1 = new JLabel("Descuento");
		GridBagConstraints gbc_lblDescuentoLabel_1 = new GridBagConstraints();
		gbc_lblDescuentoLabel_1.gridheight = 2;
		gbc_lblDescuentoLabel_1.gridwidth = 2;
		gbc_lblDescuentoLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuentoLabel_1.gridx = 2;
		gbc_lblDescuentoLabel_1.gridy = 13;
		getContentPane().add(lblDescuentoLabel_1, gbc_lblDescuentoLabel_1);

		textFieldDescuento = new JTextField();
		textFieldDescuento.setText("1");
		GridBagConstraints gbc_textFieldDescuento = new GridBagConstraints();
		gbc_textFieldDescuento.gridheight = 2;
		gbc_textFieldDescuento.gridwidth = 3;
		gbc_textFieldDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescuento.gridx = 4;
		gbc_textFieldDescuento.gridy = 13;
		textFieldDescuento.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (textFieldDescuento.getText().matches("^0+\\d+")) {
					textFieldDescuento.setText(Utilidades.eliminarCerosPrincipio(textFieldDescuento.getText()));
					cantidadDescuentoAntigua = Integer.parseInt(textFieldDescuento.getText());
				} else if (textFieldDescuento.getText().matches("\\d+")
						&& !textFieldDescuento.getText().matches("0+")) {
					cantidadDescuentoAntigua = Integer.parseInt(textFieldDescuento.getText());
				} else {
					textFieldDescuento.setText(Integer.toString(cantidadDescuentoAntigua));
				}
			}
		});
		getContentPane().add(textFieldDescuento, gbc_textFieldDescuento);
		textFieldDescuento.setColumns(10);

		JLabel lblDescuentoLabel_1_1 = new JLabel((String) null);
		GridBagConstraints gbc_lblDescuentoLabel_1_1 = new GridBagConstraints();
		gbc_lblDescuentoLabel_1_1.gridheight = 2;
		gbc_lblDescuentoLabel_1_1.gridwidth = 2;
		gbc_lblDescuentoLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuentoLabel_1_1.gridx = 2;
		gbc_lblDescuentoLabel_1_1.gridy = 14;
		getContentPane().add(lblDescuentoLabel_1_1, gbc_lblDescuentoLabel_1_1);

		JLabel lblFechaAltaLabel = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblFechaAltaLabel = new GridBagConstraints();
		gbc_lblFechaAltaLabel.gridwidth = 2;
		gbc_lblFechaAltaLabel.gridheight = 2;
		gbc_lblFechaAltaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaLabel.gridx = 2;
		gbc_lblFechaAltaLabel.gridy = 16;
		getContentPane().add(lblFechaAltaLabel, gbc_lblFechaAltaLabel);

		comboBoxFechaAltaDia = new JComboBox<>();
		GridBagConstraints gbc_comboBoxFechaAltaDia = new GridBagConstraints();
		gbc_comboBoxFechaAltaDia.gridheight = 2;
		gbc_comboBoxFechaAltaDia.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAltaDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaDia.gridx = 4;
		gbc_comboBoxFechaAltaDia.gridy = 16;
		getContentPane().add(comboBoxFechaAltaDia, gbc_comboBoxFechaAltaDia);

		JButton btnAceptarButton = new JButton("Registrar");
		GridBagConstraints gbc_btnAceptarButton = new GridBagConstraints();
		gbc_btnAceptarButton.fill = GridBagConstraints.BOTH;
		gbc_btnAceptarButton.gridwidth = 2;
		gbc_btnAceptarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptarButton.gridx = 3;
		gbc_btnAceptarButton.gridy = 19;
		btnAceptarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				altaPaquete();
			}
		});

		comboBoxFechaAltaMes = new JComboBox<>();
		for (String mes : Utilidades.Month) {
			comboBoxFechaAltaMes.addItem(mes);
		}
		comboBoxFechaAltaMes.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxFechaAltaMes = new GridBagConstraints();
		gbc_comboBoxFechaAltaMes.gridheight = 2;
		gbc_comboBoxFechaAltaMes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAltaMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaMes.gridx = 5;
		gbc_comboBoxFechaAltaMes.gridy = 16;
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
		gbc_comboBoxFechaAltaAnio.gridheight = 2;
		gbc_comboBoxFechaAltaAnio.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxFechaAltaAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxFechaAltaAnio.gridx = 6;
		gbc_comboBoxFechaAltaAnio.gridy = 16;
		comboBoxFechaAltaAnio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox();
			}
		});
		getContentPane().add(comboBoxFechaAltaAnio, gbc_comboBoxFechaAltaAnio);
		getContentPane().add(btnAceptarButton, gbc_btnAceptarButton);

		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 6;
		gbc_btnCancelar.gridy = 19;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		agregarDiasComboBox();
		setearFechaActual();
	}

	public JButton getCancelarButton() {
		return btnCancelar;
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
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldDescuento.setText("1");
		cantidadDescuentoAntigua = Integer.parseInt(textFieldDescuento.getText());
		textFieldPeriodoValidez.setText("1");
		cantidadPeriodoAntigua = Integer.parseInt(textFieldPeriodoValidez.getText());
		setearFechaActual();
	}

	@SuppressWarnings("deprecation")
	private void altaPaquete() {
		String nombre = textFieldNombre.getText();
		String descripcion = textAreaDescripcion.getText();
		Integer periodoValidez = Integer.parseInt(textFieldPeriodoValidez.getText());
		Integer descuento = Integer.parseInt(textFieldDescuento.getText());
		Integer dia = (Integer) comboBoxFechaAltaDia.getSelectedItem();
		Integer mes = Utilidades.MesStringToInt((String) comboBoxFechaAltaMes.getSelectedItem()) - 1;
		Integer anio = ((Integer) comboBoxFechaAltaAnio.getSelectedItem()) - 1900;
		Date fechaAlta = new Date(anio, mes, dia);
		Utilidades.actualizarFechaAlta(fechaAlta);
		PaqueteDto paqueteDto = new PaqueteDto(nombre, descripcion, periodoValidez, descuento, fechaAlta, 0, null,
				null, null);
		try {
			if (nombre.matches(".*[{}\\[\\]|\\\\^`].*")) {
				throw new Exception("El nombre del paquete contiene caracteres no permitidos");
			}
			paqueteCtrl.crearPaqueteDeRutaDeVuelo(paqueteDto);
			JOptionPane.showMessageDialog(this, "Paquete guardado exitosamente.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
			limpiarFormulario();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	@SuppressWarnings("deprecation")
	private void setearFechaActual() {
		comboBoxFechaAltaMes.setSelectedIndex(fechaActual.getMonth());
		comboBoxFechaAltaAnio.setSelectedIndex(fechaActual.getYear() - 24);
		agregarDiasComboBox();
		comboBoxFechaAltaDia.setSelectedIndex(fechaActual.getDate() - 1);
	}

}
