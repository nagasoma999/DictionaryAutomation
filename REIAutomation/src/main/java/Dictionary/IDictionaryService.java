package Dictionary;
import java.util.List;

public interface IDictionaryService {
	
    List<String> getDictionary();
    boolean isEnglishWord(String word);
}
