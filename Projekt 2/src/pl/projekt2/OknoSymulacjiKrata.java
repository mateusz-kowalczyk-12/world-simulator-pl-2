package pl.projekt2;

import javax.swing.*;
import java.awt.*;

public class OknoSymulacjiKrata extends OknoSymulacji
{
    public OknoSymulacjiKrata(Swiat swiat)
    {
        super(swiat);

        int width = swiat.odczytajRozmiarX()  * swiat.stale.dlugoscKrawedzi + szerokoscPrzyciskuSzybkosciAntylopy + 30 * swiat.stale.gruboscKrawedzi;
        int height = (swiat.odczytajRozmiarY() + 1) * swiat.stale.dlugoscKrawedzi + rozmiarPolaKomunikatow + 18 * swiat.stale.gruboscKrawedzi;

        setSize(width, height);
        dodajPodstawoweKomponenty();
    }


    @Override
    public void rysujSwiat()
    {
        getContentPane().removeAll();
        dodajPodstawoweKomponenty();

        for(int y = 0; y < swiat.odczytajRozmiarY(); y++)
        {
            for(int x = 0; x < swiat.odczytajRozmiarX(); x++)
            {
                dodajLewaIGornaKrawedz(x, y);

                if(y == swiat.odczytajRozmiarY() - 1)
                    dodajDolnaKrawedz(x);

                Organizm dodawanyOrganizm = swiat.dajOrganizm(x, y);

                if(dodawanyOrganizm == null)
                    dodajPrzyciskWolnegoPola(x, y);
                else
                    dodajPanelOrganizmu(x, y, dodawanyOrganizm);
            }

            dodajPrawaKrawedz(y);
        }

        validate();
        repaint();
    }


    @Override
    protected void dodajPodstawoweKomponenty()
    {
        super.dodajPodstawoweKomponenty();

        panelWidokuSwiata.setBounds(0, 0, swiat.odczytajRozmiarX() * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi, swiat.odczytajRozmiarY() * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi);
        add(panelWidokuSwiata);

        JScrollPane przewijanePoleKomunikatow = new JScrollPane(poleKomunikatow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        przewijanePoleKomunikatow.setBounds(0, swiat.odczytajRozmiarY() * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi + 2 * swiat.stale.gruboscKrawedzi, swiat.odczytajRozmiarX() * swiat.stale.dlugoscKrawedzi + szerokoscPrzyciskuSzybkosciAntylopy + 12, rozmiarPolaKomunikatow);

        add(przewijanePoleKomunikatow);
    }

    @Override
    protected void dodajPrzyciskiDoOczekiwaniaNaKontynuowanie()
    {
        super.dodajPrzyciskiDoOczekiwaniaNaKontynuowanie();

        przyciskKontynuowania.setBounds(swiat.odczytajRozmiarX() * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi + 10, 10, 120, 20);
        add(przyciskKontynuowania);

        przyciskZapisywania.setBounds(swiat.odczytajRozmiarX() * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi + 10, 40, 100, 20);
        add(przyciskZapisywania);
    }

    @Override
    protected void dodajPrzyciskWolnegoPola(int x, int y)
    {
        PrzyciskWolnegoPola przyciskWolnegoPola = new PrzyciskWolnegoPola(x, y, swiat, this);
        add(przyciskWolnegoPola);
    }

    @Override
    protected void dodajPanelOrganizmu(int x, int y, Organizm dodawanyOrganizm)
    {
        super.dodajPanelOrganizmu(x, y, dodawanyOrganizm);

        panelAktualnegoOrganizmu.setBounds(x * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi, y * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi, swiat.stale.wymiarOrganizmu, swiat.stale.wymiarOrganizmu);
        add(panelAktualnegoOrganizmu);
    }


    private void dodajLewaIGornaKrawedz(int x, int y)
    {
        JPanel krawedzLewa = new JPanel();

        krawedzLewa.setBounds(x * swiat.stale.dlugoscKrawedzi, y * swiat.stale.dlugoscKrawedzi, swiat.stale.gruboscKrawedzi, swiat.stale.dlugoscKrawedzi);
        krawedzLewa.setVisible(true);
        krawedzLewa.setBackground(new Color(55, 40, 10));

        add(krawedzLewa);

        JPanel krawedzGorna = new JPanel();

        krawedzGorna.setBounds(x * swiat.stale.dlugoscKrawedzi, y * swiat.stale.dlugoscKrawedzi, swiat.stale.dlugoscKrawedzi, swiat.stale.gruboscKrawedzi);
        krawedzGorna.setVisible(true);
        krawedzGorna.setBackground(new Color(55, 40, 10));

        add(krawedzGorna);
    }

    private void dodajPrawaKrawedz(int y)
    {
        JPanel krawedzPrawa = new JPanel();

        krawedzPrawa.setBounds(swiat.odczytajRozmiarX() * swiat.stale.dlugoscKrawedzi, y * swiat.stale.dlugoscKrawedzi, swiat.stale.gruboscKrawedzi, swiat.stale.dlugoscKrawedzi);
        krawedzPrawa.setVisible(true);
        krawedzPrawa.setBackground(new Color(55, 40, 10));

        add(krawedzPrawa);
    }

    private void dodajDolnaKrawedz(int x)
    {
        JPanel krawedzDolna = new JPanel();

        krawedzDolna.setBounds(x * swiat.stale.dlugoscKrawedzi, swiat.odczytajRozmiarY() * swiat.stale.dlugoscKrawedzi, swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi, swiat.stale.gruboscKrawedzi);
        krawedzDolna.setVisible(true);
        krawedzDolna.setBackground(new Color(55, 40, 10));

        add(krawedzDolna);
    }

    @Override
    public void dodajPrzyciskSzybkosciAntylopy()
    {
        super.dodajPrzyciskSzybkosciAntylopy();

        przyciskSzybkosciAntylopy.setBounds(swiat.odczytajRozmiarX() * swiat.stale.dlugoscKrawedzi + swiat.stale.gruboscKrawedzi + 10, 10, szerokoscPrzyciskuSzybkosciAntylopy, 20);

        add(przyciskSzybkosciAntylopy);
        repaint();
    }
}
