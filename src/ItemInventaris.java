public class ItemInventaris {
    private String kodeBarang;
    private String namaBarang;
    private String kategoriBarang;
    private int jumlahUnit;
    private double hargaSatuan;

    public ItemInventaris(String kodeBarang, String namaBarang, String kategoriBarang, int jumlahUnit, double hargaSatuan) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.kategoriBarang = kategoriBarang;
        this.jumlahUnit = jumlahUnit;
        this.hargaSatuan = hargaSatuan;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public String getKategoriBarang() {
        return kategoriBarang;
    }

    public int getJumlahUnit() {
        return jumlahUnit;
    }

    public double getHargaSatuan() {
        return hargaSatuan;
    }

    public double hitungTotalNilai() {
        return jumlahUnit * hargaSatuan;
    }

    public String getRingkasanItem() {
        return "[" + kodeBarang + "] " + namaBarang 
                + " | Kategori: " + kategoriBarang 
                + " | Jumlah: " + jumlahUnit + " unit"
                + " | Harga: Rp" + (int) hargaSatuan 
                + " | Total: Rp" + (int) hitungTotalNilai();
    }
}