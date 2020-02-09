import React from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import { addNewAdminKC } from "../../store/actions/adminKC";

const DodajAKCTab = ({ addNewAdminKC }) => {
  const [state, setState] = React.useState({
    ime: "",
    prezime: "",
    email: "",
    password: "",
    drzava: "",
    grad: "",
    adresa: "",
    telefon: ""
  });

  const handleChange = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  const handleSubmit = e => {
    e.preventDefault();
    addNewAdminKC(state);
    setState({
      ime: "",
      prezime: "",
      email: "",
      password: "",
      drzava: "",
      grad: "",
      adresa: "",
      telefon: ""
    });
  };

  return (
    <form noValidate onSubmit={handleSubmit}>
      <Grid container spacing={10}>
        <Grid item sm={4}>
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
        <Grid item sm={4}>
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
      <Grid container spacing={1} sm={3}>
        <Button type="submit" variant="contained" color="primary">
          Dodaj
        </Button>
      </Grid>
    </form>
  );
};

const mapStateToProps = state => ({});

export default connect(mapStateToProps, { addNewAdminKC })(DodajAKCTab);
