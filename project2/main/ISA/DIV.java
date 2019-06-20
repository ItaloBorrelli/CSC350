package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class DIV extends Instruction {

	public DIV(int input1, int input2) {
		super(
				new ArrayList<Integer>() {{
					add(input1);
					add(input2);
				}},
				new ArrayList<Integer>() {{
					add(32);
					add(33);
				}},
				new ArrayList<StatusReg>() {{
					add(StatusReg.ZERO);
					add(StatusReg.CARRY);
					add(StatusReg.SIGN);
					add(StatusReg.OVERFLOW);
				}},
				null, InstructionType.floating, ExecUnit.fpalu, 30
		);
	}
}