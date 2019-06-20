package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class BC1F extends Instruction {

	public BC1F() {
		super(
				new ArrayList<Integer>(),
				new ArrayList<Integer>() {{
					add(-1);
				}},
				new ArrayList<StatusReg>(),
				StatusReg.FP, InstructionType.branch, ExecUnit.alu, 1
		);
	}
}
