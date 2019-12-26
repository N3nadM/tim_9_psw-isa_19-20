import React, { useState } from "react";
import Button from "@material-ui/core/Button";
import CssBaseline from "@material-ui/core/CssBaseline";
import TextField from "@material-ui/core/TextField";
import Typography from "@material-ui/core/Typography";
import { makeStyles } from "@material-ui/core/styles";
import Container from "@material-ui/core/Container";
import { connect } from "react-redux";
import ThumbUpIcon from "@material-ui/icons/ThumbUp";
import { authUser } from "../../../store/actions/auth";
import Axios from "axios";

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

const PromeniLozinku = ({ currentUser, authUser }) => {
  const classes = useStyles();
  const [promenjena, setPromenjena] = useState(null);
  const [state, setState] = useState({
    confirmedPassword: "",
    newPassword: "",
    oldPassword: ""
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
    const resp = await Axios.put("/api/users/changePassword", {
      newPassword: state.newPassword,
      confirmedPassword: state.confirmedPassword,
      oldPassword: state.oldPassword
    });
    setState({
      confirmedPassword: "",
      newPassword: "",
      oldPassword: ""
    });
    setPromenjena(resp.data);
    if (resp.data) {
      await authUser({
        username: currentUser.user.username,
        password: state.newPassword
      });
    }
  };

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <div className={classes.paper}>
        {promenjena ? (
          <div style={{ textAlign: "center" }}>
            <ThumbUpIcon style={{ fontSize: 155, color: "#4caf50" }} />
            <Typography component="h4" variant="h3">
              Uspesno ste promenili lozinku
            </Typography>
          </div>
        ) : (
          <>
            <Typography component="h1" variant="h3">
              Promena Lozinke
            </Typography>

            <form className={classes.form} noValidate onSubmit={handleSubmit}>
              <TextField
                error={promenjena === false}
                helperText={promenjena === false && "Unesite ispravnu sifru"}
                value={state.oldPassword}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
                required
                fullWidth
                type="password"
                id="password-old"
                label="Trenutna lozinka"
                name="oldPassword"
                autoFocus
              />
              <TextField
                value={state.newPassword}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
                required
                fullWidth
                type="password"
                id="password-n"
                label="Nova lozinka"
                name="newPassword"
                autoFocus
              />
              <TextField
                error={
                  (state.confirmedPassword !== state.newPassword ||
                    state.confirmedPassword.length < 5) &&
                  state.newPassword.length !== 0
                }
                helperText={
                  state.confirmedPassword !== state.newPassword
                    ? "Lozinke se moraju poklapati"
                    : state.newPassword.length < 5 &&
                      "Lozinka mora imati vise od 5 karaktera"
                }
                value={state.confirmedPassword}
                onChange={handleChange}
                variant="outlined"
                margin="normal"
                required
                fullWidth
                name="confirmedPassword"
                label="Lozinka"
                type="password"
                id="password"
              />
              <Button
                type="submit"
                fullWidth
                variant="contained"
                color="primary"
                className={classes.submit}
                disabled={
                  state.confirmedPassword !== state.newPassword ||
                  state.confirmedPassword.length < 5
                }
              >
                Promeni Lozinku
              </Button>
            </form>
          </>
        )}
      </div>
    </Container>
  );
};

function mapStateToProps(state) {
  return {
    currentUser: state.currentUser
  };
}

export default connect(mapStateToProps, { authUser })(PromeniLozinku);
