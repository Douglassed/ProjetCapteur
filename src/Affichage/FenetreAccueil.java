package Affichage;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.Document;

public class FenetreAccueil {
	private JFrame frame;
	private JLabel lab1;
	private JTextField  text;
	Document la;
	public FenetreAccueil() {
		JLabel connect = new JLabel("Connexion (Port) :");
		JButton ok = new JButton("OK");
		ok.setFont(new Font("OK", Font.PLAIN, ok.getFont().getSize() * 2));
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fonction(false);
			}
		});
		ok.setToolTipText("OK");
		JButton quitter = new JButton("Quitter");
		quitter.setFont(new Font("Quitter", Font.PLAIN, quitter.getFont().getSize() * 2));
		quitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fonction(true);
			}
		});
		text = new JTextField("8892",5);
		text.setFont(new Font("8892", Font.PLAIN, 30));
		lab1 = new JLabel("Bienvenue",JLabel.CENTER);
		frame = new JFrame("Page d'accueil");
		frame.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
		lab1.setFont(new Font(lab1.getFont().getName(),Font.BOLD,lab1.getFont().getSize()*5));
		connect.setFont(new Font(connect.toString(),Font.PLAIN,connect.getFont().getSize()*3));
		frame.add(lab1);
		frame.add(connect);
		frame.add(text);
		frame.add(ok);
		frame.add(quitter);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(490,400);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private void fonction(boolean close) {
		frame.dispose();
		if (!close) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new Panneau(null, null);
				}
			});
		}
	}
}
