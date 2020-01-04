import React, { useEffect } from "react";
import Button from "@material-ui/core/Button";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Typography from "@material-ui/core/Typography";
import { connect } from "react-redux";
import { getZavrseniPregledi } from "../../store/actions/pregled";
import { getZavrseneOperacije } from "../../store/actions/operacija";

const TabelaZavrsenihPregledaOperacija = ({
  korisnikId,
  zavrseniPregledi,
  zavrseneOperacije,
  getZavrseniPregledi,
  getZavrseneOperacije
}) => {
  useEffect(() => {
    getZavrseniPregledi(korisnikId);
    getZavrseneOperacije(korisnikId);
    //eslint-disable-next-line
  }, []);

  console.log(zavrseniPregledi);

  return (
    <div className="Tabela">
      <Typography variant="h5">Pregledi</Typography>
      <Table aria-label="simple table">
        <TableHead>
          <TableRow>
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
                <TableCell>{pregled.izvestaj}</TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    color="primary"
                    //onClick={() => handleSubmit(pregled.id)}
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
                <TableCell>{operacija.izvestaj}</TableCell>
                <TableCell>
                  <Button
                    variant="contained"
                    color="primary"
                    //onClick={() => handleSubmit(pregled.id)}
                  >
                    Izmeni izvestaj
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
    korisnikId: state.currentUser.user.id,
    zavrseniPregledi: state.pregled.zavrseniPregledi,
    zavrseneOperacije: state.operacija.zavrseneOperacije
  };
}

export default connect(mapStateToProps, {
  getZavrseniPregledi,
  getZavrseneOperacije
})(TabelaZavrsenihPregledaOperacija);
