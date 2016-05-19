/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.konnekting.suite;

import java.awt.Rectangle;

/**
 *
 * @author achristian
 */
public class SplashPanel extends javax.swing.JFrame {

    /**
     * Creates new form SplashPanel
     */
    public SplashPanel() {
        initComponents();
        setLocationRelativeTo(null);
        setSize(400, 300);
        setVisible(true);
    }
    
    public void setProgress(int progress) {
        progressBar.setValue(progress);
        if (progress==100) {
            setVisible(false);
            dispose();
        }
    }
    
    public void setVersionText(String text) {
        statusLabel.setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        statusLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        iconLabel = new javax.swing.JLabel();

        setUndecorated(true);
        getContentPane().setLayout(null);

        statusLabel.setFont(new java.awt.Font("Monospaced", 0, 9)); // NOI18N
        statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("de/konnekting/suite/i18n/language"); // NOI18N
        statusLabel.setText(bundle.getString("SplashPanel.statusLabel.text")); // NOI18N
        statusLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        statusLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        statusLabel.setMaximumSize(new java.awt.Dimension(30, 19));
        statusLabel.setMinimumSize(new java.awt.Dimension(30, 19));
        statusLabel.setPreferredSize(new java.awt.Dimension(30, 19));
        statusLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        getContentPane().add(statusLabel);
        statusLabel.setBounds(10, 270, 380, 20);
        getContentPane().add(progressBar);
        progressBar.setBounds(10, 250, 380, 20);

        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/splash/splash.png"))); // NOI18N
        getContentPane().add(iconLabel);
        iconLabel.setBounds(0, 0, 400, 300);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconLabel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusLabel;
    // End of variables declaration//GEN-END:variables
}
