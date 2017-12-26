package qLearning.model;

import java.util.ArrayList;

public interface State {
	@Override
	int hashCode();
	
	@Override
	boolean equals(Object stateActionPair);
	
	ArrayList<Action> getAvailableActions();
	
	boolean isTerminal();
}
