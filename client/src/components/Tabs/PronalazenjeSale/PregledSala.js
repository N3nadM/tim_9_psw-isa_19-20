import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getPregledById } from "../../../store/actions/pregled";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Typography from "@material-ui/core/Typography";
import { Paper, TextField } from "@material-ui/core";
import Skeleton from "@material-ui/lab/Skeleton";
import PretragaSlobodnihSala from "../BrzoZakazivanjePregleda/PretragaSlobodnihSala";
import Axios from "axios";
import Button from "@material-ui/core/Button";

const PregledSala = ({ id, pregled, getPregledById, klinika }) => {
  useEffect(() => {
    getPregledById(id);
  }, []);

  const [state, setState] = React.useState({
    medSestraId: "",
    medSestraImePrezime: ""
  });
  const handleSubmit = async e => {
    e.preventDefault();
    const resp = await Axios.get(
      `/api/medsestra/sestraDostupna/${klinika.id}/${pregled.datumPocetka}/${pregled.tipPregleda.minimalnoTrajanjeMin}`
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
          Pregled
        </Typography>
        {!pregled && <Skeleton height={350} />}
        {pregled && (
          <>
            <List disablePadding>
              <ListItem>
                <ListItemText primary="Lekar" />
                <Typography variant="subtitle1">
                  {pregled.lekar.korisnik.ime}
                </Typography>
              </ListItem>
              <ListItem>
                <ListItemText primary="Pacijent" />
                <Typography variant="subtitle1">
                  {pregled.pacijent.korisnik.ime}
                </Typography>
              </ListItem>
              <ListItem>
                <ListItemText primary="Datum pocetka" />
                <Typography variant="subtitle1">
                  {pregled.datumPocetka}
                </Typography>
              </ListItem>
            </List>
          </>
        )}
      </Paper>
      {pregled && (
        <PretragaSlobodnihSala
          klinikaId={klinika.id}
          trajanje={pregled.tipPregleda.minimalnoTrajanjeMin}
          termin2={pregled.datumPocetka}
        />
      )}
      {pregled && (
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
    pregled: state.pregled.pregled
  };
}

export default connect(mapStateToProps, { getPregledById })(PregledSala);
