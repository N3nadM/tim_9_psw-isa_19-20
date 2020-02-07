import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getOperacijaById } from "../../../store/actions/operacija";

import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import { useTheme } from "@material-ui/core/styles";
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
import Dijalog from "../../Tabs/BrzoZakazivanjePregleda/DijalogOperacija";
import DateFnsUtils from "@date-io/date-fns";
import { getListaDostupnihSala } from "../../../store/actions/sala";
import DodavanjeLekaraOperacija from "./DodavanjeLekaraOperacija";
import SaleSaSlobodnimTerminima from "../PronalazenjeSale/SaleSaSlobodnimTerminima";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Box from "@material-ui/core/Box";

let today = new Date();
today.setDate(today.getDate() + 1);
let d = new Date();
d.setDate(today.getDate());

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <Typography
      component="div"
      role="tabpanel"
      hidden={value !== index}
      id={`full-width-tabpanel-${index}`}
      aria-labelledby={`full-width-tab-${index}`}
      {...other}
    >
      <Box p={3}>{children}</Box>
    </Typography>
  );
}

function a11yProps(index) {
  return {
    id: `full-width-tab-${index}`,
    "aria-controls": `full-width-tabpanel-${index}`
  };
}

const OperacijaSala = ({
  id,
  operacija,
  getOperacijaById,
  klinika,
  sale,
  termin,
  getListaDostupnihSala,
  salaZaOperaciju,
  lekariZaOperaciju,
  terminZaSalu
}) => {
  useEffect(() => {
    getOperacijaById(id);
  }, [getOperacijaById, id]);

  const [state, setState] = React.useState({
    medSestraId: "",
    medSestraImePrezime: "",
    datumZaOperaciju: ""
  });

  const theme = useTheme();

  const handleSubmit = async e => {
    e.preventDefault();

    let odabranTermin;
    if (terminZaSalu !== null) {
      odabranTermin = terminZaSalu["_data"]["♠" + salaZaOperaciju][1];
    } else {
      odabranTermin = operacija.datumPocetka;
    }

    const resp = await Axios.get(
      `/api/medsestra/sestraDostupna/${klinika.id}/${odabranTermin}/${operacija.tipPregleda.minimalnoTrajanjeMin}`
    );
    console.log(resp.data);
    if (resp.data != null && resp.data !== "") {
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

  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleDateChange = date => {
    d = date;
    setState({
      ...state,
      datumZaOperaciju: date
    });
  };
  const [izbor, setIzbor] = React.useState(0);
  const [zaLekare, setZaLekare] = React.useState({
    tip: "",
    datum: ""
  });

  const handleIzborLekar = e => {
    setZaLekare({
      tip: operacija.tipPregleda.naziv,
      datum: new Date(operacija.datumPocetka)
    });
    setIzbor(2);
  };

  const handleZakazi = async e => {
    let t;
    if (terminZaSalu !== null) {
      t = terminZaSalu["_data"]["♠" + salaZaOperaciju][1];
    } else {
      t = termin != "" ? termin : operacija.datumPocetka;
    }
    lekariZaOperaciju.push(operacija.lekari[0].id);
    const podaci = {
      medSestraId: state.medSestraId,
      salaId: salaZaOperaciju,
      lekariId: lekariZaOperaciju,
      operacijaId: operacija.id,
      termin: t
    };
    const resp = await Axios.post(`/api/operacija/sacuvajSalu`, podaci);
    console.log(resp.data);
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
      {operacija && sale.length !== 0 && (
        <PretragaSlobodnihSala
          klinikaId={klinika.id}
          trajanje={operacija.tipPregleda.minimalnoTrajanjeMin}
          termin2={operacija.datumPocetka}
        />
      )}

      {operacija && sale.length === 0 && (
        <>
          <Tabs
            value={value}
            onChange={handleChange}
            indicatorColor="primary"
            textColor="primary"
          >
            <Tab label="Prvi slobodni termini za sale" {...a11yProps(0)} />

            <Tab label="Izbor novog termina" {...a11yProps(1)} />
          </Tabs>
          <TabPanel value={value} index={0} dir={theme.direction}>
            <SaleSaSlobodnimTerminima
              lekar={operacija.lekari[0]}
              klinika={klinika}
              trajanje={operacija.tipPregleda.minimalnoTrajanjeMin}
              terminZaPregled={""}
              daLiTrebaDaSeTrazeTermini={true}
              terminZaOperaciju={operacija.datumPocetka}
              setIzbor={setIzbor}
            />
          </TabPanel>

          <TabPanel value={value} index={1} dir={theme.direction}>
            <Paper style={{ padding: 50, paddingBottom: 75 }}>
              <Typography
                variant="h6"
                component="h2"
                style={{ marginBottom: 20, marginLeft: 13 }}
              >
                Za izabrani termin nema dostupnih sala, mozete promeniti termin
                ili izabrati druge lekare
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
                    id={operacija.lekari[0].id}
                    datum={
                      !state.datumZaOperaciju ? "" : state.datumZaOperaciju
                    }
                    lekar={operacija.lekari[0]}
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
                        operacija.tipPregleda.minimalnoTrajanjeMin
                      );
                    }}
                  >
                    Pretrazi sale
                  </Button>
                </Grid>
              </Grid>
            </Paper>
          </TabPanel>
        </>
      )}
      <Grid item sm={12}>
        <Button
          variant="contained"
          disabled={salaZaOperaciju === "" || izbor === 2}
          color="primary"
          onClick={handleIzborLekar}
        >
          Dodavanje lekara
        </Button>
      </Grid>
      {salaZaOperaciju !== "" && izbor === 2 && (
        <>
          <Typography
            variant="h6"
            component="h2"
            style={{ marginBottom: 20, marginLeft: 13 }}
          >
            Izbor lekara
          </Typography>
          <DodavanjeLekaraOperacija
            stariState={zaLekare}
            idKlinike={klinika.id}
            lekar={operacija.lekari[0]}
          />
        </>
      )}
      {operacija && (
        <Paper style={{ padding: 50, paddingBottom: 75 }}>
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
      {operacija && (
        <Button
          variant="contained"
          color="primary"
          onClick={handleZakazi}
          disabled={state.medSestraId === "" || salaZaOperaciju === ""}
        >
          Sačuvaj
        </Button>
      )}
    </div>
  );
};

function mapStateToProps(state) {
  return {
    operacija: state.operacija.operacija,
    sale: state.sala.listaDostupnihSala,
    termin: state.lekar.terminZaOperaciju,
    salaZaOperaciju: state.sala.salaZaPregled,
    lekariZaOperaciju: state.lekar.lekariZaOperaciju,
    terminZaSalu: state.sala.slobodniTerminiSala
  };
}

export default connect(mapStateToProps, {
  getOperacijaById,
  getListaDostupnihSala
})(OperacijaSala);
