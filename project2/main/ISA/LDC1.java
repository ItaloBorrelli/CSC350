package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class LDC1 extends Instruction {

	public LDC1(int output) {
		super(
				new ArrayList<Integer>(),
				new ArrayList<Integer>() {{
					add(output);
				}},
				new ArrayList<StatusReg>() {{
					add(StatusReg.ZERO);
					add(StatusReg.SIGN);
				}},
				null, InstructionType.memory, ExecUnit.lsu, 2
		);
	}
}
