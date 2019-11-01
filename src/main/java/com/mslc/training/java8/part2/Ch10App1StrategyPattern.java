package com.mslc.training.java8.part2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

import com.mslc.training.java8.ch10.strategy.Compressor;
import com.mslc.training.java8.ch10.strategy.GzipCompressionStrategy;
import com.mslc.training.java8.ch10.strategy.ZipCompressionStrategy;

public class Ch10App1StrategyPattern {

	public static void main(String[] args) {

	}

	public static void classBasedExample(Path inFile, File outFile) throws IOException {
		// BEGIN classBasedExample
		Compressor gzipCompressor = new Compressor(new GzipCompressionStrategy());
		gzipCompressor.compress(inFile, outFile);

		Compressor zipCompressor = new Compressor(new ZipCompressionStrategy());
		zipCompressor.compress(inFile, outFile);
		// END classBasedExample
	}

	public static void lambdaBasedExample(Path inFile, File outFile) throws IOException {
		// BEGIN lambdaBasedExample
		Compressor gzipCompressor = new Compressor(GZIPOutputStream::new);
		gzipCompressor.compress(inFile, outFile);

		Compressor zipCompressor = new Compressor(ZipOutputStream::new);
		zipCompressor.compress(inFile, outFile);
		// END lambdaBasedExample
	}

}
