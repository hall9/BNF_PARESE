
import io.github.pixee.security.BoundedLineReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Compiler {

    /**
     * It is assumed that the first argument provided is the name of the source
     * file that is to be "compiled".
     */
    public static void main(String[] args) throws IOException {
        //args = new String[]{"<some hard coded path for testing>"};
        //args = new String[]{"D:\\Version_Controlled\\_SVN_\\Towson\\COSC455\\Java\\ParserSample\\input.txt"};

        if (args.length < 1) {
            System.out.println("Need a filename!");
        } else {
            // Java 7 "try-with-resource" to create the file input buffer.
            try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
                // Create the new lexer.
                LexicalAnalyzer lexer = new LexicalAnalyzer();

                // Start lexing and parsing.
                processFile(lexer, br);
            }
        }
    }

    /**
     * Reads each line of the input file and invokes the lexer and parser for
     * each.
     */
    static void processFile(LexicalAnalyzer lexer, BufferedReader br) throws IOException {
        String sourceLine;

        // Read each line in the source file to be compiled as a unique sentence
        // to check against the grammar.
        while ((sourceLine = BoundedLineReader.readLine(br, 5_000_000)) != null) {
            // Create a new syntax analyzer over the provided lexer.
            SyntaxAnalyzer parser = new SyntaxAnalyzer(lexer);

            // Parse the given sentence against the given grammar. We assume that the
            // sentence, <S>, production is the start state.
            try {
                // Start the lexer...
                lexer.start(sourceLine);

                parser.analyze();
                System.out.println("The sentence '" + sourceLine + "' follows the BNF grammar.");
            } catch (ParseException error) {
                // If a syntax error was found, print that the sentence does not follow the grammar.
                System.out.println("ERROR: " + error.getErrMsg());
                System.out.println("The sentence '" + sourceLine + "' does not follow the BNF grammar.");
            }

            System.out.println("-----------------------------------------------------------");
        }
    }
}
