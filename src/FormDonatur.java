import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class FormDonatur extends JFrame {
    private JTextField tfNama, tfEmail, tfNoHP;
    private JButton btnSimpan;

    public FormDonatur() {
        setTitle("Form Donatur");
        setSize(300, 250);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblNoHP = new JLabel("No HP:");

        tfNama = new JTextField();
        tfEmail = new JTextField();
        tfNoHP = new JTextField();
        btnSimpan = new JButton("Simpan");

        lblNama.setBounds(20, 20, 80, 25);
        tfNama.setBounds(100, 20, 150, 25);
        lblEmail.setBounds(20, 60, 80, 25);
        tfEmail.setBounds(100, 60, 150, 25);
        lblNoHP.setBounds(20, 100, 80, 25);
        tfNoHP.setBounds(100, 100, 150, 25);
        btnSimpan.setBounds(100, 150, 100, 30);

        add(lblNama);
        add(tfNama);
        add(lblEmail);
        add(tfEmail);
        add(lblNoHP);
        add(tfNoHP);
        add(btnSimpan);

        btnSimpan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                simpanData();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void simpanData() {
        String nama = tfNama.getText();
        String email = tfEmail.getText();
        String noHP = tfNoHP.getText();

        try {
            Connection conn = KoneksiDB.getConnection();
            String query = "INSERT INTO donatur (nama, email, no_hp) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nama);
            ps.setString(2, email);
            ps.setString(3, noHP);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new FormDonatur();
    }
}