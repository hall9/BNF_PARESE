imple Calculator Language"
; Written by Adam J. Conover, D.Sc.

; All blocks are BEGIN ÎíÎõ END
<BLOCK> ::= BEGIN <STMTLIST> END

; Statement lists can be a single statement or many
<STMTLIST> ::= <STMTLIST> <STMT> | <STMT>

; Statements come in several forms
<STMT> ::= <ASGMT> | <IFSTMT> | <LOOPSTMT> | <EXPRESSION> | <DIRECTIVE>

; Conditional statements use a relational operator, but it does not
; need to participate in the ÎéÎíexpressionÎéÎí hierarchy, since there are no 
; Boolean variables. Interestingly, this allows for expressions like 
;  "a < b < c"  to be syntactically valid.  This is specifically illegal!
<RELEXP> ::= <ID> <RELOP> <RELEXP> | <ID> <RELOP> <ID>

; Directives are a simple keyword followed by an action.
<DIRECTIVE> ::= PRINT (<EXPRESSION>) | VAR <ASGMT>

; Expressions follow the pattern right out of the text
<EXPRESSION> ::= <EXPRESSION> <ADDOP> <MULEXP> | <MULEXP>
<MULEXP> ::= <MULEXP> <MULTOP> <EXPONEXP> | <EXPONEXP>
<EXPONEXP> ::= <ROOTEXP> <EXPOP> <EXPONEXP> | <ROOTEXP> 
<ROOTEXP> ::= (<EXPRESSION>) | <INTEGER> | <ID>

; Assignment statements just bind the result of an expression to an ID
<ASGMT> ::= <ID> <ASGNOP> <EXPRESSION>

; While statements are enclose blocks
<LOOPSTMT> ::= WHILE <RELEXP> <BLOCK>

; If statements come in two forms.  The ambiguity issue described in the text
; is not an issue here because each IF clause must have a matching ENDIF.
; The ÎéÎítrickÎéÎí is two encapsulate the tail of the if statement properly.
<IFSTMT> ::= IF (<RELEXP>) <THENCLAUSE> <ELSECLAUSE>
<THENCLAUSE> ::= THEN <STMTLIST>
<ELSECLAUSE> ::= ELSE <STMTLIST> ENDIF | ENDIF

; Operators in the language
<ASGNOP> ::= ':='
<ADDOP> ::= '+' | '-' 
<MULTOP> ::= '*' | '/' | '%'
<EXPOP> ::= '**'  
<RELOP> ::=  '=' | '<' | '>'

; Identifiers, characters, and numbers.
<ID> ::= <ID> <CHAR> | <CHAR>
<INTEGER> ::= <INTEGER> <DIGIT>  | <DIGIT>
<CHAR> ::= 'a' | 'b' | 'c' | ÎíÎõ | 'x' | 'y' | 'z'    ;; to include all lower case letters
<DIGIIT> ::= '0' | '1' | '2' | ÎíÎõ | '7' | '8' | '9'  ;; to include all digits
