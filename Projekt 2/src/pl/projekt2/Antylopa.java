package pl.projekt2;

import java.util.Random;

public class Antylopa extends Zwierze
{
    public Antylopa(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 4;
        inicjatywa = 4;

        symbol = swiat.stale.antylopa;

        swiat.ustawSymbol(x, y, symbol);
    }

    public Antylopa(int x, int y, int sila, int wiek, Swiat swiat)
    {
        super(x, y, sila, wiek, swiat);

        inicjatywa = 4;

        symbol = swiat.stale.antylopa;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public void wykonajAkcje()
    {
        znajdzNowePolozenie();
        Organizm zaatakowany = swiat.dajOrganizm(nowyX, nowyY);

        if (zaatakowany == null)
            zmienPole();
        else
            przeprowadzWalke();
    }

    @Override
    public char wykonajKolizje(Organizm napastnik)
    {
        Random los = new Random();
        if (los.nextInt(1) == 0 && !(napastnik instanceof Antylopa))
        {
            if (swiat.istniejeWolnePoleObok(x, y))
            {
                while (true)
                {
                    super.znajdzNowePolozenie();

                    if (swiat.poleWolne(nowyX, nowyY))
                    {
                        zmienPole();
                        return swiat.stale.ucieczkaPrzedWalka;
                    }
                }
            }
        }

        return super.wykonajKolizje(napastnik);
    }

    @Override
    public Organizm stworzPotomka()
    {
        return new Antylopa(nowyX, nowyY, swiat);
    }


    protected void znajdzNowePolozenie()
    {
        Random los = new Random();
        int[] ruchy = {/*x: */swiat.stale.testowaWartoscPoczatkowa, /*y: */swiat.stale.testowaWartoscPoczatkowa};

        do
        {
            losujRuchyDoZmianyPolozenia(ruchy);
        }
        while (!swiat.poleIstnieje(x + ruchy[0], y + ruchy[1]));

        nowyX = x + ruchy[0];
        nowyY = y + ruchy[1];
    }


    public void losujRuchyDoZmianyPolozenia(int[] ruchy)
    {
        Random los = new Random();

        if(swiat instanceof SwiatKrata)
        {
            if (los.nextInt(2) == 0)
            {
                ruchy[0] = 0;
                ruchy[1] = los.nextInt(2);

                if (ruchy[1] == 0)
                    ruchy[1] = 2;
                else
                    ruchy[1] = -2;
            }
            else
            {
                ruchy[1] = 0;
                ruchy[0] = los.nextInt(2);

                if (ruchy[0] == 0)
                    ruchy[0] = 2;
                else
                    ruchy[0] = -2;
            }
        }
        else
        {
            int kierunek = los.nextInt(3);

            switch(kierunek)
            {
                case 0 -> { //wzdluz osi Y
                    ruchy[0] = 0;
                    ruchy[1] = los.nextInt(2);

                    if (ruchy[1] == 0)
                        ruchy[1] = 2;
                    else
                        ruchy[1] = -2;
                }
                case 1 -> { //wzgluz osi X
                    ruchy[1] = 0;
                    ruchy[0] = los.nextInt(2);

                    if (ruchy[0] == 0)
                        ruchy[0] = 2;
                    else
                        ruchy[0] = -2;
                }
                case 2 -> { //na ukos
                    if(los.nextInt(2) == 0)
                    {
                        ruchy[0] = 2;
                        ruchy[1] = -2;
                    }
                    else
                    {
                        ruchy[0] = -2;
                        ruchy[1] = 2;
                    }
                }
            }
        }
    }
}
