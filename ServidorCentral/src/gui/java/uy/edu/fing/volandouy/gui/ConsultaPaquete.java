package uy.edu.fing.volandouy.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.JInternalFrame;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.IVueloController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.JTextArea;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ConsultaPaquete extends JInternalFrame {

	// Controllers
	IPaqueteController ipaquete;
	IUsuarioController iusuario;
	IVueloController ivuelo;
	// Componentes
	private JPanel cardPanel;
	private CardLayout cardLayout;
	private JPanel panelConsultaPaquete;
	private JDesktopPane desktopPaneConsultaRuta;
	private ConsultaRutadeVuelo consultaRutaDeVueloFrame;

	private JComboBox<String> comboBoxPaquetes;
	private JLabel lblNombreLabel;
	private JLabel lblFechaAltaLabel;
	private JLabel lblPeriodoLabel;
	private JLabel lblCostoLabel;
	private JLabel lblDescuentoLabel;
	private JTextField textFieldNombre;
	private JTextField textFieldFechaAlta;
	private JTextField textFieldPeriodo;
	private JTextField textFieldDescuento;
	private JTextField textFieldCosto;
	private JLabel lblDescripcionLabel;
	private JTextArea textAreaDescripcion;
	private JLabel lblRutasLabel;
	private JComboBox<String> comboBoxRutas;
	private JButton btnConsultarRutaButton;
	private JButton btnCancelarButton;

	// Variables auxiliares
	List<PaqueteDto> paquetesDto;
	private JLabel lblCantidadRutasLabel;
	private JLabel lblTipoAsientoLabel;
	private JTextField textFieldCantidad;
	private JTextField textFieldTipoAsiento;
	private PaqueteDto paqueteSeleccionado = null;

	public ConsultaPaquete(IUsuarioController iusuarioC, IVueloController ivueloC, IPaqueteController ipaqueteC) {
		this.iusuario = iusuarioC;
		this.ivuelo = ivueloC;
		this.ipaquete = ipaqueteC;
		paquetesDto = ipaquete.listarPaquetes();

		// Inicializar valores del JInternalFrame
		setBounds(100, 100, 840, 760);
		setVisible(false);
		setTitle("Consulta Paquete");
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

		// Crea el card de consultaPaquete
		panelConsultaPaquete = new JPanel();
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0,
				1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		panelConsultaPaquete.setLayout(gridBagLayout);

		cardPanel.add(panelConsultaPaquete, "consultaPaquete");

		JLabel lblPaquetesLabel = new JLabel("Paquetes");
		GridBagConstraints gbc_lblPaquetesLabel = new GridBagConstraints();
		gbc_lblPaquetesLabel.gridwidth = 10;
		gbc_lblPaquetesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaquetesLabel.gridx = 1;
		gbc_lblPaquetesLabel.gridy = 1;
		panelConsultaPaquete.add(lblPaquetesLabel, gbc_lblPaquetesLabel);

		comboBoxPaquetes = new JComboBox<>();
		GridBagConstraints gbc_comboBoxPaquetes = new GridBagConstraints();
		gbc_comboBoxPaquetes.gridheight = 2;
		gbc_comboBoxPaquetes.gridwidth = 10;
		gbc_comboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquetes.fill = GridBagConstraints.BOTH;
		gbc_comboBoxPaquetes.gridx = 1;
		gbc_comboBoxPaquetes.gridy = 2;
		comboBoxPaquetes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxPaquetes.getSelectedIndex() != -1) {
					for (PaqueteDto paqueteDto : paquetesDto) {
						if (paqueteDto.getNombre().equals((String) comboBoxPaquetes.getSelectedItem())) {
							paqueteSeleccionado = paqueteDto;
						}
					}
					mostrarDatos();
					InicializarComboBoxRutas();
				} else {
					paqueteSeleccionado = null;
				}
			}
		});
		panelConsultaPaquete.add(comboBoxPaquetes, gbc_comboBoxPaquetes);

		lblNombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombreLabel = new GridBagConstraints();
		gbc_lblNombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreLabel.gridx = 1;
		gbc_lblNombreLabel.gridy = 5;
		panelConsultaPaquete.add(lblNombreLabel, gbc_lblNombreLabel);

		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 3;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 5;
		panelConsultaPaquete.add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
		gbc_textFieldFechaAlta.gridwidth = 3;
		gbc_textFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaAlta.fill = GridBagConstraints.BOTH;
		gbc_textFieldFechaAlta.gridx = 7;
		gbc_textFieldFechaAlta.gridy = 5;
		panelConsultaPaquete.add(textFieldFechaAlta, gbc_textFieldFechaAlta);
		textFieldFechaAlta.setColumns(10);

		lblFechaAltaLabel = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblFechaAltaLabel = new GridBagConstraints();
		gbc_lblFechaAltaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaLabel.gridx = 10;
		gbc_lblFechaAltaLabel.gridy = 5;
		panelConsultaPaquete.add(lblFechaAltaLabel, gbc_lblFechaAltaLabel);

		lblPeriodoLabel = new JLabel("Periodo Validez");
		GridBagConstraints gbc_lblPeriodoLabel = new GridBagConstraints();
		gbc_lblPeriodoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeriodoLabel.gridx = 1;
		gbc_lblPeriodoLabel.gridy = 7;
		panelConsultaPaquete.add(lblPeriodoLabel, gbc_lblPeriodoLabel);

		textFieldPeriodo = new JTextField();
		textFieldPeriodo.setEditable(false);
		GridBagConstraints gbc_textFieldPeriodo = new GridBagConstraints();
		gbc_textFieldPeriodo.gridwidth = 3;
		gbc_textFieldPeriodo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPeriodo.fill = GridBagConstraints.BOTH;
		gbc_textFieldPeriodo.gridx = 2;
		gbc_textFieldPeriodo.gridy = 7;
		panelConsultaPaquete.add(textFieldPeriodo, gbc_textFieldPeriodo);
		textFieldPeriodo.setColumns(10);

		textFieldCosto = new JTextField();
		textFieldCosto.setEditable(false);
		GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
		gbc_textFieldCosto.gridwidth = 3;
		gbc_textFieldCosto.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCosto.fill = GridBagConstraints.BOTH;
		gbc_textFieldCosto.gridx = 7;
		gbc_textFieldCosto.gridy = 7;
		panelConsultaPaquete.add(textFieldCosto, gbc_textFieldCosto);
		textFieldCosto.setColumns(10);

		lblCostoLabel = new JLabel("Costo");
		GridBagConstraints gbc_lblCostoLabel = new GridBagConstraints();
		gbc_lblCostoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoLabel.gridx = 10;
		gbc_lblCostoLabel.gridy = 7;
		panelConsultaPaquete.add(lblCostoLabel, gbc_lblCostoLabel);

		lblDescuentoLabel = new JLabel("Descuento");
		GridBagConstraints gbc_lblDescuentoLabel = new GridBagConstraints();
		gbc_lblDescuentoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuentoLabel.gridx = 1;
		gbc_lblDescuentoLabel.gridy = 9;
		panelConsultaPaquete.add(lblDescuentoLabel, gbc_lblDescuentoLabel);

		textFieldDescuento = new JTextField();
		textFieldDescuento.setEditable(false);
		GridBagConstraints gbc_textFieldDescuento = new GridBagConstraints();
		gbc_textFieldDescuento.gridwidth = 3;
		gbc_textFieldDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescuento.fill = GridBagConstraints.BOTH;
		gbc_textFieldDescuento.gridx = 2;
		gbc_textFieldDescuento.gridy = 9;
		panelConsultaPaquete.add(textFieldDescuento, gbc_textFieldDescuento);
		textFieldDescuento.setColumns(10);

		lblDescripcionLabel = new JLabel("Descripción");
		GridBagConstraints gbc_lblDescripcionLabel = new GridBagConstraints();
		gbc_lblDescripcionLabel.gridwidth = 10;
		gbc_lblDescripcionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionLabel.gridx = 1;
		gbc_lblDescripcionLabel.gridy = 11;
		panelConsultaPaquete.add(lblDescripcionLabel, gbc_lblDescripcionLabel);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setBorder(textFieldNombre.getBorder());
		textAreaDescripcion.setEditable(false);
		GridBagConstraints gbc_textAreaDescripcion = new GridBagConstraints();
		gbc_textAreaDescripcion.gridheight = 4;
		gbc_textAreaDescripcion.gridwidth = 10;
		gbc_textAreaDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescripcion.fill = GridBagConstraints.BOTH;
		gbc_textAreaDescripcion.gridx = 1;
		gbc_textAreaDescripcion.gridy = 12;
		panelConsultaPaquete.add(textAreaDescripcion, gbc_textAreaDescripcion);

		lblRutasLabel = new JLabel("Rutas");
		GridBagConstraints gbc_lblRutasLabel = new GridBagConstraints();
		gbc_lblRutasLabel.gridwidth = 10;
		gbc_lblRutasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutasLabel.gridx = 1;
		gbc_lblRutasLabel.gridy = 17;
		panelConsultaPaquete.add(lblRutasLabel, gbc_lblRutasLabel);

		comboBoxRutas = new JComboBox<>();
		GridBagConstraints gbc_comboBoxRutas = new GridBagConstraints();
		gbc_comboBoxRutas.gridheight = 2;
		gbc_comboBoxRutas.gridwidth = 10;
		gbc_comboBoxRutas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRutas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxRutas.gridx = 1;
		gbc_comboBoxRutas.gridy = 18;
		comboBoxRutas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBoxRutas.getSelectedIndex() != -1) {
					for (ComercializaDto comercializadto : paqueteSeleccionado.getRutas()) {
						if (comercializadto.getRuta().equals((String) comboBoxRutas.getSelectedItem())) {
							textFieldCantidad.setText(String.valueOf(comercializadto.getCantRutasDeVuelo()));
							textFieldTipoAsiento.setText(comercializadto.getTipoDeAsiento().toString());
						}

					}
				}
			}
		});
		panelConsultaPaquete.add(comboBoxRutas, gbc_comboBoxRutas);

		btnConsultarRutaButton = new JButton("Consultar Ruta");
		GridBagConstraints gbc_btnConsultarRutaButton = new GridBagConstraints();
		gbc_btnConsultarRutaButton.gridwidth = 2;
		gbc_btnConsultarRutaButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnConsultarRutaButton.gridx = 4;
		gbc_btnConsultarRutaButton.gridy = 24;
		btnConsultarRutaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBoxRutas.getSelectedIndex() != -1) {
					cardLayout.show(cardPanel, "consultaRuta");
					InicializarConsultaRuta();
					//setTitle("Consulta Ruta de Vuelo");
					/*JButton btnCerrarConsultaVuelo = consultaRutaDeVueloFrame.getCancelarButton();
					btnCerrarConsultaVuelo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							cardLayout.show(cardPanel, "consultaPaquete");
						}
					});*/
				}
			}
		});

		lblCantidadRutasLabel = new JLabel("Cantidad Rutas");
		GridBagConstraints gbc_lblCantidadRutasLabel = new GridBagConstraints();
		gbc_lblCantidadRutasLabel.anchor = GridBagConstraints.EAST;
		gbc_lblCantidadRutasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadRutasLabel.gridx = 1;
		gbc_lblCantidadRutasLabel.gridy = 21;
		panelConsultaPaquete.add(lblCantidadRutasLabel, gbc_lblCantidadRutasLabel);

		textFieldCantidad = new JTextField();
		textFieldCantidad.setEditable(false);
		GridBagConstraints gbc_textFieldCantidad = new GridBagConstraints();
		gbc_textFieldCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCantidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidad.gridx = 2;
		gbc_textFieldCantidad.gridy = 21;
		panelConsultaPaquete.add(textFieldCantidad, gbc_textFieldCantidad);
		textFieldCantidad.setColumns(10);

		textFieldTipoAsiento = new JTextField();
		textFieldTipoAsiento.setEditable(false);
		GridBagConstraints gbc_textFieldTipoAsiento = new GridBagConstraints();
		gbc_textFieldTipoAsiento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTipoAsiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTipoAsiento.gridx = 9;
		gbc_textFieldTipoAsiento.gridy = 21;
		panelConsultaPaquete.add(textFieldTipoAsiento, gbc_textFieldTipoAsiento);
		textFieldTipoAsiento.setColumns(10);

		lblTipoAsientoLabel = new JLabel("Tipo Asiento");
		GridBagConstraints gbc_lblTipoAsientoLabel = new GridBagConstraints();
		gbc_lblTipoAsientoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoAsientoLabel.gridx = 10;
		gbc_lblTipoAsientoLabel.gridy = 21;
		panelConsultaPaquete.add(lblTipoAsientoLabel, gbc_lblTipoAsientoLabel);
		panelConsultaPaquete.add(btnConsultarRutaButton, gbc_btnConsultarRutaButton);

		btnCancelarButton = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelarButton = new GridBagConstraints();
		gbc_btnCancelarButton.gridwidth = 2;
		gbc_btnCancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelarButton.gridx = 6;
		gbc_btnCancelarButton.gridy = 24;
		panelConsultaPaquete.add(btnCancelarButton, gbc_btnCancelarButton);

		// Inicializa el panel de consulta Ruta
		desktopPaneConsultaRuta = new JDesktopPane();
		desktopPaneConsultaRuta.setBackground(new Color(240, 240, 240));
		cardPanel.add(desktopPaneConsultaRuta, "consultaRuta");

		InicializarComboBoxPaquetes();

	}

	public JButton getCancelarButton() {
		return btnCancelarButton;
	}

	private void InicializarConsultaRuta() {
		consultaRutaDeVueloFrame = new ConsultaRutadeVuelo(iusuario, ivuelo);
		// Cambiar titulo
		//setTitle("Consulta Ruta de Vuelo");
		// Elimina el borde de arriba de la ventana
		consultaRutaDeVueloFrame.setBorder(null);
		((BasicInternalFrameUI) consultaRutaDeVueloFrame.getUI()).setNorthPane(null);

		consultaRutaDeVueloFrame.setBackground(new Color(240, 240, 240));
		consultaRutaDeVueloFrame.setSize(800, 600);
		consultaRutaDeVueloFrame.setVisible(true);
		consultaRutaDeVueloFrame.getCancelarButton().setText("Atras");
		consultaRutaDeVueloFrame.getCancelarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(cardPanel, "consultaPaquete");
				// Cambiar titulo
				//setTitle("Consulta Paquete");
				DestruirConsultaRuta();
			}
		});

		List<UsuarioDto> usuariosdto = iusuario.listarUsuario();
		AerolineaDto aerolineaSistema = null;
		for (UsuarioDto usuariodto : usuariosdto) {
			if (usuariodto instanceof AerolineaDto && aerolineaSistema == null) {
				AerolineaDto aerolineadto = (AerolineaDto) usuariodto;
				for (String rutaNombre : aerolineadto.getRutasDeVuelo()) {
					if (rutaNombre.equals((String) comboBoxRutas.getSelectedItem()) && aerolineaSistema == null) {
						aerolineaSistema = aerolineadto;
					}
				}
			}
		}

		consultaRutaDeVueloFrame.precargarCampos(aerolineaSistema.getNickName(),
				(String) comboBoxRutas.getSelectedItem());
		desktopPaneConsultaRuta.add(consultaRutaDeVueloFrame);
	}

	// Se llama desde el ConsultaRuta cuando se desea volver a ConsultaPaquete
	private void DestruirConsultaRuta() {
		consultaRutaDeVueloFrame.setVisible(false);
		desktopPaneConsultaRuta.remove(consultaRutaDeVueloFrame);
		consultaRutaDeVueloFrame.dispose();
	}

	private void InicializarComboBoxPaquetes() {
		for (PaqueteDto paqueteDto : paquetesDto) {
			comboBoxPaquetes.addItem(paqueteDto.getNombre());
		}
		if (comboBoxPaquetes.getItemCount() > 0) {
			comboBoxPaquetes.setSelectedIndex(0);
			mostrarDatos();
			InicializarComboBoxRutas();
		}

	}

	private void InicializarComboBoxRutas() {
		paqueteSeleccionado = null;
		for (PaqueteDto paqueteDto : paquetesDto) {
			if (paqueteDto.getNombre().equals((String) comboBoxPaquetes.getSelectedItem())) {
				paqueteSeleccionado = paqueteDto;
			}
		}

		comboBoxRutas.removeAllItems();
		for (ComercializaDto comercializadto : paqueteSeleccionado.getRutas()) {
			comboBoxRutas.addItem(comercializadto.getRuta());
		}
		if (comboBoxRutas.getItemCount() > 0) {
			comboBoxRutas.setSelectedIndex(0);
			for (ComercializaDto comercializadto : paqueteSeleccionado.getRutas()) {
				if (comercializadto.getRuta().equals((String) comboBoxRutas.getSelectedItem())) {
					textFieldCantidad.setText(String.valueOf(comercializadto.getCantRutasDeVuelo()));
					textFieldTipoAsiento.setText(comercializadto.getTipoDeAsiento().toString());
				}

			}

		}else {
			textFieldCantidad.setText("");
			textFieldTipoAsiento.setText("");
		}

	}

	private boolean validarFormulario() {
		if (comboBoxPaquetes.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ningún paquete", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	@SuppressWarnings("deprecation")
	private void mostrarDatos() {
		if (validarFormulario()) {
			PaqueteDto paqueteSeleccionado = null;
			for (PaqueteDto paqueteDto : paquetesDto) {
				if (paqueteDto.getNombre().equals((String) comboBoxPaquetes.getSelectedItem())) {
					paqueteSeleccionado = paqueteDto;
				}
			}

			textFieldNombre.setText(paqueteSeleccionado.getNombre());

			textFieldDescuento.setText(String.valueOf(paqueteSeleccionado.getDescuento()));

			textFieldCosto.setText(String.valueOf(paqueteSeleccionado.getCostoAsociado()));

			textAreaDescripcion.setText(paqueteSeleccionado.getDescripcion());

			textFieldPeriodo.setText(String.valueOf(paqueteSeleccionado.getPeriodoValidez()));

			String diaFechaAlta = Integer.toString(paqueteSeleccionado.getFechaAlta().getDate());
			String mesFechaAlta = Utilidades.Month[paqueteSeleccionado.getFechaAlta().getMonth()];
			String anioFechaAlta = Integer.toString(paqueteSeleccionado.getFechaAlta().getYear() + 1900);
			textFieldFechaAlta.setText(diaFechaAlta + " de " + mesFechaAlta + " de " + anioFechaAlta);

		}

	}

	@SuppressWarnings("deprecation")
	public void precargarCampos(String paquete) {
		PaqueteDto paqueteSistema = null;
		for (PaqueteDto paqueteDto : paquetesDto) {
			if (paqueteDto.getNombre().equals(paquete)) {
				paqueteSistema = paqueteDto;
			}
		}

		textFieldNombre.setText(paqueteSistema.getNombre());

		textFieldDescuento.setText(String.valueOf(paqueteSistema.getDescuento()));

		textFieldCosto.setText(String.valueOf(paqueteSistema.getCostoAsociado()));

		textAreaDescripcion.setText(paqueteSistema.getDescripcion());

		textFieldPeriodo.setText(String.valueOf(paqueteSistema.getPeriodoValidez()));

		String diaFechaAlta = Integer.toString(paqueteSistema.getFechaAlta().getDate());
		String mesFechaAlta = Utilidades.Month[paqueteSistema.getFechaAlta().getMonth()];
		String anioFechaAlta = Integer.toString(paqueteSistema.getFechaAlta().getYear() + 1900);
		textFieldFechaAlta.setText(diaFechaAlta + " de " + mesFechaAlta + " de " + anioFechaAlta);

		comboBoxPaquetes.setEnabled(false);
		comboBoxPaquetes.removeAllItems();
		comboBoxPaquetes.addItem(paqueteSistema.getNombre());
		comboBoxPaquetes.setSelectedIndex(0);

	}

}
