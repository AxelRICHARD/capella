/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.analysis.providers;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.viewpoint.description.DAnnotation;
import org.eclipse.sirius.viewpoint.description.provider.DAnnotationItemProvider;
import org.polarsys.capella.core.diagram.helpers.TitleBlockHelper;

public class TitleBlockItemProvider extends DAnnotationItemProvider {
  public TitleBlockItemProvider(AdapterFactory adapterFactory) {
    super(adapterFactory);
  }

  public String getString(String key) {
    return "Title Block";
  }

  @Override
  public Object getParent(Object object) {
    DAnnotation annotation = (DAnnotation) object;
    if (TitleBlockHelper.isElementTitleBlock(annotation)) {
      return TitleBlockHelper.getReferencedElement(annotation);

    } else if (TitleBlockHelper.isTitleBlockCell(annotation) || TitleBlockHelper.isTitleBlockLine(annotation)
        || TitleBlockHelper.isContentTitleBlock(annotation)) {
      DAnnotation parent = TitleBlockHelper.getParentAnnotation(annotation);
      if (parent != null) {
        return parent;
      }
    }
    return super.getParent(object);
  }

  @Override
  protected Command factorRemoveCommand(EditingDomain domain, CommandParameter commandParameter) {
      Command factorRemoveCommand =  super.factorRemoveCommand(domain, commandParameter);
      if (factorRemoveCommand instanceof UnexecutableCommand) {
          /* 
           * When a TitleBlock is deleted, UnexecutableCommands are created.
           * It prevents TitleBlock from deletion, but the TitleBlock is still deleted thanks to 
           * org.polarsys.capella.core.sirius.analysis.TitleBlockServices.deleteTitleBlock() 
           * called by /org.polarsys.capella.core.sirius.analysis/description/common.odesign.
           * Problems occur when the TitleBlock is not deleted alone (e.g. with any semantic 
           * Capella element). In these cases, the semantic elements are not deleted, because
           * the CompoundCommand is not executed due to the UnexecutableCommand.
           * By replacing UnexecutableCommands with DoNothingCommands, the problems are solved.
           */
          return DoNothingCommand.INSTANCE;
      }
      return factorRemoveCommand;
  }

  /**
   * A singleton {@link DoNothingCommand#INSTANCE} that do nothing.
   */
  private static class DoNothingCommand extends AbstractCommand {
      /**
       * The one instance of this object.
       */
      public static final DoNothingCommand INSTANCE = new DoNothingCommand();

      /**
       * Only one private instance is created.
       */
      private DoNothingCommand() {
          super("Do nothing");
      }

      @Override
      public boolean canExecute() {
          return true;
      }

      @Override
      public void execute() {
      }

      @Override
      public boolean canUndo() {
          return true;
      }

      @Override
      public void redo() {
      }
  }
}