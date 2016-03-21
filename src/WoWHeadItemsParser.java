import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import LootTemplates.ItemLoot;
import Managers.ItemLootMgr;
import Parsers.DisenchantLootParser;

public class WoWHeadItemsParser {
	
	private static String filename;
	private static Integer threadCount, range1, range2;
	
	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.out.println("Error arguments");
			return;
		}

		try
		{
			filename = args[0]; // название файла
			String thread = args[1];
			threadCount = Integer.valueOf(thread); // кол-во потоков
			String range = args[2];
			range1 = Integer.valueOf(range); // от
			range = args[3];
			range2 = Integer.valueOf(range); // до
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Error arguments.");
			return;
		} catch (NumberFormatException e) {
			System.out.println("Error arguments.");
			return;
		}
		
		if (range1 == range2) {
			System.out.println("Error: range1 == range2");
			return;
		}
		
		if (range1 > range2) {
			Integer temp = range2;
			range2 = range1;
			range1 = temp;
		}
		
		if (threadCount <= 0) {
			System.out.println("Error: thread number <= 0");
			return;
		}
		
		FileWriter wrt = new FileWriter(new File(filename));
/*
		GenerateIds(list); // генерация значений
		
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);
		List<Future<String>> tasks = new ArrayList<Future<String>>();
		int size = list.size();	
		for (int i = 0; i < size; i++) {
			String entry = GetRandomEntry(list);
			Future<String> future = executor.submit(new WorkerThread("Thread - " + i, entry));
			tasks.add(future);
			if (list.isEmpty()) {
				executor.shutdown();
				break;
			}
		}
		
		wrt.close();

		if (!executor.isShutdown())
			executor.shutdown();*/
		
		ExecutorService executor = Executors.newFixedThreadPool(threadCount);	
		for (int i = range1; i <= range2; ++i) {
			executor.submit(new DisenchantLootParser(i));
		}

		executor.shutdown();
		
		try {
			executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ItemLootMgr.getInstance().getLoot().size());
		for (ItemLoot l : ItemLootMgr.getInstance().getLoot()) {
			wrt.write(l.toString());
		}
		
		wrt.close();
	}
}