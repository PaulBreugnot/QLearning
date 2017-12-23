package qLearning.model;

public interface State {
	@Override
	int hashCode();
	
	@Override
	boolean equals(Object stateActionPair);
	
	boolean isTerminal();
}
