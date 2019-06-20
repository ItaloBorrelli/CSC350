package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class SRA extends Instruction {

	public SRA(int output, int input1) {
		super(
				new ArrayList<Integer>() {{
					add(input1);
				}},
				new ArrayList<Integer>() {{
					add(output);
				}},
				new ArrayList<StatusReg>() {{
					add(StatusReg.ZERO);
					add(StatusReg.CARRY);
					add(StatusReg.SIGN);
					add(StatusReg.OVERFLOW);
				}},
				null, InstructionType.logical, ExecUnit.alu, 1
		);
	}
}