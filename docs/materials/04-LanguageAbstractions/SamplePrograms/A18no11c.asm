.stacksize 500

	LOAD R0 #0
	STORE R0 STDOUT
C1: 	CALL ONE
	STORE R0 STDOUT
	HALT

ONE:	PUSH R1
	PUSH R12
	LOAD R1 #1
	STORE R1 STDOUT
C2:	CALL TWO
	STORE R1 STDOUT
	POP R12
	POP R1
	RET

TWO:	PUSH R2
	LOAD R2 #2
	STORE R2 STDOUT
	POP R2
	RET
