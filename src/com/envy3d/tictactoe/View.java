/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

public abstract class View {
	protected int marker;
	protected Controller controller;
	public boolean isTurn;
	
	public View(int marker, Controller controller) {
		this.marker = marker;
		this.controller = controller;
	}
}
