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

import de.konnekting.deviceconfig.utils.ReflectionIdComparator;
import de.konnekting.deviceconfig.DeviceConfigContainer;
import de.konnekting.deviceconfig.exception.InvalidAddressFormatException;
import de.konnekting.xml.konnektingdevice.v0.CommObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author achristian
 */
public class CommObjectTableModel extends DefaultTableModel {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private DeviceConfigContainer device;
    private final List<CommObject> commObjects = new ArrayList<>();

    private final ResourceBundle rb = java.util.ResourceBundle.getBundle("de/konnekting/suite/i18n/language");
    private int rows = 0;

    public CommObjectTableModel() {
    }

    public void setDeviceData(DeviceConfigContainer device) {
        this.device = device;
        reloadDeviceData();
    }

    @Override
    public int getRowCount() {
        if (commObjects == null) {
            return 0;
        }

        return rows;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        // 0 ID
        // 1 Name
        // 2 Funktion
        // 3 DPT
        // 4 Beschreibung
        // 5 GA
        switch (columnIndex) {
            case 0:
                return rb.getString(getClass().getSimpleName() + ".tableheader.id"); // ID
            case 1:
                return rb.getString(getClass().getSimpleName() + ".tableheader.name"); // Name
            case 2:
                return rb.getString(getClass().getSimpleName() + ".tableheader.function"); // Funktion
            case 3:
                return rb.getString(getClass().getSimpleName() + ".tableheader.dpt"); // DPT
            case 4:
                return rb.getString(getClass().getSimpleName() + ".tableheader.description"); // Beschreibung
            case 5:
                return rb.getString(getClass().getSimpleName() + ".tableheader.groupaddress"); // Gruppenadresse
            default:
                throw new IllegalArgumentException("Column " + columnIndex + " not known");
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // 0 ID
        // 1 Name
        // 2 Funktion
        // 3 DPT
        // 4 Beschreibung
        // 5 GA            
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                return String.class;
            default:
                throw new IllegalArgumentException("Column " + columnIndex + " not known");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // 0 ID
        // 1 Name
        // 2 Funktion
        // 3 DPT
        // 4 Beschreibung
        // 5 GA
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
                return false;
            case 4:
            case 5:
                return true;
            default:
                throw new IllegalArgumentException("Column " + columnIndex + " not known");

        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // 0 ID
        // 1 Name
        // 2 Funktion
        // 3 DPT
        // 4 Beschreibung
        // 5 GA
        switch (columnIndex) {
            case 0:
                return commObjects.get(rowIndex).getId();
            case 1:
                return commObjects.get(rowIndex).getName();
            case 2:
                return commObjects.get(rowIndex).getFunction();
            case 3:
                return commObjects.get(rowIndex).getDataPointType();
            case 4:
                return device.getCommObjectDescription(commObjects.get(rowIndex).getId());
            case 5:
                return device.getCommObjectGroupAddress(commObjects.get(rowIndex).getId());
            default:
                throw new IllegalArgumentException("Column " + columnIndex + " not known");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // 0 ID
        // 1 Name
        // 2 Funktion
        // 3 DPT
        // 4 Beschreibung
        // 5 GA

        short id = commObjects.get(rowIndex).getId();
        String value = (String) aValue;

        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
                throw new IllegalArgumentException("Column " + columnIndex + " not editable");
            case 4:
                device.setCommObjectDescription(id, value);
                fireTableCellUpdated(rowIndex, columnIndex);
                break;
            case 5:
                try {
                    device.setCommObjectGroupAddress(id, value);
                    fireTableCellUpdated(rowIndex, columnIndex);
                } catch (InvalidAddressFormatException ex) {
                    ex.printStackTrace();
                }
                break;
            default:
                throw new IllegalArgumentException("Column " + columnIndex + " not known");
        }
    }

    void reloadDeviceData() {
        commObjects.clear();
        if (device == null) {
            commObjects.clear();
            rows = 0;
        } else {
            commObjects.addAll(device.getAllCommObjects());

        }

        refreshCommObjVisibility();

    }

    public void refreshCommObjVisibility() {

        // sort by id
        commObjects.sort(new ReflectionIdComparator());
        log.info("Sort by ID");

        final Set<CommObject> enabled = new HashSet<>();
        final Set<CommObject> disabled = new HashSet<>();

        // sort by visibility
        commObjects.sort(new Comparator<CommObject>() {

            @Override
            public int compare(CommObject o1, CommObject o2) {
                boolean o1Enabled = device.isCommObjectEnabled(o1);
                boolean o2Enabled = device.isCommObjectEnabled(o2);
                if (o1Enabled && !o2Enabled) {
                    enabled.add(o1);
                    disabled.add(o2);
                    return -1;
                } else if (o2Enabled && !o1Enabled) {
                    disabled.add(o1);
                    enabled.add(o2);
                    return 1;
                } else if (o1Enabled && o2Enabled) {
                    enabled.add(o1);
                    enabled.add(o2);
                    return 0;
                } else if (!o1Enabled && !o2Enabled) {
                    disabled.add(o1);
                    disabled.add(o2);
                    return 0;
                } else {
                    return 0;
                }
            }

        });
        log.info("Sort by enabled/disabled: {} vs. {}", enabled.size(), disabled.size());
        rows = enabled.size();
        fireTableDataChanged();
    }

}
