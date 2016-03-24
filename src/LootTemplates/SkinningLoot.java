package LootTemplates;

public class SkinningLoot extends Loot {
	public SkinningLoot(int entry, String name) {
		super(entry, name);
	}
	
	public SkinningLoot(Loot loot) {
		super(loot);
	}

	@Override
	public String toString() {
		if (loots.size() == 0) {
			return "";
		}
		
		String data = "-- " + name + "\n";
		data += "UPDATE `creature_template` SET `skinloot` = " + entry + " WHERE `entry` = " + entry + ";\n";
		data += "DELETE FROM `skinning_loot_template` WHERE `entry` = " + entry + ";\n";
		data += "INSERT INTO `skinning_loot_template` (`Entry`, `Item`, `Reference`, `Chance`, `QuestRequired`, `LootMode`, `GroupId`, `MinCount`, `MaxCount`, `Comment`) VALUES\n";
		data += super.toString();
		return data;
	}
}
