package datasource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSetMetaData;

public class InventoryGateway 
{
//Instance Variables
	int ID;
	String UPC;
	int manID;
	int price;
	String desc;
	boolean batteryPowered;
	double length;
	int numberInStrip;
	int numberInBox;
	int type;
	int powertoolID;
	int stripnailID;
//InventoryGateway Constructor
	public InventoryGateway(int iD, String uPC, int manID, int price, String desc, boolean batteryPowered,
			double length, int numberInStrip, int numberInBox, int type, int powertoolID, int stripnailID) 
	{		super();
	ID = iD;
	UPC = uPC;
	this.manID = manID;
	this.price = price;
	this.desc = desc;
	this.batteryPowered = batteryPowered;
	this.length = length;
	this.numberInStrip = numberInStrip;
	this.numberInBox = numberInBox;
	this.type = type;
	this.powertoolID = powertoolID;
	this.stripnailID = stripnailID;
	}
/*
create table PowerToolsToNails
	powertoolsID int, 
	stripnailID int;
*/
//Create table SQL Statements	
	static void createTable()
	{
		SqlManager sql = new SqlManager();
		sql.activateJDBC();
		String query = "create table InventoryItems(\n" + 
				"  ID int,\n" + 
				"  UPC varchar(25),\n" + 
				"  manID int,\n" + 
				"  price int,\n" + 
				"  description varchar(50),\n" + 
				"  batteryPowered boolean,\n" + 
				"  length double,\n" + 
				"  numberInStrip int,\n" + 
				"  numberInBox int,\n" + 
				"  powerToolID int,\n"+
				"  stripNailId int,\n"+
				"  type int,\n" + 
				"  primary key(ID) \n" + 
				//"  foreign key(ID) references InventoryItems(ID)\n" +
				");";


		try {
			PreparedStatement stmnt = sql.createConnection().prepareStatement(query);	
			stmnt.execute();
		} 
		catch (SQLException e) 
		{
			//blank
			e.printStackTrace();
		}
	}
	
//Delete table SQL Statements
	static void deleteTable() {
		SqlManager sql = new SqlManager();
		sql.activateJDBC();
		String query = "DROP TABLE InventoryItems;";

		try {
			PreparedStatement stmnt = sql.createConnection().prepareStatement(query);	
			stmnt.execute();
		} 
		catch (SQLException e) 
		{
			//blank
			e.printStackTrace();
		}

	}
//private static void findAll() {
//	SqlManager sql = new SqlManager();
//	sql.activateJDBC();
//	String query = "Select * FROM InventoryItems WHERE "
//		
//	}
private static void persistModification() {
		
	}
private static void modifyItem(int id, String column, Object newValue) 
	{
		
	}
private  ResultSet findItem(int id) 
{
	InventoryFinder finder = new InventoryFinder();
	return finder.findItem(id);
	
}
//Instance Variable Getters and Setters
	private int getID() {
		return ID;
	}

	private void setID(int iD) {
		ID = iD;
	}

	private String getUPC() {
		return UPC;
	}

	private void setUPC(String uPC) {
		UPC = uPC;
	}

	private int getManID() {
		return manID;
	}

	private void setManID(int manID) {
		this.manID = manID;
	}

	private int getPrice() {
		return price;
	}

	private void setPrice(int price) {
		this.price = price;
	}

	private String getDesc() {
		return desc;
	}

	private void setDesc(String desc) {
		this.desc = desc;
	}

	private boolean isBatteryPowered() {
		return batteryPowered;
	}

	private void setBatteryPowered(boolean batteryPowered) {
		this.batteryPowered = batteryPowered;
	}

	private double getLength() {
		return length;
	}

	private void setLength(double length) {
		this.length = length;
	}

	private int getNumberInStrip() {
		return numberInStrip;
	}

	private void setNumberInStrip(int numberInStrip) {
		this.numberInStrip = numberInStrip;
	}

	private int getNumberInBox() {
		return numberInBox;
	}

	private void setNumberInBox(int numberInBox) {
		this.numberInBox = numberInBox;
	}

	private int getType() {
		return type;
	}

	private void setType(int type) {
		this.type = type;
	}

	private int getPowertoolID() {
		return powertoolID;
	}

	private void setPowertoolID(int powertoolID) {
		this.powertoolID = powertoolID;
	}

	private int getStripnailID() {
		return stripnailID;
	}

	private void setStripnailID(int stripnailID) {
		this.stripnailID = stripnailID;
	}

//Main Method
	public static void main(String[] args) throws SQLException {
		//createTable();
	    //deleteTable();
		//modifyItem();
		//persistModification();
		//findAll(); //Many to Many
		InventoryGateway gw = new InventoryGateway(1, "123456789", 1, 2, "description", true, 2.22, 5,20, 2 , 1, 1);
		ResultSet result = gw.findItem(1);
		ResultSetMetaData rsmd = (ResultSetMetaData) result.getMetaData();
	
		//System.out.println(result.
	
	}


}
