package pl.projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Thread.sleep;

abstract public class OknoSymulacji extends JFrame
{
    protected final Swiat swiat;
    protected MojPanelWidokuSwiata panelWidokuSwiata;

    protected JPanel panelAktualnegoOrganizmu;
    protected JButton przyciskSzybkosciAntylopy, przyciskKontynuowania, przyciskZapisywania;
    protected JLabel napisPaneluAktualnegoOrganizmu;
    protected ImageIcon ikonaNapisuPaneluAktualnegoOrganizmu;

    protected JTextArea poleKomunikatow;

    protected int kontynuowac;

    protected final int rozmiarPolaKomunikatow;
    protected final int szerokoscPrzyciskuSzybkosciAntylopy;

    protected class SluchaczPrzyciskuKontynuowania implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            kontynuowac = 1;
        }
    }

    protected class SluchaczPrzyciskuZapisywania implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            swiat.zapiszStanSymulacji();
        }
    }

    protected class SluchaczPrzyciskuSzybkosciAntylopy implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            swiat.dajCzlowieka().ustawPozostaloSzybkosciAntylopy(5);
        }
    }

    protected class SluchaczStrzalkiGora extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            swiat.dajCzlowieka().ustawStrzalkeWcisnieta("UP");
        }
    }

    protected class SluchaczStrzalkiDol extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            swiat.dajCzlowieka().ustawStrzalkeWcisnieta("DOWN");
        }
    }

    protected class SluchaczStrzalkiPrawo extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            swiat.dajCzlowieka().ustawStrzalkeWcisnieta("RIGHT");
        }
    }

    protected class SluchaczStrzalkiLewo extends AbstractAction
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            swiat.dajCzlowieka().ustawStrzalkeWcisnieta("LEFT");
        }
    }


    public OknoSymulacji(Swiat swiat)
    {
        this.swiat = swiat;

        kontynuowac = swiat.stale.testowaWartoscPoczatkowa;

        rozmiarPolaKomunikatow = 150;
        szerokoscPrzyciskuSzybkosciAntylopy = 300;

        setTitle("Symulator świata - Mateusz Kowalczyk, s188717");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }


    public boolean kontynuowac()
    {
        kontynuowac = swiat.stale.testowaWartoscPoczatkowa;

        dodajPrzyciskiDoOczekiwaniaNaKontynuowanie();
        repaint();

        while(kontynuowac == swiat.stale.testowaWartoscPoczatkowa)
        {
            try { sleep(1); }
            catch(Exception wyjatek) { System.out.println(wyjatek); }

            if(swiat.odczytajDodanoNowyOrganizm())
                break;
        }

        getContentPane().remove(przyciskKontynuowania);
        getContentPane().remove(przyciskZapisywania);

        if(swiat.odczytajDodanoNowyOrganizm())
        {
            swiat.ustawDodanoNowyOrganizm(false);
            rysujSwiat();
            return kontynuowac();
        }

        return kontynuowac == 1;
    }


    abstract protected void rysujSwiat();


    protected void dodajPodstawoweKomponenty()
    {
        panelWidokuSwiata =  new MojPanelWidokuSwiata(swiat);

        if(swiat.dajCzlowieka() != null)
        {
            Action gora = new SluchaczStrzalkiGora();
            Action dol = new SluchaczStrzalkiDol();
            Action prawo = new SluchaczStrzalkiPrawo();
            Action lewo = new SluchaczStrzalkiLewo();

            panelWidokuSwiata.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "strzalkaGora");
            panelWidokuSwiata.getActionMap().put("strzalkaGora", gora);
            panelWidokuSwiata.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "strzalkaDol");
            panelWidokuSwiata.getActionMap().put("strzalkaDol", dol);
            panelWidokuSwiata.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "strzalkaPrawo");
            panelWidokuSwiata.getActionMap().put("strzalkaPrawo", prawo);
            panelWidokuSwiata.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "strzalkaLewo");
            panelWidokuSwiata.getActionMap().put("strzalkaLewo", lewo);
        }

        poleKomunikatow = new JTextArea(10, 10);
        poleKomunikatow.setFont(new Font("Courier New", Font.PLAIN, 14));
        poleKomunikatow.setBackground(new Color(242, 255, 164));
        poleKomunikatow.setVisible(true);
        poleKomunikatow.setEditable(false);
        poleKomunikatow.setFocusable(false);
        poleKomunikatow.setText(swiat.odczytajKomunikat());
    }

    protected void dodajPrzyciskiDoOczekiwaniaNaKontynuowanie()
    {
        przyciskKontynuowania = new JButton("Nowa tura");

        przyciskKontynuowania.setBackground(Color.orange);
        przyciskKontynuowania.setFont(new Font("Courier New", Font.PLAIN, 15));
        przyciskKontynuowania.setVisible(true);
        przyciskKontynuowania.addActionListener(new SluchaczPrzyciskuKontynuowania());

        przyciskZapisywania = new JButton("Zapisz");

        przyciskZapisywania.setBackground(Color.orange);
        przyciskZapisywania.setFont(new Font("Courier New", Font.PLAIN, 15));
        przyciskZapisywania.setVisible(true);
        przyciskZapisywania.addActionListener(new SluchaczPrzyciskuZapisywania());
    }

    abstract protected void dodajPrzyciskWolnegoPola(int x, int y);

    protected void dodajPanelOrganizmu(int x, int y, Organizm dodawanyOrganizm)
    {
        panelAktualnegoOrganizmu = new JPanel();

        panelAktualnegoOrganizmu.setBackground(null);
        panelAktualnegoOrganizmu.setVisible(true);

        napisPaneluAktualnegoOrganizmu = new JLabel();
        ikonaNapisuPaneluAktualnegoOrganizmu = ustawIkone(dodawanyOrganizm);

        napisPaneluAktualnegoOrganizmu.setIcon(ikonaNapisuPaneluAktualnegoOrganizmu);
        napisPaneluAktualnegoOrganizmu.setVisible(true);

        panelAktualnegoOrganizmu.add(napisPaneluAktualnegoOrganizmu);
    }


    protected ImageIcon ustawIkone(Organizm dodawanyOrganizm)
    {
        ImageIcon ikona = new ImageIcon();

        if(dodawanyOrganizm instanceof Wilk)
            ikona = new ImageIcon("ikony/wilk.png");
        if(dodawanyOrganizm instanceof Owca)
            ikona = new ImageIcon("ikony/owca.png");
        if(dodawanyOrganizm instanceof Lis)
            ikona = new ImageIcon("ikony/lis.png");
        if(dodawanyOrganizm instanceof Zolw)
            ikona = new ImageIcon("ikony/zolw.png");
        if(dodawanyOrganizm instanceof Antylopa)
            ikona = new ImageIcon("ikony/antylopa.png");
        if(dodawanyOrganizm instanceof Trawa)
            ikona = new ImageIcon("ikony/trawa.png");
        if(dodawanyOrganizm instanceof Mlecz)
            ikona = new ImageIcon("ikony/mlecz.png");
        if(dodawanyOrganizm instanceof Guarana)
            ikona = new ImageIcon("ikony/guarana.jpg");
        if(dodawanyOrganizm instanceof WilczeJagody)
            ikona = new ImageIcon("ikony/wilczejagody.png");
        if(dodawanyOrganizm instanceof BarszczSosnowskiego)
            ikona = new ImageIcon("ikony/barszczsosnowskiego.png");
        if(dodawanyOrganizm instanceof Czlowiek)
            ikona = new ImageIcon("ikony/czlowiek.jpg");

        return ikona;
    }

    public void dodajPrzyciskSzybkosciAntylopy()
    {
        przyciskSzybkosciAntylopy = new JButton("Aktywuj szybkość antylopy");

        przyciskSzybkosciAntylopy.setBackground(Color.orange);
        przyciskSzybkosciAntylopy.setFont(new Font("Courier New", Font.PLAIN, 15));
        przyciskSzybkosciAntylopy.setVisible(true);
        przyciskSzybkosciAntylopy.addActionListener(new SluchaczPrzyciskuSzybkosciAntylopy());
    }

    public void usunPrzyciskSzybkosciAntylopy()
    {
        getContentPane().remove(przyciskSzybkosciAntylopy);
        repaint();
    }
}
