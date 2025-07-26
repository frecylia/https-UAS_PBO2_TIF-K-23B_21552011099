package model;

public abstract class Asuransi {

    protected String nama;
    protected int umur;

    public Asuransi(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    public String getInfoNasabah() {
        return "Nama: " + nama + ", Umur: " + umur;
    }

    public abstract int hitungPremi();
}
