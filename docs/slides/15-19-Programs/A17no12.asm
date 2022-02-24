P:	.word
Q:	.word
PDAT:	.word 10 20 30
QDAT: 	.word 3 5 7

	* Set the references
	LOAD R0 #PDAT
	STORE R0 P
	LOAD R0 #QDAT
	STORE R0 Q

	LOAD R0 STDIN
	BNZERO R0 CONT	* invert condition.
	
	LOAD R0 Q	* P = Q
	STORE R0 P

CONT:	LOAD R0 P	* Get P[0] (which may be Q[0]
	LOAD R1 R0
	
	STORE R1 STDOUT

	HALT