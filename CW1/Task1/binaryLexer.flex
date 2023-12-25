/**
 * Binary Lexer
 */
%%
 
%class Task1

%unicode

Binary = 0 | 1

%%
Binary+	{ System.out.println("Binary"); }