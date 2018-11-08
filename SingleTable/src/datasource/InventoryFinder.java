package datasource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryFinder 
{
	public InventoryFinder()
	{
		
	}
	public ResultSet findItem(int ID)
	{
		SqlManager manager = new SqlManager();
		manager.activateJDBC();
		
		String query = "Select * from InventoryItems WHERE (ID = ?);";
		PreparedStatement preparedStatement;
		
		try 
		{
			preparedStatement = manager.createConnection().prepareStatement(query);
			preparedStatement.setInt(1, ID);
			return preparedStatement.executeQuery();
		} 
		
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
}

