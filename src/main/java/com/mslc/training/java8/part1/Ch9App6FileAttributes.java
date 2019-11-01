package com.mslc.training.java8.part1;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;
import java.util.Date;

public class Ch9App6FileAttributes {

	public static void main(String[] args) throws IOException {

		String HOME = System.getProperty("user.home");
		Path home = Paths.get(HOME);
		BasicFileAttributeView basicView = Files.getFileAttributeView(home, BasicFileAttributeView.class);
		BasicFileAttributes basicAttribs = basicView.readAttributes();

		long size = basicAttribs.size();
		System.out.println("size : " + size);

		boolean isDir = basicAttribs.isDirectory();
		System.out.println("isDir : " + isDir);

		boolean isFile = basicAttribs.isRegularFile();
		System.out.println("isFile : " + isFile);

		boolean isSymLink = basicAttribs.isSymbolicLink();
		System.out.println("isSymLink : " + isSymLink);

		FileTime created = basicAttribs.creationTime();

		FileTime modified = basicAttribs.lastModifiedTime();

		FileTime accessed = basicAttribs.lastAccessTime();

		Date d1 = new Date(created.toMillis());
		System.out.println("Created on : " + d1);

		Date d2 = new Date(modified.toMillis());
		System.out.println("Created on : " + d2);

		FileOwnerAttributeView ownerView = Files.getFileAttributeView(
				  home, FileOwnerAttributeView.class);
		UserPrincipal owner = ownerView.getOwner();
		System.out.println(owner.toString());
		
		
		
		
		Path file = null;
		try {
			file = Paths.get(HOME);
		} catch (Exception ex) {

		}
		FileStore store = Files.getFileStore(file);
		System.out.println("total space : " + store.getTotalSpace() / (1024 * 1024 * 1024));

		System.out.println(
				"used space : " + ((store.getTotalSpace() - store.getUnallocatedSpace()) / (1024 * 1024 * 1024)));

	}

}
