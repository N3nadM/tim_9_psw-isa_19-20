import React from "react";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import Typography from "@material-ui/core/Typography";
import { editSala } from "../../../store/actions/sala";

const IzmenaPodatakaSala = ({ sala, editSala, setIsEdit }) => {
  const [state, setState] = React.useState({
    id: sala.id,
    naziv: sala.naziv,
    salaIdentifier: sala.salaIdentifier
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
    editSala(state);
    setIsEdit(false);
  };

  return (
    <form noValidate onSubmit={handleSubmit}>
      <Typography variant="h6" id="tableTitle">
        Izmenite podatke o sali
      </Typography>
      <Grid container spacing={3}>
        <TextField
          value={state.salaIdentifier}
          onChange={handleChange}
          margin="normal"
          required
          fullWidth
          name="salaIdentifier"
          label="Broj"
          type="text"
          id="salaIdentifier"
        />
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
};
const mapStateToProps = state => ({});

export default withRouter(
  connect(mapStateToProps, { editSala })(IzmenaPodatakaSala)
);
