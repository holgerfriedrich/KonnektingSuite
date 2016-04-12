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

/**
 *
 * @author achristian
 */
public class DeviceInformationPanel extends javax.swing.JPanel {

    private DeviceConfigContainer device;

    /**
     * Creates new form DeviceInformationPanel
     */
    public DeviceInformationPanel() {
        initComponents();
    }

    public void setDeviceConfig(DeviceConfigContainer device) {
        this.device = device;

        if (device == null) {
            manufacturerIdTextField.setText("");
            deviceIdTextField.setText("");
            revisionTextField.setText("");

            manufacturernameTextField.setText("");
            devicenameTextField.setText("");
        } else {
            manufacturerIdTextField.setText(String.format("0x%04x", device.getManufacturerId()));
            deviceIdTextField.setText(String.format("0x%02x", device.getDeviceId()));
            revisionTextField.setText(String.format("0x%02x", device.getRevision()));

            manufacturernameTextField.setText(device.getManufacturerName());
            devicenameTextField.setText(device.getDeviceName());
        }
        repaint();
    }

    public DeviceConfigContainer getDeviceConfig() {
        return device;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deviceInfoPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        manufacturerIdTextField = new javax.swing.JTextField();
        deviceIdTextField = new javax.swing.JTextField();
        revisionTextField = new javax.swing.JTextField();
        manufacturernameTextField = new javax.swing.JTextField();
        devicenameTextField = new javax.swing.JTextField();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/konnekting/suite/Bundle"); // NOI18N
        deviceInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(bundle.getString("DeviceInformationPanel.deviceInfoPanel.border.title"))); // NOI18N

        jLabel1.setText(bundle.getString("DeviceInformationPanel.jLabel1.text")); // NOI18N

        jLabel2.setText(bundle.getString("DeviceInformationPanel.jLabel2.text")); // NOI18N

        jLabel3.setText(bundle.getString("DeviceInformationPanel.jLabel3.text")); // NOI18N

        jLabel4.setText(bundle.getString("DeviceInformationPanel.jLabel4.text")); // NOI18N

        jLabel5.setText(bundle.getString("DeviceInformationPanel.jLabel5.text")); // NOI18N

        manufacturerIdTextField.setEditable(false);

        deviceIdTextField.setEditable(false);
        deviceIdTextField.setMinimumSize(new java.awt.Dimension(30, 27));

        revisionTextField.setEditable(false);
        revisionTextField.setMinimumSize(new java.awt.Dimension(45, 27));

        manufacturernameTextField.setEditable(false);

        devicenameTextField.setEditable(false);

        javax.swing.GroupLayout deviceInfoPanelLayout = new javax.swing.GroupLayout(deviceInfoPanel);
        deviceInfoPanel.setLayout(deviceInfoPanelLayout);
        deviceInfoPanelLayout.setHorizontalGroup(
            deviceInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deviceInfoPanelLayout.createSequentialGroup()
                .addGroup(deviceInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(deviceInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deviceInfoPanelLayout.createSequentialGroup()
                        .addComponent(manufacturerIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deviceIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(revisionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE))
                    .addComponent(devicenameTextField)
                    .addComponent(manufacturernameTextField)))
        );
        deviceInfoPanelLayout.setVerticalGroup(
            deviceInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deviceInfoPanelLayout.createSequentialGroup()
                .addGroup(deviceInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(manufacturerIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(deviceIdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(revisionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(deviceInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(manufacturernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(deviceInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(devicenameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deviceInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deviceInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField deviceIdTextField;
    private javax.swing.JPanel deviceInfoPanel;
    private javax.swing.JTextField devicenameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField manufacturerIdTextField;
    private javax.swing.JTextField manufacturernameTextField;
    private javax.swing.JTextField revisionTextField;
    // End of variables declaration//GEN-END:variables
}
