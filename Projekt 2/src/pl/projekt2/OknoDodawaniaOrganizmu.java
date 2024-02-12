package pl.projekt2;

import javax.swing.*;
import java.awt.*;

public class OknoDodawaniaOrganizmu extends JFrame
{
    private final Swiat swiat;

    private int x, y;

    public OknoDodawaniaOrganizmu(int x, int y, Swiat swiat)
    {
        this.swiat = swiat;

        this.x = x;
        this.y = y;

        setTitle("Symulator świata - Mateusz Kowalczyk, s188717");

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        setLayout(null);
        setSize(200, 320);
        setVisible(true);

        JLabel instrukcjaOkna = new JLabel("Dodaj");
        instrukcjaOkna.setFont(new Font("Courier New", Font.PLAIN, 25));
        instrukcjaOkna.setBounds(0, 0, 200, 40);
        instrukcjaOkna.setHorizontalAlignment(JLabel.CENTER);
        instrukcjaOkna.setVerticalAlignment(JLabel.TOP);
        instrukcjaOkna.setVisible(true);
        add(instrukcjaOkna);

        dodajPrzyciskiWyboruOrganizmu();
    }


    public void dodajPrzyciskiWyboruOrganizmu()
    {
        for(int numerOrganizmu = 0; numerOrganizmu < swiat.stale.liczbaRodzajowOrganizmow; numerOrganizmu++)
        {
            PrzyciskDodawaniaOrganizmu przyciskDodawaniaOrganizmu = new PrzyciskDodawaniaOrganizmu(x, y, numerOrganizmu, swiat, this);
            add(przyciskDodawaniaOrganizmu);
        }
    }
}
