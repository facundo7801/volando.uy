package uy.edu.fing.volandouy.gui;

import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.ComercializaDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.enumerados.TipoAsiento;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AgregarRutaPaquete extends JInternalFrame {

	// Controladores
	private IUsuarioController iusuario;
	private IPaqueteController ipaquete;
	
	//Componentes
	private JTextField textFieldCantidad;
	private JComboBox<TipoAsiento> comboBoxTipoAsiento;
	private JComboBox<String> comboBoxPaquetes;
	private JComboBox<String> comboBoxRutas;
	private JButton btnAgregarButton;
	private JButton btnCancelarButton;
	
	//Variables auxiliares
	String respaldoCantidad;
	List<PaqueteDto> paquetesSistema;
	List<String> rutasSistema = new ArrayList<>();

	public AgregarRutaPaquete(IUsuarioController iusuarioC, IPaqueteController ipaqueteC) {
		// Atributos y seteos previos
		this.iusuario = iusuarioC;
		this.ipaquete = ipaqueteC;
		paquetesSistema = ipaquete.listarPaquetes();
		for (UsuarioDto usuarioDto : iusuario.listarUsuario()) {
			if (usuarioDto instanceof AerolineaDto) {
				AerolineaDto aerolineaDto = (AerolineaDto) usuarioDto;
				for (String rutaNombre : aerolineaDto.getRutasDeVuelo()) {
					rutasSistema.add(rutaNombre);
				}
			}
		}

		// Inicializar valores del JInternalFrame
		setBounds(100, 100, 840, 760);
		setVisible(false);
		setTitle("Agregar Ruta Paquete");
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
		
		JLabel lblPaquetesLabel = new JLabel("Paquetes");
		GridBagConstraints gbc_lblPaquetesLabel = new GridBagConstraints();
		gbc_lblPaquetesLabel.gridwidth = 2;
		gbc_lblPaquetesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaquetesLabel.gridx = 5;
		gbc_lblPaquetesLabel.gridy = 1;
		getContentPane().add(lblPaquetesLabel, gbc_lblPaquetesLabel);
		
		comboBoxPaquetes = new JComboBox<>();
		GridBagConstraints gbc_comboBoxPaquetes = new GridBagConstraints();
		gbc_comboBoxPaquetes.gridheight = 2;
		gbc_comboBoxPaquetes.gridwidth = 10;
		gbc_comboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquetes.fill = GridBagConstraints.BOTH;
		gbc_comboBoxPaquetes.gridx = 1;
		gbc_comboBoxPaquetes.gridy = 2;
		getContentPane().add(comboBoxPaquetes, gbc_comboBoxPaquetes);
		
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
		gbc_comboBoxRutas.gridwidth = 10;
		gbc_comboBoxRutas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRutas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxRutas.gridx = 1;
		gbc_comboBoxRutas.gridy = 6;
		getContentPane().add(comboBoxRutas, gbc_comboBoxRutas);
		
		JLabel lblTipoAsientoLabel = new JLabel("TipoAsiento");
		GridBagConstraints gbc_lblTipoAsientoLabel = new GridBagConstraints();
		gbc_lblTipoAsientoLabel.gridwidth = 2;
		gbc_lblTipoAsientoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoAsientoLabel.gridx = 5;
		gbc_lblTipoAsientoLabel.gridy = 9;
		getContentPane().add(lblTipoAsientoLabel, gbc_lblTipoAsientoLabel);
		
		comboBoxTipoAsiento = new JComboBox<>();
		comboBoxTipoAsiento.setModel(new DefaultComboBoxModel<TipoAsiento>(TipoAsiento.values()));
		comboBoxTipoAsiento.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxTipoAsiento = new GridBagConstraints();
		gbc_comboBoxTipoAsiento.gridwidth = 4;
		gbc_comboBoxTipoAsiento.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipoAsiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipoAsiento.gridx = 4;
		gbc_comboBoxTipoAsiento.gridy = 10;
		getContentPane().add(comboBoxTipoAsiento, gbc_comboBoxTipoAsiento);
		
		JLabel lblCantidadLabel = new JLabel("Cantidad de rutas");
		GridBagConstraints gbc_lblCantidadLabel = new GridBagConstraints();
		gbc_lblCantidadLabel.gridwidth = 2;
		gbc_lblCantidadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadLabel.gridx = 5;
		gbc_lblCantidadLabel.gridy = 12;
		getContentPane().add(lblCantidadLabel, gbc_lblCantidadLabel);
		
		textFieldCantidad = new JTextField();
		textFieldCantidad.setText("1");
		respaldoCantidad = textFieldCantidad.getText();
		GridBagConstraints gbc_textFieldCantidad = new GridBagConstraints();
		gbc_textFieldCantidad.gridwidth = 4;
		gbc_textFieldCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCantidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidad.gridx = 4;
		gbc_textFieldCantidad.gridy = 13;
		textFieldCantidad.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (Utilidades.verificarNumeros(textFieldCantidad.getText())) {
					respaldoCantidad = textFieldCantidad.getText();
				} else if (Utilidades.verificarCerosPrincipio(textFieldCantidad.getText())) {
					textFieldCantidad
							.setText(Utilidades.eliminarCerosPrincipio(textFieldCantidad.getText()));
					respaldoCantidad = textFieldCantidad.getText();
				} else {
					textFieldCantidad.setText(respaldoCantidad);
				}
			}
		});
		getContentPane().add(textFieldCantidad, gbc_textFieldCantidad);
		textFieldCantidad.setColumns(10);
		
		btnAgregarButton = new JButton("Agregar");
		GridBagConstraints gbc_btnAgregarButton = new GridBagConstraints();
		gbc_btnAgregarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAgregarButton.gridwidth = 2;
		gbc_btnAgregarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnAgregarButton.gridx = 4;
		gbc_btnAgregarButton.gridy = 17;
		btnAgregarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarRuta();
			}
		});
		getContentPane().add(btnAgregarButton, gbc_btnAgregarButton);
		
		btnCancelarButton = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelarButton = new GridBagConstraints();
		gbc_btnCancelarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelarButton.gridwidth = 2;
		gbc_btnCancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelarButton.gridx = 6;
		gbc_btnCancelarButton.gridy = 17;
		getContentPane().add(btnCancelarButton, gbc_btnCancelarButton);

		InicializarDatos();
		
	}
	
	public JButton getCancelarButton() {
		return btnCancelarButton;
	}
	
	private void InicializarDatos () {
		for (PaqueteDto paqueteDto : paquetesSistema) {
			comboBoxPaquetes.addItem(paqueteDto.getNombre());
		}
		if (comboBoxPaquetes.getItemCount() > 0) {
			comboBoxPaquetes.setSelectedIndex(0);
		}
		
		for (String rutaNombre : rutasSistema) {
			comboBoxRutas.addItem(rutaNombre);
		}
		if (comboBoxRutas.getItemCount() > 0) {
			comboBoxRutas.setSelectedIndex(0);
		}
	}
	
	private boolean validarFormulario() {
		if (comboBoxPaquetes.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ningún paquete", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (comboBoxRutas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ninguna ruta", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	private void limpiarFormulario() {
		textFieldCantidad.setText("1");
		respaldoCantidad = textFieldCantidad.getText();
		comboBoxPaquetes.setSelectedIndex(0);
		comboBoxRutas.setSelectedIndex(0);
		comboBoxTipoAsiento.setSelectedIndex(0);
	}
	
	private void agregarRuta() {
		if (validarFormulario()) {
			try {
				
				int cantRutasDeVuelo = Integer.parseInt(textFieldCantidad.getText());
				
				TipoAsiento tipoDeAsiento = (TipoAsiento)comboBoxTipoAsiento.getSelectedItem();
				
				String ruta = (String)comboBoxRutas.getSelectedItem();
				
				String paquete = (String)comboBoxPaquetes.getSelectedItem();
				
				ComercializaDto comercializaDto = new ComercializaDto(cantRutasDeVuelo, tipoDeAsiento, ruta, paquete);
				
				ipaquete.agregarRutaDeVueloAPaquete(comercializaDto);
				
				JOptionPane.showMessageDialog(this, "La ruta fue agregada con exito en el paquete", "Exito",
						JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario();
				
			}catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}

}
