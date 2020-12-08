package com.farouq.myuts.Model;

public class Makanan {

    private String kode_makanan;
    private String nama_makanan;
    private String asal_makanan;
    private String bahan_makanan;
    private String caraBuat_makanan;

    public Makanan(String kode_makanan, String nama_makanan, String asal_makanan,
                   String bahan_makanan, String caraBuat_makanan) {
        this.kode_makanan = kode_makanan;
        this.nama_makanan = nama_makanan;
        this.asal_makanan = asal_makanan;
        this.bahan_makanan = bahan_makanan;
        this.caraBuat_makanan = caraBuat_makanan;
    }

    public String getKode_makanan() {
        return kode_makanan;
    }

    public void setKode_makanan(String kode_makanan) {
        this.kode_makanan = kode_makanan;
    }

    public String getNama_makanan() {
        return nama_makanan;
    }

    public void setNama_makanan(String nama_makanan) {
        this.nama_makanan = nama_makanan;
    }

    public String getAsal_makanan() {
        return asal_makanan;
    }

    public void setAsal_makanan(String asal_makanan) {
        this.asal_makanan = asal_makanan;
    }

    public String getBahan_makanan() {
        return bahan_makanan;
    }

    public void setBahan_makanan(String bahan_makanan) {
        this.bahan_makanan = bahan_makanan;
    }

    public String getCaraBuat_makanan() {
        return caraBuat_makanan;
    }

    public void setCaraBuat_makanan(String caraBuat_makanan) {
        this.caraBuat_makanan = caraBuat_makanan;
    }

}
