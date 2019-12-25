import React, { useEffect } from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import ListItemText from "@material-ui/core/ListItemText";
import Input from "@material-ui/core/Input";
import { getLekoviByDijagnozaId } from "../../../store/actions/lek";
import { getAllDijagnoze } from "../../../store/actions/dijagnoza";
import { connect } from "react-redux";

const PregledOperacijaTab = ({
  zdrKarton,
  lekovi,
  getLekoviByDijagnozaId,
  dijagnoze,
  getAllDijagnoze,
  editZdrKarton,
  setIsEdit
}) => {
  const [state, setState] = React.useState({
    id: zdrKarton.id,
    visina: zdrKarton.visina,
    tezina: zdrKarton.tezina,
    dioptrija: zdrKarton.dioptrija,
    krvnaGrupa: zdrKarton.krvnaGrupa
  });

  useEffect(() => {
    getAllDijagnoze();
    //eslint-disable-next-line
  }, []);

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
            defaultValue={zdrKarton.visina}
            onChange={handleChange}
            name="visina"
            label="Visina"
            type="text"
            id="visina"
          />
        </Grid>
        <Grid item sm={6}>
          <TextField
            margin="normal"
            value={state.tezina}
            defaultValue={zdrKarton.tezina}
            onChange={handleChange}
            required
            fullWidth
            name="tezina"
            label="Tezina"
            type="text"
            id="tezina"
          />
        </Grid>
        <Grid item sm={6}>
          <TextField
            value={state.dioptrija}
            defaultValue={zdrKarton.dioptrija}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="dioptrija"
            label="Dioptrija"
            type="text"
            id="dioptrija"
          />
        </Grid>
        <Grid item sm={6}>
          <TextField
            value={state.krvnaGrupa}
            defaultValue={zdrKarton.krvnaGrupa}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="krvnaGrupa"
            label="Krvna Grupa"
            type="text"
            id="krvnaGrupa"
          />
        </Grid>
        <Grid item sm={6}>
          <FormControl style={{ minWidth: 150 }}>
            <InputLabel id="demo-mutiple-checkbox-label">
              Izaberi dijagnozu
            </InputLabel>
            <Select
              labelId="demo-checkbox-label"
              id="demo-checkbox"
              input={<Input />}
            >
              {dijagnoze.map(row => (
                <MenuItem key={row.naziv} value={row.naziv}>
                  <ListItemText primary={row.naziv} />
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>
        <Grid item sm={6}>
          <TextField
            value={state.krvnaGrupa}
            defaultValue={zdrKarton.krvnaGrupa}
            onChange={handleChange}
            margin="normal"
            required
            fullWidth
            name="krvnaGrupa"
            label="Krvna Grupa"
            type="text"
            id="krvnaGrupa"
          />
        </Grid>
      </Grid>
      <Grid container spacing={3}>
        <Grid item sm={6}>
          <Button type="submit" variant="contained" color="primary">
            Zavrsi
          </Button>
        </Grid>
      </Grid>
    </form>
  );
};

const mapStateToProps = state => ({
  dijagnoze: state.dijagnoza.dijagnoze,
  lekovi: state.lek.lekovi
});

export default connect(mapStateToProps, {
  getLekoviByDijagnozaId,
  getAllDijagnoze
})(PregledOperacijaTab);
