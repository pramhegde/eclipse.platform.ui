/**********************************************************************
Copyright (c) 2000, 2002 IBM Corp. and others.
All rights reserved. This program and the accompanying materials
are made available under the terms of the Common Public License v1.0
which accompanies this distribution, and is available at
http://www.eclipse.org/legal/cpl-v10.html

Contributors:
    IBM Corporation - Initial implementation
**********************************************************************/

package org.eclipse.ui;


/**
 * 2.1 - WORK_IN_PROGRESS do not use.
 */


/**
 * Default implementation of INavigationLocation. */
public abstract class NavigationLocation implements INavigationLocation {
	
	private IWorkbenchPage page;
	private IEditorInput input;
	private String text;
	
	/**
	 * Constructs a NavigationLocation with its editor part.
	 * 	 * @param editorPart	 */
	protected NavigationLocation(IEditorPart editorPart) {
		this.page = editorPart.getSite().getPage();
		this.input = editorPart.getEditorInput();
		this.text = editorPart.getTitle();
	}
	/** 
	 * Returns the part that the receiver holds the location for.
	 * 	 * @return IEditorPart	 */
	protected IEditorPart getEditorPart() {
		if (input == null)
			return null;
		return page.findEditor(input);
	}
	/*
	 * (non-Javadoc)
	 * Method declared on INavigationLocation.
	 */
	public Object getInput() {
		return input;
	}
	/*
	 * (non-Javadoc)
	 * Method declared on INavigationLocation.
	 */
	public String getText() {
		return text;
	}
	/*
	 * (non-Javadoc)
	 * Method declared on INavigationLocation.
	 */
	public void setInput(Object input) {
		this.input = (IEditorInput)input;
	}	
	/**
	 * May be extended by clients.
	 *
	 * @see org.eclipse.ui.INavigationLocation#dispose()
	 */
	public void dispose() {
		releaseState();
	}
	/**
	 * May be extended by clients.
	 * 	 * @see org.eclipse.ui.INavigationLocation#releaseState()	 */
	public void releaseState() {
		input = null;
	}	
	/*
	 * (non-Javadoc)
	 * Method declared on INavigationLocation.
	 */
	public void setText(String text) {
		this.text = text;
	}
}