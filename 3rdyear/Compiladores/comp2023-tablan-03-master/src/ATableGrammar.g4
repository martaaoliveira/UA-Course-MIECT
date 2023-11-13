grammar ATableGrammar;

program: statement*EOF;

statement: 
      tableDefinition #StatementTableDefinition
    | assignment #StatementAssignment
    | printStatement #StatementPrint
    | addRowStatement #StatementAddRow
    | headerStatement #StatementHeader
    | loopStatement #StatementLoop
    ;

tableDefinition: 'type' 'table' ID ( '->' STRING_LITERAL )? '{' tableColumnDefinition+ (formulaDefinition+)? '}' ';';

formulaDefinition: ID ':' dataType ':=' expression ';';

tableColumnDefinition: ID ':' dataType ( '->' STRING_LITERAL )? ';';

dataType: 
        'integer' #DataTypeInteger
        | 'real' #DataTypeReal
        | 'text' #DataTypeText
    ;

assignment: 
      fieldAccess ':=' expression ';' #FieldAccessAssignment
    | ID (':=' expression | ':=' readStatement | ':' dataType (':=' expression)? | ':' tableRef | ':=' tableRef) ';' #IdentifierAssignment
    ;

printStatement: ('println' | 'print') (expressionList | STRING_LITERAL | centerExpression) ';';

expressionList: expression (',' expression)*;

addRowStatement: (ID | '[' expressionList ']' | '[]') '>>' tableRef ';';

tableRef: ('new' '(' tableRef ')' | ID) ('.' ID)*;

headerStatement: tableRef ( '.' ID )? '->' (STRING_LITERAL)? ';';

loopStatement: 'for' ID 'in' tableRef 'do' statement* 'end;' ;

centerExpression: expression 'center' expression ;

expression: primary ((op=('*' | '/' | '+' | '-' | '+') primary)*);

primary:
     'new' #NewPrimary
    |fieldAccess #fieldAccessPrimary
    | ID #IdentifierPrimary
    | INTEGER_LITERAL #IntegerLiteralPrimary
    | REAL_LITERAL #RealLiteralPrimary
    | STRING_LITERAL #StringLiteralPrimary
    | '(' expression ')' #ParenthesisPrimary
    | dataType '(' expression (',' STRING_LITERAL)? ')' #DataTypePrimary
    | '[' expressionList? ']' #ListPrimary
    | dataType '(' ('read' 'in' (STRING_LITERAL)?) ')' #ReadPrimary
    | 'length' (tableRef '.' ID | expression) #LengthPrimary
    | tableRef #TableRefPrimary 
    ;

fieldAccess: ID '.' ID;

readStatement: 'read' STRING_LITERAL (usingClause)?;

usingClause: 'using' columnMapping (',' columnMapping)*;

columnMapping: (('column')? (ID | STRING_LITERAL | INTEGER_LITERAL)) 'as' ID;

ID: [a-zA-Z_][a-zA-Z_0-9]* ;
INTEGER_LITERAL: [0-9]+;
REAL_LITERAL: [0-9]*'.'[0-9]+;
STRING_LITERAL: '"' (~'"' | '""')* '"';
WHITESPACE: [ \t\r\n]+ -> skip;
COMMENT: '--' ~[\r\n]* -> skip;
