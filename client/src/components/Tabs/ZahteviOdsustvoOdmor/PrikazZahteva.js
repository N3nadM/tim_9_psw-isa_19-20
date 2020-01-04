import React, { useEffect } from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Typography from "@material-ui/core/Typography";
import { connect } from "react-redux";
import { getZahteviOdsustvo } from "../../../store/actions/zahtevOdsustvo";
import { getZahteviOdmor } from "../../../store/actions/zahtevOdmor";
import PrikazDetaljaOdsustvo from "../ZahteviOdsustvoOdmor/PrikazDetaljaOdsustvo";
import PrikazDetaljaOdmor from "../ZahteviOdsustvoOdmor/PrikazDetaljaOdmor";
const Tabela = ({
  klinika,
  listaZahtevaOdsustvo,
  listaZahtevaOdmor,
  getZahteviOdmor,
  getZahteviOdsustvo
}) => {
  useEffect(() => {
    getZahteviOdmor(klinika.id);
    getZahteviOdsustvo(klinika.id);
  }, [getZahteviOdmor, getZahteviOdsustvo, klinika.id]);

  return (
    <div className="Tabela">
      <Typography variant="h6" id="tableTitle">
        Zahtevi za odsustvo
      </Typography>
      <Table aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>
              <b>Ime</b>
            </TableCell>
            <TableCell>
              <b>Prezime</b>
            </TableCell>

            <TableCell>
              <b>Datum</b>
            </TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {listaZahtevaOdsustvo &&
            !!listaZahtevaOdsustvo.length &&
            listaZahtevaOdsustvo.map((zahtev, i) => (
              <TableRow key={zahtev.id}>
                {zahtev.lekar === null ? (
                  <TableCell>{zahtev.medicinskaSestra.korisnik.ime}</TableCell>
                ) : (
                  <TableCell>{zahtev.lekar.korisnik.ime}</TableCell>
                )}
                {zahtev.lekar === null ? (
                  <TableCell>
                    {zahtev.medicinskaSestra.korisnik.prezime}
                  </TableCell>
                ) : (
                  <TableCell>{zahtev.lekar.korisnik.prezime}</TableCell>
                )}

                <TableCell>{zahtev.datum}</TableCell>
                {zahtev.lekar === null ? (
                  <TableCell>
                    <PrikazDetaljaOdsustvo
                      id={zahtev.medicinskaSestra.id}
                      zahtevId={zahtev.id}
                      tekst={zahtev.opis}
                      uloga={1}
                      datum={zahtev.datum}
                    />
                  </TableCell>
                ) : (
                  <TableCell>
                    <PrikazDetaljaOdsustvo
                      id={zahtev.lekar.id}
                      zahtevId={zahtev.id}
                      uloga={0}
                      tekst={zahtev.opis}
                      datum={zahtev.datum}
                    />
                  </TableCell>
                )}
              </TableRow>
            ))}
        </TableBody>
      </Table>

      <Typography variant="h6" id="tableTitle">
        Zahtevi za godisnji odmor
      </Typography>
      <Table aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>
              <b>Ime</b>
            </TableCell>
            <TableCell>
              <b>Prezime</b>
            </TableCell>

            <TableCell>
              <b>Od</b>
            </TableCell>
            <TableCell>
              <b>Do</b>
            </TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {listaZahtevaOdmor &&
            !!listaZahtevaOdmor.length &&
            listaZahtevaOdmor.map((zahtev, i) => (
              <TableRow key={zahtev.id}>
                {zahtev.lekar === null ? (
                  <TableCell>{zahtev.medicinskaSestra.korisnik.ime}</TableCell>
                ) : (
                  <TableCell>{zahtev.lekar.korisnik.ime}</TableCell>
                )}
                {zahtev.lekar === null ? (
                  <TableCell>
                    {zahtev.medicinskaSestra.korisnik.prezime}
                  </TableCell>
                ) : (
                  <TableCell>{zahtev.lekar.korisnik.prezime}</TableCell>
                )}

                <TableCell>{zahtev.datumOd}</TableCell>
                <TableCell>{zahtev.datumDo}</TableCell>
                {zahtev.lekar === null ? (
                  <TableCell>
                    <PrikazDetaljaOdmor
                      id={zahtev.medicinskaSestra.id}
                      zahtevId={zahtev.id}
                      tekst={zahtev.opis}
                      uloga={1}
                      datum1={zahtev.datumOd}
                      datum2={zahtev.datumDo}
                    />
                  </TableCell>
                ) : (
                  <TableCell>
                    <PrikazDetaljaOdmor
                      id={zahtev.lekar.id}
                      zahtevId={zahtev.id}
                      uloga={0}
                      tekst={zahtev.opis}
                      datum1={zahtev.datumOd}
                      datum2={zahtev.datumDo}
                    />
                  </TableCell>
                )}
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </div>
  );
};

function mapStateToProps(state) {
  return {
    klinika: state.adminKlinike.klinika,
    listaZahtevaOdmor: state.zahtevOdmor.listaZahtevaOdmor,
    listaZahtevaOdsustvo: state.zahtevOdsustvo.listaZahtevaOdsustvo
  };
}

export default connect(mapStateToProps, {
  getZahteviOdmor,
  getZahteviOdsustvo
})(Tabela);
