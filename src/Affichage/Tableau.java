package Affichage;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import DAO.Requetes;

public class Tableau extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 1L;
	Requetes req;
	public Tableau() {
		req = new Requetes();

	}
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (column == 5) { 
			int selectedRow = table.convertRowIndexToModel(row); 
			double quantite = Float.parseFloat(table.getModel().getValueAt(selectedRow, 5).toString()); 
			String capteur = table.getModel().getValueAt(selectedRow, 0).toString();
			
			double minQuantite = req.getSeuilMin(capteur);
			double maxQuantite = req.getSeuilMax(capteur);
			if (quantite < minQuantite || quantite > maxQuantite) { 
				cell.setBackground(Color.red); 
			}else {
				cell.setBackground(Color.white);
			}
		} 
		return cell; 
	}
}
