import React from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import { Grid } from "@material-ui/core";

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
        centered
      >
        <Tab label="Unos novog lekara" {...a11yProps(0)} />
        <Tab label="Pretraga lekara" {...a11yProps(1)} />
        <Tab label="Izmena lekara" {...a11yProps(2)} />
        <Tab label="Uklanjanje lekara" {...a11yProps(3)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        <form className={classes.form} noValidate>
          <TextField
            margin="normal"
            required
            fullWidth
            name="ime"
            label="Ime"
            type="text"
            id="imeLekar"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="prezime"
            label="Prezime"
            type="text"
            id="prezimeLekar"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="username"
            label="Username"
            type="text"
            id="usernameLekar"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="passwordLekar"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="email"
            label="Email"
            type="email"
            id="emailLekar"
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
        Unesite kriterijume pretrage
        <form className={classes.form} noValidate>
          <Grid container spacing={3}>
            <Grid item xs>
              <TextField
                margin="normal"
                fullWidth
                name="imePretraga"
                label="Ime lekara"
                type="text"
                id="imePretraga"
              />
            </Grid>
            <Grid item xs>
              <TextField
                margin="normal"
                fullWidth
                name="prezimePretraga"
                label="Prezime lekara"
                type="text"
                id="prezimePretraga"
              />
            </Grid>
            <Grid item xs>
              <TextField
                margin="normal"
                fullWidth
                name="usernamePretraga"
                label="Username lekara"
                type="text"
                id="usernamePretraga"
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
        Lista lekara ciji podaci mogu da se menjaju
      </TabPanel>
      <TabPanel value={value} index={3} dir={theme.direction}>
        Lista lekara koji mogu da se obrisu (nemaju zakazane preglede i
        operacije)
      </TabPanel>
    </div>
  );
}
