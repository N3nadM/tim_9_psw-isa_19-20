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
import Grid from "@material-ui/core/Grid";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker
} from "@material-ui/pickers";
import Dijalog from "../../Tabs/BrzoZakazivanjePregleda/Dijalog";
import PretragaSlobodnihLekara from "../../Tabs/BrzoZakazivanjePregleda/PretragaSlobodnihLekara";
import DateFnsUtils from "@date-io/date-fns";
import { getListaDostupnihSala } from "../../../store/actions/sala";

let today = new Date();
today.setDate(today.getDate() + 1);
let d = new Date();
d.setDate(today.getDate());
const PregledSala = ({
  id,
  pregled,
  getPregledById,
  klinika,
  sale,
  termin,
  getListaDostupnihSala,
  promenjenLekar
}) => {
  useEffect(() => {
    getPregledById(id);
  }, []);

  const [state, setState] = React.useState({
    medSestraId: "",
    medSestraImePrezime: "",
    datumZaPregled: "",
    termin: ""
  });

  const [izbor, setIzbor] = React.useState(0);
  const [zaLekare, setZaLekare] = React.useState({
    tip: "",
    datum: ""
  });

  if (promenjenLekar) {
    pregled.lekar = promenjenLekar;
    getListaDostupnihSala(
      klinika.id,
      pregled.datumPocetka,
      pregled.tipPregleda.minimalnoTrajanjeMin
    );
  }

  const handleIzborTermin = e => {
    setIzbor(1);
  };

  const handleIzborLekar = e => {
    setZaLekare({
      tip: pregled.tipPregleda.naziv,
      datum: new Date(pregled.datumPocetka)
    });
    setIzbor(2);
  };

  const handleSubmit = async e => {
    e.preventDefault();
    const resp = await Axios.get(
      `/api/medsestra/sestraDostupna/${klinika.id}/${pregled.datumPocetka}/${pregled.tipPregleda.minimalnoTrajanjeMin}`
    );
    console.log(resp.data);
    if (resp.data != null && resp.data != "") {
      setState({
        ...state,
        medSestraId: resp.data.id,
        medSestraImePrezime:
          resp.data.korisnik.ime + " " + resp.data.korisnik.prezime
      });
    } else {
      setState({
        ...state,
        medSestraImePrezime: "Nema slobodnih sestara za taj termin"
      });
    }
  };

  const handleDateChange = date => {
    d = date;
    setState({
      ...state,
      datumZaPregled: date
    });
  };

  return (
    <div>
      <Paper style={{ margin: 50, padding: 50, paddingBottom: 75 }}>
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
      {pregled && sale.length !== 0 && (
        <PretragaSlobodnihSala
          klinikaId={klinika.id}
          trajanje={pregled.tipPregleda.minimalnoTrajanjeMin}
          termin2={pregled.datumPocetka}
        />
      )}
      {console.log(sale.length)}
      {pregled && sale.length === 0 && (
        <Paper style={{ padding: 50, paddingBottom: 75 }}>
          {izbor === 0 && (
            <>
              <Typography
                variant="h6"
                component="h2"
                style={{ marginBottom: 20, marginLeft: 13 }}
              >
                Za izabrani termin nema dostupnih sala, mozete promeniti termin
                ili izabrati drugog lekara
              </Typography>
              <Grid container spacing={2}>
                <Grid item sm={6}>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={handleIzborTermin}
                  >
                    Izabor termina
                  </Button>
                </Grid>
                <Grid item sm={6}>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={handleIzborLekar}
                  >
                    Izbor lekara
                  </Button>
                </Grid>
              </Grid>
            </>
          )}
          {izbor === 1 && (
            <>
              <Typography
                variant="h6"
                component="h2"
                style={{ marginBottom: 20, marginLeft: 13 }}
              >
                Izbor termina
              </Typography>
              <Grid container spacing={3}>
                <Grid item sm={6}>
                  <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <KeyboardDatePicker
                      id="date-picker"
                      format="MM/dd/yyyy"
                      minDate={today}
                      fullWidth
                      style={{ marginTop: 18, width: 300 }}
                      value={d}
                      onChange={e => handleDateChange(e)}
                      KeyboardButtonProps={{
                        "aria-label": "change date"
                      }}
                    />
                  </MuiPickersUtilsProvider>
                </Grid>
                <Grid item sm={6}>
                  <Dijalog
                    id={pregled.lekar.id}
                    datum={!state.datumZaPregled ? "" : state.datumZaPregled}
                    lekar={pregled.lekar}
                  />
                </Grid>
                <Grid item sm={12}>
                  <Button
                    variant="contained"
                    disabled={termin === ""}
                    color="primary"
                    onClick={e => {
                      e.preventDefault();
                      getListaDostupnihSala(
                        klinika.id,
                        termin,
                        pregled.tipPregleda.minimalnoTrajanjeMin
                      );
                    }}
                  >
                    Pretrazi sale
                  </Button>
                </Grid>
              </Grid>
            </>
          )}
          {izbor === 2 && (
            <>
              <Typography
                variant="h6"
                component="h2"
                style={{ marginBottom: 20, marginLeft: 13 }}
              >
                Izbor lekara
              </Typography>
              <PretragaSlobodnihLekara
                stariState={zaLekare}
                idKlinike={klinika.id}
                lekar={pregled.lekar}
              />
            </>
          )}
        </Paper>
      )}
      {pregled && (
        <Paper style={{ margin: 50, padding: 50, paddingBottom: 75 }}>
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
    pregled: state.pregled.pregled,
    sale: state.sala.listaDostupnihSala,
    termin: state.lekar.terminZaPregled,
    promenjenLekar: state.lekar.promenjenLekar
  };
}

export default connect(mapStateToProps, {
  getPregledById,
  getListaDostupnihSala
})(PregledSala);
