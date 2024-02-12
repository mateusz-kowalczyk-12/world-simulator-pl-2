package pl.projekt2;

public class Mlecz extends Roslina
{
    public Mlecz(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 0;

        symbol = swiat.stale.mlecz;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public void wykonajAkcje()
    {
        for (int i = 0; i < 3; i++)
        {
            super.wykonajAkcje();
        }
    }


    @Override
    public Organizm stworzPotomka()
    {
        return new Mlecz(nowyX, nowyY, swiat);
    }
}
