package de.tud.gis.teachlets.voronoi;
import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.BevelBorder;

/**
 * class for generating the gui and adding Listeners
 * 
 * @author danielkadner
 * 
 */
public class Start_App_Voronoi extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7317991275959813609L;
	private JPopupMenu popupMenu;
	private JPanel frame;
	private JButton newButton;
	private JButton helpButton;
	private VoronoiCanvas vc;
	private Checkbox delaTri;
	private Checkbox voroDia;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				Start_App_Voronoi inst = new Start_App_Voronoi();
				frame.getContentPane().add(inst);
				((JComponent) frame.getContentPane()).setPreferredSize(inst.getSize());
				frame.pack();
				frame.setVisible(true);
				frame.setTitle("Voronoi-Triangulierung");
				frame.setSize(600, 525);
				frame.setResizable(false);
				frame.setLocation(100, 100);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	/**
	 * Create the application
	 */
	public Start_App_Voronoi() {
		super();
		{
			try {
				getContentPane().add(createContents(), BorderLayout.CENTER);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Initialize the contents of the frame
	 */
	private JPanel createContents() {

		frame = new JPanel();
		frame.setBorder(new BevelBorder(BevelBorder.LOWERED));
		frame.setLayout(null);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setName("Voronoi-Triangulierung");

		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 590, 490);
		panel_1.setLayout(new BorderLayout());
		frame.add(panel_1);

		vc = new VoronoiCanvas(panel_1);
		vc.setChoosen(false, true);
		panel_1.add(vc);

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		panel_1.add(panel_2, BorderLayout.SOUTH);

		final JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout());
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_3);

		newButton = new JButton();
		panel_3.add(newButton);
		newButton.addActionListener(new AL());
		newButton.setText("Zeichenfläche leeren");

		helpButton = new JButton();
		panel_3.add(helpButton);
		helpButton.setText("Hilfe");
		setPopupBehavior(helpButton, getPopupMenu());

		final JPanel panel_4 = new JPanel();
		panel_4.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_4, BorderLayout.NORTH);

		voroDia = new Checkbox("Voronoi Diagramm", true);
		voroDia.setBackground(Color.LIGHT_GRAY);
		voroDia.addItemListener(new IL());
		panel_4.add(voroDia);

		delaTri = new Checkbox("Delaunay Triangulation", false);
		delaTri.setBackground(Color.LIGHT_GRAY);
		delaTri.addItemListener(new IL());
		panel_4.add(delaTri);

		return frame;
	}

	private JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.setLocation(140, 180);
			popupMenu.setSize(400, 400);
			JPanel panel = new JPanel();
			panel.setBounds(30, 30, 280, 380);
			popupMenu.add(panel);

			JLabel helpText = new JLabel();
			helpText.setText("<html>Maus :<br>" + "Linksklick - Fügt einen neuen Punkt hinzu.<br>"
					+ "Rechtsklick - Löscht einen existierenden Punkt.<br>"
					+ "Linksklick und ziehen - bewegt einen vorhandenen Punkt.<br>" + "<br>" + "  <br>"
					+ "Schalter :<br>" + "Hilfe - Hilfebildschirm.<br>"
					+ "Zeichenfläche leeren - löscht alle Punkte von der Zeichenfläche.</html>");
			helpText.setBounds(10, 20, 130, 200);
			panel.add(helpText);

			JPanel panel2 = new JPanel();
			panel2.setBounds(50, 30, 280, 380);
			popupMenu.add(panel2);
			JButton zurueckButton = new JButton();
			zurueckButton.setText("Zurück");
			zurueckButton.setBounds(10, 100, 130, 200);
			panel2.add(zurueckButton);
			setPopupBehavior(zurueckButton, popupMenu);
		}
		return popupMenu;
	}

	private void setPopupBehavior(final java.awt.Component parent, final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if (menu.isVisible() == true) {
					menu.setVisible(false);
				}
				else {
					menu.setVisible(true);
				}
			}
		});
	}

	public class IL implements ItemListener {

		public void itemStateChanged(ItemEvent arg0) {
			vc.setChoosen(delaTri.getState(), voroDia.getState());
			vc.repaint();
		}

	}

	public void itemStateChanged(ItemEvent itemevent) {

	}

	/**
	 * ActionListener
	 */
	class AL implements ActionListener {
		public AL() {
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Zeichenfläche leeren")) {
				vc.initialize();
				vc.repaint();
			}
		}
	}
}