package Managers;

import java.util.ArrayList;
import java.util.List;

import LootTemplates.ItemLoot;

public class ItemLootMgr {
	private List<ItemLoot> loot;
	
	private static final ItemLootMgr instance = new ItemLootMgr();
 
	protected ItemLootMgr() {
		this.loot = new ArrayList<ItemLoot>();
	}
 
	public static ItemLootMgr getInstance() {
		return instance;
	}
	
	public List<ItemLoot> getLoot() {
		return loot;
	}
}
