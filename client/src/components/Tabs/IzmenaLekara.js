import React from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";

export default function IzmenaLekara({ lekar, editLekarByAdmin, setIsEdit }) {
  const [state, setState] = React.useState({
    id: lekar.korisnik.id,
    ime: lekar.korisnik.ime,
    prezime: lekar.korisnik.prezime,
    adresa: lekar.korisnik.adresa,
    grad: lekar.korisnik.grad,
    drzava: lekar.korisnik.drzava,
    telefon: lekar.korisnik.telefon,
    pocetakRadnogVremena: lekar.pocetakRadnogVremena,
    krajRadnogVremena: lekar.krajRadnogVremena
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
    editLekarByAdmin(state.id, state);
    setIsEdit(false);
  };

  return (
    <form onSubmit={handleSubmit}>
      <Grid container spacing={3}>
        <Grid item sm={6}>
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
            value={state.grad}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="grad"
            label="Grad"
            type="grad"
            id="grad"
          />
          <TextField
            value={state.drzava}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="drzava"
            label="Drzava"
            type="drzava"
            id="drzava"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            value={state.ime}
            onChange={handleChange}
            name="ime"
            label="Ime"
            type="text"
            id="ime"
          />
        </Grid>
        <Grid item sm={6}>
          <TextField
            margin="normal"
            value={state.prezime}
            onChange={handleChange}
            required
            fullWidth
            name="prezime"
            label="Prezime"
            type="text"
            id="prezime"
          />
          <TextField
            margin="normal"
            value={state.telefon}
            onChange={handleChange}
            required
            fullWidth
            name="telefon"
            label="Telefon"
            type="text"
            id="telefon"
          />
          <TextField
            style={{ marginTop: 16 }}
            id="pocetakRadnogVremena"
            label="Početak radnog vremena"
            type="time-local"
            name="pocetakRadnogVremena"
            value={state.pocetakRadnogVremena}
            onChange={handleChange}
            required
            fullWidth
          />
          <TextField
            style={{ marginTop: 24 }}
            id="krajRadnogVremena"
            label="Kraj radnog vremena"
            type="time-local"
            name="krajRadnogVremena"
            value={state.krajRadnogVremena}
            onChange={handleChange}
            required
            fullWidth
          />
        </Grid>
        <Button
          type="submit"
          variant="contained"
          color="primary"
          style={{ marginLeft: 13 }}
        >
          Sačuvaj
        </Button>
      </Grid>
    </form>
  );
}
