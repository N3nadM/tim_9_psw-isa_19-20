import React, { useEffect } from "react";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import PropTypes from "prop-types";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import { getLekar } from "../../../store/actions/lekar";
import Dijalog from "../BrzoZakazivanjePregleda/Dijalog";
import DijalogOperacija from "../BrzoZakazivanjePregleda/DijalogOperacija";

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
let d = new Date();
let today = new Date();
today.setDate(today.getDate() + 1);
let d1 = new Date();
d.setDate(today.getDate());
d1.setDate(today.getDate());
function a11yProps(index) {
  return {
    id: `full-width-tab-${index}`,
    "aria-controls": `full-width-tabpanel-${index}`
  };
}

const useStyles = makeStyles(theme => ({
  FormControl: {
    minWidth: 120,
    maxWidth: 300
  },
  root: {
    backgroundColor: theme.palette.background.paper,
    flexGrow: 1
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1)
  }
}));

const PregledOperacijaTab = ({
  terminZaPregled,
  terminZaOperaciju,
  korisnikId,
  lekar,
  obj //pregled
}) => {
  const [state, setState] = React.useState({
    medSestraId: obj.medicinskaSestra.id,
    vrsta: obj.vrsta,
    idPregledOperacija: obj.id,
    datumZaPregled: "",
    datumZaOperaciju: ""
  });

  useEffect(() => {
    getLekar(korisnikId);
  }, []);

  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);
  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleSubmit = e => {
    e.preventDefault();
    obj.stanje = 2;
  };

  const [selectedDate, setSelectedDate] = React.useState(new Date());

  const handleDateChange = date => {
    setSelectedDate(date);
    d = date;
    state.datumZaPregled = date;
    console.log(state.datumZaPregled);
  };

  const handleDateChange1 = date => {
    setSelectedDate(date);
    d1 = date;
    state.datumZaOperaciju = date;
    console.log(state.datumZaOperaciju);
  };

  return (
    <div className={classes.root}>
      <Tabs
        value={value}
        onChange={handleChange}
        indicatorColor="primary"
        textColor="primary"
      >
        <Tab label="Zakazivanje pregleda" {...a11yProps(0)} />
        <Tab label="Zakazivanje operacije" {...a11yProps(1)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        <Typography component="div" role="tabpanel">
          <Box>Izaberite datum za pregled</Box>
        </Typography>
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <KeyboardDatePicker
            id="date-picker"
            format="MM/dd/yyyy"
            minDate={today}
            fullWidth
            style={{ marginTop: 18, width: 765 }}
            value={d}
            onChange={e => handleDateChange(e)}
            KeyboardButtonProps={{
              "aria-label": "change date"
            }}
          />
          <Typography style={{ marginTop: 18 }} component="div" role="tabpanel">
            <Box>Izaberite termin pregleda za izabrani datum</Box>
          </Typography>
          <Grid container spacing={3}>
            <Grid item xs={9}>
              <TextField
                value={terminZaPregled}
                margin="normal"
                required
                fullWidth
                disabled
                name="termin"
                type="text"
                id="termin"
              />
            </Grid>
            <Grid item xs={3}>
              <Dijalog
                id={lekar.id}
                datum={!state.datumZaPregled ? "" : state.datumZaPregled}
                lekar={lekar}
              />
            </Grid>
          </Grid>
          <Button
            variant="contained"
            disabled={terminZaPregled === ""}
            style={{ marginTop: 18, marginLeft: 10 }}
            color="primary"
          >
            Zakazi
          </Button>
        </MuiPickersUtilsProvider>
      </TabPanel>
      <TabPanel value={value} index={1} dir={theme.direction}>
        <MuiPickersUtilsProvider utils={DateFnsUtils}>
          <Typography component="div" role="tabpanel">
            <Box>Izaberite datum za operaciju</Box>
          </Typography>
          <KeyboardDatePicker
            id="date-picker-dialog"
            format="MM/dd/yyyy"
            minDate={today}
            fullWidth
            style={{ marginTop: 18, width: 765 }}
            value={d1}
            onChange={e => handleDateChange1(e)}
            KeyboardButtonProps={{
              "aria-label": "change date"
            }}
          />
          <Typography style={{ marginTop: 18 }} component="div" role="tabpanel">
            <Box>Izaberite termin operacije za izabrani datum</Box>
          </Typography>
          <Grid container spacing={3}>
            <Grid item xs={9}>
              <TextField
                value={terminZaOperaciju}
                margin="normal"
                required
                fullWidth
                disabled
                name="termin"
                type="text"
                id="termin"
              />
            </Grid>
            <Grid item xs={3}>
              <DijalogOperacija
                id={lekar.id}
                datum={!state.datumZaOperaciju ? "" : state.datumZaOperaciju}
                lekar={lekar}
              />
            </Grid>
          </Grid>
        </MuiPickersUtilsProvider>
        <Button
          variant="contained"
          disabled={terminZaOperaciju === ""}
          style={{ marginTop: 18, marginLeft: 10 }}
          color="primary"
        >
          Zakazi
        </Button>
      </TabPanel>
    </div>
  );
};

const mapStateToProps = state => ({
  korisnikId: state.currentUser.user.id,
  lekar: state.lekar.lekar,
  terminZaPregled: state.lekar.terminZaPregled,
  terminZaOperaciju: state.lekar.terminZaOperaciju
});

export default connect(mapStateToProps, {})(PregledOperacijaTab);
