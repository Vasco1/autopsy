/*
 * Autopsy Forensic Browser
 *
 * Copyright 2017 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.filesearch;

import java.awt.event.ActionListener;
import org.openide.util.NbBundle;
import org.sleuthkit.autopsy.filesearch.FileSearchFilter.FilterValidationException;

/**
 *
 */
class HashSearchFilter extends AbstractFileSearchFilter<HashSearchPanel> {

    private static final String EMPTY_HASH_MESSAGE = NbBundle
            .getMessage(HashSearchFilter.class, "HashSearchPanel.emptyHashMsg.text");

    public HashSearchFilter() {
        this(new HashSearchPanel());
    }

    public HashSearchFilter(HashSearchPanel component) {
        super(component);
    }

    @Override
    public boolean isEnabled() {
        return this.getComponent().getHashCheckBox().isSelected();
    }

    @Override
    public String getPredicate() throws FilterValidationException {
        String md5Hash = this.getComponent().getSearchTextField().getText();

        if (md5Hash.isEmpty()) {
            throw new FilterValidationException(EMPTY_HASH_MESSAGE);
        }

        return "md5 = '" + md5Hash.toLowerCase() + "'"; //NON-NLS
    }

    @Override
    public void addActionListener(ActionListener l) {
        getComponent().addActionListener(l);
    }

    @Override
    public boolean isValid() {
        return !this.getComponent().getSearchTextField().getText().isEmpty();
    }
}
