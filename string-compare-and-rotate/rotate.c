/*
 * This function takes in a character array s and an int
 * k and it returns rotates the character array rotated to 
 * the left by the amount of s. If s is negative, it returns
 * the character array rotated to the right. Here are some
 * examples of the output that this program would give:
 *
 *  rotate("squirtle", -8)  ===>  "squirtle"
 *  rotate("squirtle", -6)  ===>  "uirtlesq"
 *  rotate("squirtle", -3)  ===>  "tlesquir"
 *  rotate("squirtle", 0)   ===>  "squirtle"
 *  rotate("squirtle", 3)   ===>  "irtlesqu"
 *  rotate("squirtle", 6)   ===>  "lesquirt"
 *  rotate("squirtle", 8)   ===>  "squirtle"
 *
 *
 *  The code is availabe online at: http://codepad.org/tkp2JTcJ
 */

#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>

char* rotate (char s[], int k) {
    
    /*
     * Here are the declared varaiables. They include the integers i (used for
     * the for loops) and len (used to keep track of the length of the character
     * array s). There is the single dimensional character arrays rotatedString
     * which will be the new rotated string.
     */
    
    int i,len;
    len = strlen(s);
    char* rotatedString = malloc(len);
    
    /*
     * This for loop iterates through the length of the passed string.
     * It check to see if the integer that is passed is negative or positive.
     * If it is positive (or equal to 0), it rotates the characters to the left by
     * that amount. If it is negative, it rotates the characters to the right
     * by that amount.
     */
    
    for (i = 0; i < len; i++) {
        if (k >= 0) {
            rotatedString[i] = s[(i+k)%len];
        } else {
            rotatedString[abs((i-k)%len)] = s[i];
        }
    }
    return rotatedString;
}

int main (int argc, char *argv[]) {

    int i, k, len;
    k = atoi(argv[2]);
    len = (int)strlen(argv[1]);
    char* string = malloc(len);
    for (i = 0; i < len; i++) { string[i] = argv[1][i]; }
    
    char* message = rotate(string, k);
    printf("%s\n", message);
    free(string);
    free(message);
    return 0;
}