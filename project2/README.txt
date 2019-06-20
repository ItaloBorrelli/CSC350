From this directory, run "javac ./main/Sim.java" to compile. Run "java main.Sim
<file>" where <file> is the relative address of the instruction file.

Instructions can be produced with most MIPS code. All instructions and most
valid pseudoinstructions are possible to compile. Syscall, data sections and
labels are not included will cause a runtime error.

Branches and jumps are not fully functional and do not truly branch. If going
to a function or looping repeat the code by copy and pasting it.

The values for any immediate instruction must exist, but will be ignored by the
simulation.

