import React from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";

export default function EditTab({ klinika, editKlinika, setIsEdit }) {
  const [state, setState] = React.useState({
    id: klinika.id,
    naziv: klinika.naziv,
    opis: klinika.opis,
    adresa: klinika.adresa
  });

  const handleChange = e => {
    const { name, value } = e.target;
    setState(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = e => {
    e.preventDefault();
    editKlinika(state);
    setIsEdit(false);
  };

  return (
    <form noValidate onSubmit={handleSubmit}>
      <Grid container spacing={3}>
        <Grid item sm={6}>
          <TextField
            margin="normal"
            required
            fullWidth
            value={state.naziv}
            onChange={handleChange}
            name="naziv"
            label="Naziv"
            type="text"
            id="naziv"
          />
          <TextField
            margin="normal"
            value={state.opis}
            onChange={handleChange}
            required
            fullWidth
            name="opis"
            label="Opis"
            type="text"
            id="opis"
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
          <Button type="submit" variant="contained" color="primary">
            Sačuvaj
          </Button>
        </Grid>
      </Grid>
    </form>
  );
}
