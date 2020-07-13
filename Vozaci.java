package hr.vsite.map.taxivodstvo;

public class Vozaci {
    private String korisnickoIme;
    private String ime;
    private String prezime;
    private String brMob;

    public Vozaci() {    }

    public Vozaci(String korisnickoIme, String ime, String prezime, String brMob) {
        this.korisnickoIme = korisnickoIme;
        this.ime = ime;
        this.prezime = prezime;
        this.brMob = brMob;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
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

    public String getBrMob() {
        return brMob;
    }

    public void setBrMob(String brMob) {
        this.brMob = brMob;
    }


}
