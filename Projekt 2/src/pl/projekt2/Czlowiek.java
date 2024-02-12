package pl.projekt2;

import java.util.Random;

import static java.lang.Thread.sleep;

public class Czlowiek extends Zwierze
{
    private int pozostaloSzybkosciAntylopy;

    private boolean oczekiwanieNaKierunekRuchu;
    private boolean[] strzalkiWcisniete;


    public Czlowiek(int x, int y, Swiat swiat)
    {
        super(x, y, swiat);

        sila = 5;
        inicjatywa = 4;

        symbol = swiat.stale.czlowiek;

        swiat.ustawSymbol(x, y, symbol);

        pozostaloSzybkosciAntylopy = -5;

        oczekiwanieNaKierunekRuchu = false;
        strzalkiWcisniete = new boolean[]{false, false, false, false};
    }

    public Czlowiek(int x, int y, int sila, int wiek, int pozostaloSzybkosciAntylopy, Swiat swiat)
    {
        super(x, y, sila, wiek, swiat);

        inicjatywa = 4;

        symbol = swiat.stale.czlowiek;

        swiat.ustawSymbol(x, y, symbol);

        this.pozostaloSzybkosciAntylopy = pozostaloSzybkosciAntylopy;

        oczekiwanieNaKierunekRuchu = false;
        strzalkiWcisniete = new boolean[]{false, false, false, false};
    }


    @Override
    public void wykonajAkcje()
    {
        if (swiat.dajOrganizm(nowyX, nowyY) == null)
            zmienPole();
        else
            przeprowadzWalke();

        if (pozostaloSzybkosciAntylopy > -5)
            pozostaloSzybkosciAntylopy--;
    }

    @Override
    public char wykonajKolizje(Organizm napastnik)
    {
        return standardowaObrona(napastnik);
    }

    @Override
    public Organizm stworzPotomka()
    {
        return new Czlowiek(nowyX, nowyY, swiat);
    }

    public void wczytajInstrukcje()
    {
        if(pozostaloSzybkosciAntylopy == -5)
            swiat.dajOknoSymulacji().dodajPrzyciskSzybkosciAntylopy();

        resetujStrzalkiWcisniete();
        oczekiwanieNaKierunekRuchu = true;

        while(!ustawionoNowaPozycje())
        {
            ustawKierunekRuchu();

            try { sleep(1); }
            catch(Exception wyjatek) { System.out.println(wyjatek); }

            if(swiat.odczytajDodanoNowyOrganizm())
                break;
        }

        if(!swiat.odczytajDodanoNowyOrganizm())
            oczekiwanieNaKierunekRuchu = false;
    }


    public void ustawPozostaloSzybkosciAntylopy(int pozostaloSzybkosciAntylopy)
    {
        this.pozostaloSzybkosciAntylopy = pozostaloSzybkosciAntylopy;

        swiat.dajOknoSymulacji().usunPrzyciskSzybkosciAntylopy();
    }

    public void ustawStrzalkeWcisnieta(String kierunek)
    {
        if(!oczekiwanieNaKierunekRuchu)
            return;

        int liczbaStrzalekWcisnietych = 0;
        for(boolean czyWcisnieta : strzalkiWcisniete)
        {
            if(czyWcisnieta)
                liczbaStrzalekWcisnietych++;
        }

        if(liczbaStrzalekWcisnietych == 2)
            resetujStrzalkiWcisniete();

        switch (kierunek)
        {
            case "UP" -> strzalkiWcisniete[0] = true;
            case "RIGHT" -> strzalkiWcisniete[1] = true;
            case "DOWN" -> strzalkiWcisniete[2] = true;
            case "LEFT" -> strzalkiWcisniete[3] = true;
        }
    }

    public void ustawKierunekRuchu()
    {
        Random los = new Random();
        int ruch;

        if ((pozostaloSzybkosciAntylopy >= 4) || (pozostaloSzybkosciAntylopy >= 1 && pozostaloSzybkosciAntylopy <= 3 && los.nextInt(2) == 0))
            ruch = 2;
        else
            ruch = 1;

        if(swiat instanceof SwiatSzesciokaty)
        {
            if(strzalkiWcisniete[0])
            {
                if(strzalkiWcisniete[1])
                {
                    if (swiat.poleIstnieje(x + ruch, y - ruch))
                    {
                        nowyX = x + ruch;
                        nowyY = y - ruch;
                    }
                }
                if(strzalkiWcisniete[3])
                {
                    if (swiat.poleIstnieje(x, y - ruch))
                    {
                        nowyX = x;
                        nowyY = y - ruch;
                    }
                }
            }
            else if(strzalkiWcisniete[2])
            {
                if(strzalkiWcisniete[1])
                {
                    if (swiat.poleIstnieje(x, y + ruch))
                    {
                        nowyX = x;
                        nowyY = y + ruch;
                    }
                }
                if(strzalkiWcisniete[3])
                {
                    if (swiat.poleIstnieje(x - ruch, y + ruch))
                    {
                        nowyX = x - ruch;
                        nowyY = y + ruch;
                    }
                }
            }
            else
            {
                if(strzalkiWcisniete[1])
                {
                    if (swiat.poleIstnieje(x + ruch, y))
                    {
                        nowyX = x + ruch;
                        nowyY = y;
                    }
                }
                if(strzalkiWcisniete[3])
                {
                    if (swiat.poleIstnieje(x - ruch, y))
                    {
                        nowyX = x - ruch;
                        nowyY = y;
                    }
                }
            }
        }
        if (swiat instanceof SwiatKrata)
        {
            if (strzalkiWcisniete[0])
            {
                if (swiat.poleIstnieje(x, y - ruch))
                {
                    nowyX = x;
                    nowyY = y - ruch;
                }
            }
            if (strzalkiWcisniete[2])
            {
                if (swiat.poleIstnieje(x, y + ruch))
                {
                    nowyX = x;
                    nowyY = y + ruch;
                }
            }
            if(strzalkiWcisniete[1])
            {
                if (swiat.poleIstnieje(x + ruch, y))
                {
                    nowyX = x + ruch;
                    nowyY = y;
                }
            }
            if(strzalkiWcisniete[3])
            {
                if (swiat.poleIstnieje(x - ruch, y))
                {
                    nowyX = x - ruch;
                    nowyY = y;
                }
            }
        }
    }

    public void resetujStrzalkiWcisniete()
    {
        for(int i = 0; i < 4; i++)
        {
            strzalkiWcisniete[i] = false;
        }
    }

    public boolean ustawionoNowaPozycje()
    {
        return nowyX != swiat.stale.testowaWartoscPoczatkowa;
    }

    public boolean odczytajOczekiwanieNaKierunekRuchu()
    {
        return oczekiwanieNaKierunekRuchu;
    }


    @Override
    String serializuj()
    {
        String zserializowany =super.serializuj();
        zserializowany += '\n';
        zserializowany += pozostaloSzybkosciAntylopy;

        return zserializowany;
    }
}
