import React, { useEffect } from "react";
import { makeStyles } from "@material-ui/core/styles";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import ListItemText from "@material-ui/core/ListItemText";
import Input from "@material-ui/core/Input";
import Checkbox from "@material-ui/core/Checkbox";
import { getLekoviByDijagnozaId } from "../../../store/actions/lek";
import {
  zavrsenPregledOperacija,
  zapocniPregledOperaciju
} from "../../../store/actions/pregledOperacija";
import { getAllDijagnoze } from "../../../store/actions/dijagnoza";
import { connect } from "react-redux";

const useStyles = makeStyles(theme => ({
  FormControl: {
    minWidth: 120,
    maxWidth: 300
  },
  root: {
    backgroundColor: theme.palette.background.paper,
    flexGrow: 1
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1)
  }
}));

const PregledOperacijaTab = ({
  zdrKarton,
  lekovi,
  getLekoviByDijagnozaId,
  dijagnoze,
  getAllDijagnoze,
  editZdrKarton,
  setIsEdit,
  obj,
  zavrsenPregledOperacija,
  zapocniPregledOperaciju
}) => {
  const [lekName, setLekName] = React.useState([]);
  const [state, setState] = React.useState({
    id: zdrKarton.id,
    visina: zdrKarton.visina,
    tezina: zdrKarton.tezina,
    dioptrija: zdrKarton.dioptrija,
    krvnaGrupa: zdrKarton.krvnaGrupa,
    izvestaj: obj.izvestaj,
    dijagnoza: "",
    lekovi: lekName,
    medSestraId: obj.medicinskaSestra.id,
    vrsta: obj.vrsta,
    idPregledOperacija: obj.id
  });

  useEffect(() => {
    zapocniPregledOperaciju(state);
    getAllDijagnoze();
    //eslint-disable-next-line
  }, []);

  const classes = useStyles();
  const handleChange = e => {
    const { name, value } = e.target;
    setState(prevState => ({
      ...prevState,
      [name]: value
    }));
    console.log(state);
  };

  const handleSubmit = e => {
    e.preventDefault();
    editZdrKarton(state);
    zavrsenPregledOperacija(state);
    obj.stanje = 2;
    setIsEdit(false);
  };

  const handleChangeSelectDijagnoza = (event, child) => {
    getLekoviByDijagnozaId(child.key);
    state.dijagnoza = child.key;
    console.log(state);
  };

  const handleChangeSelect = event => {
    setLekName(event.target.value);
    state.lekovi = event.target.value;
    console.log(state);
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
        <Grid item sm={12}>
          <TextField
            style={{ minHeight: 50 }}
            value={state.izvestaj}
            defaultValue={obj.izvestaj}
            onChange={handleChange}
            margin="normal"
            required
            multiline={true}
            fullWidth
            name="izvestaj"
            label="Izvestaj"
            type="text"
            id="izvestaj"
          />
        </Grid>
      </Grid>
      <Grid container spacing={3}>
        <Grid item sm={6}>
          <FormControl style={{ minWidth: 150 }}>
            <InputLabel id="demo-mutiple-checkbox-label">
              Izaberi dijagnozu
            </InputLabel>
            <Select
              labelId="demo-checkbox-label"
              id="demo-checkbox"
              input={<Input />}
              onChange={handleChangeSelectDijagnoza}
            >
              {dijagnoze.map(row => (
                <MenuItem key={row.id} value={row.naziv}>
                  <ListItemText primary={row.naziv} />
                </MenuItem>
              ))}
            </Select>
          </FormControl>
        </Grid>
        <Grid item sm={4}>
          <FormControl
            className={classes.formControl}
            style={{ minWidth: 150 }}
          >
            <InputLabel id="demo-mutiple-checkbox-label">
              Izaberi lek/lekove
            </InputLabel>
            <Select
              labelId="demo-mutiple-checkbox-label"
              id="demo-mutiple-checkbox"
              multiple
              value={lekName}
              onChange={handleChangeSelect}
              input={<Input />}
              renderValue={selected => selected.join(", ")}
            >
              {lekovi.map(row => (
                <MenuItem key={row.naziv} value={row.naziv}>
                  <Checkbox checked={lekName.indexOf(row.naziv) > -1} />
                  <ListItemText primary={row.naziv} />
                </MenuItem>
              ))}
            </Select>
          </FormControl>
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
  getAllDijagnoze,
  zavrsenPregledOperacija,
  zapocniPregledOperaciju
})(PregledOperacijaTab);
