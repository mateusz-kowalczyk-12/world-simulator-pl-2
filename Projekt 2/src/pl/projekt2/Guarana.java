package pl.projekt2;

public class Guarana extends Roslina
{
    Guarana(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 0;

        symbol = swiat.stale.guarana;

        swiat.ustawSymbol(x, y, symbol);
    }


    @Override
    public Organizm stworzPotomka()
    {
        return new Guarana(nowyX, nowyY, swiat);
    }

    @Override
    public char wykonajKolizje(Organizm napastnik)
    {
        napastnik.zwiekszSile(3);
        return super.wykonajKolizje(napastnik);
    }
}
