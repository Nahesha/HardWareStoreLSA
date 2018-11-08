package Dtos;

public class PowerToolDTO implements InventoryItemDTO
{
	private int id;
	private String upc;
	private int manufacturerID;
	private int price;
	private String description;
	private boolean isBatteryPowered;
	
	public PowerToolDTO(int id, String upc, int manufacturerID, int price, String description, boolean isBatteryPowered)
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
	
	public boolean getIsBatteryPowered()
	{
		return isBatteryPowered;
	}
}
