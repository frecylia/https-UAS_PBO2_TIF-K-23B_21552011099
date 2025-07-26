package model;

public class AsuransiKesehatan extends Asuransi {

    public AsuransiKesehatan(String nama, int umur) {
        super(nama, umur);
    }

    @Override
    public int hitungPremi() {
        return umur * 10000;
    }
}
