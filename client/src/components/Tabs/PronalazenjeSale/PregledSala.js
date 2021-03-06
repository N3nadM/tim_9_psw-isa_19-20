import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getPregledById } from "../../../store/actions/pregled";
import { useTheme } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Typography from "@material-ui/core/Typography";
import { Paper, TextField } from "@material-ui/core";
import Skeleton from "@material-ui/lab/Skeleton";
import PretragaSala from "../BrzoZakazivanjePregleda/PretragaSlobodnihSala";
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
import {
  getListaDostupnihSala,
  setListaDostupnihSala
} from "../../../store/actions/sala";
import SaleSaSlobodnimTerminima from "../PronalazenjeSale/SaleSaSlobodnimTerminima";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Box from "@material-ui/core/Box";
import Dialog from "@material-ui/core/Dialog";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";

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

const PregledSala = ({
  id,
  pregled,
  getPregledById,
  klinika,
  sale,
  termin,
  terminZaSalu,
  getListaDostupnihSala,
  promenjenLekar,
  lekarZaPregled,
  sala,
  setListaDostupnihSala
}) => {
  useEffect(() => {
    getPregledById(id);
  }, [getPregledById, id]);

  const [state, setState] = React.useState({
    medSestraId: "",
    medSestraImePrezime: "",
    datumZaPregled: "",
    termin: ""
  });
  const theme = useTheme();
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

  const handleIzborLekar = e => {
    setZaLekare({
      tip: pregled.tipPregleda.naziv,
      datum: new Date(pregled.datumPocetka)
    });
    setIzbor(2);
  };

  const handleSubmit = async e => {
    e.preventDefault();
    let odabranTermin;
    if (terminZaSalu !== null) {
      odabranTermin = terminZaSalu["_data"]["♠" + sala][1];
    } else {
      odabranTermin = pregled.datumPocetka;
    }

    const resp = await Axios.get(
      `/api/medsestra/sestraDostupna/${klinika.id}/${odabranTermin}/${pregled.tipPregleda.minimalnoTrajanjeMin}`
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

  const handleDateChange = date => {
    d = date;
    setState({
      ...state,
      datumZaPregled: date
    });
  };
  const [open, setOpen] = React.useState(false);

  const handleClose = () => {
    setOpen(false);
  };

  const handleZakazi = async e => {
    let t;
    if (terminZaSalu !== null) {
      t = terminZaSalu["_data"]["♠" + sala][1];
    } else {
      t = termin != "" ? termin : pregled.datumPocetka;
    }
    const podaci = {
      medSestraId: state.medSestraId,
      salaId: sala,
      lekarId: lekarZaPregled.id,
      pregledId: pregled.id,
      termin: t
    };
    const resp = await Axios.post(`/api/pregled/sacuvajSalu`, podaci);
    setOpen(true);
  };
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
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
      {pregled && sale.length !== 0 && (
        <PretragaSala
          klinikaId={klinika.id}
          trajanje={pregled.tipPregleda.minimalnoTrajanjeMin}
          termin2={pregled.datumPocetka}
        />
      )}
      {pregled && sale.length === 0 && (
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
              lekar={pregled.lekar}
              klinika={klinika}
              trajanje={pregled.tipPregleda.minimalnoTrajanjeMin}
              terminZaPregled={pregled.datumPocetka}
              daLiTrebaDaSeTrazeTermini={true}
              terminZaOperaciju={""}
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
                ili izabrati drugog lekara
              </Typography>
              {izbor === 0 && (
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
                        id={lekarZaPregled.id}
                        datum={
                          !state.datumZaPregled ? "" : state.datumZaPregled
                        }
                        lekar={lekarZaPregled}
                      />
                    </Grid>

                    <Grid item sm={12}>
                      <TextField
                        variant="outlined"
                        margin="normal"
                        value={lekarZaPregled.korisnik.ime}
                        fullWidth
                        id="lekar"
                        label="Lekar"
                        name="lekar"
                        autoComplete="off"
                      />
                    </Grid>
                    <Grid item sm={12}>
                      <Button
                        variant="contained"
                        color="primary"
                        onClick={e => handleIzborLekar(e)}
                      >
                        Promena lekara
                      </Button>
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
                  </Grid>{" "}
                </>
              )}
              <>
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
                      lekar={lekarZaPregled}
                      setIzbor={setIzbor}
                    />
                  </>
                )}
              </>
            </Paper>
          </TabPanel>
        </>
      )}
      {pregled && (
        <Paper style={{ padding: 50, paddingBottom: 75 }}>
          <TextField
            variant="outlined"
            margin="normal"
            value={state.medSestraImePrezime}
            fullWidth
            id="nadjiS"
            label="Medicinska sestra"
            name="sestra"
            autoComplete="off"
          />
          <Button
            variant="contained"
            color="primary"
            id="nadjiSestru"
            onClick={handleSubmit}
          >
            Nadji sestru
          </Button>
        </Paper>
      )}
      {pregled && (
        <Button
          id="sacuvaj"
          variant="contained"
          color="primary"
          onClick={handleZakazi}
          disabled={state.medSestraId === "" || sala === ""}
        >
          Sačuvaj
        </Button>
      )}
      <Dialog
        open={open}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">
          {"Biranje sale i medicinske sestre"}
        </DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            Uneti podaci su sacuvani
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary" autoFocus>
            OK
          </Button>
        </DialogActions>
      </Dialog>
    </div>
  );
};

function mapStateToProps(state) {
  return {
    pregled: state.pregled.pregled,
    promenjenLekar: state.lekar.promenjenLekar,
    sale: state.sala.listaDostupnihSala,
    termin: state.lekar.terminZaPregled,
    lekarZaPregled: state.lekar.lekarZaPregled,
    sala: state.sala.salaZaPregled,
    terminZaSalu: state.sala.slobodniTerminiSala
  };
}

export default connect(mapStateToProps, {
  getPregledById,
  getListaDostupnihSala,
  setListaDostupnihSala
})(PregledSala);
