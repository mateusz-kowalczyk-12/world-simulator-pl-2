package pl.projekt2;

import java.util.Vector;
import java.util.Collections;
import java.io.File;
import java.io.PrintWriter;

abstract public class Swiat
{
    protected int rozmiarX, rozmiarY;
    protected char[][] plansza;

    protected boolean dodanoNowyOrganizm;

    protected Vector<Zwierze> zwierzeta;
    protected Vector<Roslina> rosliny;

    protected String komunikat;

    protected OknoSymulacji oknoSymulacji;

    public Stale stale;


    public Swiat()
    {
        zwierzeta = new Vector<>(0);
        rosliny = new Vector<>(0);

        stale = new Stale();
    }

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

        this.dodanoNowyOrganizm = false;

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
            y = pole / rozmiarX;

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


    public void dodajZwierze(Zwierze noweZwierze)
    {
        for (Zwierze zwierze : zwierzeta)
        {
            zwierze.zwiekszWiek();
        }

        zwierzeta.add(noweZwierze);
    }

    public void dodajRosline(Roslina nowaRoslina)
    {
        rosliny.add(nowaRoslina);
    }

    public void usunOrganizm(Organizm organizm)
    {
        if (organizm instanceof Zwierze)
        {
            for (int z = 0; z < zwierzeta.size(); z++)
            {
                if (organizm == zwierzeta.get(z))
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
                if (organizm == rosliny.get(r))
                {
                    ustawSymbol(organizm.odczytajX(), organizm.odczytajY(), stale.wolne);
                    rosliny.remove(r);
                    return;
                }
            }
        }
    }

    public Organizm dajOrganizm(int x, int y)
    {
        for (Zwierze zwierze : zwierzeta)
        {
            if (zwierze.odczytajX() == x && zwierze.odczytajY() == y)
                return zwierze;
        }
        for (Roslina roslina : rosliny)
        {
            if (roslina.odczytajX() == x && roslina.odczytajY() == y)
                return roslina;
        }

        return null;
    }

    public Czlowiek dajCzlowieka()
    {
        for (Zwierze zwierze : zwierzeta)
        {
            if (zwierze instanceof Czlowiek)
                return (Czlowiek)zwierze;
        }

        return null;
    }

    public int odczytajRozmiarX()
    {
        return rozmiarX;
    }

    public int odczytajRozmiarY()
    {
        return rozmiarY;
    }

    public void ustawSymbol(int x, int y, char symbol)
    {
        if ((x >= 0 && x < rozmiarX) && (y >= 0 && y < rozmiarY))
            plansza[y][x] = symbol;
    }

    public void ustawOknoSymulacji(OknoSymulacji oknoSymulacji)
    {
        this.oknoSymulacji = oknoSymulacji;
    }

    public OknoSymulacji dajOknoSymulacji()
    {
        return oknoSymulacji;
    }

    public void ustawDodanoNowyOrganizm(boolean dodanoNowyOrganizm)
    {
        this.dodanoNowyOrganizm = dodanoNowyOrganizm;
    }

    public boolean odczytajDodanoNowyOrganizm()
    {
        return dodanoNowyOrganizm;
    }


    public void wykonajTure()
    {
        ustawKomunikat("");
        oknoSymulacji.rysujSwiat();

        Czlowiek czlowiek = dajCzlowieka();
        if (czlowiek != null)
            czlowiek.wczytajInstrukcje();

        if(czlowiek != null)
        {
            if (czlowiek.odczytajOczekiwanieNaKierunekRuchu()) {
                dodanoNowyOrganizm = false;
                wykonajTure();
                return;
            }
        }

        sortujZwierzeta();

        Vector<Zwierze> poczatkoweZwierzeta = new Vector<>(0);
        poczatkoweZwierzeta.addAll(zwierzeta);

        for (Zwierze zwierze : poczatkoweZwierzeta)
        {
            if (zwierzeNadalIstnieje(zwierze))
                zwierze.wykonajAkcje();
        }

        int liczbaRoslin = rosliny.size();
        for (int r = 0; r < liczbaRoslin; r++)
        {
            rosliny.get(r).wykonajAkcje();
        }

        oknoSymulacji.rysujSwiat();
    }


    abstract public boolean istniejeWolnePoleObok(int x, int y);

    public boolean poleWolne(int x, int y)
    {
        return plansza[y][x] == stale.wolne;
    }

    public boolean poleIstnieje(int x, int y)
    {
        return (x >= 0 && x < rozmiarX) && (y >= 0 && y < rozmiarY);
    }

    public boolean zwierzeNadalIstnieje(Zwierze poczatkoweZwierze)
    {
        for (Zwierze zwierze : zwierzeta)
        {
            if (zwierze == poczatkoweZwierze)
                return true;
        }
        return false;
    }

    public void ustawKomunikat(String komunikat)
    {
        if (komunikat.equals(""))
            this.komunikat = "";
        else
            this.komunikat += (komunikat + '\n');
    }

    public String odczytajKomunikat()
    {
        return komunikat;
    }


    abstract public void losujRuchyDoZmianyPolozenia(int[] ruchy);

    public void sortujZwierzeta()
    {
        for (int i = zwierzeta.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++)
            {
                if (zwierzeta.get(j).odczytajWiek() < zwierzeta.get(j + 1).odczytajWiek())
                    Collections.swap(zwierzeta, j, j + 1);
            }
        }

        for (int i = zwierzeta.size() - 1; i > 0; i--) {
            for (int j = 0; j < i; j++)
            {
                if (zwierzeta.get(j).odczytajInicjatywe() < zwierzeta.get(j + 1).odczytajInicjatywe())
                    Collections.swap(zwierzeta, j, j + 1);
            }
        }
    }

    public void zapiszStanSymulacji()
    {
        try
        {
            File plikZapisu = new File("zapis" + System.currentTimeMillis());
            PrintWriter pisarz = new PrintWriter(plikZapisu);

            pisarz.println(rozmiarX);
            pisarz.println(rozmiarY);

            for (Zwierze zwierze :zwierzeta)
            {
                pisarz.println(zwierze.serializuj());
            }
            for (Roslina roslina : rosliny)
            {
                pisarz.println(roslina.serializuj());
            }
            pisarz.close();
        }
        catch(Exception wyjatek)
        {
            System.out.println("Wyjatek");
        }
    }
}
