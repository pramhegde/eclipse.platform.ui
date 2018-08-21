/*******************************************************************************
 * Copyright (c) 2014 TwelveTone LLC and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Steven Spungin <steven@spungin.tv> - initial API and implementation, Bug 436283
 *******************************************************************************/

package org.eclipse.e4.tools.emf.ui.common;

/**
 * Provider status used in IProviderStatusCallback
 *
 * @author Steven Spungin
 *
 */
public enum ProviderStatus {
	INITIALIZING, READY, CANCELLED
}
