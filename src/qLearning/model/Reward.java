package qLearning.model;

public interface Reward {
	
	int getValue();
	int getReward(StateActionPair stateActionPair);
	
	//This function must calculate the reward with the implemented getReward function and put it in the HashTable
	void setValue(StateActionPair stateActionPair);
}
