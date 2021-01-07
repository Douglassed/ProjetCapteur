package Affichage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Posteriori extends JPanel{
	private static final long serialVersionUID = 1L;

	public Posteriori(String string, JFrame frame) {
		this.add(new JLabel(string));
		JButton bGraph = new JButton("nouveaux graphes");
		bGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new ChoixGraph();
			}
		});
		this.add(bGraph);
		if (string != null) {
			// //ChartFactory.createXYLineChart("Grahe", "Date", "Valeur", createCategoryDataset(), PlotOrientation.VERTICAL, true, true, false);
			//("Grahe", "Date", "Valeur", createCategoryDataset());
			//			CategoryPlot plot = (CategoryPlot) chart.getPlot();
			JFreeChart chart = ChartFactory.createTimeSeriesChart("Grahe", "Date", "Valeur", createCategoryDataset());


			//			ChartPanel chartPanel = new ChartPanel(chart);
			//			chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
			ChartPanel cp = new ChartPanel(chart,true);

			this.add(cp);
		}
	}
	private static XYSeriesCollection createCategoryDataset() {

		XYSeries series1 = new XYSeries("2014");
		series1.add(-60000*60, 0);
		series1.add(60000*60*23*3, 2219);

		XYSeries series2 = new XYSeries("2016");
		series2.add(-60000*60, 0);
		series2.add(1609965870000L, 1000);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);

		return dataset;
	}

}
