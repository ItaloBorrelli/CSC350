package main.ISA;

import java.util.ArrayList;

import main.Instruction;
import main.StatusReg;


public class NOP extends Instruction {

	public NOP() {
		super(
				new ArrayList<Integer>(),
				new ArrayList<Integer>() {{
					add(-1);
				}},
				new ArrayList<StatusReg>(),
				null, null, null, 1
		);
	}
}