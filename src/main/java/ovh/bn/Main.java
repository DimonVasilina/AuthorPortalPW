package ovh.bn;

import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {

            Faker faker = new Faker();

            StringBuilder chapterText = new StringBuilder();
            while (chapterText.length() < 3500 ) {
                String paragraph = faker.lorem().characters(24, true, true); // Generate a random paragraph
                if (chapterText.length() + paragraph.length() > 3500) {
                    paragraph = paragraph.substring(0, 3500 - chapterText.length()); // Trim last paragraph
                }
                chapterText.append(paragraph).append(" "); // Add paragraph with spacing
            }
            chapterText.replace(3499,3500,".");

            System.out.println("\nTotal Characters: " + chapterText.length()); // Verify length
    }
}