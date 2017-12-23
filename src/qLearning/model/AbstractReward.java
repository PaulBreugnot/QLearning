package qLearning.model;

import java.util.Hashtable;

public abstract class AbstractReward implements Reward{
	private StateActionPair stateActionPair;
	private static Hashtable<StateActionPair, Integer> HashReward = new Hashtable<>();
	

	
	protected static void putHashtableValue(StateActionPair stateActionPair, int value) {
		HashReward.put(stateActionPair, value);
	}
	
	public int getValue() {
		return HashReward.get(stateActionPair);
	}
}
