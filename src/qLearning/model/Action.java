package qLearning.model;

import java.util.ArrayList;

public interface Action {

	public static Object getRandomAction(ArrayList<Action> availableActions) {
		if(availableActions.size()>0) {
		return availableActions.get((int) Math.floor(Math.random() * availableActions.size()));
		}
		return null;
	}
	
	Object getValue();
	
	void executeAction();
	
	@Override
	int hashCode();
	
	@Override
	boolean equals(Object stateActionPair);
}
