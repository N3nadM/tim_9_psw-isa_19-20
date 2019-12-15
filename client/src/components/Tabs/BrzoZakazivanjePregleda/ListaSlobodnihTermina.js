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
import KalendarSala from "../BrzoZakazivanjePregleda/KalendarSala";

import { getPredefinisaniPregledi } from "../../../store/actions/pregled";

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
        Slobodni termini
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

const ListaSlobodnihTermina = ({
  termini,
  getPredefinisaniPregledi,
  klinikaId
}) => {
  useEffect(() => {
    getPredefinisaniPregledi(klinikaId);
  }, []);

  const classes = useStyles();
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("korisnik.ime");

  const [page, setPage] = React.useState(0);
  const [dense, setDense] = React.useState(false);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const [state, setState] = React.useState({
    broj: "",
    naziv: "",
    termin: ""
  });

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

  const [isEdit, setIsEdit] = React.useState(false);
  const handleSubmit = e => {
    e.preventDefault();
  };

  const handleChange = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  return (
    <div className={classes.root}>
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
                    active={orderBy === "termini.datum"}
                    direction={order}
                    onClick={() => handleRequestSort("termini.datum")}
                  >
                    Datum
                  </TableSortLabel>
                </TableCell>
                <TableCell align="left">
                  <TableSortLabel
                    active={orderBy === "termini.tipPregleda.naziv"}
                    direction={order}
                    onClick={() =>
                      handleRequestSort("termini.tipPregleda.naziv")
                    }
                  >
                    Tip pregleda
                  </TableSortLabel>
                </TableCell>
                <TableCell align="left">
                  <TableSortLabel
                    active={orderBy === "termini.sala.broj"}
                    direction={order}
                    onClick={() => handleRequestSort("termini.sala.broj")}
                  >
                    Broj sale
                  </TableSortLabel>
                </TableCell>
                <TableCell align="left">
                  <TableSortLabel
                    active={orderBy === "termini.lekar.korisnik.ime"}
                    direction={order}
                    onClick={() =>
                      handleRequestSort("termini.lekar.korisnik.ime")
                    }
                  >
                    Lekar
                  </TableSortLabel>
                </TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {termini &&
                stableSort(termini, getSorting(order, orderBy))
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
                          {row.datumPocetka}
                        </TableCell>
                        <TableCell align="left">
                          {row.tipPregleda.naziv}
                        </TableCell>
                        <TableCell align="right">
                          {row.sala.salaIdentifier}
                        </TableCell>
                        <TableCell align="right">
                          {row.lekar.korisnik.ime}
                        </TableCell>
                      </TableRow>
                    );
                  })}
              {termini &&
                rowsPerPage -
                  Math.min(rowsPerPage, termini.length - page * rowsPerPage) >
                  0 && (
                  <TableRow
                    style={{
                      height:
                        (dense ? 33 : 53) *
                        (rowsPerPage -
                          Math.min(
                            rowsPerPage,
                            termini.length - page * rowsPerPage
                          ))
                    }}
                  >
                    <TableCell colSpan={6} />
                  </TableRow>
                )}
            </TableBody>
          </Table>
        </div>
        {termini && (
          <TablePagination
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={termini.length}
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
        )}
      </Paper>
      <FormControlLabel
        control={<Switch checked={dense} onChange={handleChangeDense} />}
        label="Smanji pading"
      />
    </div>
  );
};

const mapStateToProps = state => ({
  termini: state.pregled.listaPredefinisanih
});

export default withRouter(
  connect(mapStateToProps, {
    getPredefinisaniPregledi
  })(ListaSlobodnihTermina)
);
