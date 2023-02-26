N:	.word
SUM:	.word 0

	LOAD R0 STDIN  * Read max
	STORE R0 N
	               * ...init...
	LOAD R1 #1     * R1 is i
	LOAD R2 SUM    * R2 is temporary sum
	JUMP COND
	               * ...loop body...
TOP:	ADD R2 R2 R1   * sum = sum + I

	               * ...update... 
	ADD R1 R1 #1   * i++

COND:	BLEQ R1 R0 TOP * i <= N

        STORE R2 SUM
	STORE R2 STDOUT

        HALT
