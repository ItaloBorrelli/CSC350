package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class LUI extends Instruction {

	public LUI(int input1) {
		super(
				new ArrayList<Integer>() {{
					add(input1);
				}},
				new ArrayList<Integer>(),
				new ArrayList<StatusReg>() {{
					add(StatusReg.ZERO);
					add(StatusReg.SIGN);
				}},
				null, InstructionType.memory, ExecUnit.lsu, 1
		);
	}
}