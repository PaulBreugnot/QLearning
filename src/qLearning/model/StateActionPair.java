package qLearning.model;

public class StateActionPair {

	private State state;
	private Action action;

	public StateActionPair(State state, Action action) {
		this.state = state;
		this.action = action;
	}

	public State getState() {
		return state;
	}

	public Action getAction() {
		return action;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((action == null) ? 0 : action.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object stateActionPair) {
		if (stateActionPair == null)
			return false;
		if (stateActionPair.getClass() == getClass())
			return this.state.equals(((StateActionPair) stateActionPair).getState())
					&& this.action.equals(((StateActionPair) stateActionPair).getAction());
		return false;
	}

	@Override
	public String toString() {
		return "StateActionPair [state=" + state + ", action=" + action + "]";
	}

}
