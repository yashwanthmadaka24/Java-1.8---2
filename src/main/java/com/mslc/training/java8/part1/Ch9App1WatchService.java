package com.mslc.training.java8.part1;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Ch9App1WatchService {

	public static void main(String[] args) throws Exception {
		
		WatchService watchService = FileSystems.getDefault().newWatchService();

		Path path = Paths.get(System.getProperty("user.home"));

		path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY);

		WatchKey key;

		while ((key = watchService.take()) != null) {

			for (WatchEvent<?> event : key.pollEvents()) {
				if (!event.context().toString().contains(".DS_Store")) {
					System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
				}
			}
			key.reset();
		}
	}

}