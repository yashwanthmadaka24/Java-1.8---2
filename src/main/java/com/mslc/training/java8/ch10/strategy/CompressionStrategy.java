package com.mslc.training.java8.ch10.strategy;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Strategy Interface
 * 
 * @author MuhammedShakir
 *
 */
public interface CompressionStrategy {

    public OutputStream compress(OutputStream data) throws IOException;

}
