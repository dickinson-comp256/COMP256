* LOAD R0 STDIN
** LOADi R15 1111111111111100
10010011000011111111111111111100
** LOADUi R15 1111111111111111
10011001000011111111111111111111
** LOAD R0 R15 0000000000000000
01000001111100000000000000000000
* LOAD R1 #10
** LOADi R1 0000000000001010
10010011000000010000000000001010
* LOAD R2 #20
** LOADi R2 0000000000010100
10010011000000100000000000010100
* BLT R0 R1 YES
** SUB R15 R0 R1
00101111000000011111000000000000
** BNEG R15 8
11100010000011110000000000001000
* BGT R0 R2 YES
** SUB R15 R2 R0
00101111001000001111000000000000
** BNEG R15 6
11100010000011110000000000000110
* LOAD R3 #0
** LOADi R3 0000000000000000
10010011000000110000000000000000
* STORE R3 STDOUT
** LOADi R15 1111111111111000
10010011000011111111111111111000
** LOADUi R15 1111111111111111
10011001000011111111111111111111
** STORE R3 R15 0000000000000000
01000010111100110000000000000000
* HALT
** HALT
11111111111111111111111111111111
* YES: LOAD R3 #1
** LOADi R3 0000000000000001
10010011000000110000000000000001
* STORE R3 STDOUT
** LOADi R15 1111111111111000
10010011000011111111111111111000
** LOADUi R15 1111111111111111
10011001000011111111111111111111
** STORE R3 R15 0000000000000000
01000010111100110000000000000000
* HALT
** HALT
11111111111111111111111111111111
