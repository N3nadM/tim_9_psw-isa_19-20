import React, { useEffect } from "react";
import Button from "@material-ui/core/Button";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Typography from "@material-ui/core/Typography";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";
import TextField from "@material-ui/core/TextField";
import { connect } from "react-redux";
import { getZavrseniPregledi } from "../../store/actions/pregled";
import { getZavrseneOperacije } from "../../store/actions/operacija";
import { izmeniIzvestaj } from "../../store/actions/pregledOperacija";

const TabelaZavrsenihPregledaOperacija = ({
  korisnikId,
  zavrseniPregledi,
  zavrseneOperacije,
  getZavrseniPregledi,
  getZavrseneOperacije,
  izmeniIzvestaj
}) => {
  useEffect(() => {
    getZavrseniPregledi(korisnikId);
    getZavrseneOperacije(korisnikId);
    //eslint-disable-next-line
  }, []);

  const [open, setOpen] = React.useState(false);
  const [text, setText] = React.useState("");
  const [izvestaj, setIzvestaj] = React.useState({
    id: "",
    vrsta: ""
  });

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleChange = e => {
    setText(e.target.value);
  };

  const handleSendIzvestaj = () => {
    let data = { id: izvestaj.id, vrsta: izvestaj.vrsta, izvestaj: text };
    izmeniIzvestaj(data);
    setIzvestaj({ id: "", vrsta: "" });
    handleClose();
    getZavrseniPregledi(korisnikId);
    getZavrseneOperacije(korisnikId);
  };

  console.log(zavrseniPregledi);

  return (
    <div className="Tabela">
      <Typography variant="h5">Pregledi</Typography>
      <Table aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>
              <b>Pacijent</b>
            </TableCell>
            <TableCell>
              <b>JBZO</b>
            </TableCell>
            <TableCell>
              <b>Izvestaj</b>
            </TableCell>

            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {zavrseniPregledi &&
            !!zavrseniPregledi.length &&
            zavrseniPregledi.map((pregled, i) => (
              <TableRow key={pregled.id}>
                <TableCell>
                  {pregled.pacijent.korisnik.ime +
                    " " +
                    pregled.pacijent.korisnik.prezime}
                </TableCell>
                <TableCell>{pregled.pacijent.jbzo}</TableCell>
                <TableCell style={{ maxWidth: 150 }}>
                  {pregled.izvestaj}
                </TableCell>

                <TableCell>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => {
                      handleClickOpen();
                      setIzvestaj({
                        id: pregled.id,
                        vrsta: pregled.vrsta
                      });
                    }}
                  >
                    Izmeni izvestaj
                  </Button>
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
      <Typography variant="h5">Operacije</Typography>
      <Table aria-label="simple table" title="Operacije">
        <TableHead>
          <TableRow>
            <TableCell>
              <b>Pacijent</b>
            </TableCell>
            <TableCell>
              <b>JBZO</b>
            </TableCell>
            <TableCell>
              <b>Izvestaj</b>
            </TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {zavrseneOperacije &&
            !!zavrseneOperacije.length &&
            zavrseneOperacije.map((operacija, i) => (
              <TableRow key={operacija.id}>
                <TableCell>
                  {operacija.pacijent.korisnik.ime +
                    " " +
                    operacija.pacijent.korisnik.prezime}
                </TableCell>
                <TableCell>{operacija.pacijent.jbzo}</TableCell>
                <TableCell style={{ maxWidth: 150 }}>
                  {operacija.izvestaj}
                </TableCell>

                <TableCell>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => {
                      handleClickOpen();
                      setIzvestaj({
                        id: operacija.id,
                        vrsta: operacija.vrsta
                      });
                    }}
                  >
                    Izmeni izvestaj
                  </Button>
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>

      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="form-dialog-title"
      >
        <DialogTitle id="form-dialog-title">Subscribe</DialogTitle>
        <DialogContent>
          <DialogContentText>Izmenite izvestaj</DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="text"
            label="Izvestaj"
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
          <Button onClick={handleSendIzvestaj} color="primary">
            Izmeni
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

function mapStateToProps(state) {
  return {
    korisnikId: state.currentUser.user.id,
    zavrseniPregledi: state.pregled.zavrseniPregledi,
    zavrseneOperacije: state.operacija.zavrseneOperacije
  };
}

export default connect(mapStateToProps, {
  getZavrseniPregledi,
  getZavrseneOperacije,
  izmeniIzvestaj
})(TabelaZavrsenihPregledaOperacija);
