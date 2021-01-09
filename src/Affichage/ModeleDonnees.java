package Affichage;

import javax.swing.table.AbstractTableModel;


public class ModeleDonnees extends AbstractTableModel{
	private Object[][] donnees;
	private String[] titres;
	public ModeleDonnees(Object[][] donnees, String[] titres) {
		this.donnees = donnees;
		this.titres = titres;
    }
    
    @Override
    public int getColumnCount() {
        return 7;
    }
    
    @Override
    public int getRowCount() {
        return donnees.length;
    }
    
    @Override
    public Object getValueAt(final int indiceLigne, final int indiceColonne) {
        return donnees[indiceLigne][indiceColonne];
    }
    
    
    @Override
    public String getColumnName(final int indiceColonne) {
    	return titres[indiceColonne];
    }
    
    @Override
    public Class getColumnClass(final int indiceColonne) {
        return this.getValueAt(0, indiceColonne).getClass();
    }
    
    @Override
    public boolean isCellEditable(final int indiceLigne, final int indiceColonne) {
        return false;
    }

}
