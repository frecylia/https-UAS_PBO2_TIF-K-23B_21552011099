package model;

public class AsuransiJiwa extends Asuransi {

    public AsuransiJiwa(String nama, int umur) {
        super(nama, umur);
    }

    @Override
    public int hitungPremi() {
        return umur * 15000;
    }
}
