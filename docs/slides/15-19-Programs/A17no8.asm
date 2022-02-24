A:	.word
ADAT:	.word 1 2 3 4 5

	LOAD R0 #ADAT
	STORE R0 A

	LOAD R2 R0	* get A[0]
	
	LOAD R1 R0 +4	* get A[1]
	ADD R2 R2 R1	* add A[1]

	* ...

	STORE R2 STDOUT
	HALT