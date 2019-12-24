import React, { useEffect } from "react";
import Button from "@material-ui/core/Button";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import TextField from "@material-ui/core/TextField";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import Typography from "@material-ui/core/Typography";
import DialogTitle from "@material-ui/core/DialogTitle";
import { connect } from "react-redux";
import { getZahteviOdsustvo } from "../../../store/actions/zahtevOdsustvo";
import { getZahteviOdmor } from "../../../store/actions/zahtevOdmor";
import PrikazDetaljaOdsustvo from "../ZahteviOdsustvoOdmor/PrikazDetaljaOdsustvo";
const Tabela = ({
  klinika,
  listaZahtevaOdsustvo,
  listaZahtevaOdmor,
  getZahteviOdmor,
  getZahteviOdsustvo
}) => {
  const [open, setOpen] = React.useState(false);
  const [text, setText] = React.useState("");
  const [denieInfo, setDenieInfo] = React.useState({ id: "", email: "" });

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = e => {
    setText(e.target.value);
  };

  const handleSendDenie = () => {
    handleClose();
  };

  {
    console.log(klinika);
  }

  useEffect(() => {
    getZahteviOdmor(klinika.id);
    getZahteviOdsustvo(klinika.id);
  }, []);

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
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">Obrazloženje</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Ispišite poruku koja će biti poslata podnosiocu zahteva za
            odsustvo/godišnji odmor.
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="text"
            label="Poruka"
            type="text"
            onChange={handleChange}
            fullWidth
            multiline
            rows={5}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Nazad
          </Button>
          <Button onClick={handleSendDenie} color="primary">
            Pošalji
          </Button>
        </DialogActions>
      </Dialog>
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
            <TableCell></TableCell>
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
                <TableCell>
                  <Button variant="contained" color="primary">
                    Provera zauzetosti
                  </Button>
                </TableCell>
                <TableCell>
                  <Button variant="contained">Prihvati</Button>
                </TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    onClick={() => {
                      handleClickOpen();
                    }}
                  >
                    Odbij
                  </Button>
                </TableCell>
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
