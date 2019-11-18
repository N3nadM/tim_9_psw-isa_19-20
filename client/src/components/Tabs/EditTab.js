import React from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";

export default function EditTab({ korisnik, editKorisnik, setIsEdit }) {
  const [state, setState] = React.useState({
    id: korisnik.id,
    ime: korisnik.ime,
    prezime: korisnik.prezime,
    adresa: korisnik.adresa,
    grad: korisnik.grad,
    drzava: korisnik.drzava,
    telefon: korisnik.telefon
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
    editKorisnik(state);
    setIsEdit(false);
  };

  return (
    <form noValidate onSubmit={handleSubmit}>
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
        </Grid>
        <Grid item sm={6}>
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
