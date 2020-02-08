import React, { useState } from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Grid from "@material-ui/core/Grid";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import logo from "../../images/pharmacy.png";
import { Link as Link1 } from "react-router-dom";
import { connect } from "react-redux";
import { authUser } from "../../store/actions/auth";
import AppBar from "../layout/AppBar";

const useStyles = makeStyles(theme => ({
  "@global": {
    body: {
      backgroundColor: theme.palette.common.white
    }
  },
  paper: {
    marginTop: theme.spacing(8),
    display: "flex",
    flexDirection: "column",
    alignItems: "center"
  },
  avatar: {
    margin: theme.spacing(1)
  },
  form: {
    width: "100%", // Fix IE 11 issue.
    marginTop: theme.spacing(1)
  },
  submit: {
    margin: theme.spacing(3, 0, 2)
  }
}));

const SignIn = ({
  authUser,
  currentUser: { error, isAuthenticated },
  history
}) => {
  const classes = useStyles();
  const [state, setState] = useState({
    email: "",
    password: ""
  });

  const handleChange = e => {
    const { name, value } = e.target;
    setState(prevState => ({
      ...prevState,
      [name]: value
    }));
  };

  const handleSubmit = async e => {
    e.preventDefault();
    await authUser({ username: state.email, password: state.password });
    history.push("/");
  };

  return (
    <>
      <AppBar />
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
          <Typography component="h1" variant="h3">
            Logovanje
          </Typography>
          <Avatar className={classes.avatar} src={logo}></Avatar>

          <form className={classes.form} noValidate onSubmit={handleSubmit}>
            <TextField
              error={!!error}
              value={state.email}
              onChange={handleChange}
              helperText={!!error && "Pogrešan email"}
              variant="outlined"
              margin="normal"
              required
              fullWidth
              id="email"
              label="Email adresa"
              name="email"
              autoComplete="email"
              autoFocus
            />
            <TextField
              error={!!error}
              value={state.password}
              onChange={handleChange}
              helperText={!!error && "Pogrešna lozinka"}
              variant="outlined"
              margin="normal"
              required
              fullWidth
              name="password"
              label="Lozinka"
              type="password"
              id="password"
              autoComplete="current-password"
            />
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
              className={classes.submit}
              id="submit"
            >
              Ulogujte se
            </Button>
            <Grid container>
              <Grid item>
                <Link1 to="/signUp">{"Nemate nalog? Registrujte se..."}</Link1>
              </Grid>
            </Grid>
          </form>
        </div>
      </Container>
    </>
  );
};

function mapStateToProps(state) {
  return {
    currentUser: state.currentUser
  };
}

export default connect(mapStateToProps, { authUser })(SignIn);
