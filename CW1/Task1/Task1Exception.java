// Do not modify the code below except for replacing the "..."!  Don't
// add anything (including "public" declarations), don't remove
// anything (including "public" declarations). Don't wrap it in a
// package, don't make it an innner class of some other class.  If
// your IDE suggsts to change anything below, ignore your IDE. You are
// welcome to add new classes! Please put them into separate files.

class Task1Exception extends Exception {
    public String msg;
    public Task1Exception ( String _msg ) { msg = _msg; } }

interface Matrix2D {
    public int initialState ();
    public int terminalState ();
    public int nextState ( int currentState, int character ); }

interface Language {
    public Boolean decide ( int [] input ) throws Task1Exception; }

class Task1 {
    public static Language create ( Matrix2D matrix2D ) { 
        return new Language(){ 
            public Boolean decide (int [] input){
                int state = matrix2D.initialState();
                int currentChar = 0 ;
                while (currentChar<input.length) {
                    try{
                        state = matrix2D.nextState(state,input[currentChar]);
                        currentChar++;
                    }catch(Exception e){
                        return false;
                    }
                }
                return (state == matrix2D.terminalState());
            }
        };
    }
}