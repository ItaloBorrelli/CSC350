package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

/**
 * statistics legend:
 * 0 = total time in queue
 * 1 = time in queue floating point
 * 2 = time in queue fixed point
 * 3 = time in queue branch
 * 4 = time in queue memory
 * 5 = time in queue logical
 * 6 = total time in system
 * 7 = time in system floating point
 * 8 = time in system fixed point
 * 9 = time in system branch
 * 10 = time in system memory
 * 11 = time in system logical
 * 12 = number of cycles
 * 13 = number of instructions
 * 14 = no fp instructions
 * 15 = no fixed point instructions
 * 16 = no branch instructions
 * 17 = no mem instructions
 * 18 = no logical instructions
 */
class Pipeline {
	private ArrayList<Instruction> code;
	private ArrayList<Instruction> queue = new ArrayList<>();
	private int maxQueueSize = 16;
	private int numPipes = 2;

	private int clock = 0;

	private HashMap<ExecUnit, int[]> xUnits = new HashMap<>();
	private int[] regAvail = new int[34];
	private int[] condReg = new int[4];

	private int[] statistics = new int[19];

	Pipeline(ArrayList<Instruction> code) {
		this.code = code;
		this.xUnits.put(ExecUnit.alu, new int[numPipes]);
		this.xUnits.put(ExecUnit.fpalu, new int[numPipes]);
		this.xUnits.put(ExecUnit.lsu, new int[numPipes]);
	}

	void inOrderPipeline() {
		while (!code.isEmpty() || !queue.isEmpty()) {
			for (int i = queue.size(); i < maxQueueSize; i++) {
				if (code.isEmpty()) break;

				Instruction instruction = code.remove(0);
				instruction.enterQueue = clock;
				queue.add(instruction);
			}

			for (int i = 0; i < numPipes; i++) {
				if (queue.isEmpty()) break;

				if (canRun(queue.get(0))) {
					runInstruction(queue.remove(0));
					statistics[13]++;
				} else break;
			}

			clock++;
		}

		statistics[12] = clock;
	}

	void dynamicPipeline() {
		while (!code.isEmpty() || !queue.isEmpty()) {
			for (int i = queue.size(); i < maxQueueSize; i++) {
				if (code.isEmpty()) break;

				Instruction instruction = code.remove(0);
				instruction.enterQueue = clock;
				queue.add(instruction);
			}

			int instructionsRun = 0;
			int currentInstruction = 0;
			while (currentInstruction < queue.size() && instructionsRun < numPipes) {
				if (queue.isEmpty()) break;

				boolean ranInstruction = false;
				if (canRun(queue.get(currentInstruction))) {
					ranInstruction = true;
					runInstruction(queue.remove(currentInstruction));
					instructionsRun++;
					statistics[13]++;
				}

				if (!ranInstruction) currentInstruction++;
			}

			clock++;
		}

		statistics[12] = clock;
	}

	private boolean canRun(Instruction instruction) {
		return xUnitCheck(instruction.getExecUnit())
				&& regCheck(instruction.getFromReg())
				&& condCheck(instruction.getCondChecked());
	}

	private boolean xUnitCheck(ExecUnit execUnit) {
		if (execUnit == null) return true;

		int[] a = xUnits.get(execUnit);
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			min = (a[i] < min) ? a[i] : min;
		}
		return min <= clock;
	}

	private boolean regCheck(ArrayList<Integer> fromReg) {
		for (Integer integer : fromReg) {
			if (regAvail[integer] > clock && integer != 0) {
				return false;
			}
		}

		return true;
	}

	private boolean condCheck(StatusReg condChecked) {
		if (condChecked == null) return true;
		return condReg[condChecked.ordinal()] <= clock;
	}

	private void runInstruction(Instruction instruction) {
		int cycles = instruction.getCycles();
		updateXUnits(instruction.getExecUnit(), cycles);
		updateRegAvail(instruction.getToReg(), cycles);
		updateCondReg(instruction.getCondAffected(), cycles);

		// update statistics
		int timeInQueue = clock - instruction.enterQueue;
		statistics[0] += timeInQueue;
		statistics[6] += cycles + timeInQueue;

		if (instruction.getType() != null) {
			statistics[instruction.getType().ordinal() + 1] += timeInQueue;
			statistics[6 + instruction.getType().ordinal() + 1] += cycles + timeInQueue;
			statistics[14 + instruction.getType().ordinal()] += 1;
		}
	}

	private void updateXUnits(ExecUnit execUnit, int cycles) {
		if (execUnit == null) return;

		int[] a = xUnits.get(execUnit);
		int min = a[0];
		for (int i = 1; i < a.length; i++) {
			min = (a[i] < min) ? a[i] : min;
		}

		int target = min;
		int index = IntStream.range(0, a.length)
				.filter(i -> target == a[i])
				.findFirst()
				.orElse(-1);

		a[index] = clock + cycles;

		statistics[12] = (clock + cycles > statistics[12]) ? clock + cycles : statistics[12];

		xUnits.put(execUnit, a);
	}

	private void updateRegAvail(ArrayList<Integer> toReg, int cycles) {
		for (Integer reg : toReg) {
			if (0 <= reg && reg < regAvail.length) {
				regAvail[reg] = clock + cycles;

				statistics[12] = (clock + cycles > statistics[12]) ? clock + cycles : statistics[12];
			}
		}
	}

	private void updateCondReg(ArrayList<StatusReg> condAffected, int cycles) {
		for (StatusReg statusReg : condAffected) {
			condReg[statusReg.ordinal()] = clock + cycles;

			statistics[12] = (clock + cycles > statistics[12]) ? clock + cycles : statistics[12];
		}
	}

	int[] getStatistics() {
		return statistics;
	}
}
