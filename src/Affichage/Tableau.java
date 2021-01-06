package Affichage;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class Tableau extends DefaultTableCellRenderer{

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		int selectedRow = table.convertRowIndexToModel(row); 
		if (table.getModel().getValueAt(selectedRow, 5) != null && table.getModel().getValueAt(selectedRow, 5) != null) { 
			double quantite = Double.parseDouble(table.getModel().getValueAt(selectedRow, 5).toString()); 
			String capteur = table.getModel().getValueAt(selectedRow, 0).toString();
			System.out.println(capteur);
			//demande min max capteur
			double minQuantite = 0;
			double maxQuantite = 100;
			if (quantite < minQuantite || quantite > maxQuantite) { 
				cell.setBackground(Color.red); 
			}else {
				cell.setBackground(Color.white);
			}
		} 
		return cell; 
	}
}
