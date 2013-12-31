/**
 * @author envy3d
 */

package com.envy3d.tictactoe;

public abstract class View {
	protected int marker;
	protected Model model;
	
	public View(int marker, Model model) {
		this.marker = marker;
		this.model = model;
	}
}
