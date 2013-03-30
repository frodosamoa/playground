public class Changemaker {

    public static void main (String[]args) {
        int amountOfCents = Integer.parseInt(args[0]);
        int[] arrayOfDenominations = new int [args.length-1];
        
        /**
           This checks to see if the user provided sufficient data, e.g., 
           enough arguments.
        */
       
        if (args.length <= 1) { 
            throw new IllegalArgumentException("Insufficient Data");
        }
        
        /**
           This checks to see if the change to be sorted is a proper amount.
        */
        
        if (amountOfCents < 1) {
            throw new IllegalArgumentException("Improper amount: " + amountOfCents);
        }
        
        /**
           This checks the arguments after the amount of change to be made and
           sees if the arguments are proper. If they are, then they are "added" to
           the array of denominations.
        */
        for (int i = 1; i <args.length; i++) { 
            if (Integer.parseInt(args[i]) <= 0) { 
                throw new IllegalArgumentException("Improper Denomination: " + args[i]);
            } else {
                arrayOfDenominations[i-1] = Integer.parseInt(args[i]);
            }
        }
       
        /**
           This checks to see if there are any suplicate denominations.
        */
        
        for (int i = 0; i < arrayOfDenominations.length; i++) {
            int denominationAtIndexI = arrayOfDenominations[i];
            for (int j = 0; j < arrayOfDenominations.length; j++) {
                if ((j != i) && (denominationAtIndexI == arrayOfDenominations[j])) {
                        throw new IllegalArgumentException("Duplicate Denomination: " + denominationAtIndexI);
                }
            }
        }

        Tuple denominations = new Tuple (arrayOfDenominations.length);
        Tuple[][] table = new Tuple [arrayOfDenominations.length][amountOfCents+1];
        changemaker(amountOfCents, arrayOfDenominations, denominations, table);
        
   }
    
    public static void changemaker (int amountOfCents, int[] arrayOfDenominations, Tuple denominations, Tuple[][] table) {
        int finalCount = 0;
        int tempDenom = 0;
        String denomString = "";
        
        /**
           This is the changemaking part of the program.
           It uses the method discussed in class using a table.
        */
        
        for (int i = 0; i < arrayOfDenominations.length; i++) {
            tempDenom = arrayOfDenominations[i];
            for (int j = 0; j < amountOfCents+1; j ++) {
                if (j == 0) {
                    table[i][j] = new Tuple (denominations.getDenomArray().length);
                } else if (j >= tempDenom) {
                    if (i == 0) {
                        table[i][j] = new Tuple (denominations.getDenomArray().length);
                        table[i][j].incrementByOneAtIndex(i);
                        if (table[i][j-tempDenom].getDenomArray().length > 0) {
                            table[i][j].add(table[i][j-tempDenom]);
                        } else {
                            table[i][j] = new Tuple (0);
                        }
                    } else {
                        table[i][j] = new Tuple (denominations.getDenomArray().length);
                        table[i][j].incrementByOneAtIndex(i);
                        if (table[i][j-tempDenom].getDenomArray().length > 0) {
                            table[i][j].add(table[i][j-tempDenom]);
                        } else {
                            table[i][j] = new Tuple (0);
                        }
                        if ((table[i-1][j].getDenomArray().length != 0) && table[i-1][j].isBetter(table[i][j])) {
                            table[i][j] = table[i-1][j];
                        }
                        if ((table[i-1][j].getDenomArray().length != 0) && (table[i][j].getDenomArray().length == 0)) {
                            table[i][j] = table[i-1][j];
                        }
                    }
                } else {
                    table[i][j] = new Tuple (0);
                    if (i != 0) {
                        if (table[i][j].getDenomArray().length == 0 && (table[i-1][j].getDenomArray().length > 0)) {
                            table[i][j] = table[i-1][j];
                        }
                    }
                }
            }
        }
        
        /**
           This make a string representation of all of the denominations that are
           being used.
        */
        
        for (int i = 0; i <arrayOfDenominations.length; i++) {
            denomString += arrayOfDenominations[i];
            if (i != arrayOfDenominations.length-1) {
                denomString += ", ";
            }
        }
        
        /**
           This tests to see if the bottom right tuple has a solution. If it
           does, e.g., it has a length longer than 0, then is prints out the 
           solution.
        */
        
        if (table[arrayOfDenominations.length-1][amountOfCents].getDenomArray().length == 0) {
            System.out.println("It is impossible to make " + amountOfCents + " cents with coins denominated " + denomString + ".");        
        } else {
            finalCount = table[arrayOfDenominations.length-1][amountOfCents].sum();
            System.out.println("You can make " + amountOfCents + " cents with just " + finalCount + " coin(s) like this:");
            for (int i = 0; i < denominations.getDenomArray().length; i++) {
                System.out.println(table[arrayOfDenominations.length-1][amountOfCents].get(i) + " " + arrayOfDenominations[i] + "-cent coins");
            }
        }
    }
}