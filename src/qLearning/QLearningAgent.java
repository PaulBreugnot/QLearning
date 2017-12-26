package qLearning;

import java.util.ArrayList;
import java.util.Hashtable;

import qLearning.model.Action;
import qLearning.model.Reward;
import qLearning.model.State;
import qLearning.model.StateActionPair;

public class QLearningAgent {
	static double alpha = 0.2;
	static double gamma = 0.9;
	static double epsilon = 0.99;
	static double epsilonThreshold = 0.01;

	private State previousState;
	private Action previousAction;
	private Reward previousReward;

	private State currentState;

	private Action nextAction;

	static ArrayList<Action> availableActions = new ArrayList<>();
	static Hashtable<StateActionPair, Double> QLearningTable = new Hashtable<>();

	public QLearningAgent(State currentState, Action previousAction, Reward previousReward) {
		this.currentState = currentState;
		this.previousAction = previousAction;
		setPreviousReward(previousReward);
	}

	public void setCurrentState(State state, Reward previousReward) {
		previousState = currentState;
		previousAction = nextAction;
		currentState = state;
		setPreviousReward(previousReward);
	}

	public void setPreviousAction(Action action) {
		previousAction = action;
	}

	public void setPreviousReward(Reward previousReward) {
		if (previousState != null) {
			this.previousReward = previousReward;
		}
	}

	public Action getAction() {
		Learn();
		return nextAction;
	}

	public ArrayList<Action> getAvailableActions() {
		return availableActions;
	}

	private void Learn() {
		StateActionPair previousStateActionPair = new StateActionPair(previousState, previousAction);
		if (currentState.isTerminal()) {
			QLearningTable.put(previousStateActionPair, (double) previousReward.getValue());
			nextAction = null;
		} else {
			if (previousState != null) {
				if (QLearningTable.get(previousStateActionPair) == null) {
					QLearningTable.put(previousStateActionPair, 0.0);
				}
				// Reinforcement

				System.out.println("previousStateActionPair : " + previousStateActionPair);
				System.out.println("OldQValue : " + QLearningTable.get(previousStateActionPair));

				double newQValue = QLearningTable.get(previousStateActionPair) + alpha * (previousReward.getValue()
						+ gamma * maxQValue(currentState) - QLearningTable.get(previousStateActionPair));

				System.out.println("newQValue : " + newQValue);

				QLearningTable.put(previousStateActionPair, newQValue);

				double p = Math.random();
				if (p < epsilon) {
					// Exploration
					nextAction = (Action) Action.getRandomAction(availableActions);
				}
				if (epsilon > epsilonThreshold) {
					epsilon = epsilon * 0.9999;
				}
			}

			// initState
			else {
				maxQValue(currentState);
			}
		}
	}

	private double maxQValue(State state) {
		double maxQValue = -Double.MAX_VALUE;
		if (availableActions.size() > 0) {
			for (Action action : availableActions) {
				StateActionPair s = new StateActionPair(state, action);
				if (QLearningTable.get(s) == null) {
					QLearningTable.put(s, 0.0);
				}
				if (QLearningTable.get(s) > maxQValue) {
					maxQValue = QLearningTable.get(s);
					// Exploitation
					nextAction = s.getAction();
				}
			}
			// System.out.println("Explored states number : " +
			// QLearningTable.keySet().size());
		}
		if (maxQValue == -Double.MAX_VALUE) {
			nextAction = null;
			return 0;
		}
		return maxQValue;
	}

	public void setParameters(double alpha, double gamma, double epsilonThreshold) {
		QLearningAgent.alpha = alpha;
		QLearningAgent.gamma = gamma;
		QLearningAgent.epsilonThreshold = epsilonThreshold;
	}

	public void setAvailableActions(ArrayList<Action> availableActions) {
		QLearningAgent.availableActions = availableActions;
	}
}
