/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.tools.ju.tb;

public class SA_IDTitleBlockTestCase extends IDTitleBlockTestCase {
  private static final String INTERFACE_ID = "18ee57e4-8970-43aa-93d6-5fa4334ad5ab";
  private static final String DIAGRAM_NAME = "[ID] Interface System";

  @Override
  protected void initData() {
    elementsId.add(INTERFACE_ID);
    diagramName = DIAGRAM_NAME;
    interfaceID = INTERFACE_ID;
  }

}