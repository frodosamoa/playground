/*
 *   This program outputs each note and their frequency of 
 *   a "piano" with the standard 88 keys. This program uses
 *   this formula to output each respective key frequency:
 * 
 *     f(x) = 440 x 2^((x-49)/12)
 * 
 *   440 is the frequency of the 49th key on the piano which
 *   is the fifth A, called A4, or A440. The x in this 
 *   formaula represents the key at which the frequency is 
 *   being calculated. Since the notes on pianos have 
 *   twelve-tone equal temperament, the formula works. The output
 *   should look as follows:
 *   
 *    A        27.5000 
 *    A#       29.1352 
 *    B        30.8677
 *    ...      ....
 *
 *   The variable TTET declared before the main function represents
 *   that the notes that are being used in this program follow a
 *   twelve tone temperament system.
 *   
 *   The variable scaleLen declared before the main funciton
 *   represents the length of a scale in the twelve tone temperament
 *   system.
 *
 *   The variable A440 declared before the main function represents
 *   the frequency that the 49th key on the piano resonates at (which
 *   is the fifth A, called A4 or A440).
 *
 *   The code is online at: http://codepad.org/OjUtOzsU
 *
 */

#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#define totalKeys 88
#define A440 440
#define TTET 12


int main () {
    char* notes[] = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"}; 
    int i = 0;
    for (i = 0; i < totalKeys; i++) {
        printf("%s \t%10.4f \n", notes[i%TTET], A440 * pow (2, (((double) i + 1) - 49) / TTET));
    } 
    return 0;
};