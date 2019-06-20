package main.ISA;

import java.util.ArrayList;

import main.ExecUnit;
import main.Instruction;
import main.InstructionType;
import main.StatusReg;

public class MULTU extends Instruction {

	public MULTU(int input1, int input2) {
		super(
				new ArrayList<Integer>() {{
					add(input1);
					add(input2);
				}},
				new ArrayList<Integer>() {{
					add(32); // $hi
					add(33); // $lo
				}},
				new ArrayList<StatusReg>() {{
					add(StatusReg.ZERO);
					add(StatusReg.CARRY);
					add(StatusReg.SIGN);
					add(StatusReg.OVERFLOW);
				}},
				null, InstructionType.fixed, ExecUnit.alu, 15
		);
	}
}
