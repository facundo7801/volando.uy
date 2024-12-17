package uy.edu.fing.volandouy.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
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
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.dto.VueloDto;

@SuppressWarnings("serial")
public class ConsultaVuelo extends JInternalFrame {

	//Controladores
	private IUsuarioController iusuario;
	private IVueloController ivuelo;
	//Componentes
	private JTextField textFieldNombre;
	private JTextField textFieldFecha;
	private JTextField textFieldDuracion;
	private JTextField textFieldAsientoTurista;
	private JTextField textFieldAsientoEjecutivo;
	private JTextField textFieldFechaAlta;
	private JComboBox<String> comboBoxAerolineas;
	private JComboBox<String> comboBoxRutas;
	private JComboBox<String> comboBoxVuelos;
	private JComboBox<String> comboBoxReservas;
	private JButton btnCancelar;
	//Variables auxiliares
	List<UsuarioDto> usuariosDto;

	public ConsultaVuelo(IUsuarioController iusuarioC, IVueloController ivueloC) {
		//Atributos y seteos previos
		this.iusuario = iusuarioC;
		this.ivuelo = ivueloC;
		usuariosDto = iusuario.listarUsuario();
		
		setBounds(100, 100, 840, 760);
		setVisible(true);
		setTitle("Consulta Vuelo");
		setClosable(true);
		setResizable(true);
		try {
			setMaximum(true);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblAerolineasLabel = new JLabel("Aerolineas");
		GridBagConstraints gbc_lblAerolineasLabel = new GridBagConstraints();
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
		    	if (comboBoxAerolineas.getSelectedIndex() != -1) {
		    		cargarComboBoxRutas((String)comboBoxAerolineas.getSelectedItem());
		    	}
		    }
		});
		getContentPane().add(comboBoxAerolineas, gbc_comboBoxAerolineas);
		
		JLabel lblRutasLabel = new JLabel("Rutas");
		GridBagConstraints gbc_lblRutasLabel = new GridBagConstraints();
		gbc_lblRutasLabel.gridwidth = 2;
		gbc_lblRutasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutasLabel.gridx = 5;
		gbc_lblRutasLabel.gridy = 5;
		getContentPane().add(lblRutasLabel, gbc_lblRutasLabel);
		
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
		    		cargarComboBoxVuelos((String)comboBoxAerolineas.getSelectedItem(), (String)comboBoxRutas.getSelectedItem());
		    	}
		    }
		});
		getContentPane().add(comboBoxRutas, gbc_comboBoxRutas);
		
		JLabel lblVuelosLabel = new JLabel("Vuelos");
		GridBagConstraints gbc_lblVuelosLabel = new GridBagConstraints();
		gbc_lblVuelosLabel.gridwidth = 2;
		gbc_lblVuelosLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblVuelosLabel.gridx = 5;
		gbc_lblVuelosLabel.gridy = 9;
		getContentPane().add(lblVuelosLabel, gbc_lblVuelosLabel);
		
		comboBoxVuelos = new JComboBox<>();
		GridBagConstraints gbc_comboBoxVuelos = new GridBagConstraints();
		gbc_comboBoxVuelos.gridwidth = 8;
		gbc_comboBoxVuelos.gridheight = 2;
		gbc_comboBoxVuelos.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxVuelos.fill = GridBagConstraints.BOTH;
		gbc_comboBoxVuelos.gridx = 2;
		gbc_comboBoxVuelos.gridy = 10;
		comboBoxVuelos.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(comboBoxAerolineas.getSelectedIndex() != -1 && comboBoxRutas.getSelectedIndex() != -1 && comboBoxVuelos.getSelectedIndex() != -1) {
		    		cargarDatos((String)comboBoxAerolineas.getSelectedItem(), (String)comboBoxRutas.getSelectedItem(), (String)comboBoxVuelos.getSelectedItem());
		    	}
		    }
		});
		getContentPane().add(comboBoxVuelos, gbc_comboBoxVuelos);
		
		JLabel lblNombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombreLabel = new GridBagConstraints();
		gbc_lblNombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreLabel.gridx = 2;
		gbc_lblNombreLabel.gridy = 13;
		getContentPane().add(lblNombreLabel, gbc_lblNombreLabel);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 3;
		gbc_textFieldNombre.gridy = 13;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);
		
		textFieldAsientoTurista = new JTextField();
		textFieldAsientoTurista.setEditable(false);
		textFieldAsientoTurista.setColumns(10);
		GridBagConstraints gbc_textFieldAsientoTurista = new GridBagConstraints();
		gbc_textFieldAsientoTurista.gridwidth = 2;
		gbc_textFieldAsientoTurista.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAsientoTurista.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAsientoTurista.gridx = 7;
		gbc_textFieldAsientoTurista.gridy = 13;
		getContentPane().add(textFieldAsientoTurista, gbc_textFieldAsientoTurista);
		
		JLabel lblAsientosTuristaLabel = new JLabel("Asientos Turista");
		GridBagConstraints gbc_lblAsientosTuristaLabel = new GridBagConstraints();
		gbc_lblAsientosTuristaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAsientosTuristaLabel.gridx = 9;
		gbc_lblAsientosTuristaLabel.gridy = 13;
		getContentPane().add(lblAsientosTuristaLabel, gbc_lblAsientosTuristaLabel);
		
		JLabel lblFechaLabel = new JLabel("Fecha Vuelo");
		GridBagConstraints gbc_lblFechaLabel = new GridBagConstraints();
		gbc_lblFechaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaLabel.gridx = 2;
		gbc_lblFechaLabel.gridy = 15;
		getContentPane().add(lblFechaLabel, gbc_lblFechaLabel);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setEditable(false);
		textFieldFecha.setColumns(10);
		GridBagConstraints gbc_textFieldFecha = new GridBagConstraints();
		gbc_textFieldFecha.gridwidth = 2;
		gbc_textFieldFecha.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFecha.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFecha.gridx = 3;
		gbc_textFieldFecha.gridy = 15;
		getContentPane().add(textFieldFecha, gbc_textFieldFecha);
		
		textFieldAsientoEjecutivo = new JTextField();
		textFieldAsientoEjecutivo.setEditable(false);
		textFieldAsientoEjecutivo.setColumns(10);
		GridBagConstraints gbc_textFieldAsientoEjecutivo = new GridBagConstraints();
		gbc_textFieldAsientoEjecutivo.gridwidth = 2;
		gbc_textFieldAsientoEjecutivo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAsientoEjecutivo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAsientoEjecutivo.gridx = 7;
		gbc_textFieldAsientoEjecutivo.gridy = 15;
		getContentPane().add(textFieldAsientoEjecutivo, gbc_textFieldAsientoEjecutivo);
		
		JLabel lblAsientosEjecutivoLabel = new JLabel("Asientos Ejecutivo");
		GridBagConstraints gbc_lblAsientosEjecutivoLabel = new GridBagConstraints();
		gbc_lblAsientosEjecutivoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAsientosEjecutivoLabel.gridx = 9;
		gbc_lblAsientosEjecutivoLabel.gridy = 15;
		getContentPane().add(lblAsientosEjecutivoLabel, gbc_lblAsientosEjecutivoLabel);
		
		JLabel lblDuracionLabel = new JLabel("Duración");
		GridBagConstraints gbc_lblDuracionLabel = new GridBagConstraints();
		gbc_lblDuracionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracionLabel.gridx = 2;
		gbc_lblDuracionLabel.gridy = 17;
		getContentPane().add(lblDuracionLabel, gbc_lblDuracionLabel);
		
		textFieldDuracion = new JTextField();
		textFieldDuracion.setEditable(false);
		textFieldDuracion.setColumns(10);
		GridBagConstraints gbc_textFieldDuracion = new GridBagConstraints();
		gbc_textFieldDuracion.gridwidth = 2;
		gbc_textFieldDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDuracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDuracion.gridx = 3;
		gbc_textFieldDuracion.gridy = 17;
		getContentPane().add(textFieldDuracion, gbc_textFieldDuracion);
		
		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		textFieldFechaAlta.setColumns(10);
		GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
		gbc_textFieldFechaAlta.gridwidth = 2;
		gbc_textFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaAlta.gridx = 7;
		gbc_textFieldFechaAlta.gridy = 17;
		getContentPane().add(textFieldFechaAlta, gbc_textFieldFechaAlta);
		
		JLabel lblFechaAltaLabel = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblFechaAltaLabel = new GridBagConstraints();
		gbc_lblFechaAltaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaLabel.gridx = 9;
		gbc_lblFechaAltaLabel.gridy = 17;
		getContentPane().add(lblFechaAltaLabel, gbc_lblFechaAltaLabel);
		
		JLabel lblReservasLabel = new JLabel("Reservas");
		GridBagConstraints gbc_lblReservasLabel = new GridBagConstraints();
		gbc_lblReservasLabel.gridwidth = 2;
		gbc_lblReservasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblReservasLabel.gridx = 5;
		gbc_lblReservasLabel.gridy = 19;
		getContentPane().add(lblReservasLabel, gbc_lblReservasLabel);
		
		comboBoxReservas = new JComboBox<>();
		GridBagConstraints gbc_comboBoxReservas = new GridBagConstraints();
		gbc_comboBoxReservas.gridwidth = 8;
		gbc_comboBoxReservas.gridheight = 2;
		gbc_comboBoxReservas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxReservas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxReservas.gridx = 2;
		gbc_comboBoxReservas.gridy = 20;
		getContentPane().add(comboBoxReservas, gbc_comboBoxReservas);
		
		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridwidth = 2;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 5;
		gbc_btnCancelar.gridy = 23;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		inicializarAerolineasRutas();
		
	}
	
	public JButton getCancelarButton() {
		return btnCancelar;
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
	
	//Se usa en ReservarVuelo
	@SuppressWarnings("deprecation")
	public void precargarCampos(String aerolinea, String ruta, String vuelo) {
		try {
			//El vuelo siempre esta en la list
			List<VueloDto> vuelosDto = ivuelo.listarVuelo(aerolinea, ruta);
			int i = 0;
			while (i < vuelosDto.size() && !vuelosDto.get(i).getNombre().equals(vuelo)) {
				i++;
			}
			VueloDto vueloDto = vuelosDto.get(i);
			textFieldAsientoEjecutivo.setEditable(false);
			textFieldAsientoEjecutivo.setText(Integer.toString(vueloDto.getCantMaxAsEjecutivo()));
			textFieldAsientoTurista.setEditable(false);
			textFieldAsientoTurista.setText(Integer.toString(vueloDto.getCantMaxAsTurista()));
			textFieldDuracion.setEditable(false);
			textFieldDuracion.setText(vueloDto.getDuracion().toString().substring(0, 4));
			textFieldFecha.setEditable(false);
			textFieldFechaAlta.setEditable(false);
			String diaFecha = Integer.toString(vueloDto.getFecha().getDate());
			String mesFecha = Utilidades.Month[vueloDto.getFecha().getMonth()];
			String anioFecha = Integer.toString(vueloDto.getFecha().getYear() + 1900);
			textFieldFecha.setText(diaFecha + " de " + mesFecha + " de " + anioFecha);
			String diaFechaAlta = Integer.toString(vueloDto.getFechaAlta().getDate());
			String mesFechaAlta = Utilidades.Month[vueloDto.getFechaAlta().getMonth()];
			String anioFechaAlta = Integer.toString(vueloDto.getFechaAlta().getYear() + 1900);
			textFieldFechaAlta.setText(diaFechaAlta + " de " + mesFechaAlta + " de " + anioFechaAlta);
			textFieldNombre.setEditable(false);
			textFieldNombre.setText(vueloDto.getNombre());
			
			comboBoxAerolineas.setEnabled(false);
			comboBoxAerolineas.removeAllItems();
			comboBoxAerolineas.addItem(aerolinea);
			comboBoxAerolineas.setSelectedIndex(0);
			
			comboBoxRutas.setEnabled(false);
			comboBoxRutas.removeAllItems();
			comboBoxRutas.addItem(ruta);
			comboBoxRutas.setSelectedIndex(0);
			
			comboBoxVuelos.setEnabled(false);
			comboBoxVuelos.removeAllItems();
			comboBoxVuelos.addItem(vueloDto.getNombre());
			comboBoxVuelos.setSelectedIndex(0);
			
			comboBoxReservas.removeAllItems();
			for (String cliente : vueloDto.getClientes()) {
				comboBoxReservas.addItem(cliente);
			}
			if (comboBoxReservas.getItemCount() > 0) {
				comboBoxReservas.setSelectedIndex(0);	
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
			cargarComboBoxVuelos(aerolinea, (String)comboBoxRutas.getSelectedItem());
		}else {
			limpiarFormularioTextField();
			comboBoxVuelos.removeAllItems();
			comboBoxReservas.removeAllItems();
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
				cargarDatos(aerolinea, ruta, (String)comboBoxVuelos.getSelectedItem());
			}else {
				comboBoxReservas.removeAllItems();
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	
	private void cargarComboBoxReservas(String aerolinea, String ruta, String vuelo) {
		try {
			comboBoxReservas.removeAllItems();
			List<VueloDto> vuelosDto = ivuelo.listarVuelo(aerolinea, ruta);
			//El vuelo existe en la lista
			int i = 0;
			while(i < vuelosDto.size() && !vuelosDto.get(i).getNombre().equals(vuelo)) {
				i++;
			}
			VueloDto vueloDto = vuelosDto.get(i);
			List<String> reservas = vueloDto.getClientes();
			for (String reserva : reservas) {
				comboBoxReservas.addItem(reserva);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void limpiarFormularioTextField() {
		textFieldNombre.setText("");
		
		textFieldAsientoEjecutivo.setText("");
		
		textFieldAsientoTurista.setText("");
		
		textFieldDuracion.setText("");
		
		textFieldFecha.setText("");
		
		textFieldFechaAlta.setText("");
	}
	
	@SuppressWarnings("deprecation")
	private void cargarDatos(String aerolinea, String ruta, String vuelo) {
		try {
			List<VueloDto> vuelosDto = ivuelo.listarVuelo(aerolinea, ruta);
			
			//El vuelo existe en la lista
			VueloDto vueloDto = null;
			for (VueloDto vueloData : vuelosDto) {
				if (vueloData.getNombre().equals(vuelo)) {
					vueloDto = vueloData;
				}
			}
			
			textFieldNombre.setText(vueloDto.getNombre());
			
			textFieldAsientoEjecutivo.setText(Integer.toString(vueloDto.getCantMaxAsEjecutivo()));
			
			textFieldAsientoTurista.setText(Integer.toString(vueloDto.getCantMaxAsTurista()));
			
			textFieldDuracion.setText(vueloDto.getDuracion().toString().substring(0, 5));
			
			String diaFecha = Integer.toString(vueloDto.getFecha().getDate());
			String mesFecha = Utilidades.Month[vueloDto.getFecha().getMonth()];
			String anioFecha = Integer.toString(vueloDto.getFecha().getYear() + 1900);
			textFieldFecha.setText(diaFecha + " de " + mesFecha + " de " + anioFecha);
			
			String diaFechaAlta = Integer.toString(vueloDto.getFechaAlta().getDate());
			String mesFechaAlta = Utilidades.Month[vueloDto.getFechaAlta().getMonth()];
			String anioFechaAlta = Integer.toString(vueloDto.getFechaAlta().getYear() + 1900);
			textFieldFechaAlta.setText(diaFechaAlta + " de " + mesFechaAlta + " de " + anioFechaAlta);
			
			cargarComboBoxReservas(aerolinea, ruta, vuelo);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}

}
