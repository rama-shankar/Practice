package com.rs.problems;

public class Mutate_DNA_pairs_rec {
    /**
     * Mutates the input DNA sequence and inserts G
     * in between adjacent identical letters.
     *
     * @param sequence Input sequence string.
     * @return Output mutated sequence string.
     */
    public String mutateDnaPairs(String sequence) {
        if (sequence == null || sequence.length() == 1) return sequence;
        if (sequence.charAt(0) == sequence.charAt(1))
            return sequence.charAt(0) + "G" + mutateDnaPairs(sequence.substring(1));
        return sequence.charAt(0) + mutateDnaPairs(sequence.substring(1));
    }

    public static void main(String[] args) {
        Mutate_DNA_pairs_rec o = new Mutate_DNA_pairs_rec();
        System.out.println(o.mutateDnaPairs("TATA"));
    }
}