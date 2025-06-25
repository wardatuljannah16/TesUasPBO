import java.sql.*;
import javax.swing.*;

public class FormKegiatan extends JFrame {
    private JTextField tfNama, tfTanggal, tfLokasi;
    private JTextArea taDeskripsi;
    private JButton btnSimpan;

    public FormKegiatan() {
        setTitle("Form Kegiatan");
        setSize(350, 300);
        setLayout(null);

        JLabel lblNama = new JLabel("Nama Kegiatan:");
        JLabel lblTanggal = new JLabel("Tanggal (YYYY-MM-DD):");
        JLabel lblLokasi = new JLabel("Lokasi Kegiatan:");
        JLabel lblDeskripsi = new JLabel("Deskripsi:");

        tfNama = new JTextField();
        tfTanggal = new JTextField();
        tfLokasi = new JTextField();
        taDeskripsi = new JTextArea();
        JScrollPane spDeskripsi = new JScrollPane(taDeskripsi);
        btnSimpan = new JButton("Simpan");

        lblNama.setBounds(20, 20, 120, 25);
        tfNama.setBounds(150, 20, 150, 25);
        lblTanggal.setBounds(20, 60, 120, 25);
        tfTanggal.setBounds(150, 60, 150, 25);
        lblLokasi.setBounds(20, 100, 120, 25);
        tfLokasi.setBounds(150, 100, 150, 25);
        lblDeskripsi.setBounds(20, 140, 120, 25);
        spDeskripsi.setBounds(150, 140, 150, 60);
        btnSimpan.setBounds(100, 220, 100, 30);

        add(lblNama); add(tfNama);
        add(lblTanggal); add(tfTanggal);
        add(lblLokasi); add(tfLokasi);
        add(lblDeskripsi); add(spDeskripsi);
        add(btnSimpan);

        btnSimpan.addActionListener(e -> simpanData());

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void simpanData() {
        try {
            Connection conn = KoneksiDB.getConnection();
            String query = "INSERT INTO kegiatan (nama_kegiatan, tanggal_kegiatan, lokasi, deskripsi) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, tfNama.getText());
            ps.setDate(2, Date.valueOf(tfTanggal.getText()));
            ps.setString(3, tfLokasi.getText());
            ps.setString(4, taDeskripsi.getText());
            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "Data Kegiatan berhasil disimpan!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new FormKegiatan());
    }
}