package de.tud.gis.teachlets.runlengthcoding;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * class for generating the gui and adding Listeners
 * 
 * @author danielkadner
 * 
 */
public class Start_App_RLC extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6668326337684791173L;
	@SuppressWarnings("rawtypes")
	private JComboBox exampleComboBox;
	private JTextArea rlcVPane;
	private JTextArea rlcPane;
	private JTextArea outputPane;
	private JPopupMenu popupMenu;
	private JPanel frame;
	private JPanel panel;
	@SuppressWarnings("unused")
	private CanvasPanel cp;
	private JTabbedPane tabbedPane;
	private JScrollPane ta_scrollPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	private JButton berechnenButton;
	private JButton newButton;
	private JButton helpButton;
	@SuppressWarnings("unused")
	private ReadData readdata;
	private int matrixwidth = 50;
	private String bsp1;
	@SuppressWarnings("unused")
	private RLC_Algo rlc;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				Start_App_RLC inst = new Start_App_RLC();
				frame.getContentPane().add(inst);
				((JComponent) frame.getContentPane()).setPreferredSize(inst.getSize());
				frame.pack();
				frame.setVisible(true);
				frame.setTitle(" Run Length Coding ");
				frame.setSize(800, 600);
				frame.setLocation(100, 100);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	/**
	 * Create the application
	 */
	public Start_App_RLC() {
		super();
		{
			try {
				getContentPane().add(createContents(), BorderLayout.CENTER);
//				setSize(800, 600);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Initialize the contents of the frame
	 */
	// private void createContents(){
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private JPanel createContents() {
		frame = new JPanel();
		frame.setLayout(null);
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 800, 600);
		// add 8px to the width for the offset for windows
		if (System.getProperty("os.name").contains("Windows")) {
			frame.setSize(808, 600);

		}
		frame.setName("Run-Length-Kodierung");
		CanvasPanel cp = new CanvasPanel(this);

		tabbedPane = new JTabbedPane();
		tabbedPane.setBounds(411, 5, 379, 400);
		frame.add(tabbedPane);

		scrollPane = new JScrollPane();
		tabbedPane.addTab("RLC - fest", null, scrollPane, null);

		rlcPane = new JTextArea();
		rlcPane.setLineWrap(true);
		rlcPane.setWrapStyleWord(true);
		scrollPane.setViewportView(rlcPane);

		scrollPane_2 = new JScrollPane();
		tabbedPane.addTab("RLC - variabel", null, scrollPane_2, null);

		rlcVPane = new JTextArea();
		rlcVPane.setLineWrap(true);
		rlcVPane.setWrapStyleWord(true);
		scrollPane_2.setViewportView(rlcVPane);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Remove after Layout redesign", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel.setBounds(5, 5, 500, 375);

		berechnenButton = new JButton();
		berechnenButton.setText("Kodierung berechnen");
		berechnenButton.addActionListener(new AL(cp, this));
		berechnenButton.setBounds(421, 411, 369, 23);
		frame.add(berechnenButton);

		ta_scrollPane = new JScrollPane();
		ta_scrollPane.setBounds(5, 440, 785, 133);
		frame.add(ta_scrollPane);

		outputPane = new JTextArea();
		ta_scrollPane.setViewportView(outputPane);
		outputPane.setLineWrap(true);
		outputPane.setWrapStyleWord(true);
		outputPane.setBorder(new TitledBorder(null, "Ausgabe ohne Komprimierung", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));

		newButton = new JButton();
		newButton.addActionListener(new AL(cp, this));
		newButton.setText("Zeichenfläche leeren");
		newButton.setBounds(5, 412, 175, 23);
		frame.add(newButton);

		exampleComboBox = new JComboBox();
		exampleComboBox.setBounds(186, 412, 152, 23);
		bsp1 = new String("Beispiel laden");
		String bsp2 = new String("Beispiel 1");
		String bsp3 = new String("Beispiel 2");
		String bsp4 = new String("Beispiel 3");
		exampleComboBox.addItem(bsp1);
		exampleComboBox.addItem(bsp2);
		exampleComboBox.addItem(bsp3);
		exampleComboBox.addItem(bsp4);
		setComboBoxBehavior(exampleComboBox, cp, this);
		frame.add(exampleComboBox);

		helpButton = new JButton();
		helpButton.setText("?");
		helpButton.setBounds(344, 411, 65, 23);
		setPopupBehavior(helpButton, getPopupMenu());
		frame.add(helpButton);

		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 400, 400);
		frame.add(panel_1);

		return frame;
	}

	public JPanel getFrame() {
		return frame;
	}

	public int getMatrixwidth() {
		return matrixwidth;
	}

	public void setMatrixwidth(int matrixwidth) {
		this.matrixwidth = matrixwidth;
	}

	public JTextArea getOutputPane() {
		return outputPane;
	}

	public JTextArea getRlcPane() {
		return rlcPane;
	}

	public JTextArea getrlcVPane() {
		return rlcVPane;
	}

	public void resetAll() {
		rlcPane.setText("");
		rlcVPane.setText("");
		outputPane.setText("");
		exampleComboBox.setSelectedIndex(0);
	}

	@SuppressWarnings("rawtypes")
	private void setComboBoxBehavior(final javax.swing.JComboBox parent, final CanvasPanel cp, final Start_App_RLC gui) {
		parent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox) e.getSource();
				String selected = (String) cb.getSelectedItem().toString();
				if (selected.equals("Beispiel 1")) {
					readdata = new ReadData("Beispiel1", cp, gui);
					rlcPane.setText("");
					rlcVPane.setText("");
					outputPane.setText("");
				}
				if (selected.equals("Beispiel 2")) {
					readdata = new ReadData("Beispiel2", cp, gui);
					rlcPane.setText("");
					rlcVPane.setText("");
					outputPane.setText("");
				}
				if (selected.equals("Beispiel 3")) {
					readdata = new ReadData("Beispiel3", cp, gui);
					rlcPane.setText("");
					rlcVPane.setText("");
					outputPane.setText("");
				}
			}
		});
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
			helpText.setText("<html>Hilfe-Menü für die Komprimierungsanwendung<br><br>"
					+ "Tragen Sie auf der großen grauen Fläche mit der Maus Punkte ein (durch Klicken), <br>"
					+ "die mittels RLC und Quad-Algorithmus komprimiert werden sollen.<br>"
					+ "Drücken Sie anschließend den Button -Kodierung berechnen-.<br>"
					+ "Im unteren Interface-Bereich wird Ihnen die unkomprimierte Berechnung Ihrer Zeichnung<br>"
					+ "dargestellt. Im Textfeld rechts neben der Zeichenfläche sind die komprimierten Ergebnisse<br>"
					+ "veranschaulicht.<br>"
					+ "Klicken Sie auf einen der drei Reiter um zwischen den Ergebnissen der<br>"
					+ "verschiedenen Algorithmen zu wechseln.<br>"
					+ "Klicken Sie auf den Button -Zeichenfläche leeren um eine neue Zeichnung zu beginnen.<br>"
					+ "Sie haben die Möglichkeit vordefinierte Beispielzeichnungen einzuladen. Wählen Sie <br>"
					+ "dazu den Button Beispiel laden an und klicken auf den gewünschten Namen<br><br>"
					+ "Klicken Sie auf -Zurück- um dieses Fenster zu schließen und zur Anwendung<br>"
					+ "zurückzukehren.</html>");
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

	/**
	 * ActionListener
	 */
	class AL implements ActionListener {
		CanvasPanel cp;
		Start_App_RLC gui;

		public AL(CanvasPanel cp) {
			this.cp = cp;
		}

		public AL(CanvasPanel cp, Start_App_RLC gui) {
			this.cp = cp;
			this.gui = gui;
		}

		public AL() {
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Zeichenfläche leeren")) {
				matrixwidth = 50;
				gui.resetAll();
				cp.updateFH(matrixwidth);
				cp.cleanPanel();
			}
			if (e.getActionCommand().equals("Kodierung berechnen")) {
				cp.calcWOComp();
				rlc = new RLC_Algo(cp, gui);
			}
		}
	}
}