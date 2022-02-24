* Assembly Language

A:	.word 10     * MM[A] <- 10
B:	.word 20     * MM[B] <- 20
C:	.word 0      * MM[C] <- 0
	
* Program code starts here.

	LOAD R1 A     * R1 <- MM[A]
	LOAD R2 B     * R2 <- MM[B]
	
	ADD R3 R1 R2  * R3 <- R1 + R2

	STORE R3 C    * MM[C] <- R3
	
	HALT