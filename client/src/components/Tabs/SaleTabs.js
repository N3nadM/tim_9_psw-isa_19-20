import React from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
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
        centered
      >
        <Tab label="Unos nove sale" {...a11yProps(0)} />
        <Tab label="Pretraga sale" {...a11yProps(1)} />
        <Tab label="Izmena sale" {...a11yProps(2)} />
        <Tab label="Uklanjanje sale" {...a11yProps(3)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        <form className={classes.form} noValidate>
          <TextField
            margin="normal"
            required
            fullWidth
            name="brojSale"
            label="Broj sale"
            type="text"
            id="brojSale"
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
        <p>Unesite broj sale</p>
        <form className={classes.form} noValidate>
          <TextField
            margin="normal"
            required
            fullWidth
            name="brojSalePretraga"
            label="Broj sale"
            type="text"
            id="brojSalePretraga"
          />
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
        Lista sala koje mogu da se menjaju
      </TabPanel>
      <TabPanel value={value} index={3} dir={theme.direction}>
        Lista sala koje mogu da se obrisu (ukoliko nisu rezervisane)
      </TabPanel>
    </div>
  );
}
