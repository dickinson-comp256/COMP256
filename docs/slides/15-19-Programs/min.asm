	.stacksize 100

MAIN:	LOAD R0 STDIN
	LOAD R1 STDIN

	PUSH R0
	PUSH R1
	CALL MIN
	POP R15
	POP R15

	STORE R14 STDOUT
	HALT

MIN:	PUSH R3
	PUSH R4
	LOAD R3 R13 +12
	LOAD R4 R13 +16
	
	BLT R3 R4 PMIN
	MOV R14 R4
	JUMP DONE

PMIN:	MOV R14 R3
DONE:	POP R4
	POP R3
	RET