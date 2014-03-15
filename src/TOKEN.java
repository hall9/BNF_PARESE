import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Token set as an Enum type. This is just "binds" each token to its list of
 * valid lexemes.
 *
 * @author Adam J. Conover
 */
public enum TOKEN {
    BEGIN("BEGIN"),
    END("END"),
    DIGIT("0","1","2","3","4","5","6","7","8","9"),
    CHAR("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"),
    UNKNOWN(); // keep our lexemes "type-safe"!
    //
    // The lexemes under this token
    private List<String> lexemeList;

    // Construct the token with the list of lexems
    private TOKEN(String... tokenStrings) {
        lexemeList = new ArrayList<>(tokenStrings.length);
        lexemeList.addAll(Arrays.asList(tokenStrings));
    }

    // Gets a token from a lexeme
    public static TOKEN fromLexeme(String str) {
        // Search through the lexemes looking for a match.
        for (TOKEN t : TOKEN.values()) {
            if (t.lexemeList.contains(str)) {
                return t;
            }
        }

        // If nothing matches then return UNKNOWN.
        return UNKNOWN;
    }
}