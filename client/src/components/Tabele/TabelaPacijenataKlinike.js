import React, { useEffect, useState } from "react";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import { connect } from "react-redux";
import { getPacijentiKlinike } from "../../store/actions/pacijent";

import CircularProgress from "@material-ui/core/CircularProgress";

const TabelaPacijenataKlinike = ({
  korisnikId,
  pacijent: { pacijent },
  getPacijentiKlinike
}) => {
  useEffect(() => {
    getPacijentiKlinike(korisnikId);
  }, []);

  return (
    <div className="TabelaPacijenataKlinike">
      {console.log(pacijent)}
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
          {pacijent &&
            pacijent.map(p => (
              <TableRow key={p.id}>
                <TableCell>{p.ime}</TableCell>
                <TableCell>{p.prezime}</TableCell>

                <TableCell>{p.email}</TableCell>
                <TableCell>{p.telefon}</TableCell>
                <TableCell>{p.adresa}</TableCell>
                <TableCell>{p.grad}</TableCell>
                <TableCell>{p.drzava}</TableCell>
                <TableCell>{p.jbzo}</TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </div>
  );
};

function mapStateToProps(state) {
  return {
    korisnikId: state.currentUser.user.id,
    pacijent: state.pacijent
  };
}

export default connect(mapStateToProps, {
  getPacijentiKlinike
})(TabelaPacijenataKlinike);
