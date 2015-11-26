

grammar Query;

orExpression: andExpression (OR andExpression)* ;
andExpresson: atom (AND atom)* ;

atom : 		'in' lParen string rParen;

category: 	'category' parenExpression;
name: 		'name' parenExpression;
rating: 	'rating' parenExpression;
price: 		'price' parenExpression; 

parenExpression: '(' (RANGE | STRING) ')';

RANGE: 		'[' ('1'..'5') ']..[' '1'..'5' ']';
STRING: 	

lParen: '(';
rParen ')';

OR: '||';
AND: '&&';


<orExpr> ::= <andExpr>(<or><andExpr>)*
<andExpr> ::= <atom>(<and><atom>)*
<atom> ::= <in>|<category>|<rating>|<price>|<name>|<LParen><orExpr><RParen>
<or> ::= "||"
<and> ::= "&&"
<in> ::= "in" <LParen><string><RParen>
<category> ::= "category" <LParen><string><RParen>
<name> ::= "name" <LParen><string><RParen>
<rating> ::= "rating" <LParen><range><RParen>
<price> ::= "price" <LParen><range><RParen>
<range> ::= [1-5]..[1-5]
<LParen> ::= "("
<RParen> ::= ")"

