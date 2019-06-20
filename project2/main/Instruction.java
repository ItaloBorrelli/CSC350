package main;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Instruction {
	private ArrayList<Integer> fromReg;
	private ArrayList<Integer> toReg;

	private ArrayList<StatusReg> condAffected;
	private StatusReg condChecked;

	private InstructionType type;

	private ExecUnit execUnit;
	private int cycles;

	int enterQueue;

	public Instruction(ArrayList<Integer> fromReg,
					   ArrayList<Integer> toReg,
					   ArrayList<StatusReg> condAffected,
					   StatusReg condChecked,
					   InstructionType type,
					   ExecUnit execUnit,
					   int cycles) {

		this.fromReg = fromReg;
		this.toReg = toReg;

		this.condAffected = condAffected;
		this.condChecked = condChecked;

		this.type = type;

		this.execUnit = execUnit;
		this.cycles = cycles;
	}

	ArrayList<Integer> getFromReg() {
		return fromReg;
	}

	ArrayList<Integer> getToReg() {
		return toReg;
	}

	ArrayList<StatusReg> getCondAffected() {
		return condAffected;
	}

	StatusReg getCondChecked() {
		return condChecked;
	}

	InstructionType getType() {
		return type;
	}

	ExecUnit getExecUnit() {
		return execUnit;
	}

	int getCycles() {
		return cycles;
	}
}
