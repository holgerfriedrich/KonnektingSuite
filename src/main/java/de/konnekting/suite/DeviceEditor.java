/*
 * Copyright (C) 2016 Alexander Christian <alex(at)root1.de>. All rights reserved.
 * 
 * This file is part of KONNEKTING Suite.
 *
 *   KONNEKTING Suite is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   KONNEKTING Suite is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with KONNEKTING DeviceConfig.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.konnekting.suite;

import de.konnekting.deviceconfig.DeviceConfigContainer;
import de.root1.rooteventbus.RootEventBus;
import de.konnekting.suite.events.EventDeviceListRefresh;
import de.konnekting.suite.events.StickyDeviceSelected;

/**
 *
 * @author achristian
 */
public class DeviceEditor extends javax.swing.JPanel {

    /**
     * Creates new form DeviceEditor
     */
    public DeviceEditor() {
        initComponents();
        RootEventBus.getDefault().registerSticky(this);
    }

    public void onEvent(StickyDeviceSelected event) {
        DeviceConfigContainer device = event.getDeviceConfig();
        if (device == null) {
            userdescriptionTextField.setText("");
            userdescriptionTextField.setEnabled(false);

            individualAddressInputPanel.setDevice(null);
            deviceInformationPanel.setDeviceConfig(null);

        } else {

            userdescriptionTextField.setText(device.getDescription());
            userdescriptionTextField.setEnabled(true);
            deviceInformationPanel.setDeviceConfig(device);
            individualAddressInputPanel.setDevice(device);
        }
        repaint();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        deviceEditorTabbedPane = new javax.swing.JTabbedPane();
        deviceTab = new javax.swing.JPanel();
        propertiesPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        userdescriptionTextField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        individualAddressInputPanel = new de.konnekting.suite.uicomponents.IndividualAddressInputPanel();
        deviceInformationPanel = new de.konnekting.suite.DeviceInformationPanel();
        commObjectTab = new javax.swing.JPanel();
        commObjectTable = new de.konnekting.suite.CommObjectTable();
        parameterTab = new javax.swing.JPanel();
        parameterPanel = new de.konnekting.suite.ParameterPanel();

        deviceTab.setMinimumSize(new java.awt.Dimension(400, 300));

        propertiesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Eigenschaften"));

        jLabel6.setText("Beschreibung");

        userdescriptionTextField.setEnabled(false);
        userdescriptionTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                userdescriptionTextFieldFocusLost(evt);
            }
        });
        userdescriptionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userdescriptionChanged(evt);
            }
        });

        jLabel7.setText("Physikalische Adresse");

        javax.swing.GroupLayout propertiesPanelLayout = new javax.swing.GroupLayout(propertiesPanel);
        propertiesPanel.setLayout(propertiesPanelLayout);
        propertiesPanelLayout.setHorizontalGroup(
            propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(userdescriptionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 521, Short.MAX_VALUE)
                    .addGroup(propertiesPanelLayout.createSequentialGroup()
                        .addComponent(individualAddressInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        propertiesPanelLayout.setVerticalGroup(
            propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(userdescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(propertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(propertiesPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(individualAddressInputPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(propertiesPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout deviceTabLayout = new javax.swing.GroupLayout(deviceTab);
        deviceTab.setLayout(deviceTabLayout);
        deviceTabLayout.setHorizontalGroup(
            deviceTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(propertiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(deviceInformationPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
        deviceTabLayout.setVerticalGroup(
            deviceTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deviceTabLayout.createSequentialGroup()
                .addComponent(deviceInformationPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(propertiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 81, Short.MAX_VALUE))
        );

        deviceEditorTabbedPane.addTab("Gerät", deviceTab);

        commObjectTab.setMinimumSize(new java.awt.Dimension(400, 300));
        java.awt.GridBagLayout commObjectTabLayout = new java.awt.GridBagLayout();
        commObjectTabLayout.columnWeights = new double[] {1.0};
        commObjectTabLayout.rowWeights = new double[] {1.0};
        commObjectTab.setLayout(commObjectTabLayout);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        commObjectTab.add(commObjectTable, gridBagConstraints);

        deviceEditorTabbedPane.addTab("Kommunikationsobjekte", commObjectTab);

        parameterTab.setMinimumSize(new java.awt.Dimension(400, 300));
        parameterTab.setLayout(new java.awt.BorderLayout());
        parameterTab.add(parameterPanel, java.awt.BorderLayout.CENTER);

        deviceEditorTabbedPane.addTab("Parameter", parameterTab);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deviceEditorTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deviceEditorTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void userdescriptionChanged(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userdescriptionChanged
        StickyDeviceSelected event = RootEventBus.getDefault().getStickyEvent(StickyDeviceSelected.class);
        DeviceConfigContainer device = event.getDeviceConfig();
        device.setDescription(userdescriptionTextField.getText());
        RootEventBus.getDefault().post(new EventDeviceListRefresh());
    }//GEN-LAST:event_userdescriptionChanged

    private void userdescriptionTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userdescriptionTextFieldFocusLost
        userdescriptionChanged(null);
    }//GEN-LAST:event_userdescriptionTextFieldFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel commObjectTab;
    private de.konnekting.suite.CommObjectTable commObjectTable;
    private javax.swing.JTabbedPane deviceEditorTabbedPane;
    private de.konnekting.suite.DeviceInformationPanel deviceInformationPanel;
    private javax.swing.JPanel deviceTab;
    private de.konnekting.suite.uicomponents.IndividualAddressInputPanel individualAddressInputPanel;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private de.konnekting.suite.ParameterPanel parameterPanel;
    private javax.swing.JPanel parameterTab;
    private javax.swing.JPanel propertiesPanel;
    private javax.swing.JTextField userdescriptionTextField;
    // End of variables declaration//GEN-END:variables
}
