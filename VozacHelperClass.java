package hr.vsite.map.taxivodstvo;

public class VozacHelperClass {

    String ime, prezime, spol, ulica, kBr, postBr, mjesto, brMob, email, korisnickoIme, zaporka, rangBool;

    public VozacHelperClass() {    }

    public VozacHelperClass(String ime, String prezime, String spol, String ulica, String kBr, String postBr, String mjesto, String brMob, String email, String korisnickoIme, String zaporka, String rangBool) {
        this.ime = ime;
        this.prezime = prezime;
        this.spol = spol;
        this.ulica = ulica;
        this.kBr = kBr;
        this.postBr = postBr;
        this.mjesto = mjesto;
        this.brMob = brMob;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.zaporka = zaporka;
        this.rangBool = rangBool;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getSpol() {
        return spol;
    }

    public void setSpol(String spol) {
        this.spol = spol;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getkBr() {
        return kBr;
    }

    public void setkBr(String kBr) {
        this.kBr = kBr;
    }

    public String getPostBr() {
        return postBr;
    }

    public void setPostBr(String postBr) {
        this.postBr = postBr;
    }

    public String getMjesto() {
        return mjesto;
    }

    public void setMjesto(String mjesto) {
        this.mjesto = mjesto;
    }

    public String getBrMob() {
        return brMob;
    }

    public void setBrMob(String brMob) {
        this.brMob = brMob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getZaporka() {
        return zaporka;
    }

    public void setZaporka(String zaporka) {
        this.zaporka = zaporka;
    }

    public String getRangBool() {
        return rangBool;
    }

    public void setRangBool(String rangBool) {
        this.rangBool = rangBool;
    }
}
