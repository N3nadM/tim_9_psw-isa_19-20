import React, { useEffect } from "react";
import { LineChart, Line, XAxis, YAxis, CartesianGrid } from "recharts";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import { connect } from "react-redux";
import { getPodaciGrafik } from "../../../store/actions/adminKlinike";

const Chart = ({ klinika, podaci, getPodaciGrafik }) => {
  useEffect(() => {
    getPodaciGrafik("Dan", klinika.id);
  }, [getPodaciGrafik, klinika.id]);

  const handleChange = e => {
    setState({
      ...state,
      [e.target.name]: e.target.value
    });
    getPodaciGrafik(e.target.value, klinika.id);
  };

  const izbor = ["Dan", "Nedelja", "Mesec"];
  const [state, setState] = React.useState({
    select: "Dan"
  });
  return (
    <React.Fragment>
      <Grid container space={3}>
        <Grid item xs={6}>
          <Typography component="h2" variant="h6" color="primary" gutterBottom>
            Grafik odrzanih pregleda
          </Typography>
        </Grid>
        <Grid item xs={3}>
          <Typography component="h2" variant="h6" gutterBottom>
            Izaberite period:
          </Typography>
        </Grid>
        <Grid item xs={3}>
          <Select
            style={{ height: 18 }}
            labelId="demo-simple-select-placeholder-label-label"
            id="s"
            value={state.value}
            fullWidth
            name="select"
            onChange={handleChange}
            defaultValue="Dan"
          >
            {izbor &&
              izbor.map(izbor => (
                <MenuItem key={izbor} value={izbor}>
                  {izbor}
                </MenuItem>
              ))}
          </Select>
        </Grid>
        <Grid item xs={12}>
          <LineChart width={1100} height={300} data={podaci}>
            <Line type="monotone" dataKey="amount" stroke="#8884d8" />
            <CartesianGrid stroke="#ccc" />
            <XAxis dataKey="time" />
            <YAxis />
          </LineChart>
        </Grid>
      </Grid>
    </React.Fragment>
  );
};

const mapStateToProps = state => ({
  podaci: state.adminKlinike.podaciGrafik
});

export default connect(mapStateToProps, { getPodaciGrafik })(Chart);
