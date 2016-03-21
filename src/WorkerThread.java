import java.net.URL;
import java.util.concurrent.Callable;

import Loaders.Loader;

public class WorkerThread implements Callable<String> {
	private String thread;
	private int entry;

	public WorkerThread(String thread, int entry) {
		this.thread = thread;
		this.entry = entry;
	}
	
	@Override
    public String call() throws Exception {
		String htmlText = new Loader().LoadHtml(new URL("http://wowhead.com/item=" + entry));
		if (htmlText.isEmpty())
			return "";

		FactionItemParse parse = new FactionItemParse(htmlText, 0, entry);
		if (!parse.IsValid())
			return "";
		
		String data = "-- http://www.wowhead.com/item=" + entry + "\n";
		data += "DELETE FROM `player_factionchange_items` WHERE `alliance_id` = " + parse.GetAEntry() + " AND `horde_id` = " + parse.GetHEntry() + ";\n";
		data += "INSERT INTO `player_factionchange_items` (`alliance_id`, `commentA`, `horde_id`, `commentH`) VALUES (" + parse.GetAEntry() + ", \"" + parse.GetAName() + "\", " + parse.GetHEntry() + ", \"" + parse.GetHName() + "\");\n\n";
		System.out.println("Parsing OK. " + thread + "; Entry: " + entry);
		return data;
    }
}