package Parsers;

import java.net.MalformedURLException;
import java.net.URL;

import Loaders.Loader;
import LootTemplates.CreatesTradeSkill;
import Managers.ParseDataMgr;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class CreatesItemParser implements Runnable {
	int entry;

	public CreatesItemParser(int entry) {
		super();
		this.entry = entry;
	}

	@Override
	public void run() {
		System.out.println("CreatesItemParser: Parse " + entry);
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
		
		int spellPos = htmlText.indexOf("Use: <a href=\"/spell=");
		if (spellPos == -1)
			return;
		
		String temp = htmlText.substring(spellPos, spellPos + 50);
		entry = Integer.parseInt(temp.substring(21, temp.indexOf("\" class=\"")));
		
		CreatesTradeSkill creates = new CreatesTradeSkill(entry, htmlText.substring(titlePos + 7, htmlText.indexOf(" - Item -")));
		
		int idx = htmlText.indexOf("new Listview({template: 'item', id: 'creates'");
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
			System.out.println("CreatesItemParser: Parsing problem item " + entry);
			System.out.println(e.getMessage());
			return;
		}
		
		if (items.size() < 1) {
			return;
		}

		for (JsonElement el : items) {
			JsonObject object = el.getAsJsonObject();
			creates.AddItem(Integer.parseInt(object.get("id").toString()), object.get("name").toString().replaceAll("\"", "").substring(1));
		}
		
		if (creates.Size() > 0) {
			ParseDataMgr.getInstance().getData().add(new CreatesTradeSkill(creates));
		}
	}
}
