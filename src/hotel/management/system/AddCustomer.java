package hotel.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {
    
    JComboBox comboid;
    JTextField tfnumber, tfname, tfcountry, tfdeposit;
    JRadioButton rbmale, rbfemale;
    Choice croom;
    JLabel checkintime;
    JButton add, back;
    
    AddCustomer() {
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel text = new JLabel("NEW CUSTOMER FORM");
        text.setFont(new Font("Raleway", Font.PLAIN, 20));
        text.setBounds(100, 20, 300, 30);
        add(text);
        
        JLabel lblid = new JLabel("ID");
        lblid.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblid.setBounds(35, 80, 100, 20);
        add(lblid);
        
        String Options[] = {"AAdhar Card", "Passport", "Driving Licence", "Voter-ID", "Ration Card"};
        comboid = new JComboBox(Options);
        comboid.setBounds(200, 80, 150, 25);
        comboid.setBackground(Color.WHITE);
        add(comboid);
        
        JLabel lblnumber = new JLabel("Id Number");
        lblnumber.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblnumber.setBounds(35, 120, 100, 20);
        add(lblnumber);
        
        tfnumber = new JTextField();
        tfnumber.setBounds(200, 120, 150, 25);
        add(tfnumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblname.setBounds(35, 160, 100, 20);
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);
        
        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblgender.setBounds(35, 200, 100, 20);
        add(lblgender);
        
        rbmale = new JRadioButton("M");
        rbmale.setBounds(200, 200, 60, 25);
        rbmale.setFont (new Font ("Tohoma", Font.PLAIN, 14));
        rbmale.setBackground(Color.WHITE);
        add(rbmale);
        
        rbfemale = new JRadioButton("F");
        rbfemale.setBounds(270, 200, 60, 25);
        rbfemale.setFont (new Font ("Tohoma", Font.PLAIN, 14));
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        
        JLabel lblcountry = new JLabel("Country");
        lblcountry.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblcountry.setBounds(35, 240, 100, 20);
        add(lblcountry);
        
        tfcountry = new JTextField();
        tfcountry.setBounds(200, 240, 150, 25);
        add(tfcountry);
        
        JLabel lblroom = new JLabel("Room Number");
        lblroom.setFont(new Font("Raleway", Font.PLAIN, 20));
        lblroom.setBounds(35, 280, 150, 20);
        add(lblroom);
        
        croom = new Choice();
        
        try {
            Conn conn = new Conn();
            String query = "select * from room where availability = 'Available'";
            ResultSet rs = conn.s.executeQuery(query);
            while(rs.next()) {
                croom.add(rs.getString("room"));
            }
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        croom.setBounds(200, 280, 150, 25);
        add(croom);
        
        JLabel lbltime = new JLabel("Checkin Time");
        lbltime.setFont(new Font("Raleway", Font.PLAIN, 20));
        lbltime.setBounds(35, 320, 150, 20);
        add(lbltime);
        
        Date date = new Date();
        
        checkintime = new JLabel("" + date);
        checkintime.setFont(new Font("Raleway", Font.PLAIN, 16));
        checkintime.setBounds(200, 320, 150, 25);
        add(checkintime);
        
        JLabel lbldeposit = new JLabel("Deposit");
        lbldeposit.setFont(new Font("Raleway", Font.PLAIN, 20));
        lbldeposit.setBounds(35, 360, 100, 20);
        add(lbldeposit);
        
        tfdeposit = new JTextField();
        tfdeposit.setBounds(200, 360, 150, 25);
        add(tfdeposit);
        
        add = new JButton("Add");
        add.setBackground(Color.WHITE);
        add.setForeground(Color.BLACK);
        add.setBounds(50, 410, 120, 30);
        add.addActionListener(this);
        add(add);
        
        back = new JButton("Back");
        back.setBackground(Color.WHITE);
        back.setForeground(Color.BLACK);
        back.setBounds(200, 410, 120, 30);
        back.addActionListener(this);
        add(back);
        
        ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 300, 400);
        add(image);
        
        setBounds(350, 200, 800, 550);
        setVisible(true);
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource() == add) {
            String id = (String) comboid.getSelectedItem();
            String number = tfnumber.getText();
            String name = tfname.getText();
            String gender = null;
            
            if(rbmale.isSelected()){
                gender = "Male";
                
            }else{
                gender = "Female";
            }
            
            String country = tfcountry.getText();
            String room = croom.getSelectedItem();
            String time = checkintime.getText();
            String deposit = tfdeposit.getText();
            
            try {
                String query = "insert into customer values('"+id+"','"+number+"','"+name+"','"+gender+"','"+country+"','"+room+"','"+time+"','"+deposit+"')";
                String query2 = "update room set availability = 'Occupied' where room = '"+room+"'";
                
                Conn conn = new Conn();
                conn.s.executeUpdate(query);
                conn.s.executeUpdate(query2);
                
                JOptionPane.showMessageDialog(null, "New Customer Added Successfully");
                
                setVisible(false);
                new Reception();
                
            }catch(Exception e) {
                e.printStackTrace();
            }
            
        }
        else if (ae.getSource() == back) {
            setVisible(false);
                new Reception();
        }
    }
    
    public static void main(String a[]) {
        new AddCustomer();
    }
}