package edu.pucmm;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.validator.routines.UrlValidator;

/**
 * Created by MEUrena on 5/17/16.
 * All rights reserved.
 */
public class Scraper {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] schemes = {"http","https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        String url;
        do {
            System.out.print("Introduzca la URL que desea parsear: ");
            url = scanner.next();
            if (urlValidator.isValid(url)) {
                System.out.println("La URL es válida. Parseando website...\n");
            } else {
                System.out.println("La URL es inválida. Por favor, introduzca una URL válida (i.e.: https://google.com)\n");
            }

        } while (!urlValidator.isValid(url));
        scanner.close();

        Document doc;
        try {
            doc = Jsoup.connect(url).get();
            Element body = doc.body();

            String[] lines = doc.html().split("\r\n|\r|\n");

            System.out.println("Cantidad de líneas: " + lines.length + "\n");

            Elements pTags = body.getElementsByTag("p");
            Elements imgTags = body.getElementsByTag("img");
            Elements formTags = body.getElementsByTag("form");

            ArrayList<Elements> inputs = new ArrayList<Elements>();

            for (Element form : formTags) {
                inputs.add(form.getElementsByTag("input"));
            }

            System.out.println("Etiquetas <p> encontradas: " + pTags.size());
            System.out.println("Etiquetas <img> encontradas: " + imgTags.size());
            System.out.println("Etiquetas <form> encontradas: " + formTags.size());

            System.out.println("\nMostrando etiquetas <input> de cada formulario:\n");
            int count = 0;
            for (Elements items : inputs) {
                System.out.println("Formulario " + count + ": ");
                for (Element input : items) {
                    String type = input.attr("type");
                    System.out.println("Tipo: " + type + " - Campo: " + input);
                }
                count++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
