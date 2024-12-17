package uy.edu.fing.volandouy.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import uy.edu.fing.volandouy.controllers.IUsuarioController;
import uy.edu.fing.volandouy.dto.AerolineaDto;
import uy.edu.fing.volandouy.dto.RutaDeVueloDto;
import uy.edu.fing.volandouy.dto.UsuarioDto;

import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class RutasMasVisitadas extends JInternalFrame {

	// Controladores
	private IUsuarioController usuarioCtrl;
	private JButton btnCancelar;
	private JTable table;

	List<RutaDeVueloDto> rutasDeVuelo;
	List<UsuarioDto> usuarios;
	private JLabel lblTituloLabel;
	private JScrollPane scrollPane;

	public RutasMasVisitadas(IUsuarioController usuarioCtrl) {
		this.usuarioCtrl = usuarioCtrl;
		usuarios = this.usuarioCtrl.listarUsuario();
		rutasDeVuelo = this.RutasDeVueloMasVisitadas(this.ObtenerRutasDeVuelo(usuarios));

		setBounds(100, 100, 840, 760);
		setVisible(false);
		setTitle("Rutas De Vuelo Mas Visitadas");
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
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				1.0, 1.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		String[] columns = { "#", "Ruta de vuelo", "Aerolinea", "Ciudad de origen", "Ciudad de destino", "Visitas" };

		Object[][] rutasMasVisitadas = new Object[5][6];
		for (int i = 0; i < 5; i++) {
			rutasMasVisitadas[i][0] = "-";
	 		rutasMasVisitadas[i][1] = "-";
	 		rutasMasVisitadas[i][2] = "-";
	 		rutasMasVisitadas[i][3] = "-";
	 		rutasMasVisitadas[i][4] = "-";
	 		rutasMasVisitadas[i][5] = "-";
		}
		for (int i = 0; i < rutasDeVuelo.size(); i++) {
			if (rutasDeVuelo.get(i).getVisitas() > 0) {
				rutasMasVisitadas[i][0] = i + 1;
			    rutasMasVisitadas[i][1] = rutasDeVuelo.get(i).getNombre();
			    rutasMasVisitadas[i][2] = rutasDeVuelo.get(i).getAerolinea().getNombre();
			    rutasMasVisitadas[i][3] = rutasDeVuelo.get(i).getCiudadOrigen().getNombre();
			    rutasMasVisitadas[i][4] = rutasDeVuelo.get(i).getCiudadDestino().getNombre();
			    rutasMasVisitadas[i][5] = rutasDeVuelo.get(i).getVisitas();
			}
		}
		/*Object[][] rutasMasVisitadas = {
				{ rutasDeVuelo.get(0).getNombre(), rutasDeVuelo.get(0).getAerolinea().getNombre(),
						rutasDeVuelo.get(0).getCiudadOrigen().getNombre(),
						rutasDeVuelo.get(0).getCiudadDestino().getNombre(), rutasDeVuelo.get(0).getVisitas() },
				{ rutasDeVuelo.get(1).getNombre(), rutasDeVuelo.get(1).getAerolinea().getNombre(),
						rutasDeVuelo.get(1).getCiudadOrigen().getNombre(),
						rutasDeVuelo.get(1).getCiudadDestino().getNombre(), rutasDeVuelo.get(1).getVisitas() },
				{ rutasDeVuelo.get(2).getNombre(), rutasDeVuelo.get(2).getAerolinea().getNombre(),
						rutasDeVuelo.get(2).getCiudadOrigen().getNombre(),
						rutasDeVuelo.get(2).getCiudadDestino().getNombre(), rutasDeVuelo.get(2).getVisitas() },
				{ rutasDeVuelo.get(3).getNombre(), rutasDeVuelo.get(3).getAerolinea().getNombre(),
						rutasDeVuelo.get(3).getCiudadOrigen().getNombre(),
						rutasDeVuelo.get(3).getCiudadDestino().getNombre(), rutasDeVuelo.get(3).getVisitas() },
				{ rutasDeVuelo.get(4).getNombre(), rutasDeVuelo.get(4).getAerolinea().getNombre(),
						rutasDeVuelo.get(4).getCiudadOrigen().getNombre(),
						rutasDeVuelo.get(4).getCiudadDestino().getNombre(), rutasDeVuelo.get(4).getVisitas() }, };*/
		
		table = new JTable(rutasMasVisitadas, columns);
		table.setEnabled(false);
		//table.setFillsViewportHeight(true);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_table = new GridBagConstraints();
		gbc_table.gridheight = 4;
		gbc_table.gridwidth = 10;
		gbc_table.insets = new Insets(0, 0, 5, 5);
		gbc_table.fill = GridBagConstraints.BOTH;
		gbc_table.gridx = 1;
		gbc_table.gridy = 9;
		//getContentPane().add(table, gbc_table);
				
		scrollPane = new JScrollPane(table);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 10;
		gbc_scrollPane.gridwidth = 10;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 4;
		
		lblTituloLabel = new JLabel("Rutas más visitadas");
		GridBagConstraints gbc_lblTituloLabel = new GridBagConstraints();
		gbc_lblTituloLabel.gridheight = 2;
		gbc_lblTituloLabel.gridwidth = 10;
		gbc_lblTituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblTituloLabel.gridx = 1;
		gbc_lblTituloLabel.gridy = 7;
		getContentPane().add(lblTituloLabel, gbc_lblTituloLabel);
		getContentPane().add(scrollPane, gbc_table);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < columns.length; i++) {
		    TableColumn column = table.getColumnModel().getColumn(i);
		    column.setCellRenderer(centerRenderer);
		}

		TableColumnModel columnModel = table.getColumnModel();
		for (int i = 0; i < columnModel.getColumnCount(); i++) {
		    columnModel.getColumn(i).setHeaderRenderer(centerRenderer);
		}
		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		table.setShowGrid(true);
		table.setGridColor(Color.BLACK);

		int rowHeight = 40;
        table.setRowHeight(rowHeight);

        // Calcula la altura preferida de la tabla basándose en el número de filas y su altura
        Dimension tableSize = new Dimension(table.getPreferredSize().width, rowHeight * table.getRowCount());
        table.setPreferredScrollableViewportSize(tableSize);
		
		btnCancelar = new JButton("Volver");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridheight = 4;
		gbc_btnCancelar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCancelar.gridwidth = 4;
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 16;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	public JButton getCancelarButton() {
		return btnCancelar;
	}

	private List<RutaDeVueloDto> ObtenerRutasDeVuelo(List<UsuarioDto> usuarios) {
		List<String> rutasAux = new ArrayList<>();
		List<RutaDeVueloDto> result = new ArrayList<>();
		for (UsuarioDto usuario : usuarios) {
			if (usuario instanceof AerolineaDto) {
				AerolineaDto aerolinea = (AerolineaDto) usuario;
				rutasAux = aerolinea.getRutasDeVuelo();
				for (String ruta : rutasAux) {
					result.add(usuarioCtrl.obtenerRutaDeVueloPorNombre(ruta));
				}
			}
		}

		return result;
	}

	private List<RutaDeVueloDto> RutasDeVueloMasVisitadas(List<RutaDeVueloDto> rutas) {
		List<RutaDeVueloDto> result = new ArrayList<>();

		for (int i = 0; i < 5 && i < rutas.size(); i++) {
			for (int j = 0; j < rutas.size(); j++) {
				if (i - result.size() == j) {
					result.add(rutas.get(j));
				}

				if (result.get(i).getVisitas() < rutas.get(j).getVisitas()) {
					result.remove(i);
					result.add(rutas.get(j));
				}
			}
			rutas.remove(result.get(i));
		}
		return result;
	}
}
