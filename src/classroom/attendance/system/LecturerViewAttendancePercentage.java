/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package classroom.attendance.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author uwagb
 */
public class LecturerViewAttendancePercentage extends javax.swing.JFrame {
private int courseID;
//private String courseName;
private float totalAttendance;
    /**
     * Creates new form LecturerViewAttendancePercentage
     */
    public LecturerViewAttendancePercentage(int courseID, String courseName, float totalAttendance) {
        initComponents();
        this.courseID = courseID;
        //this.courseName = courseName;
        this.totalAttendance = totalAttendance;
        populateStudentNameTable();
        
        course_name_label.setText("Attendance for " + courseName);
    }
    
    public float calculatePercentageAttendance(int userID){
        float count = 0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
            PreparedStatement ps = con.prepareStatement("select * from StudentAttendance where StudentID in("
                    + "select StudentID from Student where AttendanceID in ("
                    + "select AttendanceID from Attendance where CourseID=?"
                    + ") and UserID=?"
                    + ")");
            ps.setInt(1, this.courseID);
            ps.setInt(2, userID);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                count++;
            }
            //System.out.println("Attendance signed: " + String.valueOf(count) + "; " + this.totalAttendance );
            //System.out.println(count / this.totalAttendance);
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        return (count / this.totalAttendance) * 100;
    }
    
    public void populateStudentNameTable(){
        DefaultTableModel td = (DefaultTableModel)student_name_table.getModel();
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS", "root", "example");
            PreparedStatement ps = con.prepareStatement(""
                    + "select * from User where UserID in("
                    + "select UserID from Student where StudentID in("
                    + "select StudentID from StudentAttendance where AttendanceID in("
                    + "select AttendanceID from Attendance where CourseID=?"
                    + ")"
                    + ")"
                    + ")");
            ps.setInt(1, this.courseID);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                String[] data = new String[4];
                
                data[0] = rs.getString("FirstName");
                
                data[1] = rs.getString("LastName");
                data[2] = rs.getString("MiddleName");
                data[3] = String.valueOf(calculatePercentageAttendance(rs.getInt("UserID")));
                td.addRow(data);
            }
        }catch(Exception e){
            System.out.println(e);
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

        jPanel1 = new javax.swing.JPanel();
        course_name_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        student_name_table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        course_name_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        course_name_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        course_name_label.setText("Attendance for a course");

        student_name_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "Middle Name", "Percentage"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        student_name_table.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(student_name_table);
        if (student_name_table.getColumnModel().getColumnCount() > 0) {
            student_name_table.getColumnModel().getColumn(0).setResizable(false);
            student_name_table.getColumnModel().getColumn(1).setResizable(false);
            student_name_table.getColumnModel().getColumn(2).setResizable(false);
            student_name_table.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(course_name_label, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(160, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(course_name_label)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(LecturerViewAttendancePercentage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LecturerViewAttendancePercentage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LecturerViewAttendancePercentage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LecturerViewAttendancePercentage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new LecturerViewAttendancePercentage(6).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel course_name_label;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable student_name_table;
    // End of variables declaration//GEN-END:variables
}
