public class Tuple {

    private int[] denominationArray;
   
    /**
       Constructs a tuple with a given length denominationsLength, e.g., the
       denominations of coins one is trying to make change with.
    */
    
    public Tuple (int denominationsLength) {
        this.denominationArray = new int[denominationsLength];
        for (int i = 0; i < denominationsLength; i++) {
            this.denominationArray[i] = 0;
        }
    }
    
    /**
       Returns the current amounts of the denominations of a tuple.
    */
    
    public int[] getDenomArray () {
        return this.denominationArray;
    }
    
    /**
       Returns the current amount of a denomination of a tuple at an index i.
    */
    
    public int get (int i) {
        return this.denominationArray[i];
    }
    
    /**
       Set the current amount of a denominations to j of a tuple at an index i.
    */
    
    public void set (int i, int j) {
        this.denominationArray[i] = j; 
    }
    
    /**
       Increments the current amount of a denominations by one of a tuple at an index i.
    */
    
    public void incrementByOneAtIndex (int i) {
        this.denominationArray[i] = this.get(i) + 1;
    }
    
    /**
       Returns a stringy representation of a tuple.
    */
    
    public String toString () {
        String tupleString = "";
        
        for (int i = 0; i <this.getDenomArray().length; i++) {
            tupleString += this.get(i);
            if (i != this.getDenomArray().length-1) {
                tupleString += ",";
            }
        }
        
        return "[" + tupleString + "]";
    }
    
    /**
       Adds two tuples together.
    */
    
    public void add (Tuple t) {
        for (int i = 0; i < this.denominationArray.length; i++) {
            int j = this.get(i) + t.get(i);
            this.set(i,j);
        }
    }
    
    /**
       Returns the sum of all of the current denominations of a tuple.
    */
    
    public int sum () {
        int sumOfTuple = 0;
        for (int i = 0; i <this.getDenomArray().length; i++) {
            sumOfTuple += this.get(i);
        }  
        return sumOfTuple;
    }
    
    /**
       Returns true if a given tuple has less coins, e.g., a better solution.
    */
    
    public boolean isBetter (Tuple t) { 
        return this.sum() < t.sum();
    }

}