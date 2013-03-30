/*
 *  UTF32-LE to UTF-8 convter written by Andrew Kowalczyk, Drew Hontz, Joseph Crawley 
 *
 *  This program reads text, from standard input, encoded in UTF-32LE and writes
 *  the corresponding UTF-8 to standard output. This program works if txt from a
 *  UTF-32LE encoded text file (UTF32.txt) is used using this terminal command:
 *
 *  gcc utf32toutf8.c && ./a.out < UTF32.txt
 *
 *  Or the output can be put into a new txt file (UTF8.txt) by using this 
 *  terminal command:
 *
 *  gcc utf32toutf8.c && ./a.out < UTF32.txt > UTF8.txt
 * 
 *  Code is online at: http://ideone.com/Hseya
 *
 */

#include <stdlib.h>
#include <stdio.h>

#define SIZE_OF_BUFFER 4  //we want to read 4 bytes at a time

/*
 * This method simply writes a byte with the value c to standard output.
 */

int emitByte (unsigned long c) {
    fputc(c, stdout);
    return 0;
};

/*
 *  This code was taken from Ray Toal's notes on character encoding.
 *  The notes are located at http://www.cs.lmu.edu/~ray/notes/charenc/
 *  This method takes in a character in int form (the same in C) and
 *  streams out its UTF-8 form.
 */

int converter (unsigned long c) {

    if (c <= 0x7F) {
        emitByte(c);
    } else if (c <= 0x7FF) {
        emitByte(0xC0 | c>>6);
        emitByte(0x80 | c & 0x3F);
    } else if (c <= 0xFFFF) {
        emitByte(0xE0 | c>>12);
        emitByte(0x80 | c>>6 & 0x3F);
        emitByte(0x80 | c & 0x3F);
    } else if (c <= 0x1FFFFF) {
        emitByte(0xF0 | c>>18);
        emitByte(0x80 | c>>12 & 0x3F);
        emitByte(0x80 | c>>6 & 0x3F);
        emitByte(0x80 | c & 0x3F);
    } else if (c <= 0x3FFFFFF) {
        emitByte(0xF8 | c>>24);
        emitByte(0x80 | c>>18 & 0x3F);
        emitByte(0x80 | c>>12 & 0x3F);
        emitByte(0x80 | c>>6 & 0x3F);
        emitByte(0x80 | c & 0x3F);
    } else if (c <= 0x7FFFFFFF) {
        emitByte(0xFC | c>>30);
        emitByte(0x80 | c>>24 & 0x3F);
        emitByte(0x80 | c>>18 & 0x3F);
        emitByte(0x80 | c>>12 & 0x3F);
        emitByte(0x80 | c>>6 & 0x3F);
        emitByte(0x80 | c & 0x3F);
    } else {
        //if a character does meet any of the conditions
        //it outprints a UTF-8 REPLACEMENT CHARACTER
        emitByte(0xEFBFBD);
    }
    return 0;
};

int main(int argc, char** argv) {

    unsigned long bytesRead;
    int i;
    int *buffer;
    buffer = (int *)malloc(SIZE_OF_BUFFER);

    while(1) {
        //read a block of bytes from standard in
        bytesRead = fread(buffer, 1, SIZE_OF_BUFFER, stdin);
        if (bytesRead == 0) { break; }
        
        //converts each block to UTF-8
        for (i = 0; i< bytesRead/SIZE_OF_BUFFER; i++) {
            converter(buffer[i]);
        }
    }
  
    free(buffer);
    return 0;
};