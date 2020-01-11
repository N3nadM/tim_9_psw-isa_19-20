package com.isapsw.Projekat.service;

import com.isapsw.Projekat.domain.*;
import com.isapsw.Projekat.dto.KorisnikDTO;
import com.isapsw.Projekat.dto.LekarDTO;
import com.isapsw.Projekat.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class LekarService {

    @Autowired
    private LekarRepository lekarRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private KlinikaService klinikaService;

    @Autowired
    private TipPregledaService tipPregledaService;

    @Autowired
    private OcenaLekaraRepository ocenaLekaraRepository;

    @Autowired
    private PregledRepository pregledRepository;

    @Autowired
    private PacijentRepository pacijentRepository;

    @Autowired
    private OperacijaRepository operacijaRepository;

    @Autowired
    private KorisnikRepository korisnikRepository;

    public Lekar findLekar(String id) {
        return lekarRepository.findLekarByKorisnikId(Long.parseLong(id));
    }

    public Lekar getLekarById(String id) { return lekarRepository.findLekarById(Long.parseLong(id));}

    public Lekar updateLekar(Lekar lekar){
        return lekarRepository.save(lekar);
    }

    public List<String> findSlobodniTermini(Long id, String datum) {
        Lekar lekar = lekarRepository.findLekarById(id);

        List<String> termini = new ArrayList<>();

        lekar.getPregledi().sort((p, k) -> p.getDatumPocetka().after(k.getDatumPocetka()) ? 1 : -1);

        List<Pregled> preglediIstogDanaJednogLekara = new ArrayList<>();

        for(int i = 0; i < lekar.getPregledi().size(); i++) {
            if (KlinikaService.compareDatesOnly(Date.from(Instant.parse(datum)), lekar.getPregledi().get(i).getDatumPocetka())) {
                preglediIstogDanaJednogLekara.add(lekar.getPregledi().get(i));
            }
        }


        Date pocetak = KlinikaService.makeDateFromDateAndTime(Date.from(Instant.parse(datum)),lekar.getPocetakRadnogVremena());
        Date kraj = KlinikaService.makeDateFromDateAndTime(Date.from(Instant.parse(datum)),lekar.getKrajRadnogVremena());

        if(preglediIstogDanaJednogLekara.size() == 0) {
            while(pocetak.getTime() < kraj.getTime()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                String stringDate = dateFormat.format(pocetak);
                termini.add(stringDate);
                pocetak.setTime(pocetak.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000);
            }
        } else {
            for(int i = 0; i < preglediIstogDanaJednogLekara.size(); i++) {
                while(pocetak.getTime() < kraj.getTime() && pocetak.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000 <= preglediIstogDanaJednogLekara.get(i).getDatumPocetka().getTime()) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                    String stringDate = dateFormat.format(pocetak);
                    termini.add(stringDate);
                    pocetak.setTime(pocetak.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000);
                 }
                pocetak.setTime(preglediIstogDanaJednogLekara.get(i).getDatumZavrsetka().getTime());
                if(i == preglediIstogDanaJednogLekara.size() - 1) {
                    while(pocetak.getTime() < kraj.getTime()) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
                        String stringDate = dateFormat.format(pocetak);
                        termini.add(stringDate);
                        pocetak.setTime(pocetak.getTime() + lekar.getTipPregleda().getMinimalnoTrajanjeMin() * 60 * 1000);
                    }
                }
            }
        }

        return new ArrayList<>(termini);
    }

    public Lekar createLekar(LekarDTO lekarDTO) {
        Korisnik k = new Korisnik(new KorisnikDTO(lekarDTO));

        k.setPassword(bCryptPasswordEncoder.encode(lekarDTO.getPassword()));
        Authority a = authorityService.findByName("ROLE_LEKAR");
        k.getAuthorityList().add(a);
        korisnikService.addKorisnik(k);

        Lekar l = new Lekar();
        l.setAktivan(true);
        l.setKorisnik(k);
        l.setPocetakRadnogVremena(LocalTime.parse(lekarDTO.getPocetakRadnogVremena()));
        l.setKrajRadnogVremena(LocalTime.parse(lekarDTO.getKrajRadnogVremena()));
        l.setKlinika(klinikaService.findKlinikaId(lekarDTO.getKlinikaId()).get());
        l.setTipPregleda(tipPregledaService.getTipPregledaById(lekarDTO.getTipPregledaId()).get());
        return lekarRepository.save(l);

    }

    public Lekar editLekarByAdmin(String id,LekarDTO lekarDTO) throws Exception {
        Lekar l = lekarRepository.findLekarByKorisnikId(Long.parseLong(id));
        Korisnik k = korisnikService.findKoriskikId(Long.parseLong(id));
        k.setIme(lekarDTO.getIme());
        k.setPrezime(lekarDTO.getPrezime());
        k.setAdresa(lekarDTO.getAdresa());
        k.setGrad(lekarDTO.getGrad());
        k.setDrzava(lekarDTO.getDrzava());
        k.setTelefon(lekarDTO.getTelefon());
        l.setKorisnik(k);

        l.getPregledi().sort((a,b) -> a.getDatumPocetka().after(b.getDatumPocetka()) ? 1 : -1);

        Date datumSada = new Date();


        LocalTime noviPocetak = LocalTime.parse(lekarDTO.getPocetakRadnogVremena());
        LocalTime noviKraj = LocalTime.parse(lekarDTO.getKrajRadnogVremena());

        for(int i = 0; i < l.getPregledi().size(); i++) {

            if(l.getPregledi().get(i).getDatumPocetka().after(datumSada)) {
                LocalTime pocetakRadnogVremena = l.getPocetakRadnogVremena();
                LocalTime krajRadnogVremena = l.getKrajRadnogVremena();


                if (LocalDateTime.ofInstant(l.getPregledi().get(i).getDatumPocetka().toInstant(), ZoneId.systemDefault()).toLocalTime().compareTo(noviPocetak) == -1) {
                    throw new Exception("Lekar ima preglede");
                }
                if(LocalDateTime.ofInstant(l.getPregledi().get(i).getDatumZavrsetka().toInstant(), ZoneId.systemDefault()).toLocalTime().compareTo(noviKraj) == 1) {
                    throw new Exception("Lekar ima preglede");
                }
            }
        }

        l.setPocetakRadnogVremena(noviPocetak);
        l.setKrajRadnogVremena(noviKraj);

        return lekarRepository.save(l);
    }

    public Integer getOcenaLekaraOdPacijenta(Korisnik korisnik, String id) {
        OcenaLekara ocenaLekara = ocenaLekaraRepository.findOcenaLekaraByOcLekaraIdentifier(id + "-" + korisnik.getId());
        if(ocenaLekara == null) {
            return 0;
        } else {
            return ocenaLekara.getOcena();
        }

    }

    public OcenaLekara oceniLekara(String id, String ocena, Korisnik korisnik) {
        OcenaLekara ocenaLekara = ocenaLekaraRepository.findOcenaLekaraByOcLekaraIdentifier(id + "-" + korisnik.getId());
        Lekar lekar = lekarRepository.getOne(Long.parseLong(id));
        if(ocenaLekara == null) {
            Pacijent pacijent = pacijentRepository.findPacijentByKorisnikId(korisnik.getId());
            List<Pregled> pregledi = pregledRepository.findPregledByPacijentIdAndLekarId(Long.parseLong(id), pacijent.getId(), new Date());
            List<Operacija> operacije = operacijaRepository.findOperacijeByPacijentIdAndLekarId(Long.parseLong(id), pacijent.getId(), new Date());
            if(pregledi.size() == 0 && operacije.size() == 0) {
                return null;
            }
            ocenaLekara = new OcenaLekara();
            ocenaLekara.setKorisnik(korisnik);
            ocenaLekara.setLekar(lekar);
            ocenaLekara.setOcLekaraIdentifier(id + "-" + korisnik.getId());
        }
        ocenaLekara.setOcena(Integer.parseInt(ocena));

        ocenaLekaraRepository.save(ocenaLekara);

        Double prosek = ocenaLekaraRepository.calculateAverage(Long.parseLong(id));
        lekar.setOcena(prosek);
        lekarRepository.save(lekar);
        return ocenaLekara;
    }

    public List<OcenaLekara> getOceneLekara(String ids, Korisnik korisnik) {
        List<String> idjevi = new ArrayList<>();
        ids = ids.replace("[", "");
        ids = ids.replace("]", "");
        String[] parts = ids.split("\\s*,\\s*");

        for(int i = 0; i < parts.length; i++) {
            idjevi.add(parts[i] + "-" + korisnik.getId());
        }
        List<OcenaLekara> ocene = ocenaLekaraRepository.findLekariByIds(idjevi);


        return ocene;
    }

    public List<Lekar> getLekartKojiSeMeoguObrisati(String klinikaId){
        List<Lekar> lekars = lekarRepository.findLekarsByKlinikaId(Long.parseLong(klinikaId));
        Date date = Calendar.getInstance().getTime();
        //lekari koji imaju zakazane preglede u buducnosti
        List<Lekar> lekariPregled = pregledRepository.findLekareKodKojihImaZakazanihPregleda(Long.parseLong(klinikaId), date);
        //operacije koje ce se u buducnosti odrzati na klinici
        List<Operacija> operacijas = operacijaRepository.findBuduceOperacijeNaKlinici(Long.parseLong(klinikaId), date);
        List<Lekar> ret = new ArrayList<>(lekars);
        lekars.forEach(lekar -> {
            operacijas.forEach(operacija -> {
                if(operacija.getLekari().contains(lekar)){
                    ret.remove(lekar);
                }
            });
            if(ret.contains(lekar) && lekariPregled.contains(lekar)){
                ret.remove(lekar);
            }
        });
        return ret;
    }

    public Lekar obrisiLekara(String id){
        Lekar l = lekarRepository.findLekarById(Long.parseLong(id));
        l.setAktivan(false);
        return lekarRepository.save(l);
    }
}
