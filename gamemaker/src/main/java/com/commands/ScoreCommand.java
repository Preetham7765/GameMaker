package com.commands;

//import breakout.ScreenElements.ScoreBoard;

public class ScoreCommand implements Command {

	private static final long serialVersionUID = -8955725810338038703L;
	//	private ScoreBoard scoreBoard;
	protected int oldScore; 
	
	public int getOldScore() {
		return oldScore;
	}

	public void setOldScore(int oldScore) {
		this.oldScore = oldScore;
	}

	/**
	 * Store the current state then update
	 */
//	public ScoreCommand(ScoreBoard scoreBoard) {
//		super();
//		this.scoreBoard = scoreBoard;
//		this.oldScore = scoreBoard.getScore();
//		execute();
//	}
	
	/**
	 * This command has been created when a brick was hit, so we increment the score
	 */
	public void execute() {
//		this.scoreBoard.incrementScore();
	}

	/**
	 * This command has been created when a brick was un-hit, so we return score to previous state
	 */
	@Override
	public void undo() {
//		this.scoreBoard.setScore(oldScore);
	}
	
//	public String save() {
//		return "ScoreBoard Score= " + oldScore;
//	}
}
