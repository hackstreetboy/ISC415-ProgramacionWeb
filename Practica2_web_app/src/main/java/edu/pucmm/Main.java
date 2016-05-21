package edu.pucmm;

import edu.pucmm.handlers.TemplateHandler;
import spark.Spark;

/**
 * Created by MEUrena on 5/21/16.
 * All rights reserved.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Spark.staticFileLocation("/public");

        new TemplateHandler().startApp();

    }

}
