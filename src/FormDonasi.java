import java.sql.*;
import javax.swing.*;

public class FormDonasi extends JFrame {
    private JTextField tfIdDonatur, tfTanggal, tfJumlah;
    private JButton btnSimpan;

    public FormDonasi() {
        setTitle("Form Donasi");
        setSize(300, 250);
        setLayout(null);

        JLabel lblIdDonatur = new JLabel("ID Donatur:");
        JLabel lblTanggal = new JLabel("Tanggal (YYYY-MM-DD):");
        JLabel lblJumlah = new JLabel("Jumlah:");

        tfIdDonatur = new JTextField();
        tfTanggal = new JTextField();
        tfJumlah = new JTextField();
        btnSimpan = new JButton("Simpan");

        lblIdDonatur.setBounds(20, 20, 150, 25);
        tfIdDonatur.setBounds(170, 20, 100, 25);
        lblTanggal.setBounds(20, 60, 150, 25);
        tfTanggal.setBounds(170, 60, 100, 25);
        lblJumlah.setBounds(20, 100, 150, 25);
        tfJumlah.setBounds(170, 100, 100, 25);
        btnSimpan.setBounds(90, 150, 100, 30);

        add(lblIdDonatur); add(tfIdDonatur);
        add(lblTanggal); add(tfTanggal);
        add(lblJumlah); add(tfJumlah);
        add(btnSimpan);

        btnSimpan.addActionListener(e -> simpanData());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void simpanData() {
        try {
            Connection conn = KoneksiDB.getConnection();
            String query = "INSERT INTO donasi (id_donatur, tanggal, jumlah) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(tfIdDonatur.getText()));
            ps.setDate(2, Date.valueOf(tfTanggal.getText()));
            ps.setBigDecimal(3, new java.math.BigDecimal(tfJumlah.getText()));
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Donasi berhasil disimpan!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new FormDonasi());
    }
}