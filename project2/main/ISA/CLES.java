package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class CLES extends Instruction {

	public CLES(int input1, int input2) {
		super(
				new ArrayList<Integer>() {{
					add(input1);
					add(input2);
				}},
				new ArrayList<Integer>(),
				new ArrayList<StatusReg>() {{
					add(StatusReg.FP);
				}},
				null, InstructionType.logical, ExecUnit.fpalu, 1
		);
	}
}
