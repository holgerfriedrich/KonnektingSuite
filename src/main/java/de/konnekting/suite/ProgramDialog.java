/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.konnekting.suite;

import de.konnekting.deviceconfig.DeviceConfigContainer;
import de.konnekting.deviceconfig.Program;
import de.konnekting.deviceconfig.ProgramException;
import de.konnekting.deviceconfig.ProgramProgressListener;
import de.konnekting.suite.events.EventConsoleMessage;
import de.root1.rooteventbus.RootEventBus;
import de.root1.slicknx.Knx;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author achristian
 */
public class ProgramDialog extends javax.swing.JDialog {
    
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/konnekting/suite/i18n/language"); // NOI18N
    private final List<DeviceConfigContainer> deviceList = new ArrayList<>();
    private Program p;
    private boolean doIndividualAddress;
    private boolean doComObjects;
    private boolean doParams;

    /**
     * Creates new form ProgramDialog
     *
     * @param parent
     */
    public ProgramDialog(JFrame parent) {
        super(parent, false);
        initComponents();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                cancelButton.doClick();
            }
            
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progressBar = new javax.swing.JProgressBar();
        statusMessageLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        programmingLabel = new javax.swing.JLabel();
        deviceNameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        statusMessageLabel.setText("..."); // NOI18N

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/konnekting/suite/i18n/language"); // NOI18N
        cancelButton.setText(bundle.getString("ProgramDialog.cancelButton.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        programmingLabel.setText(bundle.getString("ProgramDialog.programmingLabel.text")); // NOI18N

        deviceNameLabel.setText("..."); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(programmingLabel)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(deviceNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(statusMessageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cancelButton)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(programmingLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deviceNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(cancelButton))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String getLangString(String key, Object... values) {
        String completeKey = getClass().getSimpleName()+"."+key;
        try {
            String s = bundle.getString(completeKey);
            return String.format(s, values);
        } catch (Exception ex) {
            log.error("Problem reading/using key '"+completeKey+"'", ex);
            return "<"+completeKey+">";
        }
    }
    
    private String getLangString(String key) {
        return bundle.getString(getClass().getSimpleName()+"."+key);
    }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        p.abort();
        RootEventBus.getDefault().post(new EventConsoleMessage(getLangString("ConsoleMsg.prefix")+" " + getLangString("ConsoleMsg.cancelRequested")));
        cancelButton.setText(cancelButton.getText() + "...");
        cancelButton.setEnabled(false);
    }//GEN-LAST:event_cancelButtonActionPerformed
    
    public void prepare(Knx knx, boolean doIndividualAddress, boolean doComObjects, boolean doParams) {
        this.doIndividualAddress = doIndividualAddress;
        this.doComObjects = doComObjects;
        this.doParams = doParams;
        p = new Program(knx);
    }
    
    void addDeviceToprogram(DeviceConfigContainer device) {
        deviceList.add(device);
    }
    
    @Override
    public void setVisible(boolean b) {
        if (b) {
            super.setVisible(b); //To change body of generated methods, choose Tools | Templates.

            progressBar.setDoubleBuffered(true);
            
            ProgramProgressListener ppl = new ProgramProgressListener() {
                @Override
                public void onStatusMessage(String statusMsg) {
                    statusMessageLabel.setText(statusMsg);
                    RootEventBus.getDefault().post(new EventConsoleMessage(getLangString("ConsoleMsg.prefix")+" " + statusMsg));
                }
                
                @Override
                public void onProgressUpdate(int currentStep, int steps) {
                    progressBar.setMaximum(steps);
                    progressBar.setValue(currentStep);
                    RootEventBus.getDefault().post(new EventConsoleMessage(getLangString("ConsoleMsg.prefix")+" "+getLangString("ConsoleMsg.progress")+" " + currentStep + "/" + steps));
                }
            };
            new BackgroundTask(getLangString("ConsoleMsg.prefix")) {
                @Override
                public void run() {
                    long start = System.currentTimeMillis();
                    try {
                        p.addProgressListener(ppl);
                        
                        String name = deviceList.get(0).getIndividualAddress() + " " + deviceList.get(0).getDescription();
                        RootEventBus.getDefault().post(new EventConsoleMessage(getLangString("ConsoleMsg.prefix")+" " + name));
                        deviceNameLabel.setText(name);
                        start = System.currentTimeMillis();
                        p.program(deviceList.get(0), doIndividualAddress, doComObjects, doParams);
                    } catch (ProgramException ex) {
                        RootEventBus.getDefault().post(new EventConsoleMessage(getLangString("ConsoleMsg.prefix")+" "+getLangString("ConsoleMsg.errorOccured"), ex));
                    } finally {
                        long stop = System.currentTimeMillis();
                        p.removeProgressListener(ppl);
                        dispose();
                        RootEventBus.getDefault().post(new EventConsoleMessage(getLangString("ConsoleMsg.prefix")+" "+getLangString("ConsoleMsg.doneResult", (stop - start)))); // "Fertig! Dauer: " + (stop - start) + "ms")
                    }
                }
            };
            
        }
        
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel deviceNameLabel;
    private javax.swing.JLabel programmingLabel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusMessageLabel;
    // End of variables declaration//GEN-END:variables
}
