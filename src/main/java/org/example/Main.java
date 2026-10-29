package org.example;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

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

    String categoryPath = "";
    String categoryManagement = "";

    for (Map<String, String> row : csv) {
      // code like yoda
      if ("Add Product".equals(row.get("Action"))) {
        System.out.println(row.get("Name"));
        System.out.println(row.get("Description"));
        System.out.println(row.get("CategoryPath"));
        System.out.println(row.get("CategoryManagement"));
        System.out.println(Arrays.toString(CATEGORIES));
        if (Arrays.stream(FAVOURITES).toList().contains(row.get("Name"))) {
          System.out.println("FAVOURITE");
          System.out.println("FAVOURITE");
          System.out.println("FAVOURITE");
        }

        String[] categories = findCategories(input);

        categoryPath = categories[0];
        categoryManagement = Arrays.stream(categories).skip(1).collect(Collectors.joining(":"));
      }

      System.out.println(categoryPath+":"+categoryManagement);
      row.put("CategoryPath", categoryPath);
      row.put("CategoryManagement", categoryManagement);
    }

    CsvUtil.writeCsv(CSV_PATH.getParent().resolve("OUTPUT.csv"), csv);
  }

  private static String[] findCategories(Scanner input) {
    String[] categories = input.nextLine().split(",");
    String[] matchedCategories = new String[categories.length + 1];
    int i = 0;

    for (String inputCategory : categories) {
      List<String> matchingCategories = Arrays.stream(CATEGORIES)
              .filter(category -> category.toLowerCase().startsWith(inputCategory.trim().toLowerCase()))
              .toList();

      if (matchingCategories.isEmpty()) {
        System.out.println(inputCategory + " has no matches! sorry");
        // can stack overflow, niche error
        return findCategories(input);
      }
      if (matchingCategories.size() > 1) {
        System.out.println(inputCategory + " matched to: " + matchingCategories + " try again...");
        return findCategories(input);
      }

      matchedCategories[i++] = getCategoryPath(matchingCategories.getFirst());
    }

    matchedCategories[i] = getCategoryPath("");

    return matchedCategories;
  }

  private static String getCategoryPath(String path) {
    return "Home > Shop Online" + (path.isBlank() ? "" : " > " + path);
  }
}