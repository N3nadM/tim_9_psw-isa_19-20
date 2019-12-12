import React, { useEffect, useState } from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { getAllTipoviPregleda } from "../../../store/actions/tipoviPregleda";
import { getKlinikaAdmin } from "../../../store/actions/adminKlinike";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";

import MenuItem from "@material-ui/core/MenuItem";
import FormHelperText from "@material-ui/core/FormHelperText";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import { connect } from "react-redux";
import Grid from "@material-ui/core/Grid";
import PretragaSlobodnihLekara from "../BrzoZakazivanjePregleda/PretragaSlobodnihLekara";
import PretragaSlobodnihSala from "../BrzoZakazivanjePregleda/PretragaSlobodnihSala";
import Autocomplete from "@material-ui/lab/Autocomplete";
import { stat } from "fs";
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

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.any.isRequired,
  value: PropTypes.any.isRequired
};

function a11yProps(index) {
  return {
    id: `full-width-tab-${index}`,
    "aria-controls": `full-width-tabpanel-${index}`
  };
}

const useStyles = makeStyles(theme => ({
  root: {
    backgroundColor: theme.palette.background.paper,
    flexGrow: 1
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1)
  }
}));

const SlobodniTerminiTabs = ({
  tipoviPregleda: { tipoviPregleda },
  adminKlinike: { klinika },
  getKlinikaAdmin,
  getAllTipoviPregleda,
  lekarZaPregled
}) => {
  const [state, setState] = React.useState({
    cena: "",
    trajanje: "",
    tipPregledaId: "",
    tip: "",
    klinikaId: "",
    lekarId: "",
    datum: "",
    sala: ""
  });
  useEffect(() => {
    getAllTipoviPregleda(klinika.id);
    setState({
      ...state,
      lekarId: lekarZaPregled ? lekarZaPregled.korisnik.ime : ""
    });
  }, []);
  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);
  const [isEdit, setIsEdit] = React.useState(false);
  const [isSala, setIsSala] = React.useState(false);

  const [selectedDate, setSelectedDate] = React.useState(
    new Date("2019-12-18T21:11:54")
  );

  const handleDateChange = date => {
    setSelectedDate(date);
    setState({
      ...state,
      datum: date
    });
  };
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };
  const postaviCenuTrajanje = tip => {
    {
      tipoviPregleda &&
        tipoviPregleda.map(tipoviPregleda => {
          tipoviPregleda.id === tip &&
            setState({
              ...state,
              cena: tipoviPregleda.cenaPregleda,
              trajanje: tipoviPregleda.minimalnoTrajanjeMin,
              tipPregledaId: tip,
              tip: tipoviPregleda.naziv
            });
        });
    }
  };
  const handleChange1 = e => {
    setState({ ...state, [e.target.name]: e.target.value });
    {
      e.target.name === "tipPregledaId" && postaviCenuTrajanje(e.target.value);
    }
  };

  const handleChangeIndex = index => {
    setValue(index);
  };

  return (
    <div className={classes.root}>
      <Tabs
        value={value}
        onChange={handleChange}
        indicatorColor="primary"
        textColor="primary"
      >
        <Tab label="Unos novog termina pregleda" {...a11yProps(0)} />
        <Tab label="Slobodni termini pregleda" {...a11yProps(1)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        {isEdit && !isSala && (
          <PretragaSlobodnihLekara
            stariState={state}
            idKlinike={klinika.id}
            setIsEdit={setIsEdit}
          />
        )}
        {!isEdit && !isSala && (
          <form className={classes.form} noValidate>
            <Grid container spacing={3}>
              <Grid item sm={6}>
                <FormControl
                  className={classes.form}
                  fullWidth
                  style={{ marginTop: 16 }}
                >
                  <InputLabel
                    shrink
                    id="demo-simple-select-placeholder-label-label"
                  >
                    Tip pregleda
                  </InputLabel>
                  <Select
                    labelId="demo-simple-select-placeholder-label-label"
                    id="tipPregledaId"
                    displayEmpty
                    fullWidth
                    onChange={handleChange1}
                    name="tipPregledaId"
                  >
                    {tipoviPregleda &&
                      tipoviPregleda.map(tipoviPregleda => (
                        <MenuItem
                          key={tipoviPregleda.id}
                          value={tipoviPregleda.id}
                        >
                          {tipoviPregleda.naziv}
                        </MenuItem>
                      ))}
                  </Select>
                  <FormHelperText>Izaberite tip pregleda</FormHelperText>
                </FormControl>
                <TextField
                  id="cena"
                  label="Cena"
                  type="text"
                  value={state.cena}
                  disabled={true}
                  fullWidth
                  className={classes.textField}
                  InputLabelProps={{
                    shrink: true
                  }}
                />
                <TextField
                  id="trajanje"
                  label="Trajanje pregleda"
                  type="text"
                  fullWidth
                  disabled={true}
                  value={state.trajanje}
                  className={classes.textField}
                  InputLabelProps={{
                    shrink: true
                  }}
                />

                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                  <KeyboardDatePicker
                    id="date-picker-dialog"
                    label="Datum pregleda"
                    format="MM/dd/yyyy"
                    value={selectedDate}
                    onChange={handleDateChange}
                    KeyboardButtonProps={{
                      "aria-label": "change date"
                    }}
                  />
                </MuiPickersUtilsProvider>
                <TextField
                  id="popust"
                  label="Popust"
                  type="number"
                  fullWidth
                  className={classes.textField}
                  inputProps={{ min: "0", max: "100", step: "1" }}
                />
              </Grid>
              <Grid item sm={6}>
                <TextField
                  id="sala"
                  label="Sala"
                  type="text"
                  disabled={true}
                  value={state.sala}
                  fullWidth
                  className={classes.textField}
                  inputProps={{ min: "0", max: "100", step: "1" }}
                />
                <Button
                  variant="contained"
                  color="primary"
                  className={classes.submit}
                  onClick={() => setIsSala(true)}
                >
                  Pronadji salu
                </Button>
                <TextField
                  id="lekar"
                  label="Lekar"
                  type="text"
                  disabled={true}
                  value={state.lekarId}
                  fullWidth
                  className={classes.textField}
                  inputProps={{ min: "0", max: "100", step: "1" }}
                />
                <Button
                  variant="contained"
                  color="primary"
                  className={classes.submit}
                  onClick={() => setIsEdit(true)}
                >
                  Pronadji lekara
                </Button>
              </Grid>
              <Button
                type="submit"
                variant="contained"
                color="primary"
                className={classes.submit}
              >
                Sačuvajte
              </Button>
            </Grid>
          </form>
        )}
        {!isEdit && isSala && <PretragaSlobodnihSala klinikaId={klinika.id} />}
      </TabPanel>

      <TabPanel value={value} index={1} dir={theme.direction}>
        Lista slobodnih termina pregleda
      </TabPanel>
    </div>
  );
};
const mapStateToProps = state => ({
  tipoviPregleda: state.tipoviPregleda,
  adminKlinike: state.adminKlinike,
  lekarZaPregled: state.lekar.lekarZaPregled
});

export default connect(mapStateToProps, {
  getAllTipoviPregleda,
  getKlinikaAdmin
})(SlobodniTerminiTabs);
