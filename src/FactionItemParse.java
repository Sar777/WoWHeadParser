enum Type {
	TYPE_ITEM, TYPE_SPELL, TYPE_MAX
}

public class FactionItemParse {

	private String allianceName;
	private String hordeName;

	private String allianceEntry;
	private String hordeEntry;

	public FactionItemParse(String html, int i, int entry) {
		allianceName = "";
		hordeName = "";
		allianceEntry = "";
		hordeEntry = "";
		
		if (html.indexOf("This item will be converted to") == -1)
			return;
		
		String currItemName = html.substring(html.indexOf("<title>") + 7, html.indexOf("- Item") - 1);
		currItemName = currItemName.replace("&#039;", "'");
		
		html = html.substring(html.indexOf("This item will be converted to"), html.indexOf("transfer to"));
		String swapItemName = html.substring(html.lastIndexOf(")\">") + 3, html.indexOf("</a> if you"));
		String swapItemEntry = html.substring(html.indexOf("item=") + 5, html.indexOf("\" class=\""));
		
		if (html.indexOf("icon-horde") != -1)
		{
			allianceEntry += entry;
			hordeEntry = swapItemEntry;
			allianceName = currItemName;
			hordeName = swapItemName;
		}
		else
		{
			hordeEntry += entry;
			allianceEntry = swapItemEntry;
			hordeName = currItemName;
			allianceName = swapItemName;
		}
	}
	
	public final boolean IsValid() {
		return !allianceEntry.isEmpty() && !hordeEntry.isEmpty() &&
				!allianceName.isEmpty() && !hordeName.isEmpty();
	}

	public final String GetAEntry() {
		return allianceEntry;
	}

	public final String GetHEntry() {
		return hordeEntry;
	}

	public final String GetAName() {
		return allianceName;
	}

	public final String GetHName() {
		return hordeName;
	}
}
