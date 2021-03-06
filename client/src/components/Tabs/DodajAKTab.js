import React, { useEffect } from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import MenuItem from "@material-ui/core/MenuItem";
import FormHelperText from "@material-ui/core/FormHelperText";
import FormControl from "@material-ui/core/FormControl";
import Select from "@material-ui/core/Select";
import InputLabel from "@material-ui/core/InputLabel";
import { connect } from "react-redux";
import { addNewAdmin } from "../../store/actions/adminKlinike";
import { getAllKlinike } from "../../store/actions/klinika";

const DodajAKTab = ({
  admin,
  klinika: { klinike },
  addNewAdmin,
  getAllKlinike
}) => {
  const [state, setState] = React.useState({
    ime: "",
    prezime: "",
    email: "",
    password: "",
    drzava: "",
    grad: "",
    adresa: "",
    telefon: "",
    klinikaId: ""
  });

  const handleChange = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  const handleSubmit = e => {
    e.preventDefault();

    addNewAdmin(state);
    setState({
      ime: "",
      prezime: "",
      email: "",
      password: "",
      drzava: "",
      grad: "",
      adresa: "",
      telefon: "",
      klinikaId: ""
    });
  };
  useEffect(() => {
    getAllKlinike();
    //eslint-disable-next-line
  }, []);

  return (
    <form noValidate onSubmit={handleSubmit}>
      <Grid container spacing={4} direction="row" alignItems="baseline">
        <Grid item sm={5}>
          <TextField
            value={state.ime}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="ime"
            label="Ime"
            type="text"
            id="ime"
          />
          <TextField
            value={state.prezime}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="prezime"
            label="Prezime"
            type="text"
            id="prezime"
          />
          <TextField
            value={state.email}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="email"
            label="Email"
            type="text"
            id="email"
          />
          <TextField
            value={state.password}
            onChange={handleChange}
            required
            margin="normal"
            fullWidth
            name="password"
            label="Password"
            type="password"
            id="password"
          />
        </Grid>
        <Grid item sm={5}>
          <TextField
            value={state.drzava}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="drzava"
            label="Drzava"
            type="text"
            id="drzava"
          />
          <TextField
            value={state.grad}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="grad"
            label="Grad"
            type="text"
            id="grad"
          />
          <TextField
            value={state.adresa}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="adresa"
            label="Adresa"
            type="text"
            id="adresa"
          />
          <TextField
            value={state.telefon}
            onChange={handleChange}
            margin="normal"
            fullWidth
            name="telefon"
            label="Telefon"
            type="number"
            id="telefon"
          />
        </Grid>
      </Grid>
      <Grid
        container
        spacing={4}
        direction="column"
        justify="space-evenly"
        alignItems={200}
      >
        <Grid item sm={5}>
          <FormControl>
            <InputLabel shrink id="demo-simple-select-placeholder-label-label">
              Klinika
            </InputLabel>
            <Select
              labelId="demo-simple-select-placeholder-label-label"
              id="klinike"
              onChange={handleChange}
              displayEmpty
              name="klinikaId"
            >
              {klinike &&
                klinike.map(klinika => (
                  <MenuItem key={klinika.id} value={klinika.id}>
                    {klinika.naziv}
                  </MenuItem>
                ))}
            </Select>
            <FormHelperText>Izaberite kliniku</FormHelperText>
          </FormControl>
        </Grid>

        <Grid item sm={5}>
          <Button type="submit" variant="contained" color="primary">
            Dodaj
          </Button>
        </Grid>
      </Grid>
    </form>
  );
};

const mapStateToProps = state => ({
  admin: state.admin,
  klinika: state.klinika
});

export default connect(mapStateToProps, { addNewAdmin, getAllKlinike })(
  DodajAKTab
);
