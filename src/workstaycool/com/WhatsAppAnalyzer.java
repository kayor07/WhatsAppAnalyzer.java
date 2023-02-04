package workstaycool.com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class WhatsAppAnalyzer {

    private static final String[] BAD_WORDS = {"fuck", "merde", "putain", "ass"};
    private static final String[] EMOJIS = {":)", "😂", "😍", "😭", "😡"};
    private static final String[] THANK_WORDS = {"amen", "akpe", "merci", "nagode", "imela", "thanks", "thank you", "alhamdulillah", "shukran"};

    public static void main(String[] args) throws IOException {
        // Demande le nom de l'utilisateur
        System.out.println("Entrez le nom de l'utilisateur:");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();

        // Initialise les compteurs
        int totalMessages = 0;
        int lolCount = 0;
        int lmaoCount = 0;
        int emojiSentCount = 0;
        int badWordsCount = 0;
        int emojiReceivedCount = 0;
        int angryEmojiCount = 0;
        int thankCount = 0;

        // Lit le fichier d'historique de WhatsApp
        BufferedReader reader = new BufferedReader(new FileReader("Mon historique de groupe whatsapp.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            // Si la ligne contient le nom de l'utilisateur, cela signifie qu'elle contient un message envoyé par cet utilisateur
            if (line.contains(userName)) {
                totalMessages++;
                if (line.contains("lol")) {
                    lolCount++;
                }
                if (line.contains("lmao")) {
                    lmaoCount++;
                }
                for (String emoji : EMOJIS) {
                    if (line.contains(emoji)) {
                        emojiSentCount++;
                    }
                }
                for (String badWord : BAD_WORDS) {
                    if (line.contains(badWord)) {
                        badWordsCount++;
                    }
                }
                for (String thankWord : THANK_WORDS) {
                    if (line.contains(thankWord)) {
                        thankCount++;
                    }
                }
            } else {
                // Si la ligne ne contient pas le nom de l'utilisateur, cela signifie qu'elle contient un message reçu par l'utilisateur
                for (String emoji : EMOJIS) {
                    if (line.contains(emoji)) {
                        emojiReceivedCount++;
                    }
                }
                if (line.contains("😡")) {
                    angryEmojiCount++;
                }
            }
        }
        reader.close();

        // Affiche les résultats
        System.out.println("Nombre total de messages envoyes: " + totalMessages);
        System.out.println("Nombre total de fois que l'utilisateur a envoye 'lol': " + lolCount);
        System.out.println("Nombre total de fois ou l'utilisateur a envoye 'lmao': " + lmaoCount);
        System.out.println("Nombre total de fois ou l'utilisateur a envoye des emojis: " + emojiSentCount);
        System.out.println("Nombre total de grossieretes envoyees par l'utilisateur: " + badWordsCount);
        System.out.println("Nombre total de fois que l'utilisateur a recu des emojis: " + emojiReceivedCount);
        System.out.println("Nombre total de fois ou l'utilisateur a recu l'emoji en colere: " + angryEmojiCount);
        System.out.println("Nombre total de fois ou l'utilisateur a envoye et recu les mots de remerciement: " + thankCount);
    }
}