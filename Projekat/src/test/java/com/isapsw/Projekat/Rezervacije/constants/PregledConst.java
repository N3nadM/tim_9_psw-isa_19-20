package com.isapsw.Projekat.Rezervacije.constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PregledConst {

    public static final long PREGLED_ID = 1L;

    public static final String PREGLED_IZVESTAJ = "Odlicno ste bolesni";

    public static Date PREGLED_DATUMPOCETKA;

    static {
        try {
            PREGLED_DATUMPOCETKA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-12-12 15:50:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static Date PREGLED_DATUMZAVRSETKA;

    static {
        try {
            PREGLED_DATUMZAVRSETKA = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2019-12-12 16:50:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static final String PREGLED_LEKAR_IME = "Mile";

    public static final String PREGLED_MEDSESTRA_IME = "Sestrica";

    public static final String PREGLED_PACIJENT_IME = "Pacijent";

    public static final String PREGLED_SALA_NAZIV = "Sala za preglede 2";

    public static final int PREGLED_POPUST = 0;

    public static final int PREGLED_STANJE = 2;

    public static final int PREGLED_VRSTA = 0;

    public static final String PREGLED_DATUMKREIRANJA = "2019-11-30";
}
