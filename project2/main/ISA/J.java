package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class J extends Instruction {

	public J() {
		super(
				new ArrayList<Integer>(),
				new ArrayList<Integer>(),
				new ArrayList<StatusReg>(),
				null, InstructionType.branch, ExecUnit.alu, 1
		);
	}
}
