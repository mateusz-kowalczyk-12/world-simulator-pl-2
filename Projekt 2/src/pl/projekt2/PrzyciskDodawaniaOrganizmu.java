package pl.projekt2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrzyciskDodawaniaOrganizmu extends JButton
{
    private Swiat swiat;
    private OknoDodawaniaOrganizmu oknoDodawaniaOrganizmu;

    private int numerOrganizmu;
    private int x, y;

    private class SluchaczPrzyciskuDodawaniaOrganizmu implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent akcja)
        {
            if(numerOrganizmu == swiat.stale.wilkID)
                swiat.dodajZwierze(new Wilk(x, y, swiat));
            if(numerOrganizmu == swiat.stale.owcaID)
                swiat.dodajZwierze(new Owca(x, y, swiat));
            if(numerOrganizmu == swiat.stale.lisID)
                swiat.dodajZwierze(new Lis(x, y, swiat));
            if(numerOrganizmu == swiat.stale.zolwID)
                swiat.dodajZwierze(new Zolw(x, y, swiat));
            if(numerOrganizmu == swiat.stale.antylopaID)
                swiat.dodajZwierze(new Antylopa(x, y, swiat));

            if(numerOrganizmu == swiat.stale.trawaID)
                swiat.dodajRosline(new Trawa(x, y, swiat));
            if(numerOrganizmu == swiat.stale.mleczID)
                swiat.dodajRosline(new Mlecz(x, y, swiat));
            if(numerOrganizmu == swiat.stale.guaranaID)
                swiat.dodajRosline(new Guarana(x, y, swiat));
            if(numerOrganizmu == swiat.stale.wilczeJagodyID)
                swiat.dodajRosline(new WilczeJagody(x, y, swiat));
            if(numerOrganizmu == swiat.stale.barszczSosnowskiegoID)
                swiat.dodajRosline(new BarszczSosnowskiego(x, y, swiat));

            oknoDodawaniaOrganizmu.dispose();
            swiat.ustawDodanoNowyOrganizm(true);
        }
    }


    public PrzyciskDodawaniaOrganizmu(int x, int y, int numerOrganizmu, Swiat swiat, OknoDodawaniaOrganizmu oknoDodawaniaOrganizmu)
    {
        this.swiat = swiat;
        this.oknoDodawaniaOrganizmu = oknoDodawaniaOrganizmu;

        this.numerOrganizmu = numerOrganizmu;
        this.x = x;
        this.y = y;

        int xPrzycisku = 30;
        if(numerOrganizmu >= 5)
            xPrzycisku = 120;

        int yPrzycisku;
        if(numerOrganizmu < 5)
            yPrzycisku = 60 + 40 * numerOrganizmu;
        else
            yPrzycisku = 60 + 40 * (numerOrganizmu - 5);

        setBounds(xPrzycisku, yPrzycisku, swiat.stale.wymiarOrganizmu, swiat.stale.wymiarOrganizmu);

        ImageIcon ikona = new ImageIcon("ikony/wilk.png");

        if(numerOrganizmu == swiat.stale.wilkID)
            ikona = new ImageIcon("ikony/wilk.png");
        if(numerOrganizmu == swiat.stale.owcaID)
            ikona = new ImageIcon("ikony/owca.png");
        if(numerOrganizmu == swiat.stale.lisID)
            ikona = new ImageIcon("ikony/lis.png");
        if(numerOrganizmu == swiat.stale.zolwID)
            ikona = new ImageIcon("ikony/zolw.png");
        if(numerOrganizmu == swiat.stale.antylopaID)
            ikona = new ImageIcon("ikony/antylopa.png");
        if(numerOrganizmu == swiat.stale.trawaID )
            ikona = new ImageIcon("ikony/trawa.png");
        if(numerOrganizmu == swiat.stale.mleczID)
            ikona = new ImageIcon("ikony/mlecz.png");
        if(numerOrganizmu == swiat.stale.guaranaID)
            ikona = new ImageIcon("ikony/guarana.jpg");
        if(numerOrganizmu == swiat.stale.wilczeJagodyID)
            ikona = new ImageIcon("ikony/wilczejagody.png");
        if(numerOrganizmu == swiat.stale.barszczSosnowskiegoID)
            ikona = new ImageIcon("ikony/barszczsosnowskiego.png");

        addActionListener(new SluchaczPrzyciskuDodawaniaOrganizmu());
        setIcon(ikona);
    }
}
