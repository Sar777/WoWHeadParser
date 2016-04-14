package Parsers;

import java.net.MalformedURLException;
import java.net.URL;

import Loaders.Loader;
import LootTemplates.CreatureLoot;
import LootTemplates.Loot;
import LootTemplates.LootTemplate;
import Managers.ParseDataMgr;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class CreatureLootParser implements Runnable {
	int entry;

	public CreatureLootParser(int entry) {
		super();
		this.entry = entry;
	}

	@Override
	public void run() {
		System.out.println("CreatureLootParser: Parse loot npc for " + entry);
		String htmlText = "";
		try {
			htmlText = new Loader().LoadHtml(new URL("http://wowhead.com/npc=" + entry));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (htmlText.isEmpty())
			return;
		
		int titlePos = htmlText.indexOf("<title>");
		if (titlePos == -1)
			return;
		
		Loot loot = new CreatureLoot(entry, htmlText.substring(titlePos + 7, htmlText.indexOf(" - NPC -")));
		
		int idx = htmlText.indexOf("new Listview({template: 'item', id: 'drops'");
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
			System.out.println("CreatureLootParser: Parsing problem npc " + entry);
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
			lItem.setChance(Float.parseFloat(object.get("pctstack") != null ? object.get("pctstack").getAsJsonObject().get("1").getAsString() : "0"));
			lItem.setComment(object.get("name").toString().replaceAll("\"", "").substring(1));
			loot.getLoots().add(lItem);
		}
		
		if (loot.getLoots().size() > 0) {
			ParseDataMgr.getInstance().getData().add(new CreatureLoot(loot));
		}
	}

}
