package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class BEQ extends Instruction {

	public BEQ(int input1, int input2) {
		super(
				new ArrayList<Integer>() {{
					add(input1);
					add(input2);
				}},
				new ArrayList<Integer>() {{
					add(-1);
				}},
				new ArrayList<StatusReg>() {{
					add(StatusReg.ZERO);
				}},
				null, InstructionType.branch, ExecUnit.alu, 1
		);
	}
}
