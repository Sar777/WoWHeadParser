package LootTemplates;

public class MillingLoot extends Loot {
	public MillingLoot(int entry, String name) {
		super(entry, name);
	}
	
	public MillingLoot(Loot loot) {
		super(loot);
	}
	
	@Override
	public String toString() {
		if (loots.size() == 0) {
			return "";
		}
		
		String data = "-- " + name + "\n";
		data += "DELETE FROM `milling_loot_template` WHERE `entry` = " + entry + ";\n";
		data += "INSERT INTO `milling_loot_template` (`Entry`, `Item`, `Reference`, `Chance`, `QuestRequired`, `LootMode`, `GroupId`, `MinCount`, `MaxCount`, `Comment`) VALUES\n";
		data += super.toString();
		return data;
	}
}
