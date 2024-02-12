package pl.projekt2;

import javax.swing.*;

import static java.lang.Math.sqrt;

public class OknoSymulacjiSzesciokaty extends OknoSymulacji
{
    public OknoSymulacjiSzesciokaty(Swiat swiat)
    {
        super(swiat);

        int szerokosc = (swiat.odczytajRozmiarX() + 1 + (swiat.odczytajRozmiarY() - 1) / 2) * 4 / 3 * swiat.stale.dlugoscKrawedzi + szerokoscPrzyciskuSzybkosciAntylopy;
        int wysokosc = (int)(swiat.odczytajRozmiarY() * swiat.stale.wymiarOrganizmu + (double)swiat.stale.wymiarOrganizmu / 3) + rozmiarPolaKomunikatow + 60;

        setSize(szerokosc,  wysokosc);
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
                Organizm dodawanyOrganizm = swiat.dajOrganizm(x, y);

                if(dodawanyOrganizm == null)
                    dodajPrzyciskWolnegoPola(x, y);
                else
                    dodajPanelOrganizmu(x, y, dodawanyOrganizm);
            }
        }

        validate();
        repaint();
    }


    @Override
    protected void dodajPodstawoweKomponenty()
    {
        super.dodajPodstawoweKomponenty();

        int szerokoscPaneluWidokuSwiata = (int)((swiat.odczytajRozmiarX() + (double)(swiat.odczytajRozmiarY() - 1) / 2) * 2 / 3 * sqrt(3) * swiat.stale.wymiarOrganizmu) + 2;
        int wysokoscPaneluWidokuSwiata = (int)(swiat.odczytajRozmiarY() * swiat.stale.wymiarOrganizmu + (double)swiat.stale.wymiarOrganizmu / 3) + 2;

        panelWidokuSwiata.setBounds(0, 0, szerokoscPaneluWidokuSwiata, wysokoscPaneluWidokuSwiata);
        add(panelWidokuSwiata);

        JScrollPane przewijanePoleKomunikatow = new JScrollPane(poleKomunikatow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        int szerokoscPolaKomunikatow = (swiat.odczytajRozmiarX() + 1 + (swiat.odczytajRozmiarY() - 1) / 2) * 4 / 3 * swiat.stale.dlugoscKrawedzi + szerokoscPrzyciskuSzybkosciAntylopy - 20;
        int yPolaKomunikatow = (int)(swiat.odczytajRozmiarY() * swiat.stale.wymiarOrganizmu + (double)swiat.stale.wymiarOrganizmu / 3) + 10;
        przewijanePoleKomunikatow.setBounds(0, yPolaKomunikatow, szerokoscPolaKomunikatow, rozmiarPolaKomunikatow);

        add(przewijanePoleKomunikatow);
    }

    @Override
    protected void dodajPrzyciskiDoOczekiwaniaNaKontynuowanie()
    {
        super.dodajPrzyciskiDoOczekiwaniaNaKontynuowanie();

        int xPrzycisku = (int)((swiat.odczytajRozmiarX() + (double)(swiat.odczytajRozmiarY() - 1) / 2) * 2 / 3 * sqrt(3) * swiat.stale.wymiarOrganizmu) + 10;
        przyciskKontynuowania.setBounds(xPrzycisku, 10, 120, 20);
        add(przyciskKontynuowania);

        przyciskZapisywania.setBounds(xPrzycisku, 40, 100, 20);
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

        int xPaneluOrganizmu = (int)((2 * sqrt(3) - 3) / 6 * swiat.stale.wymiarOrganizmu + (double)x * 2 / 3 * sqrt(3) * swiat.stale.wymiarOrganizmu + (double)y * sqrt(3) / 3 * swiat.stale.wymiarOrganizmu);
        int yPaneluOrganiuzmu = (int)((double)swiat.stale.wymiarOrganizmu / 6 + y * swiat.stale.wymiarOrganizmu);

        panelAktualnegoOrganizmu.setBounds(xPaneluOrganizmu, yPaneluOrganiuzmu, swiat.stale.wymiarOrganizmu, swiat.stale.wymiarOrganizmu);
        add(panelAktualnegoOrganizmu);
    }


    @Override
    public void dodajPrzyciskSzybkosciAntylopy()
    {
        super.dodajPrzyciskSzybkosciAntylopy();

        int xPrzyciskuAntylopy = (int)((swiat.odczytajRozmiarX() + (double)(swiat.odczytajRozmiarY() - 1) / 2) * 2 / 3 * sqrt(3) * swiat.stale.wymiarOrganizmu) + 10;
        przyciskSzybkosciAntylopy.setBounds(xPrzyciskuAntylopy, 10, szerokoscPrzyciskuSzybkosciAntylopy, 20);

        add(przyciskSzybkosciAntylopy);
        repaint();
    }
}
