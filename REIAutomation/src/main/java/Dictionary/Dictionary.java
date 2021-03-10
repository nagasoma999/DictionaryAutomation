package Dictionary;
import java.util.ArrayList;
import java.util.List;

public class Dictionary  {

    IDictionaryService dictService;

    public void setDictionaryService(IDictionaryService dictService){
        this.dictService = dictService;
    }

    /**
     * Represents the getWordDictionary function
     * @return String list with the dictionary content
     */
    public List<String> getWordDictionary() {
        return dictService.getDictionary();
    }

    /**
     * Represents the isEnglishDictionaryWords function
     * @param word any given string
     * @return boolean if the word was found in the dictionary
     */
    public boolean isEnglishDictionaryWords(String word) {
        return dictService.isEnglishWord(word);
    }

    /**
     * Find all possible words in a given string
     * @param input any given string
     * @return String list with all the possible word combinations based on the given input
     */
    public List<String> findAllPossibleWords(String input) {
        ArrayList <String> matches = new ArrayList <String> ();
        List<String> dictionary = getWordDictionary();
        input = input.toLowerCase();

        // for each word in dictionary
        for (String word : dictionary) {
            Boolean nonMatch = true;
            for (char chWord : word.toCharArray()) {
                String w = Character.toString(chWord);
 
                if (word.length() - word.replace(w, "").length() !=
                        input.length() - input.replace(w, "").length()) {
                    nonMatch = false;
                    break;
                }
            }
            if (nonMatch) {
                matches.add(word);
            }
        }
        System.out.println(matches);
        return matches;
    }
}
