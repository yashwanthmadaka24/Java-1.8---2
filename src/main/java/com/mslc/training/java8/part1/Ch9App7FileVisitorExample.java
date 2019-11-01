package com.mslc.training.java8.part1;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class Ch9App7FileVisitorExample implements FileVisitor<Path> {

	private final String fileName;
	private final Path startDir;

	public Ch9App7FileVisitorExample(String fileName, Path startingDir) {
		this.fileName = fileName;
		startDir = startingDir;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		String fileName = file.getFileName().toString();
		if (this.fileName.equals(fileName)) {
			System.out.println("File found: " + file.toString());
			return TERMINATE;
		}
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.out.println("Failed to access file: " + file.toString());
		return CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		boolean finishedSearch = Files.isSameFile(dir, startDir);
		if (finishedSearch) {
			System.out.println("File:" + fileName + " not found");
			return TERMINATE;
		}
		return CONTINUE;
	}

	public static void main(String[] args) throws IOException {
		Path startingDir = Paths.get(System.getProperty("user.home") + "/images");

		Ch9App7FileVisitorExample crawler = new Ch9App7FileVisitorExample("classroom-training.svg", startingDir);
		Files.walkFileTree(startingDir, crawler);
	}

}
