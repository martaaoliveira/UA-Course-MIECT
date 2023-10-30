grammar BigIntCalc;


program: stat*EOF;

stat: (show|assignment)';';

show: 'show' expr;

assignment: expr '->' ID;

expr:
    op=('+'|'-') expr #ExprUnary
    |expr 'div' expr #ExprDiv
    |expr 'mod' expr #ExprMod
    |expr '*' expr #ExprMul
    |expr op=('+'|'-')expr #ExprAddSub
    |'('expr')' #ExprParen
    |INTEGER #ExprInteger
    |ID     #ExprID
    ;


INTEGER:[0-9]+;
ID: [a-z0-9]+;
COMMENT: '#'.*?'\n' -> skip;
WS: [ \r\n\t]+ ->skip;
ERROR: .->skip;