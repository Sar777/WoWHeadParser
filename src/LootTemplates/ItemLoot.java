package LootTemplates;

public class ItemLoot extends Loot {
	
	public ItemLoot(int entry, String name) {
		super(entry, name);
	}
	
	public ItemLoot(Loot loot) {
		super(loot);
	}
	
	@Override
	public String toString() {
		if (loots.size() == 0) {
			return "";
		}
		
		String data = "-- " + name + "\n";
		data += "DELETE FROM `item_loot_template` WHERE `entry` = " + entry + ";\n";
		data += "INSERT INTO `item_loot_template` (`Entry`, `Item`, `Reference`, `Chance`, `QuestRequired`, `LootMode`, `GroupId`, `MinCount`, `MaxCount`, `Comment`) VALUES\n";
		data += super.toString();
		return data;
	}
}
