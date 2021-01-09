package Affichage;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

import DAO.Requetes;

public class Tableau extends DefaultTableCellRenderer{
	private static final long serialVersionUID = 1L;
	Requetes req;
	Object[][] donnees;
	public Tableau(Requetes req, Object[][] donnees) {
		this.req = req;
		this.donnees = donnees;
		

	}
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,   boolean hasFocus, int row, int column) {
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		setHorizontalAlignment(SwingConstants.RIGHT);
		setValue(donnees[row][column]);
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
		return cell; 
	}
}
