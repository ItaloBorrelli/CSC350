package main.ISA;

import java.util.ArrayList;

import main.Instruction;
import main.StatusReg;

public class MFLO extends Instruction {

	public MFLO(int output) {
		super(
				new ArrayList<Integer>() {{
					add(33);
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
