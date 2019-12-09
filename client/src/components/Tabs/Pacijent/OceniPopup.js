import React from "react";
import { makeStyles, withStyles } from "@material-ui/core/styles";
import Popover from "@material-ui/core/Popover";
import Button from "@material-ui/core/Button";
import ButtonGroup from "@material-ui/core/ButtonGroup";
import FavoriteIcon from "@material-ui/icons/Favorite";
import Rating from "@material-ui/lab/Rating";
import Box from "@material-ui/core/Box";
import Axios from "axios";
import CircularProgress from "@material-ui/core/CircularProgress";
import { green } from "@material-ui/core/colors";
import CheckIcon from "@material-ui/icons/Check";
import LocalHospitalIcon from "@material-ui/icons/LocalHospital";
import { Typography, Divider } from "@material-ui/core";

const useStyles = makeStyles(theme => ({
  wrapper: {
    margin: theme.spacing(1),
    position: "relative"
  },
  buttonProgress: {
    color: green[500],
    position: "absolute",
    top: "50%",
    left: "50%",
    marginTop: -18,
    marginLeft: -18
  }
}));

const StyledRating = withStyles({
  iconFilled: {
    color: "#ff6d75"
  },
  iconHover: {
    color: "#ff3d47"
  }
})(Rating);

export default function SimplePopover({ klinikaId, lekarId, lekari }) {
  const classes = useStyles();
  const [anchorEl, setAnchorEl] = React.useState(null);
  const [value, setValue] = React.useState(0);
  const [values, setValues] = React.useState([]);
  const [selected, setSelected] = React.useState(0);
  const [submited, setSubmited] = React.useState(0);
  const [lid, setLid] = React.useState(lekarId);

  React.useEffect(() => {}, [lid]);

  const handleClick = event => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
    setSelected(0);
    setSubmited(0);
  };

  const handleChose = async opt => {
    try {
      setSelected(opt);
      setSubmited(1);

      if (opt === 1) {
        if (lekari && lekari.length > 0) {
          const ocene = await Axios.post(`/api/lekar/ocenePacijenta`, {
            ocene: lekari.map(l => l.id)
          });
          console.log(ocene.data);
          const hashMap = ocene.data.reduce(
            (acc, curr) => ({ ...acc, [curr.lekar.id]: curr.ocena }),
            {}
          );
          setValues(hashMap);
        } else {
          const ocena = await Axios.get(`/api/lekar/ocenaPacijenta/${lid}`);
          setValue(ocena.data);
        }
      } else {
        const ocena = await Axios.get(
          `/api/klinika/ocenaPacijenta/${klinikaId}`
        );
        setValue(ocena.data);
      }

      setSubmited(0);
    } catch (err) {
      console.log(err);
    }
  };

  const handleRatingChange = async (event, newValue, newId = lid) => {
    setValue(newValue);
    setSubmited(1);
    try {
      if (selected === 1) {
        console.log(newId);
        const ocena = await Axios.post(`/api/lekar/oceni`, {
          id: newId,
          ocena: newValue
        });
      } else {
        const ocena = await Axios.post(`/api/klinika/oceni`, {
          id: klinikaId,
          ocena: newValue
        });
      }
      setSubmited(2);
      setTimeout(() => handleClose(), 1400);
    } catch (err) {
      console.log(err);
    }
  };

  const handleLekariRatingChange = (e, nv, newid) => {
    setLid(newid);
    handleRatingChange(e, nv, newid);
  };

  const open = Boolean(anchorEl);
  const id = open ? "simple-popover" : undefined;

  return (
    <div>
      <Button
        aria-describedby={id}
        variant="contained"
        color="primary"
        onClick={handleClick}
      >
        Oceni
      </Button>
      <Popover
        id={id}
        open={open}
        anchorEl={anchorEl}
        onClose={handleClose}
        anchorOrigin={{
          vertical: "top",
          horizontal: "left"
        }}
        transformOrigin={{
          vertical: "top",
          horizontal: "left"
        }}
      >
        {selected === 0 ? (
          <ButtonGroup
            color="secondary"
            size="large"
            aria-label="large outlined secondary button group"
          >
            <Button onClick={() => handleChose(1)}>Lekara</Button>
            <Button onClick={() => handleChose(2)}>Kliniku</Button>
          </ButtonGroup>
        ) : (
          <Box
            component="fieldset"
            style={{ padding: "8px 10px" }}
            borderColor="transparent"
            className={classes.wrapper}
          >
            {lekari && lekari.length > 0 && selected === 1 ? (
              lekari.map((l, i) => (
                <div key={l.id}>
                  {i != 0 && <Divider />}
                  <Typography>
                    {l.korisnik.ime} {l.korisnik.prezime}
                  </Typography>
                  <StyledRating
                    name="customized-color"
                    value={values[l.id]}
                    onChange={(e, newl) =>
                      handleLekariRatingChange(e, newl, l.id)
                    }
                    precision={1}
                    icon={<FavoriteIcon fontSize="inherit" />}
                    disabled={submited !== 0}
                  />
                </div>
              ))
            ) : (
              <StyledRating
                name="customized-color"
                value={value}
                onChange={handleRatingChange}
                precision={1}
                icon={
                  selected === 1 ? (
                    <FavoriteIcon fontSize="inherit" />
                  ) : (
                    <LocalHospitalIcon fontSize="inherit" />
                  )
                }
                disabled={submited !== 0}
              />
            )}
            {submited === 1 && (
              <CircularProgress size={36} className={classes.buttonProgress} />
            )}
            {submited === 2 && (
              <CheckIcon fontSize="large" className={classes.buttonProgress} />
            )}
          </Box>
        )}
      </Popover>
    </div>
  );
}
