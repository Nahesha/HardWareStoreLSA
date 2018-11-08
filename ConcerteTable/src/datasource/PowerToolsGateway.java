package datasource;

public class PowerToolsGateway {

	private int id;
	private String upc;
	private int type;
	private int manufacturerID;
	private int price;
	private String description;
	private boolean batteryPowered;
	
	public PowerToolsGateway() {
		// TODO Auto-generated constructor stub
	}

	public boolean isBatteryPowered() {
		return batteryPowered;
	}

	public void setBatteryPowered(boolean batteryPowered) {
		this.batteryPowered = batteryPowered;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUpc() {
		return upc;
	}
	public void setUpc(String upc) {
		this.upc = upc;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getManufacturerID() {
		return manufacturerID;
	}
	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
