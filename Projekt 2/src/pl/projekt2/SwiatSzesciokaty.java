package pl.projekt2;

import java.util.Random;

public class SwiatSzesciokaty extends Swiat
{
    @Override
    public boolean istniejeWolnePoleObok(int x, int y)
    {
        for (int numerPola = 0; numerPola < 6; numerPola++)
        {
            int wzgledneX = stale.testowaWartoscPoczatkowa;
            int wzgledneY = stale.testowaWartoscPoczatkowa;

            switch (numerPola)
            {
                case 0 -> {
                    wzgledneX = 1;
                    wzgledneY = -1;
                }
                case 1 -> {
                    wzgledneX = 1;
                    wzgledneY = 0;
                }
                case 2 -> {
                    wzgledneX = 0;
                    wzgledneY = 1;
                }
                case 3 -> {
                    wzgledneX = -1;
                    wzgledneY = 1;
                }
                case 4 -> {
                    wzgledneX = -1;
                    wzgledneY = 0;
                }
                case 5 -> {
                    wzgledneX = 0;
                    wzgledneY = -1;
                }
            }

            if (poleIstnieje(x + wzgledneX, y + wzgledneY))
            {
                if (plansza[y + wzgledneY][x + wzgledneX] == stale.wolne)
                    return true;
            }
        }

        return false;
    }


    @Override
    public void losujRuchyDoZmianyPolozenia(int[] ruchy)
    {
        Random los = new Random();
        int kierunek = los.nextInt(3);

        switch(kierunek)
        {
            case 0 -> { //wzdluz osi Y
                ruchy[0] = 0;
                ruchy[1] = los.nextInt(2);

                if (ruchy[1] == 0)
                    ruchy[1] = 1;
                else
                    ruchy[1] = -1;
            }
            case 1 -> { //wzgluz osi X
                ruchy[1] = 0;
                ruchy[0] = los.nextInt(2);

                if (ruchy[0] == 0)
                    ruchy[0] = 1;
                else
                    ruchy[0] = -1;
            }
            case 2 -> { //na ukos
                if(los.nextInt(2) == 0)
                {
                    ruchy[0] = 1;
                    ruchy[1] = -1;
                }
                else
                {
                    ruchy[0] = -1;
                    ruchy[1] = 1;
                }
            }
        }
    }
}
