package vista;

import controlador.ControladoresConcretos;
import java.util.HashMap;
import java.util.Map;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class QueryPromdioDuracion extends javax.swing.JFrame {

    private ControladoresConcretos controlador;
    public QueryPromdioDuracion(ControladoresConcretos controlador) {
        initComponents();
        setResizable(false);
        botonEjecutarBusqueda.setEnabled(false); 
        this.controlador = controlador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        campoEntradaBuscador = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        botonRegresar = new javax.swing.JButton();
        botonEjecutarBusqueda = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        campoUniversidadEspaniol = new javax.swing.JTextField();
        campoUniversidadIngles = new javax.swing.JTextField();
        campoUniversidadAcronimo = new javax.swing.JTextField();
        nombreCarreraEspaniol = new javax.swing.JTextField();
        nombreCarreraIngles = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Ingrese la cadena de busqueda deseada:");

        jLabel9.setText("Nombre de la carrera (En español):");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonRegresar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonRegresar.setText("Regresar");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });

        botonEjecutarBusqueda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        botonEjecutarBusqueda.setText("Ejecutar Busqueda");
        botonEjecutarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEjecutarBusquedaActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 255, 255));

        jLabel1.setFont(new java.awt.Font("Zilla Slab SemiBold", 2, 24)); // NOI18N
        jLabel1.setText("Obtener promedio de duracion de empleo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        campoUniversidadEspaniol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoUniversidadEspaniolKeyReleased(evt);
            }
        });

        campoUniversidadIngles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoUniversidadInglesKeyReleased(evt);
            }
        });

        campoUniversidadAcronimo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoUniversidadAcronimoKeyReleased(evt);
            }
        });

        nombreCarreraEspaniol.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreCarreraEspaniolKeyReleased(evt);
            }
        });

        nombreCarreraIngles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nombreCarreraInglesKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Deseo obtener el promedio general de duración (en meses), del apartado de experiencia laboral,");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("de ciertas personas con las siguientes caracteristicas relacionadas a su educación:");

        jLabel5.setText("Nombre de la Universidad (En español):");

        jLabel6.setText("Nombre de la Universidad (En Ingles):");

        jLabel7.setText("Acronimo de la Universidad:");

        jLabel8.setText("Nombre de la carrera (En español):");

        jLabel10.setText("Nombre de la carrera (En ingles):");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Recuerde el uso de acentos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonEjecutarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(187, 187, 187)
                .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                                .addComponent(nombreCarreraIngles, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombreCarreraEspaniol, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(campoUniversidadAcronimo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(campoUniversidadIngles)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(53, 53, 53)
                        .addComponent(campoUniversidadEspaniol)))
                .addGap(99, 99, 99))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel11)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(campoUniversidadEspaniol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoUniversidadIngles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoUniversidadAcronimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(nombreCarreraEspaniol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nombreCarreraIngles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonRegresar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonEjecutarBusqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void botonEjecutarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarBusquedaActionPerformed
  
         Map<String, String> datos = new HashMap<String, String>() {{
            put("acronimoUniversidad", campoUniversidadAcronimo.getText().trim());
            put("nombreUniversidadEspaniol", campoUniversidadEspaniol.getText().trim());
            put("nombreUniversidadIngles", campoUniversidadIngles.getText().trim());
            
            put("nombreCarreraEspaniol", nombreCarreraEspaniol.getText().trim());
            put("nombreCarreraIngles", nombreCarreraIngles.getText().trim());
        }};
         
        controlador.QueryPromdioDuracion(datos, this);      
    }//GEN-LAST:event_botonEjecutarBusquedaActionPerformed

    private void campoEntradaBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoEntradaBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoEntradaBuscadorActionPerformed

    private void campoEntradaBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoEntradaBuscadorKeyReleased
        
    }//GEN-LAST:event_campoEntradaBuscadorKeyReleased

    private void campoUniversidadEspaniolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoUniversidadEspaniolKeyReleased
        this.verificarCamposTexto();
    }//GEN-LAST:event_campoUniversidadEspaniolKeyReleased

    private void campoUniversidadInglesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoUniversidadInglesKeyReleased
        this.verificarCamposTexto();
    }//GEN-LAST:event_campoUniversidadInglesKeyReleased

    private void campoUniversidadAcronimoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoUniversidadAcronimoKeyReleased
       this.verificarCamposTexto();
    }//GEN-LAST:event_campoUniversidadAcronimoKeyReleased

    private void nombreCarreraEspaniolKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreCarreraEspaniolKeyReleased
        this.verificarCamposTexto();
    }//GEN-LAST:event_nombreCarreraEspaniolKeyReleased

    private void nombreCarreraInglesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nombreCarreraInglesKeyReleased
        this.verificarCamposTexto();
    }//GEN-LAST:event_nombreCarreraInglesKeyReleased

    
    private void verificarCamposTexto() {
        String CampoUniversidadAcronimo = campoUniversidadAcronimo.getText().trim();
        String CampoUniversidadEspaniol = campoUniversidadEspaniol.getText().trim();
        String CampoUniversidadIngles = campoUniversidadIngles.getText().trim();
        String NombreCarreraEspaniol = nombreCarreraEspaniol.getText().trim();
        String NombreCarreraIngles = nombreCarreraIngles.getText().trim();

        if (!CampoUniversidadAcronimo.isEmpty() && !CampoUniversidadEspaniol.isEmpty() &&
            !CampoUniversidadIngles.isEmpty() && !NombreCarreraEspaniol.isEmpty() &&   
            !NombreCarreraIngles.isEmpty()) 
        {
            botonEjecutarBusqueda.setEnabled(true);
        } else {
            botonEjecutarBusqueda.setEnabled(false);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEjecutarBusqueda;
    private javax.swing.JButton botonRegresar;
    private javax.swing.JTextField campoEntradaBuscador;
    private javax.swing.JTextField campoUniversidadAcronimo;
    private javax.swing.JTextField campoUniversidadEspaniol;
    private javax.swing.JTextField campoUniversidadIngles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nombreCarreraEspaniol;
    private javax.swing.JTextField nombreCarreraIngles;
    // End of variables declaration//GEN-END:variables
}
