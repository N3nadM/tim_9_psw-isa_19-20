import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getOperacijaById } from "../../../store/actions/operacija";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Typography from "@material-ui/core/Typography";
import { Paper, TextField } from "@material-ui/core";
import Skeleton from "@material-ui/lab/Skeleton";
import PretragaSlobodnihSala from "../BrzoZakazivanjePregleda/PretragaSlobodnihSala";
import Axios from "axios";
import Button from "@material-ui/core/Button";

const OperacijaSala = ({ id, operacija, getOperacijaById, klinika }) => {
  useEffect(() => {
    getOperacijaById(id);
  }, []);

  const [state, setState] = React.useState({
    medSestraId: "",
    medSestraImePrezime: ""
  });
  const handleSubmit = async e => {
    e.preventDefault();
    const resp = await Axios.get(
      `/api/medsestra/sestraDostupna/${klinika.id}/${operacija.datumPocetka}/${operacija.tipPregleda.minimalnoTrajanjeMin}`
    );
    console.log(resp.data);
    if (resp.data != null && resp.data != "") {
      setState({
        medSestraId: resp.data.id,
        medSestraImePrezime:
          resp.data.korisnik.ime + " " + resp.data.korisnik.prezime
      });
    } else {
      setState({
        medSestraImePrezime: "Nema slobodnih sestara za taj termin"
      });
    }
  };

  return (
    <div>
      <Paper style={{ padding: 50, paddingBottom: 75 }}>
        <Typography
          variant="h4"
          component="h2"
          style={{ marginBottom: 20, marginLeft: 13 }}
        >
          Operacija
        </Typography>
        {!operacija && <Skeleton height={350} />}
        {operacija && (
          <>
            <List disablePadding>
              <ListItem>
                <ListItemText primary="Pacijent" />
                <Typography variant="subtitle1">
                  {operacija.pacijent.korisnik.ime}
                </Typography>
              </ListItem>
              <ListItem>
                <ListItemText primary="Datum pocetka" />
                <Typography variant="subtitle1">
                  {operacija.datumPocetka}
                </Typography>
              </ListItem>
            </List>
          </>
        )}
      </Paper>
      {operacija && (
        <PretragaSlobodnihSala
          klinikaId={klinika.id}
          trajanje={operacija.tipPregleda.minimalnoTrajanjeMin}
          termin2={operacija.datumPocetka}
        />
      )}
      {operacija && (
        <Paper>
          <TextField
            variant="outlined"
            margin="normal"
            value={state.medSestraImePrezime}
            fullWidth
            id="iza"
            label="Medicinska sestra"
            name="sestra"
            autoComplete="off"
          />
          <Button variant="contained" color="primary" onClick={handleSubmit}>
            Nadji sestru
          </Button>
        </Paper>
      )}
    </div>
  );
};

function mapStateToProps(state) {
  return {
    operacija: state.operacija.operacija
  };
}

export default connect(mapStateToProps, { getOperacijaById })(OperacijaSala);
