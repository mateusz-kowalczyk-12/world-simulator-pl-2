package pl.projekt2;

public class Trawa extends Roslina
{
    public Trawa(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 0;

        symbol = swiat.stale.trawa;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public Organizm stworzPotomka()
    {
        return new Trawa(nowyX, nowyY, swiat);
    }
}
