import React from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";

export default function EditZdrKartonTab({
  zdrKarton,
  editZdrKarton,
  setIsEdit
}) {
  const [state, setState] = React.useState({
    id: zdrKarton.id,
    visina: zdrKarton.visina,
    tezina: zdrKarton.tezina,
    dioptrija: zdrKarton.dioptrija,
    krvnaGrupa: zdrKarton.krvnaGrupa
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
    editZdrKarton(state);
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
            value={state.visina}
            onChange={handleChange}
            name="visina"
            label="Visina"
            type="text"
            id="visina"
          />
          <TextField
            margin="normal"
            value={state.tezina}
            onChange={handleChange}
            required
            fullWidth
            name="tezina"
            label="Tezina"
            type="text"
            id="tezina"
          />
          <TextField
            value={state.dioptrija}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="dioptrija"
            label="Dioptrija"
            type="text"
            id="dioptrija"
          />
          <TextField
            value={state.krvnaGrupa}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="krvnaGrupa"
            label="Krvna Grupa"
            type="text"
            id="krvnaGrupa"
          />
          <Button type="submit" variant="contained" color="primary">
            Sačuvaj
          </Button>
        </Grid>
      </Grid>
    </form>
  );
}
