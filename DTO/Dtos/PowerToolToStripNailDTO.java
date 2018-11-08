package Dtos;

public class PowerToolToStripNailDTO implements InventoryItemDTO
{
	private ToolDTO powerTool;
	private StripNailDTO stripNail;
	
	public PowerToolToStripNailDTO(ToolDTO powerTool, StripNailDTO stripNail)
	{
		this.powerTool = powerTool;
		this.stripNail = stripNail;
	}
	
	public ToolDTO getPowerTool()
	{
		return powerTool;
	}
	
	public StripNailDTO getStripNail()
	{
		return stripNail;
	}
}
