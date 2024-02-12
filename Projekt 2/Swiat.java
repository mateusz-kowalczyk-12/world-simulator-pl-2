package pl.projekt2;


import java.util.Vector;

public class Swiat
{
    private int rozmiarX, rozmiarY;
    private char[][] plansza;

    private Vector<Zwierze&> = new Vector<zwierzeta>(0);
    private vector<Roslina&> rosliny;

    private String komunikat;


    public Stale stale;


    public Swiat(int rozmiarX, int rozmiarY)
    {
        konstruujSwiat(rozmiarX, rozmiarY);

        dodajOrganizmy();
    }

    public Swiat()
    {}

    public void konstruujSwiat(int rozmiarX, int rozmiarY)
    {
        this.rozmiarX = rozmiarX;
        this.rozmiarY = rozmiarY;

        this.plansza = new char[rozmiarY][rozmiarX];
        for (int y = 0; y < rozmiarY; y++)
        {
            for (int x = 0; x < rozmiarX; x++)
            {
                this.plansza[y][x] = stale.wolne;
            }
        }

        komunikat = "";
    }

    public void dodajOrganizmy()
    {
        dodajZwierze(new Czlowiek(0, 0, this));

        int pole = stale.krokInicjalizowaniaOrganizmow;
        int x, y;
        int dodanyOrganizm = 1;

        while (true)
        {
            x = pole % rozmiarX;
            y = pole / rozmiarY;
            int rodzajOrganizmu = dodanyOrganizm % stale.liczbaRodzajowOrganizmow;

            if (poleIstnieje(x, y))
            {
                if (rodzajOrganizmu == stale.wilkID)
                    dodajZwierze(new Wilk(x, y, this));
                if (rodzajOrganizmu == stale.owcaID)
                    dodajZwierze(new Owca(x, y, this));
                if (rodzajOrganizmu == stale.lisID)
                    dodajZwierze(new Lis(x, y, this));
                if (rodzajOrganizmu == stale.zolwID)
                    dodajZwierze(new Zolw(x, y, this));
                if (rodzajOrganizmu == stale.antylopaID)
                    dodajZwierze(new Antylopa(x, y, this));

                if (rodzajOrganizmu == stale.trawaID)
                    dodajRosline(new Trawa(x, y, this));
                if (rodzajOrganizmu == stale.mleczID)
                    dodajRosline(new Mlecz(x, y, this));
                if (rodzajOrganizmu == stale.guaranaID)
                    dodajRosline(new Guarana(x, y, this));
                if (rodzajOrganizmu == stale.wilczeJagodyID)
                    dodajRosline(new WilczeJagody(x, y, this));
                if (rodzajOrganizmu == stale.barszczSosnowskiegoID)
                    dodajRosline(new BarszczSosnowskiego(x, y, this));

                dodanyOrganizm++;
                pole += stale.krokInicjalizowaniaOrganizmow;
            }
            else
                break;
        }
    }


    public int odczytajRozmiarX()
    {
        return rozmiarX;
    }

    public int odczytajRozmiarY()
    {
        return rozmiarY;
    }

    public void dodajZwierze(Zwierze& noweZwierze)
    {
        for (Zwierze& zwierze : zwierzeta)
        {
            zwierze.zwiekszWiek();
        }

        zwierzeta.add(noweZwierze);
    }

    public void dodajRosline(Roslina& nowaRoslina)
    {
        rosliny.add(nowaRoslina);
    }

    public void usunOrganizm(Organizm& organizm)
    {
        if (organizm instanceof Zwierze)
        {
            for (int z = 0; z < zwierzeta.size(); z++)
            {
                if ((Zwierze&)organizm == zwierzeta[z])
                {
                    ustawSymbol(organizm.odczytajX(), organizm.odczytajY(), stale.wolne);
                    zwierzeta.remove(z);
                    return;
                }
            }
        }
        else
        {
            for (int r = 0; r < rosliny.size(); r++)
            {
                if ((Roslina&)organizm == rosliny[r])
                {
                    ustawSymbol(organizm.odczytajX(), organizm.odczytajY(), stale.wolne);
                    rosliny.remove(r);
                    return;
                }
            }
        }
    }

    public Organizm& dajOrganizm(int x, int y)
    {
        Organizm& zaatakowany;

        for (Zwierze& zwierze : zwierzeta)
        {
            if (zwierze.odczytajX() == x && zwierze.odczytajY() == y)
                return zwierze;
        }
        for (Roslina& roslina : rosliny)
        {
            if (roslina.odczytajX() == x && roslina.odczytajY() == y)
                return roslina;
        }

        return null;
    }

    public Czlowiek& dajCzlowieka()
    {
        for (Zwierze& zwierze : zwierzeta)
        {
            if (zwierze instanceof Czlowiek&)
                return (Czlowiek&)zwierze;
        }

        return nullptr;
    }

    public void ustawSymbol(int x, int y, char symbol)
    {
        if ((x >= 0 && x < rozmiarX) && (y >= 0 && y < rozmiarY))
            plansza[y][x] = symbol;
    }

    void ustawKomunikat(String komunikat)
    {
        if (komunikat == "")
            this.komunikat = komunikat;
	    else
            this.komunikat += (komunikat + '\n');
    }

    public String odczytajKomunikat()
    {
        return komunikat;
    }


    public void rysujSie()
    {
        System.out.print(stale.autor + "\n\n");

        //wypiszNumeryKolumn();
        for (int y = 0; y < rozmiarY; y++)
        {
            //std::cout << std::setw(policzCyfry(rozmiarY - 1)) << std::setfill('0') << y;

            for (int x = 0; x < rozmiarX; x++)
            {
                System.out.print(plansza[y][x]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println(komunikat);
    }

    public void wykonajTure()
    {
        sortujZwierzeta();

        ustawKomunikat("");
        rysujSie();

        Czlowiek& czlowiek = dajCzlowieka();
        if (czlowiek != null)
        {
            System.out.print("\033[H\033[2J");
            czlowiek.wczytajInstrukcje();
            ustawKomunikat("");
            System.out.print("\033[H\033[2J");
        }

        Vector<Zwierze&> poczatkoweZwierzeta = new Vector<Zwierze&>;
        poczatkoweZwierzeta = zwierzeta;
        for (Zwierze& zwierze : poczatkoweZwierzeta)
        {
            if (zwierzeNadalIstnieje(zwierze))
            {
                zwierze.wykonajAkcje();
            }
        }

        int liczbaRoslin = rosliny.size();
        for (int r = 0; r < liczbaRoslin; r++)
        {
            rosliny[r]->wykonajAkcje();
        }
    }


    void wypiszNumeryKolumn() const;

    void sortujZwierzeta();


    bool istniejeWolnePoleObok(int x, int y) const;

    bool poleWolne(int x, int y) const;

    bool poleIstnieje(int x, int y) const;

    int policzCyfry(int liczba) const;

    bool zwierzeNadalIstnieje(Zwierze* poczatkoweZwierze) const;


    void czekajNaSpacje() const;

    bool kontynuowac();


    void zapiszStanSymulacji();

    void wczytajStanSymulacji();
}
