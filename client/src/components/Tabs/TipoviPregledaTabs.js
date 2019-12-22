import React from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";

import Button from "@material-ui/core/Button";
import { addNewTipPregleda } from "../../store/actions/tipoviPregleda";
import PretragaTipovaPregleda from "../Tabs/TipoviPregleda/PretragaTipovaPregleda";
import IzmenaBrisanjeTipova from "../Tabs/TipoviPregleda/IzmenaBrisanjeTipova";

import { connect } from "react-redux";
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

const TipoviPregledaTabs = ({
  adminKlinike: { klinika },
  addNewTipPregleda
}) => {
  const [state, setState] = React.useState({
    naziv: "",
    cenaPregleda: "",
    cenaOperacije: "",
    minimalnoTrajanjeMin: "",
    klinikaId: ""
  });
  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleChange1 = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  const handleSubmit = e => {
    e.preventDefault();
    state.klinikaId = klinika.id;
    addNewTipPregleda(state);
    setState({
      naziv: "",
      cenaPregleda: "",
      cenaOperacije: "",
      minimalnoTrajanjeMin: "",
      klinikaId: ""
    });
  };

  return (
    <div className={classes.root}>
      <Tabs
        value={value}
        onChange={handleChange}
        indicatorColor="primary"
        textColor="primary"
      >
        <Tab label="Pretraga tipova pregleda" {...a11yProps(0)} />
        <Tab label="Unos novog tipa pregleda" {...a11yProps(1)} />
        <Tab label="Izmena i brisanje tipa pregleda" {...a11yProps(2)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        {value === 0 && <PretragaTipovaPregleda />}
      </TabPanel>
      <TabPanel value={value} index={1} dir={theme.direction}>
        <p>Unesite podatke o tipu pregleda</p>
        <form className={classes.form} onSubmit={handleSubmit}>
          <TextField
            margin="normal"
            value={state.naziv}
            onChange={handleChange1}
            required
            fullWidth
            name="naziv"
            label="Naziv tipa pregleda"
            type="text"
            id="nazivTipa"
          />
          <TextField
            margin="normal"
            value={state.cenaPregleda}
            onChange={handleChange1}
            required
            fullWidth
            name="cenaPregleda"
            label="Cena pregleda"
            type="number"
            id="cenaPregleda"
          />
          <TextField
            margin="normal"
            value={state.cenaOperacije}
            onChange={handleChange1}
            required
            fullWidth
            name="cenaOperacije"
            label="Cena operacije"
            type="number"
            id="cenaOperacije"
          />
          <TextField
            margin="normal"
            value={state.minimalnoTrajanjeMin}
            onChange={handleChange1}
            required
            fullWidth
            name="minimalnoTrajanjeMin"
            label="Minimalno trajanje u minutima"
            type="number"
            id="minimalnoTrajanjeMin"
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Sačuvajte
          </Button>
        </form>
      </TabPanel>
      <TabPanel value={value} index={2} dir={theme.direction}>
        {value === 2 && <IzmenaBrisanjeTipova />}
      </TabPanel>
    </div>
  );
};
const mapStateToProps = state => ({
  adminKlinike: state.adminKlinike
});

export default connect(mapStateToProps, { addNewTipPregleda })(
  TipoviPregledaTabs
);
