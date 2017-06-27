package de.tud.gis.teachlets.spatialreferencesystems;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/** 
 * Main Class
 * 
 * creates and displays components
 */ 
public class JSpatRefSysMain extends JApplet 
{
	private static final long serialVersionUID = 1L;
	protected static final String buttonString = "JButton";
	private JButton btnEuclid, btnCityBlock, btnCorn;
	private Metric _metric;
	private JMapPaintPanel _viewer; 
	public enum Metrics { EUCL, CITY, CORN}; 
	
	/** 
	 * initializes applet
	 */ 
	public void init() 
	{
	    setSize(920, 540);	    
    }

	/** 
	 * main function of application
	 * creates frame
	 */ 
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JSpatRefSysMain inst = new JSpatRefSysMain();
				JFrame frame = new JFrame();
				frame.getContentPane().add(inst);
				((JComponent)frame.getContentPane()).setPreferredSize(inst.getSize());
				frame.setTitle(" Räumliche Bezugssysteme ");
				frame.setSize(925, 575);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
	/**
	 * Class constructor
	 */
	public JSpatRefSysMain() {
		super();
		try{
			javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());	
			getContentPane().add(getMainPanel(), BorderLayout.CENTER);
			
			setData();						
			
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * sets initial data like points and adds them to metric
	 */
	private void setData()
	{
		//set start points
		Point p1 = new Point(388, 190); 
		Point p2 = new Point(43, 390);
		_metric.setPoint1(p1);
		_metric.setPoint2(p2);
	}
	
	/**
	 * creates components and adds them to panel
	 * 
	 * @return main panel
	 */
	private JPanel getMainPanel() 
	{
		//create model
		_metric = new Metric();
		//create & add viewer
		_viewer = new JMapPaintPanel(_metric);
		
		//create TextPanes for TabbedPane
		JTextPane textPaneEukl = createTextPane(Metrics.EUCL);
		JTextPane textPaneCity = createTextPane(Metrics.CITY);
		JTextPane textPaneEcke = createTextPane(Metrics.CORN);
		textPaneEukl.setEditable(false);
		textPaneCity.setEditable(false);
		textPaneEcke.setEditable(false);
		JTabbedPane tabbedPane = new JTabbedPane();
		
		//add Tabs to TabbedPane
		JComponent panel1 = textPaneEukl;
		panel1.setBackground(Color.white);
		tabbedPane.addTab(_metric.getEuclName(), null, panel1);
		
		JComponent panel2 = textPaneCity;
		panel2.setBackground(Color.white);
		tabbedPane.addTab(_metric.getCityBName(), null, panel2);
		
		JComponent panel3 = textPaneEcke;
		panel3.setBackground(Color.white);
		tabbedPane.addTab(_metric.getCornName(), null, panel3);
		
		tabbedPane.setBounds(5, 5, 400, 530);
		
		//create viewer panel for map
		JPanel pnlMain = new JPanel();
		pnlMain.setLayout(null);
		_viewer.setPreferredSize(new Dimension(501, 529));
		_viewer.setLayout(null);
		_viewer.setBounds(410, 5, _viewer.getPreferredSize().width+1, _viewer.getPreferredSize().height+1);
		
		pnlMain.add(tabbedPane);
		pnlMain.add(_viewer);
		
		return pnlMain;
	}
	
	/**
	 * creates text pane with declarations and images of metrics
	 * 
	 * @return text pane
	 */
	private JTextPane createTextPane(Metrics metric) 
	{
		JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc, metric);
        
        //add elements to styled document
        try {
        	switch (metric)
        	{
        	case EUCL: 
        		doc.insertString(doc.getLength(), _metric.getEuclName()+":", doc.getStyle("heading"));
        		doc.insertString(doc.getLength(), _metric.getEuclDeclarationText(), doc.getStyle("regular"));
        		doc.insertString(doc.getLength(), " ", doc.getStyle("buttonEucl"));
        		doc.insertString(doc.getLength(), "\n\nDie Formel für die euklidische Distanz zweier Punkte lautet:\n\n", 
        		doc.getStyle("regular"));
        		doc.insertString(doc.getLength(), " ", doc.getStyle("iconEucl"));
        		break;
        	case CITY:
        		doc.insertString(doc.getLength(), _metric.getCityBName()+":", doc.getStyle("heading"));
            	doc.insertString(doc.getLength(), _metric.getCityBDeclarationText(), doc.getStyle("regular"));
            	doc.insertString(doc.getLength(), " ", doc.getStyle("buttonCityB"));
            	doc.insertString(doc.getLength(), "\n\nDie Formel für die Distanz zweier Punkte in der City-Block-Metrik lautet:\n\n",
            	doc.getStyle("regular"));
            	doc.insertString(doc.getLength(), " ", doc.getStyle("iconCityB"));
            	break;
        	case CORN:       
        		doc.insertString(doc.getLength(), _metric.getCornName()+":", doc.getStyle("heading"));
            	doc.insertString(doc.getLength(), _metric.getCornDeclarationText(), doc.getStyle("regular"));
            	doc.insertString(doc.getLength(), " ", doc.getStyle("buttonCorn"));
            	doc.insertString(doc.getLength(), "\n\nDie Formel für die Distanz zweier Punkte in der Ecken-Kanten-Metrik lautet:\n\n",
            	doc.getStyle("regular"));
            	doc.insertString(doc.getLength(), " ", doc.getStyle("iconCorn"));
            	doc.insertString(doc.getLength(), "\n\nHinweis:", doc.getStyle("underline"));
            	doc.insertString(doc.getLength(), getHintForCorn(), doc.getStyle("regular"));
            	doc.insertString(doc.getLength(), " ", doc.getStyle("iconCornSqrt"));
            	break;
        	}        	
        } catch (BadLocationException ble) {
            System.err.println("Couldn't insert initial text into text pane.");
        }

        return textPane;
    }
	
	/**
	 * defines all available styles and elements for the text pane
	 * 
	 * @param styled document of text pane
	 */
	protected void addStylesToDocument(StyledDocument doc, Metrics metric) 
	{
        //initialize some styles
        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

        //parent style
        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");
        StyleConstants.setFontSize(def, 12);
        
        //children styles
        Style s = doc.addStyle("heading", regular);
        StyleConstants.setBold(s, true);
        StyleConstants.setUnderline(s, true);
        
        s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);

        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);
        
        s = doc.addStyle("underline", regular);
        StyleConstants.setUnderline(s, true);

        //add formula images
        switch (metric)
    	{
    	case EUCL:
	        s = doc.addStyle("iconEucl", regular);
	        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
	        ImageIcon imgEuclid = createImageIcon("formel_euklid.gif",
	                                            "Formel Euklidische Metrik");
	        if (imgEuclid != null) {
	            StyleConstants.setIcon(s, imgEuclid);
	        }
	        break;
    	case CITY:
        
	        s = doc.addStyle("iconCityB", regular);
	        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
	        ImageIcon imgCityBlock = createImageIcon("formel_cityblock.gif",
	                                            "Formel City-Block-Metrik");
	        if (imgCityBlock != null) {
	            StyleConstants.setIcon(s, imgCityBlock);
	        }
	        break;
		case CORN:
            
	        s = doc.addStyle("iconCorn", regular);
	        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
	        ImageIcon imgCorn = createImageIcon("formel_schachbrett.gif",
	                                            "Formel Schachbrett-Metrik");
	        if (imgCorn != null) {
	            StyleConstants.setIcon(s, imgCorn);
	        }
	        s = doc.addStyle("iconCornSqrt", regular);
	        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
	        ImageIcon imgCornSqrt = createImageIcon("formel_schachbrett_wurzel2.gif",
	                                            "Formel Schachbrett-Metrik 2");
	        if (imgCornSqrt != null) {
	            StyleConstants.setIcon(s, imgCornSqrt);
	        }
	        break;
    	}
        
        //add buttons
        switch (metric)
    	{
    	case EUCL:
	        s = doc.addStyle("buttonEucl", regular);
	        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
	        
	        btnEuclid = new JButton();
	        btnEuclid.setText("Beispiel ein");        
	        btnEuclid.setCursor(Cursor.getDefaultCursor());
	        btnEuclid.setActionCommand(buttonString);
	        
	        //change button text and status with button click
	        btnEuclid.addActionListener( new ActionListener() {
				public void actionPerformed( ActionEvent e ) {				
					if (_metric.getEuclidStatus()) {
						btnEuclid.setText("Beispiel ein");	
					} else {
						btnEuclid.setText("Beispiel aus");					
					}
					_metric.changeEuclidStatus();				
					_viewer.repaint();
			  } 
			} );
	        StyleConstants.setComponent(s, btnEuclid);
	        break;
    	case CITY:        
	        s = doc.addStyle("buttonCityB", regular);
	        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
	        
	        btnCityBlock = new JButton();
	        btnCityBlock.setText("Beispiel ein");        
	        btnCityBlock.setCursor(Cursor.getDefaultCursor());
	        btnCityBlock.setActionCommand(buttonString);
	        
	        //change button text and status with button click
	        btnCityBlock.addActionListener( new ActionListener() {
				public void actionPerformed( ActionEvent e ) {				
					if (_metric.getCityBStatus()) {
						btnCityBlock.setText("Beispiel ein");					
					} else {
						btnCityBlock.setText("Beispiel aus");					
					}
					_metric.changeCityBStatus();
					_viewer.repaint();
			  } 
			} );
	        StyleConstants.setComponent(s, btnCityBlock);
	        break;
    	case CORN:        
	        s = doc.addStyle("buttonCorn", regular);
	        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
	        
	        btnCorn = new JButton();
	        btnCorn.setText("Beispiel ein");        
	        btnCorn.setCursor(Cursor.getDefaultCursor());
	        btnCorn.setActionCommand(buttonString);
	        
	        //change button text and status with button click
	        btnCorn.addActionListener( new ActionListener() {
				public void actionPerformed( ActionEvent e ) {				
					if (_metric.getCornStatus()) {
						btnCorn.setText("Beispiel ein");										
					} else {
						btnCorn.setText("Beispiel aus");					
					}
					_metric.changeCornStatus();
					_viewer.repaint();
			  } 
			} );
	        StyleConstants.setComponent(s, btnCorn);
	        break;
    	}
    }
	
	/** 
	 * Returns an ImageIcon, or null if the path was invalid. 
	 * 
	 * @param path of image
	 * @param description of image
	 * @return image icon or null
	 */
    protected static ImageIcon createImageIcon(String path, String description) 
    {
    	java.net.URL imgURL = JSpatRefSysMain.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    /**
     * returns a hint for corner edge metric
     * 
     * @return hint string
     */
    private static String getHintForCorn()
    {
		return "\n\nDa die Schachbrett-Metrik hier auf dem Rastermodell basiert, wird " +
				"die Distanz aus der Mindestanzahl der überschrittenen Zell-Kanten oder " +
				"Zell-Ecken ermittelt, wobei eine Rasterzelle eine Kantenlänge von 1 hat. " +
				"Daher ergibt sich für dieses Beispiel bei der Schachbrett-Metrik eine kürze " +
				"Distanz zwischen den zwei Punkten als bei der Luftliniendistanz der euklidischen " +
				"Metrik.\nLegt man ein kartesisches Koordinatensystem zugrunde (wie bei der euklidischen " +
				"Metrik), müsste man das Ergebnis der oberen Formel mit √2 multiplizieren. Dann wäre " +
				"der Abstand zwischen den zwei Punkten:\n\t";
    	
    }
}
