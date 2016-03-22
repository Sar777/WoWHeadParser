package Loaders;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Loader {
	private String htmlText;
	public synchronized String LoadHtml(URL url) {
		htmlText = "";
		BufferedReader in = null;
		try {
			URLConnection spoof = url.openConnection();
			spoof.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)");
			spoof.setRequestProperty("Accept-language", "en");
			in = new BufferedReader(new InputStreamReader(spoof.getInputStream()));
			String inputLine;
			while ((inputLine = in.readLine()) != null)
				htmlText = htmlText + inputLine + "\n";
		} catch (FileNotFoundException e) {
		} catch (MalformedURLException e) {	
		} catch (IOException e) {
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		};

		return htmlText;
	}
	
	public final boolean IsValid() {
		return !htmlText.isEmpty();
	}
}
