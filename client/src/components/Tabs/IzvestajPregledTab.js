import React from "react";
import PropTypes from "prop-types";
import { makeStyles, useTheme } from "@material-ui/core/styles";
import Tabs from "@material-ui/core/Tabs";
import Tab from "@material-ui/core/Tab";
import Typography from "@material-ui/core/Typography";
import Box from "@material-ui/core/Box";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";

import Autocomplete from "@material-ui/lab/Autocomplete";
import { Divider } from "@material-ui/core";
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

  return (
    <div className={classes.root}>
      <Tabs
        value={value}
        onChange={handleChange}
        indicatorColor="primary"
        textColor="primary"
      >
        <Tab label="Unos izveštaja o pregledu" {...a11yProps(0)} />
      </Tabs>
      <TabPanel value={value} index={0} dir={theme.direction}>
        <form className={classes.form} noValidate>
          <Autocomplete
            id="pregled"
            style={{ width: 440 }}
            renderInput={params => (
              <TextField {...params} label="Izaberite pregled" fullWidth />
            )}
          />
          <Autocomplete
            id="dijagnoza"
            style={{ width: 440 }}
            renderInput={params => (
              <TextField {...params} label="Dijagnoza" fullWidth />
            )}
          />
          <Divider />
          <TextField
            id="izvestaj"
            label="Izveštaj"
            style={{ width: 600 }}
            multiline
            rows="7"
            className={classes.textField}
            margin="normal"
            variant="outlined"
          />
          <br />
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
    </div>
  );
}
