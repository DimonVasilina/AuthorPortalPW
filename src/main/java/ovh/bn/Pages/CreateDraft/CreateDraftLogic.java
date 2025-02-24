package ovh.bn.Pages.CreateDraft;

import com.github.javafaker.Faker;

public class CreateDraftLogic {

    public String generateChapterText (int countChar) {
        Faker faker = new Faker();

        StringBuilder chapterText = new StringBuilder();
        while (chapterText.length() < countChar ) {
            String paragraph = faker.lorem().characters(24, true, true); // Generate a random paragraph
            if (chapterText.length() + paragraph.length() > countChar) {
                paragraph = paragraph.substring(0, countChar - chapterText.length()); // Trim last paragraph
            }
            chapterText.append(paragraph).append(" "); // Add paragraph with spacing
        }
        chapterText.replace(countChar-1,countChar,".");

        System.out.println("\nTotal Characters: " + chapterText.length()); // Verify length

    return String.valueOf(chapterText);
}}
