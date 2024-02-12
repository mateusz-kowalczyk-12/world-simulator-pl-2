package pl.projekt2;

public class Lis extends Zwierze
{
    public Lis(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 3;
        inicjatywa = 7;

        symbol = swiat.stale.lis;

        swiat.ustawSymbol(x, y, symbol);
    }

    public Lis(int x, int y, int sila, int wiek, Swiat swiat)
    {
        super(x, y, sila, wiek, swiat);

        inicjatywa = 7;

        symbol = swiat.stale.lis;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public void wykonajAkcje()
    {
        if (daSieRuszyc())
        {
            while (true)
            {
                znajdzNowePolozenie();
                Organizm zaatakowany = swiat.dajOrganizm(nowyX, nowyY);

                if (zaatakowany == null)
                {
                    zmienPole();
                    break;
                }
                if (zaatakowany.odczytajSile() <= sila)
                {
                    przeprowadzWalke();
                    break;
                }
            }
        }
    }


    @Override
    public Organizm stworzPotomka()
    {
        return new Lis(nowyX, nowyY, swiat);
    }

    public boolean daSieRuszyc()
    {
        for (int numerPola = 0; numerPola < 6; numerPola++)
        {
            if(swiat instanceof SwiatKrata && numerPola == 4)
                break;

            int wzgledneX = swiat.stale.testowaWartoscPoczatkowa;
            int wzgledneY = swiat.stale.testowaWartoscPoczatkowa;

            switch (numerPola)
            {
                case 0 -> {
                    wzgledneX = 0;
                    wzgledneY = 1;
                }
                case 1 -> {
                    wzgledneX = 1;
                    wzgledneY = 0;
                }
                case 2 -> {
                    wzgledneX = 0;
                    wzgledneY = -1;
                }
                case 3 -> {
                    wzgledneX = -1;
                    wzgledneY = 0;
                }
                case 4 -> {
                    wzgledneX = 1;
                    wzgledneY = -1;
                }
                case 5 -> {
                    wzgledneX = -1;
                    wzgledneY = 1;
                }
            }

            if (swiat.poleIstnieje(x + wzgledneX, y + wzgledneY))
            {
                Organizm kandydatDoZaatakowania = swiat.dajOrganizm(x + wzgledneX, y + wzgledneY);

                if (kandydatDoZaatakowania == null)
                    return true;
                else
                {
                    if (kandydatDoZaatakowania.odczytajSile() <= sila)
                        return true;
                }
            }
        }

        return false;
    }
}
