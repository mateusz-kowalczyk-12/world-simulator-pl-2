package pl.projekt2;

public class WilczeJagody extends Roslina
{
    public WilczeJagody(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 99;

        symbol = swiat.stale.wilczeJagody;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public Organizm stworzPotomka()
    {
        return new WilczeJagody(nowyX, nowyY, swiat);
    }

    @Override
    public char wykonajKolizje(Organizm napastnik)
    {
        return swiat.stale.zatrucie;
    }
}
