/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package classroom.attendance.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author uwagb
 */
public class StudentSignInView extends javax.swing.JFrame {
    public class Student {
        int userID;    
        
        Student(int user_id){
            this.userID = user_id;
        }
    }
    private static int studentID = 0;
    /**
     * Creates new form StudentSignInView
     */
    public StudentSignInView() {
        initComponents();
        Retrieve retrieve = new Retrieve();
        retrieve.setVisible(false);
        //this.userID = retrieve.userID;
        
        
    }
    
    
    public static boolean checkForMultipleSigning(int userID){
        boolean signedIn = false;
        try{
            // Start
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS","root", "example");
            PreparedStatement ps = con.prepareStatement("select StudentID from Student where UserID=?");
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                studentID  = rs.getInt("StudentID");
            }
            // End
            
            
            Calendar calendar = Calendar.getInstance();
            java.sql.Date date_ = new java.sql.Date(calendar.getTime().getTime());
            java.sql.Time time_ = new java.sql.Time(date_.getTime());
            
            // Start
            PreparedStatement ps1 = con.prepareStatement("select * from Attendance where "
                    + "Date=? and Status=?");
            ps1.setDate(1, date_);
            ps1.setInt(2, 1);
            
            ResultSet rs1 = ps1.executeQuery();
            
            while(rs1.next()){
                java.sql.Time startTime = rs1.getTime("StartTime");
                if(startTime.getHours() == time_.getHours()){
                    PreparedStatement ps2 = con.prepareStatement("select * from StudentAttendance where "
                            + "StudentID=? and AttendanceID=?");
                    ps2.setInt(1,studentID);
                    ps2.setInt(2, rs1.getInt("AttendanceID"));
                    
                    ResultSet rs2 = ps2.executeQuery();
                    while(rs2.next()){
                        signedIn = true; // Student has signed in
                    }
                    
                    
                }
                
            }
            // End
            
           con.close();
        }catch(Exception e){
            System.out.println(e);
        }
        
        return signedIn;
    }
    
    
    public static boolean signAttendance(int userID){
        
        Calendar calendar = Calendar.getInstance();
        java.sql.Date date_ = new java.sql.Date(calendar.getTime().getTime());
        java.sql.Time time_ = new java.sql.Time(date_.getTime());
        
        boolean attendanceClosed = true;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CAS","root", "example");
            
            // Get Courses offered by Student
            PreparedStatement ps_ = con.prepareStatement("select * from StudentCourse where StudentID in("
                    + "select StudentID from Student where UserID=?"
                    + ")");
            ps_.setInt(1, userID);
            ResultSet rs_  = ps_.executeQuery();
            
            int courseID = 0;
            
            while(rs_.next()){
                courseID = rs_.getInt("CourseID");
                break;
            }
            //End
            
            if (courseID > 0){
                PreparedStatement ps = con.prepareStatement("select * from Attendance where "
                        + "Date=? and Status=? and courseID=?");
                ps.setDate(1, date_);
                ps.setInt(2, 1);
                ps.setInt(3, courseID);

                ResultSet rs = ps.executeQuery();

                while(rs.next()){
                    java.sql.Time startTime = rs.getTime("StartTime");
                    if(startTime.getHours() == time_.getHours()){
                        attendanceClosed = false;
                        PreparedStatement ps1 = con.prepareStatement("insert into StudentAttendance("
                                + "AttendanceID,StudentID,Status"
                                + ") values(?,?,?)");
                        ps1.setInt(1, rs.getInt("AttendanceID"));
                        ps1.setInt(2, studentID);
                        ps1.setString(3, "p");

                        ps1.executeUpdate();
                    }


                }
            }else{
                JOptionPane.showMessageDialog(null, "You do not offer this course");
            }
            con.close();
            
        }catch(Exception e){
            System.out.println(e);
        }
        return attendanceClosed;
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
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        finger_print_display_label = new javax.swing.JLabel();
        course_name_label = new javax.swing.JLabel();
        course_code_label = new javax.swing.JLabel();
        units_label = new javax.swing.JLabel();
        lecturer_name_label = new javax.swing.JLabel();
        dialogue_box_label = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Classroom");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Attendance");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("System");

        finger_print_display_label.setBackground(new java.awt.Color(255, 255, 255));
        finger_print_display_label.setOpaque(true);

        course_name_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        course_name_label.setText("Course Name");

        course_code_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        course_code_label.setText("Course Code");

        units_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        units_label.setText("Units");

        lecturer_name_label.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lecturer_name_label.setText("Lecturer's Name");

        dialogue_box_label.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dialogue_box_label.setText("Dialog Box");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)))
                    .addComponent(lecturer_name_label)
                    .addComponent(units_label)
                    .addComponent(course_name_label)
                    .addComponent(course_code_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dialogue_box_label, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                    .addComponent(finger_print_display_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(finger_print_display_label, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(dialogue_box_label, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel3)
                                .addGap(15, 15, 15)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addComponent(course_name_label)
                        .addGap(18, 18, 18)
                        .addComponent(course_code_label)
                        .addGap(18, 18, 18)
                        .addComponent(units_label)
                        .addGap(18, 18, 18)
                        .addComponent(lecturer_name_label)))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(StudentSignInView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentSignInView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentSignInView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentSignInView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentSignInView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel course_code_label;
    private javax.swing.JLabel course_name_label;
    public static javax.swing.JLabel dialogue_box_label;
    public static javax.swing.JLabel finger_print_display_label;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lecturer_name_label;
    private javax.swing.JLabel units_label;
    // End of variables declaration//GEN-END:variables
}
