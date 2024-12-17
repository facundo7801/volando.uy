package uy.edu.fing.volandouy.gui;

import java.awt.GridBagLayout;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;
import uy.edu.fing.volandouy.enumerados.EstadoRuta;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class AceptarRechazarRuta extends JInternalFrame {
	// Controllers
	private IUsuarioController iusuario;

	// Componentes
	JComboBox<String> comboBoxAerolineas;
	JComboBox<String> comboBoxRutas;
	JButton btnConfirmarButton;
	JButton btnRechazarButton;
	JButton btnCancelarButton;

	// Variables auxiliares
	List<AerolineaDto> aerolineasdto = new ArrayList<>();

	public AceptarRechazarRuta(IUsuarioController iusuarioC) {
		this.iusuario = iusuarioC;

		for (UsuarioDto usuariodto : iusuario.listarUsuario()) {
			if (usuariodto instanceof AerolineaDto) {
				aerolineasdto.add((AerolineaDto) usuariodto);
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

		JLabel lblAerolineaLabel = new JLabel("Aerolíneas");
		GridBagConstraints gbc_lblAerolineaLabel = new GridBagConstraints();
		gbc_lblAerolineaLabel.gridwidth = 2;
		gbc_lblAerolineaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblAerolineaLabel.gridx = 5;
		gbc_lblAerolineaLabel.gridy = 2;
		getContentPane().add(lblAerolineaLabel, gbc_lblAerolineaLabel);

		comboBoxAerolineas = new JComboBox<>();
		GridBagConstraints gbc_comboBoxAerolineas = new GridBagConstraints();
		gbc_comboBoxAerolineas.gridheight = 3;
		gbc_comboBoxAerolineas.gridwidth = 8;
		gbc_comboBoxAerolineas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxAerolineas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxAerolineas.gridx = 2;
		gbc_comboBoxAerolineas.gridy = 3;
		comboBoxAerolineas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refrescarRutas();
			}
		});
		getContentPane().add(comboBoxAerolineas, gbc_comboBoxAerolineas);

		JLabel lblRutasIngresadasLabel = new JLabel("Rutas en estado Ingresada");
		GridBagConstraints gbc_lblRutasIngresadasLabel = new GridBagConstraints();
		gbc_lblRutasIngresadasLabel.gridwidth = 2;
		gbc_lblRutasIngresadasLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblRutasIngresadasLabel.gridx = 5;
		gbc_lblRutasIngresadasLabel.gridy = 8;
		getContentPane().add(lblRutasIngresadasLabel, gbc_lblRutasIngresadasLabel);

		comboBoxRutas = new JComboBox<>();
		GridBagConstraints gbc_comboBoxRutas = new GridBagConstraints();
		gbc_comboBoxRutas.gridheight = 3;
		gbc_comboBoxRutas.gridwidth = 8;
		gbc_comboBoxRutas.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxRutas.fill = GridBagConstraints.BOTH;
		gbc_comboBoxRutas.gridx = 2;
		gbc_comboBoxRutas.gridy = 9;
		getContentPane().add(comboBoxRutas, gbc_comboBoxRutas);

		btnConfirmarButton = new JButton("Confirmar Ruta");
		GridBagConstraints gbc_btnConfirmarButton = new GridBagConstraints();
		gbc_btnConfirmarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnConfirmarButton.gridwidth = 2;
		gbc_btnConfirmarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnConfirmarButton.gridx = 3;
		gbc_btnConfirmarButton.gridy = 18;
		btnConfirmarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cambiarEstadoRuta(EstadoRuta.Confirmada);
			}
		});
		getContentPane().add(btnConfirmarButton, gbc_btnConfirmarButton);

		btnRechazarButton = new JButton("Rechazar Ruta");
		GridBagConstraints gbc_btnRechazarButton = new GridBagConstraints();
		gbc_btnRechazarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRechazarButton.gridwidth = 2;
		gbc_btnRechazarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnRechazarButton.gridx = 5;
		gbc_btnRechazarButton.gridy = 18;
		btnRechazarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cambiarEstadoRuta(EstadoRuta.Rechazada);
			}
		});
		getContentPane().add(btnRechazarButton, gbc_btnRechazarButton);

		btnCancelarButton = new JButton("Cancelar");
		GridBagConstraints gbc_btnCancelarButton = new GridBagConstraints();
		gbc_btnCancelarButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelarButton.gridwidth = 2;
		gbc_btnCancelarButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelarButton.gridx = 7;
		gbc_btnCancelarButton.gridy = 18;
		getContentPane().add(btnCancelarButton, gbc_btnCancelarButton);

		Inicializar();

	}

	public JButton getCancelarButton() {
		return btnCancelarButton;
	}

	private void Inicializar() {
		for (AerolineaDto aerolineadto : aerolineasdto) {
			comboBoxAerolineas.addItem(aerolineadto.getNickName());
		}

		if (comboBoxAerolineas.getItemCount() > 0) {
			comboBoxAerolineas.setSelectedIndex(0);
			refrescarRutas();
		}

	}

	// Se llama una vez que se confirma o rechaza una ruta para eliminarla del
	// comboBox
	private void refrescarRutas() {
		try {

			comboBoxRutas.removeAllItems();
			for (RutaDeVueloDto ruta : iusuario.listarRutas((String) comboBoxAerolineas.getSelectedItem())) {
				if (ruta.getEstado() == EstadoRuta.Ingresada) {
					comboBoxRutas.addItem(ruta.getNombre());
				}

			}

			if (comboBoxRutas.getItemCount() > 0) {
				comboBoxRutas.setSelectedIndex(0);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private boolean chequearFormulario() {
		if (comboBoxAerolineas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Falta seleccionar una aerolínea", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (comboBoxRutas.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Falta seleccionar una ruta", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;

	}

	private void cambiarEstadoRuta(EstadoRuta estado) {
		if (chequearFormulario()) {
			try {

				iusuario.cambiarEstadoRuta((String)comboBoxAerolineas.getSelectedItem(), (String)comboBoxRutas.getSelectedItem(), estado);
				JOptionPane.showMessageDialog(this, "El estado de la ruta fue cambiado exitosamente", "Exito",
						JOptionPane.INFORMATION_MESSAGE);
				refrescarRutas();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

}
