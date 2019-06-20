package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class MULD extends Instruction {

	public MULD(int output, int input1, int input2) {
		super(
				new ArrayList<Integer>() {{
					add(input1);
					add(input2);
					add(input1 + 1);
					add(input2 + 1);
				}},
				new ArrayList<Integer>() {{
					add(output);
					add(output + 1);
				}},
				new ArrayList<StatusReg>() {{
					add(StatusReg.ZERO);
					add(StatusReg.CARRY);
					add(StatusReg.SIGN);
					add(StatusReg.OVERFLOW);
				}},
				null, InstructionType.floating, ExecUnit.fpalu, 30
		);
	}
}
