package com.erettsegi;

public class Vizsgaadatok {

    // vizsgazo tábla mezőinek
    public int azon;
    public String nev;
    public String osztaly;

    // vizsgatargy tábla mezőinek
    public int targyAzon;
    public String targyNev;
    public int szomax;
    public int irmax;

    // vizsga tábla mezőinek
    public int szobeli;
    public int irasbeli;

    public Vizsgaadatok() {}

    public Vizsgaadatok(int azon, String nev, String osztaly, int targyAzon, String targyNev, int szomax, int irmax, int szobeli, int irasbeli) {
        this.azon = azon;
        this.nev = nev;
        this.osztaly = osztaly;
        this.targyAzon = targyAzon;
        this.targyNev = targyNev;
        this.szomax = szomax;
        this.irmax = irmax;
        this.szobeli = szobeli;
        this.irasbeli = irasbeli;
    }

    public int getAzon() {
        return azon;
    }

    public void setAzon(int azon) {
        this.azon = azon;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public String getOsztaly() {
        return osztaly;
    }

    public void setOsztaly(String osztaly) {
        this.osztaly = osztaly;
    }

    public int getTargyAzon() {
        return targyAzon;
    }

    public void setTargyAzon(int targyAzon) {
        this.targyAzon = targyAzon;
    }

    public String getTargyNev() {
        return targyNev;
    }

    public void setTargyNev(String targyNev) {
        this.targyNev = targyNev;
    }

    public int getSzomax() {
        return szomax;
    }

    public void setSzomax(int szomax) {
        this.szomax = szomax;
    }

    public int getIrmax() {
        return irmax;
    }

    public void setIrmax(int irmax) {
        this.irmax = irmax;
    }

    public int getSzobeli() {
        return szobeli;
    }

    public void setSzobeli(int szobeli) {
        this.szobeli = szobeli;
    }

    public int getIrasbeli() {
        return irasbeli;
    }

    public void setIrasbeli(int irasbeli) {
        this.irasbeli = irasbeli;
    }
}
