import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getPacijent } from "../../store/actions/pacijent";
import Button from "@material-ui/core/Button";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Typography from "@material-ui/core/Typography";

const PacijentProfilTab = ({
  pacijent: { pacijent },
  korisnikId,
  getPacijent
}) => {
  useEffect(() => {
    getPacijent(korisnikId);
  }, []);

  const [isEdit, setIsEdit] = React.useState(false);
  const [state, setState] = React.useState(null);

  const handleChange = e => {
    const { name, value } = e.target;
    setState(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = e => {
    e.preventDefault();
  };

  return (
    <div>
      <Typography
        variant="h4"
        component="h2"
        style={{ marginBottom: 20, marginLeft: 13 }}
      >
        Korisnički profil
      </Typography>

      {isEdit && (
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
      )}
      {!isEdit && pacijent && (
        <>
          <List disablePadding>
            <ListItem>
              <ListItemText primary="Ime" />
              <Typography variant="subtitle1">
                {pacijent.korisnik.ime}
              </Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Prezime" />
              <Typography variant="subtitle1">
                {pacijent.korisnik.prezime}
              </Typography>
            </ListItem>
            <Divider />
            <ListItem>
              <ListItemText primary="Adresa" />
              <Typography variant="subtitle1">
                {pacijent.korisnik.adresa}
              </Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Grad" />
              <Typography variant="subtitle1">{pacijent.grad}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Drzava" />
              <Typography variant="subtitle1">{pacijent.drzava}</Typography>
            </ListItem>
            <Divider />
            <ListItem>
              <ListItemText primary="Broj osiguranika" />
              <Typography variant="subtitle1">{pacijent.jbzo}</Typography>
            </ListItem>
            <ListItem>
              <ListItemText primary="Email" />
              <Typography variant="subtitle1">
                {pacijent.korisnik.email}
              </Typography>
            </ListItem>
            <Divider />

            <Divider />
          </List>
          <Button
            variant="contained"
            color="primary"
            style={{ float: "right", marginTop: 20 }}
            onClick={() => {
              setIsEdit(true);
              setState({
                ime: pacijent.korisnik.ime,
                prezime: pacijent.korisnik.prezime,
                adresa: pacijent.korisnik.adresa,
                grad: pacijent.grad,
                drzava: pacijent.drzava
              });
            }}
          >
            Izmeni
          </Button>
        </>
      )}
    </div>
  );
};

function mapStateToProps(state) {
  return {
    pacijent: state.pacijent,
    korisnikId: state.currentUser.user.id
  };
}

export default connect(mapStateToProps, { getPacijent })(PacijentProfilTab);
