package pl.projekt2;

public class Owca extends Zwierze
{
    public Owca(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 4;
        inicjatywa = 4;

        symbol = swiat.stale.owca;

        swiat.ustawSymbol(x, y, symbol);
    }

    public Owca(int x, int y, int sila, int wiek, Swiat swiat)
    {
        super(x, y, sila, wiek, swiat);

        inicjatywa = 4;

        symbol = swiat.stale.owca;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public Organizm stworzPotomka()
    {
        return new Owca(nowyX, nowyY, swiat);
    }
}
