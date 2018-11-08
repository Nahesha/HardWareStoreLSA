

package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import Dtos.*;



public class ToolInventoryItemsGateway {
	  public static void main(String[] args) {
//		  checkTableExistence();
//		  insert("852",5,6,"I'm a power tool",true);
//		  insert("567",3,56,"I'm NOT a power tool",false);
//		  update(43,"222",2,3,"I updated this to be a tool",true);
		  filterByTools(true);

	    }
	
	
	
	private String description;
	private String upc;
	private int manufacturerID;
	private int price;
	private int inventoryItemID;
	private boolean batteryPowered;
	
	public ToolInventoryItemsGateway(int inventoryItemID, String upc, int manufacturerID, int price, String description, boolean batteryPowered)
	{
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.description = description;
		this.inventoryItemID = inventoryItemID;
		this.batteryPowered = batteryPowered;
	}
	// sets the description
	public String setDescription(String sd)
	{
		return description = sd;
	}
	
	// set whether this is a powered tool
	public boolean setPowerTool(boolean bp)
	{
		return batteryPowered = bp;
	}
	
	//sets the upc
	public String setUPC(String u)
	{
		return upc = u;
	}
	//sets the manyfacturerID
	public int setManufacturerID(int mID)
	{
		return manufacturerID = mID;
	}
	public int setPrice(int p)
	{
		return price = p;
	}	
	
	public int settInventoryItemID()
	{
		return inventoryItemID;
	}
	public int getInventoryItemID()
	{
		return inventoryItemID;
	}
	public String getDescription()
	{
		return description;
	}
	public String getUPC()
	{
		return upc;
	}
	public int getManufacturerID()
	{
		return manufacturerID;
	}
	public int getPrice()
	{
		return price;
	}	
	public boolean getPowerTool()
	{
		return batteryPowered;
	}
	
	
  /**
   * This method takes the fields from both the inventoryitems and tools
   * then puts them in the respectful tables
   * @param upc
   * @param manufacturerID
   * @param price
   * @param description
   */
	public static void insert(String upc, int manufacturerID,int price, String description, boolean batteryPowered)
	{
		checkTableExistence();
		 SqlManager sqlm = new SqlManager();
		 sqlm.activateJDBC();
		String InvItemQ = "INSERT INTO InventoryItems(upc,manufacturerID,price) VALUES (?,?,?)";
		try {
			//THIS IS FOR INVENTORY ITEMS
			
			  PreparedStatement invItemStmnt = createInventoryItem(upc, manufacturerID, price, sqlm, InvItemQ);
			  PreparedStatement toolStmnt = createTool(description, sqlm, invItemStmnt);
			  		
			  createPowerTool(batteryPowered, sqlm, toolStmnt);
			  
			 
		  }catch(SQLException e)
		  {
			  e.printStackTrace();
		  }

		
		
	}

	/**
	 * This method updates both the Inventory Items and tools table respectfully
	 * @param InventoryItemID
	 * @param upc
	 * @param manufacturerID
	 * @param price
	 * @param description
	 */
	
	public static void update(int InventoryItemID, String upc, int manufacturerID,int price, String description, boolean batteryPowered)
	{
		checkTableExistence();
		 SqlManager sqlm = new SqlManager();
		 sqlm.activateJDBC();
		 String updateInvItem = "UPDATE InventoryItems SET upc = ?, manufacturerID = ?, price = ? WHERE ID = ?";
		 String updateTools = "UPDATE Tools SET description = ? WHERE InventoryItemID = ? ";
		 String updatePowerTools = "UPDATE PowerTools SET batteryPowered = ? WHERE ToolID = ? ";

		 try {
				PreparedStatement uInvItemStmt = sqlm.createConnection().prepareStatement(updateInvItem);
				uInvItemStmt.setString(1, upc);
				uInvItemStmt.setInt(2, manufacturerID);
				uInvItemStmt.setInt(3, price);
				uInvItemStmt.setInt(4, InventoryItemID);
				uInvItemStmt.execute();
				PreparedStatement uToolStmt = sqlm.createConnection().prepareStatement(updateTools);
				uToolStmt.setString(1, description);
				uToolStmt.setInt(2, InventoryItemID);
				uToolStmt.execute();
				
				int tools_id = findToolIDForPowerTool(InventoryItemID, sqlm);
				PreparedStatement uPowerToolStmt = sqlm.createConnection().prepareStatement(updatePowerTools);
				uPowerToolStmt.setBoolean(1, batteryPowered);
				uPowerToolStmt.setInt(2,tools_id);
				uPowerToolStmt.execute();


			  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
    }
	/* TABLE DATA GATEWAY */


	/**
	 * Returns all the entries in the view
	 * @return a DTO of those instances
	 */
	
	public static ArrayList<InventoryItemDTO> findAll()
	{
		checkTableExistence();
		 SqlManager sqlm = new SqlManager();
		 sqlm.activateJDBC();
			ArrayList<InventoryItemDTO> tInvItemDTO = new ArrayList<>();

			String ToolInvTable = "SELECT * FROM ToolInventoryItems;";
			  
			try {
				PreparedStatement toolInvTable = sqlm.createConnection().prepareStatement(ToolInvTable);
				ResultSet rs = toolInvTable.executeQuery();
				while(rs.next())
				{
					InventoryItemDTO tII = new ToolDTO(rs.getInt("InventoryItemID"),rs.getString("upc"),rs.getInt("manufacturerID"),rs.getInt("price"),rs.getString("description"));
					tInvItemDTO.add(tII);

				}
			  } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return tInvItemDTO;

	}
	
	
	/**
	 * Allows for searching of a specific UPC
	 * @param upc
	 * @return
	 */

	public static ArrayList<InventoryItemDTO> filterByTools(boolean batteryPowered)
	{
		 checkTableExistence();
		 SqlManager sqlm = new SqlManager();
		 sqlm.activateJDBC();
			ArrayList<InventoryItemDTO> tInvItemDTO = new ArrayList<>();

		 String powerTools = "SELECT * FROM ToolInventoryItems WHERE batteryPowered = ?";
		try {
			 PreparedStatement pt = sqlm.createConnection().prepareStatement(powerTools);
			 pt.setBoolean(1, batteryPowered);
			 ResultSet rs = pt.executeQuery();
			while(rs.next())
			{
				InventoryItemDTO tII = new ToolDTO(rs.getInt("InventoryItemID"),rs.getString("upc"),rs.getInt("manufacturerID"),rs.getInt("price"),rs.getString("description"));
				tInvItemDTO.add(tII);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tInvItemDTO;
			
	}
	
	
	/**
	 * Allows for searching of a specific UPC
	 * @param upc
	 * @return
	 */

	public static ArrayList<InventoryItemDTO> findByUPC(String upc)
	{
		 checkTableExistence();
		 SqlManager sqlm = new SqlManager();
		 sqlm.activateJDBC();
			ArrayList<InventoryItemDTO> tInvItemDTO = new ArrayList<>();

		 String findUPCQ = "SELECT * FROM ToolInventoryItems WHERE upc = ?";
		try {
			 PreparedStatement toolInvTable = sqlm.createConnection().prepareStatement(findUPCQ);
			 toolInvTable.setString(1, upc);
			ResultSet rs = toolInvTable.executeQuery();
			while(rs.next())
			{
				InventoryItemDTO tII = new ToolDTO(rs.getInt("InventoryItemID"),rs.getString("upc"),rs.getInt("manufacturerID"),rs.getInt("price"),rs.getString("description"));
				tInvItemDTO.add(tII);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tInvItemDTO;
			
	}
	
	/**
	 *  Allows for searching of Items by their specific manufacturer ID
	 * @return the DTO of that said object

	 **/
	public static ArrayList<InventoryItemDTO> findmanufacturerID(int manufacturerID)
	{
		 checkTableExistence();
		 SqlManager sqlm = new SqlManager();
		 sqlm.activateJDBC();
			ArrayList<InventoryItemDTO> tInvItemDTO = new ArrayList<>();

		 String findManufIDQ = "SELECT * FROM ToolInventoryItems WHERE manufacturerID = ?";
		try {
			 PreparedStatement toolInvTable = sqlm.createConnection().prepareStatement(findManufIDQ);
			 toolInvTable.setInt(1, manufacturerID);
			 ResultSet rs = toolInvTable.executeQuery();
			while(rs.next())
			{
				InventoryItemDTO tII = new ToolDTO(rs.getInt("InventoryItemID"),rs.getString("upc"),rs.getInt("manufacturerID"),rs.getInt("price"),rs.getString("description"));
				tInvItemDTO.add(tII);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tInvItemDTO;
			
	}


	
	
	/* ROW DATA GATEWAY */
	/**
	 * Finds the instance via by id 
	 * @param invItemID
	 * @return
	 */
	   public ToolInventoryItemsGateway find(int invItemID)
	   {
		   checkTableExistence();
			 SqlManager sqlm = new SqlManager();
			 sqlm.activateJDBC();
			 String itemQ = "SELECT upc,manufacturerID,price,description,batteryPowered FROM ToolInventoryItems WHERE InventoryItemID =? ";
			 try {
					
				  PreparedStatement invItemStmnt = sqlm.createConnection().prepareStatement(itemQ);	
				  invItemStmnt.setInt(1,invItemID);
				  ResultSet rs = invItemStmnt.executeQuery();
				  while(rs.next())
				  {
					  String upc = rs.getString("upc");
					  int mID = rs.getInt("manufacturerID");
					  int price = rs.getInt("price");
					  String description = rs.getString("description");
					  boolean bp = rs.getBoolean("batteryPowered");

					  ToolInventoryItemsGateway tIIG = new ToolInventoryItemsGateway(invItemID,upc,mID,price,description, batteryPowered);
					  return tIIG;
				  }
				  
				
	           
			  }catch(SQLException e)
			  {
				  e.printStackTrace();
			  }
			 return null;
	   }
	   
	   /*
		 * This method only creates a new table for InventoryItems and Tools if not existent. 
		 * If the tools and InventoryItems table exist a view  (which is the join of the two)
		 * is created ONLY if the view doesn't already exist.
		 */
		private static void checkTableExistence() {
		 SqlManager sqlm = new SqlManager();
		 sqlm.activateJDBC();
		 String inventoryItemQuery = "CREATE TABLE IF NOT EXISTS InventoryItems" + "("+ 
				 												"ID int NOT NULL AUTO_INCREMENT,\n"+
				 												"upc varchar(25)\n," +
				 												"manufacturerID int\n," +
				 												" price int,\n" +
				 												"PRIMARY KEY (ID)" + ");";
		 String powerToolQuery = "CREATE TABLE IF NOT EXISTS PowerTools" + "("+ 
					"ToolID int NOT NULL\n," +
					"batteryPowered boolean\n,"+
					"FOREIGN KEY (ToolID) REFERENCES Tools(ID)" + ");";
		 
	     String toolsQuery = "CREATE TABLE IF NOT EXISTS Tools" + "("+ "id int NOT NULL AUTO_INCREMENT,\n"+
	    		 															 "InventoryItemID int NOT NULL,\n" +
	    		 															 "description varchar(50) ,\n" +
	    		 															 "FOREIGN KEY (InventoryItemID) " +
	    		 															 "REFERENCES InventoryItems(ID),\n" + 
	    		 															 "PRIMARY KEY (ID)" + ");";
	     String ToolInventoryItemQuery = "CREATE OR REPLACE VIEW ToolInventoryItems AS SELECT Tools.InventoryItemID, upc,"
	     									+ " manufacturerID,price,description, batteryPowered"
	     									+ " FROM InventoryItems"
	     									+ " INNER JOIN Tools ON InventoryItems.ID = Tools.InventoryItemID"
	     									+ " INNER JOIN PowerTools ON Tools.ID = PowerTools.ToolID;\n";
	     

	 
		  try {
			  PreparedStatement toolsStmnt = sqlm.createConnection().prepareStatement(toolsQuery);	
			  PreparedStatement inventoryItemStmnt = sqlm.createConnection().prepareStatement(inventoryItemQuery);	
			  PreparedStatement powerToolStmnt = sqlm.createConnection().prepareStatement(powerToolQuery);	
			  PreparedStatement toolInventoryItemStmnt = sqlm.createConnection().prepareStatement(ToolInventoryItemQuery);	
			  
			  powerToolStmnt.execute();
			  toolsStmnt.execute();
			  inventoryItemStmnt.execute();
			  toolInventoryItemStmnt.execute();


		  }catch(SQLException e)
		  {
			  e.printStackTrace();
		  }

		
		}
		
		private static int findToolIDForPowerTool(int InventoryItemID, SqlManager sqlm) throws SQLException {
			String tool_id = "SELECT id FROM Tools WHERE InventoryItemID = ? ";
			
			PreparedStatement ToolIDStmt = sqlm.createConnection().prepareStatement(tool_id);
			ToolIDStmt.setInt(1, InventoryItemID);
			
			 ResultSet rs2 = ToolIDStmt.executeQuery();
			  int tools_id = 0;
			  //gets the id from the Tools ID
			  if(rs2.next())
			  {
				  tools_id = rs2.getInt("id");
			  }
			return tools_id;
		}
		
		private static PreparedStatement createInventoryItem(String upc, int manufacturerID, int price, SqlManager sqlm,
				String InvItemQ) throws SQLException {
			PreparedStatement invItemStmnt = sqlm.createConnection().prepareStatement(InvItemQ,Statement.RETURN_GENERATED_KEYS);	
			  invItemStmnt.setString(1, upc);
			  invItemStmnt.setInt(2, manufacturerID);
			  invItemStmnt.setInt(3, price);
			  invItemStmnt.execute();
			return invItemStmnt;
		}
		private static void createPowerTool(boolean batteryPowered, SqlManager sqlm, PreparedStatement toolStmnt)
				throws SQLException {
			  ResultSet rs2 = toolStmnt.getGeneratedKeys();
			  int tools_id = 0;
			  //gets the id from the Tools ID
			  if(rs2.next())
			  {
				  tools_id = rs2.getInt(1);
			  }
			  //allows me to insert the id from the Tools into Powertools

			  String PowerToolQ = "INSERT INTO PowerTools(ToolID,batteryPowered) VALUES (?, ?)";
			  PreparedStatement powerToolStmnt = sqlm.createConnection().prepareStatement(PowerToolQ,Statement.RETURN_GENERATED_KEYS);
			  powerToolStmnt.setInt(1,tools_id);
			  powerToolStmnt.setBoolean(2, batteryPowered);
			  powerToolStmnt.execute();
		}
		private static PreparedStatement createTool(String description, SqlManager sqlm, PreparedStatement invItemStmnt)
				throws SQLException {
			  //once the inventory item was executed, getGeneratedKeys will give me the results of the autogenerated ID
			  ResultSet rs = invItemStmnt.getGeneratedKeys();
			  int invItem_id = 0;
			  //gets the id from the Inventory Item ID
			  if(rs.next())
			  {
				  invItem_id = rs.getInt(1);
			  }
			  //allows me to insert the id from the InventoryItem into tools
			  String toolQ = "INSERT INTO Tools(InventoryItemID,description) VALUES (?, ?)";
			  PreparedStatement toolStmnt = sqlm.createConnection().prepareStatement(toolQ,Statement.RETURN_GENERATED_KEYS);
			  toolStmnt.setInt(1,invItem_id);
			  toolStmnt.setString(2, description);
			  toolStmnt.execute();
			return toolStmnt;
		}

}