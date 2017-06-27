package de.tud.gis.teachlets.quadtree;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

//import org.jvnet.substance.skin.SubstanceBusinessBlackSteelLookAndFeel;
//import org.jvnet.substance.skin.SubstanceRavenGraphiteLookAndFeel;

/**
 * class for generating the gui and adding Listeners
 * @author danielkadner
 *
 */
@SuppressWarnings("unused")
public class Start_Algo_Quad extends JApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7317991275959813609L;
	private JTextArea textArea;
	private JPopupMenu popupMenu;
	private JPanel frame;
	private JPanel panel;
	private JScrollPane ta_scrollPane;
	private QuadPanel qp;
	private JButton berechnenButton;
	private JButton newButton;
	private JButton helpButton;
	private int matrixwidth = 50;

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				Start_Algo_Quad inst = new Start_Algo_Quad();
				frame.getContentPane().add(inst);
				((JComponent)frame.getContentPane()).setPreferredSize(inst.getSize());
				frame.pack();
				frame.setVisible(true);
				frame.setTitle("QuadTree-Berechnung");
				frame.setSize(1000, 800);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	/**
	 * Create the application
	 */
	public Start_Algo_Quad() {
		super();
		{try{
			getContentPane().add(createContents(), BorderLayout.CENTER);
			setSize(1017, 800);
		}catch(Exception e){e.printStackTrace();}}
	}

	/**
	 * Initialize the contents of the frame
	 */
	private JPanel createContents(){
		frame = new JPanel();
		frame.setLayout(null);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 1000, 800);
		//add 8px to the width for the offset for windows
		if (System.getProperty("os.name").contains("Windows")){
			frame.setSize(1008, 800);
		}
		frame.setName("QuadTree-Berechnung");
		CanvasPanel cp = new CanvasPanel(this);

		panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(null, "Remove after Layout redesign", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		panel.setBounds(5, 5, 500, 375);
		//frame.add(panel);

		berechnenButton = new JButton();
		berechnenButton.setText("Kodierung berechnen");
		berechnenButton.addActionListener(new AL(cp,this));
		berechnenButton.setBounds(411, 381, 294, 23);
		frame.add(berechnenButton);

		ta_scrollPane = new JScrollPane();
		ta_scrollPane.setBounds(5, 410, 1005, 350);
		frame.add(ta_scrollPane);
		
		qp = new QuadPanel();
		ta_scrollPane.setViewportView(qp);
		qp.setBorder(new TitledBorder(null, "Ausgabe des Baumes", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		
		newButton = new JButton();
		newButton.addActionListener(new AL(cp,this));
		newButton.setText("Zeichenfläche leeren");
		newButton.setBounds(711, 381, 208, 23);
		frame.add(newButton);

		helpButton = new JButton();
		helpButton.setText("?");
		helpButton.setBounds(935, 381, 65, 23);
		setPopupBehavior(helpButton, getPopupMenu());
		frame.add(helpButton);

		final JPanel panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 400, 400);
		frame.add(panel_1);

		textArea = new JTextArea();
		textArea.setBounds(411, 5, 580, 370);
		//frame.add(textArea);
	
		JLabel ta = new JLabel();
//		ta.setBackground(Color.WHITE);
		ta.setBorder(new TitledBorder(null, "Wie funktioniert der Algorithmus", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		ta.setBounds(411, 5, 580, 370);
		//ta.setBackground(Color.WHITE);
		frame.add(ta);
		ta.setText("<html>Der Quadtree - Algorithmus ermittelt die Anzahl von markierten (schwarzen) Feldern " +
				"auf der Zeichenfläche. Ist diese Anzahl kleiner 1 oder beinhaltet die Fläche nur " +
				"markierte Felder so wird ausschließlich ein Wurzelknoten in einem Baum erzeugt. <br><br> Dieser Knoten ist " +
				"weiß, wenn die Felder weiß sind bzw. schwarz, wenn alle Felder der Zeichenfläche schwarz sind. " +
				"Ist auf der Zeichenfläche eine Anzahl von Feldern größer bzw. gleich 1 und kleiner als die " +
				"Gesamtmenge alle Felder markiert so wird die Zeichenfläche in vier gleichgroße Quadranten " +
				"unterteilt. <br><br>Diese neuen Teilflächen werden unter einen Wurzelknoten sortiert angeordnet. Der " +
				"Quadrant links oben wird im Baum ganz links dargestellt, der Quadrant rechts oben daneben. Es " +
				"folgen die Flächen rechts unten und links unten.<br> (Quadranten im Uhrzeigersinn, links oben beginnend)<br><br>Die neuen Quadranten werden wieder bezüglich " +
				"der Anzahl der markierten Felder überprüft. Und nach obiger Regel unterteilt oder nicht." +
				" </html>");
		
		Quad quad = new Quad(cp,/* this, ta_scrollPane,*/ qp);
		
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
	
	private JPopupMenu getPopupMenu() {
		if(popupMenu == null) {
			popupMenu = new JPopupMenu();
			popupMenu.setLocation(140, 180);
			popupMenu.setSize(400, 400);
			JPanel panel = new JPanel();
			panel.setBounds(30, 30, 280, 380);
			popupMenu.add(panel);
			
			JLabel helpText = new JLabel();
			helpText.setText("<html>Hilfe-Menü für die Komprimierungsanwendung<br><br>"+
				"Tragen Sie auf der großen grauen Fläche mit der Maus Punkte ein (durch Klicken), <br>" +
				"die mittels Quad-Algorithmus komprimiert werden soll.<br>" +
				"Drücken Sie anschließend den Button -Kodierung berechnen-.<br>" +
				"Im unteren Interface-Bereich wird Ihnen der Ihrer Zeichnung zugehörige Baum<br>" +
				"dargestellt. Im Textfeld rechts neben der Zeichenfläche ist der Algorithmus in<br>" +
				"seiner allgmeinen Funktionsweise erläutert.<br>" +
				"Klicken Sie auf den Button -Zeichenfläche leeren- um eine neue Zeichnung zu beginnen.<br>" +
				"Klicken Sie auf -Zurück- um dieses Fenster zu schließen und zur Anwendung<br>" +
				"zurückzukehren.</html>");
			helpText.setBounds(10, 20, 130, 200);
			panel.add(helpText);
			
			JPanel panel2 = new JPanel();
			panel2.setBounds(50, 30, 280, 380);
			popupMenu.add(panel2);	
			JButton zurueckButton = new JButton();
			zurueckButton.setText("Zurueck");
			zurueckButton.setBounds(10, 100, 130, 200);
			panel2.add(zurueckButton);
			setPopupBehavior(zurueckButton, popupMenu);
		}
		return popupMenu;
	}
	
	private void setPopupBehavior(final java.awt.Component parent, final javax.swing.JPopupMenu menu) {
		parent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent e) {
				if(menu.isVisible()==true){menu.setVisible(false);}else{menu.setVisible(true);}			
			}
		});
	}
	/**
	 * ActionListener
	 */
	class AL implements ActionListener {
		CanvasPanel cp;
		Start_Algo_Quad gui;
		
		public AL (CanvasPanel cp){
			this.cp = cp;
		}
		public AL (CanvasPanel cp, Start_Algo_Quad gui){
			this.cp = cp;
			this.gui = gui;
		}
		public AL(){}
		public void actionPerformed(ActionEvent e) {		
			if(e.getActionCommand().equals("Zeichenfläche leeren"))		{cp.updateFH(matrixwidth); cp.cleanPanel(); /*qp.cleanPanel();*/ Quad quad = new Quad(cp, qp);}
			if(e.getActionCommand().equals("Kodierung berechnen"))	{Quad quad = new Quad(cp, qp);}
		}
	}
}