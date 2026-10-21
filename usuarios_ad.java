/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import Juego.Persona;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class usuarios_ad extends JFrame {
    JMenuBar mb;
    JMenu archivo, bd;
    JMenuItem bd_C, bd_R, bd_U, bd_D; 
    
    //Atributos 
    //modificador de acceso 
    //dato atributo
    public JLabel lb1, lb_id, lb_cred, lb_perm, lb_est, lb_desc;
    public JTextField txt_id, credenciales, permisos, estado, descripcion;
    public JButton btn_consulta, btn_alta, btn_actualizar, btn_eliminar;
    Persona personal;

    public usuarios_ad() {
        this.setBounds(150, 200, 800, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.WHITE);
        this.setResizable(true);
        this.setLayout(null);
        
        // Barra
        this.mb = new JMenuBar();
        this.setJMenuBar(mb);
        
        this.archivo = new JMenu("Archivo");
        this.mb.add(this.archivo);
                
        this.bd = new JMenu("Base de datos");
        this.mb.add(this.bd);
        
        // CRUD
        //instanciacion
        this.bd_C = new JMenuItem("Create");
        this.bd_C.addActionListener((e) -> insertar());
        this.bd.add(this.bd_C);

        this.bd_R = new JMenuItem("Consult");
        this.bd_R.addActionListener((e) -> consultar());
        this.bd.add(this.bd_R);

        this.bd_U = new JMenuItem("Update");
        this.bd_U.addActionListener((e) -> actualizar());
        this.bd.add(this.bd_U);

        this.bd_D = new JMenuItem("Delete");
        this.bd_D.addActionListener((e) -> eliminar());
        this.bd.add(this.bd_D);

        // --- COMPONENTES GRÁFICOS ---
        this.lb1 = new JLabel("Welcome to Java");
        this.lb1.setBounds(340, 40, 200, 30);

        // Campo 1: ID
        this.lb_id = new JLabel("ID");
        this.lb_id.setBounds(100, 75, 100, 30);

        this.txt_id = new JTextField("1");
        this.txt_id.setBounds(210, 75, 250, 30);
        
        // Campo 2: Credenciales
        this.lb_cred = new JLabel("Credenciales");
        this.lb_cred.setBounds(100, 120, 100, 30);

        this.credenciales = new JTextField("admin");
        this.credenciales.setBounds(210, 120, 250, 30);

        // Campo 3: Permisos
        this.lb_perm = new JLabel("Permisos");
        this.lb_perm.setBounds(100, 165, 100, 30);

        this.permisos = new JTextField("Total");
        this.permisos.setBounds(210, 165, 250, 30);

        // Campo 4: Estado
        this.lb_est = new JLabel("Estado");
        this.lb_est.setBounds(100, 210, 100, 30);

        this.estado = new JTextField("Activo");
        this.estado.setBounds(210, 210, 250, 30);

        // Campo 5: Descripción
        this.lb_desc = new JLabel("Descripción");
        this.lb_desc.setBounds(100, 255, 100, 30);

        this.descripcion = new JTextField("Administrador principal");
        this.descripcion.setBounds(210, 255, 250, 30);

        // --- BOTONES ---
        this.btn_consulta = new JButton("Consultar");
        this.btn_consulta.setBounds(100, 310, 130, 40);
        this.btn_consulta.addActionListener((e) -> consultar());

        this.btn_alta = new JButton("Alta BD");
        this.btn_alta.setBounds(240, 310, 130, 40);
        this.btn_alta.addActionListener((e) -> insertar());

        this.btn_actualizar = new JButton("Actualizar");
        this.btn_actualizar.setBounds(380, 310, 130, 40);
        this.btn_actualizar.addActionListener((e) -> actualizar());

        this.btn_eliminar = new JButton("Eliminar");
        this.btn_eliminar.setBounds(520, 310, 130, 40);
        this.btn_eliminar.addActionListener((e) -> eliminar());

        // AGREGAR COMPONENTES
        this.add(lb_id);
        this.add(txt_id);
        this.add(lb_cred);
        this.add(credenciales);
        this.add(lb_perm);
        this.add(permisos);
        this.add(lb_est);
        this.add(estado);
        this.add(lb_desc);
        this.add(descripcion);
        this.add(lb1);
        
        this.add(btn_consulta);
        this.add(btn_alta);
        this.add(btn_actualizar);
        this.add(btn_eliminar);

        this.setVisible(true);
    }

    // tipo dato de retorno(void)
    public void consultar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sistema_administradores", "root", "");
            PreparedStatement ps = cnx.prepareStatement("select * from administradores");
            ResultSet rs = ps.executeQuery();
            
            System.out.println("--- CONSULTA BD ---");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") 
                        + " | Credenciales: " + rs.getString("credenciales") 
                        + " | Permisos: " + rs.getString("permisos")
                        + " | Estado: " + rs.getString("estado")
                        + " | Descripción: " + rs.getString("descripcion"));
            }
            cnx.close();

        } catch (SQLException ex) {
            System.out.println("Error conexion: " + ex.getMessage());
        }
    }

    public void insertar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sistema_administradores", "root", "");
            PreparedStatement ps = cnx.prepareStatement("insert into administradores (credenciales, permisos, estado, descripcion) values (?, ?, ?, ?)");
            ps.setString(1, this.credenciales.getText());
            ps.setString(2, this.permisos.getText());
            ps.setString(3, this.estado.getText());
            ps.setString(4, this.descripcion.getText());

            ps.execute();
            cnx.close();

            JOptionPane.showMessageDialog(rootPane, "Registro Insertado");
            this.credenciales.setText("");
            this.permisos.setText("");
            this.estado.setText("");
            this.descripcion.setText("");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al insertar: " + ex.getMessage());
        }
    }

    public void actualizar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sistema_administradores", "root", "");
            PreparedStatement ps = cnx.prepareStatement("update administradores set credenciales = ?, permisos = ?, estado = ?, descripcion = ? where id = ?");
            ps.setString(1, this.credenciales.getText());
            ps.setString(2, this.permisos.getText());
            ps.setString(3, this.estado.getText());
            ps.setString(4, this.descripcion.getText());
            ps.setInt(5, Integer.parseInt(this.txt_id.getText()));
            
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(rootPane, "Registro actualizado correctamente");
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se encontró un registro con ese ID");
            }

            this.consultar();
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Ingresa un número ID válido.");
        }
    }

    public void eliminar() {
        try {
            Connection cnx = DriverManager.getConnection("jdbc:mysql://127.0.0.1/sistema_administradores", "root", "");
            PreparedStatement ps = cnx.prepareStatement("delete from administradores where id = ?");
            
            ps.setInt(1, Integer.parseInt(this.txt_id.getText()));
            
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(rootPane, "Registro eliminado correctamente");
                this.txt_id.setText("");
            } else {
                JOptionPane.showMessageDialog(rootPane, "No se encontró el registro con esa ID para eliminar...");
            }

            this.consultar();
            cnx.close();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(rootPane, "Ingresa un número ID válido para eliminar.");
        }
    }

    //Parámetro de método
    public static void main(String[] args) {
        new usuarios_ad();
    }
}

