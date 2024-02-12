package pl.projekt2;

import java.util.Random;

public class Zolw extends Zwierze
{
    public Zolw(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 2;
        inicjatywa = 1;

        symbol = swiat.stale.zolw;

        swiat.ustawSymbol(x, y, symbol);
    }
    public Zolw(int x, int y, int sila, int wiek, Swiat swiat)
    {
        super(x, y, sila, wiek, swiat);

        inicjatywa = 1;

        symbol = swiat.stale.zolw;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public void wykonajAkcje()
    {
        if (!ruszycSie())
            return;

        super.wykonajAkcje();
    }

    @Override
    public char wykonajKolizje(Organizm napastnik)
    {
        if (odbilAtak(napastnik))
            return swiat.stale.odbicieAtaku;

        return super.wykonajKolizje(napastnik);
    }

    @Override
    public Organizm stworzPotomka()
    {
        return new Zolw(nowyX, nowyY, swiat);
    }


    boolean odbilAtak(Organizm napastnik)
    {
        return napastnik.odczytajSile() < 5 && !(napastnik instanceof Zolw);
    }

    boolean ruszycSie()
    {
        Random los = new Random();
        return los.nextInt(4) == 3;
    }
}
