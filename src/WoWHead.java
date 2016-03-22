
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Common.ParserType;
import Managers.LootMgr;
import Parsers.DisenchantLootParser;
import Parsers.SkiningLootParser;

public class WoWHead {

	private static String filename;
	private static Integer threadCount, range1, range2;
	private static ParserType parseType;

	public static void main(String[] args) {
		if (!InitialParserParams(args))
			return;

		Parse();
	}
	
	private static Boolean InitialParserParams(String[] args) {
		if (args.length == 0) {
			System.out.println("Error arguments");
			return false;
		}
		
		try
		{
			parseType = ParserType.fromInteger(Integer.valueOf(args[0]));
			filename = args[1]; // название файла
			threadCount = Integer.valueOf(args[2]); // кол-во потоков
			range1 = Integer.valueOf(args[3]); // от
			range2 = Integer.valueOf(args[4]); // до
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error arguments.");
			return false;
		} catch (NumberFormatException e) {
			System.out.println("Error arguments.");
			return false;
		}
		
		if (range1 == range2) {
			System.out.println("Error: range1 == range2");
			return false;
		}
		
		if (range1 > range2) {
			Integer temp = range2;
			range2 = range1;
			range1 = temp;
		}
		
		if (threadCount <= 0) {
			System.out.println("Error: thread number <= 0");
			return false;
		}
		
		if (parseType == ParserType.PARSER_NONE) {
			System.out.println("Unknown parser type");
			return false;
		}
		
		return true;
	}

	private static void Parse() {
		
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		switch (parseType) {
			case PARSER_ITEM_LOOT:
				for (int i = range1; i <= range2; ++i)
					executor.submit(new DisenchantLootParser(i));
				break;
			case PARSER_SKINNING_LOOT:
				for (int i = range1; i <= range2; ++i)
					executor.submit(new SkiningLootParser(i));
				break;
			default:
				break;
		}

		executor.shutdown();
		
		try {
			executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		FileWriter wrt = null;
		try {
			wrt = new FileWriter(new File(filename));
			for (Object l : LootMgr.getInstance().getLoot())
				wrt.write(l.toString() + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				wrt.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
}
