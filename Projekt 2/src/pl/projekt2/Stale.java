package pl.projekt2;

public class Stale
{
    public final byte testowaWartoscPoczatkowa;

    public final char wolne;
    public final char wilk;
    public final char owca;
    public final char lis;
    public final char zolw;
    public final char antylopa;
    public final char trawa;
    public final char mlecz;
    public final char guarana;
    public final char wilczeJagody;
    public final char barszczSosnowskiego;
    public final char czlowiek;

    public final int liczbaRodzajowOrganizmow;
    public final int wilkID;
    public final int owcaID;
    public final int lisID;
    public final int zolwID;
    public final int antylopaID;
    public final int trawaID;
    public final int mleczID;
    public final int guaranaID;
    public final int wilczeJagodyID;
    public final int barszczSosnowskiegoID;

    public final int krokInicjalizowaniaOrganizmow;

    public final char wygralNapastnik;
    public final char wygralZaatakowany;
    public final char odbicieAtaku;
    public final char ucieczkaPrzedWalka;
    public final char rozmnozenie;
    public final char probaRozmnozenia;
    public final char zatrucie;
    public final char zjedzenie;

    public final char prawdopodobienstwoRozprzestrzenienia; //w %

    public final int wymiarOrganizmu;
    public final int gruboscKrawedzi;
    public final int dlugoscKrawedzi;


    public Stale()
    {
        testowaWartoscPoczatkowa = -1;

        wolne = ' ';

        wilk = 'W';
        owca = 'O';
        lis = 'L';
        zolw = 'Z';
        antylopa = 'A';
        trawa = 't';
        mlecz = 'm';
        guarana = 'g';
        wilczeJagody = 'j';
        barszczSosnowskiego ='b';
        czlowiek = 'C';

        liczbaRodzajowOrganizmow = 10;
        wilkID = 0;
        owcaID = 1;
        lisID = 2;
        zolwID = 3;
        antylopaID = 4;
        trawaID = 5;
        mleczID = 6;
        guaranaID = 7;
        wilczeJagodyID = 8;
        barszczSosnowskiegoID = 9;

        krokInicjalizowaniaOrganizmow = 3;

        wygralNapastnik = 0;
        wygralZaatakowany = 1;
        odbicieAtaku = 2;
        ucieczkaPrzedWalka = 3;
        rozmnozenie = 4;
        probaRozmnozenia = 5;
        zatrucie = 6;
        zjedzenie = 7;

        prawdopodobienstwoRozprzestrzenienia = 60; //%

        wymiarOrganizmu = 25;
        gruboscKrawedzi = 1;
        dlugoscKrawedzi = wymiarOrganizmu + gruboscKrawedzi;
    }
}
