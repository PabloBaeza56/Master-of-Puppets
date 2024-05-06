package vista;

import controlador.ControladoresConcretos;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CambiarUsuario extends javax.swing.JFrame {
    private ControladoresConcretos controlador;

    public CambiarUsuario(ControladoresConcretos controlador) {
        initComponents();
        setResizable(false);
        ejecutarCambioBoton.setEnabled(false);
        this.controlador = controlador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuarioTextBox = new javax.swing.JTextField();
        contraseniaTextBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ejecutarCambioBoton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        botonRegresar1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        usuarioTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usuarioTextBoxKeyReleased(evt);
            }
        });

        contraseniaTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                contraseniaTextBoxKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Ingrese su usuario:");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Ingrese su contraseña: ");

        ejecutarCambioBoton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        ejecutarCambioBoton.setText("Ejecutar Cambio");
        ejecutarCambioBoton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarCambioBotonActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Cambiar usuario de LinkedIn");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        botonRegresar1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonRegresar1.setText("Regresar");
        botonRegresar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresar1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Verifique previamente que los datos introducidos sean correctos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ejecutarCambioBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addComponent(botonRegresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(contraseniaTextBox)
                        .addGap(69, 69, 69))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(usuarioTextBox)
                        .addGap(67, 67, 67))))
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel4)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usuarioTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(contraseniaTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ejecutarCambioBoton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonRegresar1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresar1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonRegresar1ActionPerformed

    private void ejecutarCambioBotonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarCambioBotonActionPerformed
        String UsuarioTextBox = usuarioTextBox.getText().trim();
        String ContraseniaTextBox = contraseniaTextBox.getText().trim();
        
        try {
            controlador.cambiarUsuarioLinkedin(UsuarioTextBox, ContraseniaTextBox, this);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(CambiarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ejecutarCambioBotonActionPerformed

    private void usuarioTextBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioTextBoxKeyReleased
        this.verificarCamposTexto();
    }//GEN-LAST:event_usuarioTextBoxKeyReleased

    private void contraseniaTextBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_contraseniaTextBoxKeyReleased
        this.verificarCamposTexto();
    }//GEN-LAST:event_contraseniaTextBoxKeyReleased


    private void verificarCamposTexto() {
        String ContraseniaTextBox = contraseniaTextBox.getText().trim();
        String UsuarioTextBox = usuarioTextBox.getText().trim();

        if (!UsuarioTextBox.isEmpty() && !ContraseniaTextBox.isEmpty()) {
            ejecutarCambioBoton.setEnabled(true);
        } else {
            ejecutarCambioBoton.setEnabled(false);
        }
    }
    
    public void cambiarPanel() throws IOException, ParseException{
        //PantallaPrincipal modal = new PantallaPrincipal();
        //modal.setVisible(true);
        this.dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegresar1;
    private javax.swing.JTextField contraseniaTextBox;
    private javax.swing.JButton ejecutarCambioBoton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField usuarioTextBox;
    // End of variables declaration//GEN-END:variables
}
