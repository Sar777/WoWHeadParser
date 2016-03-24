package LootTemplates;

import java.util.ArrayList;
import java.util.List;

public class Loot {
	int entry;
	String name;
	List<LootTemplate> loots;
	
	public Loot(int entry, String name) {
		this.entry = entry;
		this.name = name;
		this.loots = new ArrayList<LootTemplate>();
	}
	
	public Loot(Loot loot) {
		this.entry = loot.entry;
		this.name = loot.name;
		this.loots = loot.loots;
	}
	
	@Override
	public String toString() {
		if (loots.size() == 0) {
			return "";
		}

		String data = "";
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
