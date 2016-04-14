package LootTemplates;

import java.util.ArrayList;
import java.util.List;

public class CreatesTradeSkill {
	private int Entry;
	private String Name;
	private List<String> Data;
	
	public CreatesTradeSkill(int entry, String name) {
		this.Entry = entry;
		this.Name = name;
		this.Data = new ArrayList<String>();
	}
	
	public CreatesTradeSkill(CreatesTradeSkill creates) {
		this.Entry = creates.Entry;
		this.Name = creates.Name;
		this.Data = creates.Data;
	}
	
	public void AddItem(int item, String comment) {
		Data.add("(" + Entry + ", " + item + ", 100, \"" + comment + "\"),\n");
	}
	
	public int Size() {
		return Data.size();
	}
	
	@Override
	public String toString() {
		if (Data.size() == 0)
			return "";
		
		String data = "-- " + Name + "\n";
		data += "DELETE FROM `spell_loot_template` WHERE `entry` = " + Entry + ";\n";
		data += "INSERT INTO `spell_loot_template` (`Entry`, `Item`, `Chance`, `Comment`) VALUES\n";
		for (String line : Data) {
			data += line;
		}

		data = data.substring(0, data.lastIndexOf("),"));
		data += ");\n";
		return data;
	}
}
