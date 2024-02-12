package pl.projekt2;

public class Wilk extends Zwierze
{
    public Wilk(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 9;
        inicjatywa = 5;

        symbol = swiat.stale.wilk;

        swiat.ustawSymbol(x, y, symbol);
    }

    public Wilk(int x, int y, int sila, int wiek, Swiat swiat)
    {
        super(x, y, sila, wiek, swiat);

        inicjatywa = 5;

        symbol = swiat.stale.wilk;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public Organizm stworzPotomka()
    {
        return new Wilk(nowyX, nowyY, swiat);
    }
}
