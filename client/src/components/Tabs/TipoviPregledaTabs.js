import React from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
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

export default function FullWidthTabs() {
  const classes = useStyles();
  const theme = useTheme();
  const [value, setValue] = React.useState(0);

  const handleChange = (event, newValue) => {
    setValue(newValue);
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
        <Tab label="Unos novog tipa pregleda" {...a11yProps(0)} />
        <Tab label="Pretraga tipova pregleda" {...a11yProps(1)} />
        <Tab label="Izmena tipa pregleda" {...a11yProps(2)} />
        <Tab label="Uklanjanje tipa pregleda" {...a11yProps(3)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        <p>Unesite podatke o tipu pregleda</p>
        <form className={classes.form}>
          <TextField
            margin="normal"
            required
            fullWidth
            name="naziv"
            label="Naziv tipa pregleda"
            type="text"
            id="nazivTipa"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="cena"
            label="Cena tipa pregleda"
            type="text"
            id="cena"
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
      <TabPanel value={value} index={1} dir={theme.direction}>
        <p>Unesite kriterijume pretrage</p>
        <form className={classes.form} noValidate>
          <Grid container spacing={3}>
            <Grid item xs>
              <TextField
                margin="normal"
                fullWidth
                name="nazivPretraga"
                label="Naziv tipa pregleda"
                type="text"
                id="nazivPretraga"
              />
            </Grid>
            <Grid item xs>
              <TextField
                margin="normal"
                fullWidth
                name="cenaPretraga"
                label="Cena tipa pregleda"
                type="text"
                id="cenaPretraga"
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Pretraga
          </Button>
        </form>
      </TabPanel>
      <TabPanel value={value} index={2} dir={theme.direction}>
        Lista tipova pregleda koji se mogu menjati
      </TabPanel>
      <TabPanel value={value} index={3} dir={theme.direction}>
        Lista tipova pregleda koji se mogu obrisati (ukoliko ne postoje pregledi
        takvog tipa)
      </TabPanel>
    </div>
  );
}
