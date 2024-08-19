public class CreditCard {
    private String sonIstifadeMuddeti;
    private String kartNomresi;
    private String ad;
    private int cvv;

    public void kreditHesabla () {
        double kredtiMiqtari = 5000;
        int muddet = 12;
        double illikFaizDerecesi = 1.5;
        double ayliqFaizDerecesi = illikFaizDerecesi / 1200; //

        double ayliqFaizTutumu = (kredtiMiqtari * ayliqFaizDerecesi) / (1 - Math.pow(1 + ayliqFaizDerecesi, -muddet ));

        System.out.println("Aylıq ödenecek miqdar: " + ayliqFaizTutumu);
        System.out.println("Toplam geri ödenecek miqdar: " + ayliqFaizTutumu * muddet);
    }




    public String getSonIstifadeMuddeti() {
        return sonIstifadeMuddeti;
    }

    public void setSonIstifadeMuddeti(String sonIstifadeMuddeti) {
        this.sonIstifadeMuddeti = sonIstifadeMuddeti;
    }

    public String getKartNomresi() {
        return kartNomresi;
    }

    public void setKartNomresi(String kartNomresi) {
        this.kartNomresi = kartNomresi;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public CreditCard(String sonIstifadeMuddeti, String kartNomresi, String ad, int cvv) {
        this.sonIstifadeMuddeti = sonIstifadeMuddeti;
        this.kartNomresi = kartNomresi;
        this.ad = ad;
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "sonIstifadeMuddeti='" + sonIstifadeMuddeti + '\'' +
                ", kartNomresi='" + kartNomresi + '\'' +
                ", ad='" + ad + '\'' +
                ", cvv=" + cvv +
                '}';
    }
}




