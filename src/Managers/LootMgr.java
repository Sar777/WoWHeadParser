package Managers;

import java.util.ArrayList;
import java.util.List;

public class LootMgr {
	private List<Object> loot;
	
	private static final LootMgr instance = new LootMgr();
 
	protected LootMgr() {
		this.loot = new ArrayList<Object>();
	}
 
	public static LootMgr getInstance() {
		return instance;
	}
	
	public List<Object> getLoot() {
		return loot;
	}
}