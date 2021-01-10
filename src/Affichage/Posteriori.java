package Affichage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import DAO.Requetes;


public class Posteriori extends JPanel{
	private static final long serialVersionUID = 1L;
	private List<String> capteurs;
	private List<String> listDate;
	SortedSet<String> set;
	

	public Posteriori(JFrame frame, TypeCapteurs type, List<String> capteursChoisis, List<String> list) {
		set = new TreeSet<String>();
		listDate = list;
		capteurs = capteursChoisis;
		JButton bGraph = new JButton("nouveau graphe");
		bGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ChoixGraph();
			}
		});
		this.setLayout(new BorderLayout());
		this.add(bGraph,BorderLayout.NORTH);
		String str = "";

		if (type != null) {
			XYSeriesCollection xy = createCategoryDataset();
			System.out.println(set);
			if (set.size() < 3 && set.size() > 0) {
				str = " [";
				for (String string : set) {
					str += string+"], [";
				}
				str = str.substring(0, str.length()-3);
			}
			JFreeChart chart = ChartFactory.createTimeSeriesChart("Graphe", "Date"+str, type.toString()+" "+ type.unite(), xy);
			ChartPanel cp = new ChartPanel(chart,true);
			this.add(cp,BorderLayout.CENTER);
		}
	}
	private XYSeriesCollection createCategoryDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		Requetes req = new Requetes();
		
		for(String capteur : capteurs) {
			if (capteur != null) {
				XYSeries series = new XYSeries(capteur);

				if (listDate != null)
					Collections.reverse(listDate);

				for(String date : listDate) {
					Map<Long, Float> map = req.getAllValOfDay(capteur, date);
					
					for(Long day : map.keySet()) {
						series.add(day, map.get(day));
						Date d = new Date(day);
						set.add(d.toString());
					}
				}
				dataset.addSeries(series);
			}
		}
		return dataset;
	}

}
