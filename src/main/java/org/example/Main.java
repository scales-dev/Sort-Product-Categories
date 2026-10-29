package org.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class Main {
  private static final String[] CATEGORIES = {
          "Apples",
          "Bananas",
          "Citrus",
          "Mangoes",
          "Melons",
          "Potatoes & Yams",
          "Chilli's & Peppers",
          "Coconuts",
          "Dates",
          "Dragon Fruit",
          "Durian",
          "Jackfruit",
          "Passion Fruit",
          "Soursop",
          "Luxury Fruit",
          "Flavours & Aromatics",
          "Sweet Fruit",
          "Sour Fruit",
          "Juice",
          "Selection Boxes",
          "Alkaline Fruits",
          "Unique Fruits",
          "Miscellaneous",
          "Gifts",
          "Dried Fruits",
          "Favourites",
  };

  private static final String[] FAVOURITES = {
          "Mangosteen",
          "Soursop",
          "Citrus - key limes, seeded",
          "Kumquat",
          "Starfruit",
          "Key lime",
          "Passionfruit - granadilla",
          "Soursop leaf teabag",
          "Exotic fruit selection box - small",
          "Rambutan",
          "Guava - pink flesh",
          "Kumquat/piece",
          "Soursop frozen pulp",
          "Lychee",
          "Longan",
          "Citrus - key limes, seeded",
          "Banana - burro",
          "Yellow pitahaya",
          "Cactus fig",
          "Custard apple - atemoya",
          "Feijoa",
          "Potato - purple sweet",
          "Banana - red dacca",
          "Dragon fruit red flesh",
          "Tamarind sweet",
  };

  private static final Path CSV_PATH = Path.of("/home/jack-scales/Music/products.csv");

  public static void main(String[] args) {
    ArrayList<Map<String, String>> csv = CsvUtil.readCsv(CSV_PATH);

    // funny enough, after nearly 7 years of java, this is my first time reading an input outside clash of code
    Scanner input = new Scanner(System.in);

    for (Map<String, String> row : csv) {
      System.out.println(row);
      // code like yoda
      if ("Add Product".equals(row.get("Action"))) {
        System.out.println(row.get("Name"));
        System.out.println(row.get("Description"));
        System.out.println(row.get("CategoryPath"));
        System.out.println(row.get("CategoryManagement"));
        System.out.println(Arrays.toString(CATEGORIES));

        String categories = input.nextLine();
      }
    }

    CsvUtil.writeCsv(CSV_PATH.getParent().resolve("OUTPUT.csv"), csv);
  }
}