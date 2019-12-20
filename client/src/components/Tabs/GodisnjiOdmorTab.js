import React from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import { addNewOdmor } from "../../store/actions/odmor";
import { addNewOdsustvo } from "../../store/actions/odsustvo";

import Autocomplete from "@material-ui/lab/Autocomplete";
import { addNewDijagnoza } from "../../store/actions/dijagnoza";
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

const OdmorOdsustvoTab = ({ korisnikId, addNewOdsustvo, addNewOdmor }) => {
  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);

  const [selectedDate, setSelectedDate] = React.useState({
    korisnikId,
    datumOd: new Date(),
    datumDo: new Date(),
    opis: ""
  });

  const [selectedDate2, setSelectedDate2] = React.useState({
    korisnikId,
    datum: new Date(),
    opis: ""
  });

  const handleDateChangeOdsustvo = e => {
    setSelectedDate2({
      ...selectedDate2,
      datum: new Date(e.target.value),
      [e.target.name]: e.target.value
    });
  };

  const handleDateChangeOdmor1 = e => {
    setSelectedDate({
      ...selectedDate,
      datumOd: new Date(e.target.value),
      [e.target.name]: e.target.value
    });
  };

  const handleDateChangeOdmor2 = e => {
    setSelectedDate({
      ...selectedDate,
      datumDo: new Date(e.target.value),
      [e.target.name]: e.target.value
    });
  };

  const handleChangeOdmorOpis = e => {
    setSelectedDate({
      ...selectedDate,
      opis: e.target.value,
      [e.target.name]: e.target.value
    });
  };

  const handleChangeOdsustvoOpis = e => {
    setSelectedDate2({
      ...selectedDate2,
      opis: e.target.value,
      [e.target.name]: e.target.value
    });
  };

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleChangeIndex = index => {
    setValue(index);
  };

  const handleSubmit = e => {
    e.preventDefault();
    addNewOdmor(selectedDate);
  };

  const handleSubmit2 = e => {
    e.preventDefault();
    addNewOdsustvo(selectedDate2);
  };

  {
    console.log(selectedDate);
  }

  return (
    <div className={classes.root}>
      <Tabs
        value={value}
        onChange={handleChange}
        indicatorColor="primary"
        textColor="primary"
      >
        <Tab label="Unos zahteva za godišnji odmor" {...a11yProps(0)} />
        <Tab label="Unos zahteva za odsustvo" {...a11yProps(1)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        <form className={classes.form} noValidate>
          <TextField
            id="datumOd"
            label="Od:"
            type="date"
            fullWidth
            defaultValue={formatDate(selectedDate.datumOd)}
            onChange={handleDateChangeOdmor1}
            className={classes.textField}
            InputLabelProps={{
              shrink: true
            }}
          />
          <TextField
            id="datumDo"
            label="Do:"
            type="date"
            fullWidth
            defaultValue={formatDate(selectedDate.datumDo)}
            onChange={handleDateChangeOdmor2}
            className={classes.textField}
            InputLabelProps={{
              shrink: true
            }}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            onChange={handleChangeOdmorOpis}
            name="opis"
            label="Opis zahteva"
            type="text"
            id="opis"
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={handleSubmit}
          >
            Pošaljite
          </Button>
        </form>
      </TabPanel>

      <TabPanel value={value} index={1} dir={theme.direction}>
        <form className={classes.form} noValidate>
          <TextField
            id="datum"
            label="Datum odsustva:"
            type="date"
            fullWidth
            defaultValue={formatDate(selectedDate2.datum)}
            onChange={handleDateChangeOdsustvo}
            className={classes.textField}
            InputLabelProps={{
              shrink: true
            }}
          />
          <TextField
            margin="normal"
            required
            fullWidth
            onChange={handleChangeOdsustvoOpis}
            name="opis"
            label="Opis zahteva"
            type="text"
            id="opis"
          />
          <Button
            type="submit"
            variant="contained"
            color="primary"
            className={classes.submit}
            onClick={handleSubmit2}
          >
            Pošaljite
          </Button>
        </form>
      </TabPanel>
    </div>
  );
};

function formatDate(date) {
  var day = date.getDate();
  var monthIndex = date.getMonth();
  var year = date.getFullYear();

  return year + "-" + monthIndex + "-" + day;
}

function mapStateToProps(state) {
  return {
    korisnikId: state.currentUser.user.id
  };
}

export default connect(mapStateToProps, { addNewOdsustvo, addNewOdmor })(
  OdmorOdsustvoTab
);
