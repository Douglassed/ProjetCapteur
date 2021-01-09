package Affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
	

	public Posteriori(JFrame frame, TypeCapteurs type, List<String> capteursChoisis, List<String> list) {
		listDate = list;
		capteurs = capteursChoisis;
		JButton bGraph = new JButton("nouveau graphe");
		bGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ChoixGraph();
			}
		});
		this.add(bGraph);
		if (type != null) {
			JFreeChart chart = ChartFactory.createTimeSeriesChart("Graphe", "Date", type.toString()+" "+ type.unite(), createCategoryDataset());
			ChartPanel cp = new ChartPanel(chart,true);
			this.add(cp);
			if (listDate.size() == 1)
				this.add(new JLabel(listDate.get(0)));
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
					}
				}
				dataset.addSeries(series);
			}
		}
		return dataset;
	}

}
