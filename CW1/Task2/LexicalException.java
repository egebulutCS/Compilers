// Do not modify the code below except for replacing the "..."!  Don't
// add anything (including "public" declarations), don't remove
// anything (including "public" declarations). Don't wrap it in a
// package, don't make it an innner class of some other class.  If
// your IDE suggsts to change anything below, ignore your IDE. You are
// welcome to add new classes! Please put them into separate files.

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class LexicalException extends Exception {
    public String msg;
    public LexicalException ( String _msg ) { msg = _msg; } } 

class Task2Exception extends Exception {
    public String msg;
    public Task2Exception ( String _msg ) { msg = _msg; } }

interface Lexer {
    public List<Token> lex ( String input ) throws LexicalException, Task2Exception; }

class Task2 {
    public static Lexer create () { 
        return new Lexer(){
            public List<Token> lex(String input){
                List<Token> tokenList = new ArrayList<Token>();
                List<Integer> indexes = new ArrayList<Integer>();
                String whitespace = "\\s+";
                String keywords = "\\d+|\\p{Punct}+|[a-z](\\w*|_*)|def|if|then|else|skip|while|do|repeat|until|break|continue";
                String[] nots = new String[11];
                nots[0] = "def";
                nots[1] = "if";
                nots[2] = "then";
                nots[3] = "else";
                nots[4] = "skip";
                nots[5] = "while";
                nots[6] = "do";
                nots[7] = "repeat";
                nots[8] = "until";
                nots[9] = "break";
                nots[10] = "continue";

                try{
                    Pattern pattern = Pattern.compile(keywords);
                    Matcher matcher = pattern.matcher(input);   
                    while (matcher.find())
                    {
                        //System.out.print("Start index: " + matcher.start());
                        //System.out.print(" End index: " + matcher.end() + " ");
                        String m = matcher.group();
                        System.out.println(m);
                        Pattern patternWhite = Pattern.compile(whitespace);
                        Matcher matcherWhite = patternWhite.matcher(input);
                        //Pattern pint = Pattern.compile(integer);
                        //Matcher mint = pint.matcher(matcher.split());
                        //System.out.println(mint.group());
                        if(m.equals("def")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_Def());
                                indexes.add(matcher.start());
                            } else {                               
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("if")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_If());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("then")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_Then());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("else")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_Else());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("skip")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_Skip());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("while")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_While());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("do")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_Do());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("repeat")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_Repeat());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("until")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_Until());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("break")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_Break());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals("continue")){
                            while(matcherWhite.find()){
                                if((matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                    break;
                                }
                            }
                            if(((matcher.start() == 0) && (matcher.end() == input.length())) || (matcherWhite.end() == matcher.start()) || (matcherWhite.start() == matcher.end())){
                                tokenList.add(new T_Continue());
                                indexes.add(matcher.start());
                            } else {
                                tokenList.add(new T_Identifier(matcher.group()));
                                indexes.add(matcher.start());
                            }
                        }
                        else if(m.equals(";")){
                            tokenList.add(new T_Semicolon());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("(")){
                            tokenList.add(new T_LeftBracket());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals(")")){
                            tokenList.add(new T_RightBracket());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("=")){
                            tokenList.add(new T_EqualDefines());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("==")){
                            tokenList.add(new T_Equal());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("<")){
                            tokenList.add(new T_LessThan());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals(">")){
                            tokenList.add(new T_GreaterThan());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("<=")){
                            tokenList.add(new T_LessEq());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals(">=")){
                            tokenList.add(new T_GreaterEq());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals(",")){
                            tokenList.add(new T_Comma());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("{")){
                            tokenList.add(new T_LeftCurlyBracket());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("}")){
                            tokenList.add(new T_RightCurlyBracket());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals(":=")){
                            tokenList.add(new T_Assign());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("+")){
                            tokenList.add(new T_Plus());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("*")){
                            tokenList.add(new T_Times());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("-")){
                            tokenList.add(new T_Minus());
                            indexes.add(matcher.start());
                        }
                        else if(m.equals("/")){
                            tokenList.add(new T_Div());
                            indexes.add(matcher.start());
                        } else if(Character.isDigit(m.charAt(0))){
                            tokenList.add(new T_Integer(Integer.parseInt(matcher.group())));
                            indexes.add(matcher.start());
                        } else {
                            tokenList.add(new T_Identifier(matcher.group()));
                            indexes.add(matcher.start());
                        }
                    }

                    System.out.print("array sizes: ");
                    System.out.print(indexes.size() + " ");
                    System.out.println(tokenList.size() + " ");
                    for(int i = 0; i<indexes.size()-1;i++){
                        for(int j = 0; j<indexes.size()-i-1;j++){
                            if(indexes.get(j)>indexes.get(j+1)){
                                Collections.swap(indexes,j,j+1);
                                Collections.swap(tokenList,j,j+1);
                            }
                        }
                    }

                    for(int i = 0; i<indexes.size();i++){
                        System.out.println(indexes.get(i));
                    }

                    for(int i = 0; i<tokenList.size();i++){
                        System.out.println(tokenList.get(i));
                    }

                }catch(Exception e){

                }

                return tokenList;
            }
        }    
        ;
    } 
}
