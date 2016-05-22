package Parsers;

import java.net.MalformedURLException;
import java.net.URL;

import Common.Profession;
import Common.ProfessionUpgradeStore;
import Loaders.Loader;
import LootTemplates.Loot;
import LootTemplates.LootTemplate;
import LootTemplates.SkinningLoot;
import Managers.ParseDataMgr;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class ProfessionUpgradeParser implements Runnable {
	int entry;

	public ProfessionUpgradeParser(int entry) {
		super();
		this.entry = entry;
	}

	@Override
	public void run() {
		System.out.println("ProfessionUpgrade: Parse prof upgrade item for " + entry);
		String htmlText = "";
		try {
			htmlText = new Loader().LoadHtml(new URL("http://wowhead.com/item=" + entry));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (htmlText.isEmpty())
			return;
		
		int idxSpell = htmlText.indexOf("Use: <a href=\"/spell=");
		if (idxSpell == -1)
			return;
		
		String spell = htmlText.substring(idxSpell + 21, htmlText.indexOf("\" class=\"q2\">"));
		
		int idx = htmlText.indexOf("new Listview({template: 'item', id: 'profession-upgrades'");
		if (idx == -1)
			return;
		
		htmlText = htmlText.substring(idx);
		idx = htmlText.indexOf("}]});");
		if (idx == -1)
			return;
		
		ProfessionUpgradeStore store = new ProfessionUpgradeStore();
		
		htmlText = htmlText.substring(htmlText.indexOf("data: [") + 6, idx + 2);
		
		// Validate
		htmlText = htmlText.replaceAll("(\\w+)\\s*\\:","\"$1\" :");
		htmlText = htmlText.replaceAll("'", "");
		
		JsonArray items = null;
		try {
			JsonParser parser = new JsonParser();
			items = parser.parse(htmlText).getAsJsonArray();	
		} catch (JsonSyntaxException e) {
			System.out.println("ProfessionUpgrade: Parsing problem item " + entry);
			return;
		}
		
		if (items.size() < 1) {
			return;
		}

		for (JsonElement el : items) {
			JsonObject object = el.getAsJsonObject();
			Profession p = new Profession();
			p.item = Integer.parseInt(object.get("id").toString());
			p.spell = Integer.parseInt(spell);
			store.addProf(p);
		}
		
		if (store.getUpgrades().size() > 0) {
			ParseDataMgr.getInstance().getData().add(new ProfessionUpgradeStore(store));
		}
	}
}
