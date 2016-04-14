package Parsers;

import java.net.MalformedURLException;
import java.net.URL;

import Loaders.Loader;
import LootTemplates.Loot;
import LootTemplates.LootTemplate;
import LootTemplates.MillingLoot;
import Managers.ParseDataMgr;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class MillingLootParser implements Runnable {
	int entry;

	public MillingLootParser(int entry) {
		super();
		this.entry = entry;
	}

	@Override
	public void run() {
		System.out.println("MillingLootParser: Parse loot for " + entry);
		String htmlText = "";
		try {
			htmlText = new Loader().LoadHtml(new URL("http://wowhead.com/item=" + entry));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (htmlText.isEmpty())
			return;
		
		int titlePos = htmlText.indexOf("<title>");
		if (titlePos == -1)
			return;
		
		Loot loot = new MillingLoot(entry, htmlText.substring(titlePos + 7, htmlText.indexOf(" - Item -")));
		
		int idx = htmlText.indexOf("new Listview({template: 'item', id: 'milling'");
		if (idx == -1)
			return;	
		
		htmlText = htmlText.substring(idx);
		idx = htmlText.indexOf("}]});");
		if (idx == -1)
			return;
		
		htmlText = htmlText.substring(htmlText.indexOf("data: [{") + 6, idx + 2);
		
		// Validate
		htmlText = htmlText.replaceAll("(\\w+)\\s*\\:","\"$1\" :");
		htmlText = htmlText.replaceAll("'", "");
		
		JsonArray items = null;
		try {
			JsonParser parser = new JsonParser();
			items = parser.parse(htmlText).getAsJsonArray();	
		} catch (JsonSyntaxException e) {
			System.out.println("MillingLootParser: Parsing problem item " + entry);
			System.out.println(e.getMessage());
			return;
		}
		
		if (items.size() < 1) {
			return;
		}

		for (JsonElement el : items) {
			JsonObject object = el.getAsJsonObject();
			LootTemplate lItem = new LootTemplate();
			lItem.setEntry(entry);
			lItem.setItem(Integer.parseInt(object.get("id").toString()));
			lItem.setMincount(Integer.parseInt(object.get("stack").getAsJsonArray().get(0).getAsString()));
			lItem.setMaxcount(Integer.parseInt(object.get("stack").getAsJsonArray().get(1).getAsString()));
			lItem.setChance(0);
			lItem.setComment(object.get("name").toString().replaceAll("\"", "").substring(1));
			loot.getLoots().add(lItem);
		}

		if (loot.getLoots().size() > 0) {
			ParseDataMgr.getInstance().getData().add(new MillingLoot(loot));
		}
	}
}
