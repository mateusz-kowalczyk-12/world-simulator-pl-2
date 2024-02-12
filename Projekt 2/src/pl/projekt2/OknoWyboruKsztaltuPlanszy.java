package pl.projekt2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OknoWyboruKsztaltuPlanszy extends JFrame
{
    private int wybranyKsztalt;

    private class SluchaczPrzyciskuWyboruKraty implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            wybranyKsztalt = 0;
            dispose();
        }
    }

    private class SluchaczPrzyciskuWyboruSzesciokatow implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            wybranyKsztalt = 1;
            dispose();
        }
    }


    public OknoWyboruKsztaltuPlanszy()
    {
        wybranyKsztalt = -1;

        setTitle("Symulator świata - Mateusz Kowalczyk, s188717");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        setSize(800, 300);
        setVisible(true);

        JLabel tekstOkna = new JLabel("Wybierz kształt planszy");
        tekstOkna.setFont(new Font("Courier New", Font.PLAIN, 30));
        tekstOkna.setBounds(165, 10, 800, 40);
        add(tekstOkna);

        JButton przyciskWyboruKsztaltuKraty = new JButton("Krata");
        przyciskWyboruKsztaltuKraty.setBounds(150, 100, 200, 30);
        przyciskWyboruKsztaltuKraty.setBackground(Color.orange);
        przyciskWyboruKsztaltuKraty.setFont(new Font("Courier New", Font.PLAIN, 15));
        przyciskWyboruKsztaltuKraty.addActionListener(new SluchaczPrzyciskuWyboruKraty());
        add(przyciskWyboruKsztaltuKraty);

        JButton przyciskWyboruKsztaltuSzesciokatow = new JButton("Sześciokąty");
        przyciskWyboruKsztaltuSzesciokatow.setBounds(400, 100, 200, 30);
        przyciskWyboruKsztaltuSzesciokatow.setBackground(Color.orange);
        przyciskWyboruKsztaltuSzesciokatow.setFont(new Font("Courier New", Font.PLAIN, 15));
        przyciskWyboruKsztaltuSzesciokatow.addActionListener(new SluchaczPrzyciskuWyboruSzesciokatow());
        add(przyciskWyboruKsztaltuSzesciokatow);
    }


    public int odczytajWybranyKsztalt()
    {
        return wybranyKsztalt;
    }
}
