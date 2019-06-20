package main;

import main.ISA.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sim {
	private static int[] inOrderStatistics;
	private static int[] dynamicStatistics;
	private static ArrayList<Instruction> code = new ArrayList<>();

	public static void main(String args[]) {
		intakeFile(args[0]);

		inOrderExec();
		dynamicExec();

		System.out.println("In order stats");
		for (int inOrderStatistic1 : inOrderStatistics) {
			System.out.print(inOrderStatistic1 + "\t");
		}

		System.out.println("\nOut of order stats");
		for (int dynamicStatistic : dynamicStatistics) {
			System.out.print(dynamicStatistic + "\t");
		}
		System.out.println();
	}


	private static int StringToReg(String temp) {
		if (temp.endsWith(",")) {
			temp = temp.substring(0, temp.length() - 1);
		}

		switch (temp) {
			case "$zero":
				return 0;
			case "$at":
				return 1;
			case "$gp":
				return 28;
			case "$sp":
				return 29;
			case "$fp":
				return 30;
			case "$ra":
				return 31;
			case "$mfhi":
				return 32;
			case "$mflo":
				return 33;
			default:
		}

		int regNum = 0;
		char type = temp.charAt(1);
		int value = Character.getNumericValue(temp.charAt(2));
		switch (type) {
			case 'v':
				regNum += 2;
				break;
			case 'a':
				regNum += 4;
				break;
			case 't':
				regNum += 8;
				break;
			case 's':
				regNum += 16;
				break;
			case 'k':
				regNum += 26;
				break;
			default:
		}
		if (type == 't') {
			if (value >= 8) {
				regNum += 16;
			}
		} else {
			regNum += Character.getNumericValue(temp.charAt(2));
		}
		return regNum;
	}

	private static void inOrderExec() {
		Pipeline p = new Pipeline(new ArrayList<>(code));
		p.inOrderPipeline();
		inOrderStatistics = p.getStatistics();
	}

	private static void dynamicExec() {
		Pipeline p = new Pipeline(new ArrayList<>(code));
		p.dynamicPipeline();
		dynamicStatistics = p.getStatistics();
	}

	private static void intakeFile(String file) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			System.out.println("Error: " + e);
			System.exit(1);
		}

		int a;
		int b;
		String instruction;
		while (sc.hasNext()) {
			instruction = sc.next().toUpperCase();
			switch (instruction) {
				case "ADD":
					code.add(new ADD(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "ADDD":
					code.add(new ADDD(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "ADDI":
					code.add(new ADDI(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "ADDIU":
					code.add(new ADDIU(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "ADDS":
					code.add(new ADDS(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "ADDU":
					code.add(new ADDU(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "AND":
					code.add(new AND(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "ANDI":
					code.add(new ANDI(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "BC1F":
					code.add(new BC1F());
					sc.next();
					break;
				case "BC1T":
					code.add(new BC1T());
					sc.next();
					break;
				case "BEQ":
					code.add(new BEQ(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "BNE":
					code.add(new BNE(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "CEQD":
					code.add(new CEQD(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "CEQS":
					code.add(new CEQS(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "CLED":
					code.add(new CLED(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "CLES":
					code.add(new CLES(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "CLTD":
					code.add(new CLTD(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "CLTS":
					code.add(new CLTS(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "DIV":
					code.add(new DIV(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "DIVD":
					code.add(new DIVD(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "DIVS":
					code.add(new DIVS(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "DIVU":
					code.add(new DIVU(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "J":
					code.add(new J());
					sc.next();
					break;
				case "JAL":
					code.add(new JAL());
					sc.next();
					break;
				case "JR":
					code.add(new JR(StringToReg(sc.next())));
					break;
				case "LBU":
					code.add(new LBU(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "LDC1":
					code.add(new LDC1(StringToReg(sc.next())));
					sc.next();
					break;
				case "LHU":
					code.add(new LHU(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "LL":
					code.add(new LL(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "LUI":
					code.add(new LUI(StringToReg(sc.next())));
					sc.next();
					break;
				case "LW":
					code.add(new LW(StringToReg(sc.next())));
					sc.next();
					break;
				case "LWC1":
					code.add(new LWC1(StringToReg(sc.next())));
					sc.next();
					break;
				case "MFC0":
					code.add(new MFC0(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "MFHI":
					code.add(new MFHI(StringToReg(sc.next())));
					break;
				case "MFLO":
					code.add(new MFLO(StringToReg(sc.next())));
					break;
				case "MULD":
					code.add(new MULD(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "MULS":
					code.add(new MULS(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "MULT":
					code.add(new MULT(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "MULTU":
					code.add(new MULTU(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "NOR":
					code.add(new NOR(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "OR":
					code.add(new OR(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "ORI":
					code.add(new ORI(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "SB":
					code.add(new SB(StringToReg(sc.next())));
					sc.next();
					break;
				case "SC":
					code.add(new SC(StringToReg(sc.next())));
					sc.next();
					break;
				case "SDC1":
					code.add(new SDC1(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "SH":
					code.add(new SH(StringToReg(sc.next())));
					sc.next();
					break;
				case "SLL":
					code.add(new SLL(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "SLT":
					code.add(new SLT(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "SLTI":
					code.add(new SLTI(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "SLTIU":
					code.add(new SLTIU(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "SLTU":
					code.add(new SLTU(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "SRA":
					code.add(new SRA(StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "SRL":
					code.add(new SRL(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "SUB":
					code.add(new SUB(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "SUBD":
					code.add(new SUBD(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "SUBI":
					code.add(new SUBI(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "SUBS":
					code.add(new SUBS(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "SUBU":
					code.add(new SUBU(StringToReg(sc.next()), StringToReg(sc.next()), StringToReg(sc.next())));
					break;
				case "SW":
					code.add(new SW(StringToReg(sc.next())));
					sc.next();
					break;
				case "SWC1":
					code.add(new SWC1(StringToReg(sc.next()), StringToReg(sc.next())));
					sc.next();
					break;
				case "NOP":
					code.add(new NOP());
					break;
				case "ABS":
					a = StringToReg(sc.next());
					b = StringToReg(sc.next());
					code.add(new ADDU(a, b, 0));
					code.add(new SLT(1, b, 0));
					code.add(new BEQ(1, 0)); // if eq skip sub but assuming sub needs to be done
					code.add(new SUB(a, b, 0));
					break;
				case "B":
					code.add(new BEQ(0, 0));
					sc.next();
					break;
				case "BAL":
					code.add(new JAL());
					sc.next();
					break;
				case "BEQZ":
					code.add(new BEQ(StringToReg(sc.next()), 0));
					sc.next();
					break;
				case "BGE":
					code.add(new SLT(1, StringToReg(sc.next()), StringToReg(sc.next())));
					code.add(new BEQ(1, 0));
					sc.next();
					break;
				case "BGT":
					a = StringToReg(sc.next());
					b = StringToReg(sc.next());
					code.add(new SLT(1, b, a));
					code.add(new BNE(1, 0));
					sc.next();
					break;
				case "BGTU":
					a = StringToReg(sc.next());
					b = StringToReg(sc.next());
					code.add(new SLTU(1, b, a));
					code.add(new BNE(1, 0));
					sc.next();
					break;
				case "BLE":
					a = StringToReg(sc.next());
					b = StringToReg(sc.next());
					code.add(new SLT(1, b, a));
					code.add(new BEQ(1, 0));
					sc.next();
					break;
				case "BLT":
					code.add(new SLT(1, StringToReg(sc.next()), StringToReg(sc.next())));
					code.add(new BNE(1, 0));
					sc.next();
					break;
				case "CLEAR":
					code.add(new OR(StringToReg(sc.next()), 0, 0));
					break;
				case "LA":
					code.add(new LUI(1));
					code.add(new ORI(StringToReg(sc.next()), 1));
					sc.next();
					break;
				case "LI": // assuming 32 bit immediate
					code.add(new LUI(1));
					code.add(new ORI(StringToReg(sc.next()), 1));
					sc.next();
					break;
				case "MOVE":
					code.add(new ADD(StringToReg(sc.next()), StringToReg(sc.next()), 0));
					break;
				case "MUL":
					a = StringToReg(sc.next());
					code.add(new MULT(StringToReg(sc.next()), StringToReg(sc.next())));
					code.add(new MFLO(a));
					break;
				default:
					System.out.println("No command " + instruction + ". Exiting.");
					System.exit(1);
			}
		}
	}
}
