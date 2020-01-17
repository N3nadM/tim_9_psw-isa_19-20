import React from "react";
import Typography from "@material-ui/core/Typography";
import { connect } from "react-redux";
import { Grid } from "@material-ui/core";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Axios from "axios";

let d = new Date();
let d1 = new Date();

const RacunanjePrihoda = ({ klinika }) => {
  const [state, setState] = React.useState({
    datumOd: d,
    datumDo: "",
    prihod: ""
  });
  const [selectedDate, setSelectedDate] = React.useState(new Date());

  const handleDateChange = date => {
    setSelectedDate(date);
    d = date;
    state.datumOd = date;
    console.log(state);
  };

  const handleDateChange2 = date => {
    setSelectedDate(date);
    d1 = date;
    state.datumDo = date;
    console.log(state);
  };
  const handleClick = async () => {
    const resp = await Axios.post(`/api/klinika/prihod/${klinika.id}`, state);
    setState({
      ...state,
      prihod: resp.data + " DIN"
    });
  };
  return (
    <React.Fragment>
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <Typography component="h2" variant="h6" color="primary" gutterBottom>
            Računanje prihoda klinike
          </Typography>
        </Grid>
        <Grid item xs={2}>
          <Typography
            style={{ marginTop: 18 }}
            component="h2"
            variant="h6"
            gutterBottom
          >
            Od:
          </Typography>
        </Grid>
        <Grid item xs={4}>
          <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <KeyboardDatePicker
              id="date-picker-dialog"
              format="MM/dd/yyyy"
              fullWidth
              style={{ marginTop: 18 }}
              onChange={e => handleDateChange(e)}
              value={d}
              KeyboardButtonProps={{
                "aria-label": "change date"
              }}
            />
          </MuiPickersUtilsProvider>
        </Grid>
        <Grid item xs={2}>
          <Typography
            style={{ marginTop: 18, marginLeft: 30 }}
            component="h2"
            variant="h6"
            gutterBottom
          >
            Do:
          </Typography>
        </Grid>
        <Grid item xs={4}>
          <MuiPickersUtilsProvider utils={DateFnsUtils}>
            <KeyboardDatePicker
              id="date-picker"
              format="MM/dd/yyyy"
              onChange={e => handleDateChange2(e)}
              value={d1}
              fullWidth
              style={{ marginTop: 18 }}
              KeyboardButtonProps={{
                "aria-label": "change date"
              }}
            />
          </MuiPickersUtilsProvider>
        </Grid>
        <Grid item xs={9}>
          <TextField
            id="prihod"
            label="Ukupan prihod"
            type="text"
            value={state.prihod}
            disabled={true}
            fullWidth
            InputLabelProps={{
              shrink: true
            }}
          />
        </Grid>
        <Grid item xs={3}>
          <Button
            variant="contained"
            color="primary"
            style={{ marginTop: 13 }}
            onClick={handleClick}
          >
            Izračunaj
          </Button>
        </Grid>
      </Grid>
    </React.Fragment>
  );
};

function mapStateToProps(state) {
  return {
    klinika: state.adminKlinike.klinika
  };
}

export default connect(mapStateToProps, {})(RacunanjePrihoda);
