import React, { useEffect, useState } from "react";
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
import DialogTitle from "@material-ui/core/DialogTitle";
import { connect } from "react-redux";
import {
  getZahtevi,
  confirmZahtev,
  denieZahtev
} from "../../store/actions/zahtev";
import CircularProgress from "@material-ui/core/CircularProgress";

const Tabela = ({
  zahtevi,
  loading,
  getZahtevi,
  confirmZahtev,
  denieZahtev
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
    denieZahtev(denieInfo.id, denieInfo.email, text);
    setDenieInfo({ id: "", email: "" });
    handleClose();
  };

  useEffect(() => {
    getZahtevi();
  }, []);

  return (
    <div className="Tabela">
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
              <b>E-mail</b>
            </TableCell>

            <TableCell>
              <b>Telefon</b>
            </TableCell>
            <TableCell>
              <b>Grad</b>
            </TableCell>
            <TableCell>
              <b>Drzava</b>
            </TableCell>
            <TableCell>
              <b>Adresa</b>
            </TableCell>
            <TableCell>
              <b>JBZO</b>
            </TableCell>
            <TableCell></TableCell>
            <TableCell></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {zahtevi &&
            !!zahtevi.length &&
            zahtevi.map((zahtev, i) => (
              <TableRow key={zahtev.id}>
                <TableCell>{zahtev.ime}</TableCell>
                <TableCell>{zahtev.prezime}</TableCell>

                <TableCell>{zahtev.email}</TableCell>
                <TableCell>{zahtev.telefon}</TableCell>
                <TableCell>{zahtev.adresa}</TableCell>
                <TableCell>{zahtev.grad}</TableCell>
                <TableCell>{zahtev.drzava}</TableCell>
                <TableCell>{zahtev.jbzo}</TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={() => confirmZahtev(zahtev.id, zahtev.email)}
                  >
                    Prihvati
                  </Button>
                </TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    onClick={() => {
                      handleClickOpen();
                      setDenieInfo({ id: zahtev.id, email: zahtev.email });
                    }}
                  >
                    Odbij
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
          <DialogContentText>
            Ispisite poruku koja ce biti poslata podnosiocu zahteva za kreiranje
            korisnickog naloga kojeg odbijate ovim putem.
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
      {loading && <CircularProgress />}
    </div>
  );
};

function mapStateToProps(state) {
  return {
    zahtevi: state.zahtev.zahtevi,
    loading: state.zahtev.loading
  };
}

export default connect(mapStateToProps, {
  getZahtevi,
  confirmZahtev,
  denieZahtev
})(Tabela);
