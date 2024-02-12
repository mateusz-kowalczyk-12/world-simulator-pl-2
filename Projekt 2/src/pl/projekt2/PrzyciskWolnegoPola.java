package pl.projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.sqrt;

public class PrzyciskWolnegoPola extends JButton
{
    private final Swiat swiat;

    private final int x, y;

    private final OknoSymulacji oknoSymulacji;

    private class SluchaczPrzyciskuWolnegoPola implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            new OknoDodawaniaOrganizmu(x, y, swiat);
        }
    }


    public PrzyciskWolnegoPola(int x, int y, Swiat swiat, OknoSymulacji oknoSymulacji)
     {
        this.swiat = swiat;

        this.x = x;
        this.y = y;

        this.oknoSymulacji = oknoSymulacji;

        int xPrzycisku, yPrzycisku;
        if(oknoSymulacji instanceof OknoSymulacjiKrata)
        {
            xPrzycisku = x * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi;
            yPrzycisku = y * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi;
            setBounds(xPrzycisku, yPrzycisku, swiat.stale.wymiarOrganizmu, swiat.stale.wymiarOrganizmu);
        }
        else
        {
            xPrzycisku = (int)((2 * sqrt(3) - 3) / 6 * swiat.stale.wymiarOrganizmu + (double)x * 2 / 3 * sqrt(3) * swiat.stale.wymiarOrganizmu + (double)y * sqrt(3) / 3 * swiat.stale.wymiarOrganizmu) + swiat.stale.wymiarOrganizmu / 10;
            yPrzycisku = (int)((double)swiat.stale.wymiarOrganizmu / 6 + y * swiat.stale.wymiarOrganizmu) + swiat.stale.wymiarOrganizmu / 10;
            setBounds(xPrzycisku, yPrzycisku, swiat.stale.wymiarOrganizmu * 9 / 10, swiat.stale.wymiarOrganizmu * 9 / 10);
        }

        setBackground(new Color(238, 255, 163));
        addActionListener(new SluchaczPrzyciskuWolnegoPola());
        setVisible(true);
    }
}
