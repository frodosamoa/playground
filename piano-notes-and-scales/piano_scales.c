/*
 *   This program takes a command-line argument, a note, and
 *   outputs the major and minor scales for that note. A example
 *   of the output is shown here:
 *
 *   C major:  C  D  E  F  G  A  B  C
 *   C minor:  C  D D#  F  G G# A#  C
 *
 *   A major:  A  B C#  D  E F# G#  A
 *   A minor:  A  B  C  D  E  F  G  A
 * 
 *   The variable TTET declared before the main function represents
 *   that the notes that are being used in this program follow a
 *   twelve tone temperament system.
 *   
 *   The variable scaleLen declared before the main funciton
 *   represents the length of a scale in the twelve tone temperament
 *   system.
 *
 *   The code is online at: http://codepad.org/fiz72gtr
 *
 */


#include <stdio.h>
#include <string.h>
#include <math.h> 
#include <stdlib.h>

#define TTET 12
#define scaleLen 8

int main (int argc, char *argv[]) {

    /*
     * Here are the declared varaiables. They include the integers i (used for
     * the for loops), start (used to find a starting point for the scales),
     * stringScaleLen (3 times the length of a scale[including the first note
     * being the last note] in order to present the scale in a visually 
     * appealing way). It also includes the two dimensional character arrays
     * key (which must be two dimensional in case the nots given is #) and notes
     * (which has all of the notes in a twelve ton equal temperment). There are
     * the single dimensional character arrays majorScale and minorScale.
     * There are also two integer arrays, majorSteps and minorSteps, which shows
     * how far from the original note each subsequent note is in each respective
     * scale.
     */
     
    int i, start, stringScaleLen;
    stringScaleLen = scaleLen * 3;
    char* key[] = {argv[1]};
    char* notes[] = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};
    char* majorScale = malloc(scaleLen*3);
    char* minorScale = malloc(scaleLen*3);
    int majorSteps[] = {0, 2, 4, 5, 7, 9, 11, 12};
    int minorSteps[] = {0, 2, 3, 5, 7, 8, 10, 12};
    
    /* 
     * This takes the commandline note and finds where it is located in the
     * notes character array and gives a starting point for both scales.
     */
    for (i = 0; i < TTET; i++) { if (strcmp(notes[i], key[0]) == 0) { start = i; } };

    /*
     * This for loop actually assigns characters to each spot in the majorScale and
     * minorScale character arrays. It increments itself by 3 through each iteration
     * of the for loop. In each iteration, it assign the i-th character out of three
     * to be a space. Then the loop checks if the next note in the given scale has a
     * # or not. If it does, it assigns the i+1-th character to the note name then
     * the i+2-th character to the #. If the next note does not have a #, then it assigns
     * the i+1-th character to a space and the i+2-th character to the note name.
     */
    for (i = 0; i < stringScaleLen; i += 3) {
        majorScale[i] = ' ';
        minorScale[i] = ' ';
                
        if (notes[(start+majorSteps[(int)((double)i/3)])%TTET][1] == '#') {
            majorScale[i+1] = notes[(start+majorSteps[(int)((double)i/3)])%TTET][0];
            majorScale[i+2] = notes[(start+majorSteps[(int)((double)i/3)])%TTET][1]; 
        } else {
            majorScale[i+1] = ' ';
            majorScale[i+2] = notes[(start+majorSteps[(int)((double)i/3)])%TTET][0]; 
        }
        
        if (notes[(start+minorSteps[(int)((double)i/3)])%TTET][1] == '#') {
            minorScale[i+1] = notes[(start+minorSteps[(int)((double)i/3)])%TTET][0];
            minorScale[i+2] = notes[(start+minorSteps[(int)((double)i/3)])%TTET][1];
        } else {
            minorScale[i+1] = ' ';
            minorScale[i+2] = notes[(start+minorSteps[(int)((double)i/3)])%TTET][0];
        }
    }
    
    /*
     * This printfs the major and minor scales of the note given.
     */
    printf("%s major:%s\n", key[0], majorScale);
    printf("%s minor:%s\n", key[0], minorScale);
    
    return 0;
};