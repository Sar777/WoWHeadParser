package Managers;

import java.util.ArrayList;
import java.util.List;

public class ParseDataMgr {
	private List<Object> loot;
	
	private static final ParseDataMgr instance = new ParseDataMgr();
 
	protected ParseDataMgr() {
		this.loot = new ArrayList<Object>();
	}
 
	public static ParseDataMgr getInstance() {
		return instance;
	}
	
	public List<Object> getData() {
		return loot;
	}
}