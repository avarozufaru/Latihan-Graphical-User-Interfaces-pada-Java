import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InventarisFrame implements ActionListener, ItemListener, KeyListener {
    private JFrame frameUtama;

    private JTabbedPane tabKontainer;
    private JPanel panelTabUtama;
    private JPanel panelFormInput;
    private JPanel panelTombolAksi;
    private JPanel panelTabStatistik;
    private JScrollPane scrollPaneDaftar;

    private JLabel labelJudulHeader;
    private JLabel labelKodeBarang;
    private JLabel labelNamaBarang;
    private JLabel labelKategoriBarang;
    private JLabel labelJumlahUnit;
    private JLabel labelHargaSatuan;
    private JLabel labelStatusKarakter;
    private JLabel labelInfoKategori;

    private JTextField fieldKodeBarang;
    private JTextField fieldNamaBarang;
    private JComboBox<String> comboKategoriBarang;
    private JTextField fieldJumlahUnit;
    private JTextField fieldHargaSatuan;

    private JButton tombolTambah;
    private JButton tombolBersihkan;
    private JButton tombolHapusSemua;
    private JButton tombolKeluar;

    private JTextArea areaDaftarInventaris;
    private JTextArea areaRingkasanStatistik;

    private ArrayList<ItemInventaris> daftarInventaris;

    public InventarisFrame() {
        daftarInventaris = new ArrayList<ItemInventaris>();
        inisialisasiKomponenGUI();
    }

    private void inisialisasiKomponenGUI() {
        frameUtama = new JFrame("Sistem Manajemen Inventaris Laboratorium");
        frameUtama.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layoutUtama = new BorderLayout(10, 10);
        frameUtama.getContentPane().setLayout(layoutUtama);

        labelJudulHeader = new JLabel("SISTEM INVENTARIS PERANGKAT LABORATORIUM", SwingConstants.CENTER);
        labelJudulHeader.setFont(new Font("Arial", Font.BOLD, 16));
        labelJudulHeader.setForeground(Color.BLUE);
        frameUtama.getContentPane().add(labelJudulHeader, BorderLayout.NORTH);

        panelTabUtama = new JPanel();
        panelTabUtama.setLayout(new BorderLayout(10, 10));

        panelFormInput = new JPanel();
        GridLayout layoutForm = new GridLayout(6, 2);
        layoutForm.setHgap(10);
        layoutForm.setVgap(8);
        panelFormInput.setLayout(layoutForm);

        labelKodeBarang = new JLabel("  Kode Barang (Otomatis/Manual):");
        fieldKodeBarang = new JTextField("INV-01");

        labelNamaBarang = new JLabel("  Nama Perangkat:");
        fieldNamaBarang = new JTextField();

        labelKategoriBarang = new JLabel("  Kategori Perangkat:");
        String[] opsiKategori = {"Komputer & Server", "Jaringan (Networking)", "Periferal & Aksesoris", "Alat Ukur & Sensor"};
        comboKategoriBarang = new JComboBox<String>(opsiKategori);

        labelJumlahUnit = new JLabel("  Jumlah Unit (Angka):");
        fieldJumlahUnit = new JTextField();

        labelHargaSatuan = new JLabel("  Harga Satuan (Rp):");
        fieldHargaSatuan = new JTextField();

        labelStatusKarakter = new JLabel("  Panjang Nama: 0 karakter");
        labelInfoKategori = new JLabel("Dipilih: Komputer & Server");

        panelFormInput.add(labelKodeBarang);
        panelFormInput.add(fieldKodeBarang);
        panelFormInput.add(labelNamaBarang);
        panelFormInput.add(fieldNamaBarang);
        panelFormInput.add(labelKategoriBarang);
        panelFormInput.add(comboKategoriBarang);
        panelFormInput.add(labelJumlahUnit);
        panelFormInput.add(fieldJumlahUnit);
        panelFormInput.add(labelHargaSatuan);
        panelFormInput.add(fieldHargaSatuan);
        panelFormInput.add(labelStatusKarakter);
        panelFormInput.add(labelInfoKategori);

        panelTombolAksi = new JPanel();
        FlowLayout layoutTombol = new FlowLayout(FlowLayout.CENTER);
        layoutTombol.setHgap(10);
        layoutTombol.setVgap(10);
        panelTombolAksi.setLayout(layoutTombol);

        tombolTambah = new JButton("Simpan Barang");
        tombolBersihkan = new JButton("Bersihkan Form");
        tombolHapusSemua = new JButton("Reset Data");
        tombolKeluar = new JButton("Keluar Aplikasi");

        panelTombolAksi.add(tombolTambah);
        panelTombolAksi.add(tombolBersihkan);
        panelTombolAksi.add(tombolHapusSemua);
        panelTombolAksi.add(tombolKeluar);

        areaDaftarInventaris = new JTextArea(10, 50);
        areaDaftarInventaris.setEditable(false);
        areaDaftarInventaris.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaDaftarInventaris.setText("=== DAFTAR INVENTARIS LABORATORIUM ===\nBelum ada data barang yang dimasukkan.\n");
        scrollPaneDaftar = new JScrollPane(areaDaftarInventaris);

        panelTabUtama.add(panelFormInput, BorderLayout.NORTH);
        panelTabUtama.add(scrollPaneDaftar, BorderLayout.CENTER);
        panelTabUtama.add(panelTombolAksi, BorderLayout.SOUTH);

        panelTabStatistik = new JPanel();
        panelTabStatistik.setLayout(new BorderLayout(10, 10));

        areaRingkasanStatistik = new JTextArea();
        areaRingkasanStatistik.setEditable(false);
        areaRingkasanStatistik.setFont(new Font("Monospaced", Font.PLAIN, 13));
        perbaruiTampilanStatistik();

        panelTabStatistik.add(new JScrollPane(areaRingkasanStatistik), BorderLayout.CENTER);

        tabKontainer = new JTabbedPane();
        tabKontainer.add(panelTabUtama, "TAB 1: Input & Daftar Barang");
        tabKontainer.add(panelTabStatistik, "TAB 2: Ringkasan & Statistik");

        frameUtama.getContentPane().add(tabKontainer, BorderLayout.CENTER);

        tombolTambah.addActionListener(this);
        tombolBersihkan.addActionListener(this);
        tombolHapusSemua.addActionListener(this);
        tombolKeluar.addActionListener(this);

        comboKategoriBarang.addItemListener(this);

        fieldNamaBarang.addKeyListener(this);
        fieldJumlahUnit.addKeyListener(this);
        fieldHargaSatuan.addKeyListener(this);
    }

    public void tampilkanFrame() {
        frameUtama.setSize(680, 520);
        frameUtama.setLocationRelativeTo(null);
        frameUtama.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object sumberAksi = event.getSource();

        if (sumberAksi == tombolTambah) {
            prosesTambahBarang();
        } else if (sumberAksi == tombolBersihkan) {
            bersihkanFormInput();
        } else if (sumberAksi == tombolHapusSemua) {
            prosesResetSemuaData();
        } else if (sumberAksi == tombolKeluar) {
            prosesKonfirmasiKeluar();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getSource() == comboKategoriBarang && event.getStateChange() == ItemEvent.SELECTED) {
            String kategoriTerpilih = (String) comboKategoriBarang.getSelectedItem();
            labelInfoKategori.setText("Dipilih: " + kategoriTerpilih);
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        if (event.getSource() == fieldJumlahUnit || event.getSource() == fieldHargaSatuan) {
            char karakterInput = event.getKeyChar();
            if (!Character.isDigit(karakterInput) && karakterInput != KeyEvent.VK_BACK_SPACE) {
                event.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if (event.getSource() == fieldHargaSatuan && event.getKeyCode() == KeyEvent.VK_ENTER) {
            prosesTambahBarang();
        }
    }

    @Override
    public void keyReleased(KeyEvent event) {
        if (event.getSource() == fieldNamaBarang) {
            int panjangTeks = fieldNamaBarang.getText().length();
            labelStatusKarakter.setText("  Panjang Nama: " + panjangTeks + " karakter");
        }
    }

    private void prosesTambahBarang() {
        String kode = fieldKodeBarang.getText().trim();
        String nama = fieldNamaBarang.getText().trim();
        String kategori = (String) comboKategoriBarang.getSelectedItem();
        String teksJumlah = fieldJumlahUnit.getText().trim();
        String teksHarga = fieldHargaSatuan.getText().trim();

        if (kode.isEmpty() || nama.isEmpty() || teksJumlah.isEmpty() || teksHarga.isEmpty()) {
            JOptionPane.showMessageDialog(frameUtama,
                    "Seluruh kolom input wajib diisi sebelum menyimpan data!",
                    "Peringatan Validasi",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int jumlah = Integer.parseInt(teksJumlah);
            double harga = Double.parseDouble(teksHarga);

            if (jumlah <= 0 || harga <= 0) {
                JOptionPane.showMessageDialog(frameUtama,
                        "Jumlah unit dan harga satuan harus lebih besar dari nol!",
                        "Input Tidak Valid",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            ItemInventaris itemBaru = new ItemInventaris(kode, nama, kategori, jumlah, harga);
            daftarInventaris.add(itemBaru);

            perbaruiTampilanDaftar();
            perbaruiTampilanStatistik();
            bersihkanFormInput();

            int nomorBerikutnya = daftarInventaris.size() + 1;
            fieldKodeBarang.setText(String.format("INV-%02d", nomorBerikutnya));

            JOptionPane.showMessageDialog(frameUtama,
                    "Data barang \"" + nama + "\" berhasil ditambahkan ke inventaris.",
                    "Informasi Sukses",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException kesalahanFormat) {
            JOptionPane.showMessageDialog(frameUtama,
                    "Format angka pada Jumlah atau Harga tidak sesuai!",
                    "Kesalahan Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void bersihkanFormInput() {
        fieldNamaBarang.setText("");
        fieldJumlahUnit.setText("");
        fieldHargaSatuan.setText("");
        comboKategoriBarang.setSelectedIndex(0);
        labelStatusKarakter.setText("  Panjang Nama: 0 karakter");
        fieldNamaBarang.requestFocus();
    }

    private void prosesResetSemuaData() {
        if (daftarInventaris.isEmpty()) {
            JOptionPane.showMessageDialog(frameUtama,
                    "Daftar inventaris masih kosong.",
                    "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int hasilKonfirmasi = JOptionPane.showConfirmDialog(frameUtama,
                "Apakah Anda yakin ingin menghapus seluruh data inventaris?",
                "Konfirmasi Reset Data",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (hasilKonfirmasi == JOptionPane.OK_OPTION) {
            daftarInventaris.clear();
            perbaruiTampilanDaftar();
            perbaruiTampilanStatistik();
            fieldKodeBarang.setText("INV-01");
            JOptionPane.showMessageDialog(frameUtama,
                    "Seluruh data inventaris telah dihapus.",
                    "Pilihan Anda",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void prosesKonfirmasiKeluar() {
        int hasilKonfirmasi = JOptionPane.showConfirmDialog(frameUtama,
                "Apakah Anda yakin ingin menutup aplikasi inventaris ini?",
                "Konfirmasi Keluar",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (hasilKonfirmasi == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void perbaruiTampilanDaftar() {
        StringBuilder teksBuilder = new StringBuilder();
        teksBuilder.append("=== DAFTAR INVENTARIS LABORATORIUM ===\n");

        if (daftarInventaris.isEmpty()) {
            teksBuilder.append("Belum ada data barang yang dimasukkan.\n");
        } else {
            double totalAsetKeseluruhan = 0;
            for (int indeks = 0; indeks < daftarInventaris.size(); indeks++) {
                ItemInventaris item = daftarInventaris.get(indeks);
                teksBuilder.append((indeks + 1)).append(". ").append(item.getRingkasanItem()).append("\n");
                totalAsetKeseluruhan += item.hitungTotalNilai();
            }
            teksBuilder.append("------------------------------------------------------------------------------\n");
            teksBuilder.append("Total Jenis Barang : ").append(daftarInventaris.size()).append(" item\n");
            teksBuilder.append("Total Nilai Aset   : Rp").append((int) totalAsetKeseluruhan).append("\n");
        }
        areaDaftarInventaris.setText(teksBuilder.toString());
    }

    private void perbaruiTampilanStatistik() {
        StringBuilder statistikBuilder = new StringBuilder();
        statistikBuilder.append("=== RINGKASAN STATISTIK INVENTARIS PER KATEGORI ===\n\n");

        int totalUnitKomputer = 0;
        int totalUnitJaringan = 0;
        int totalUnitPeriferal = 0;
        int totalUnitAlatUkur = 0;
        double totalNilaiSeluruhAset = 0;

        for (int indeks = 0; indeks < daftarInventaris.size(); indeks++) {
            ItemInventaris item = daftarInventaris.get(indeks);
            String kategori = item.getKategoriBarang();
            int unit = item.getJumlahUnit();

            if (kategori.equals("Komputer & Server")) {
                totalUnitKomputer += unit;
            } else if (kategori.equals("Jaringan (Networking)")) {
                totalUnitJaringan += unit;
            } else if (kategori.equals("Periferal & Aksesoris")) {
                totalUnitPeriferal += unit;
            } else if (kategori.equals("Alat Ukur & Sensor")) {
                totalUnitAlatUkur += unit;
            }
            totalNilaiSeluruhAset += item.hitungTotalNilai();
        }

        statistikBuilder.append("1. Komputer & Server      : ").append(totalUnitKomputer).append(" unit\n");
        statistikBuilder.append("2. Jaringan (Networking)  : ").append(totalUnitJaringan).append(" unit\n");
        statistikBuilder.append("3. Periferal & Aksesoris  : ").append(totalUnitPeriferal).append(" unit\n");
        statistikBuilder.append("4. Alat Ukur & Sensor     : ").append(totalUnitAlatUkur).append(" unit\n\n");
        statistikBuilder.append("===================================================\n");
        statistikBuilder.append("Total Transaksi Tercatat  : ").append(daftarInventaris.size()).append(" entri\n");
        statistikBuilder.append("Akumulasi Nilai Inventaris: Rp").append((int) totalNilaiSeluruhAset).append("\n");

        areaRingkasanStatistik.setText(statistikBuilder.toString());
    }
}