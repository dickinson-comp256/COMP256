X:     .word
Y:     .word
M:     .word
	
       * Allocate the call stack 
       .stacksize 100  * 100 bytes/25 words

MAIN:  LOAD R0 STDIN   * read X
       STORE R0 X	
       LOAD R1 STDIN   * read Y
       STORE R1 Y
		  
       * Put parameters in stack frame for call
       PUSH R0	   * X
       PUSH R1	   * Y
		
       CALL MAX        * Transfer control to MAX 
		
RA:    STORE R14 M     * Get the return value
       STORE R14 STDOUT

       HALT

MAX:  PUSH R12    * + 4
      PUSH R1     * + 8
      PUSH R2     * + 12
      PUSH R3     * + 16
     
      LOAD R2 R13 +20 * R2 is b
      LOAD R1 R13 +24 * R1 is a

      MOV R3 R1       * R3 is x = a
		
      BGEQ R1 R2 SKIP * a >= b
      MOV R3 R2       * x = b
		
SKIP: MOV R14 R3      * RV = x
	
      POP R3
      POP R2
      POP R1
      POP R12 
	
      ADD R13 R13 #8

      RET		
