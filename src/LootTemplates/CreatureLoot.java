package LootTemplates;

public class CreatureLoot extends Loot {
	public CreatureLoot(int entry, String name) {
		super(entry, name);
	}
	
	public CreatureLoot(Loot loot) {
		super(loot);
	}
	
	@Override
	public String toString() {
		if (loots.size() == 0) {
			return "";
		}
		
		String data = "-- " + name + "\n";
		data += "UPDATE `creature_template` SET `lootid` = " + entry + " WHERE `entry` = " + entry + ";\n";
		data += "DELETE FROM `creature_loot_template` WHERE `entry` = " + entry + ";\n";
		data += "INSERT INTO `creature_loot_template` (`Entry`, `Item`, `Reference`, `Chance`, `QuestRequired`, `LootMode`, `GroupId`, `MinCount`, `MaxCount`, `Comment`) VALUES\n";
		data += super.toString();
		return data;
	}
}
