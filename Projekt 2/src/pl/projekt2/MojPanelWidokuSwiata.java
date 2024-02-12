package pl.projekt2;

import javax.swing.*;
import java.awt.*;

import static java.lang.Math.sqrt;

public class MojPanelWidokuSwiata extends JPanel
{
    Swiat swiat;


    public MojPanelWidokuSwiata(Swiat swiat)
    {
        this.swiat = swiat;

        setVisible(true);
        setBackground(new Color(223, 255, 23, 50));
    }

    @Override
    public void paint(Graphics grafika)
    {
        super.paint(grafika);

        if(swiat instanceof SwiatKrata)
            return;

        Graphics2D grafika2D = (Graphics2D)grafika;
        grafika2D.setStroke(new BasicStroke(swiat.stale.gruboscKrawedzi));

        for(int y = 0; y < swiat.odczytajRozmiarY(); y++)
        {
            rysujLinieLewa(grafika2D, y);

            for(int x = 0; x < swiat.odczytajRozmiarX(); x++)
            {
                rysujLinieGorna(grafika2D, x, y);
                rysujLiniePrawa(grafika2D, x, y);
            }
        }

        for(int x = 0; x < swiat.odczytajRozmiarX(); x++)
        {
            rysujLinieGorna(grafika2D, x, swiat.odczytajRozmiarY());
        }
    }


    private void rysujLinieGorna(Graphics2D grafika2D, int x, int y)
    {
        int x1 = (int)((double)x * 2 / 3 * swiat.stale.wymiarOrganizmu * sqrt(3) + (double)y / 3 * swiat.stale.wymiarOrganizmu * sqrt(3));
        int y1 = (int)((double)swiat.stale.wymiarOrganizmu / 3 + (double)y * swiat.stale.wymiarOrganizmu);
        int x2 = x1 + (int)((double)swiat.stale.wymiarOrganizmu / 3 * sqrt(3));
        int y2 = y1 - (int)((double)swiat.stale.wymiarOrganizmu / 3);

        grafika2D.drawLine(x1, y1, x2, y2);

        x1 = x2 + (int)((double)swiat.stale.wymiarOrganizmu / 3 * sqrt(3));
        y1 = y2 + (int)((double)swiat.stale.wymiarOrganizmu / 3);

        grafika2D.drawLine(x2, y2, x1, y1);
    }

    private void rysujLiniePrawa(Graphics2D grafika2D, int x, int y)
    {
        int x1 = (int)((double)(x + 1) * 2 / 3 * sqrt(3) * swiat.stale.wymiarOrganizmu + (double)y / 3 * sqrt(3) * swiat.stale.wymiarOrganizmu);
        int y1 = (int)((double)swiat.stale.wymiarOrganizmu / 3 + (double)y * swiat.stale.wymiarOrganizmu);
        int y2 = (int)(y1 + (double)2 / 3 * swiat.stale.wymiarOrganizmu);

        grafika2D.drawLine(x1, y1, x1, y2);
    }

    private void rysujLinieLewa(Graphics2D graphika2D, int y)
    {
        int x1 = (int)((double)y / 3 * sqrt(3) * swiat.stale.wymiarOrganizmu);
        int y1 = (int)((double)swiat.stale.wymiarOrganizmu / 3 + y * swiat.stale.wymiarOrganizmu);
        int x2 = x1;
        int y2 = (int)(y1 + (double)2 / 3 * swiat.stale.wymiarOrganizmu);

        graphika2D.drawLine(x1, y1, x2, y2);

        x1 = (int)(x2 + (double)swiat.stale.wymiarOrganizmu / 3 * sqrt(3));
        y1 = (int)(y2 + (double)swiat.stale.wymiarOrganizmu / 3);

        graphika2D.drawLine(x2, y2, x1, y1);
    }
}
