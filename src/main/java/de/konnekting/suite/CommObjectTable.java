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

import de.konnekting.deviceconfig.utils.Helper;
import de.konnekting.suite.events.EventParameterChanged;
import de.konnekting.suite.events.StickyDeviceSelected;
import de.konnekting.suite.uicomponents.GroupAddressTextField;
import de.root1.rooteventbus.RootEventBus;
import javax.swing.DefaultCellEditor;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author achristian
 */
public class CommObjectTable extends javax.swing.JPanel {
    
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final DefaultCellEditor gaEditor;

    /**
     * Creates new form CommObjectTable
     */
    public CommObjectTable() {
        RootEventBus.getDefault().registerSticky(this);
        InputVerifier gaVerifier = getGaInputVerifier();
        gaEditor = getGaTableCellEditor(gaVerifier);

        initComponents();
        table.setRowHeight(27); // enough space for textfields
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(150);
        table.getColumnModel().getColumn(5).setPreferredWidth(150);
        
        table.putClientProperty("terminateEditOnFocusLost", true);
        
        
    }
    
    public void onEvent(StickyDeviceSelected ev) {
        
        // save all current data, end input
        if (table.isEditing()) {
            table.getCellEditor().stopCellEditing();
            log.debug("Stop editing before applying new device to table");
        }
        
        // set new device data
        dataModel.setDeviceData(ev.getDeviceConfig());
    }
    
    // Update comobj table when parameter has changed (check dependencies...)
    public void onEvent(EventParameterChanged ev) {
        dataModel.refreshCommObjVisibility();
    }

    private InputVerifier getGaInputVerifier() {
        InputVerifier verifier = new InputVerifier() {

            @Override
            public boolean verify(JComponent input) {
                JTextField field = (JTextField) input;
                String text = field.getText();
                return Helper.checkValidGa(text);
            }

            @Override
            public boolean shouldYieldFocus(JComponent input) {
                boolean valid = verify(input);
                if (!valid) {
                    JOptionPane.showMessageDialog(input.getParent(), "Invalid group address");
                }
                return valid;
            }
        };
        return verifier;
    }

    private DefaultCellEditor getGaTableCellEditor(final InputVerifier verifier) {
        DefaultCellEditor editor = new DefaultCellEditor(new GroupAddressTextField()) {
            {
                getComponent().setInputVerifier(verifier);
            }

            @Override
            public boolean stopCellEditing() {
                if (!verifier.shouldYieldFocus(getComponent())) {
                    return false;
                }
                System.out.println("Stopped editing: "+getComponent().getText());
                return super.stopCellEditing();
            }

            @Override
            public JTextField getComponent() {
                return (JTextField) super.getComponent();
            }

        };
        return editor;
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

        dataModel = new de.konnekting.suite.CommObjectTableModel();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable(){
            public TableCellEditor getCellEditor(int row, int column) {
                int modelColumn = convertColumnIndexToModel(column);
                switch(modelColumn) {
                    case 5:
                    return gaEditor;
                    default:
                    return super.getCellEditor(row, column);
                }
            }
        }
        ;

        setRequestFocusEnabled(false);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWeights = new double[] {1.0};
        layout.rowWeights = new double[] {1.0};
        setLayout(layout);

        table.setModel(dataModel);
        table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        scrollPane.setViewportView(table);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 524;
        gridBagConstraints.ipady = 147;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(scrollPane, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.konnekting.suite.CommObjectTableModel dataModel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
