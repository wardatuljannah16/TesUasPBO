import java.sql.*;
import javax.swing.*;

public class FormDistribusi extends JFrame {
    private JTextField tfIdKegiatan, tfTanggal, tfJumlah, tfPenerima;
    private JButton btnSimpan;

    public FormDistribusi() {
        setTitle("Form Distribusi");
        setSize(350, 250);
        setLayout(null);

        JLabel lblIdKegiatan = new JLabel("ID Kegiatan:");
        JLabel lblTanggal = new JLabel("Tanggal (YYYY-MM-DD):");
        JLabel lblJumlah = new JLabel("Jumlah Distribusi:");
        JLabel lblPenerima = new JLabel("Penerima:");

        tfIdKegiatan = new JTextField();
        tfTanggal = new JTextField();
        tfJumlah = new JTextField();
        tfPenerima = new JTextField();
        btnSimpan = new JButton("Simpan");

        lblIdKegiatan.setBounds(20, 20, 150, 25);
        tfIdKegiatan.setBounds(170, 20, 150, 25);
        lblTanggal.setBounds(20, 60, 150, 25);
        tfTanggal.setBounds(170, 60, 150, 25);
        lblJumlah.setBounds(20, 100, 150, 25);
        tfJumlah.setBounds(170, 100, 150, 25);
        lblPenerima.setBounds(20, 140, 150, 25);
        tfPenerima.setBounds(170, 140, 150, 25);
        btnSimpan.setBounds(110, 180, 100, 30);

        add(lblIdKegiatan); add(tfIdKegiatan);
        add(lblTanggal); add(tfTanggal);
        add(lblJumlah); add(tfJumlah);
        add(lblPenerima); add(tfPenerima);
        add(btnSimpan);

        btnSimpan.addActionListener(e -> simpanData());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void simpanData() {
        try {
            Connection conn = KoneksiDB.getConnection();
            String query = "INSERT INTO distribusi (id_kegiatan, tanggal_distribusi, jumlah_distribusi, penerima) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, Integer.parseInt(tfIdKegiatan.getText()));
            ps.setDate(2, Date.valueOf(tfTanggal.getText()));
            ps.setBigDecimal(3, new java.math.BigDecimal(tfJumlah.getText()));
            ps.setString(4, tfPenerima.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Distribusi berhasil disimpan!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new FormDistribusi());
    }
}