package de.tud.gis.teachlets.idw;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;


@SuppressWarnings("serial")
public class Start_App_IDW extends JApplet {

	
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JPanel frame;
	private JPanel panel_1;
	private JButton newButton;
	private JButton helpButton;
	private JButton idwButton;
	private IDWCanvas ic;
	private JPopupMenu popupMenu;
	@SuppressWarnings("unused")
	private JPopupMenu errorPopup;
	private JTextField valueTF;
	private int matrixwidth = 10;

	
	public static void main(String args[]) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				Start_App_IDW inst = new Start_App_IDW();
				frame.getContentPane().add(inst);
				((JComponent) frame.getContentPane()).setPreferredSize(inst.getSize());
				frame.pack();
				frame.setVisible(true);
				frame.setTitle("Inverse Distanzwichtung");
				frame.setSize(650, 525);
//				frame.setResizable(false);
				frame.setLocation(100, 100);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}

	
	/**
	 * Create the applet
	 */
	public Start_App_IDW() {
		super();
		//
		{
			try {
				getContentPane().add(createContents(), BorderLayout.CENTER);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public JPanel createContents(){
		frame = new JPanel();
		frame.setBorder(new BevelBorder(BevelBorder.LOWERED));
		frame.setLayout(null);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setName("Voronoi-Triangulierung");

		panel_1 = new JPanel();
		panel_1.setBounds(5, 5, 640, 490);
		panel_1.setLayout(new BorderLayout());
		frame.add(panel_1);

		ic = new IDWCanvas(this);
		panel_1.add(ic);
//		panel_1.add(vc);

		final JPanel panel_2 = new JPanel();
		panel_2.setLayout(new BorderLayout());
		panel_1.add(panel_2, BorderLayout.SOUTH);

		final JPanel panel_3 = new JPanel();
		panel_3.setLayout(new FlowLayout());
		panel_3.setBackground(Color.LIGHT_GRAY);
		panel_2.add(panel_3);
		
		JLabel value = new JLabel();
		value.setText("Wert:");
		panel_3.add(value);
		
		valueTF = new JTextField(2);
		valueTF.setEditable(true);
		panel_3.add(valueTF);

	

		comboBox = new JComboBox();
		comboBox.addItem("Anzahl Stützstellen");
		comboBox.setEditable(false);
		panel_3.add(comboBox);
		
		
		idwButton = new JButton();
		panel_3.add(idwButton);
		idwButton.addActionListener(new AL());
		idwButton.setText("IDW");

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

//		voroDia = new Checkbox("Voronoi Diagramm", true);
//		voroDia.setBackground(Color.LIGHT_GRAY);
//		voroDia.addItemListener(new IL());
//		panel_4.add(voroDia);
//
//		delaTri = new Checkbox("Delaunay Triangulation", false);
//		delaTri.setBackground(Color.LIGHT_GRAY);
//		delaTri.addItemListener(new IL());
//		panel_4.add(delaTri);

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
	
	@SuppressWarnings("unchecked")
	public void updateComboBox(int k){
		comboBox.removeAllItems();
		comboBox.addItem("Anzahl Stützstellen");
		for (int i = 1; i <= k ; i++){
			comboBox.addItem(i);
		}
	}
	
	public JPanel getCanvasPanel(){
		return panel_1;
	}
	public Start_App_IDW getApp(){
		return this;
	}
	
	public int getMatrixwidth(){
		return matrixwidth;
	}
	
	public void setValue(Integer d){
		if (d == null){
			this.valueTF.setText("");
		}
		else{
			this.valueTF.setText(String.valueOf(d));
		}
	}
	
	public Integer getValue(){
		try {
			return Integer.valueOf(this.valueTF.getText());
		}
		catch (NumberFormatException e){
			return null;
		}
	}
	
	/**
	 * ActionListener
	 */
	class AL implements ActionListener {
		
		public AL() {
		}

		@SuppressWarnings("unused")
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Zeichenfläche leeren")) {
				ic.initialize();
				ic.repaint();
			}
			if (e.getActionCommand().equals("IDW")){
				if (ic.isEmpty()) {
					System.out.println(getValue());
					JOptionPane.showMessageDialog(frame, "Die Zeichenfläche ist noch leer",  "Input Error",   JOptionPane.ERROR_MESSAGE);
				}
				else{
					if(comboBox.getSelectedIndex() != 0){
						IDW_Algo algo = new IDW_Algo(ic, getApp(), comboBox.getSelectedIndex()); 
					}
				}
			}
		}
	}

}
