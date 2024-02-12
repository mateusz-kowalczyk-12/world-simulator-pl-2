package pl.projekt2;

import java.util.Random;

abstract public class Roslina extends Organizm
{
    public Roslina(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        inicjatywa = 0;
    }


    @Override
    public void wykonajAkcje()
    {
        Random los = new Random();
        if (los.nextInt(100) < swiat.stale.prawdopodobienstwoRozprzestrzenienia)
        {
            if (swiat.istniejeWolnePoleObok(x, y))
            {
                do
                {
                    znajdzNowePolozenie();
                }
                while (!swiat.poleWolne(nowyX, nowyY));

                Organizm potomek = stworzPotomka();
                swiat.dodajRosline((Roslina)potomek);

                nowyX = swiat.stale.testowaWartoscPoczatkowa;
                nowyY = swiat.stale.testowaWartoscPoczatkowa;
            }
        }
    }

    @Override
    public char wykonajKolizje(Organizm napastnik)
    {
        return swiat.stale.zjedzenie;
    }
}
