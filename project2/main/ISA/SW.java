package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class SW extends Instruction {

	public SW(int input) {
		super(
				new ArrayList<Integer>() {{
					add(input);
				}},
				new ArrayList<Integer>() {{
					add(-1);
				}},
				new ArrayList<StatusReg>(),
				null, InstructionType.memory, ExecUnit.lsu, 1
		);
	}
}