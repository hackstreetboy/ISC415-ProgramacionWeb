package edu.pucmm;

import spark.Spark;

/**
 * Created by MEUrena on 5/30/16.
 * All rights reserved.
 */
public class Main {
    public static void main(String[] args) {
        Spark.staticFileLocation("/public");
    }
}
