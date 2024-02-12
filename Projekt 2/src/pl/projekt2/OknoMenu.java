package pl.projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Thread.sleep;

public class OknoMenu extends JFrame
{
    private final Swiat swiat;

    private int wczytacStanSymulacji;

    private File plikZapisu;

    private JTextField poleRozmiarX, poleRozmiarY;

    private class SluchaczPrzyciskuNowejSymulacji implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            wczytacStanSymulacji = 0;
        }
    }

    private class SluchaczPrzyciskuWczytywaniaSymulacji implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            wczytacStanSymulacji = 1;
        }
    }

    private class SluchaczPrzyciskuWyboruPliku implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            JFileChooser oknoWyboruPliku = new JFileChooser();
            oknoWyboruPliku.setCurrentDirectory(new File(System.getProperty("user.dir")));

            String nazwaPlikuZapisu;

            if(oknoWyboruPliku.showOpenDialog(null) == 0)
            {
                plikZapisu = new File(oknoWyboruPliku.getSelectedFile().getAbsolutePath());
            }
        }
    }

    private class SluchaczPrzyciskuStwarzaniaSwiata implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            int rozmiarX = parseInt(poleRozmiarX.getText());
            int rozmiarY = parseInt(poleRozmiarY.getText());

            swiat.konstruujSwiat(rozmiarX, rozmiarY);
            swiat.dodajOrganizmy();
        }
    }


    public OknoMenu(Swiat swiat)
    {
        this.swiat = swiat;
        wczytacStanSymulacji = -1;

        setTitle("Symulator świata - Mateusz Kowalczyk, s188717");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setSize(800, 300);
        setVisible(true);
    }

    public void wykonajMenu()
    {
        JLabel tekstMenu = new JLabel("Wybierz opcję rozpoczęcia symulacji");
        tekstMenu.setFont(new Font("Courier New", Font.PLAIN, 30));
        tekstMenu.setBounds(110, 10, 800, 40);
        add(tekstMenu);

        JButton przyciskNowejSymulacji = new JButton("Nowa symulacja");
        SluchaczPrzyciskuNowejSymulacji sluchaczPrzyciskuNowejSymulacji = new SluchaczPrzyciskuNowejSymulacji();
        przyciskNowejSymulacji.setBounds(150, 100, 200, 30);
        przyciskNowejSymulacji.setBackground(Color.orange);
        przyciskNowejSymulacji.setFont(new Font("Courier New", Font.PLAIN, 15));
        przyciskNowejSymulacji.addActionListener(sluchaczPrzyciskuNowejSymulacji);
        add(przyciskNowejSymulacji);

        JButton przyciskWczytywaniaSymulacji = new JButton("Wczytaj symulację");
        SluchaczPrzyciskuWczytywaniaSymulacji sluchaczPrzyciskuWczytywaniaSymulacji = new SluchaczPrzyciskuWczytywaniaSymulacji();
        przyciskWczytywaniaSymulacji.setBounds(400, 100, 200, 30);
        przyciskWczytywaniaSymulacji.setBackground(Color.orange);
        przyciskWczytywaniaSymulacji.setFont(new Font("Courier New", Font.PLAIN, 15));
        przyciskWczytywaniaSymulacji.addActionListener(sluchaczPrzyciskuWczytywaniaSymulacji);
        add(przyciskWczytywaniaSymulacji);

        repaint();
    }

    public void wczytajStanSymulacji()
    {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JLabel tekstWczytywania = new JLabel("Wybierz plik z zapisem");
        tekstWczytywania.setBounds(0, 50, 800, 30);
        tekstWczytywania.setFont(new Font("Courier New", Font.PLAIN, 25));
        tekstWczytywania.setHorizontalAlignment(JLabel.CENTER);
        tekstWczytywania.setVerticalAlignment(JLabel.TOP);
        add(tekstWczytywania);

        JButton przyciskWyboruPliku = new JButton("Wybierz plik");
        przyciskWyboruPliku.setBounds(300,100,200,30);
        przyciskWyboruPliku.setBackground(Color.orange);
        przyciskWyboruPliku.setFont(new Font("Courier New", Font.PLAIN, 15));
        przyciskWyboruPliku.setHorizontalAlignment(JButton.CENTER);
        przyciskWyboruPliku.setVerticalAlignment(JButton.BOTTOM);
        przyciskWyboruPliku.setVisible(true);
        przyciskWyboruPliku.addActionListener(new SluchaczPrzyciskuWyboruPliku());
        add(przyciskWyboruPliku);

        repaint();

        do
        {
            try { sleep(1); }
            catch(Exception wyjatek) { System.out.println("Wyjatek");}
        }
        while(plikZapisu == null);

        try
        {
            Scanner skanerPliku = new Scanner(plikZapisu);

            String linia;
            int rozmiarX, rozmiarY;

            linia = skanerPliku.nextLine();
            rozmiarX = parseInt(linia);
            linia = skanerPliku.nextLine();
            rozmiarY = parseInt(linia);

            swiat.konstruujSwiat(rozmiarX, rozmiarY);

            while (skanerPliku.hasNext()) {
                linia = skanerPliku.nextLine();

                String linia2;
                int x, y;

                linia2 = skanerPliku.nextLine();
                x = parseInt(linia2);
                linia2 = skanerPliku.nextLine();
                y = parseInt(linia2);

                if (linia.equals("Trawa")) {
                    swiat.dodajRosline(new Trawa(x, y, swiat));
                    continue;
                }
                if (linia.equals("Mlecz")) {
                    swiat.dodajRosline(new Mlecz(x, y, swiat));
                    continue;
                }
                if (linia.equals("Guarana")) {
                    swiat.dodajRosline(new Guarana(x, y, swiat));
                    continue;
                }
                if (linia.equals("WilczeJagody")) {
                    swiat.dodajRosline(new WilczeJagody(x, y, swiat));
                    continue;
                }
                if (linia.equals("BarszczSosnowskiego")) {
                    swiat.dodajRosline(new BarszczSosnowskiego(x, y, swiat));
                    continue;
                }

                int sila, wiek;

                linia2 = skanerPliku.nextLine();
                sila = parseInt(linia2);
                linia2 = skanerPliku.nextLine();
                wiek = parseInt(linia2);

                if (linia.equals("Wilk")) {
                    swiat.dodajZwierze(new Wilk(x, y, sila, wiek, swiat));
                    continue;
                }
                if (linia.equals("Owca")) {
                    swiat.dodajZwierze(new Owca(x, y, sila, wiek, swiat));
                    continue;
                }
                if (linia.equals("Lis")) {
                    swiat.dodajZwierze(new Lis(x, y, sila, wiek, swiat));
                    continue;
                }
                if (linia.equals("Zolw")) {
                    swiat.dodajZwierze(new Zolw(x, y, sila, wiek, swiat));
                    continue;
                }
                if (linia.equals("Antylopa")) {
                    swiat.dodajZwierze(new Antylopa(x, y, sila, wiek, swiat));
                    continue;
                }
                if (linia.equals("Czlowiek")) {
                    int pozostaloSzybkosciAntylopy;

                    linia2 = skanerPliku.nextLine();
                    pozostaloSzybkosciAntylopy = parseInt(linia2);

                    swiat.dodajZwierze(new Czlowiek(x, y, sila, wiek, pozostaloSzybkosciAntylopy, swiat));
                }
            }
        }
        catch(Exception wyjatek)
        {
            System.out.println(wyjatek);
        }
    }

    public void stworzNowaSymulacje()
    {
        getContentPane().removeAll();
        setLayout(new BorderLayout());

        JLabel tekstWyboruRozmiaru = new JLabel("Wybierz rozmiar planszy");
        tekstWyboruRozmiaru.setBounds(0, 0, 800, 30);
        tekstWyboruRozmiaru.setFont(new Font("Courier New", Font.PLAIN, 30));
        tekstWyboruRozmiaru.setHorizontalAlignment(JLabel.CENTER);
        tekstWyboruRozmiaru.setVerticalAlignment(JLabel.TOP);
        add(tekstWyboruRozmiaru);

        JLabel etykietaX = new JLabel("Rozmiar x:");
        etykietaX.setBounds(100, 60, 800, 30);
        etykietaX.setFont(new Font("Courier New", Font.PLAIN, 20));
        add(etykietaX);

        poleRozmiarX = new JTextField();
        poleRozmiarX.setBounds(240, 60, 50, 30);
        poleRozmiarX.setFont(new Font("Courier New", Font.PLAIN, 20));
        add(poleRozmiarX);

        JLabel etykietaY = new JLabel("Rozmiar y: ");
        etykietaY.setBounds(450, 60, 800, 30);
        etykietaY.setFont(new Font("Courier New", Font.PLAIN, 20));
        add(etykietaY);

        poleRozmiarY = new JTextField();
        poleRozmiarY.setBounds(590, 60, 50, 30);
        poleRozmiarY.setFont(new Font("Courier New", Font.PLAIN, 20));
        add(poleRozmiarY);

        JButton przyciskStwarzaniaSwiata = new JButton("Stworz świat");
        SluchaczPrzyciskuStwarzaniaSwiata sluchaczPrzyciskuNowejSymulacji = new SluchaczPrzyciskuStwarzaniaSwiata();
        przyciskStwarzaniaSwiata.setBounds(280, 150, 200, 30);
        przyciskStwarzaniaSwiata.setBackground(Color.orange);
        przyciskStwarzaniaSwiata.setFont(new Font("Courier New", Font.PLAIN, 15));
        przyciskStwarzaniaSwiata.addActionListener(sluchaczPrzyciskuNowejSymulacji);
        add(przyciskStwarzaniaSwiata);

        repaint();

        while(!swiat.poleIstnieje(0, 0))
        {
            try { sleep(1); }
            catch(Exception wyjatek) { System.out.println(wyjatek); }
        }
    }


    public int odczytajWczytacStanSymulacji()
    {
        return wczytacStanSymulacji;
    }
}
