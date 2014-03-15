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
        parseBlock(0);
    }

    // This method implements the BNF rule for a BLOCK
    // <BLOCK> ::= BEGIN <STMTLIST> END
    protected void parseBlock(int treeDepth) throws ParseException {
        log("<BlOCK>", treeDepth++);

        BEGIN(treeDepth);
        STMTLIST(treeDepth);
        END(treeDepth);
        
    }

    // This method implements the BNF rule for a STMTLIST
    // <STMTLIST> ::= <STMTLIST> <STMT> | <STMT>
    protected void STMTLIST(int treeDepth) throws ParseException {
        log("<STMTLIST>", treeDepth++);

        //STMTLIST(treeDepth);
    }

    protected void BEGIN(int treeDepth) throws ParseException {
        log("BEGIN", treeDepth);

        if (TOKEN.BEGIN != lexer.curToken) {
            String msg = "An BEGIN was expected when '" + lexer.lexemeBuffer + "' was found.";
            throw new ParseException(msg);
        }

        lexer.parseNextToken();
    }
    
    protected void END(int treeDepth) throws ParseException {
        log("END", treeDepth);

        if (TOKEN.END != lexer.curToken) {
            String msg = "An END was expected when '" + lexer.lexemeBuffer + "' was found.";
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