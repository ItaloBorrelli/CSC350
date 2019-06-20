package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class JR extends Instruction {

	public JR(int input1) {
		super(
				new ArrayList<Integer>() {{
					add(input1);
				}},
				new ArrayList<Integer>(),
				new ArrayList<StatusReg>(),
				null, InstructionType.branch, ExecUnit.alu, 1
		);
	}
}
