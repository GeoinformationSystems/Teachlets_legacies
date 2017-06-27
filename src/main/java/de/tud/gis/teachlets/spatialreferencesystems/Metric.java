package de.tud.gis.teachlets.spatialreferencesystems;

/** 
 * This class creates and manages different metrics 
 */ 
public class Metric 
{
	private Point _p1;
	private Point _p2;
	private EuclideanMetric _euclmet;
	private CityBlockMetric _citybmet;
	private CornerEdgeMetric _cornmet;
	private boolean _bEuclidStatus;
	private boolean _bCityStatus;
	private boolean _bCornStatus;
	
	/** 
	 * Class constructor
	 */
	Metric()
	{
		_bEuclidStatus = false;
		_bCityStatus = false;
		_bCornStatus = false;
		_citybmet = new CityBlockMetric();
		_euclmet = new EuclideanMetric();
		_cornmet = new CornerEdgeMetric();
	}
	
	/** 
	 * sets first point
	 * 
	 * @param point
	 */
	public void setPoint1(Point p)
	{
		_p1 = p;
	}
	
	/** 
	 * sets second point
	 * 
	 * @param point
	 */
	public void setPoint2(Point p)
	{
		_p2 = p;
	}
	
	/** 
	 * returns first point
	 * 
	 * @return point
	 */
	public Point getPoint1()
	{
		return _p1;
	}
	
	/** 
	 * returns second point
	 * 
	 * @return point
	 */
	public Point getPoint2()
	{
		return _p2;
	}
	
	/** 
	 * returns status of euclidean metric
	 * true: euclidean metric is to be shown
	 * false: eucl. metric is not to be shown
	 * 
	 * @return eucl. metric status
	 */
	public boolean getEuclidStatus()
	{
		return _bEuclidStatus;
	}
	
	/** 
	 * returns status of city block metric
	 * true: city block metric is to be shown
	 * false: city block metric is not to be shown
	 * 
	 * @return city block metric status
	 */
	public boolean getCityBStatus()
	{
		return _bCityStatus;
	}
	
	/** 
	 * returns status of corner edge metric
	 * true: city block metric is to be shown
	 * false: city block metric is not to be shown
	 * 
	 * @return city block metric status
	 */
	public boolean getCornStatus()
	{
		return _bCornStatus;
	}
	
	/** 
	 * changes eucl. metric status between true and false
	 */
	public void changeEuclidStatus()
	{
		_bEuclidStatus = !_bEuclidStatus;		
	}
	
	/** 
	 * changes city block metric status between true and false
	 */

	public void changeCityBStatus()
	{
		_bCityStatus = !_bCityStatus;		
	}
	
	/** 
	 * changes corner edge metric status between true and false
	 */

	public void changeCornStatus()
	{
		_bCornStatus = !_bCornStatus;
	}
	
	/** 
	 * returns distance of 2 points in euclidean metric
	 * 
	 * @return distance between 2 points
	 */
	public double getEuclDistance()
	{
		return _euclmet.getDistance(_p1, _p2);
	}
	
	/** 
	 * returns declaration text of euclidean metric
	 * 
	 * @return declaration text
	 */
	public String getEuclDeclarationText()
	{
		return _euclmet.getDeclarationText();
	}
	
	/**
	 * returns name of metric
	 * 
	 * @return name
	 */
	public String getEuclName()
	{
		return _euclmet.getName();
	}
	
	/** 
	 * returns distance of 2 points in city block metric
	 * 
	 * @return distance between 2 points
	 */
	public double getCityBDistance()
	{
		return _citybmet.getDistance(_p1, _p2);
	}
	
	/** 
	 * returns declaration text of city block metric
	 * 
	 * @return declaration text
	 */
	public String getCityBDeclarationText()
	{
		return _citybmet.getDeclarationText();
	}
	
	/**
	 * returns name of metric
	 * 
	 * @return name
	 */
	public String getCityBName()
	{
		return _citybmet.getName();
	}
	
	/** 
	 * returns distance of 2 points in corner edge metric
	 * 
	 * @return distance between 2 points
	 */
	public double getCornDistance()
	{
		return _cornmet.getDistance(_p1, _p2);
	}
	
	/** 
	 * returns declaration text of corner edge metric
	 * 
	 * @return declaration text
	 */
	public String getCornDeclarationText()
	{
		return _cornmet.getDeclarationText();
	}
	
	/**
	 * returns name of metric
	 * 
	 * @return name
	 */
	public String getCornName()
	{
		return _cornmet.getName();
	}
}
