#### COMP256 Assembly Language Reference Card

```
Instruction        Example             Meaning                        Comments
---------------------------------------------------------------------------------------------
ADD R R R          ADD R1 R2 R3        R1 = R2 + R3              
ADD R R #          ADD R1 R2 #231      R1 = R2 + 231

SUB R R R          SUB R1 R2 R3        R1 = R2 - R3
SUB R R #          SUB R1 R2 #0xFF     R1 = R2 - 0xFF

AND R R R          AND R1 R2 R3        R1 = R2 & R3
AND R R #          AND R1 R2 #723      R1 = R2 & 723

OR R R R           OR R1 R2 R3         R1 = R2 | R3
OR R R #           OR R1 R2 #0xAB      R1 = R2 | 0xAB

MOV R R            MOV R1 R2           R1 = R2
NOT R R            NOT R1 R2           R1 = ~R2
SHL R R            SHL R1 R2           R1 = R2 << 1                   LSb = 0
SHR R R            SHR R1 R2           R1 = R2 >>> 1                  MSb = 0

LOAD R #           LOAD R1 #0xC4       R1 = 0xC4                      Immediate
LOAD R #L          LOAD R1 #X          R1 = X                         Immediate Label
LOAD R L           LOAD R1 X           R1 = MM[X]                     Direct
LOAD R R [+]       LOAD R1 R2          R1 = MM[R2]                    Indirect
                   LOAD R1 R2 +4       R1 = MM[R2 + 4]                Indirect

STORE R L          STORE R1 X          MM[X] = R1                     Direct
STORE R R [+]      STORE R1 R2         MM[R2] = R1                    Indirect
                   STORE R1 R2 +8      MM[R2 + 8] = R1

JUMP L             JUMP JLOC           PC = JLOC                 
JUMP R             JUMP R1             PC = R1                   

CALL L             CALL CLOC           RA = PC + 4, PC = CLOC	     
RET                RET                 PC = RA

BNEG R L           BNEG R1 BLOC        IF R1 < 0 THEN PC = BLOC  
BPOS R L           BPOS R1 BLOC        IF R1 > 0 THEN PC = BLOC
BZERO R L          BZERO R1 BLOC       IF R1 == 0 THEN PC = BLOC
BNZERO R L         BNZERO R1 BLOC      IF R1 != 0 THEN PC = BLOC

BODD R L           BODD R1 BLOC        IF R1 % 2 != 0 THEN PC = BLOC         
BEVEN R L          BEVEN R1 BLOC       IF R1 % 2 == 0 THEN PC = BLOC

BEQ R R L          BEQ R1 R2 BLOC      IF R1 == R2 THEN PC = BLOC
BNEQ R R L         BNEQ R1 R2 BLOC     IF R1 != R2 THEN PC = BLOC
BGEQ R R L         BGEQ R1 R2 BLOC     IF R1 >= R2 THEN PC = BLOC
BLEQ R R L         BLEQ R1 R2 BLOC     IF R1 <= R2 THEN PC = BLOC
BGT R R L          BGT R1 R2 BLOC      IF R1 > R2 THEN PC = BLOC
BLT R R L          BLT R1 R2 BLOC      IF R1 < R2 THEN PC = BLOC

PUSH R             PUSH R1             MM[SP] = R1; SP = SP - 4
POP R              POP R1              SP = SP + 4; R1 = MM[SP]

NOP                NOP                 Do Nothing
HALT               HALT                Halt the machine
```
