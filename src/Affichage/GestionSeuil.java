package Affichage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

import DAO.Requetes;

public class GestionSeuil extends JPanel{
	private JTree tree;
	private JLabel selectedLabel;
	private Requetes req = new Requetes();

	public GestionSeuil(JFrame frame) {
		JButton seuil = new JButton("Modifier seuils");
		seuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seuil seuil = new Seuil(frame);
				seuil.getSeuilMax();
				seuil.getSeuilMin();
			}
		});



		//create the root node
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

		for (String bat : req.getAllBatiments()) {
			DefaultMutableTreeNode batiment = new DefaultMutableTreeNode(bat+"                                         ");
			for (String et : req.getEtagesFromBatiment(bat)) {
				DefaultMutableTreeNode etage = new DefaultMutableTreeNode(et);
				for (String cap : req.getCapteursFromEtageAndBatiment(et, bat)) {
					DefaultMutableTreeNode capteur = new DefaultMutableTreeNode(cap);
					etage.add(capteur);
				}
				batiment.add(etage);
			}
			root.add(batiment);
		}

		//create the tree by passing in the root node
		tree = new JTree(root);
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();       

		tree.setCellRenderer(renderer);
		tree.setShowsRootHandles(false);
		tree.setRootVisible(false);
		add(new JScrollPane(tree));
		add(seuil);


		selectedLabel = new JLabel();
		add(selectedLabel);
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode.getLevel() == 3) {
					selectedLabel.setText("");
					TreeNode[] tn = selectedNode.getPath();
					for (int i = 1; i < selectedNode.getPath().length; i++) {
						System.out.println(tn[i]);
					}
				}
				repaint();
			}
		});
	}

}
