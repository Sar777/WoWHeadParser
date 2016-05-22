package Common;

import java.util.ArrayList;
import java.util.List;

public class ProfessionUpgradeStore {
	private List<Profession> list;
	
	public ProfessionUpgradeStore() {
		list = new ArrayList<Profession>();
	}
	
	public ProfessionUpgradeStore(ProfessionUpgradeStore store) {
		this.list = store.getUpgrades();
	}
	
	@Override
	public String toString() {
		String data = "INSERT INTO `item_spell_change_bonus` (`Id`, `spellId`) VALUES\n";
		for (Profession prof : list) {
			data += prof.toString() + ",\n";
		}
		
		data = data.substring(0, data.length() - 2);
		data += ";\n";
		return data;
	}
	
	public void addProf(Profession prof) {
		list.add(prof);
	}
	
	public List<Profession> getUpgrades() {
		return list;
	}
}
