package com.rs.problems;

import java.util.Random;


public class RandomReverse {

	public static void main(String[] args) {
		Random rng = new Random(1337L);

		int i1 = rng.nextInt();
		int i2 = rng.nextInt();

		long internalSeed = getSeed(i1, i2);
		long firstInternalSeed = previousSeed(previousSeed(internalSeed));

		/*
		 * If the original seed was larger than 2^48
		 * the seed produced will be different but
		 * produce the same pseudo-random sequence.
		 * */
		long originalSeed = (firstInternalSeed ^ 0x5DEECE66DL);

		System.out.println(originalSeed);
	}

	public static long parseSeed(long seed) {
		return ((seed ^ 0x5DEECE66DL) & ((1L << 48) - 1));
	}

	public static long previousSeed(long seed) {
		seed -= 0xBL;
		long result = 0;
		for (int i = 0; i < 48; i++) {
			long mask = 1L << i;
			long bit = seed & mask;
			result |= bit;
			if (bit == mask) {
				seed -= 0x5DEECE66DL << i;
			}
		}
		return result;
	}

	public static long getSeed(int x1, int x2) {
		long i1 = x1 & 0xFFFFFFFFL;
		long i2 = x2 & 0xFFFFFFFFL;
		long seed = i1 << 16;
		for (int i = 0; i < 65536; i++) {
			long tseed = seed + i;
			tseed = (tseed * 0x5DEECE66DL + 0xBL) & ((1L << 48) - 1);
			if ((tseed >>> 16) == i2) {
				return tseed;
			}
		}
		throw new AssertionError(); //This should never happen.
	}
}