package LootTemplates;

import java.util.ArrayList;
import java.util.List;

public class ItemLoot {
	int entry;
	String name;
	List<LootTemplate> loots;
	
	public ItemLoot(int entry, String name) {
		this.entry = entry;
		this.name = name;
		this.loots = new ArrayList<LootTemplate>();
	}
	
	public ItemLoot(ItemLoot loot) {
	    this.entry = loot.entry;
	    this.name = loot.name;
	    this.loots = loot.loots;
	}
	
	@Override
	public String toString() {
		if (loots.size() == 0) {
			return "";
		}
		
		String data = "-- " + name + "\n";
		data += "DELETE FROM `item_loot_template` WHERE `entry` = " + entry + ";\n";
		data += "INSERT INTO `item_loot_template` (`Entry`, `Item`, `Reference`, `Chance`, `QuestRequired`, `LootMode`, `GroupId`, `MinCount`, `MaxCount`, `Comment`) VALUES\n";
		for (LootTemplate loot : loots) {
			data += loot.toString() + ",\n";
		}

		return data.substring(0, data.lastIndexOf(',')) + ";\n";
	}
	
	public int getEntry() {
		return entry;
	}

	public void setEntry(int entry) {
		this.entry = entry;
	}

	public List<LootTemplate> getLoots() {
		return loots;
	}
}
