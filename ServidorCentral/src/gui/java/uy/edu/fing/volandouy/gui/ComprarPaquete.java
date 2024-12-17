package uy.edu.fing.volandouy.gui;

import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;

import uy.edu.fing.volandouy.controllers.IPaqueteController;
import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.controllers.Utilidades;
import uy.edu.fing.volandouy.dto.ClienteDto;
import uy.edu.fing.volandouy.dto.CompraPaqueteDto;
import uy.edu.fing.volandouy.dto.PaqueteDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ComprarPaquete extends JInternalFrame {

	// Controladores
	private IUsuarioController iusuario;
	private IPaqueteController ipaquete;

	// Componentes
	private JComboBox<Integer> comboBoxDia;
	private JComboBox<String> comboBoxMes;
	private JComboBox<Integer> comboBoxAnio;
	private JComboBox<String> comboBoxPaquetes;
	private JComboBox<String> comboBoxClientes;
	private JButton btnComprarButton;
	private JButton btnCancelarButton;

	// Variables auxiliares
	private Date fechaActual = new Date();
	private List<PaqueteDto> paquetesSistema;
	private List<String> clientesSistema = new ArrayList<>();

	public ComprarPaquete(IUsuarioController iusuarioC, IPaqueteController ipaqueteC) {
		// Atributos y seteos previos
		this.iusuario = iusuarioC;
		this.ipaquete = ipaqueteC;
		paquetesSistema = ipaquete.listarPaquetes();
		for (UsuarioDto usuarioDto : iusuario.listarUsuario()) {
			if (usuarioDto instanceof ClienteDto) {
				clientesSistema.add(usuarioDto.getNickName());
			}
		}

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

		JLabel lblClientesLabel = new JLabel("Clientes");
		GridBagConstraints gbc_lblClientesLabel = new GridBagConstraints();
		gbc_lblClientesLabel.gridwidth = 2;
		gbc_lblClientesLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblClientesLabel.gridx = 5;
		gbc_lblClientesLabel.gridy = 5;
		getContentPane().add(lblClientesLabel, gbc_lblClientesLabel);

		comboBoxClientes = new JComboBox<>();
		GridBagConstraints gbc_comboBoxClientes = new GridBagConstraints();
		gbc_comboBoxClientes.gridheight = 2;
		gbc_comboBoxClientes.gridwidth = 10;
		gbc_comboBoxClientes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxClientes.fill = GridBagConstraints.BOTH;
		gbc_comboBoxClientes.gridx = 1;
		gbc_comboBoxClientes.gridy = 6;
		getContentPane().add(comboBoxClientes, gbc_comboBoxClientes);

		JLabel lblFechaCompraLabel = new JLabel("Fecha Compra");
		GridBagConstraints gbc_lblFechaCompraLabel = new GridBagConstraints();
		gbc_lblFechaCompraLabel.gridwidth = 2;
		gbc_lblFechaCompraLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaCompraLabel.gridx = 5;
		gbc_lblFechaCompraLabel.gridy = 9;
		getContentPane().add(lblFechaCompraLabel, gbc_lblFechaCompraLabel);

		comboBoxDia = new JComboBox<>();
		GridBagConstraints gbc_comboBoxDia = new GridBagConstraints();
		gbc_comboBoxDia.gridwidth = 2;
		gbc_comboBoxDia.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDia.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDia.gridx = 2;
		gbc_comboBoxDia.gridy = 10;
		getContentPane().add(comboBoxDia, gbc_comboBoxDia);

		comboBoxMes = new JComboBox<>();
		for (String mes : Utilidades.Month) {
			comboBoxMes.addItem(mes);
		}
		comboBoxMes.setSelectedIndex(0);
		GridBagConstraints gbc_comboBoxMes = new GridBagConstraints();
		gbc_comboBoxMes.gridwidth = 2;
		gbc_comboBoxMes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxMes.gridx = 5;
		gbc_comboBoxMes.gridy = 10;
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
		gbc_comboBoxAnio.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxAnio.gridx = 8;
		gbc_comboBoxAnio.gridy = 10;
		comboBoxAnio.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				agregarDiasComboBox();
			}
		});
		getContentPane().add(comboBoxAnio, gbc_comboBoxAnio);

		btnComprarButton = new JButton("Comprar");
		GridBagConstraints gbc_btnComprarButton = new GridBagConstraints();
		gbc_btnComprarButton.gridwidth = 2;
		gbc_btnComprarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnComprarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnComprarButton.gridx = 4;
		gbc_btnComprarButton.gridy = 15;
		btnComprarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comprarPaquete();
			}
		});
		getContentPane().add(btnComprarButton, gbc_btnComprarButton);

		btnCancelarButton = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelarButton = new GridBagConstraints();
		gbc_btnCancelarButton.gridwidth = 2;
		gbc_btnCancelarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelarButton.gridx = 6;
		gbc_btnCancelarButton.gridy = 15;
		getContentPane().add(btnCancelarButton, gbc_btnCancelarButton);
		
		InicializarComboBox();
		setearFechaActual();

	}

	public JButton getCancelarButton() {
		return btnCancelarButton;
	}
	
	private void InicializarComboBox() {
		for (PaqueteDto paqueteDto : paquetesSistema) {
			if (paqueteDto.getRutas().size() > 0) {
			comboBoxPaquetes.addItem(paqueteDto.getNombre());
			}
		}
		if (comboBoxPaquetes.getItemCount() > 0) {
			comboBoxPaquetes.setSelectedIndex(0);
		}
		
		for (String clienteNombre : clientesSistema) {
			comboBoxClientes.addItem(clienteNombre);
		}
		if (comboBoxClientes.getItemCount() > 0) {
			comboBoxClientes.setSelectedIndex(0);
			comboBoxClientes.setSelectedIndex(0);
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void setearFechaActual() {
		comboBoxMes.setSelectedIndex(fechaActual.getMonth());
		comboBoxAnio.setSelectedIndex(fechaActual.getYear() - 24);
		agregarDiasComboBox();
		comboBoxDia.setSelectedIndex(fechaActual.getDate() - 1);
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
	
	private boolean validarFormulario() {
		if (comboBoxPaquetes.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ningún paquete", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (comboBoxClientes.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "No se seleccionó ningún cliente", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		return true;
		
	}
	
	private void limpiarFormulario() {
		setearFechaActual();
		comboBoxPaquetes.setSelectedIndex(0);
		comboBoxClientes.setSelectedIndex(0);
	}
	
	@SuppressWarnings("deprecation")
	private void comprarPaquete() {
		if (validarFormulario()) {
			try {
				Integer dia = (Integer)comboBoxDia.getSelectedItem();
				Integer mes = Utilidades.MesStringToInt((String)comboBoxMes.getSelectedItem()) - 1;
				Integer anio = ((Integer)comboBoxAnio.getSelectedItem()) - 1900;
				Date fechaCompra = new Date(anio, mes, dia);
				Utilidades.actualizarFechaAlta(fechaCompra);
				
				String comprador = (String)comboBoxClientes.getSelectedItem();
				
				String paquete = (String)comboBoxPaquetes.getSelectedItem();
				
				CompraPaqueteDto compraPaquetedto = new CompraPaqueteDto(fechaCompra, null, comprador, paquete, null);
				
				ipaquete.comprarPaquete(compraPaquetedto);
				
				JOptionPane.showMessageDialog(this, "La compra del paquete fue registrada exitosamente en el sistema", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario();
				
			}catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}

}
