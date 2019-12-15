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
import { setPregled } from "../../../store/actions/pregled";

import Stepper from "@material-ui/core/Stepper";
import Step from "@material-ui/core/Step";
import StepLabel from "@material-ui/core/StepLabel";
import PretragaSlobodnihSestara from "../BrzoZakazivanjePregleda/PretragaSlobodnihSestara";
import ListaSlobodnihTermina from "../BrzoZakazivanjePregleda/ListaSlobodnihTermina";

function getSteps() {
  return [
    "Unos tipa pregleda, popusta i datuma",
    "Izbor lekara",
    "Izbor medicinske sestre",
    "Izbor sale"
  ];
}
let d = new Date();
function getStepContent(
  step,
  state,
  handleChange1,
  classes,
  tipoviPregleda,
  klinika,
  selectedDate,
  handleDateChange
) {
  const today = new Date();
  switch (step) {
    case 0:
      return (
        <>
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
                  style={{ height: 18 }}
                  labelId="demo-simple-select-placeholder-label-label"
                  id="tipPregledaId"
                  fullWidth
                  value={state.tipPregledaId}
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
            </Grid>
            <Grid item sm={6}>
              <MuiPickersUtilsProvider utils={DateFnsUtils}>
                <KeyboardDatePicker
                  id="date-picker-dialog"
                  format="MM/dd/yyyy"
                  minDate={today}
                  fullWidth
                  style={{ marginTop: 18 }}
                  value={d}
                  onChange={e => handleDateChange(e)}
                  KeyboardButtonProps={{
                    "aria-label": "change date"
                  }}
                />
              </MuiPickersUtilsProvider>
              <TextField
                id="popust"
                label="Popust"
                type="number"
                name="popust"
                value={state.popust}
                onChange={handleChange1}
                fullWidth
                className={classes.textField}
                inputProps={{ min: "0", max: "100", step: "1" }}
              />
            </Grid>
          </Grid>
        </>
      );
    case 1:
      return (
        <>
          <PretragaSlobodnihLekara stariState={state} idKlinike={klinika.id} />
        </>
      );
    case 2:
      return (
        <>
          <PretragaSlobodnihSestara
            klinikaId={klinika.id}
            trajanje={state.trajanje}
          />
        </>
      );
    case 3:
      return (
        <>
          <PretragaSlobodnihSala
            klinikaId={klinika.id}
            trajanje={state.trajanje}
          />
        </>
      );
    default:
      return "Unknown step";
  }
}
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
  lekarZaPregled,
  setPregled,
  salaZaPregled,
  medSestraId,
  termin
}) => {
  const [state, setState] = React.useState({
    cena: "",
    trajanje: "",
    tipPregledaId: "",
    tip: "",
    klinikaId: "",
    lekarId: "",
    datum: "",
    salaId: "",
    popust: "",
    medSestraId: ""
  });
  useEffect(() => {
    getAllTipoviPregleda(klinika.id);
    setState({
      ...state,
      lekarId: lekarZaPregled ? lekarZaPregled : ""
    });
  }, []);
  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);
  const [isEdit, setIsEdit] = React.useState(false);
  const [isSala, setIsSala] = React.useState(false);

  const handleNext = () => {
    setActiveStep(prevActiveStep => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep(prevActiveStep => prevActiveStep - 1);
  };

  const [selectedDate, setSelectedDate] = React.useState(new Date());

  const handleDateChange = date => {
    setSelectedDate(date);
    d = date;
    state.datum = date;
    console.log(state.datum);
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
    setState({
      ...state,
      salaId: salaZaPregled,
      lekarId: lekarZaPregled,
      [e.target.name]: e.target.value
    });
    {
      e.target.name === "tipPregledaId" && postaviCenuTrajanje(e.target.value);
    }
  };

  const handleChangeIndex = index => {
    setValue(index);
  };

  const handleSubmit = e => {
    const state2 = {
      lekarId: lekarZaPregled,
      salaId: salaZaPregled,
      medSestraId: medSestraId,
      datum: termin,
      popust: state.popust,
      tipPregledaId: state.tipPregledaId
    };
    console.log(state2);
    setIsEdit(true);
    e.preventDefault();
    setPregled(state2);
  };

  const steps = getSteps();
  const [activeStep, setActiveStep] = useState(0);

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
        {!isEdit ? (
          <div>
            <div
              style={{
                marginTop: 64,
                display: "flex",
                flexDirection: "column",
                alignItems: "center"
              }}
            >
              <Typography component="h1" variant="h5">
                Unos novog termina pregleda
              </Typography>
            </div>
            <Stepper activeStep={activeStep}>
              {steps.map((label, index) => {
                return (
                  <Step key={label}>
                    <StepLabel>{label}</StepLabel>
                  </Step>
                );
              })}
            </Stepper>
            <form className={classes.form} noValidate onSubmit={handleSubmit}>
              <div className={classes.instructions}>
                {getStepContent(
                  activeStep,
                  state,
                  handleChange1,
                  classes,
                  tipoviPregleda,
                  klinika,
                  selectedDate,
                  handleDateChange
                )}
              </div>
              <Button
                disabled={activeStep === 0}
                onClick={handleBack}
                className={classes.button}
                style={{ marginTop: 10 }}
              >
                Nazad
              </Button>

              {activeStep !== steps.length - 1 ? (
                <Button
                  style={{ marginTop: 10 }}
                  variant="contained"
                  color="primary"
                  onClick={handleNext}
                  className={classes.button}
                >
                  Dalje
                </Button>
              ) : (
                <Button
                  variant="contained"
                  color="primary"
                  className={classes.submit}
                  onClick={handleSubmit}
                >
                  Sačuvajte
                </Button>
              )}
            </form>{" "}
          </div>
        ) : (
          <div>
            <Typography style={{ marginTop: 40 }} variant="h6">
              Uspesno ste kreirali slobodan termin pregleda koji pacijenti mogu
              da rezervisu jednim klikom.
            </Typography>
          </div>
        )}
      </TabPanel>

      <TabPanel value={value} index={1} dir={theme.direction}>
        {value === 1 && <ListaSlobodnihTermina klinikaId={klinika.id} />}
      </TabPanel>
    </div>
  );
};
const mapStateToProps = state => ({
  tipoviPregleda: state.tipoviPregleda,
  adminKlinike: state.adminKlinike,
  lekarZaPregled: state.lekar.lekarZaPregled,
  salaZaPregled: state.sala.salaZaPregled,
  termin: state.lekar.terminZaPregled,
  medSestraId: state.medsestra.sestraZaPregled
});

export default connect(mapStateToProps, {
  getAllTipoviPregleda,
  getKlinikaAdmin,
  setPregled
})(SlobodniTerminiTabs);
