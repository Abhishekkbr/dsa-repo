package com.dynamic;

public class DiceRoll {
	private static Integer[][] memo;
	private static final int MOD = 1_000_000_007;
	
	public static void main(String[] args) {
		int dice = 2;
		int faces = 6;
		int target = 5;
		
		memo = new Integer[faces+1][target+1];
		System.out.println(possibleCombination(dice, faces, target));
		System.out.println(combination(dice, faces, target));
	}

	private static int possibleCombination(int dice, int faces, int target) {		
		if(target < 0) return 0;
		if(dice == 0) return target == 0 ? 1 : 0;           // [1,6,4]  [0,6,0]   // if dice and target both becomes 0 then means one combination is there
		if(memo[dice][target] != null) return memo[dice][target];
		
		int ways = 0;
		for(int i = 1 ; i <= faces ; i++) {
			ways = ways + possibleCombination(dice-1, faces, target-i) % MOD;
		}
		return memo[dice][target] = ways;
	}


	private static int combination(int dice, int faces, int target) {
		if (target < 0) {
			return 0;
		}
		if(dice == 0) return target == 0 ? 1 : 0;
		if(memo[dice][target] != null) return memo[dice][target];

		int ways = 0;

		for(int i = 0 ; i <= faces ; i++) {
			ways += combination(dice-1, faces, target-i) % MOD;
		}
		return memo[dice][target] = ways;
	}
}
