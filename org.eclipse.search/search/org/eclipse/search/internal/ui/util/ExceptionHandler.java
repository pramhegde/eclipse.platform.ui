/*
 * (c) Copyright IBM Corp. 2000, 2001.
 * All Rights Reserved.
 */
package org.eclipse.search.internal.ui.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.search.internal.ui.SearchPlugin;
import org.eclipse.search.ui.SearchUI;

/**
 * Shows an error dialog for exceptions that contain an <code>IStatus</code>.
 * If the throwable passed to the methods is of a kind that the methods can handle, 
 * the error dialog is shown and <code>true</code> is returned. Otherwise <code>false
 * </code>is returned, and the client has to handle the error itself. If the passed
 * throwable is of type <code>InvocationTargetException</code> the wrapped excpetion
 * is considered.
 */
public class ExceptionHandler {

	private static ExceptionHandler fgInstance= new ExceptionHandler();

	/**
	 * Shows an error dialog for exceptions that contain an <code>IStatus</code>.
	 */
	public static boolean handle(Throwable t, String title, String message) {
		return handle(t, SearchPlugin.getActiveWorkbenchShell(), title, message);	
	}
	/**
	 * Shows an error dialog for exceptions that contain an <code>IStatus</code>.
	 */
	public static boolean handle(Throwable t, Shell shell, String title, String message) {
		if (fgInstance == null)
			return false;
		return fgInstance.perform(t, shell, title, message);	
	}
	/**
	 * Logs the given exception using the platforms logging mechanism.
	 */
	public static void log(Throwable t, String message) {
		SearchPlugin.log(new Status(IStatus.ERROR, SearchUI.PLUGIN_ID, 
			IStatus.ERROR, message, t));
	}
	/**
	 * Actually displays the error message. Subclasses may override the method to
	 * perform their own error handling.
	 */
	protected boolean perform(Throwable t, Shell shell, String title, String message) {
		if (t instanceof InvocationTargetException)
			t= ((InvocationTargetException)t).getTargetException();
		if (handleCoreException(t, shell, title, message))
			return true;
		return handleCriticalExceptions(t, shell, title, message);
	}

	protected boolean handleCoreException(Throwable t, Shell shell, String title, String message) {
		IStatus status= null;
		if (t instanceof CoreException) {
			status= ((CoreException)t).getStatus();
			if (status != null)
				ErrorDialog.openError(shell, title, message, status);
			else
				displayMessageDialog(t, shell, title, message);
			return true;
		}
		return false;
	}

	protected boolean handleCriticalExceptions(Throwable t, Shell shell, String title, String message) {
		if (t instanceof RuntimeException || t instanceof Error) {
			log(t, message);
			displayMessageDialog(t, shell, title, message);
			return true;
		}
		return false;	
	}
	/**
	 * Shows the error in a message dialog
	 */
	protected void displayMessageDialog(Throwable t, Shell shell, String title, String message) {
		StringWriter msg= new StringWriter();
		if (message != null) {
			msg.write(message);
			msg.write("\n\n"); //$NON-NLS-1$
		}
		if (t.getMessage() == null || t.getMessage().length() == 0)
			msg.write(t.toString());
		else
			msg.write(t.getMessage());
		MessageDialog.openError(shell, title, msg.toString());			
	}
	/**
	 * Shows a dialog containing the stack trace of the exception
	 */
	public static void showStackTraceDialog(Throwable t, Shell shell, String title) {
		StringWriter writer= new StringWriter();
		t.printStackTrace(new PrintWriter(writer));
		MessageDialog.openError(shell, title, writer.toString());
	}	
}