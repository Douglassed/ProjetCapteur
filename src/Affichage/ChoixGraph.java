package Affichage;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ChoixGraph {
	List<String> capteursChoisi = new ArrayList<>();
	JFrame frame = new JFrame("Choix des capteurs");
	List<String> capteurs = new ArrayList<String>();
	int cpt = 0;
	TypeCapteurs[] liste;
	int indexType;
	JLabel txt;
	TypeCapteurs type;

	public ChoixGraph() {
		liste = TypeCapteurs.values();
		indexType = JOptionPane.showOptionDialog(null, "Choisissez le type du capteur", "Choix du type", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, liste, null);
		type = liste[indexType];
		//recuperer la liste des capteurs de ce type
		capteurs.add("none");
		capteurs.add("EAC2");
		capteurs.add("EAG2");
		capteurs.add("TRK1");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
		txt = new JLabel("Choix du capteur n°"+cpt+" : ");
		txt.setFont(new Font(txt.getText(), Font.PLAIN, txt.getFont().getSize()*2));
		frame.add(txt);
		frame.add(new JLabel("(info bulle sur chaque capteur)"));
		
		frame.setSize(304,228);
		frame.setLocationRelativeTo(null);
		
		for(String s : capteurs) {
			JButton button = new JButton(s);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					fonction(s,button);
				}
			});
			button.setToolTipText(s);
			frame.add(button);
		}
		frame.setVisible(true);

	}
	
	private void fonction(String s, JButton button) {
		capteursChoisi.add(s);
		if (button.getText() != "none") {
			frame.remove(button);
		}
		//frame.setVisible(false);
		frame.remove(txt);
		cpt++;
		txt.setText("Choix du capteur n°"+cpt+" : ");
		frame.add(txt, 0);
		if (cpt > 2 ) {
			System.out.println("Type : " + liste[indexType] +"\ncapteurs : " + capteursChoisi);
			frame.dispose();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new Panneau(capteursChoisi.toString() + "\n" + type);
				}
			});
		}else
			frame.repaint();
			//frame.setVisible(true);		
	}
}
