
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.centroauto.reservas.vista;

import com.centroauto.reservas.dao.CiudadesDao;
import com.centroauto.reservas.dao.DepartamentosDao;
import com.centroauto.reservas.dao.SucursalesDao;
import com.centroauto.reservas.dto.SucursalesDTO;
import com.centroauto.reservas.entidades.Ciudades;
import com.centroauto.reservas.entidades.Departamentos;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author danny
 */
public class WinSucursalesForm extends javax.swing.JDialog {

    /**
     * Variable que indica el modo de la pantalla (T: Creación, F: Edición)
     */
    private boolean modoNuevo;
    private SucursalesDTO sucursalExistente;
    private WinSucursalesPpal sucursalPpal;
    private SucursalesDao sucursalDao;
    private DepartamentosDao deptoDao;
    private CiudadesDao ciudadDao;

    public WinSucursalesForm(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public WinSucursalesForm(boolean modoNuevo, SucursalesDTO sucursalExistente,
            WinSucursalesPpal sucursalPpal) {
        this.modoNuevo = modoNuevo;
        this.sucursalExistente = sucursalExistente;
        this.sucursalPpal = sucursalPpal;
        initComponents();
        this.setLocationRelativeTo(null);
        deptoDao = new DepartamentosDao();
        ciudadDao = new CiudadesDao();
        sucursalDao = new SucursalesDao();
        llenarDepartamentos();
        //Modo Edición
        if (!this.modoNuevo) {
            llenarFormulario();
        }
        //TODO: Bloquear el depto y la ciudad en modo edición        
        this.cmbDepto.setEditable(this.modoNuevo);
        this.cmbCiudades.setEditable(this.modoNuevo);
    }

    public void llenarFormulario() {
        this.txtNombre.setText(this.sucursalExistente.getNomSucursal());
        this.txtDireccion.setText(this.sucursalExistente.getDirSucursal());
        this.txtTelefono.setText(this.sucursalExistente.getTelSucursal().toString());
    }

    public void llenarDepartamentos() {
        try {
            List<Departamentos> lstDeptos = deptoDao.consultarDepartamentos();
            this.cmbDepto.addItem("0 - Seleccione");
            for (Departamentos d : lstDeptos) {
                this.cmbDepto.addItem(d.getIdDepto() + " - " + d.getNomDepto());
            }
            //Modo Edición: seleccionar Depto de la sucursal existente
            if (!this.modoNuevo) {
                this.cmbDepto.setSelectedItem(
                        this.sucursalExistente.getIdDepto() + " - "
                        + this.sucursalExistente.getNomDepto());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error Consulta Deptos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void llenarCiudades() {
        ciudadDao = new CiudadesDao();
        Integer idDepto = Integer.parseInt(this.cmbDepto.getSelectedItem().toString()
                .split("-")[0].trim());
        try {
            this.cmbCiudades.removeAllItems();
            this.cmbCiudades.addItem("0 - Seleccione");
            if (idDepto > 0) {
                List<Ciudades> lstCiudades
                        = ciudadDao.consultarCiudadesPorDepto(idDepto);
                for (Ciudades c : lstCiudades) {
                    this.cmbCiudades.addItem(c.getIdCiudad() + " - " + c.getNomCiudad());
                }
                //Modo Edición: seleccionar ciudad de la sucursal existente 
                if (!this.modoNuevo) {
                    this.cmbCiudades.setSelectedItem(
                            this.sucursalExistente.getIdCiudad() + " - "
                            + this.sucursalExistente.getNomCiudad()
                    );
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(),
                    "Error Consulta Ciudades", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblDireccion = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblDepto = new javax.swing.JLabel();
        cmbDepto = new javax.swing.JComboBox<>();
        lblDepto1 = new javax.swing.JLabel();
        cmbCiudades = new javax.swing.JComboBox<>();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitulo.setText("CREAR / EDITAR SUCURSAL");

        lblNombre.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblNombre.setText("NOMBRE:");

        lblDireccion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDireccion.setText("DIRECCION:");

        lblTelefono.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblTelefono.setText("TELEFONO:");

        lblDepto.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDepto.setText("DEPARTAMENTO:");

        cmbDepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDeptoActionPerformed(evt);
            }
        });

        lblDepto1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDepto1.setText("CIUDAD:");

        btnGuardar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCancelar.setText("CANCELAR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNombre)
                            .addComponent(lblTelefono)
                            .addComponent(lblDepto1))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmbCiudades, 0, 227, Short.MAX_VALUE)
                            .addComponent(txtTelefono)
                            .addComponent(txtNombre))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDepto)
                            .addComponent(lblDireccion))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDireccion)
                            .addComponent(cmbDepto, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(btnCancelar)))
                        .addGap(0, 14, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTitulo)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDepto, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cmbDepto))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDepto1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbCiudades))
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbDeptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDeptoActionPerformed
        this.llenarCiudades();
    }//GEN-LAST:event_cmbDeptoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (this.txtNombre.getText() == null || this.txtDireccion.getText() == null
                || Integer.parseInt(this.cmbCiudades.getSelectedItem().toString()
                        .split("-")[0].trim()) == 0) {
            JOptionPane.showMessageDialog(this, "Faltan campos por diligenciar",
                    "Error Creación Sucursal", JOptionPane.ERROR_MESSAGE);
            return;
        }
        //  Validar modo nuevo o edicion
        SucursalesDTO s;
        if (this.modoNuevo) {
            s = new SucursalesDTO();
        } else {
            s = this.sucursalExistente;
        }

        s.setNomSucursal(this.txtNombre.getText());
        s.setDirSucursal(this.txtDireccion.getText());
        s.setTelSucursal(Long.parseLong(this.txtTelefono.getText()));
        s.setIdCiudad(Integer.parseInt(this.cmbCiudades.getSelectedItem().toString().split(" - ")[0].trim()));
        try {
            if (this.modoNuevo) {
                sucursalDao.insertarSucursal(s);
            } else {
                sucursalDao.actualizarSucursales(s);
            }
            sucursalDao.insertarSucursal(s);
            this.sucursalPpal.consultarDatos(null, null);
            this.setVisible(false);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Creacion Sucursal: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WinSucursalesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WinSucursalesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WinSucursalesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WinSucursalesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                WinSucursalesForm dialog = new WinSucursalesForm(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cmbCiudades;
    private javax.swing.JComboBox<String> cmbDepto;
    private javax.swing.JLabel lblDepto;
    private javax.swing.JLabel lblDepto1;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
