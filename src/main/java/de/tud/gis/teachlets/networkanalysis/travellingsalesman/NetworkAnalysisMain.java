package de.tud.gis.teachlets.networkanalysis.travellingsalesman;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
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
public class NetworkAnalysisMain extends JApplet 
{
	private DataModel _dataModel;
	private JPaintPanel _viewer; 
	private static final long serialVersionUID = 1L;
	protected static final String buttonString = "JButton";
	private JButton btnStartTravel;
	private JButton btnAlgoTravel;
	private JTextArea taInstrucTravel;
	final static int DIAMETER = 11; //change diameter of nodes here
	
	/**
	 * Class constructor
	 */
	public NetworkAnalysisMain() 
	{
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
	 * initializes applet
	 */ 
	public void init() 
	{
	    setSize(910, 665);	
    }
	
	/** 
	 * main function of application
	 * creates frame
	 */ 
	public static void main(String[] args) 
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				NetworkAnalysisMain inst = new NetworkAnalysisMain();
				JFrame frame = new JFrame();
				frame.getContentPane().add(inst);
				((JComponent)frame.getContentPane()).setPreferredSize(inst.getSize());
				frame.setTitle(" Netzwerkanalyse ");
				frame.setSize(916, 690);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
	
	/**
	 * sets initial data like points and adds them to metric
	 */
	private void setData()
	{
		//create general nodes valid for all algorithms
		Node n1 = new Node(400-(DIAMETER/2), 200-(DIAMETER/2), _dataModel.getDiameter(), "Berlin");
		Node n2 = new Node(420-(DIAMETER/2), 320-(DIAMETER/2), _dataModel.getDiameter(), "Dresden");
		Node n3 = new Node(280-(DIAMETER/2), 340-(DIAMETER/2), _dataModel.getDiameter(), "Erfurt");
		Node n4 = new Node(180-(DIAMETER/2), 520-(DIAMETER/2),_dataModel.getDiameter(), "Stuttgart");
		Node n5 = new Node(60-(DIAMETER/2), 340-(DIAMETER/2),_dataModel.getDiameter(), "Koeln");
		Node n6 = new Node(220-(DIAMETER/2), 120-(DIAMETER/2),_dataModel.getDiameter(), "Hamburg");
		Node n7 = new Node(320-(DIAMETER/2), 580-(DIAMETER/2), _dataModel.getDiameter(), "Muenchen");
		Node n8 = new Node(60-(DIAMETER/2), 480-(DIAMETER/2), _dataModel.getDiameter(), "Saarbruecken");
		Node n9 = new Node(290-(DIAMETER/2), 465-(DIAMETER/2), _dataModel.getDiameter(), "Nuernberg");
		Node n10 = new Node(110-(DIAMETER/2), 590-(DIAMETER/2), _dataModel.getDiameter(), "Freiburg");
		Node n11 = new Node(350-(DIAMETER/2), 300-(DIAMETER/2), _dataModel.getDiameter(), "Leipzig");
		Node n12 = new Node(90-(DIAMETER/2), 295-(DIAMETER/2), _dataModel.getDiameter(), "Dortmund");
		Node n13 = new Node(310-(DIAMETER/2), 340-(DIAMETER/2), _dataModel.getDiameter(), "Jena");
		Node n14 = new Node(220-(DIAMETER/2), 440-(DIAMETER/2), _dataModel.getDiameter(), "Wuerzburg");
		Node n15 = new Node(155-(DIAMETER/2), 415-(DIAMETER/2), _dataModel.getDiameter(), "Frankfurt");
		Node n16 = new Node(50-(DIAMETER/2), 317-(DIAMETER/2), _dataModel.getDiameter(), "Duesseldorf");
		Node n17 = new Node(160-(DIAMETER/2), 160-(DIAMETER/2), _dataModel.getDiameter(), "Bremen");
		Node n18 = new Node(200-(DIAMETER/2), 220-(DIAMETER/2), _dataModel.getDiameter(), "Hannover");
		Node n19 = new Node(310-(DIAMETER/2), 235-(DIAMETER/2), _dataModel.getDiameter(), "Magdeburg");
		Node n20 = new Node(290-(DIAMETER/2), 110-(DIAMETER/2), _dataModel.getDiameter(), "Schwerin");
		Node n21 = new Node(230-(DIAMETER/2), 50-(DIAMETER/2), _dataModel.getDiameter(), "Kiel");
		Node n22 = new Node(325-(DIAMETER/2), 65-(DIAMETER/2), _dataModel.getDiameter(), "Rostock");
		Node n23 = new Node(200-(DIAMETER/2), 310-(DIAMETER/2), _dataModel.getDiameter(), "Kassel");
		Node n24 = new Node(125-(DIAMETER/2), 230-(DIAMETER/2), _dataModel.getDiameter(), "Osnabrueck");
		
		//add nodes and edges for shortest path algorithm
		add(n1); add(n2);  add(n3); add(n4); add(n5); add(n6); add(n7); add(n8);
	    add(n9); add(n10); add(n11); add(n12); add(n13); add(n14); add(n15); 
	    add(n16);  add(n17); add(n18); add(n19); add(n20); add(n21); add(n22);
	    add(n23); add(n24);
	    
	    /*add(new Edge(n21, n6, 9));
	    add(new Edge(n17, n6, 9));
	    add(new Edge(n17, n18, 10));
	    add(new Edge(n1, n6, 25));
	    add(new Edge(n20, n6, 9));
	    add(new Edge(n22, n6, 15));
	    add(new Edge(n20, n22, 7));
	    add(new Edge(n22, n1, 19));
	    add(new Edge(n18, n6, 13));
	    add(new Edge(n18, n1, 24));
	    add(new Edge(n18, n19, 13));
	    add(new Edge(n1, n19, 12));
	    add(new Edge(n1, n20, 18));
	    add(new Edge(n1, n2, 17));
	    add(new Edge(n1, n11, 15));
	    add(new Edge(n19, n11, 10));
	    add(new Edge(n11, n2, 10));
	    add(new Edge(n2, n13, 15));
	    add(new Edge(n13, n3, 4));
	    add(new Edge(n11, n3, 10));
	    add(new Edge(n18, n12, 18));
	    add(new Edge(n12, n16, 6));
	    add(new Edge(n16, n5, 3));
	    add(new Edge(n18, n5, 25));
	    add(new Edge(n18, n23, 12));
	    add(new Edge(n5, n23, 18));
	    add(new Edge(n5, n15, 15));
	    add(new Edge(n23, n15, 15));
	    add(new Edge(n3, n23, 11));
	    add(new Edge(n3, n15, 19));
	    add(new Edge(n15, n8, 15));
	    add(new Edge(n8, n10, 15));
	    add(new Edge(n8, n4, 17));
	    add(new Edge(n10, n4, 13));
	    add(new Edge(n15, n14, 7));
	    add(new Edge(n14, n9, 9));
	    add(new Edge(n15, n4, 15));
	    add(new Edge(n4, n7, 19));
	    add(new Edge(n9, n7, 15));
	    add(new Edge(n9, n13, 17));
	    add(new Edge(n9, n11, 23));
	    add(new Edge(n9, n2, 26));
	    add(new Edge(n24, n17, 10));
	    add(new Edge(n24, n18, 12));
	    add(new Edge(n24, n12, 9));*/	  
	}

	/**
	 * creates components and adds them to panel
	 * 
	 * @return main panel
	 */
	private JPanel getMainPanel() 
	{
		taInstrucTravel = new JTextArea();
		btnAlgoTravel = new JButton();
				
		//create model
		_dataModel = new DataModel(DIAMETER);
		//create & add viewer
		_viewer = new JPaintPanel(_dataModel);
		
		//create textpane
		JTextPane textPane = createTextPane();
		textPane.setEditable(false);
				
		JPanel pnlMain = new JPanel();
		pnlMain.setLayout(null);
		_viewer.setPreferredSize(new Dimension(493, 653));
		_viewer.setLayout(null);
		_viewer.setBounds(410, 5, _viewer.getPreferredSize().width+1, _viewer.getPreferredSize().height+1);
		_viewer.addMouseMotionListener(new MyMouseAdapter(_viewer, _dataModel));
		_viewer.addMouseListener(new MyMouseAdapter(_viewer, _dataModel));
		
		textPane.setBounds(5, 5, 400, 655);
		
		//draw border of textpane
		Border bord = BorderFactory.createLineBorder(Color.black);
		textPane.setBorder(bord);
		
		taInstrucTravel.setEditable(false);
		
		pnlMain.add(textPane);
		pnlMain.add(_viewer);
		
		return pnlMain;
	}
	
	/** 
	 * returns data model
	 * 
	 * @return data model 
	 */
	public DataModel getDataModel() 
	{ 
		return _dataModel; 
	}
	
	/** 
	 * add Node to data model
	 * 
	 * @param Node object 
	 */
	public void add(Node n)
	{
		_dataModel.add(n);
		
	}
	
	/** 
	 * add Edge to data model
	 * 
	 * @param Edge object 
	 */
	public void add(Edge e)
	{
		_dataModel.add(e);
		
	}
	
	/**
	 * creates text pane with declarations and images of metrics
	 * 
	 * @return text pane
	 */
	private JTextPane createTextPane() 
	{
		JTextPane textPane = new JTextPane();
        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);
        
        //add elements to styled document according to algorithm
        try {
    		doc.insertString(doc.getLength(), "Travelling-Salesman-Problem:", doc.getStyle("heading"));
    		doc.insertString(doc.getLength(), _dataModel.getAlgorithm().getDescription(), doc.getStyle("regular"));
    		doc.insertString(doc.getLength(), " ", doc.getStyle("buttonStartTravel"));
    		doc.insertString(doc.getLength(), "\n\n", doc.getStyle("regular"));
    		doc.insertString(doc.getLength(), " ", doc.getStyle("lblInstrucTravel"));
    		doc.insertString(doc.getLength(), "\n\n", doc.getStyle("regular"));
    		doc.insertString(doc.getLength(), " ", doc.getStyle("buttonAlgoTravel"));
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
	protected void addStylesToDocument(StyledDocument doc) 
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
        
        //add buttons
        s = doc.addStyle("buttonStartTravel", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        
        btnStartTravel = new JButton();
        btnStartTravel.setText("Start");        
        btnStartTravel.setCursor(Cursor.getDefaultCursor());
        btnStartTravel.setActionCommand(buttonString);
        
        //change button text and status with button click
        btnStartTravel.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) 
			{				
				_dataModel.setActualPhase(DataModel.AlgorPhase.SETSTARTNODE);
				_dataModel.clearNodeListStartEnd();
				_dataModel.getNodeListAlgo().clear();
				_dataModel.clearResultLists();
				_viewer.setActualInstructionTextArea(taInstrucTravel);
				_viewer.setActualContinueButton(btnAlgoTravel);
				taInstrucTravel.setText(_dataModel.getAlgorithm().getInstructionText(_dataModel.getActualPhase()));	
				
				//andere Label auf leer setzen
				btnAlgoTravel.setVisible(true);
				btnAlgoTravel.setEnabled(false);
				_viewer.repaint();
		  } 
		} );
        StyleConstants.setComponent(s, btnStartTravel);
        
        s = doc.addStyle("buttonAlgoTravel", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        btnAlgoTravel.setText("Algorithmus starten");        
        btnAlgoTravel.setCursor(Cursor.getDefaultCursor());
        btnAlgoTravel.setActionCommand(buttonString);
        btnAlgoTravel.setVisible(false);
        
      //execute algorithm with button click
        btnAlgoTravel.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) 
			{				
				if (_dataModel.getEndNodes().size() > 0)
					_dataModel.setActualPhase(DataModel.AlgorPhase.SHOWRESULT);
				Vector<Edge> resultEdges = _dataModel.getAlgorithm().calculateAlgo(_dataModel);
				_dataModel.setEdgeResultList(resultEdges);
				_viewer.repaint();
		  } 
		} );
        StyleConstants.setComponent(s, btnAlgoTravel);
        
        //add Label	        
        s = doc.addStyle("lblInstrucTravel", regular);
        StyleConstants.setAlignment(s, StyleConstants.ALIGN_CENTER);
        
        taInstrucTravel.setFont(new Font("SansSerif", Font.BOLD, 12));
        StyleConstants.setComponent(s, taInstrucTravel);   	
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
    	java.net.URL imgURL = NetworkAnalysisMain.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

