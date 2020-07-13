package hr.vsite.map.taxivodstvo;

public class SmjenaHelperClass {

    String etDatum, smjena, pk, zk, ukupnikm, ukPromet, kartice, potvrde, fiskal;

    public SmjenaHelperClass() {

    }

    public SmjenaHelperClass(String etDatum, String smjena, String pk, String zk, String ukupnikm, String ukPromet, String kartice, String potvrde, String fiskal) {
        this.etDatum = etDatum;
        this.smjena = smjena;
        this.pk = pk;
        this.zk = zk;
        this.ukupnikm = ukupnikm;
        this.ukPromet = ukPromet;
        this.kartice = kartice;
        this.potvrde = potvrde;
        this.fiskal = fiskal;
    }

    public String getEtDatum() {
        return etDatum;
    }

    public void setEtDatum(String etDatum) {
        this.etDatum = etDatum;
    }

    public String getSmjena() {
        return smjena;
    }

    public void setSmjena(String smjena) {
        this.smjena = smjena;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public String getZk() {
        return zk;
    }

    public void setZk(String zk) {
        this.zk = zk;
    }

    public String getUkupnikm() {
        return ukupnikm;
    }

    public void setUkupnikm(String ukupnikm) {
        this.ukupnikm = ukupnikm;
    }

    public String getUkPromet() {
        return ukPromet;
    }

    public void setUkPromet(String ukPromet) {
        this.ukPromet = ukPromet;
    }

    public String getKartice() {
        return kartice;
    }

    public void setKartice(String kartice) {
        this.kartice = kartice;
    }

    public String getPotvrde() {
        return potvrde;
    }

    public void setPotvrde(String potvrde) {
        this.potvrde = potvrde;
    }

    public String getFiskal() {
        return fiskal;
    }

    public void setFiskal(String fiskal) {
        this.fiskal = fiskal;
    }
}
