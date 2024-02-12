package pl.projekt2;

import java.util.Random;

public class SwiatKrata extends Swiat
{
    @Override
    public boolean istniejeWolnePoleObok(int x, int y)
    {
        for (int numerPola = 0; numerPola < 4; numerPola++)
        {
            int wzgledneX = stale.testowaWartoscPoczatkowa;
            int wzgledneY = stale.testowaWartoscPoczatkowa;

            switch (numerPola)
            {
                case 0 -> {
                    wzgledneX = 0;
                    wzgledneY = 1;
                }
                case 1 -> {
                    wzgledneX = 1;
                    wzgledneY = 0;
                }
                case 2 -> {
                    wzgledneX = 0;
                    wzgledneY = -1;
                }
                case 3 -> {
                    wzgledneX = -1;
                    wzgledneY = 0;
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
        Random rand = new Random();
        if (rand.nextInt(2) == 0)
        {
            ruchy[0] = 0;
            ruchy[1] = rand.nextInt(2);

            if (ruchy[1] == 0)
                ruchy[1] = 1;
            else
                ruchy[1] = -1;
        }
        else
        {
            ruchy[1] = 0;
            ruchy[0] = rand.nextInt(2);

            if (ruchy[0] == 0)
                ruchy[0] = 1;
            else
                ruchy[0] = -1;
        }
    }
}
