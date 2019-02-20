/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.fa.properties.sections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.data.core.properties.sections.CapellaElementSection;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.ui.properties.controllers.SimpleSemanticFieldController;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.fields.ConstraintReferenceGroup;
import org.polarsys.capella.core.ui.properties.fields.SimpleSemanticField;

/**
 * The SequenceLink section.
 */
public class SequenceLinkSection extends CapellaElementSection {

  protected ConstraintReferenceGroup conditionField;
  private SimpleSemanticField sourceField;
  private SimpleSemanticField targetField;

  /**
   * Default constructor.
   */
  public SequenceLinkSection() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createControls(parent, aTabbedPropertySheetPage);

    boolean displayedInWizard = isDisplayedInWizard();

    conditionField = new ConstraintReferenceGroup(Collections.singletonMap(Messages.SequenceLinkSection_Condition_Label,
        FaPackage.Literals.SEQUENCE_LINK__CONDITION));
    conditionField.createControls(rootParentComposite, getWidgetFactory(), isDisplayedInWizard());

    sourceField = new SimpleSemanticField(getReferencesGroup(), Messages.SequenceLinkSection_Source_Label,
        getWidgetFactory(), new SimpleSemanticFieldController());
    sourceField.setDisplayedInWizard(displayedInWizard);

    targetField = new SimpleSemanticField(getReferencesGroup(), Messages.SequenceLinkSection_Target_Label,
        getWidgetFactory(), new SimpleSemanticFieldController());
    targetField.setDisplayedInWizard(displayedInWizard);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    conditionField.loadData(capellaElement);
    sourceField.loadData(capellaElement, FaPackage.Literals.SEQUENCE_LINK__SOURCE);
    targetField.loadData(capellaElement, FaPackage.Literals.SEQUENCE_LINK__TARGET);
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == FaPackage.eINSTANCE.getSequenceLink()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    List<AbstractSemanticField> fields = new ArrayList<AbstractSemanticField>();

    fields.addAll(super.getSemanticFields());
    fields.addAll(conditionField.getFields());
    fields.add(sourceField);
    fields.add(targetField);

    return fields;
  }
}
