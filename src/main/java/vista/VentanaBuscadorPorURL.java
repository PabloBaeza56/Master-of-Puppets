/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ControladorMaestro;
import controlador.BusquedaLinks;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class VentanaBuscadorPorURL extends javax.swing.JFrame {

    public VentanaBuscadorPorURL() {
        initComponents();
        setResizable(false);
        botonEjecutarBusqueda.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonRegresar = new javax.swing.JButton();
        campoEntradaBuscador = new javax.swing.JTextField();
        botonEjecutarBusqueda = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonRegresar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonRegresar.setText("Regresar");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        campoEntradaBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoEntradaBuscadorActionPerformed(evt);
            }
        });
        campoEntradaBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoEntradaBuscadorKeyReleased(evt);
            }
        });

        botonEjecutarBusqueda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonEjecutarBusqueda.setText("Ejecutar Busqueda");
        botonEjecutarBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEjecutarBusquedaMouseClicked(evt);
            }
        });
        botonEjecutarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEjecutarBusquedaActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 153));

        jLabel1.setFont(new java.awt.Font("Zilla Slab SemiBold", 2, 24)); // NOI18N
        jLabel1.setText("Busqueda por URL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(215, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ingrese la URL de busqueda deseada:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(botonEjecutarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(campoEntradaBuscador))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoEntradaBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonEjecutarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        PantallaPrincipal modal = new PantallaPrincipal();
        modal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void botonEjecutarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarBusquedaActionPerformed
        String CadenaBusqueda;
        CadenaBusqueda = campoEntradaBuscador.getText();
        System.out.println(CadenaBusqueda + "URL");
        
        WebDriver driver = new ChromeDriver();
        try {
            ControladorMaestro controler = new ControladorMaestro();
            controler.inyectarCookies( driver );
        } catch (IOException | ParseException ex) {
            Logger.getLogger(VentanaBuscadorCadena.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        BusquedaLinks buscador = new BusquedaLinks(driver);
        buscador.metodoURL(CadenaBusqueda);
        driver.quit();
        
        PantallaPrincipal modal = new PantallaPrincipal();
        modal.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_botonEjecutarBusquedaActionPerformed

    private void campoEntradaBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEntradaBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEntradaBuscadorActionPerformed

    private void botonEjecutarBusquedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEjecutarBusquedaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_botonEjecutarBusquedaMouseClicked

    private void campoEntradaBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEntradaBuscadorKeyReleased
        String text = campoEntradaBuscador.getText();
    // Verificar si el campo de texto está vacío
        if (text.isEmpty()) {
            botonEjecutarBusqueda.setEnabled(false);
        } else {
            botonEjecutarBusqueda.setEnabled(true);
        }
    }//GEN-LAST:event_campoEntradaBuscadorKeyReleased

   
    public static void main(String args[]) {
        try {
            // Establece el look and feel predeterminado del sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaBuscadorPorURL().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEjecutarBusqueda;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JTextField campoEntradaBuscador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
