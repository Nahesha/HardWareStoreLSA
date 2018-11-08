package Dtos;

public class StripNailDTO implements InventoryItemDTO
{
	private int numberInStrip;
	private int id;
	private String upc;
	private int manufacturerID;
	private int price;
	private double length;
	
	public StripNailDTO(int id, String upc, int manufacturerID, int price, double length, int numberInStrip)
	{
		this.id = id;
		this.upc = upc;
		this.manufacturerID = manufacturerID;
		this.price = price;
		this.length = length;
		this.numberInStrip = numberInStrip;
	}
	
	public int getNumberInStrip()
	{
		return numberInStrip;
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

	public double getLength()
	{
		return length;
	}
}