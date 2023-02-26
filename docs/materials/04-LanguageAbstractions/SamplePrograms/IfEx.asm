* Assembly Language
X:		.word
      
		LOAD R0 STDIN
		STORE R0 X

		LOAD R1 #0
		BGEQ R0 R1 XNNEG   	* X >= 0?
     
		* MM[X] <- -MM[X]
		NOT R0 R0         	* X <- ~X
		ADD R0 R0 #1      	* X <- X + 1
		STORE R0 X

XNNEG:	STORE R0 STDOUT
         
		HALT
