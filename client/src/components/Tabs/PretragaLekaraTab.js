import React, { useEffect } from "react";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import TableSortLabel from "@material-ui/core/TableSortLabel";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Paper from "@material-ui/core/Paper";
import IconButton from "@material-ui/core/IconButton";
import Tooltip from "@material-ui/core/Tooltip";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Switch from "@material-ui/core/Switch";
import FilterListIcon from "@material-ui/icons/FilterList";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { Grid } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import TextField from "@material-ui/core/TextField";
import { getLekariNaKlinici } from "../../store/actions/lekar";
import { searchLekariNaKlinici } from "../../store/actions/lekar";
import IzmenaLekara from "../Tabs/IzmenaLekara";
import { editLekarByAdmin } from "../../store/actions/lekar";

function desc(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function stableSort(array, cmp) {
  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    const order = cmp(a[0], b[0]);
    if (order !== 0) return order;
    return a[1] - b[1];
  });
  return stabilizedThis.map(el => el[0]);
}

function getSorting(order, orderBy) {
  return order === "desc"
    ? (a, b) => desc(a, b, orderBy)
    : (a, b) => -desc(a, b, orderBy);
}

const useToolbarStyles = makeStyles(theme => ({
  title: {
    flex: "1 1 100%"
  }
}));

const EnhancedTableToolbar = () => {
  const classes = useToolbarStyles();

  return (
    <Toolbar>
      <Typography className={classes.title} variant="h6" id="tableTitle">
        Lekari
      </Typography>

      <Tooltip title="Filter list">
        <IconButton aria-label="filter list">
          <FilterListIcon />
        </IconButton>
      </Tooltip>
    </Toolbar>
  );
};

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: theme.spacing(3)
  },
  paper: {
    width: "100%",
    marginBottom: theme.spacing(2)
  },
  table: {
    minWidth: 750
  },
  tableWrapper: {
    overflowX: "auto"
  },
  visuallyHidden: {
    border: 0,
    clip: "rect(0 0 0 0)",
    height: 1,
    margin: -1,
    overflow: "hidden",
    padding: 0,
    position: "absolute",
    top: 20,
    width: 1
  },
  formControl: {
    minWidth: 120
  }
}));

const PretragaLekaraTab = ({
  lekari,
  getLekariNaKlinici,
  klinikaId,
  searchLekariNaKlinici,
  editLekarByAdmin
}) => {
  useEffect(() => {
    getLekariNaKlinici(klinikaId);
  }, []);

  const classes = useStyles();
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("korisnik.ime");

  const [page, setPage] = React.useState(0);
  const [dense, setDense] = React.useState(false);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleRequestSort = (property, event) => {
    const isDesc = orderBy === property && order === "desc";
    setOrder(isDesc ? "asc" : "desc");
    setOrderBy(property);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleChangeDense = event => {
    setDense(event.target.checked);
  };

  const [state, setState] = React.useState({
    imePretraga: "",
    prezimePretraga: "",
    emailPretraga: "",
    zaIzmenu: ""
  });

  const [isEdit, setIsEdit] = React.useState(false);
  const handleSubmit = e => {
    e.preventDefault();
    searchLekariNaKlinici(klinikaId, {
      ...state
    });
  };

  const handleChange = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  return (
    <div className={classes.root}>
      {isEdit && (
        <IzmenaLekara
          lekar={state.zaIzmenu}
          editLekarByAdmin={editLekarByAdmin}
          setIsEdit={setIsEdit}
        />
      )}
      {!isEdit && (
        <form onSubmit={handleSubmit}>
          <Paper style={{ padding: 50, marginBottom: 50 }}>
            <Grid container spacing={3}>
              <Grid item md={3}>
                <TextField
                  style={{ width: "80%" }}
                  margin="normal"
                  value={state.imePretraga}
                  onChange={handleChange}
                  fullWidth
                  name="imePretraga"
                  label="Ime lekara"
                  type="text"
                  id="imePretraga"
                />
              </Grid>
              <Grid item md={3}>
                <TextField
                  style={{ width: "80%" }}
                  margin="normal"
                  value={state.prezimePretraga}
                  onChange={handleChange}
                  fullWidth
                  name="prezimePretraga"
                  label="Prezime lekara"
                  type="text"
                  id="prezimePretraga"
                />
              </Grid>
              <Grid item md={2}>
                <TextField
                  style={{ width: "80%" }}
                  onChange={handleChange}
                  value={state.emailPretraga}
                  margin="normal"
                  fullWidth
                  name="emailPretraga"
                  label="E-mail lekara"
                  type="text"
                  id="emailPretraga"
                />
              </Grid>
              <Grid
                item
                md={3}
                style={{
                  display: "flex",
                  alignItems: "center",
                  justifyContent: "center"
                }}
              >
                <Button variant="contained" color="primary" type="submit">
                  Pretraži
                </Button>
              </Grid>
            </Grid>
          </Paper>
        </form>
      )}
      {!isEdit && lekari && !!lekari.length && (
        <Paper className={classes.paper}>
          <EnhancedTableToolbar />
          <div className={classes.tableWrapper}>
            <Table
              className={classes.table}
              aria-labelledby="tableTitle"
              size={dense ? "small" : "medium"}
              aria-label="enhanced table"
            >
              <TableHead>
                <TableRow>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "korisnik.ime"}
                      direction={order}
                      onClick={() => handleRequestSort("korisnik.ime")}
                    >
                      Ime
                    </TableSortLabel>
                  </TableCell>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "korisnik.prezime"}
                      direction={order}
                      onClick={() => handleRequestSort("korisnik.prezime")}
                    >
                      Prezime
                    </TableSortLabel>
                  </TableCell>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "korisnik.email"}
                      direction={order}
                      onClick={() => handleRequestSort("korisnik.email")}
                    >
                      E-mail
                    </TableSortLabel>
                  </TableCell>
                  <TableCell align="right"></TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {lekari &&
                  stableSort(lekari, getSorting(order, orderBy))
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((row, index) => {
                      return (
                        <TableRow
                          hover
                          role="checkbox"
                          tabIndex={-1}
                          key={row.id}
                        >
                          <TableCell component="th" allign="left">
                            {row.korisnik.ime}
                          </TableCell>
                          <TableCell align="left">
                            {row.korisnik.prezime}
                          </TableCell>
                          <TableCell align="left">
                            {row.korisnik.email}
                          </TableCell>
                          <TableCell align="right">
                            <Button
                              variant="outlined"
                              color="primary"
                              onClick={() => {
                                state.zaIzmenu = row;
                                setIsEdit(true);
                              }}
                            >
                              Izmeni
                            </Button>
                          </TableCell>
                        </TableRow>
                      );
                    })}
                {rowsPerPage -
                  Math.min(rowsPerPage, lekari.length - page * rowsPerPage) >
                  0 && (
                  <TableRow
                    style={{
                      height:
                        (dense ? 33 : 53) *
                        (rowsPerPage -
                          Math.min(
                            rowsPerPage,
                            lekari.length - page * rowsPerPage
                          ))
                    }}
                  >
                    <TableCell colSpan={6} />
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </div>
          <TablePagination
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={lekari.length}
            rowsPerPage={rowsPerPage}
            page={page}
            backIconButtonProps={{
              "aria-label": "previous page"
            }}
            nextIconButtonProps={{
              "aria-label": "next page"
            }}
            onChangePage={handleChangePage}
            onChangeRowsPerPage={handleChangeRowsPerPage}
          />
        </Paper>
      )}
      {!isEdit && (
        <FormControlLabel
          control={<Switch checked={dense} onChange={handleChangeDense} />}
          label="Smanji pading"
        />
      )}
    </div>
  );
};

const mapStateToProps = state => ({
  lekari: state.lekar.listaLekaraNaKlinici
});

export default withRouter(
  connect(mapStateToProps, {
    getLekariNaKlinici,
    searchLekariNaKlinici,
    editLekarByAdmin
  })(PretragaLekaraTab)
);
