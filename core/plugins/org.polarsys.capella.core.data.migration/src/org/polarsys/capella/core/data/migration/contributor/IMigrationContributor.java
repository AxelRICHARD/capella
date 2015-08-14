/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.contributor;

import java.util.Collection;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.polarsys.capella.core.data.migration.AbstractMigrationRunnable;

/**
 * 
 */
public interface IMigrationContributor {

  public String getKind();

  public Collection<IResource> getMigrableFiles(IResource root);

  public boolean isValidResource(IResource member);

  public AbstractMigrationRunnable getRunnable(IFile file);

}
