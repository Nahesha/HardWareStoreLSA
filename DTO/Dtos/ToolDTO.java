package Dtos;

public class ToolDTO implements InventoryItemDTO
{
	private int id;
	private String upc;
	private int manufacturerID;
	private int price;
	private String description;
	
	public ToolDTO(int id, String upc, int manufacturerID, int price, String description)
	{
		this.id = id;
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.description = description;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getUpc()
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
	
	public String getDescription()
	{
		return description;
	}
}
