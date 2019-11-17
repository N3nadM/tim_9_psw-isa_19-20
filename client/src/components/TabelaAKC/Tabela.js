import React, { useEffect, useState } from "react";
import Button from "@material-ui/core/Button";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
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
  useEffect(() => {
    getZahtevi();
  }, []);

  return (
    <div className="Tabela">
      {console.log(zahtevi)}
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
              <b>Username</b>
            </TableCell>
            <TableCell>
              <b>E-mail</b>
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
                <TableCell>{zahtev.username}</TableCell>
                <TableCell>{zahtev.email}</TableCell>
                <TableCell>{zahtev.adresa}</TableCell>
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
                    onClick={() =>
                      denieZahtev(
                        zahtev.id,
                        zahtev.email,
                        "Koju posluku porati"
                      )
                    }
                  >
                    Odbij
                  </Button>
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
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
