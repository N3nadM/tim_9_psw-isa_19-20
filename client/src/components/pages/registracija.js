import React from "react";
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

function getSteps() {
  return ["Korisnički podaci", "Lični podaci", "Registracija"];
}

function getStepContent(step) {
  switch (step) {
    case 0:
      return (
        <>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="email"
            label="Email adresa"
            name="email"
            autoComplete="email"
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="ime"
            label="Ime"
            name="ime"
            autoComplete="ime"
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="Prezime"
            label="Prezime"
            name="prezime"
            autoComplete="prezime"
          />
        </>
      );
    case 1:
      return (
        <>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="adresa"
            label="Adresa"
            name="adresa"
            autoComplete="adresa"
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="drzava"
            label="Država"
            name="drzava"
            autoComplete="drzava"
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="grad"
            label="Grad"
            name="grad"
            autoComplete="grad"
          />
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="telefon"
            label="Broj telefona"
            name="telefon"
            autoComplete="telefon"
          />
        </>
      );
    case 2:
      return (
        <>
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="brojOsiguranika"
            label="Broj osiguranika"
            name="brojOsiguranika"
            autoComplete="brojOsiguranika"
          />
          <TextField
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
          <TextField
            variant="outlined"
            margin="normal"
            required
            fullWidth
            id="ponovljenaLozinka"
            label="Potvrdite lozinku"
            type="password"
            name="ponovljenaLozinka"
            autoComplete="ponovljenaLozinka"
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

export default function SignUp() {
  const classes = useStyles();
  const [activeStep, setActiveStep] = React.useState(0);
  const steps = getSteps();

  const handleNext = () => {
    setActiveStep(prevActiveStep => prevActiveStep + 1);
  };

  const handleBack = () => {
    setActiveStep(prevActiveStep => prevActiveStep - 1);
  };

  return (
    <Container component="main" maxWidth="md">
      <CssBaseline />

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
        <form className={classes.form} noValidate>
          <div className={classes.instructions}>
            {getStepContent(activeStep)}
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
            >
              Registrujte se
            </Button>
          )}
        </form>
      </div>
    </Container>
  );
}
