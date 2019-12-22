import React, { useEffect } from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Input from "@material-ui/core/Input";
import InputLabel from "@material-ui/core/InputLabel";
import MenuItem from "@material-ui/core/MenuItem";
import FormControl from "@material-ui/core/FormControl";
import ListItemText from "@material-ui/core/ListItemText";
import Select from "@material-ui/core/Select";
import Checkbox from "@material-ui/core/Checkbox";
import Grid from "@material-ui/core/Grid";
import { connect } from "react-redux";
import { getAllLekovi } from "../../store/actions/lek";
import { addNewLek } from "../../store/actions/lek";
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

const SifranikTab = ({ lekovi, getAllLekovi, addNewLek, addNewDijagnoza }) => {
  useEffect(() => {
    getAllLekovi();
    //eslint-disable-next-line
  }, []);

  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);
  const [state, setState] = React.useState({
    naziv: "",
    sifra: "",
    lekovi: [],
    checked: -1
  });
  const [state2, setState2] = React.useState({
    naziv: "",
    sifra: "",
    sadrzaj: ""
  });
  const [lekName, setLekName] = React.useState([]);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  const handleChangeDijagnoza = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  const handleChangeLek = e => {
    setState2({ ...state2, [e.target.name]: e.target.value });
  };

  const handleChangeSelect = event => {
    setLekName(event.target.value);
  };

  const handleSubmitDijagnoza = e => {
    e.preventDefault();
    state.lekovi = lekName;
    addNewDijagnoza(state);
  };

  const handleSubmitLek = e => {
    e.preventDefault();
    addNewLek(state2);
  };

  return (
    <div className={classes.root}>
      <Tabs
        value={value}
        indicatorColor="primary"
        onChange={handleChange}
        textColor="primary"
      >
        <Tab label="Dodavanje dijagnize" {...a11yProps(0)} />
        <Tab label="Dodavanje leka" {...a11yProps(1)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        {lekovi && (
          <form className={classes.form} noValidate>
            <Grid container direction="column" spacing={3}>
              <Grid item sm={4}>
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  onChange={handleChangeDijagnoza}
                  name="naziv"
                  label="Naziv"
                  type="text"
                  id="naziv"
                />
              </Grid>
              <Grid item sm={4}>
                <TextField
                  margin="normal"
                  required
                  fullWidth
                  onChange={handleChangeDijagnoza}
                  name="sifra"
                  label="Sifra"
                  type="text"
                  id="sifra"
                />
              </Grid>
              <Grid item sm={4}>
                <FormControl className={classes.formControl}>
                  <InputLabel id="demo-mutiple-checkbox-label">
                    Izaberi lekove
                  </InputLabel>
                  <Select
                    labelId="demo-mutiple-checkbox-label"
                    id="demo-mutiple-checkbox"
                    multiple
                    value={lekName}
                    onChange={handleChangeSelect}
                    input={<Input />}
                    renderValue={selected => selected.join(", ")}
                  >
                    {lekovi.map(row => (
                      <MenuItem key={row.naziv} value={row.naziv}>
                        <Checkbox checked={lekName.indexOf(row.naziv) > -1} />
                        <ListItemText primary={row.naziv} />
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </Grid>
              <Grid item sm={4}>
                <Button
                  type="submit"
                  variant="contained"
                  color="primary"
                  className={classes.submit}
                  onClick={handleSubmitDijagnoza}
                >
                  Dodaj
                </Button>
              </Grid>
            </Grid>
          </form>
        )}
      </TabPanel>
      <TabPanel value={value} index={1} dir={theme.direction}>
        <form className={classes.form} noValidate>
          <Grid container direction="column" spacing={3}>
            <Grid item sm={4}>
              <TextField
                margin="normal"
                required
                fullWidth
                onChange={handleChangeLek}
                name="naziv"
                label="Naziv"
                type="text"
                id="naziv"
              />
            </Grid>
            <Grid item sm={4}>
              <TextField
                margin="normal"
                required
                fullWidth
                onChange={handleChangeLek}
                name="sifra"
                label="Sifra"
                type="text"
                id="sifra"
              />
            </Grid>
            <Grid item sm={4}>
              <TextField
                margin="normal"
                required
                fullWidth
                onChange={handleChangeLek}
                name="sadrzaj"
                label="Sadrzaj"
                type="text"
                id="sadrzaj"
              />
            </Grid>
            <Grid item sm={4}>
              <Button
                type="submit"
                variant="contained"
                color="primary"
                className={classes.submit}
                onClick={handleSubmitLek}
              >
                Dodaj
              </Button>
            </Grid>
          </Grid>
        </form>
      </TabPanel>
    </div>
  );
};

function mapStateToProps(state) {
  return {
    lekovi: state.lek.lekovi,
    lek: state.lek.lek,
    dijagnoza: state.dijagnoza.dijagnoza
  };
}

export default connect(mapStateToProps, {
  getAllLekovi,
  addNewDijagnoza,
  addNewLek
})(SifranikTab);
