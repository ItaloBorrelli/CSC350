package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class MFC0 extends Instruction {

	public MFC0(int output, int input) {
		super(
				new ArrayList<Integer>() {{
					add(input);
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
				null, null, null, 1
		);
	}
}
