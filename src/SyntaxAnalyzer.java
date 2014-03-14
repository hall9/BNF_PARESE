/**
 * COSC 455 Programming Languages: Implementation and Design.
 *
 * A Simple Lexical Analyzer Adapted from Sebesta (2010) by Josh Dehlinger
 * further modified by Adam Conover (2012-2013)
 *
 * This syntax analyzer implements a top-down, left-to-right, recursive-descent
 * parser based on the production rules for the simple English language provided
 * by Weber in Section 2.2. Helper methods to get, set and reset the error flag.
 */
public class SyntaxAnalyzer {

    private LexicalAnalyzer lexer; // The lexer which will provide the tokens

    /**
     * The constructor initializes the terminal literals in their vectors.
     */
    public SyntaxAnalyzer(LexicalAnalyzer lexer) {
        this.lexer = lexer;
    }

    /**
     * Begin analyzing...
     */
    public void analyze() throws ParseException {
        parseSentance(0);
    }

    // This method implements the BNF rule for a sentence from Section 2.2.
    // <S> ::= <NP> <V> <NP>
    protected void parseSentance(int treeDepth) throws ParseException {
        log("<S>", treeDepth++);

        NounPhrase(treeDepth);
        Verb(treeDepth);
        NounPhrase(treeDepth);
    }

    // This method implements the BNF rule for a noun phrase from Section 2.2.
    // <NP> ::= <A> <N>
    protected void NounPhrase(int treeDepth) throws ParseException {
        log("<NP>", treeDepth++);

        Article(treeDepth);
        Noun(treeDepth);
    }

    // This method implements the BNF rule for a verb from Section 2.2.
    // <V> ::= loves | hates | eats
    protected void Verb(int treeDepth) throws ParseException {
        log("<V> = " + lexer.lexemeBuffer, treeDepth);

        if (TOKEN.VERB != lexer.curToken) {
            String msg = "A verb was expected when '" + lexer.lexemeBuffer + "' was found.";
            throw new ParseException(msg);
        }

        lexer.parseNextToken();
    }

    // This method implements the BNF rule for a noun from Section 2.2.
    // <N> ::= dog | cat | rat
    protected void Noun(int treeDepth) throws ParseException {
        log("<N> = " + lexer.lexemeBuffer, treeDepth);

        if (TOKEN.NOUN != lexer.curToken) {
            String msg = "A noun was expected when '" + lexer.lexemeBuffer + "' was found.";
            throw new ParseException(msg);
        }

        lexer.parseNextToken();
    }

    // This method implements the BNF rule for an article from Section 2.2.
    // <A> ::= a | the
    protected void Article(int treeDepth) throws ParseException {
        log("<A> = " + lexer.lexemeBuffer, treeDepth);

        if (TOKEN.ARTICLE != lexer.curToken) {
            String msg = "An article was expected when '" + lexer.lexemeBuffer + "' was found.";
            throw new ParseException(msg);
        }

        lexer.parseNextToken();
    }

    private void log(String msg, int treeDepth) {
        for (int i = 0; i < treeDepth; i++) {
            System.out.print("  ");
        }
        System.out.println(msg);
    }
}