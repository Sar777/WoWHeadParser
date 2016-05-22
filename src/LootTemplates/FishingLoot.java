package LootTemplates;

public class FishingLoot extends Loot {
	public FishingLoot(int entry, String name) {
		super(entry, name);
	}
	
	public FishingLoot(Loot loot) {
		super(loot);
	}
	
	@Override
	public String toString() {
		if (loots.size() == 0) {
			return "";
		}
		
		String data = "-- " + name + "\n";
		data += "DELETE FROM `fishing_loot_template` WHERE `entry` = " + entry + ";\n";
		data += "INSERT INTO `fishing_loot_template` (`Entry`, `Item`, `Reference`, `Chance`, `QuestRequired`, `LootMode`, `GroupId`, `MinCount`, `MaxCount`, `Comment`) VALUES\n";
		data += super.toString();
		return data;
	}
}
