
public class Pengunjung {
    private int id;
    private String Nama = null;
    private String Jenis_Kelamin = null;
    private String Jam_Masuk = null;

    public Pengunjung(Integer inputid, String inputNama, String inputJenisKelamin, String inputJamMasuk){
        this.id = inputid;
        this.Nama = inputNama;
        this.Jenis_Kelamin = inputJenisKelamin;
        this.Jam_Masuk = inputJamMasuk;
    }

    public int getId() {
        return id;
    }
    public String getNama(){
        return Nama;
    }
    public String getJenisKelamin(){
        return Jenis_Kelamin;
    }
    public String getJamMasuk(){
        return Jam_Masuk;
    }

    public void setid(String text) {
    }

    public void setNama(String text) {
    }

    public void setJenisKelamin(String text) {
    }

    public void setJamMasuk(String text) {
    }
}
