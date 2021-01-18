package Affichage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import DAO.Requetes;

public class GestionSeuil extends JPanel{
	private static final long serialVersionUID = 1L;

	private JTree tree;
	private Requetes req = new Requetes();
	private boolean choose = false;
	private JPanel details;
	Object[] data = {"","","","","","",""};
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private String capteur;
	DefaultMutableTreeNode root;

	public GestionSeuil(JFrame frame) {
		super(new BorderLayout());
		details = new JPanel();
		JButton seuil = new JButton("Modifier");
		seuil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choose) {
					float seuilMax = req.getSeuilMax(labels.get(0).getText());
					float seuilMin = req.getSeuilMin(labels.get(0).getText());
					Seuil seuil = new Seuil(frame, seuilMin, seuilMax, capteur, req);
					req.seuils(capteur, seuil.getSeuilMax(), seuil.getSeuilMin());
					setData(capteur);
				}else {
					JOptionPane.showMessageDialog(frame, "Veuillez choisir un capteur dans l'abre");
				}
			}
		});
		JButton refresh = new JButton("rafraichir");
		refresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				root.removeAllChildren();
				for (String bat : req.getAllBatiments()) {
					DefaultMutableTreeNode batiment = new DefaultMutableTreeNode(bat+"                                ");
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
				tree.updateUI();
				frame.repaint();
				frame.pack();
			}
		});

		root = new DefaultMutableTreeNode("Arbre de capteurs");

		for (String bat : req.getAllBatiments()) {
			DefaultMutableTreeNode batiment = new DefaultMutableTreeNode(bat+"                                   ");
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
		
		//create the tree 
		tree = new JTree(root);
		tree.setShowsRootHandles(false);
		tree.setVisibleRowCount(10);
		if (root.getChildCount() == 0)
			tree.setRootVisible(true);
		else
			tree.setRootVisible(false);
		JScrollPane scroll = new JScrollPane(tree);
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(scroll,BorderLayout.CENTER);
		panel.add(refresh,BorderLayout.SOUTH);
		add(panel,BorderLayout.WEST);
		
		
		details.setLayout(new BorderLayout());
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
		Box box = new Box(BoxLayout.X_AXIS);
		box.add(Box.createHorizontalGlue());
		box.add(new JLabel("Modifier les Seuils : "));
		box.add(new JLabel("    "));
		box.add(seuil);
		box.add(Box.createHorizontalGlue());
		Box box2 = new Box(BoxLayout.X_AXIS);
		box2.add(new JLabel(" "));
		jp.add(box);
		jp.add(box2);

		details.add(jp,BorderLayout.SOUTH);

		JPanel tableau = new JPanel();
		String[] titles = {"Nom", "Type", "Batiment", "Etage", "Lieu", "Seuil Min", "Seuil Max"};



		tableau.setLayout(new BoxLayout(tableau, BoxLayout.Y_AXIS));
		//BOX 1
		Box line1=new Box(BoxLayout.X_AXIS);
		line1.add(Box.createVerticalGlue());
		//BOX 2
		Box line2=new Box(BoxLayout.X_AXIS);
		line2.add(Box.createHorizontalGlue());
		JPanel jsp = new JPanel(new GridLayout(2, 5));

		for (String str : titles) {
			JLabel lab = new JLabel(str);
			lab.setHorizontalAlignment(JLabel.CENTER);
			lab.setBorder(BorderFactory.createLineBorder(Color.black));
			jsp.add(lab);
		}

		for (int i = 0; i < 7; i++) {
			JLabel lab = new JLabel(" ");
			labels.add(lab);
			lab.setHorizontalAlignment(JLabel.CENTER);
			lab.setBorder(BorderFactory.createLineBorder(Color.black));
			jsp.add(lab);
		}
		line2.add(jsp);
		line2.add(Box.createHorizontalGlue());
		//BOX 3
		Box line3 = new Box(BoxLayout.X_AXIS);
		line3.add(Box.createVerticalGlue());

		tableau.add(line1);
		tableau.add(line2);
		tableau.add(line3);		

		details.add(tableau,BorderLayout.CENTER);
		add(details,BorderLayout.CENTER);

		JLabel txt = new JLabel(" Panneau de gestion des capteurs");
		txt.setBorder(BorderFactory.createLineBorder(Color.black));
		txt.setFont(new Font(txt.getText(), Font.PLAIN, 20));
		add(txt,BorderLayout.NORTH);
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				frame.pack();
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if (selectedNode.getLevel() == 3) {
					choose = true;
					setData((capteur = selectedNode.getUserObject().toString()));
					frame.pack();
					frame.repaint();
				}
			}
		});
	}

	public void setData(String capteur) {
		if (choose) {
			labels.get(0).setText(capteur);
			labels.get(1).setText(req.getType(capteur));
			labels.get(2).setText(req.getBatiment(capteur));
			labels.get(3).setText(req.getEtage(capteur));
			labels.get(4).setText(req.getLieu(capteur));
			labels.get(5).setText(String.valueOf(req.getSeuilMin(capteur))+" "+TypeCapteurs.stringToUnite(req.getType(capteur)));
			labels.get(6).setText(String.valueOf(req.getSeuilMax(capteur))+" "+TypeCapteurs.stringToUnite(req.getType(capteur)));
		}

	}
}
