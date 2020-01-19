import React, { useEffect } from "react";
import { useTheme } from "@material-ui/core/styles";
import {
  LineChart,
  Line,
  XAxis,
  YAxis,
  CartesianGrid,
  ResponsiveContainer
} from "recharts";
import Typography from "@material-ui/core/Typography";
import Grid from "@material-ui/core/Grid";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import { connect } from "react-redux";
import Axios from "axios";

// Generate Sales Data
function createData(time, amount) {
  return { time, amount };
}

const Chart = ({ klinika }) => {
  useEffect(() => {
    handleClick("Dan");
  }, []);
  let data = [];

  const handleClick = async period => {
    const resp = await Axios.post(
      `/api/klinika/grafik/${period}/${klinika.id}`
    );
    promeniPodatke(resp.data);
  };
  const handleChange = e => {
    handleClick(e.target.value);
  };
  const promeniPodatke = d => {
    data = [];
    for (const entry of Object.entries(d)) {
      data.push(createData(entry[0], entry[1]));
    }
    console.log(data);
    state.kljuc = Math.random();
    console.log(state.kljuc);
  };
  const [state, setState] = React.useState({
    kljuc: "grafik"
  });

  const izbor = ["Dan", "Mesec", "Godina"];
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
            fullWidth
            name="select"
            onChange={handleChange}
          >
            {izbor &&
              izbor.map(izbor => (
                <MenuItem key={izbor} value={izbor}>
                  {izbor}
                </MenuItem>
              ))}
          </Select>
        </Grid>
      </Grid>
      <ResponsiveContainer key={state.kljuc}>
        <LineChart width={600} height={300} data={data}>
          <Line type="monotone" dataKey="amount" stroke="#8884d8" />
          <CartesianGrid stroke="#ccc" />
          <XAxis dataKey="time" />
          <YAxis />
        </LineChart>
      </ResponsiveContainer>
    </React.Fragment>
  );
};

const mapStateToProps = state => ({});

export default connect(mapStateToProps, {})(Chart);
