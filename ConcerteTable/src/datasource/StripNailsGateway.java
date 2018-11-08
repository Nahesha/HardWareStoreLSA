package datasource;

public class StripNailsGateway {

	private int id;
	private String upc;
	private int type;
	private int manufacturerID;
	private int price;
	private double length;
	private int numberInStrip;
	
	public StripNailsGateway() {
		// TODO Auto-generated constructor stub
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
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public int getNumberInStrip() {
		return numberInStrip;
	}
	public void setNumberInStrip(int numberInStrip) {
		this.numberInStrip = numberInStrip;
	}

}
