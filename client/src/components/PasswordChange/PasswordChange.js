import React from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Link from "@material-ui/core/Link";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import logo from "../../images/pharmacy.png";
import { Link as Link1 } from "react-router-dom";

const useStyles = makeStyles(theme => ({
  "@global": {
    body: {
      backgroundColor: theme.palette.common.white
    }
  },
  avatar: {
    margin: theme.spacing(1)
  }
}));

export default function PasswordChange() {
  const classes = useStyles();

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        <Typography component="h3" variant="h6">
          Izmena lozinke
        </Typography>

        <form className={classes.form} noValidate>
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Stara lozinka"
            type="staraLozinka"
            id="password"
            autoComplete="current-password"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Nova lozinka"
            type="password"
            id="novaLozinka"
            autoComplete="current-password"
          />
          <TextField
            margin="normal"
            required
            fullWidth
            name="password"
            label="Potvrda Nove Lozinke"
            type="password"
            id="potvrdaNovaLozinka"
            autoComplete="current-password"
          />
          <Button
            type="submit"
            fullWidth
            variant="contained"
            color="primary"
            className={classes.submit}
          >
            Izmenite
          </Button>
        </form>
      </div>
    </Container>
  );
}
