#include <assert.h>
#include "utf32toutf8.h"

int main () {

    assert(!emitByte(33));
	assert(!emitByte(34));
	assert(!emitByte(126));
	assert(!emitByte(5932));
	assert(!emitByte(15712189));
	
	assert(!converter(33));
	assert(!converter(34));
	assert(!converter(126));
	assert(!converter(5932));
	assert(!converter(15712));


    printf("\nWe passed all of the tests!!!\n");
    return 0;
}