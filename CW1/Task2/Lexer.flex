//imports, package
%%

%public
%interface Lexer
%type List<Token>

%{
	//	this is a comment
%}

Int 	= [0-9]+
NewLine = \r|\n|\r\n;
Space 	= {NewLine} | [ \t\f]

%%

<YYINITIAL>{

	"+"       { return new T_Plus   ();                             }
    "-"       { return new T_Minus  ();                             }
    "*"       { return new T_Times  ();                             }
    "/"       { return new T_Divide ();                             }
    "("       { return new T_LBr    ();                             }
    ")"       { return new T_RBr    ();                             }
    {Int}     { return new T_Num(Integer.parseInt(yytext()));       }
    {Space}   { /* ignore whitespace */                             }
    [^]       { throw new Error("Illegal text <" + yytext() + ">"); }

}