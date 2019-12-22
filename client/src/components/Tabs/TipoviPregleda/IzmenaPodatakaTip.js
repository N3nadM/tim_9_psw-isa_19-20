import React from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import Typography from "@material-ui/core/Typography";
import { editTip } from "../../../store/actions/tipoviPregleda";

const IzmenaPodatakaTip = ({ tip, editTip, setIsEdit }) => {
  const [state, setState] = React.useState({
    id: tip.id,
    naziv: tip.naziv,
    cenaPregleda: tip.cenaPregleda,
    cenaOperacije: tip.cenaOperacije,
    minimalnoTrajanjeMin: tip.minimalnoTrajanjeMin
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
    editTip(state);
    setIsEdit(false);
  };

  return (
    <form noValidate onSubmit={handleSubmit}>
      <Typography variant="h6" id="tableTitle">
        Izmenite podatke o tipu pregleda
      </Typography>
      <Grid container spacing={3}>
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
          value={state.cenaPregleda}
          onChange={handleChange}
          margin="normal"
          required
          fullWidth
          name="cenaPregleda"
          label="Cena pregleda"
          type="number"
          id="cenaPregleda"
        />
        <TextField
          value={state.cenaOperacije}
          onChange={handleChange}
          margin="normal"
          required
          fullWidth
          name="cenaOperacije"
          label="Cena operacije"
          type="number"
          id="cenaOperacije"
        />
        <TextField
          value={state.minimalnoTrajanjeMin}
          onChange={handleChange}
          margin="normal"
          required
          fullWidth
          name="minimalnoTrajanjeMin"
          label="Minimalno trajanje"
          type="number"
          id="minimalnoTrajanjeMin"
        />
        <Button
          type="submit"
          variant="contained"
          color="primary"
          style={{ marginLeft: 13 }}
        >
          Sačuvajte
        </Button>
      </Grid>
    </form>
  );
};
const mapStateToProps = state => ({});

export default withRouter(
  connect(mapStateToProps, { editTip })(IzmenaPodatakaTip)
);
