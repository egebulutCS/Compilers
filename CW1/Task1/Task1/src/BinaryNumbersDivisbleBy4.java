class BinaryNumbersDivisibleBy4 implements Matrix2D {
    private int [] [] fsaTable
        = new int [] [] { { 0, 1 }, { 2, 1 }, { 0, 1 } };
    public int initialState () { return 0; };
    public int terminalState () { return 0; };
    public int nextState ( int currentState, int character ) {
	return fsaTable [ currentState ] [ character ]; } }
	  