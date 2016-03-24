package LootTemplates;

public class LootTemplate {
	int entry;
	int item;
	int reference;
	float chance;
	int lootmode;
	int groupid;
	int mincount;
	int maxcount;
	String comment;
	
	public LootTemplate() {
		this.entry = 0;
		this.item = 0;
		this.reference = 0;
		this.chance = 0.0f;
		this.lootmode = 0;
		this.groupid = 0;
		this.mincount = 0;
		this.maxcount = 0;
		this.comment = "";
	}
	
	public LootTemplate(LootTemplate loot) {
	    this.entry = loot.entry;
	    this.item = loot.item;
	    this.reference = loot.reference;
	    this.chance = loot.chance;
	    this.lootmode = loot.lootmode;
	    this.groupid = loot.groupid;
	    this.mincount = loot.mincount;
	    this.maxcount = loot.maxcount;
	    this.comment = loot.comment;
	}
	
	@Override
	public String toString() {
		return "(" + entry + ", " + item + ", " + reference + ", " 
				   + chance + ", " + lootmode + ", 1, " + groupid + ", "
				   + mincount + ", " + maxcount + ", \"" + comment + "\")";
	}
	
	public int getEntry() {
		return entry;
	}

	public void setEntry(int entry) {
		this.entry = entry;
	}

	public int getItem() {
		return item;
	}

	public void setItem(int item) {
		this.item = item;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public float getChance() {
		return chance;
	}

	public void setChance(float chance) {
		this.chance = chance;
	}

	public int getLootmode() {
		return lootmode;
	}

	public void setLootmode(int lootmode) {
		this.lootmode = lootmode;
	}

	public int getGroupid() {
		return groupid;
	}

	public void setGroupid(int groupid) {
		this.groupid = groupid;
	}

	public int getMincount() {
		return mincount;
	}

	public void setMincount(int mincount) {
		this.mincount = mincount;
	}

	public int getMaxcount() {
		return maxcount;
	}

	public void setMaxcount(int maxcount) {
		this.maxcount = maxcount;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
