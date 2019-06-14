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
package org.polarsys.capella.test.diagram.filters.ju.cii;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.sirius.analysis.constants.IFilterNameConstants;

public class HideExchangeItemsForCII extends FiltersForCII {

  @Override
  protected String getFilterName() {
    return IFilterNameConstants.FILTER_LCCII_HIDE_EXCHANGE_ITEMS;
  }

  @Override
  protected List<String> getFilteredObjetIDs() {
    return Arrays.asList(new String[] { TEST_EXCHANGE_ITEM_3_ID, EXCHANGE_ITEM_2_ID, SEND_ID, CONSUME_ID, PRODUCE_ID,
        EXCHANGE_ITEM_1_ID, LOGICAL_SYSTEM_TO_EXCHANGE_ITEM_SEND_ID });
  }

}
