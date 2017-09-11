package com.infomover.training.java8.part1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class Ch9App0NIOFileAndDirectories {

	private static String HOME = System.getProperty("user.home");

	public static void main(String[] args) throws IOException {

		// CODE SNIPPET 1 : Working with Files & Directories

		System.out.println(" ***** Basic File & Directory Operations ***** ");

		try {
			Path p = Paths.get(HOME + "/non-existent_file.txt");

			System.out.println("File does not exist ? " + Files.notExists(p));

			Path homePath = Paths.get(HOME);
			System.out.println(" is " + homePath + " REGULAR File :" + Files.isRegularFile(homePath));

			System.out.println(" is " + homePath + " READABLE ? :" + Files.isReadable(homePath));

			System.out.println(" is " + homePath + " WRITABLE? :" + Files.isWritable(homePath));

			System.out.println(" is " + homePath + " EXECUTABLE? :" + Files.isExecutable(homePath));

			Path homePath2 = Paths.get(HOME);

			System.out.println(" Are these 2 paths same ? " + Files.isSameFile(homePath, homePath2));

		} finally {

		}

		/** CODE SNIPPET 2 : Creating Files **/

		System.out.println(" ***** Creating Files ***** ");
		try {
			String fileName = "myfile_" + UUID.randomUUID().toString() + ".txt";
			Path newFile = Paths.get(HOME + "/" + fileName);

			System.out.println(Files.exists(newFile));

			Files.createFile(newFile);

			System.out.println(Files.exists(newFile));

		} finally {

		}

		/** CODE SNIPPET 3 : Creating Directories **/
		try {
			String dirName = "myDir_" + UUID.randomUUID().toString();
			Path newDirectory = Paths.get(HOME + "/" + dirName);
			System.out.println(Files.exists(newDirectory));

			Files.createDirectory(newDirectory);

			System.out.println(Files.exists(newDirectory));
			System.out.println(Files.isRegularFile(newDirectory));
			System.out.println(Files.isDirectory(newDirectory));

		} finally {

		}
		/** CODE SNIPPET 4 : Checking if directory exists **/

		System.out.println(" ***** Checking if directory exists ***** ");

		try {
			String newDirName = "myDir_" + UUID.randomUUID().toString() + "/subdir";
			Path newDirPath = Paths.get(HOME + "/" + newDirName);
			System.out.println(Files.exists(newDirPath));

			System.out.println("before create directory - path must exist or else exception");
			Files.createDirectory(newDirPath);
		} catch (Exception ex) {

			System.out.println(" Error : " + ex.getMessage());
		}

		/** CODE SNIPPET 5 : Creating hierarchy of directories **/

		System.out.println(" ***** Creating hierarcy of directories ***** ");

		try {

			Path dir = Paths.get(HOME + "/myDir_" + UUID.randomUUID().toString());
			Path subdir = dir.resolve("subdir");
			System.out.println(Files.exists(dir));
			System.out.println(Files.exists(subdir));

			Files.createDirectories(subdir);

			System.out.println(Files.exists(dir));
			System.out.println(Files.exists(subdir));
		} finally {

		}

		/** CODE SNIPPET 6 : Temp files **/

		System.out.println(" ***** Creating temp files ***** ");

		try {

			String prefix = "log_";
			String suffix = ".txt";
			Path p = Paths.get(HOME + "/");

			Files.createTempFile(p, prefix, suffix);

			System.out.println("Temp file : " + p.toAbsolutePath() + " " + Files.exists(p));

		} finally {

		}

		/** CODE SNIPPET 7: Tempfiles without prefix & suffix. Extension will be tmp **/
		
		System.out.println(" ***** Tempfiles without prefix & suffix. Extension will be tmp ***** ");
		try {

			Path p = Paths.get(HOME + "/");

			Files.createTempFile(p, null, null);

			System.out.println("Temp file without prefix and suffix : " + Files.exists(p));

		} finally {

		}

		// CODE SNIPPET 8 : Temp file in default temp folder
		
		System.out.println(" ***** Temp file in default temp folder ***** ");

		try {
			Path p = Files.createTempFile(null, null);

			System.out.println("Temp file in default folder : " + p.toAbsolutePath() + " - " + Files.exists(p));
		} finally {

		}

		// CODE SNIPPET 9: Deleting a file. IOException if does not exist
		
		System.out.println(" ***** Deleting a file. IOException if does not exist ***** ");

		try {

			Path p = Paths.get(HOME + "/fileToDelete.txt");
			System.out.println("File exists ?  " + Files.exists(p));
			Files.createFile(p);
			System.out.println("Now file exists ? " + Files.exists(p));

			// Works with directory also but not recursive
			// If directory is not empty then will throw IOException
			Files.delete(p);

			// Use Files.deleteIfExists(p) to avoid exception if file does not
			// exist

			System.out.println("Where is the file now ? " + Files.exists(p));

		} finally {

		}

		// CODE SNIPPET 10 - Copying files

		System.out.println(" ***** Copying files ***** ");

		
		try {

			Path dir1 = Paths.get(HOME + "/firstdir_" + UUID.randomUUID().toString());
			Path dir2 = Paths.get(HOME + "/otherdir_" + UUID.randomUUID().toString());

			Files.createDirectory(dir1);
			Files.createDirectory(dir2);

			Path file1 = dir1.resolve("filetocopy.txt");
			Path file2 = dir2.resolve("filetocopy.txt");

			Files.createFile(file1);

			System.out.println("file 1 exists : " + Files.exists(file1));
			System.out.println("file 2 exists : " + Files.exists(file2));

			// The copy fails if the target file exists unless the
			// REPLACE_EXISTING option is specified:

			// Files.copy(file1, file2, StandardCopyOption.REPLACE_EXISTING);

			Files.copy(file1, file2);

			System.out.println("Now file2 exists : " + Files.exists(file2));
		} finally {

		}

		// CODE SNIPPET 11 : Moving files
		System.out.println(" ***** Moving files ***** ");

		try {

			Path dir1 = Paths.get(HOME + "/firstdir_" + UUID.randomUUID().toString());
			Path dir2 = Paths.get(HOME + "/otherdir_" + UUID.randomUUID().toString());

			Files.createDirectory(dir1);
			Files.createDirectory(dir2);

			Path file1 = dir1.resolve("filetocopy.txt");
			Path file2 = dir2.resolve("filetocopy.txt");
			Files.createFile(file1);

			System.out.println("File 1 exists : " + Files.exists(file1));
			System.out.println("File 2 exists : " + Files.exists(file2));

			// The move operation fails if the target file exists unless the
			// REPLACE_EXISTING option is specified

			Files.move(file1, file2);

			System.out.println("After moving. File 1 exists : " + Files.exists(file2));
			System.out.println("After moving. File 2 exists : " + Files.exists(file1));

		} finally {

		}

	}
}
