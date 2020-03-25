/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.ui.wizards.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.ui.ImageExt2;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.IImageKeys;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 */
public class SelectInProjectExplorerAction extends Action {

  /** */
  private ISelection selection;

  /**
   * 
   */
  public SelectInProjectExplorerAction(ISelection selection) {
    super();
    this.selection = selection;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void run() {
    try {
      IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      // Get the Capella Explorer.
      CapellaCommonNavigator explorerView = (CapellaCommonNavigator) activePage.findView(CapellaCommonNavigator.ID);
      if (null == explorerView) {
        // Show it if not found.
        explorerView = (CapellaCommonNavigator) activePage.showView(CapellaCommonNavigator.ID);
      }
      explorerView.selectReveal(selection);
    } catch (PartInitException exception) {
    	// Catch exception silently,
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ImageDescriptor getImageDescriptor() {
    return ImageExt2.getImageDescriptor(FrameworkUtil.getBundle(CapellaNavigatorPlugin.class).getSymbolicName(), IImageKeys.IMG_SHOW_IN_CAPELLA_EXPLORER);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText() {
    return "Show in Capella Explorer"; //$NON-NLS-1$
  }
}
