/*
 *   This function takes in two character arrays or "strings"
 *   and compares each character at a given index and returns
 *   a new string with each "larger" character at that given index.
 *   Here is an example of the output of the function:
 *   
 *       stringcompare("squirtle", "charmander") ===> "squrrtneer"
 *       stringcompare("magikarp", "GYRADOS")    ===> "magikarp"
 *       stringcompare("BlAsToIsE", "vEnAsAur")  ===> "vlnssousE"
 *       stringcompare("123mew", "316mew")       ===> "326mew"
 * 
 *   The code is available online at: http://codepad.org/GzWSBshH
 *
 */

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char* stringcompare (char a[], char b[]) {

    /*
     * Here are the declared varaiables. They include the integers i (used for
     * the for loops), alen and blen (used for the length of a and b), and 
     * shorter and longer (used to monitor which string is longer and which one is 
     * shorter). There is the single dimensional character array c (which will
     * become the new string).
     */

    int i, alen, blen, shorter, longer;
    alen = (int)strlen(a);
    blen = (int)strlen(b);
    longer = (alen > blen) ? alen : blen;
    shorter = (alen > blen) ? blen : alen;
    char* c = malloc(longer);
    
    /*
     * This for loop iterates through the length of the longer string.
     * Within the for loop if checks to see if the current iteration of i
     * is less than the lenght of the shorter string. If it is, it compares
     * the character at index i of both passes character arrays and assigns
     * the larger to the index i in c (the new string). If the current iteration
     * is larger than the length of the shorter string, it then assigns the index
     * i of c to the index i of whichever sting is longer.
     */
    
    for (i = 0; i < longer; i++) {
        if (i < shorter) {
            c[i] = a[i] > b[i] ? a[i] : b[i];
        } else {
            if (alen > blen) { c[i] = a[i]; }
            else if (blen > alen) { c[i] = b[i]; }
        }
    }
    
    return c;
}

int main (int argc, char *argv[]) {

    int i;
    char* a = malloc(strlen(argv[1]));
    char* b = malloc(strlen(argv[2]));

    for (i = 0; i < strlen(argv[1]); i++) { a[i] = argv[1][i]; }
    for (i = 0; i < strlen(argv[2]); i++) { b[i] = argv[2][i]; }

    char* message = stringcompare(a,b);
    printf("%s\n", message);
    free(a);
    free(b);
    free(message);
    return 0;
}

