import React, { useEffect } from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
import { getKlinikaAdmin } from "../../store/actions/adminKlinike";

import MenuItem from "@material-ui/core/MenuItem";
import FormHelperText from "@material-ui/core/FormHelperText";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import Button from "@material-ui/core/Button";
import { Grid } from "@material-ui/core";
import { connect } from "react-redux";
import { getAllTipoviPregleda } from "../../store/actions/tipoviPregleda";
import { addNewLekar } from "../../store/actions/lekar";
import PretragLekaraTab from "./PretragaLekaraTab";
import BrisanjeLekaraTab from "../Tabs/Lekar/BrisanjeLekaraTab";

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
  }
}));

const LekariTabs = ({
  tipoviPregleda: { tipoviPregleda },
  adminKlinike: { klinika },
  korisnikId,
  getAllTipoviPregleda,
  getKlinikaAdmin,
  addNewLekar
}) => {
  const [state, setState] = React.useState({
    ime: "",
    prezime: "",
    email: "",
    password: "",
    drzava: "",
    grad: "",
    adresa: "",
    telefon: "",
    tipPregledaId: "",
    klinikaId: "",
    pocetakRadnogVremena: "",
    krajRadnogVremena: ""
  });
  useEffect(() => {
    getAllTipoviPregleda(klinika.id);
    //eslint-disable-next-line
  }, []);
  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleSubmit = e => {
    e.preventDefault();
    state.klinikaId = klinika.id;
    addNewLekar(state);
    setState({
      ime: "",
      prezime: "",
      email: "",
      password: "",
      drzava: "",
      grad: "",
      adresa: "",
      telefon: "",
      tipPregledaId: "",
      klinikaId: "",
      krajRadnogVremena: "",
      pocetakRadnogVremena: ""
    });
  };
  const handleChange1 = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  return (
    <div className={classes.root}>
      <Tabs
        value={value}
        onChange={handleChange}
        indicatorColor="primary"
        textColor="primary"
      >
        <Tab label="Unos novog lekara" {...a11yProps(0)} />
        <Tab label="Pretraga lekara" {...a11yProps(1)} />
        <Tab label="Izmena lekara" {...a11yProps(2)} />
        <Tab label="Uklanjanje lekara" {...a11yProps(3)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        <form className={classes.form} onSubmit={handleSubmit}>
          <Grid container spacing={10}>
            <Grid item sm={6}>
              <TextField
                margin="normal"
                value={state.ime}
                onChange={handleChange1}
                required
                fullWidth
                name="ime"
                label="Ime"
                type="text"
                id="imeLekar"
              />
              <TextField
                margin="normal"
                value={state.prezime}
                required
                fullWidth
                onChange={handleChange1}
                name="prezime"
                label="Prezime"
                type="text"
                id="prezimeLekar"
              />
              <TextField
                margin="normal"
                required
                value={state.drzava}
                fullWidth
                onChange={handleChange1}
                name="drzava"
                label="Drzava"
                type="text"
                id="drzava"
              />
              <TextField
                margin="normal"
                required
                value={state.grad}
                fullWidth
                onChange={handleChange1}
                name="grad"
                label="Grad"
                type="text"
                id="grad"
              />
              <TextField
                margin="normal"
                value={state.adresa}
                required
                onChange={handleChange1}
                fullWidth
                name="adresa"
                label="Adresa"
                type="text"
                id="Adresa"
              />
              <TextField
                margin="normal"
                value={state.telefon}
                required
                onChange={handleChange1}
                fullWidth
                name="telefon"
                label="Telefon"
                type="text"
                id="telefon"
              />
            </Grid>
            <Grid item sm={5}>
              <TextField
                margin="normal"
                required
                fullWidth
                value={state.password}
                onChange={handleChange1}
                name="password"
                label="Password"
                type="password"
                id="passwordLekar"
              />
              <TextField
                margin="normal"
                required
                value={state.email}
                fullWidth
                onChange={handleChange1}
                name="email"
                label="Email"
                type="email"
                id="emailLekar"
              />
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
                  id="tipoviPregleda"
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
                style={{ marginTop: 5 }}
                id="pocetakRadnogVremena"
                label="Početak radnog vremena"
                type="time-local"
                name="pocetakRadnogVremena"
                value={state.pocetakRadnogVremena}
                onChange={handleChange1}
                required
                fullWidth
              />
              <TextField
                style={{ marginTop: 24 }}
                id="krajRadnogVremena"
                label="Kraj radnog vremena"
                type="time-local"
                name="krajRadnogVremena"
                value={state.krajRadnogVremena}
                onChange={handleChange1}
                required
                fullWidth
              />

              <Button
                style={{ marginTop: 24 }}
                type="submit"
                variant="contained"
                color="primary"
                className={classes.submit}
              >
                Sačuvajte
              </Button>
            </Grid>
          </Grid>
        </form>
      </TabPanel>
      <TabPanel value={value} index={1} dir={theme.direction}>
        {value === 1 && <PretragLekaraTab klinikaId={klinika.id} />}
      </TabPanel>
      <TabPanel value={value} index={2} dir={theme.direction}>
        Lista lekara ciji podaci mogu da se menjaju
      </TabPanel>
      <TabPanel value={value} index={3} dir={theme.direction}>
        {value === 3 && <BrisanjeLekaraTab />}
      </TabPanel>
    </div>
  );
};
const mapStateToProps = state => ({
  tipoviPregleda: state.tipoviPregleda,
  adminKlinike: state.adminKlinike,
  korisnikId: state.currentUser.user.id
});

export default connect(mapStateToProps, {
  getAllTipoviPregleda,
  getKlinikaAdmin,
  addNewLekar
})(LekariTabs);
