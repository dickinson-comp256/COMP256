## COMP256 Assembly Language Reference Card

### Basic Information:
- The assembler "case quirky".
  - OpCodes are not case sensitive but are given in all caps by convention.
  - Registers must be all caps.
  - Labels must be all caps.
  - Assembler directives must be lowercase.

### Registers
```
Register:       Use:
-----------------------------------------
R15             Scratch
R14             RV : Return Value
R13             SP : Stack Pointer
R12             RA : Return Address
```

### Instructions

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

### OpCode Format Fields
```
Value:   Meaning:            Value Types Allowed:
--------------------------------------------------------------
R        Register            Register
#        Constant            positive decimal, negative decimal,
                             2's complement hex,
                             2's complement binary
L        Label               Labels
[+]      Optional Offset     positive decimal,
                             unsigned hex,
                             unsigned binary
```

### Constant Values
```
Example:             Value Type:
----------------------------------------------
R12                  Register
START                Label
123                  decimal number
-123                 negative decimal number
0xF3                 hexadecimal numbers
b0101010111010011    binary numbers
```

### Data Allocation
```
Type:                  Example:                     Comments
--------------------------------------------------------------------------------
.word v1,v2,...        .word 3031, 312, 13, 83      Store 3031, 312, 13 and 83 in successive
                                                    4 byte chunks of memory.
.half v1,v2,...        .half 0x017B                 Store 0x017B in memory
.byte v1,v2,...        .byte b10101101, 17, 0x3C    Store the values in successive bytes
                                                    of memory.
.space n               .space 32		                Allocate 32 bytes of memory initialized
                                                    to zeros.

.stacksize n           .stacksize 128               Allocate 128 bytes of stack space and
                                                    set the stack pointer (R13) to point to
                                                    the top of the stack.
```
#### Allocation Examples:

```
Example:                                 Comments
-----------------------------------------------------------------------------------------------------------
A:      .word 0xFC42                     One word (4-bytes) with initial value FC42 (hex)
B:      .word -27                        One word (4-bytes) with initial value -27.
C:      .word                            One word (4-bytes) with initial value 0.

D:      .half b1010000011001010          Half word (2 bytes) with initial value 1010000011001010 (binary)
E:      .byte -88                        Half word (2 bytes) with initial value -88

XARRAY: .word 32, 33, 57                 Three consecutive words allocated with initial values 32, 33, 57

YARRAY: .space 128                       128-bytes allocated with initial values all 0.

ZARRAY: .word 0 1 2 3 4 5 6 7            21 consecutive bytes allocated with specified initial values.
        .word 8 9 10 11 12 13 14 15
        .word 16 17 18 19 20

        .stacksize 128                   128 byte stack allocated.
```

#### Branch Targets:
```
TARG1: SUB R2 R2 #1
       * ...
       BNZ R2 TARG1
       * ...
       JUMP TARG2
       * ...
TARG2: STORE R1
       * ...
```

#### The `.break` Directive
A `.break` directive can be inserted on any line in the assembly language program. The presence of this directive will cause the machine emulator to pause the execution of the program when the line is reached. This provides a convenient way to run the program to a point of interest and then begin stepping through individual instructions.

For example consider the following fragment of code:
```  
     * ...
     LOAD R0 #100
     STORE R0 X
     .break
     LOAD R0 Y
     *...
```

Assume a program containing this code has been assembled and loaded into the machine emulator. Clicking the "Run" button will execute the program up to the line with the `.break` directive. The execution would pause at that point. Execution can be continued in a step by step manner with the "Step" button or continued until the next `.break` with the "Run" button.

#### The `.include` Directive
A `.include` directive imports the code from another file into the program at the point where it appears.

For example, consider the following fragment of code:
```
.include mult.asm MATH

    * ...
    CALL MATH_MULT
    * ...
```

The `.include` directive imported the code from the file `mult.asm` into the current program. All of the labels in the `mult.asm` file have been prefixed with the string `MATH` to prevent label conflicts. Thus the instruction `CALL MATH_MULT` will use the `CALL` instruction to branch to the line labeled `MULT` in the `mult.asm` file.

Note that included files may use `.break` and additional `.include` directives. However, included files may not use any data allocation directives (e.g. `.word`, `.stacksize`).
