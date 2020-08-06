A Breadth First Search sliding tile puzzle solver written in Java. This program will solve a 9-tile sliding puzzle with any given input state, and will output
each level in the solution tree (if a solution is possible). If a solution is not possible, then the program will not compute a solution at all.

Instructions

1. Enter a 9 character long string when prompted on the CLI.
2. Make sure that the string is just numbers, and ranges from 0-8 (0 represents the blank space in the puzzle)
  2.1 The first three characters are the three values on row one.
  2.2 The next three characters are the three values for row two of the puzzle.
  2.3 The last three values are the three values for row three of the puzzle.

For example: 

Input string = 231340218

Equivalent input string in a 9-tile format (below):

231
340
218
