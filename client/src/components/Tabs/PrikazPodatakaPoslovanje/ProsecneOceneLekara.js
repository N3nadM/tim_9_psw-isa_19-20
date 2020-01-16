import React, { useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import { getLekariNaKlinici } from "../../../store/actions/lekar";
import Typography from "@material-ui/core/Typography";
import { connect } from "react-redux";
import FullWidthTabs from "../PronalazenjeSale/ZahteviPregledOperacija";

const useStyles = makeStyles(theme => ({
  seeMore: {
    marginTop: theme.spacing(3)
  }
}));

const ProsecneOceneLekara = ({ klinika, rows, getLekariNaKlinici }) => {
  const classes = useStyles();

  useEffect(() => {
    getLekariNaKlinici(klinika.id);
  }, []);
  return (
    <React.Fragment>
      <Typography component="h2" variant="h6" color="primary" gutterBottom>
        Prosečne ocene lekara
      </Typography>
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Ime</TableCell>
            <TableCell>Prezime</TableCell>
            <TableCell>Tip pregleda</TableCell>
            <TableCell align="right">Ocena</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows.map(row => (
            <TableRow key={row.id}>
              <TableCell>{row.korisnik.ime}</TableCell>
              <TableCell>{row.korisnik.prezime}</TableCell>
              <TableCell>{row.tipPregleda.naziv}</TableCell>
              <TableCell align="right">{row.ocena}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </React.Fragment>
  );
};

function mapStateToProps(state) {
  return {
    rows: state.lekar.listaLekaraNaKlinici
  };
}

export default connect(mapStateToProps, { getLekariNaKlinici })(
  ProsecneOceneLekara
);
