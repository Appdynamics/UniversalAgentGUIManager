/*******************************************************************************
 * Copyright 2018 AppDynamics LLC
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.appdynamics.universalagent.gui;

/**
 * 
 * @author nikolaos.papageorgiou
 *
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.plaf.basic.BasicTextFieldUI;
import javax.swing.text.JTextComponent;

public class HintTextFieldUI extends BasicTextFieldUI implements FocusListener {

	private String hint;
	private boolean hideOnFocus;
	private Color color;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		repaint();
	}

	private void repaint() {
		if (getComponent() != null) {
			getComponent().repaint();
		}
	}

	public boolean isHideOnFocus() {
		return hideOnFocus;
	}

	public void setHideOnFocus(boolean hideOnFocus) {
		this.hideOnFocus = hideOnFocus;
		repaint();
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
		repaint();
	}

	public HintTextFieldUI(String hint) {
		this(hint, false);
	}

	public HintTextFieldUI(String hint, boolean hideOnFocus) {
		this(hint, hideOnFocus, null);
	}

	public HintTextFieldUI(String hint, boolean hideOnFocus, Color color) {
		this.hint = hint;
		this.hideOnFocus = hideOnFocus;
		this.color = color;
	}

	@Override
	protected void paintSafely(Graphics g) {
		super.paintSafely(g);
		JTextComponent comp = getComponent();
		if (hint != null && comp.getText().length() == 0 && (!(hideOnFocus && comp.hasFocus()))) {
			if (color != null) {
				g.setColor(color);
			} else {
				g.setColor(comp.getForeground().brighter().brighter());
			}
			int padding = (comp.getHeight() - comp.getFont().getSize()) / 2;
			// g.drawString(hint, 2, comp.getHeight()-padding-1);
			g.drawString(hint, 10, comp.getHeight() - padding - 1);
		}
	}

	public void focusGained(FocusEvent e) {
		if (hideOnFocus)
			repaint();

	}

	public void focusLost(FocusEvent e) {
		if (hideOnFocus)
			repaint();
	}

	@Override
	protected void installListeners() {
		super.installListeners();
		getComponent().addFocusListener(this);
	}

	@Override
	protected void uninstallListeners() {
		super.uninstallListeners();
		getComponent().removeFocusListener(this);
	}
}
