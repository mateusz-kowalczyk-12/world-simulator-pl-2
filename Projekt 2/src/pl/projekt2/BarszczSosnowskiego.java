package pl.projekt2;

public class BarszczSosnowskiego extends Roslina
{
    public BarszczSosnowskiego(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 10;

        symbol = swiat.stale.barszczSosnowskiego;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public void wykonajAkcje()
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

            Organizm organizm = swiat.dajOrganizm(x + wzgledneX, y + wzgledneY);

            if (organizm == null)
                continue;
            if (organizm instanceof Zwierze)
            {
                swiat.ustawKomunikat(stworzKomunikat(organizm, "zabity"));
                swiat.usunOrganizm(organizm);
            }
        }

        super.wykonajAkcje();
    }


    @Override
    public Organizm stworzPotomka()
    {
        return new BarszczSosnowskiego(nowyX, nowyY, swiat);
    }

    @Override
    public char wykonajKolizje(Organizm napastnik)
    {
        return swiat.stale.zatrucie;
    }
}
