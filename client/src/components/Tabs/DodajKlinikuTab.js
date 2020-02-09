import React from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import { addNewKlinika } from "../../store/actions/klinika";

const DodajKlinikuTab = ({ klinika, addNewKlinika }) => {
  const [state, setState] = React.useState({ naziv: "", opis: "", adresa: "" });

  const handleChange = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  const handleSubmit = e => {
    e.preventDefault();
    addNewKlinika(state);
    setState({ naziv: "", opis: "", adresa: "" });
  };

  return (
    <form noValidate onSubmit={handleSubmit}>
      <Grid container spacing={3}>
        <Grid item sm={6}>
          <TextField
            value={state.naziv}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="naziv"
            label="Naziv"
            type="naziv"
            id="naziv"
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
            value={state.opis}
            onChange={handleChange}
            margin="normal"
            fullWidth
            name="opis"
            label="Opis"
            type="opis"
            id="opis"
          />
        </Grid>

        <Grid container spacing={1}>
          <Button type="submit" variant="contained" color="primary">
            Dodaj
          </Button>
        </Grid>
      </Grid>
    </form>
  );
};

const mapStateToProps = state => ({
  klinika: state.klinika
});

export default connect(mapStateToProps, { addNewKlinika })(DodajKlinikuTab);
