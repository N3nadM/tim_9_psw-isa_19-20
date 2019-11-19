import React from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";

export default function DodajKlinikuTab() {

  const handleChange = e => {
    const { name, value } = e.target;
  };

  const handleSubmit = e => {
    e.preventDefault();
  };

  return (
    <form noValidate onSubmit={handleSubmit}>
      <Grid container spacing={3}>
        <Grid item sm={6}>
          <TextField
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
            <Button
            type="submit"
            variant="contained"
            color="primary"
            >
            Dodaj
            </Button>
        </Grid>

      </Grid>
      
    </form>
  );
}
