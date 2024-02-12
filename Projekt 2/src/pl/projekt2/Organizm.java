package pl.projekt2;

public abstract class Organizm
{
    protected int sila, inicjatywa;

    protected int x, y;
    public int nowyX, nowyY;

    protected char symbol;

    protected final Swiat swiat;


    public Organizm(int x, int y, Swiat swiat)
    {
        this.swiat = swiat;

        this.x = x;
        this.y = y;

        nowyX = swiat.stale.testowaWartoscPoczatkowa;
        nowyY = swiat.stale.testowaWartoscPoczatkowa;
    }


    abstract public void wykonajAkcje();

    abstract public char wykonajKolizje(Organizm napastnik);


    protected void znajdzNowePolozenie()
    {
        int[] ruchy = {/*x: */swiat.stale.testowaWartoscPoczatkowa, /*y: */swiat.stale.testowaWartoscPoczatkowa};
        do
        {
            swiat.losujRuchyDoZmianyPolozenia(ruchy);
        }
        while (!swiat.poleIstnieje(x + ruchy[0], y + ruchy[1]));

        nowyX = x + ruchy[0];
        nowyY = y + ruchy[1];
    }


    String stworzKomunikat(Organizm podmiot, String haslo)
    {
        String nowyKomunikat = "";

        if (!haslo.equals("zjedzony"))
            if (haslo.equals("zatrul"))
                nowyKomunikat = "Zjedzony ";
            else
                nowyKomunikat = "Zaatakowany ";

        nowyKomunikat += podmiot.odczytajSymbol();
        nowyKomunikat += " na (";
        nowyKomunikat += podmiot.odczytajX();
        nowyKomunikat += ", ";
        nowyKomunikat += podmiot.odczytajY();

        if (haslo.equals("odbil"))
            nowyKomunikat += ") odbil atak ";
        if (haslo.equals("zabity"))
            nowyKomunikat += ") zostal zabity przez ";
        if (haslo.equals("zabil"))
            nowyKomunikat += ") zabil ";
        if (haslo.equals("uciekl"))
            nowyKomunikat += ") uciekl przed ";
        if (haslo.equals("zatrul"))
            nowyKomunikat += ") zatrul ";
        if (haslo.equals("zjedzony"))
            nowyKomunikat += ") zostal zjedzony przez ";

        nowyKomunikat += symbol;
        nowyKomunikat += " z (";
        nowyKomunikat += x;
        nowyKomunikat += ", ";
        nowyKomunikat += y;
        nowyKomunikat += ')';

        return nowyKomunikat;
    }

    abstract public Organizm stworzPotomka();


    public int odczytajX()
    {
        return x;
    }

    public void ustawNowyX(int nowyX)
    {
        this.nowyX = nowyX;
    }

    public int odczytajY()
    {
        return y;
    }

    public void ustawNowyY(int nowyY)
    {
        this.nowyY = nowyY;
    }

    public int odczytajSile()
    {
        return sila;
    }

    public int odczytajInicjatywe()
    {
        return inicjatywa;
    }

    public void zwiekszSile(int zmiana)
    {
        sila += zmiana;
    }

    public char odczytajSymbol()
    {
        return symbol;
    }


    String serializuj()
    {
        String zserializowany = "";

        zserializowany += this.getClass().getSimpleName();
        zserializowany += '\n';

        zserializowany += x;
        zserializowany += '\n';
        zserializowany += y;

        return zserializowany;
    }
}
