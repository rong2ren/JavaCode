package Complex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DecodeSecretMessage {

    public static List<String> fetchDocument(String url) throws IOException {
        // Fetch and parse the HTML document
        Document doc = Jsoup.connect(url).get();
        
        // Extract text from <p> tags
        Elements paragraphs = doc.select("p");
        List<String> document = new ArrayList<>();
        
        for (var paragraph : paragraphs) {
            document.add(paragraph.text().trim());
        }

        return document;
    }

    public static List<DataPoint> parseDocument(List<String> document) {
        // Remove headers by finding the first numeric value
        while (!document.isEmpty() && !document.get(0).matches("\\d+")) {
            document.remove(0);
        }

        List<DataPoint> data = new ArrayList<>();

        // Process data in groups of three (x, char, y)
        for (int i = 0; i < document.size(); i += 3) {
            try {
                int x = Integer.parseInt(document.get(i));
                String character = document.get(i + 1);
                int y = Integer.parseInt(document.get(i + 2));
                data.add(new DataPoint(character, x, y));
            } catch (Exception e) {
                System.out.println("Error parsing data: Incorrect format detected.");
                break;
            }
        }
        return data;
    }

    public static void decodeSecretMessage(String url) throws IOException {
        List<String> document = fetchDocument(url);
        List<DataPoint> data = parseDocument(document);

        if (data.isEmpty()) {
            System.out.println("No valid data found in the document.");
            return;
        }

        // Determine grid size
        int maxColumn = data.stream().mapToInt(dp -> dp.x).max().orElse(0);
        int maxRow = data.stream().mapToInt(dp -> dp.y).max().orElse(0);

        // Initialize grid
        char[][] grid = new char[maxRow + 1][maxColumn + 1];
        for (int i = 0; i <= maxRow; i++) {
            for (int j = 0; j <= maxColumn; j++) {
                grid[i][j] = ' ';
            }
        }

        // Fill the grid
        for (DataPoint dp : data) {
            grid[maxRow - dp.y][dp.x] = dp.character.charAt(0);
        }

        // Print the grid
        for (char[] row : grid) {
            System.out.println(new String(row));
        }
    }

    public static void main(String[] args) {
        String url = "https://docs.google.com/document/d/e/2PACX-1vRMx5YQlZNa3ra8dYYxmv-QIQ3YJe8tbI3kqcuC7lQiZm-CSEznKfN_HYNSpoXcZIV3Y_O3YoUB1ecq/pub";
        try {
            decodeSecretMessage(url);
        } catch (IOException e) {
            System.out.println("Error fetching document: " + e.getMessage());
        }
    }

    // Helper class to store (character, x, y)
    static class DataPoint {
        String character;
        int x, y;

        public DataPoint(String character, int x, int y) {
            this.character = character;
            this.x = x;
            this.y = y;
        }
    }
}
