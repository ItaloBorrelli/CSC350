package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class NOR extends Instruction {

	public NOR(int output, int input1, int input2) {
		super(
				new ArrayList<Integer>() {{
					add(input1);
					add(input2);
				}},
				new ArrayList<Integer>() {{
					add(output);
				}},
				new ArrayList<StatusReg>() {{
					add(StatusReg.ZERO);
					add(StatusReg.SIGN);
				}},
				null, InstructionType.logical, ExecUnit.alu, 1
		);
	}
}
