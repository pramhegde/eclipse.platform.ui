/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.ui.part.services;

import org.eclipse.core.runtime.IStatus;

/**
 * Service for constructing IStatus objects. IStatus objects represent
 * individual messages which will be logged or displayed to the user. Implementations
 * of <code>IStatusFactory</code> will attach context to the
 * IStatus, such as the plugin that generated the message.
 * 
 * <p>
 * Not intended to be implemented by clients.
 * </p>
 * 
 * <p>EXPERIMENTAL: The components framework is currently under active development. All
 * aspects of this class including its existence, name, and public interface are likely
 * to change during the development of Eclipse 3.1</p>
 * 
 * @since 3.1
 */
public interface IStatusFactory {
    /**
     * Constructs an error status for the given throwable.
     * 
     * @param t throwable associated with the error
     * @return a newly constructed Status message
     */
    public IStatus newStatus(Throwable t);
    
    /**
     * Constructs an error message from the given user-readable string
     * and the given throwable.
     * 
     * @param message user-readable string describing the error
     * @param t exception that caused the error
     * @return a newly constructed Status message
     */
    public IStatus newStatus(String message, Throwable t);
    
    /**
     * Constructs a status message with the given severity and the given
     * user-readable string
     * 
     * @param severity one of Status.* severity constants
     * @param message user-readable string
     * @return a newly constructed Status message
     */
    public IStatus newStatus(int severity, String message);

}
