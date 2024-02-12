package pl.projekt2;

abstract public class Zwierze extends Organizm
{
    protected int wiek;


    Zwierze(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        wiek = 0;
    }

    Zwierze(int x, int y, int sila, int wiek, Swiat swiat)
    {
        super(x, y, swiat);

        this.sila = sila;

        this.wiek = wiek;
    }


    @Override
    public void wykonajAkcje()
    {
        znajdzNowePolozenie();

        if (swiat.dajOrganizm(nowyX, nowyY) == null)
        {
            zmienPole();
        }
        else
            przeprowadzWalke();
    }

    public void przeprowadzWalke()
    {
        Organizm zaatakowany = swiat.dajOrganizm(nowyX, nowyY);

        char wynikWalki = zaatakowany.wykonajKolizje(this);
        konsekwencjaWalki(zaatakowany, wynikWalki);
    }

    public void konsekwencjaWalki(Organizm zaatakowany, char wynikWalki)
    {
        if (wynikWalki == swiat.stale.wygralNapastnik)
        {
            swiat.ustawKomunikat(stworzKomunikat(zaatakowany, "zabity"));
            swiat.usunOrganizm(zaatakowany);
            zmienPole();
        }
        if (wynikWalki == swiat.stale.wygralZaatakowany)
        {
            swiat.ustawKomunikat(stworzKomunikat(zaatakowany, "zabil"));
            zaatakowany.ustawNowyX(swiat.stale.testowaWartoscPoczatkowa);
            zaatakowany.ustawNowyY(swiat.stale.testowaWartoscPoczatkowa);
            swiat.usunOrganizm(this);
        }
        if (wynikWalki == swiat.stale.odbicieAtaku)
        {
            swiat.ustawKomunikat(stworzKomunikat(zaatakowany, "odbil"));
            nowyX = swiat.stale.testowaWartoscPoczatkowa;
            nowyY = swiat.stale.testowaWartoscPoczatkowa;
        }
        if (wynikWalki == swiat.stale.ucieczkaPrzedWalka)
        {
            swiat.ustawKomunikat(stworzKomunikat(zaatakowany, "uciekl"));
            zmienPole();
        }
        if (wynikWalki == swiat.stale.zjedzenie)
        {
            swiat.ustawKomunikat(stworzKomunikat(zaatakowany, "zjedzony"));
            swiat.usunOrganizm(zaatakowany);
            zmienPole();
        }
        if (wynikWalki == swiat.stale.zatrucie)
        {
            swiat.ustawKomunikat(stworzKomunikat(zaatakowany, "zatrul"));
            swiat.usunOrganizm(zaatakowany);
            swiat.usunOrganizm(this);
        }
    }


    @Override
    public char wykonajKolizje(Organizm napastnik)
    {
        if (napastnik.getClass() == this.getClass())
            return rozmnozSie(napastnik);
	    else
            return standardowaObrona(napastnik);
    }

    public char rozmnozSie(Organizm inicjator)
    {
        if (swiat.istniejeWolnePoleObok(x, y))
        {
            do
            {
                znajdzNowePolozenie();
            }
            while (!swiat.poleWolne(nowyX, nowyY));
        }
        else
        {
            int xInicjatora = inicjator.odczytajX();
            int yInicjatora = inicjator.odczytajY();

            if (swiat.istniejeWolnePoleObok(xInicjatora, yInicjatora))
            {
                int[] ruchy = {swiat.stale.testowaWartoscPoczatkowa, swiat.stale.testowaWartoscPoczatkowa};
                do
                {
                    do
                    {
                        swiat.losujRuchyDoZmianyPolozenia(ruchy);
                    }
                    while (!swiat.poleIstnieje(xInicjatora + ruchy[0], yInicjatora + ruchy[1]));
                }
                while (!swiat.poleWolne(xInicjatora + ruchy[0], yInicjatora + ruchy[1]));

                nowyX = xInicjatora + ruchy[0];
                nowyY = yInicjatora + ruchy[1];
            }
        }

        if (nowyX != swiat.stale.testowaWartoscPoczatkowa)
        {
            Zwierze potomek = (Zwierze)stworzPotomka();
            swiat.dodajZwierze(potomek);

            return swiat.stale.rozmnozenie;
        }

        return swiat.stale.probaRozmnozenia;
    }

    public char standardowaObrona(Organizm napastnik)
    {
        if (napastnik.odczytajSile() < sila)
            return swiat.stale.wygralZaatakowany;

        return swiat.stale.wygralNapastnik;
    }


    public int odczytajWiek()
    {
        return wiek;
    }

    public void zwiekszWiek()
    {
        wiek++;
    }

    public void zmienPole()
    {
        swiat.ustawSymbol(x, y, swiat.stale.wolne);
        x = nowyX;
        y = nowyY;
        swiat.ustawSymbol(x, y, symbol);

        nowyX = swiat.stale.testowaWartoscPoczatkowa;
        nowyY = swiat.stale.testowaWartoscPoczatkowa;
    }


    @Override
    String serializuj()
    {
        String zserializowany = super.serializuj();
        zserializowany += '\n';
        zserializowany +=sila;
        zserializowany += '\n';
        zserializowany += wiek;

        return zserializowany;
    }
}
