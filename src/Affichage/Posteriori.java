package Affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
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

	public Posteriori(JFrame frame, TypeCapteurs type, List<String> capteursChoisis) {
		capteurs = capteursChoisis;
		JButton bGraph = new JButton("nouveau graphe");
		bGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(frame.getPreferredSize());
				frame.dispose();
				new ChoixGraph();
			}
		});
		this.add(bGraph);
		if (type != null) {
			JFreeChart chart = ChartFactory.createTimeSeriesChart("Graphe", "Date", "Valeur", createCategoryDataset());
			ChartPanel cp = new ChartPanel(chart,true);
			this.add(cp);
		}
	}
	private XYSeriesCollection createCategoryDataset() {
		XYSeriesCollection dataset = new XYSeriesCollection();
		Requetes req = new Requetes();
		
		for(String capteur : capteurs) {
			if (capteur != null) {
				XYSeries series = new XYSeries(capteur);
				Map<Long, Float> map = req.getAllValOfDay(capteur, "2021-01-07");
				
				for(Long day : map.keySet()) {
					series.add(day, map.get(day));
				}
				dataset.addSeries(series);
			}
		}
		return dataset;
	}

}
