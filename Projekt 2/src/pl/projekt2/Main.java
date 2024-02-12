package pl.projekt2;

import static java.lang.Thread.sleep;

public class Main
{
    public static void main(String[] args)
    {
        OknoWyboruKsztaltuPlanszy oknoWyboruKsztaltuPlanszy = new OknoWyboruKsztaltuPlanszy();
        int ksztalt = -1;
        do
        {
            try { sleep(1); }
            catch(Exception wyjatek) { System.out.println(wyjatek);}
            ksztalt = oknoWyboruKsztaltuPlanszy.odczytajWybranyKsztalt();
        }
        while(ksztalt == -1);

        Swiat swiat;
        if(ksztalt == 0)
            swiat = new SwiatKrata();
        else
            swiat = new SwiatSzesciokaty();

        OknoMenu oknoMenu = new OknoMenu(swiat);

        int wczytacStanSymulacji;
        oknoMenu.wykonajMenu();
        do
        {
            wczytacStanSymulacji = oknoMenu.odczytajWczytacStanSymulacji();
            try { sleep(1); }
            catch(Exception wyjatek) { System.out.println(wyjatek);}
        }
        while(wczytacStanSymulacji == -1);

        if (wczytacStanSymulacji == 1)
            oknoMenu.wczytajStanSymulacji();
        else
            oknoMenu.stworzNowaSymulacje();

        oknoMenu.dispose();

        OknoSymulacji oknoSymulacji;
        if(ksztalt == 0)
            oknoSymulacji = new OknoSymulacjiKrata(swiat);
        else
            oknoSymulacji = new OknoSymulacjiSzesciokaty(swiat);

        swiat.ustawOknoSymulacji(oknoSymulacji);

        do
        {
            swiat.wykonajTure();
        }
        while (oknoSymulacji.kontynuowac());
    }
}
