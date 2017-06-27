package de.tud.gis.teachlets.cellular.automata;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JToggleButton;
import javax.swing.UIManager;
import javax.swing.JCheckBox;


/**
 * class for generating the gui and adding Listeners
 * 
 * @author danielkadner
 * 
 */
public class Start_App_CA extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6668326337684791173L;
	private JPopupMenu popupMenu;
	private JPanel frame;
	private JPanel panel;
	private CanvasPanel cp;
	private JButton newButton;
	private int matrixwidth = 20;
	private CA_Algo rlc;
	private boolean sim;
	private boolean raster;
	private int delay;
	private Timer time;
	
	static final int FPS_MIN = 1;
    static final int FPS_MAX = 19;
    static final int FPS_INIT = 10;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				Start_App_CA inst = new Start_App_CA();
				frame.getContentPane().add(inst);
				((JComponent) frame.getContentPane()).setPreferredSize(inst.getSize());
				frame.pack();
				frame.setVisible(true);
				frame.setTitle(" Zellulärer Automat (Game of Life) ");
				frame.setSize(410, 520);
				frame.setLocation(100, 100);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	/**
	 * Create the application
	 */
	public Start_App_CA() {
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
		frame.setLayout(null);
		frame.setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 800, 600);
		// add 8px to the width for the offset for windows
		if (System.getProperty("os.name").contains("Windows")) {
			frame.setSize(808, 600);

		}
		frame.setName("Zellulärer Automat (Game of Life)");
		cp = new CanvasPanel(this);


		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Remove after Layout redesign", TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel.setBounds(5, 5, 500, 375);

		newButton = new JButton();
		newButton.addActionListener(new AL(cp, this));
		newButton.setText("Zeichenfläche leeren");
		newButton.setBounds(5, 412, 186, 23);
		frame.add(newButton);

		
		JLabel lblFPS= new JLabel("Geschwindigkeit:");
		lblFPS.setForeground(Color.LIGHT_GRAY);
		lblFPS.setBounds(213, 441, 192, 16);
		frame.add(lblFPS);
		JCheckBox cbRaster = new JCheckBox("Raster");
		cbRaster.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.DESELECTED) {
					raster = false;
					cp.repaint();
				}
				else {
					raster = true;
					cp.repaint();
				}
			}
		});
		cbRaster.setForeground(Color.LIGHT_GRAY);
		cbRaster.setBounds(203, 410, 71, 23);
		frame.add(cbRaster);
		JButton button = new JButton("?");
		button.setBounds(347, 409, 61, 29);
		setPopupBehavior(button, getPopupMenu());
		frame.add(button);
		
		
		 //Create the slider.
        JSlider framesPerSecond = new JSlider(JSlider.HORIZONTAL,FPS_MIN, FPS_MAX, FPS_INIT);
        framesPerSecond.setForeground(Color.LIGHT_GRAY);
        delay = 1000 / FPS_INIT;
        framesPerSecond.setMajorTickSpacing(9);
        framesPerSecond.setMinorTickSpacing(1);
        framesPerSecond.setPaintTicks(true);
        framesPerSecond.setPaintLabels(true);
        framesPerSecond.setBounds(203, 459, 202, 29);

        framesPerSecond.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        framesPerSecond.addChangeListener(new CL(cp,this));
        @SuppressWarnings("unused")
		Font font = new Font("Serif", Font.ITALIC, 15);
        framesPerSecond.setFont(UIManager.getFont("ComboBox.font"));
		frame.add(framesPerSecond);

		JToggleButton tgl = new JToggleButton("Simulation start/stop");
		tgl.setBounds(5, 447, 186, 29);
		tgl.addActionListener(new AL(cp, this));
		frame.add(tgl);

		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 400, 400);
		frame.add(panel_1);
		
		time = new Timer(delay, new ALTimer(cp,rlc));

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

	public boolean getRaster(){
		return raster;
	}

	private JPopupMenu getPopupMenu() {
		if (popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.setLocation(150, 150);
			popupMenu.setSize(400, 400);
			JPanel panel = new JPanel();
			panel.setBounds(30, 30, 280, 380);
			popupMenu.add(panel);

			JLabel helpText = new JLabel();
			helpText.setText("<html>Hilfe-Menü für das \"Game of Life\"<br><br>"
					+ "Tragen Sie auf der großen grauen Fläche mit der Maus Punkte ein (durch Klicken). <br>"
					+ "Drücken Sie anschließend den Button -Simulation start/stop-.<br>"
					+ "Im Anschluß wird gemäß den folgenden Regeln der zelluläre Automat gestartet. <br>" 
					+ "<ul><li>Jede Zelle kann entweder lebendig oder tot sein.</li>"
					+ "<li>Die Zelle der Folgegeneration bleibt am leben, <br>"
					+ "wenn in ihren direkten Umgebungszellen 2 oder 3 Zellen lebend sind.</li>"
					+ "<li>Die Zelle der Folgegeneration wird lebendig, <br>"
					+ "wenn in ihren direkten Umgebungszellen 3 zellen lebendig sind.</li>"
					+ "<li>In allen anderen Fällen stirbt die Zelle oder bleibt tot.</li>"
					+ "</ul>"
					+ "Es besteht die Möglichkeit die Anzahl der Folgegenerationen pro Sekunde<br>"
					+ "mittels des Schiebereglers einzustellen. </html>");
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
		Start_App_CA gui;

		public AL(CanvasPanel cp) {
			this.cp = cp;
		}

		public AL(CanvasPanel cp, Start_App_CA gui) {
			this.cp = cp;
			this.gui = gui;
		}

		public AL() {
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Zeichenfläche leeren")) {
				time.stop();
				cp.updateFH(matrixwidth);
				cp.cleanPanel();
			}
			if (e.getActionCommand().equals("Simulation start/stop")) {
				if (sim){
					sim = !sim;
					time.stop();
				}
				else{
					sim = !sim;
					time.start();
				}
			}
		}
	}
	
	class ALTimer implements ActionListener {
		
		private CanvasPanel cp;
		@SuppressWarnings("unused")
		private CA_Algo rlc;
		
		public ALTimer(CanvasPanel cp, CA_Algo rlc){
			this.cp = cp;
			this.rlc = rlc;
		}

		public void actionPerformed(ActionEvent e) {
			rlc = new CA_Algo(cp);	
		}
		
	}
	
	class CL implements ChangeListener{
		CanvasPanel cp;
		Start_App_CA gui;
		
		public CL(CanvasPanel cp, Start_App_CA gui){
			this.cp = cp;
			this.gui = gui;
		}

		public void stateChanged(ChangeEvent e) {
			JSlider source = (JSlider)e.getSource();
	        if (!source.getValueIsAdjusting()) {
	            int fps = (int)source.getValue();
                delay = 1000 / fps;
                time.setDelay(delay);
            }
        }
	}
}