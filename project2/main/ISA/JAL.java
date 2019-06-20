package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class JAL extends Instruction {

	public JAL() {
		super(
				new ArrayList<Integer>(),
				new ArrayList<Integer>() {{
					add(31); // $ra
				}},
				new ArrayList<StatusReg>(),
				null, InstructionType.branch, ExecUnit.alu, 1
		);
	}
}
