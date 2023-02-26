* Assembly Language

X:	.word 
Z:	.word	
	
* This program reads X from STDIN
* Computes Z = 2*X-1
* Displays the result using STDOUT

	LOAD R1 STDIN     * R1 <- MM[STDIN]
	STORE R1 X        * MM[X] <- R1

	ADD R2 R1 R1      * R2 <- 2* R1
	SUB R2 R2 #1      * R2 <- R2 - 1
	STORE R2 Z        * MM[Z] <- R2

	STORE R2 STDOUT   * MM[STDOUT] <- R2
	
	HALT