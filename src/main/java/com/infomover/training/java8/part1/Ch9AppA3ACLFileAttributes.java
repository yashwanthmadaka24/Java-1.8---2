package com.infomover.training.java8.part1;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.PosixFileAttributeView;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.UserPrincipal;
import java.util.Set;

public class Ch9AppA3ACLFileAttributes {

	public static void main(String[] args) throws IOException {

		Path path = Paths.get(System.getProperty("user.home") + "/files");

		FileOwnerAttributeView ownerView = Files.getFileAttributeView(path, FileOwnerAttributeView.class);

		UserPrincipal owner = ownerView.getOwner();

		System.out.println(owner.getName());

		boolean isACL = false;
		boolean isDOS = false;

		FileSystem defFS = FileSystems.getDefault();
		for (String fileAttrView : defFS.supportedFileAttributeViews()) {
			System.out.printf("Default file system supports: %s%n", fileAttrView);
			
			if (fileAttrView.equals("acl"))
				isACL = true;
			if (fileAttrView.equals("dos"))
				isDOS = true;
		}
		System.out.println();

		// Path path = Paths.get(args[0]);

		// Output basic attributes, which are always supported.

		System.out.println("Basic attributes:");
		String[] attrNames = { "lastModifiedTime", "lastAccessTime", "creationTime", "size", "isRegularFile",
				"isDirectory", "isSymbolicLink", "isOther", // something other
															// that a regular
															// file, directory,
															// or
															// symbolic link
				"fileKey" // an object that uniquely identifies the given file,
							// or
							// null when a file key is not available.
		};
		for (String attrName : attrNames) {
			System.out.printf("%s: %s%n", attrName, Files.getAttribute(path, "basic:" + attrName));
			System.out.println();
		}

		// Output ACL owner attribute when this view is supported.

		if (isACL) {
			System.out.println("ACL attributes:");
			System.out.printf("Owner: %s%n%n", Files.getAttribute(path, "acl:owner"));
		}

		// Output DOS attributes when this view is supported.

		if (isDOS) {
			System.out.println("DOS attributes:");
			attrNames = new String[] { "readonly", "hidden", "system", "archive" };
			for (String attrName : attrNames)
				System.out.printf("%s: %s%n", attrName, Files.getAttribute(path, "dos:" + attrName));
			System.out.println();
		}
		
		System.out.println( " ***** " );
		
		final PosixFileAttributeView posixView = Files.getFileAttributeView(path, PosixFileAttributeView.class);
		final PosixFileAttributes attrs = posixView.readAttributes();
		final Set<PosixFilePermission> permissions = attrs.permissions();
		 
		// display all the permissions
		System.out.println("\nPermissions before modification:");
		 
		for (PosixFilePermission permission : permissions) {
		    System.out.println(permission);
		}
		 
		// clear all the permissions
		permissions.clear();
		 
		// set own permissions
		permissions.add(PosixFilePermission.OWNER_READ);
		permissions.add(PosixFilePermission.OWNER_WRITE);
		permissions.add(PosixFilePermission.OWNER_EXECUTE);
		
		
//		OWNER_READ
//		GROUP_READ
//		OTHERS_READ
//		OWNER_EXECUTE
//		GROUP_EXECUTE
//		OWNER_WRITE
//		OTHERS_EXECUTE
		
		 
		// modife file attributes
		posixView.setPermissions(permissions);
		 
		System.out.println("\nPermissions after modification:");
		 
		// verify results
		final Set<PosixFilePermission> permessionsNew = posixView.readAttributes().permissions();
		 
		for (PosixFilePermission permission : permessionsNew) {
		    System.out.println(permission);
		}
		
		
		

	}
}
