import React, { useState } from "react";
import Avatar from "@material-ui/core/Avatar";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import logo from "../../images/pharmacy.png";
import Stepper from "@material-ui/core/Stepper";
import Step from "@material-ui/core/Step";
import StepLabel from "@material-ui/core/StepLabel";
import { connect } from "react-redux";
import { registerUser } from "../../store/actions/auth";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import AppBar from "../layout/AppBar";

function getSteps() {
  return ["Korisnički podaci", "Lični podaci", "Registracija"];
}

function getStepContent(step, state, handleChange) {
  switch (step) {
    case 0:
      return (
        <>
          <TextField
            value={state.email}
            onChange={handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email adresa"
            name="email"
            autoComplete="off"
          />
          <TextField
            value={state.ime}
            onChange={handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="ime"
            label="Ime"
            name="ime"
            autoComplete="off"
          />
          <TextField
            value={state.prezime}
            onChange={handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="Prezime"
            label="Prezime"
            name="prezime"
            autoComplete="off"
          />
        </>
      );
    case 1:
      return (
        <>
          <TextField
            value={state.adresa}
            onChange={handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="adresa"
            label="Adresa"
            name="adresa"
            autoComplete="off"
          />
          <TextField
            value={state.drzava}
            onChange={handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="drzava"
            label="Država"
            name="drzava"
            autoComplete="off"
          />
          <TextField
            value={state.grad}
            onChange={handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="grad"
            label="Grad"
            name="grad"
            autoComplete="off"
          />
          <TextField
            error={state.telefon.length > 0 && !/^[0-9]*$/.test(state.telefon)}
            value={state.telefon}
            helperText={
              state.telefon.length > 0 &&
              !/^[0-9]*$/.test(state.telefon) &&
              "Unesite samo brojeve"
            }
            onChange={handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="telefon"
            label="Broj telefona"
            name="telefon"
            autoComplete="off"
          />
        </>
      );
    case 2:
      return (
        <>
          <TextField
            value={state.jbzo}
            onChange={handleChange}
            error={state.telefon.length > 0 && !/^[0-9]*$/.test(state.telefon)}
            helperText={
              state.telefon.length > 0 &&
              !/^[0-9]*$/.test(state.telefon) &&
              "Unesite samo brojeve"
            }
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="jbzo"
            label="Broj osiguranika"
            name="jbzo"
            autoComplete="off"
          />
          <TextField
            value={state.password}
            onChange={handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            name="password"
            label="Lozinka"
            type="password"
            id="password"
            autoComplete="off"
          />
          <TextField
            value={state.confirmedPassword}
            onChange={handleChange}
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="confirmedPassword"
            label="Potvrdite lozinku"
            type="password"
            name="confirmedPassword"
            autoComplete="off"
          />
        </>
      );
    default:
      return "Unknown step";
  }
}

const useStyles = makeStyles(theme => ({
  "@global": {
    body: {
      backgroundColor: theme.palette.common.white
    }
  },
  root: {
    width: "90%"
  },
  button: {
    marginRight: theme.spacing(1)
  },
  instructions: {
    marginTop: theme.spacing(1),
    marginBottom: theme.spacing(1)
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

const SignUp = ({ registerUser, currentUser: { error } }) => {
  const classes = useStyles();
  const [activeStep, setActiveStep] = useState(0);
  const steps = getSteps();

  const [registrated, setRegistrated] = useState(false);

  const [state, setState] = useState({
    email: "",
    password: "",
    confirmedPassword: "",
    telefon: "",
    jbzo: "",
    ime: "",
    prezime: "",
    adresa: "",
    drzava: "",
    grad: ""
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
    await registerUser(state);

    if (!error) {
      setRegistrated(true);
    }
  };

  const handleNext = () => {
    setActiveStep(prevActiveStep => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep(prevActiveStep => prevActiveStep - 1);
  };

  return (
    <>
      <AppBar />
      <Container component="main" maxWidth="md">
        <CssBaseline />
        {!registrated ? (
          <div>
            <div
              style={{
                marginTop: 64,
                display: "flex",
                flexDirection: "column",
                alignItems: "center"
              }}
            >
              <Typography component="h1" variant="h3">
                Registracija
              </Typography>
              <Avatar className={classes.avatar} src={logo}></Avatar>
            </div>
            <Stepper activeStep={activeStep}>
              {steps.map((label, index) => {
                return (
                  <Step key={label}>
                    <StepLabel>{label}</StepLabel>
                  </Step>
                );
              })}
            </Stepper>
            <form className={classes.form} noValidate onSubmit={handleSubmit}>
              <div className={classes.instructions}>
                {getStepContent(activeStep, state, handleChange)}
              </div>
              <Button
                disabled={activeStep === 0}
                onClick={handleBack}
                className={classes.button}
              >
                Nazad
              </Button>

              {activeStep !== steps.length - 1 ? (
                <Button
                  variant="contained"
                  color="primary"
                  onClick={handleNext}
                  className={classes.button}
                >
                  Dalje
                </Button>
              ) : (
                <Button
                  variant="contained"
                  color="primary"
                  className={classes.submit}
                  onClick={handleSubmit}
                >
                  Registrujte se
                </Button>
              )}
            </form>
          </div>
        ) : (
          <div
            style={{
              marginTop: 64
            }}
          >
            <Typography variant="h5" gutterBottom>
              Zahtev
            </Typography>
            <Divider />
            <List disablePadding>
              <ListItem className={classes.listItem}>
                <ListItemText primary="Ime" />
                <Typography variant="subtitle1" className={classes.total}>
                  {state.ime}
                </Typography>
              </ListItem>
              <ListItem className={classes.listItem}>
                <ListItemText primary="Prezime" />
                <Typography variant="subtitle1" className={classes.total}>
                  {state.prezime}
                </Typography>
              </ListItem>
              <Divider />
              <ListItem className={classes.listItem}>
                <ListItemText primary="Adresa" />
                <Typography variant="subtitle1" className={classes.total}>
                  {state.adresa}
                </Typography>
              </ListItem>
              <ListItem className={classes.listItem}>
                <ListItemText primary="Grad" />
                <Typography variant="subtitle1" className={classes.total}>
                  {state.grad}
                </Typography>
              </ListItem>
              <ListItem className={classes.listItem}>
                <ListItemText primary="Drzava" />
                <Typography variant="subtitle1" className={classes.total}>
                  {state.drzava}
                </Typography>
              </ListItem>
              <Divider />
              <ListItem className={classes.listItem}>
                <ListItemText primary="Broj osiguranika" />
                <Typography variant="subtitle1" className={classes.total}>
                  {state.jbzo}
                </Typography>
              </ListItem>
              <ListItem className={classes.listItem}>
                <ListItemText primary="Email" />
                <Typography variant="subtitle1" className={classes.total}>
                  {state.email}
                </Typography>
              </ListItem>
              <Divider />
            </List>
            <Typography style={{ marginTop: 40 }} variant="h6">
              Zahtev je formiran i poslat administratoru klinickog centra na
              revidiranje. Bicete obavesteni o aktivaciji putem email-a.
            </Typography>
          </div>
        )}
      </Container>
    </>
  );
};

function mapStateToProps(state) {
  return {
    currentUser: state.currentUser
  };
}

export default connect(mapStateToProps, { registerUser })(SignUp);
