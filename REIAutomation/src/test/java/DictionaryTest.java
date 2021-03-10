
//Mock the dictionary service and perform testing
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import Dictionary.Dictionary;
import Dictionary.IDictionaryService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DictionaryTest {

    private Dictionary dictionary;
    private IDictionaryService dictService;
    List<String> dictionaryList;

    @Before
    public void setUp() {
        dictionary = new Dictionary();
        dictService = mock(IDictionaryService.class);
        dictionary.setDictionaryService(dictService);
        when(dictService.getDictionary()).thenReturn(createDictionaryArray());
        dictionaryList = dictService.getDictionary();
    }

    /**
     * Create String list based on the Dictionary file "EnglishWords" to mock the dictionary service
     * @return String list with the dictionary content
     */
    static List<String> createDictionaryArray() {
        List<String> listDictionary = new ArrayList<String>();
        BufferedReader reader;

        try {
            ClassLoader loader = DictionaryTest.class.getClassLoader();
            File file = new File(loader.getResource("EnglishWords").getFile());
            reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                listDictionary.add(line);
                line = reader.readLine(); // read next line
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listDictionary;
    }

    /**
     * Validate if given word exists in the dictionary "EnglishWords" to mock the isEnglishWord function
     * @param word
     * @return boolean based if the word was found in the dictionary
     */
    public boolean isEnglish(String inputword) {
    	String lowercasewords = inputword.toLowerCase();
        for (String dicwords : dictionaryList) {
        	 if (dicwords.equals(lowercasewords)) {
                System.out.println(inputword + " is a valid english word");
                return true;
            }
        }
        System.out.println(inputword + " is an invalid english word");
        return false;
        
    }

    
    /// Below are test cases - valid & invalid
    
    @Test
    public void validateInvalidWord() {
        when(dictService.isEnglishWord("antifa")).thenReturn(isEnglish("antifa"));
        Assert.assertFalse(dictionary.isEnglishWord("antifa"));
        
    }
    
       
    @Test
    public void validateWorkingWord() {
        when(dictService.isEnglishWord("working")).thenReturn(isEnglish("working"));
        Assert.assertTrue(dictionary.isEnglishWord("working"));
        // Print all possible words
        dictionary.findAllPossibleWords("working");
    }
}
